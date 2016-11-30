# Android 事件输入系统
* Service端的实现在/frameworks/native/services/inputflinger/下。
* 通用部分的实现在/frameworks/native/libs/input/下。

1. SystemServer中初始化IMS，然后依次初始化
NativeInputManager，EventHub，InputManager,InputDispatcher，InputReader，InputReaderThread, InputDispatcherThread
    * NativeInputManager：IMS和InputManager的中间层，将IMS的请求转化为对InputManager及其内部对象的操作，同时将InputManager中模块的请求通过JNI调回IMS。
    * InputManager：输入控制中心，它有两个关键线程InputReaderThread和InputDispatcherThread，它们的主要功能部分分别在InputReader和InputDispacher。前者用于从设备中读取事件，后者将事件分发给目标窗口。
    * EventHub：负责处理输入设备的增减，查询，输入事件的处理并向上层提供getEvents()接口接收事件。
1. SystemServer调用start()函数让InputManager中两个线程开始运行。
1. InputReaderThread读取事件。
    1. InputReaderThread不断调用InputReader的pollOnce()->getEvents()函数来得到事件
    1. 第一次进入时会扫描/dev/input目录建立设备列表，存在mDevice成员变量中(EventHub中有设备列表KeyedVector<int32_t, Device*> mDevices；），同时将增加的fd加到epoll的等待集合中。
    1. 在接下来的epoll_wait()等待时，如果有事件就会返回，同时返回可读事件数量。在这里，从Input driver读出的事件从原始的input_event结构转为RawEvent结构，放到getEvents()的输出参数buffer中。
    1. getEvents()返回后，InputReader调用processEventsLocked()处理事件
        * 对于设备改变，会根据实际情况调用addDeviceLocked(), removeDeviceLocked()和handleConfigurationChangedLocked()（InputReader中也有设备列表KeyedVector<int32_t, InputDevice*> mDevices）并注册的InputMapper然后notifyConfigurationChanged()将事件处理请求放入缓冲队列（QueuedInputListener中的mArgsQueue）。
        * 对于其它设备中来的输入事件，会调用processEventsForDeviceLocked()进一步处理。其中会根据当时注册的InputMapper解析原始输入事件(比如back, home等VirtualKey，传上来时是个Touch事件，这里要根据坐标转化为相应的按键事件。再比如多点触摸时，需要计算每个触摸点分别属于哪条轨迹，这本质上是个二分图匹配问题)，然后将事件处理请求放入缓冲队列（QueuedInputListener中的mArgsQueue）。
    1. InputReader的loopOnce()的结尾会调用QueuedInputListener::flush()统一回调缓冲队列中各元素的notify()接口,以按键事件为例，最后会调用到InputDispatcher的notifyKey()函数中。
        1. 这里先将参数封装成KeyEvent,把它作为参数调用NativeInputManager的interceptKeyBeforeQueueing()函数。就是在放到待处理队列前看看是不是需要系统处理的系统按键，它会通过JNI调回Java世界，最终调到PhoneWindowManager的interceptKeyBeforeQueueing()。
        1. 基于输入事件信息创建KeyEntry对象，调用enqueueInboundEventLocked()将之放入队列mInboundQueue等待InputDiaptcherThread线程拿出处理。
1. InputDispatcher处理分发事件
    1. InputDispatcher的dispatchOnce()函数中，dispatchOnceInnerLocked()会根据拿出的EventEntry类型调用相应的处理函数，以Key事件为例会调用dispatchKeyLocked()
        * 如果是个需要系统处理的Key事件，这里会封装成CommandEntry插入到mCommandQueue队列中，后面的runCommandLockedInterruptible()函数中会调用doInterceptKeyBeforeDispatchingLockedInterruptible()来让PWM有机会进行处理。
        * 非系统Key事件会找到目标窗口
            1. InputDispatcher成员变量mFocusedWindowHandle指示了焦点窗口，findFocusedWindowTargetsLocked()会调用一系列函数（handleTargetsNotReadyLocked(), checkInjectionPermission(), checkWindowReadyForMoreInputLocked()等）检查mFocusedWindowHandle是否能接收输入事件。如果可以，将之以InputTarget的形式加到目标窗口数组中。然后就会调用dispatchEventLocked()进行发送。
          然后通过之前和App间建立的连接发送事件。
          窗口连接的管理及焦点窗口的管理
          在App端，新的顶层窗口需要被注册到WMS中，这是在ViewRootImpl::setView()中做的。先创建InputChannel，注意还没初始化。ViewRootImpl通过Session中的addToDisplay()会最终调用WMS的addWindow()。在WMS中，会创建一对InputChannel，本质上是一对本地socket。然后一个注册给InputDispatcher，一个作为输出参数传回给App的ViewRootImpl，完成InputChannel的初始化。这样就建立了App与IMS的一对连接。
    1. 最后dispatchOnce()调用pollOnce()从和App的连接上接收处理完成消息。

