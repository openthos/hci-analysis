### 一、进入docker
1. ssh lh@192.168.0.180   密码：oto124
1. docker start -ai ll-5.1
1. cd ll

### 二、创建lh@192.168.0.180的工作目录
1. ssh lh@192.168.0.180   密码：oto124
1. cd ll
1. mkdir WORKING_DIRECTORY
1. cd WORKING_DIRECTORY

### 三、下载repo 工具:（docker和lh@192.168.0.180都可以同步代码）
1. mkdir ~/ll/bin
1. PATH=~/ll/bin:$PATH
1. curl http://192.168.0.185/git-repo-downloads/repo > ~/ll/bin/repo #repo也可下载： https://storage.googleapis.com/git-repo-downloads/repo
1. chmod a+x ~/ll/bin/repo

### 四、初始化仓库, 并选择multiwindow Android-x86 版本:
1. repo init -u git://192.168.0.185/lollipop-x86/manifest.git -b multiwindow  
1. 如果提示无法连接到 gerrit.googlesource.com，可以编辑 ~/bin/repo，把 REPO_URL 一行替换成下面的：
REPO_URL = 'git://192.168.0.185/git-repo' 或清华大学的源：REPO_URL = 'https://gerrit-google.tuna.tsinghua.edu.cn/git-repo'
1. 同步源码树（以后只需执行这条命令来同步）：
repo sync

### 五、Add MyLog
1. 登录lh@192.168.0.180
1. cd WORKING_DIRECTORY
1. MyLog格式：

* Slog.i(TAG, "MyLog:所在函数:调用函数:说明");

* Slog.i(TAG, "MyLog:system server.run():system server.startOtherServices():system server start other service including input manager service");

### 六、获取MyLog
1. adb connect 192.168.0.141(openthos的IP)连接上openthos
1. adb logcat -v time -d -f /home/ll/LL/OpenthosLog/logcat.txt
1. 输出结果：

* 11-24 20:20:03.652 I/SystemServer( 1996): MyLog:system server.run():system server.startOtherServices():system server start other service including input manager service












