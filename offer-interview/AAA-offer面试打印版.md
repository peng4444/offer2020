# 面试打印版
## 自我介绍
```markdown
教育经历 + 工作经历 + 简单的性格介绍。而工作经历中的项目将成为接下来面试官询问的重点，所以最好能准备一个拿得出手的项目。

如果在面试之前已经有工作经历和项目经历，就需要深挖你项目的亮点：

开发的时候使用的技术和中间件
遇到的问题、如何解决问题
是否有优化方案，如何优化你的项目
上面这三个问题基本上是最常见也是最基本的问题，准备项目的时候，一定要把上面这些问题准备好。
```
## 项目工作经验
```markdown
1.介绍一下你简历上写的项目？自己主要做了什么？（简历上虽然写了，但是面试官还是问了）
2.你觉得项目里给你最大的挑战是什么？遇到了什么问题？如何解决的？从中学到了什么？
3.项目的架构图能画一下不？（一个很 low 的后台网站）
4.觉得项目有哪些地方可以改进完善？（我说可以加一个 redis 缓存把热点数据缓存起来）
5.为什么要用 Nginx?有啥用？优缺点？
6.有没有遇到过内存泄漏的场景?
1.聊javaweb项目，负责的工作，项目需求功能
2. 权限管理模块的实现（这个地方还跟面试官argue一段时间，最终还是败下阵来）
3. 项目一些数据库表是如何设计的，有哪些字段
4. 项目的困难，如何克服
```
## HR及其他
```markdown

```
## 计算机网络
```markdown
OSI7 层模型（TCP4 层）、每层的协议、
常见协议为位置在 OSI 七层协议的位置（HTTP/TCP/UDP/DNS/IMAP/ARP/ 路由器 / 交换机）
http/https 1.0、1.1、2.0、https 加密过程（对称加密和非对称加密）
get/post 以及 http 幂等性
http 协议头相关、http 常见的状态码
TCP 与 UDP 比较
TCP 三次握手、四次挥手、拥塞控制（过程、阈值）、流量控制与滑动窗口
TCP 的 time_wait 和 close_wait
url 到页面的过程
网络攻击（CSRF、XSS、DDos）

OSI 七层模型？HTTP 协议对应第几层？IP 协议呢？
从一个 URL 到获取页面的过程？
session 的实现原理？cookie 的原理？
session 和 cookie 的关系？禁用 cookie 后对 session 的影响？
数据一致性，分布式session，session，cookie区别？
forward 和 redirect 的区别？
内网和外网 IP 地址的区别？ABC 三类 IP 地址的划分
网关和子网掩码的关系是什么？
MAC 地址和 IP 地址的关系是什么？
什么是 DNS 服务器？
IP 如何映射到 MAC 地址的？
TCP 是如何保证可靠传输数据的？
TCP 和 UDP 的区别？
TCP 三次握手和四次挥手的过程？
TCP 为什么需要三次握手？只进行两次会出现什么问题？
TCP 第三次握手失败的情况 TCP 是如何处理的？
为什么连接的时候是三次握手，关闭的时候却是四次握手？
http1.0 与 http1.1 的区别？什么是 keep-alive 模式？
简单说一下 http2.0？
什么是幂等性？http 的方法是否都符合幂等性？若不符合，怎么避免？
https 与 http 的区别？
https 加密的过程？
https 是否存在安全问题？如何避免？
get 方法和 post 方法的区别？
什么 XSS 攻击？如何预防？
什么是 CSRF 攻击？如何预防？
什么是 DDoS 攻击？如何预防
什么是 SQL 注入攻击？如何预防？
```
## 操作系统
```markdown
进程通信 IPC（几种方式），进程与线程定义与区别
进程调度算法、磁盘扫描算法
虚拟内存、页面置换算法
内核态和用户态的转换的条件、中断、系统调用
互斥与死锁
linux 常用命令（问的时候都会给具体某一个场景）
Linux 的 IO 模型 BIO/NIO/AIO、 IO 多路复用
Linux 内核 select poll epoll、边缘触发和水平触发
僵尸进程和孤儿进程

什么是僵尸进程？什么是孤儿进程？有什么危害？
CPU 的上下文切换有几种？系统中断进行了几次上下文切换？
进程的通信方式？效率最高的通信方式是什么？
进程调度算法有几种？应用最广泛的是什么？
进程和线程的区别？
虚拟内存的作用与特性？
虚拟内存的实现方式？分别有何种缺陷？
页面置换算法？
什么是中断？产生中断的方式？
什么是系统调用？
什么会导致用户态陷入内核态？
陷阱和中断的区别？
互斥和同步的关系？
死锁产生的条件？
信号量的 PV 实现？
生产者消费者的代码实现？
如何动态查看服务器日志文件？
如何打包压缩文件？
修改 /test 下的 aaa.txt 的权限为属主有全部权限，属主所在的组有读写权限， 其他用户只有读的权限？
查找 text.txt 文件中的 abc 的位置？
在 /home 目录下查找以.txt 结尾的文件名？
普通文件 IO 页缓存需要复制几次？具体过程是什么？
常见的 IO 模型？
IO 多路复用的 select、poll 和 epoll 函数的区别？
```
## 数据库
```markdown
索引（包括分类及优化方式，失效条件，底层结构 B 树、B + 树的区别优缺点）
优化（explain，慢查询，show profile）、数据库的范式
辅助索引、主键索引、聚簇索引、非聚簇索引、索引回表、索引覆盖、索引下推
联合索引和最左匹配原则
引擎对比（InnoDB，MyISAM）
数据库的锁（行锁，表锁，页级锁，读锁，写锁，悲观锁，乐观锁）
隔离级别，依次解决的问题（脏读、不可重复读、幻读）、隔离级别与加锁的关系
事务的 ACID
分库分表，主从复制，读写分离。
sql 语法（join，union，子查询，having，group by）主要考察 sql 语句的书写

什么是超键？什么是主键？二者有什么关系？
数据库的三范式是什么？
char 和 varchar 的区别是什么？
delete 和 truncate 有什么区别？谁效率更好？
存储过程和函数的区别？
视图的操作会对基本表产生影响吗？
count（*）和 count（列名）谁的效率更高？
索引是什么？
索引的分类？索引失效条件？
索引优化方式？
怎么验证 mysql 的索引是否满足需求？
索引的底层结构是什么？说说各种的特点和缺点？
什么是事务？
事务的 ACID 特性？
事务并发会造成的问题？
事务的隔离级别？
说一下乐观锁和悲观锁？说一下 mysql 的行锁和表锁？
事务的隔离级别和加锁的关系？
两种常见的数据库引擎？分别具有什么特点？
一张自增表里面总共有 7 条数据，删除了最后 2 条数据，重启 MySQL 数据库，又插入了一条数据，此时 id 是几？
什么是主从复制？什么是读写分离？
数据库从哪几方面进行调优？
索引优化方向？
mysql 问题排查都有哪些手段？怎么验证 mysql 的索引是否满足需求？
```
## Java语言基础
```markdown
1. 基础
面向对象、四个特性、重载重写、继承、多态、反射
常见关键字 final、static、abstract、finalize、transient、native
StringBuffer、StringBuilder 和 String、字符常量池
六大设计原则、常见的设计模式（代理、工厂、单例、装饰者、观察者）
异常、常见的异常类
序列化
深拷贝和浅拷贝、值传递和引用传递
抽象类和接口
基本类型的默认值和取值范围以及字节数
2. 多线程

synchronized 关键字底层原理、锁升级原理、轻量级锁、重量级锁
Lock 锁机制、线程通信、、ThreadLocal、Atom 包、AQS、CAS 原理
volatile 关键字、内存屏障、happen-before 原则
同步队列、等待队列、阻塞队列
线程池、线程池的执行流程、线程池的主要参数（coresize、阻塞队列、maxsize、拒绝策略）、线程池的任务递交方式和状态变化。
thread 类的常见方法：sleep、join、yield；sleep 和 wait 方法的区别
线程的五种状态、run 方法和 start 方法
守护线程和用户线程
3. 集合框架

hashmap（这个基本是必考题）hashcode 函数、equals 函数、扩容机制、put 和 get 操作、jdk1.7 和 jdk1.8 的变化、hashmap 的尾部遍历、红黑树的引入
hashmap 的线程安全问题，synchronizedMap、hashtable、concurrentHashMap
concurrentHashMap 的 jdk1.7 和 jdk1.8 的变化，底层实现线程安全的方式
常见的集合框架哪些是线程安全的，哪些是线程不安全的
arraylist 和 linkedlist、vector
集合的遍历、迭代器
4.JVM

JVM 内存模型、堆、栈、PC 计算器、永久代分别存储的内容
GC 垃圾回收，三种 GC 算法，七种 GC 收集器
CMS 和 G1 的优缺点、执行流程
年老代和年轻代、分代垃圾回收算法
四种引用类型、GCroot 的可达性分析法
对象创建的过程、对象的内存分配
类加载的过程、类加载器
双亲委派机制、tomcat 的反双亲委派机制
JVM 调优，常见的调优参数
内存泄漏和内存溢出
```
## Java 语言基础
```markdown
== 和 equals 的区别？
拆箱和装箱分别是什么？分别应用在什么场景？
String str=“abc” 与 String str=new String (“abc”) 一样吗？为什么？
String str=“abc” 和 String str=new String (“abc”); 产生几个对象？ String str = new String (“hello”)+new String (“123”); 产生了几个对象？
字符常量池的位置？字符常量池存储的内容？
给定三个变量：i1、i2、i3。Integer i1 = 120；Integer i2 = 120；int i3= 120；i1 和 i2 一样吗？i1 和 i3 呢？为什么？如果把 120 换成 130 呢，i1,12,i3 的关系又如何，为什么？
throw 和 throws 的区别？
try-catch-finally 中哪个部分可以省略？
try-catch-finally 中，如果 catch 中 return 了，finally 还会执行吗？
常见的异常类有哪些？
java 异常的执行流程？
为什么要使用克隆？如何实现对象克隆？
深拷贝和浅拷贝区别是什么？
值传递和引用传递的区别是什么？
什么是 java 序列化？什么情况下需要序列化？如何避免序列化对象中的属性序列化？
什么是反射？反射的应用场景？
代理模式有什么用？应用场景是什么？
动态代理的实现方式都有什么？哪种实现效率高？
动态代理是什么？与静态代理的区别在于？
抽象类和接口的区别？普通类和抽象类有哪些区别？
抽象类必须要有抽象方法吗？抽象类能使用 final 修饰吗？
局部内部类和匿名内部类为什么只能访问 final 的局部变量？
什么是多态？
BIO、NIO、AIO 有什么区别？
同步、异步、阻塞、非阻塞？
java 中 IO 流分为几种？
final 在 java 中有什么作用？抽象类能使用 final 修饰吗？
java 中的 Math.round (-1.5) 等于多少？
管道的类型？
什么是半双工？什么是全双工？
在多线程下选用什么处理大规模字符串？
Java 中的设计原则？
什么是组合？什么是聚合？
说一下你熟悉的设计模式？
简单工厂和抽象工厂有什么区别？
装饰者模式和适配器模式以及代理模式的区别？
说出几个在 JDK 库中使用的设计模式？
jdk1.8 新增的变化？
```
## JVM
```markdown
说一下 jvm 的主要组成部分？及其作用？
jvm 运行时数据区组成？
堆栈的区别？
运行时数据区哪些是线程共享，哪些是线程私有？
Java 中成员变量、局部变量、静态变量、常量分别存储在那些内存区域中？
说一下类加载的执行过程？
Java 中都有哪些加载器？
什么是双亲委派模型？
反射中，Class.forName () 和 ClassLoader.loadClass () 区别
说一下对象创建的过程？
对象有哪几部分构成？虚拟机如何访问对象？
java 中都有哪些引用类型？
怎么判断对象是否可以被回收？
内存泄露和内存溢出分别是什么？什么原因造成？如何避免？
给对象分配内存如何保证线程安全？
说一下 jvm 有哪些垃圾回收算法？
说一下 jvm 有哪些垃圾回收器？
详细介绍一下 CMS 垃圾回收器？
新生代垃圾回收器和老生代垃圾回收器都有哪些？有什么区别？
简述分代垃圾回收器是怎么工作的？
G1 为什么能建立可预测的停顿时间模型？
Minor Gc 和 Full GC 有什么不同呢？
什么对象会进入老年代？
如果对象的引用被置为 null，垃圾收集器是否会立即释放对象占用的内存？
常量池都包括哪些内容？常量池的位置？
```
## 容器类集合框架
```markdown
java 容器都有哪些？
Collection 和 Collections 有什么区别？
List、Set、Map 之间的区别是什么？
如何决定使用 HashMap 还是 TreeMap？
ArrayList 和 LinkedList 的区别是什么？
ArrayList 和 Vector 的区别是什么？
说一下 HashMap 的实现原理？
java 如何判断 HashMap 中的元素是否相等？添加的元素是自定义类的时候，需要注意什么？
HashMap 为什么引入红黑树？
JDK1.8 的 HashMap 有哪些优化？
请写出 HashMap 的添加操作和扩容操作的代码？
HashMap 和 Hashtable 有什么区别？HashMap 和 HashSet 呢？
final 关键字用于什么场景？
ConcurrentHashMap 如何实现线程同步？
Map 遍历的两种方式？
哪些集合类是线程安全的？
Iterator 怎么使用？有什么特点？Iterator 的 fail-fast 属性是什么？
Iterator 和 ListIterator 有什么区别？
Arrays 和 Collections 的常用方法
Array 和 ArrayList 有何区别？
在 Queue 中 poll () 和 remove () 有什么区别？
怎么确保一个集合不能被修改？
```
## 多线程
```markdown
什么是锁消除？什么是锁粗化？
乐观锁有哪几种？主要思想是什么？
多线程锁的升级原理是什么（锁膨胀）？
什么是死锁？如何预防死锁？死锁和活锁的区别是什么？
并行和并发有什么区别？同步、异步、阻塞、非阻塞有什么区别？同步等同于阻塞吗？
线程和进程的区别？线程有哪些状态？
守护线程是什么？
创建线程有哪几种方式？线程的 run () 和 start () 有什么区别？
ThreadLocal 是什么？有哪些使用场景？
sleep 和 wait 方法有什么区别？notify () 和 notifyAll () 有什么区别？
现在有 T1、T2、T3 三个线程，你怎样保证 T2 在 T1 执行完后执行，T3 在 T2 执行完后执行？
volatile 有什么用？synchronized 和 volatile 的区别是什么？
synchronized 和 Lock 有什么区别？
Lock 实现锁的底层原理？
说一下 atomic 的原理？
乐观锁的实现方式？
说一下 synchronized 底层实现原理？
线程池都有哪些状态？
线程池中 submit () 和 execute () 方法有什么区别？
创建线程池有哪几种方式？
创建线程池的各个参数代表的含义？
```
## 代码手写
```markdown
108. 将有序数组转换为二叉搜索树
109. 有序链表转换二叉搜索树
```
```markdown
堆排序的原理及时间复杂度，是否稳定，最坏及最坏场景。
手写快速排序、插入排序、冒泡排序，并分析时间复杂度和空间复杂度，它们的稳定性。（内部排序，外部排序）
二分查找算法简述如何改进。
手写算法：从中序与后序遍历序列构造二叉树
```


### 等概率无重复的从n个数中选取m个数
[等概率无重复的从n个数中选取m个数](https://blog.csdn.net/yusiguyuan/article/details/42607681)
###[面试经典问题——如何从非负整数[0,N)中等概率不重复地选择K个数](https://zhuanlan.zhihu.com/p/32845748)
```markdown
问题描述：程序的输入包含两个整数m和n，其中m<n。
输出是0~n-1范围内的m个随机整数，要求：每个数选择出现的概率相等，且按序输出。    
int gen(int m,int n){
    int i, select = m,remaining = n;
    for(i=0;i<n;i++) {
        if(rand() % remaining <select) {
            printf("%d\n",i);
            select--;
        }
        remaining--;
    }
    return 0;
}
//优化
int genknuth(int m,int n){
    int i;
    for(i=0;i<n;i++)
        if(rand()%(n -i) < m) {
            printf("%d\n",i);
            m--;
        }
    return 0;
```
### 统计有序数组里平方和的数目
>> 排序数组-3,-2,-2,1,1,2,2,3,4输出每个元素平方后有多少个不同的元素空间O(1)
```markdown
public static int DifferentMi(int nums[]){
   int cnt=0;
   int i=0,j=nums.length-1;
   while(i<j){
       while(i<j && nums[i]*nums[i]==nums[j]*nums[j])
           i++;
       if(nums[i]*nums[i]>nums[j]*nums[j]){
           while(i<j && nums[i]*nums[i]==nums[i+1]*nums[i+1])
               i++;
               i++;
       }else{
           while(i<j && nums[j]*nums[j]==nums[j-1]*nums[j-1])
                j--;
                j--;
           }
           cnt++;
       }
       return cnt;
}
public int handle(int[] nums) {
    if(nums==null  || nums.length==0)
        return 0;
    HashSet<Integer> set = new HashSet<Integer>();
    for (int number : nums)
        set.add(Math.abs(number));
    return set.size();
}
```
### LeetCode 53.最大子序和
```markdown
public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = dp[0];
        for(int i = 1;i<n;i++){
            dp[i] = Math.max(dp[i-1],0)+nums[i];
            res = Math.max(res,dp[i]);
        }
        return res;
    }
public int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        for(int num:nums){
            if(sum>0){
                sum+=num;
            }else{
                sum = num;
            }
            res = Math.max(res,sum);
        }
        return res;
    }
```
### 给定负数0正数组成的数组，给定一个目标值，找到一个最长连续子数组使其和为目标值。
```java
public class Main{
    //暴力
    public int maxLength(int[] nums,int key){
        int res = 0;
        for(int i = 0;i<nums.length;i++){
            int sum = 0;
            for(int j = i;j<nums.length;j++){
                sum+=nums[j];
                if(sum==key){
                    res = Math.max(res,j-i+1);
                }
            }
        }
    }
    //
    public int maxLength(int[] nums,int key){
        if(nums==null||nums.length==0) return 0;
        // key 某个累加和sum value 这个累加和最早出现的位置
        // 取以每个位置为结尾的等于定值的子数组的最大长度的最大值
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);// 非常重要 意思是累加和为0 最早出现的位置为 -1 默认
        int len = 0;
        int sum = 0;// 前缀和 到i位置
        for(int i = 0;i<nums.length;i++){
            sum+=nums[i];// arr[0..i]的和 到当前位置的累加和
            if(map.containsKey(sum-k)){// 能找到sum-aim出现的最早位置
                len = Math.max(i-map.get(sum-k),len);// 更新最大长度 i-(map.get(sum-aim)+1)+1
            }
            if(!map.containsKey(sum)){// 只放和为sum出现的最早位置
                map.put(sum,i);
            }
        }
        return len;
    }
}
```
### 给定一个数组，值全是正数，请返回累加和为给定值k的最长子数组长度
```markdown
public static int getMaxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k <= 0) {
			return 0;
		}
		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {
				right++;
				if (right == arr.length) {//边界判断
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}
```
### LeetCode 3.最长无重复字符串
```markdown
//暴力-时间复杂度O(n^2)
    public int lengthOfLongestSubstring2(String s) {
        int len = s.length(),i=0,j,k,max = 0;
        char[] chars = s.toCharArray();
        for (j = 0; j < len; j++) {
            for (k = i; k < j; k++) {
                if (chars[k] == chars[j]) {
                    i = k + 1;
                    break;
                }
            }
            if (j - i + 1 > max) {
                max = j - i + 1;
            }
        }
        return max;
    }
//优化的滑动窗口
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // 如果窗口中包含当前字符
            if (map.containsKey(s.charAt(j))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            ans = Math.max(ans, j - i + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
//HashSet 滑动窗口
    public class Solution {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int ans = 0, i = 0, j = 0;
            while (i < n && j < n) {
                // try to extend the range [i, j]
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j++));
                    ans = Math.max(ans, j - i);
                } else {
                    set.remove(s.charAt(i++));
                }
            }
            return ans;
        }
    }
```
### LeetCode 312.戳气球
```markdown
//递归分治-超时
public int maxCoins(int[] nums) {
        int[] a = new int[nums.length+2];
        a[0] = 1;
        a[a.length-1] = 1;
        for(int i = 0;i<nums.length;i++){
            a[i+1] = nums[i];
        }
        return help(a,0,a.length-1);
    }
    public int help(int[] a,int start,int end){
        int max = 0;
        for(int i =start+1;i<end;i++){
            max = Math.max(max,help(a,start,i)+a[start]*a[i]*a[end]+help(a,i,end));
        }
        return max;
    }
//二维dp
public int maxCoins(int[] nums) {
        int[] a = new int[nums.length+2];
        a[0] = 1;
        a[a.length-1] = 1;
        for(int i = 0;i<nums.length;i++){
            a[i+1] = nums[i];
        }
        int[][] dp = new int[nums.length+2][nums.length+2];
        for(int i = nums.length-1;i>=0;i--){
            for(int j = i+2;j<=nums.length+1;j++){
                for(int k = i+1;k<=j-1;k++){
                    dp[i][j] = Math.max(dp[i][j],dp[i][k]+dp[k][j]+a[i]*a[k]*a[j]);
                }
            }
        }
        return dp[0][nums.length+1];
    }
```
### LeetCode 200.岛屿数量
```markdown
//利用深度优先搜索
思路：遍历岛这个二维数组，如果当前数为1，则进入感染函数并将岛个数+1
感染函数：其实就是一个递归标注的过程，它会将所有相连的1都标注成2。为什么要标注？这样就避免了遍历过程中的重复计数的情况，一个岛所有的1都变成了2后，遍历的时候就不会重复遍历了。建议没想明白的同学画个图看看。
public int numIslands(char[][] grid) {
       int landNum = 0;
       for(int i = 0;i<grid.length;i++){
           for(int j = 0;j<grid[0].length;j++){
               if(grid[i][j]=='1'){
                   infect(grid,i,j);
                   landNum++;
               }
           }
       }
       return landNum;
    }
    private void infect(char[][] grid,int i,int j){
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]!='1') return ;
        grid[i][j] = 2;
        infect(grid,i+1,j);
        infect(grid,i-1,j);
        infect(grid,i,j+1);
        infect(grid,i,j-1); 
    }
```
#### 322.零钱兑换
```markdown
//凑成总金额所需的最少的硬币个数
public int coinChange(int[] coins, int amount) {
        int max = amount+1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for(int i = 0;i<=amount;i++){
            for(int j = 0;j<coins.length;j++){
                if(coins[j]<=i){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }
//凑成总金额的硬币组合数
public int change(int amount, int[] coins) {
        int[] dp= new int[amount+1];
        dp[0] = 1;
        for(int coin:coins){
            for(int j = 1;j<=amount;j++){
                if(j>=coin){
                    dp[j] = dp[j]+dp[j-coin];
                }
            }
        }
        return dp[amount];
    }
```
#### 215,347.TopK 692.前K个高频单词
```markdown
LeetCode 215. 数组中的第K个最大元素
public int findKthLargest(int[] nums, int k) {
        //快速排序，堆排序，调用库函数都可以
        //利用优先队列构造堆排序
        Queue<Integer> queue = new PriorityQueue<>();
        for(int num:nums){
            if(queue.size()<k){
                queue.offer(num);
            }else{
                if(queue.peek()<num){
                    queue.poll();
                    queue.offer(num);
                }
            }
        }
        return queue.peek();
    }
//快速排序
public int findKth(int[] a, int n, int K) {
        quickSort(a,0,n-1);
        return a[n-K];
    }
    public void  quickSort(int[] a,int start,int end){
        if(start<end){
            int i = partition(a,start,end);
            quickSort(a,i+1,end);
            quickSort(a,start,i-1);
        }
    }
    public int partition(int[] a,int p,int q){
        int x = a[p];
        int i = p;
        for(int j = p+1;j<=q;j++){
            if(a[j]<x){
                swap(a,i+1,j);
                i++;
            }
        }
        swap(a,p,i);
        return i;
    }
    public void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
LeetCode 347. 前K个高频元素
//堆排序
public int[] topKFrequent(int[] nums, int k) {
        // 先对每个数字计数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 初始化堆，按照出现次数升序
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((x, y) -> x.getValue() - y.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            heap.add(entry);
            // 维护堆的大小为 K，在堆里的都是最大的
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] res = new int[k];
        int i = 0;
        while (!heap.isEmpty()) {
            res[i++] = heap.poll().getKey();
        }
        return res;
    }
//桶排序法
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int frequent = map.get(key);
            if (buckets[frequent] == null) {
                buckets[frequent] = new ArrayList<>();
            }
            buckets[frequent].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for(int i = buckets.length-1;i>=0&&topK.size()<k;i--){
            if (buckets[i] == null) {
                continue;
            }
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        return topK;
    }
//692. 前K个高频单词
public List<String> topKFrequent(String[] words, int k) {
         Map<String, Integer> map = new HashMap<>(words.length);
        // 建立哈希表统计单词频率
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 小顶堆，相同频率下，字母顺序高的在前，方便入栈
        PriorityQueue<String> queue = new PriorityQueue<>((o1, o2) -> {
            Integer o1Count = map.get(o1);
            Integer o2Count = map.get(o2);
            if (o1Count.equals(o2Count)) {
                return o2.compareTo(o1);
            } else {
                return o1Count - o2Count;
            }
        });
        // 维持topK频率的单词
        for (String word : map.keySet()) {
            queue.offer(word);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        // 利用栈特性
        LinkedList<String> stack = new LinkedList<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        };
        return stack;
    }
```
### 100亿黑名单URL，每个64B，问这个黑名单要怎么存？判断一个URL是否在黑名单中
```markdown
​ 散列表：
​ 如果把黑名单看成一个集合，将其存在 hashmap 中，貌似太大了，需要 640G，明显不科学。
​ 布隆过滤器：
​ 它实际上是一个很长的二进制矢量和一系列随机映射函数。
​ 它可以用来判断一个元素是否在一个集合中。它的优势是只需要占用很小的内存空间以及有着高效的查询效率。对于布隆过滤器而言，它的本质是一个位数组：位数组就是数组的每个元素都只占用 1 bit ，并且每个元素只能是 0 或者 1。
​ 在数组中的每一位都是二进制位。布隆过滤器除了一个位数组，还有 K 个哈希函数。当一个元素加入布隆过滤器中的时候，会进行如下操作：
使用 K 个哈希函数对元素值进行 K 次计算，得到 K 个哈希值。
根据得到的哈希值，在位数组中把对应下标的值置为 1。
```
### 多线程手写
```markdown
主线程到达一个条件，需要多个子线程去执行任务，等子任务都执行完后再往下走，如何编写代码(CountDownLatch)。
写个程序，两个线程交叉打印1到100的数字，需要多种实现方式。
```
### 手写算法
```java
 一个数组，前半部分增序，后半部分降序，以小于O(n)的时间复杂度找到最大值
 对K个长度为N的有序数组排序的时间复杂度
找出多数元素（美团）找出数组中大于一半的数。下一个大于N的回文数
判断是否有一个数在有序数组中出现次数多于数组长度一半
无序序列的连续子序列长度
最长无重复字符的子串。
给定一个数组，求和为s的所有子数组
两个字符串求最长公共子串
最长公共字串 （美团）
快速排序  递归 非递归（阿里）
蓄水池 （快手）
自定义实现parseDouble方法 （快手）
a+b+c=0 （头条）
保留有序链表中的重复元素，并且只保留一次 （阿里，这个很有意思，感兴趣的同学可以实现下）
给定一个有序链表，保留链表中重复出现的元素，并且只保留一次，如给定链表1->1->1->2->3->3->4. 结果为1->3。请写出一个高效的算法
使用三个线程分别打印A，B，C，按ABC CBA ABC CBA ...的顺序进行打印 (阿里)
无重复字符的最长子串
寻找旋转排序数组中的最小值
旋转链表
LRU缓存机制
数据流的中位数
无序数组中找中位数，快排patition解决
搜索旋转排序数组
2.快速排序，堆排序，插入排序（其实八大排序算法都应该了解 介绍快速排序
1.给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
在十万的数字中找出前100？说了快排patition+二分，堆
10亿的数字找前10万，空间给1亿（分治法加堆），分析时间复杂度
100亿数字找前10亿，空间1亿（没怎么说好，bitmap）
10进制数转2进制数判断几个1
写个hashmap
1.一个圆上n个点，两两相连且连线不可交叉，找到所有可能性，奇数个点有一个点空余
2.平面内n个点，找到距离最近的两个点
最大连续子序列和
能用一个数组里数字组成的最大数字
```

## 数据结构
```markdown
怎么从一个数组中找出出现次数大于一半的那个数字？
还有一个求数组中两个数字相乘，求最大值，并打印出来
10个数，每个数在1~100之间，奇数从大到小输出，偶数从小到大。
给定一个数组 [1,2,3] 和一个值 x，数字可以重复选取，输出所有和等于 x 的结果。
给你一个16*16矩阵，从最左上角到最右下角，有几条路径
第一个缺失的正数
一个数字找出重复数字
```
### 栈队列算法题手写
优先队列底层实现？-堆
如何用两个栈实现队列
两个队列模拟栈，一个队列怎么做？
两个栈模拟队列？

