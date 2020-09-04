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
### 手写单例模式【2+】
[设计模式：单例模式介绍及8种写法（饿汉式、懒汉式、Double-Check、静态内部类、枚举）](https://www.cnblogs.com/lifegoeson/p/13474269.html)
>> 单例模式使用的场景是：需要频繁创建和销毁的对象、创建对象耗时过多或耗资源太多（重型对象）、工具类对象、频繁访问数据库或者文件的对象（数据源、session工厂等），都应用单例模式去实现。
>> 单例模式有哪些实现方式？双重检查锁怎么实现，为什么用volatile，序列化破坏单例了解吗，怎么避免？
饿汉式（静态变量）,饿汉式（静态代码块）,懒汉式（线程不安全）,懒汉式（线程安全）,懒汉式（同步代码块）,双重检查锁,静态内部类,枚举。
#### 1.饿汉式（静态变量）
```markdown
class Singleton{
    //1私有化构造方法 （防止用new来得到对象实例）
    private Singleton(){}
    //2创建对象实例
    private final static Singleton instance = new Singleton();
    //3对外提供公有静态方法
    public static Singleton getInstance(){
        return instance;
    }
}
//获取对象就不能通过new的方式，而要通过Singleton.getInstance()；并且多次获取到的都是同一个对象。
优点：
    简单，类装载的时候就完成了实例化，避免了多线程同步的问题。
缺点：
    类装载的时候完成实例化，没有达到Lazy Loading（懒加载）的效果，如果从始至终都没用过这个实例呢？那就会造成内存的浪费。
    （大多数的时候，调用getInstance方法然后类装载，是没问题的，但是导致类装载的原因有很多，可能有其他的方式或者静态方法导致类装载）
```
#### 2.饿汉式（静态代码块）
```markdown
class Singleton{
    //1同样私有化构造方法
    private Singleton(){}
    //2创建对象实例
    private static Singleton instance;
    //在静态代码块里进行单例对象的创建
    static {
        instance = new Singleton();
    }
    //3提供静态方法返回实例对象
    public static Singleton getInstance() {
        return instance;
    }
}
优缺点：和上一种静态常量的方式一样；
原因：实现本来就是和上面的一样，因为类装载的时候一样马上会执行静态代码块中的代码。
```
#### 3.懒汉式（线程不安全）
```markdown
class Singleton{
    private static Singleton instance;
    private Singleton(){}
    //提供静态公有方法，使用的时候才创建instance
    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return  instance;
    }
}
优点：
    起到了Lazy Loading 的作用
缺点：
    但是只能在单线程下使用。如果一个线程进入了if判断，但是没来得及向下执行的时候，另一个线程也通过了这个if语句，
    这时候就会产生多个实例，所以多线程环境下不能使用这种方式。
```
#### 4.懒汉式（线程安全）
```markdown
class Singleton{
    private static Singleton instance;
    private Singleton(){}
    //使用的时候才创建instance,同时加入synchronized同步代码，解决线程不安全问题
    public static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return  instance;
    }
}
优点：
    保留了单例的性质的情况下，解决了线程不安全的问题
缺点：
    效率太差了，每个线程想要获得类的实例的时候都调用getInstance方法，就要进行同步。
    然而这个方法本身执行一次实例化代码就够了，后面的想要获得实例，就应该直接return，而不是进行同步。
```
#### 5.懒汉式（同步代码块）
```markdown
class Singleton{
    private static Singleton instance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(instance == null){
            synchronized( Singleton.class){
                instance = new Singleton();
            }
        }
        return  instance;
    }
}
会导致可能别的线程同样进入if语句，回到了第三种的问题，所以来不及同步就会产生线程不安全的问题。
```
#### 6.双重检查锁【推荐开发使用】
```markdown
class Singleton{
    private static volatile Singleton instance;
    private Singleton(){}
    //双重检查
    public static Singleton getInstance(){
        //第一次检查
        if(instance == null){
            synchronized (Singleton.class){
                //第二次检查
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return  instance;
    }
}
优点：double-check是多线程开发里经常用到的，满足了我们需要的线程安全&&避免反复进行同步的效率差&&lazy loading。
```
#### 7.静态内部类
```markdown
class Singleton{
    //构造器私有化
    private Singleton(){}
    //一个静态内部类，里面有一个静态属性，就是实例
    private static class SingletonInstance{
        private static final Singleton instance = new Singleton();
    }
    //静态的公有方法
    public static Singleton getInstance(){
        return SingletonInstance.instance;
    }
}
静态内部类：用static修饰的内部类，称为静态内部类，完全属于外部类本身，不属于外部类某一个对象，外部类不可以定义为静态类，Java中静态类只有一种，那就是静态内部类。
核心：
    1.静态内部类在外部类装载的时候并不会执行，也就是满足了lazy loading；
    2.调用getInstance的时候会取属性，此时才加载静态内部类，而jvm底层的类装载机制是线程安全的，所以利用jvm达到了我们要的线程安全；
    3.类的静态属性保证了实例化也只会进行一次，满足单例。
```
#### 8.枚举
```markdown
enum Singleton{
    instance;
    public void sayOk(){
        System.out.println("ok");
    }
}
调用的时候也不用new，直接用Singleton.instance，拿到这个属性。（一般INSTANCE写成大写）
优点：
    满足单例模式要的特点，同时还能够避免反序列化重新创建新的对象。这种方法是effective java作者提供的方式。
```
#### 9.JDK中的单例模式
```markdown
untime类就是一个单例模式的类，并且可以看到，他是采用饿汉式（静态常量的方式）
    1.私有构造器；
    2.静态常量，类的内部直接将类实例化；
    3.提供公有的静态方法。
```
### 手写工厂模式【2+】
[设计模式：工厂设计模式介绍及3种写法（简单工厂、工厂方法、抽象工厂）](https://www.cnblogs.com/lifegoeson/p/13474404.html)
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
### LRU【3+】
#### 基于LinkedhashMap实现的LRU算法 LeetCode146
```java
class LRUCache {
    private int cap;
    private Map<Integer,Integer> map = new LinkedHashMap<>();
    public LRUCache(int capacity) {
        this.cap = capacity;
    }
    
    public int get(int key) {
        if(map.keySet().contains(key)){
            int val = map.get(key);
            map.remove(key);
            map.put(key,val);
            return val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.keySet().contains(key)){
            map.remove(key);
        }else if(map.size()==cap){
            Iterator<Map.Entry<Integer,Integer>> iterator = map.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
        map.put(key,value);
    }
}

```
### 查找排序算法
![](https://img2020.cnblogs.com/blog/1176183/202004/1176183-20200402212157028-1769221183.png)
#### 二分查找
```markdown
//迭代
    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }
    //寻找左侧边界的二分搜索
    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }
    //寻找右侧边界的二分查找   ​
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

```
#### 选择排序 时间复杂度O(n^2) 不稳定排序
```markdown
//第一个跟后面的所有数相比，如果小于（或小于）第一个数的时候，暂存较小数的下标，
   *   第一趟结束后，将第一个数，与暂存的那个最小数进行交换，第一个数就是最小（或最大的数）
   *   下标移到第二位，第二个数跟后面的所有数相比，一趟下来，确定第二小（或第二大）的数
   *   重复以上步骤，直到指针移到倒数第二位，确定倒数第二小（或倒数第二大）的数，那么最后一位也就确定了，排序完成。
public int[] chanceSort(int[] array) {
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        int[] arr = Arrays.copyOf(array,array.length);
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }
```
#### 插入排序 时间复杂度O(n^2)
```markdown
//第一位数已经有序从第二位开始遍历，当前数（第一趟是第二位数）与前面的数依次比较，如果前面的数大于当前数，则将这个数放在当前数的位置上，当前数的下标-1，
   * 重复以上步骤，直到当前数不大于前面的某一个数为止，这时，将当前数，放到这个位置。
   * 1-3步就是保证当前数的前面的数都是有序的，内层循环的目的就是将当前数插入到前面的有序序列里
   * 重复以上3步，直到遍历到最后一位数，并将最后一位数插入到合适的位置，插入排序结束。
public int[] insertSort(int[] num) {
        if (null == num || num.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        for (int i = 1, j, current; i < num.length; i++) {
            current = num[i];
            for (j = i - 1; j >= 0 && num[j] > current; j--) {
                num[j + 1] = num[j];
            }
            num[j + 1] = current;
        }
        return num;
    }
```
#### 冒泡排序 时间复杂度O(n^2)
```markdown
//把相邻的元素两两比较，当一个元素大于右侧相邻元素时，交换它们的位置；当一个元素小于或等于右侧相邻元素时，位置不变。
//冒泡排序
    public static void MaopaoSort(int[] array) {
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        //外循环是趟数，每一趟都会将未排序中最大的数放到尾端
        for (int i = 0; i < array.length - 1; i++) {
            //内循环是从第一个元素开始，依次比较相邻元素，
            // 比较次数随着趟数减少，因为每一趟都排好了一个元素
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    //优化1：在最后一次交换开始之前，已经有序，就提前终止循环排序
    public static void MaopaoSort2(int array[]) {
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始值都是true，假设每一趟开始前都假设已经有序
            boolean sortFlag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //因为有元素进行交换，所以不是有序的，标记为false
                    sortFlag = false;
                }
            }
            if (sortFlag) {
                break;//如果某一轮有序标记为true，说明当前已有序，可以终止循环
            }
        }
    }
    //优化2：我们可以在每一轮排序后，记录下来最后一次元素交换的位置，该位置即为有序无序数列的边界，再往后就是有序区了
    public static void MaopaoSort3(int array[]) {
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //当前趟无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始值都是true，假设每一趟开始前都假设已经有序
            boolean sortFlag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //因为有元素进行交换，所以不是有序的，标记为false
                    sortFlag = false;
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (sortFlag) {
                break;//如果某一轮有序标记为true，说明当前已有序，可以终止循环
            }
        }
        // 还可以进一步优化， 有兴趣的可以去看看鸡尾酒排序
    }
```
#### 快速排序 时间复杂度是O(nlogn) 不稳定排序
```markdown
//选一个数作为基数（这里我选的是第一个数），大于这个基数的放到右边，小于这个基数的放到左边，
      等于这个基数的数可以放到左边或右边，一趟结束后，将基数放到中间分隔的位置，
      第二趟将数组从基数的位置分成两半，分割后的两个的数组继续重复以上步骤，
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
}
```
#### 堆排序 时间复杂度是O(nlogn) 不稳定排序
```markdown
//把无序数组构建成二叉堆。需要从小到大排序，则构建成最大堆；需要从大到小排序，则构建成最小堆。
  循环删除堆顶元素，替换到二叉堆的末尾，调整堆产生新的堆顶。
    /*** “下沉”调整
     * @param array 待调整的堆
     * @param parentIndex “下沉”的父节点
     * @param length 堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // temp 保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex +1]> array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个孩子的值，则直接跳出
            if (temp >= array[childIndex]) break;
            //无须真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }
    /*** 堆排序（升序）* @param array 待调整的堆*/
    public static void heapSort(int[] array) {
        // 1. 把无序数组构建成最大堆
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        // 2. 循环删除堆顶元素，移到集合尾部，调整堆产生新的堆顶
        for (int i = array.length - 1; i > 0; i--) {
            // 最后1个元素和第1个元素进行交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            // “下沉”调整最大堆
            downAdjust(array, 0, i);
        }
    }
```
#### 归并排序 时间复杂度O(nlogn)
```markdown
//归并排序就是先将要排序的数组递归地分成两半分别排序，然后将结果归并起来。
   * 1.  向上归并排序的时候，需要一个暂存数组用来排序，
   * 2.  将待合并的两个数组，从第一位开始比较，小的放到暂存数组，指针向后移，
   * 3.  直到一个数组空，这时，不用判断哪个数组空了，直接将两个数组剩下的元素追加到暂存数组里，
   * 4.  再将暂存数组排序后的元素放到原数组里，两个数组合成一个，这一趟结束。
public static void mergeSort(int[] arr, int lo, int hi) {
        if(lo==hi){
            return;
        }
        int mid = ((hi-lo)>>1)+lo;
        mergeSort(arr, lo, hi);
        mergeSort(arr,mid+1,hi);
        merge(arr,lo,mid,hi);
    }
    //二路归并的实现
    public static void merge(int[] arr, int lo, int mid, int hi) {
        int[] temp = arr.clone();
        int k = lo, i = lo, j = mid + 1;
        while (k <= hi) {
            if (i > mid) {
                arr[k++] = temp[j++];
            } else if (j > hi) {
                arr[k++] = temp[i++];
            } else if (temp[j] < temp[i]) {
                arr[k++] = temp[j++];
            } else {
                arr[k++] = temp[i++];
            }
        }
    }
```
#### 计数排序和桶排序 时间复杂度O(n)
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
找出多数元素（美团）找出数组中大于一半的数。下一个大于N的回文数
最长公共字串 （美团）
单例模式 （美团）
快速排序 （阿里）
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
```

## 数据结构
```markdown
怎么从一个数组中找出出现次数大于一半的那个数字？
还有一个求数组中两个数字相乘，求最大值，并打印出来
10个数，每个数在1~100之间，奇数从大到小输出，偶数从小到大。
给你一个16*16矩阵，从最左上角到最右下角，有几条路径
```
### 栈队列算法题手写
优先队列底层实现？-堆
如何用两个栈实现队列
两个队列模拟栈，一个队列怎么做？
两个栈模拟队列？

