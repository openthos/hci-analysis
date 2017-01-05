### diskstats(http://blog.csdn.net/tenfyguo/article/details/7477526)

查看出具体的磁盘IO压力

每列参数的含义：

Column1 主设备号

Column2 次设备号

Column3 设备名称：sda为整个硬盘的统计信息，sda1为第一个分区的统计信息，sda2为第二个分区的统计信息。ramdisk设备为通过软件将RAM当做硬盘来使用的一项技术。

Column4 成功完成读磁盘的总次数

Column5 成功完成合并读磁盘的总次数——为了效率可能会合并相邻的读和写。从而两次4K的读在它最终被处理到磁盘上之前可能会变成一次8K的读，才被计数和排队，因此只有一次I/O操作。这个域使你知道这样的操作有多频繁。

Column6 成功完成读扇区的总次数

Column7 所有读操作所花费的毫秒数

Column8 成功完成写磁盘的总次数

Column9 成功完成合并写磁盘的总次数 

Column10 成功完成写扇区的总次数

Column11 所有写操作所花费的毫秒数

Column12 正在处理的输入/输出请求数 -- -I/O的当前进度，只有这个域可以是0。当请求被交给适当的request_queue_t时增加，当请求完成时减小

Column13 正在处理的输入/输出花的时间（毫秒），这个字段是Column12的一个累加值，这个域会增长只要Column12不为0。

Column14 正在处理的输入/输出操作花费的加权毫秒数—— 在每次I/O启动，I/O完成，I/O合并或读取由正在进行的I/O数（字段12）乘以执行I/O的毫秒数的这些统计信息时增加。这可以提供I/O完成时间和可能积累的积压的简单测量。
```
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
```
### cpuinfo
查看系统中CPU的提供商和相关配置信息
```
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
```
### crypto
系统上已安装的内核使用的密码算法及每个算法的详细信息列表
```
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
```
### loadavg

查看CPU的平均负载（可运行的进程的平均数）

每列参数的含义：

系统5分钟内的平均负载，系统10分钟内的平均负载，系统15分钟内的平均负载，正在运行的进程数／进程总数，最近运行的进程ID号
```
ll@ll-pc:~$ cat /proc/loadavg
0.39 0.31 0.37 1/564 14180
```
### locks
这个文件包含在打开的文件上的加锁信息。文件中的每一行描述了特定文件和文档上的加锁信息以及对文件施加的锁的类型。内核也可以需要时对文件施加强制性锁。

保存当前由内核锁定的文件的相关信息，包含内核内部的调试数据；每个锁定占据一行，且具有一个惟一的编号；如下输出信息中每行的第二列表示当前锁定使用的锁

定类别，POSIX表示目前较新类型的文件锁，由lockf系统调用产生，FLOCK是传统的UNIX文件锁，由flock系统调用产生；第三列也通常由两种类型，ADVISORY表示

不允许其他用户锁定此文件，但允许读取，MANDATORY表示此文件锁定期间不允许其他用户任何形式的访问；
```
ll@ll-pc:~$ cat /proc/locks
1: POSIX  ADVISORY  WRITE 13735 08:08:5123677 0 EOF
2: POSIX  ADVISORY  WRITE 12774 08:06:786519 0 EOF
3: POSIX  ADVISORY  WRITE 12774 08:08:5121388 0 EOF
4: POSIX  ADVISORY  READ  6750 08:08:5120480 128 128
5: POSIX  ADVISORY  READ  6750 08:08:5112051 1073741826 1073742335
6: POSIX  ADVISORY  WRITE 4749 00:29:43 1 3
7: POSIX  ADVISORY  WRITE 1461 08:06:786449 0 EOF
8: POSIX  ADVISORY  READ  1249 08:08:5111973 128 128
9: POSIX  ADVISORY  READ  1249 08:08:5111971 1073741826 1073742335
10: OFDLCK ADVISORY  WRITE 1687 08:08:5111977 0 0
11: FLOCK  ADVISORY  WRITE 671 00:16:7 0 EOF
12: POSIX  ADVISORY  WRITE 12774 08:06:786529 0 EOF
13: POSIX  ADVISORY  WRITE 12774 08:06:786500 0 EOF
14: POSIX  ADVISORY  READ  1687 08:08:5111973 128 128
15: POSIX  ADVISORY  READ  1687 08:08:5111971 1073741826 1073742335
16: POSIX  ADVISORY  READ  6750 08:08:5120472 128 128
17: POSIX  ADVISORY  READ  6750 08:08:5112046 1073741826 1073742335
18: POSIX  ADVISORY  READ  6750 08:08:5120461 128 128
19: POSIX  ADVISORY  READ  6750 08:08:5112035 1073741826 1073742335
20: POSIX  ADVISORY  WRITE 6750 08:08:5112018 0 EOF
21: POSIX  ADVISORY  READ  5141 08:08:5111973 128 128
22: POSIX  ADVISORY  READ  5141 08:08:5111971 1073741826 1073742335
23: POSIX  ADVISORY  READ  1674 08:08:5111973 128 128
24: POSIX  ADVISORY  READ  1674 08:08:5111971 1073741826 1073742335
25: POSIX  ADVISORY  READ  1680 08:08:5111973 128 128
26: POSIX  ADVISORY  READ  1680 08:08:5111971 1073741826 1073742335
27: FLOCK  ADVISORY  WRITE 679 00:13:774 0 EOF
```
### slabinfo
```
root@ll-pc:/home/ll# cat /proc/slabinfo
slabinfo - version: 2.1
# name            <active_objs> <num_objs> <objsize> <objperslab> <pagesperslab> : tunables <limit> <batchcount> <sharedfactor> : slabdata <active_slabs> <num_slabs> <sharedavail>
ext4_groupinfo_1k     60     60    136   30    1 : tunables    0    0    0 : slabdata      2      2      0
kvm_async_pf           0      0    136   30    1 : tunables    0    0    0 : slabdata      0      0      0
kvm_vcpu               0      0  16832    1    8 : tunables    0    0    0 : slabdata      0      0      0
kvm_mmu_page_header      0      0    168   24    1 : tunables    0    0    0 : slabdata      0      0      0
ext4_groupinfo_4k   3724   3724    144   28    1 : tunables    0    0    0 : slabdata    133    133      0
UDPLITEv6              0      0   1088   30    8 : tunables    0    0    0 : slabdata      0      0      0
UDPv6                120    120   1088   30    8 : tunables    0    0    0 : slabdata      4      4      0
tw_sock_TCPv6        116    116    280   29    2 : tunables    0    0    0 : slabdata      4      4      0
TCPv6                 56     56   2240   14    8 : tunables    0    0    0 : slabdata      4      4      0
kcopyd_job             0      0   3312    9    8 : tunables    0    0    0 : slabdata      0      0      0
dm_uevent              0      0   2632   12    8 : tunables    0    0    0 : slabdata      0      0      0
dm_rq_target_io        0      0    112   36    1 : tunables    0    0    0 : slabdata      0      0      0
cfq_queue              0      0    232   17    1 : tunables    0    0    0 : slabdata      0      0      0
mqueue_inode_cache     18     18    896   18    4 : tunables    0    0    0 : slabdata      1      1      0
fuse_request          40     40    400   20    2 : tunables    0    0    0 : slabdata      2      2      0
fuse_inode            21     21    768   21    4 : tunables    0    0    0 : slabdata      1      1      0
ecryptfs_key_record_cache      0      0    576   28    4 : tunables    0    0    0 : slabdata      0      0      0
ecryptfs_inode_cache      0      0   1024   16    4 : tunables    0    0    0 : slabdata      0      0      0
ecryptfs_auth_tok_list_item      0      0    832   19    4 : tunables    0    0    0 : slabdata      0      0      0
fat_inode_cache      460    460    704   23    4 : tunables    0    0    0 : slabdata     20     20      0
fat_cache           1020   1020     40  102    1 : tunables    0    0    0 : slabdata     10     10      0
hugetlbfs_inode_cache     56     56    584   28    4 : tunables    0    0    0 : slabdata      2      2      0
jbd2_journal_handle    340    340     48   85    1 : tunables    0    0    0 : slabdata      4      4      0
jbd2_journal_head   1972   1972    120   34    1 : tunables    0    0    0 : slabdata     58     58      0
jbd2_revoke_table_s    768    768     16  256    1 : tunables    0    0    0 : slabdata      3      3      0
jbd2_revoke_record_s   1664   1664     32  128    1 : tunables    0    0    0 : slabdata     13     13      0
ext4_inode_cache  111698 112189   1032   31    8 : tunables    0    0    0 : slabdata   3619   3619      0
ext4_free_data      1856   1856     64   64    1 : tunables    0    0    0 : slabdata     29     29      0
ext4_allocation_context    128    128    128   32    1 : tunables    0    0    0 : slabdata      4      4      0
ext4_io_end         2352   2352     72   56    1 : tunables    0    0    0 : slabdata     42     42      0
ext4_extent_status  63191  63852     40  102    1 : tunables    0    0    0 : slabdata    626    626      0
dquot                256    256    256   16    1 : tunables    0    0    0 : slabdata     16     16      0
dio                    0      0    640   25    4 : tunables    0    0    0 : slabdata      0      0      0
pid_namespace          0      0   2224   14    8 : tunables    0    0    0 : slabdata      0      0      0
posix_timers_cache      0      0    240   17    1 : tunables    0    0    0 : slabdata      0      0      0
ip4-frags              0      0    216   18    1 : tunables    0    0    0 : slabdata      0      0      0
UDP-Lite               0      0    960   17    4 : tunables    0    0    0 : slabdata      0      0      0
xfrm_dst_cache         0      0    448   18    2 : tunables    0    0    0 : slabdata      0      0      0
RAW                   72     72    896   18    4 : tunables    0    0    0 : slabdata      4      4      0
UDP                  170    170    960   17    4 : tunables    0    0    0 : slabdata     10     10      0
tw_sock_TCP          261    261    280   29    2 : tunables    0    0    0 : slabdata      9      9      0
request_sock_TCP     104    104    312   26    2 : tunables    0    0    0 : slabdata      4      4      0
TCP                  133    208   2048   16    8 : tunables    0    0    0 : slabdata     13     13      0
blkdev_queue          70     70   2200   14    8 : tunables    0    0    0 : slabdata      5      5      0
blkdev_requests      496    638    368   22    2 : tunables    0    0    0 : slabdata     29     29      0
blkdev_ioc           936    936    104   39    1 : tunables    0    0    0 : slabdata     24     24      0
user_namespace       104    104    304   26    2 : tunables    0    0    0 : slabdata      4      4      0
dmaengine-unmap-256     15     15   2112   15    8 : tunables    0    0    0 : slabdata      1      1      0
dmaengine-unmap-128    120    120   1088   30    8 : tunables    0    0    0 : slabdata      4      4      0
sock_inode_cache    1168   1300    640   25    4 : tunables    0    0    0 : slabdata     52     52      0
file_lock_cache      152    152    208   19    1 : tunables    0    0    0 : slabdata      8      8      0
file_lock_ctx        949    949     56   73    1 : tunables    0    0    0 : slabdata     13     13      0
net_namespace         28     28   4608    7    8 : tunables    0    0    0 : slabdata      4      4      0
shmem_inode_cache   2748   3312    656   24    4 : tunables    0    0    0 : slabdata    138    138      0
taskstats            144    144    328   24    2 : tunables    0    0    0 : slabdata      6      6      0
proc_inode_cache    7336   7618    624   26    4 : tunables    0    0    0 : slabdata    293    293      0
sigqueue             100    100    160   25    1 : tunables    0    0    0 : slabdata      4      4      0
bdev_cache            95     95    832   19    4 : tunables    0    0    0 : slabdata      5      5      0
kernfs_node_cache  41639  42058    120   34    1 : tunables    0    0    0 : slabdata   1237   1237      0
mnt_cache            569    609    384   21    2 : tunables    0    0    0 : slabdata     29     29      0
inode_cache         8868   9436    568   28    4 : tunables    0    0    0 : slabdata    337    337      0
dentry            235956 235956    192   21    1 : tunables    0    0    0 : slabdata  11236  11236      0
iint_cache             0      0     72   56    1 : tunables    0    0    0 : slabdata      0      0      0
buffer_head       469120 506727    104   39    1 : tunables    0    0    0 : slabdata  12993  12993      0
mm_struct           1065   1139    960   17    4 : tunables    0    0    0 : slabdata     67     67      0
files_cache          322    322    704   23    4 : tunables    0    0    0 : slabdata     14     14      0
signal_cache         522    532   1152   28    8 : tunables    0    0    0 : slabdata     19     19      0
sighand_cache        327    375   2112   15    8 : tunables    0    0    0 : slabdata     25     25      0
task_struct          600    639   3520    9    8 : tunables    0    0    0 : slabdata     71     71      0
Acpi-ParseExt      15344  15344     72   56    1 : tunables    0    0    0 : slabdata    274    274      0
Acpi-State           204    204     80   51    1 : tunables    0    0    0 : slabdata      4      4      0
Acpi-Namespace      8670   8670     40  102    1 : tunables    0    0    0 : slabdata     85     85      0
anon_vma           13071  14382     80   51    1 : tunables    0    0    0 : slabdata    282    282      0
numa_policy          170    170     24  170    1 : tunables    0    0    0 : slabdata      1      1      0
radix_tree_node    35785  37324    584   28    4 : tunables    0    0    0 : slabdata   1333   1333      0
trace_event_file    1242   1242     88   46    1 : tunables    0    0    0 : slabdata     27     27      0
ftrace_event_field  43645  43775     48   85    1 : tunables    0    0    0 : slabdata    515    515      0
idr_layer_cache      537    540   2096   15    8 : tunables    0    0    0 : slabdata     36     36      0
dma-kmalloc-8192       0      0   8192    4    8 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-4096       0      0   4096    8    8 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-2048       0      0   2048   16    8 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-1024       0      0   1024   16    4 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-512       16     16    512   16    2 : tunables    0    0    0 : slabdata      1      1      0
dma-kmalloc-256        0      0    256   16    1 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-128        0      0    128   32    1 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-64         0      0     64   64    1 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-32         0      0     32  128    1 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-16         0      0     16  256    1 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-8          0      0      8  512    1 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-192        0      0    192   21    1 : tunables    0    0    0 : slabdata      0      0      0
dma-kmalloc-96         0      0     96   42    1 : tunables    0    0    0 : slabdata      0      0      0
kmalloc-8192         160    168   8192    4    8 : tunables    0    0    0 : slabdata     42     42      0
kmalloc-4096         283    328   4096    8    8 : tunables    0    0    0 : slabdata     41     41      0
kmalloc-2048        1285   1312   2048   16    8 : tunables    0    0    0 : slabdata     82     82      0
kmalloc-1024        2741   3152   1024   16    4 : tunables    0    0    0 : slabdata    197    197      0
kmalloc-512         1620   1664    512   16    2 : tunables    0    0    0 : slabdata    104    104      0
kmalloc-256        13150  15648    256   16    1 : tunables    0    0    0 : slabdata    978    978      0
kmalloc-192        42376  49140    192   21    1 : tunables    0    0    0 : slabdata   2340   2340      0
kmalloc-128         8048  11648    128   32    1 : tunables    0    0    0 : slabdata    364    364      0
kmalloc-96          4145   4494     96   42    1 : tunables    0    0    0 : slabdata    107    107      0
kmalloc-64        147884 148992     64   64    1 : tunables    0    0    0 : slabdata   2328   2328      0
kmalloc-32         17655  24064     32  128    1 : tunables    0    0    0 : slabdata    188    188      0
kmalloc-16          8192   8192     16  256    1 : tunables    0    0    0 : slabdata     32     32      0
kmalloc-8           7680   7680      8  512    1 : tunables    0    0    0 : slabdata     15     15      0
kmem_cache_node      320    320     64   64    1 : tunables    0    0    0 : slabdata      5      5      0
kmem_cache           144    144    256   16    1 : tunables    0    0    0 : slabdata      9      9      0
```
### vmstat
```
ll@ll-pc:~$ cat /proc/vmstat
nr_free_pages 758873
nr_alloc_batch 2111
nr_inactive_anon 122602
nr_active_anon 317429
nr_inactive_file 255048
nr_active_file 465424
nr_unevictable 8
nr_mlock 8
nr_anon_pages 418771
nr_mapped 116198
nr_file_pages 742674
nr_dirty 45
nr_writeback 0
nr_slab_reclaimable 62287
nr_slab_unreclaimable 13769
nr_page_table_pages 10729
nr_kernel_stack 565
nr_unstable 0
nr_bounce 0
nr_vmscan_write 99770
nr_vmscan_immediate_reclaim 1822
nr_writeback_temp 0
nr_isolated_anon 0
nr_isolated_file 0
nr_shmem 17239
nr_dirtied 46018008
nr_written 36541457
nr_pages_scanned 0
numa_hit 6580129889
numa_miss 0
numa_foreign 0
numa_interleave 21466
numa_local 6580129889
numa_other 0
workingset_refault 860623
workingset_activate 170829
workingset_nodereclaim 0
nr_anon_transparent_hugepages 452
nr_free_cma 0
nr_dirty_threshold 288917
nr_dirty_background_threshold 144458
pgpgin 10909442
pgpgout 156048718
pswpin 9959
pswpout 99732
pgalloc_dma 2
pgalloc_dma32 2058878682
pgalloc_normal 4839797005
pgalloc_movable 0
pgfree 6952846439
pgactivate 22881709
pgdeactivate 4047307
pgfault 5037086029
pgmajfault 45336
pgrefill_dma 0
pgrefill_dma32 1175394
pgrefill_normal 2365597
pgrefill_movable 0
pgsteal_kswapd_dma 0
pgsteal_kswapd_dma32 1026794
pgsteal_kswapd_normal 2225851
pgsteal_kswapd_movable 0
pgsteal_direct_dma 0
pgsteal_direct_dma32 703834
pgsteal_direct_normal 2102082
pgsteal_direct_movable 0
pgscan_kswapd_dma 0
pgscan_kswapd_dma32 1084856
pgscan_kswapd_normal 2319341
pgscan_kswapd_movable 0
pgscan_direct_dma 0
pgscan_direct_dma32 766894
pgscan_direct_normal 2245562
pgscan_direct_movable 0
pgscan_direct_throttle 0
zone_reclaim_failed 0
pginodesteal 991442
slabs_scanned 3341778
kswapd_inodesteal 258271
kswapd_low_wmark_hit_quickly 225
kswapd_high_wmark_hit_quickly 361
pageoutrun 917
allocstall 32657
pgrotated 99986
drop_pagecache 0
drop_slab 0
numa_pte_updates 0
numa_huge_pte_updates 0
numa_hint_faults 0
numa_hint_faults_local 0
numa_pages_migrated 0
pgmigrate_success 50263574
pgmigrate_fail 2851
compact_migrate_scanned 77311606
compact_free_scanned 1549639477
compact_isolated 105711345
compact_stall 205520
compact_fail 26260
compact_success 179260
htlb_buddy_alloc_success 0
htlb_buddy_alloc_fail 0
unevictable_pgs_culled 1
unevictable_pgs_scanned 0
unevictable_pgs_rescued 810
unevictable_pgs_mlocked 818
unevictable_pgs_munlocked 810
unevictable_pgs_cleared 0
unevictable_pgs_stranded 0
thp_fault_alloc 359080
thp_fault_fallback 8833
thp_collapse_alloc 252296
thp_collapse_alloc_failed 17021
thp_split 252170
thp_zero_page_alloc 11
thp_zero_page_alloc_failed 0
balloon_inflate 0
balloon_deflate 0
balloon_migrate 0
```
### zoneinfo
```
ll@ll-pc:~$ cat /proc/zoneinfo
Node 0, zone      DMA
  pages free     3965
        min      33
        low      41
        high     49
        scanned  0
        spanned  4095
        present  3988
        managed  3967
    nr_free_pages 3965
    nr_alloc_batch 8
    nr_inactive_anon 0
    nr_active_anon 0
    nr_inactive_file 0
    nr_active_file 0
    nr_unevictable 0
    nr_mlock     0
    nr_anon_pages 0
    nr_mapped    0
    nr_file_pages 0
    nr_dirty     0
    nr_writeback 0
    nr_slab_reclaimable 0
    nr_slab_unreclaimable 2
    nr_page_table_pages 0
    nr_kernel_stack 0
    nr_unstable  0
    nr_bounce    0
    nr_vmscan_write 0
    nr_vmscan_immediate_reclaim 0
    nr_writeback_temp 0
    nr_isolated_anon 0
    nr_isolated_file 0
    nr_shmem     0
    nr_dirtied   0
    nr_written   0
    nr_pages_scanned 0
    numa_hit     1
    numa_miss    0
    numa_foreign 0
    numa_interleave 0
    numa_local   1
    numa_other   0
    workingset_refault 0
    workingset_activate 0
    workingset_nodereclaim 0
    nr_anon_transparent_hugepages 0
    nr_free_cma  0
        protection: (0, 2354, 7857, 7857)
  pagesets
    cpu: 0
              count: 0
              high:  0
              batch: 1
  vm stats threshold: 6
    cpu: 1
              count: 0
              high:  0
              batch: 1
  vm stats threshold: 6
    cpu: 2
              count: 0
              high:  0
              batch: 1
  vm stats threshold: 6
    cpu: 3
              count: 0
              high:  0
              batch: 1
  vm stats threshold: 6
  all_unreclaimable: 1
  start_pfn:         1
  inactive_ratio:    1
Node 0, zone    DMA32
  pages free     193604
        min      5053
        low      6316
        high     7579
        scanned  0
        spanned  1044480
        present  632016
        managed  611980
    nr_free_pages 193604
    nr_alloc_batch 1262
    nr_inactive_anon 47471
    nr_active_anon 154598
    nr_inactive_file 55282
    nr_active_file 132184
    nr_unevictable 0
    nr_mlock     0
    nr_anon_pages 196003
    nr_mapped    39899
    nr_file_pages 193621
    nr_dirty     12
    nr_writeback 0
    nr_slab_reclaimable 18989
    nr_slab_unreclaimable 3273
    nr_page_table_pages 2765
    nr_kernel_stack 144
    nr_unstable  0
    nr_bounce    0
    nr_vmscan_write 44143
    nr_vmscan_immediate_reclaim 4
    nr_writeback_temp 0
    nr_isolated_anon 0
    nr_isolated_file 0
    nr_shmem     5540
    nr_dirtied   14141327
    nr_written   11309675
    nr_pages_scanned 0
    numa_hit     1957754666
    numa_miss    0
    numa_foreign 0
    numa_interleave 0
    numa_local   1957754666
    numa_other   0
    workingset_refault 221551
    workingset_activate 41733
    workingset_nodereclaim 0
    nr_anon_transparent_hugepages 156
    nr_free_cma  0
        protection: (0, 0, 5502, 5502)
  pagesets
    cpu: 0
              count: 177
              high:  186
              batch: 31
  vm stats threshold: 36
    cpu: 1
              count: 166
              high:  186
              batch: 31
  vm stats threshold: 36
    cpu: 2
              count: 121
              high:  186
              batch: 31
  vm stats threshold: 36
    cpu: 3
              count: 180
              high:  186
              batch: 31
  vm stats threshold: 36
  all_unreclaimable: 0
  start_pfn:         4096
  inactive_ratio:    4
Node 0, zone   Normal
  pages free     557816
        min      11809
        low      14761
        high     17713
        scanned  0
        spanned  1441792
        present  1441792
        managed  1408701
    nr_free_pages 557816
    nr_alloc_batch 519
    nr_inactive_anon 75131
    nr_active_anon 166887
    nr_inactive_file 199764
    nr_active_file 333243
    nr_unevictable 8
    nr_mlock     8
    nr_anon_pages 226315
    nr_mapped    76299
    nr_file_pages 549054
    nr_dirty     42
    nr_writeback 0
    nr_slab_reclaimable 43302
    nr_slab_unreclaimable 10478
    nr_page_table_pages 7966
    nr_kernel_stack 421
    nr_unstable  0
    nr_bounce    0
    nr_vmscan_write 55627
    nr_vmscan_immediate_reclaim 1818
    nr_writeback_temp 0
    nr_isolated_anon 0
    nr_isolated_file 0
    nr_shmem     11699
    nr_dirtied   31881796
    nr_written   25234633
    nr_pages_scanned 0
    numa_hit     4622485788
    numa_miss    0
    numa_foreign 0
    numa_interleave 21466
    numa_local   4622485788
    numa_other   0
    workingset_refault 639073
    workingset_activate 129096
    workingset_nodereclaim 0
    nr_anon_transparent_hugepages 296
    nr_free_cma  0
        protection: (0, 0, 0, 0)
  pagesets
    cpu: 0
              count: 137
              high:  186
              batch: 31
  vm stats threshold: 42
    cpu: 1
              count: 141
              high:  186
              batch: 31
  vm stats threshold: 42
    cpu: 2
              count: 162
              high:  186
              batch: 31
  vm stats threshold: 42
    cpu: 3
              count: 170
              high:  186
              batch: 31
  vm stats threshold: 42
  all_unreclaimable: 0
  start_pfn:         1048576
  inactive_ratio:    7
info
```
