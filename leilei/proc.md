1.1.1.9 /proc/diskstats(http://blog.csdn.net/tenfyguo/article/details/7477526)
查看出具体的磁盘IO压力
每列参数的含义：
Column1 主设备号
Column2 次设备号
Column3 设备名称：sda为整个硬盘的统计信息，sda1为第一个分区的统计信息，sda2为第二个分区的统计信息。ramdisk设备为通过软件将RAM当做硬盘来使用的
一项技术。
Column4 成功完成读磁盘的总次数
Column5 成功完成合并读磁盘的总次数——为了效率可能会合并相邻的读和写。从而两次4K的读在它最终被处理到磁盘上之前可能会变成一次8K的读，才被计数和排队，
因此只有一次I/O操作。这个域使你知道这样的操作有多频繁。
Column6 成功完成读扇区的总次数
Column7 所有读操作所花费的毫秒数
Column8 成功完成写磁盘的总次数
Column9 成功完成合并写磁盘的总次数 
Column10 成功完成写扇区的总次数
Column11 所有写操作所花费的毫秒数
Column12 正在处理的输入/输出请求数 -- -I/O的当前进度，只有这个域可以是0。当请求被交给适当的request_queue_t时增加，当请求完成时减小
Column13 正在处理的输入/输出花的时间（毫秒），这个字段是Column12的一个累加值，这个域会增长只要Column12不为0。
Column14 正在处理的输入/输出操作花费的加权毫秒数—— 在每次I/O启动，I/O完成，I/O合并或读取由正在进行的I/O数（字段12）乘以执行I/O的毫秒数的这些统
计信息时增加。这可以提供I/O完成时间和可能积累的积压的简单测量。
ll@ll-pc:~$ cat /proc/diskstats
   1       0 ram0 0 0 0 0 0 0 0 0 0 0 0
   1       1 ram1 0 0 0 0 0 0 0 0 0 0 0
   1       2 ram2 0 0 0 0 0 0 0 0 0 0 0
   1       3 ram3 0 0 0 0 0 0 0 0 0 0 0
   1       4 ram4 0 0 0 0 0 0 0 0 0 0 0
   1       5 ram5 0 0 0 0 0 0 0 0 0 0 0
   1       6 ram6 0 0 0 0 0 0 0 0 0 0 0
   1       7 ram7 0 0 0 0 0 0 0 0 0 0 0
   1       8 ram8 0 0 0 0 0 0 0 0 0 0 0
   1       9 ram9 0 0 0 0 0 0 0 0 0 0 0
   1      10 ram10 0 0 0 0 0 0 0 0 0 0 0
   1      11 ram11 0 0 0 0 0 0 0 0 0 0 0
   1      12 ram12 0 0 0 0 0 0 0 0 0 0 0
   1      13 ram13 0 0 0 0 0 0 0 0 0 0 0
   1      14 ram14 0 0 0 0 0 0 0 0 0 0 0
   1      15 ram15 0 0 0 0 0 0 0 0 0 0 0
   7       0 loop0 0 0 0 0 0 0 0 0 0 0 0
   7       1 loop1 0 0 0 0 0 0 0 0 0 0 0
   7       2 loop2 0 0 0 0 0 0 0 0 0 0 0
   7       3 loop3 0 0 0 0 0 0 0 0 0 0 0
   7       4 loop4 0 0 0 0 0 0 0 0 0 0 0
   7       5 loop5 0 0 0 0 0 0 0 0 0 0 0
   7       6 loop6 0 0 0 0 0 0 0 0 0 0 0
   7       7 loop7 0 0 0 0 0 0 0 0 0 0 0
  11       0 sr0 0 0 0 0 0 0 0 0 0 0 0
   8       0 sda 379781 9055 19064906 2750192 3807693 3646522 308304394 94060300 0 14625908 96816640
   8       1 sda1 6655 3440 82328 32324 12895 86785 797856 186964 0 70092 219284
   8       2 sda2 5 0 10 56 0 0 0 0 0 56 56
   8       5 sda5 433 12 4452 6760 137 54 394 2616 0 6676 9376
   8       6 sda6 22972 376 546410 186916 798814 272048 11265048 3313040 0 1691624 3499128
   8       7 sda7 189056 2860 5807138 1414244 29146 53243 824928 1661940 0 652664 3076076
   8       8 sda8 110777 1877 9056456 719928 2843556 3131879 266535360 75681448 0 11551404 76408580
   8       9 sda9 21138 275 1906288 192508 1969 2044 638048 113800 0 100716 306280
   8      10 sda10 333 1 4488 8056 308 143 3608 16028 0 16820 24084
   8      11 sda11 28202 214 1654336 188428 103122 100326 28239152 12786744 0 968572 12975072
1.1.1.10 /proc/cpuinfo
查看系统中CPU的提供商和相关配置信息
ll@ll-pc:~$ cat /proc/cpuinfo
processor	: 0      逻辑处理器的id。对于单核处理器，则可认为是其CPU编号，对于多核处理器则可以是物理核、或者使用超线程技术虚拟的逻辑核
vendor_id	: GenuineIntel　　CPU制造商
cpu family	: 6　　　CPU产品系列代号
model		: 63　　　　CPU属于其系列中的哪一代的代号
model name	: Intel(R) Xeon(R) CPU E5-1603 v3 @ 2.80GHz　　CPU属于的名字及其编号、主频
stepping	: 2　　　　CPU属于制作更新版本
microcode	: 0x27
cpu MHz		: 1199.953　　　CPU的实际使用主频
cache size	: 10240 KB　　　CPU二级缓存大小
physical id	: 0　　　 物理封装的处理器的id
siblings	: 4　　　　位于相同物理封装的处理器中的逻辑处理器的数量
core id		: 0　　　　每个核心的id
cpu cores	: 4　　　　位于相同物理封装的处理器中的内核数量
apicid		: 0　　　　用来区分不同逻辑核的编号，系统中每个逻辑核的此编号必然不同，此编号不一定连续
initial apicid	: 0
fpu		: yes　　　　　是否具有浮点运算单元（Floating Point Unit）
fpu_exception	: yes　　　　是否支持浮点计算异常
cpuid level	: 15　　　　执行cpuid指令前，eax寄存器中的值，根据不同的值cpuid指令会返回不同的内容
wp		: yes　　　　表明当前CPU是否在内核态支持对用户空间的写保护（Write Protection）
flags		: fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush dts acpi mmx fxsr sse sse2 ss ht tm 
pbe syscall nx pdpe1gb rdtscp lm constant_tsc arch_perfmon pebs bts rep_good nopl xtopology nonstop_tsc aperfmperf eagerfpu 
pni pclmulqdq dtes64 monitor ds_cpl vmx smx est tm2 ssse3 fma cx16 xtpr pdcm pcid dca sse4_1 sse4_2 x2apic movbe popcnt 
tsc_deadline_timer aes xsave avx f16c rdrand lahf_lm abm arat epb pln pts dtherm tpr_shadow vnmi flexpriority ept vpid fsgsbase 
tsc_adjust bmi1 avx2 smep bmi2 erms invpcid cqm xsaveopt cqm_llc cqm_occup_llc　　　　当前CPU支持的功能
bugs		:
bogomips	: 5587.00　　　　在系统内核启动时粗略测算的CPU速度（Million Instructions Per Second）
clflush size	: 64　　　　每次刷新缓存的大小单位
cache_alignment	: 64　　　　缓存地址对齐单位
address sizes	: 46 bits physical, 48 bits virtual　　　　可访问地址空间位数
power management:　　　对能源管理的支持

1.1.1.11 /proc/crypto
系统上已安装的内核使用的密码算法及每个算法的详细信息列表
ll@ll-pc:~$ cat /proc/crypto
name         : crct10dif
driver       : crct10dif-pclmul
module       : crct10dif_pclmul
priority     : 200
refcnt       : 1
selftest     : passed
internal     : no
type         : shash
blocksize    : 1
digestsize   : 2


1.1.1.12 /proc/loadavg
1.1.1.13 /proc/locks
1.1.1.14 /proc/slabinfo
1.1.1.15 /proc/vmstat
1.1.1.16 /proc/zoneinfo
