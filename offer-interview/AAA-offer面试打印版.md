# 面试打印版
>> [Java那些事儿](https://www.nowcoder.com/discuss/421353)
## 自我介绍
```markdown
教育经历 + 工作经历 + 简单的性格介绍。而工作经历中的项目将成为接下来面试官询问的重点，所以最好能准备一个拿得出手的项目。

如果在面试之前已经有工作经历和项目经历，就需要深挖你项目的亮点：

开发的时候使用的技术和中间件
遇到的问题、如何解决问题
是否有优化方案，如何优化你的项目
上面这三个问题基本上是最常见也是最基本的问题，准备项目的时候，一定要把上面这些问题准备好。
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

## 手写
```markdown
手写消费者生产者模式
堆排序的原理及时间复杂度，是否稳定，最坏及最坏场景。
手写快速排序、插入排序、冒泡排序，并分析时间复杂度和空间复杂度，它们的稳定性。（内部排序，外部排序）
二分查找算法简述如何改进。
手写算法：从中序与后序遍历序列构造二叉树
分隔链表；给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
    你应当保留两个分区中每个节点的初始相对位置。
```
### 手写单例模式
>> 一个类的构造函数私有化，然后在类中定义一个私有静态变量，通过一个静态函数get获得私有变量实例即可实现单例。
    如果想要懒加载，可以用上双重检验锁在get函数中。
>> 手写单例模式 1.写一个单例模式，
   4.线程安全的单例模式：双重检查写法
   4.用volatile+synchronized写一个单例模式，用双重校验锁方法，说出两个if判断语句的作用
   写个单例保证线程安全(虽然写出了，但被问住了，告诉我代码不能死记硬背)
   为什么还要再判断一次是否为空
   怎样保证这个单例在序列化和反序列中还是这个单例枚举
```markdown
class Singleton{
    public static volatile Singleton uniqueInstance;
    public static Singleton getUniqueInstance(){
        if(uniqueInstance==null){
            synchronized(Singleton.class){
                if(uniqueInstance==null){
                    uniqueInstance=new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
    public static void main(String args[]){
        for(int i=0;i<50;i++){
            new Thread(new Runnable(){
                public void run(){
                    System.out.println(Thread.currentThread().getName()+":"+Singleton.getUniqueInstance().hashCode());
                }
            }).start();
        }
    }
}
```
### 手写生产者消费者模型
#### 用ArrayBlockingQueue实现的生产者消费者模型
```markdown
public class ArrayBlockingQueueDemo {
    private ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3, true);
 
    public static void main(String[] args) {
        ArrayBlockingQueueDemo test = new ArrayBlockingQueueDemo();
        Consumer c1 = test.new Consumer();//内部非静态类实例化方式
        Producer p1 = test.new Producer();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(c1);
        //new Thread(p1).start();
        //new Thread(c1).start();
    }
 
    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("消费" + blockingQueue.take());
                    if (blockingQueue.size() == 0) {
                        System.out.println("队列为空，阻塞");
                    }
                }
            } catch (InterruptedException e1) {
                System.out.println("消费者等待时被打断");
                e1.printStackTrace();
            }
        }
    }
 
    class Producer extends Thread {
        private int element = 0;
 
        @Override
        public void run() {
            try {
                while (element < 20) {
                    System.out.println("生产" + element);
                    blockingQueue.put(element++);
                }
                if (blockingQueue.size() == 20) {
                    System.out.println("队列满，阻塞");
                }
            } catch (InterruptedException e) {
                System.out.println("生产者等空闲时被打断");
                e.printStackTrace();
            }
            System.out.println("终止生产");
        }
    }
}
```
#### 用ReentrantLock写生产者消费者模型
>> 手写一个生产者消费者模式，用的ReentrantLock，为什么判断当前count是否满足生产或者消费时用while
```markdown
public class ProducerAndConsumer {
    private int number = 0;
    private final int MAX = 10;
    private final int MIN = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
 
    public static void main(String args[]) {
        ProducerAndConsumer test = new ProducerAndConsumer();
        Consumer c1 = test.new Consumer();
        Producer p1 = test.new Producer();
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(p1);
        }
        for (int i = 0; i < 5; i++) {
            service.execute(c1);
        }
    }
 
    class Producer extends Thread {
        public void run() {
            try {
                lock.lock();
                while (number >= MAX) {//不用if是因为可能有错误唤醒的线程，while可以进行多次判断
                    System.err.println("产品已满");
                    condition.await();
                }
                number++;
                System.out.println("生产了一个产品，现在有：" + number + "个产品");
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
 
    class Consumer extends Thread {
        public void run() {
            try {
                lock.lock();
                while (number <= MIN) {
                    condition.await();
                }
                number--;
                System.out.println("消费了一个，现在有" + number);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
```
### LRU
```markdown
import java.util.LinkedHashMap;
 
public class LRUCache extends LinkedHashMap {
 
    //首先设定最大缓存空间 MAX_ENTRIES 为 3；
    private static final int MAX_ENTRIES = 3;
 
    //之后使用LinkedHashMap的构造函数将 accessOrder设置为 true，开启 LRU顺序；
    public LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }
 
    //最后覆盖removeEldestEntry(）方法实现，在节点多于 MAX_ENTRIES 就会将最近最少使用的数据移除。
    //因为这个函数默认返回false，不重写的话缓存爆了的时候无法删除最近最久未使用的节点
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
        //在容量超过最大允许节点数的时候返回true，使得在afterNodeInsertion函数中能执行removeNode()
        return size() > MAX_ENTRIES;
    }
 
    public static void main(String[] args) {
        LRUCache cache = new LRUCache();
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.put(4, 4);
        System.out.println(cache.keySet());
    }
}
```
### 快速排序
```markdown
作者：酥悠沫
链接：https://www.nowcoder.com/discuss/429362?type=2&channel=0&source_id=2
来源：牛客网

public class QuickSort {
    private  void quickSortC(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(a, l, r);
        quickSortC(a, l, p - 1);
        quickSortC(a, p + 1, r);
    }
    //空间浪费比较多的分区函数可以将小于分界点存一段，大于分界点存一段，再合并
    public  int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int i = l;
        for (int j = l; j < r; j++) {
            if (a[j] < pivot) {
                swap(a, i, j);
                i = i + 1;
            }
        }
        swap(a, i, r);
        return i;
    }
    public static void swap(int[] a,int l,int r){
        int temp=a[l];
        a[l]=a[r];
        a[r]=temp;
    }
    public static void main(String[] args) {
        int a[]={9,8,6,1,2,5,3,7,10,4};
        QuickSort quickSort=new QuickSort();
        for(int a1:a){
            System.out.print(a1+" ");
        }
        System.out.println();
        quickSort.quickSortC(a,0,9);
        for(int a1:a){
            System.out.print(a1+" ");
        }
    }
}
```
### 等概率无重复的从n个数中选取m个数
[等概率无重复的从n个数中选取m个数](https://blog.csdn.net/yusiguyuan/article/details/42607681)
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