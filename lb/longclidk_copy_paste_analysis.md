关于browser长按事件的分析结果：

## 1整体流程事件分析，设备事件由硬件触发，包括鼠标、键盘、触摸等事件，有底层封装成EventHub传递到上来，我们在window曾进行接收，通过InputChannle接收事件；接下流事件有InputEventRecevied.java类处理，转换成InputEvent事件，经由PhoneWindow分发处理，此时会有与Activity的交互，之后就是进入到viewGroup以及view 层级的进一步分发处理最后与performLongClick事件消化，长按事件每次必调时间setOnLongClickListener,它为longclickListenr对象赋值,performLongClick()方法根据listner对象情况，调用showContexMenu,展示长按事件后的contextmenu
setOnLongClickListener的调用堆栈 信息：
- 结果：
I/View    ( 3425):      at android.view.View.setOnLongClickListener(View.java:4762)
I/View    ( 3425):      at com.android.internal.view.menu.ActionMenuItemView.<init>(ActionMenuItemView.java:87)
I/View    ( 3425):      at com.android.internal.view.menu.ActionMenuItemView.<init>(ActionMenuItemView.java:69)                                                                                 I/View    ( 3425):      at com.android.internal.view.menu.ActionMenuItemView.<init>(ActionMenuItemView.java:65)                                                                                 I/View    ( 3425):      at java.lang.reflect.Constructor.newInstance(Native Method)                                                                                                             I/View    ( 3425):      at java.lang.reflect.Constructor.newInstance(Constructor.java:288)
I/View    ( 3425):      at android.view.LayoutInflater.createView(LayoutInflater.java:607)                                                                                                      I/View    ( 3425):      at android.view.LayoutInflater.createViewFromTag(LayoutInflater.java:743)
I/View    ( 3425):      at android.view.LayoutInflater.inflate(LayoutInflater.java:482)                                                                                                         I/View    ( 3425):      at android.view.LayoutInflater.inflate(LayoutInflater.java:414)                                                                                                         I/View    ( 3425):      at com.android.internal.view.menu.BaseMenuPresenter.createItemView(BaseMenuPresenter.java:157)                                                                          I/View    ( 3425):      at com.android.internal.view.menu.BaseMenuPresenter.getItemView(BaseMenuPresenter.java:176)
I/View    ( 3425):      at android.widget.ActionMenuPresenter.getItemView(ActionMenuPresenter.java:171)
I/View    ( 3425):      at android.widget.ActionMenuPresenter.flagActionItems(ActionMenuPresenter.java:430)
I/View    ( 3425):      at com.android.internal.view.menu.MenuBuilder.flagActionItems(MenuBuilder.java:1082)                                                                                    I/View    ( 3425):      at com.android.internal.view.menu.BaseMenuPresenter.updateMenuView(BaseMenuPresenter.java:87)
I/View    ( 3425):      at android.widget.ActionMenuPresenter.updateMenuView(ActionMenuPresenter.java:208)
I/View    ( 3425):      at com.android.internal.view.menu.BaseMenuPresenter.getMenuView(BaseMenuPresenter.java:72)                                                                              I/View    ( 3425):      at android.widget.ActionMenuPresenter.getMenuView(ActionMenuPresenter.java:162)
I/View    ( 3425):      at com.android.internal.widget.ActionBarContextView.initForMode(ActionBarContextView.java:243)                                                                          I/View    ( 3425):      at com.android.internal.app.WindowDecorActionBar.startActionMode(WindowDecorActionBar.java:502)
I/View    ( 3425):      at android.app.Activity.onWindowStartingActionMode(Activity.java:5712)                                                                                                  I/View    ( 3425):      at com.android.internal.policy.impl.PhoneWindow$DecorView.startActionMode(PhoneWindow.java:2930)
I/View    ( 3425):      at com.android.internal.policy.impl.PhoneWindow$DecorView.startActionModeForChild(PhoneWindow.java:2917)                                                                I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)                                                                                                   I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)                                                                                                   I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)                                                                                                   I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)
I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)
I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)                                                                                                   I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)                                                                                                   I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)
I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)
I/View    ( 3425):      at android.view.ViewGroup.startActionModeForChild(ViewGroup.java:706)
I/View    ( 3425):      at android.view.View.startActionMode(View.java:4902)
I/View    ( 3425):      at com.android.org.chromium.content.browser.ContentViewCore.showSelectActionBar(ContentViewCore.java:1962)                                                              I/View    ( 3425):      at com.android.org.chromium.content.browser.ContentViewCore.onSelectionEvent(ContentViewCore.java:2011)                                                                 I/View    ( 3425):      at com.android.org.chromium.android_webview.AwContents.nativeOnDraw(Native Method)                                                                                      I/View    ( 3425):      at com.android.org.chromium.android_webview.AwContents.access$4000(AwContents.java:85)
I/View    ( 3425):      at com.android.org.chromium.android_webview.AwContents$AwViewMethodsImpl.onDraw(AwContents.java:2288)
I/View    ( 3425):      at com.android.org.chromium.android_webview.AwContents.onDraw(AwContents.java:1014)                                                                                     I/View    ( 3425):      at com.android.webview.chromium.WebViewChromium.onDraw(WebViewChromium.java:1706)
I/View    ( 3425):      at android.webkit.WebView.onDraw(WebView.java:2393)
I/View    ( 3425):      at com.android.browser.BrowserWebView.onDraw(BrowserWebView.java:115)
I/View    ( 3425):      at android.view.View.draw(View.java:15274)                                                                                                                              I/View    ( 3425):      at android.view.View.updateDisplayListIfDirty(View.java:14210)                                                                                                          I/View    ( 3425):      at android.view.View.getDisplayList(View.java:14232)                                                                                                                    I/View    ( 3425):      at android.view.ViewGroup.recreateChildDisplayList(ViewGroup.java:3395)                                                                                                 I/View    ( 3425):      at android.view.ViewGroup.dispatchGetDisplayList(ViewGroup.java:3374)
I/View    ( 3425):      at android.view.View.updateDisplayListIfDirty(View.java:14170)
I/View    ( 3425):      at android.view.View.getDisplayList(View.java:14232)
I/View    ( 3425):      at android.view.ViewGroup.recreateChildDisplayList(ViewGroup.java:3395)
I/View    ( 3425):      at android.views  

## 2
事件分发流程：
参考文档：
http://blog.waynell.com/2015/04/20/android-touch-01/
http://blog.waynell.com/2015/05/09/android-touch-02/

父view的事件分发最后一次分发返回true,走进了webView进行了详细处理，关键点找到webView的触发点
分析log结果：
11-09 16:20:59.223 I/libing  ( 3937): ====dispatch======enter dispatch  and ====onTouchEvent()=====                                                                                             11-09 16:20:59.223 I/libing  ( 3937): =====onTouchEvent===inner====enter toucheEvent====                                                                                                        11-09 16:20:59.223 I/libing  ( 3937): =====onTouchEvent======= Callnum=========4                                                                                                                11-09 16:20:59.223 I/libing  ( 3937): ====befor==if 2=====mTouchDelegate=====null                                                                                                               11-09 16:20:59.223 I/libing  ( 3937): =======before===if 3===swtich=====click=====false======longclick====false                                                                                 11-09 16:20:59.223 I/libing  ( 3937): ===onTouchEvent===out=====end==========
11-09 16:20:59.223 I/libing  ( 3937): =====dispatch==checked touch event=====false
11-09 16:20:59.223 I/libing  ( 3937): ====dispatch======enter dispatch  and ====onTouchEvent()=====
11-09 16:20:59.223 I/libing  ( 3937): ====webView===enter onTouchEvent=====
11-09 16:20:59.223 I/libing  ( 3937): ====dispatch========enter dispatchOnTouchEvent true=======
11-09 16:20:59.223 I/libing  ( 3937): =====dispatch==checked touch event=====true


复制粘贴跨进程问题（跨进程的情况有两类，一类是从浏览器无法往termianl复制，一类是浏览器内部部分网站无法copy,例如emind邮箱）
1.针对于复制粘贴来说， 整体流程比较简单，上次由ClipbordManager,java控制，复制主要实现setPrimaryClip()方法，而粘贴主要实现getPrimaryClip()方法，而服务层ClipboardService进行一些具体实现。
对于一般文本来说，大部分继承自TextView.java类，具体复制粘贴在父类控制即可。但浏览器内部粘贴同样涉及到webView,

 调用getPrimaryClip的堆栈信息：

11-10 16:42:27.287 I/libing  ( 3321): ====ClipboardManager====setPrimaryClip====clip==ClipData { text/plain {NULL} }                                                                       3683 11-10 16:42:27.288 I/ClipboardManager( 3321): java.lang.Throwable                                                                                                                          3684 11-10 16:42:27.288 I/ClipboardManager( 3321):   at android.content.ClipboardManager.setPrimaryClip(ClipboardManager.java:122)
3685 11-10 16:42:27.288 I/ClipboardManager( 3321):   at com.android.org.chromium.ui.base.Clipboard.setPrimaryClipNoException(Clipboard.java:158)                                                3686 11-10 16:42:27.288 I/ClipboardManager( 3321):   at com.android.org.chromium.ui.base.Clipboard.setText(Clipboard.java:107)                                                                  3687 11-10 16:42:27.288 I/ClipboardManager( 3321):   at com.android.org.chromium.ui.base.Clipboard.setText(Clipboard.java:120)                                                                  3688 11-10 16:42:27.288 I/ClipboardManager( 3321):   at com.android.org.chromium.base.SystemMessageHandler.nativeDoRunLoopOnce(Native Method)                                                   3689 11-10 16:42:27.288 I/ClipboardManager( 3321):   at com.android.org.chromium.base.SystemMessageHandler.handleMessage(SystemMessageHandler.java:53)                                          3690 11-10 16:42:27.288 I/ClipboardManager( 3321):   at android.os.Handler.dispatchMessage(Handler.java:102)
3691 11-10 16:42:27.288 I/ClipboardManager( 3321):   at android.os.Looper.loop(Looper.java:135)                                                                                                 3692 11-10 16:42:27.288 I/ClipboardManager( 3321):   at android.app.ActivityThread.main(ActivityThread.java:5254)                                                                               3693 11-10 16:42:27.288 I/ClipboardManager( 3321):   at java.lang.reflect.Method.invoke(Native Method)                                                                                          3694 11-10 16:42:27.288 I/ClipboardManager( 3321):   at java.lang.reflect.Method.invoke(Method.java:372)
3695 11-10 16:42:27.288 I/ClipboardManager( 3321):   at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:903)
3696 11-10 16:42:27.288 I/ClipboardManager( 3321):   at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:698)

然而在emind邮箱中，根本没有触发该方法
