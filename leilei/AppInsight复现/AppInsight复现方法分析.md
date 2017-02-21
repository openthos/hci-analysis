## AppInsight复现方法分析

### 一、AppInsight论文关键点摘要：
1. **插桩位置**：interposing on event handlers.

1. **性能分析目标**：
 * automatically highlight the critical paths for the longest user transactions. 
 * group similar user transactions together and correlate variability in their performance with variation in the environment.

1. **二进制前提**：
 * based on the fact that phone apps are often written using higher-level frameworks and compiled to an intermediate language (byte code). 
 * Our current implementation is designed for apps written using the Silverlight framework, compiled to MSIL byte code.

1. **instrumentation**:
 1. **目标**：capture the information necessary to build execution traces of user transactions and identify their critical paths and exception paths with minimal overhead.
 1. **跟踪的详细程度**：strike the right balance between the overhead and our ability to give useful feedback to the developer

 1. **步骤**：instrument the app in three steps
   1. read the app binary and assign a unique identifier to all methods in the app code and to system calls.Each call site is considered unique;This mapping is stored in a metadata file

      需要知道点击startmenu执行的所有应用程序方法和系统方法，并对每一个调用唯一编号，将编号和函数名映射表保存在文件中

    1. link two libraries to the app
      * **The Detour library** is dynamically generated during instrumentation. It exports a series of detouring functions , which　help attribute callback executions to the asynchronous calls that triggered them.

      输出一系列的detour方法，帮助将回调函数执行归因于触发它们的异步调用点
      * **The Logger library** exports several logging functions and event handlers that insert trace records into a memory buffer. Each record is tagged with a timestamp and the id of the thread that called the logging function. The buffer is flushed to stable storage to prevent overflow as needed. When the app exits, the buffer is scheduled for upload using BTS.
       输出一系列log方法和事件处理handlers
    1. instrument the app binary with calls to　methods in the Logger and Detour libraries from appropriate places to collect the data we need

 1. **捕捉６类数据**：
    1. **when the user manipulates the UI;——Capturing UI Manipulation events**

       **分析**：When the user interacts with the UI，the Silverlight framework delivers several UI input events　on the UI thread of the app running in the foreground.The first event in this series is always a ManipulationStarted event, and the last is always the ManipulationEnded event. Further, any app-specified handler to handle the UI event is also called on the UI thread in between these two events.

       http://blog.csdn.net/ddna/article/details/5451722

       onClick被触发的基本时序的Log:

       `04-05 05:57:47.123: DEBUG/TSActivity(209): onTouch ACTION_DOWN`

       `04-05 05:57:47.263: DEBUG/TSActivity(209): onTouch ACTION_UP`

       `04-05 05:57:47.323: DEBUG/TSActivity(209): onClick`

       可以看出是按ACTION_DOWN -> ACTION_UP -> onClick的次序发生的。

       **方法**：The logger library exports handlers for ManipulationStarted and ManipulationEnded events, which we add to　the app’s code. The handlers log the times of the events,which allows us to match the UI manipulation to the right　app handler for that UI input.

       https://zhidao.baidu.com/question/560651725675055844.html

       onTouch onClick onLongClick 的区别

     1. **when the app code executes on various threads (i.e.,start and end of horizontal line segments);——Capturing thread execution**

        **分析**：at the beginning　of each horizontal line segment in Figure 2, the top frame　in the thread’s stack corresponds to an app method ，and that this method is the only app method on the stack.These methods are upcalls from the framework into the　app code.

        找到系统方法调用的所有应用方法

        **方法**：identify all potential upcall methods。　When a method is specified as a callback to a　system call, a reference to it, a function pointer, called delegate is passed to the system call.Any method that is referenced by these opcodes may be called as an upcall.

     1. **causality between asynchronous calls and callbacks;  ——Matching async calls to their callbacks**

        (1)identify all call sites where an asynchronous system call was made

           找到所有系统异步调用点

           **方法**：any system call that accepts a delegate as an argument, is an asynchronous call.

        (2)log when the callback started executing as an upcall

        (3)connect the beginning of callback execution to the right asynchronous call
           
           **分析**：a single callback function　may be specified as a callback for several asynchronous system calls.One possibility is to rewrite the app code to clone the callback function several times, and assign them unique ids.since the asynchronous call may be called in a loop and specify the same function as a callback.

           **方法**：rewrite the callback methods to detour them　through the Detour library

        (4)总方法：

           1)identify the system call BeginGetResponse as an asynchronous call. The instrumenter has assigned a call id of 7 to this call site. We log the call site id, and the start and end time of the call

           2)We generate a new method called cb1 that matches the signature of the supplied callback function, i.e., reqCallback, and add it to the Detour class in the Detour library. This method is responsible for invoking the original callback

           3)We instrument the call site to call GetDetour to generate a new instance of the Detour object. This object stores the original callback, and is assigned a unique id (called matchId) at runtime. This matchId helps match the asynchronous call to the callback.

           4)We then rewrite the app code to replace the original callback argument with the newly generated detour method, Detour.cb1.

     1. **thread　synchronization points (e.g., through Wait calls) and their　causal relationship; ——Capturing Thread Synchronization**

        **分析**：Silverlight provides a set of methods for thread synchronization.

        **方法**：log calls to these functions and the identities of semaphore objects　they use. These object ids can be used to determine the causal relationship between synchronization calls. Waiting on multiple objects, and thread join calls are handled similarly.

     1. **when the UI was updated; 　——Capturing UI updates**

        **分析**：The Silverlight framework generates a special LayoutUpdated event whenever the app finishes updating the UI.

        **方法**：The Logger library exports a handler for this event, which we add to the app code. The handler logs the time this event was raised.

     1. **any unhandled exceptions. ——Capturing unhandled exceptions**

        **分析**：When an unhandled exception occurs in the app code,the system delivers a special event to the app. The data contains the exception type and the stack trace of the thread in which the exception occurred.

        **方法**：To log this data, the logger library exports a handler for this event, which we add to the app code.

     1. **some additional data, as discussed in § 5.7**

        For certain asynchronous calls such as web requests and　GPS calls, we collect additional information both at the　call and at the callback.
 
        For example, for web request　calls, we log the URL and the network state. For GPS　calls, we log the state of the GPS.

 1. **结果**：
   * **web-based interface** provides the developers with information on the critical path through their code for every user transaction.
    * **critical paths for user transactions exception paths** when apps fail during a transaction.

### 二、复现方法分析
1. **关键思想**：匹配代码位置，然后插入或重写代码
1. **检测粒度**：框架调用和回调层，即检测应用如何同Android框架交互以及框架反过来如何调用应用。
1. **目标**：以最小的开销捕捉必要的信息来构建用户事务的执行轨迹，并且识别关键路径（先不考虑异常路径）
1. **需要捕捉的数据**：
 1. **when the user manipulates the UI;——Capturing UI Manipulation events**

    当用户与UI交互时，android framework 给在前台运行的app的UI线程发送几个UI输入事件，其中第一个事件就是用户开始操作UI的事件，最后一个事件就是用户结束操作UI的事件。

    http://blog.csdn.net/ddna/article/details/5451722

    https://zhidao.baidu.com/question/560651725675055844.html　　　　　　　　

    onClick被触发的基本时序的Log:

    `04-05 05:57:47.123: DEBUG/TSActivity(209): onTouch ACTION_DOWN`

    `04-05 05:57:47.263: DEBUG/TSActivity(209): onTouch ACTION_UP`

    `04-05 05:57:47.323: DEBUG/TSActivity(209): onClick`

    可以看出是按ACTION_DOWN -> ACTION_UP -> onClick的次序发生的。

    添加ACTION_DOWN和ACTION_UP的handler打印log，获取时间。

 1. **when the app code executes on various threads (i.e.,start and end of horizontal line segments);——Capturing thread execution**

    找到所有的_回调方法_，为每个方法调用唯一标号，在它的开始和结束处打印log，获取时间。

 1. **causality between asynchronous calls and callbacks; ——Matching async calls to their callbacks**

    1. 找到所有的_系统异步调用方法_（app代码中使用的系统异步调用方法），为每个方法唯一标号

    1. 找到系统异步调用方法对应的回调方法的开始时间（和结束时间）

    1. 重写回调方法来detour

 1. **thread synchronization points (e.g., through Wait calls) and their causal relationship; ——Capturing Thread Synchronization**

    线程同步方法

 1. **when the UI was updated; 　——Capturing UI updates**   

    UI更新不是事件而是方法

1. **添加两个类库完成代码插入和重写**：

 * The Detour library:输出一系列的detour方法，帮助将回调函数执行归因于触发它们的异步调用点，
 * The Logger library:输出一系列log方法和事件处理handlers 


