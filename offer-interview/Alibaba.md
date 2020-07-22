# Alibaba面试

## 一,实习
### [0229阿里淘系技术部一二面凉经](https://www.nowcoder.com/discuss/375916)
```markdown
一面 1h20min 
1. ==和equals区别【OK】
2. equals和hashcode，什么时候需要重写hashcode【OK】
ArrayList和LinkedList区别
3. java实现多线程的方式
4. 如何处理从线程的异常
5. java内存结构，怎么回收对象
6. 死循环是哪里会出现问题
7. 什么时候会OOM
8. 有哪些锁，ReentrantLock底层怎么实现
9. 线程池的核心参数
10. 线程池满了会怎么办，四种拒绝策略（我只回答上了默认的），ThreadLocal
11. 什么是守护线程，cpu结构
12. 线程上下文间的切换
13. mysql乐观，悲观锁
14. 如何使用联合索引，最左匹配原则，字段顺序如何选择
15. 索引失效
16. redis了解到什么程度（答：不太懂底层数据结构，会使用），rdb和aof区别
17. spring了解到什么程度，有看过底层源码吗（看了IOC，AOP还没看）
18. BeanFactory和FactroyBean，ApplictionContext和BeanFactory
19. AOP怎么实现，解决了开发中的哪些问题
20. 了解Template模板设计模式吗（不会）
21. MyBatis，MyBatis解决了什么问题，和Hibernate区别（没了解过Hibernate）
笔试：LRU，给了30min，给了接口类和测试代码
之前在面经上看见过，但没写过，用HashMap和ArrayList实现的，面试官看了觉得有些啰嗦，先问我加读写锁（不会），
然后告诉我可以用LinkedHashMap中的原方法实现。

二面 45min 3.4
聊项目，但我项目太水了，没聊多少，估计是这个原因，接下来一直考场景题
1. url输入后流程
2. 三次握手
3. GC有哪些算法
4. 如何分配Eden，Survivor区的大小
我：这个要看实际场景才能确定。。。
5. 如何生成一个有上亿个无序不重复随机数的文件
我：用不同区间的随机函数和HashSet生成
面试官：这会整体有序，怎么解决
我：整体再shuffle一下
面试官：有更好的办法吗？    无。。。
6. 还是上个问题，如何对这个文件中的数据排序？
我：分治思想，分成小段排序然后归并
面试官：怎么归并？
我：两两归并。说完就知道错了，然后没想出来。。
7. 会不会dubbo（不会），项目如何分布式部署吗？我只会用SpringCloud。。
就到这吧，还有什么想问的？
二面面试官是中台技术部的，面的每一分钟都是煎熬。。我再面完后又想了想排序的问题，觉得桶排序可行，就在微信上说了下我的思路，面试官回了个好的。。。
```
### [20200503阿里Java开发在等4面](https://www.nowcoder.com/discuss/421953)
```markdown
一面-电话面 (52min)
1.问项目、研究方向 
2.Hashset中的contains函数，equals实现
》》3.java代码输出hello world字符串的整个过程（这个我答的不是很全，如果看到我这篇帖子的可爱有知道的可以留言给我吗 笔芯）
[详解HelloWorld执行过程](https://blog.csdn.net/qq_27760433/article/details/72630633)
    编写代码->编译执行。
    java编译器首先找到源文件中的public class，再根据public class找到源文件中的其他类，java编译器会把类编译成一个字节码文件。
    java执行.class文件：
        系统找到文件中唯一的主类public class。
        据public static关键字找到跟主类关联可执行的main方法。
        String[] args:系统会传递一个空的字符串数组给主方法。系统加载String [].class字节码文件到方法区，系统会为args变量在主线程的栈帧中开辟一块空间（存放String[]数据的地址）指向堆中存放的数据。
        String s：系统加载String.class字节码文件到方法区，在主线程栈帧中为s变量开辟一块空间（存放数据的地址），此时s的值还没有确定（垃圾值，编译无法通过）。
        s=”HelloWorld”:字符串”HelloWorld”被存放到方法区的常量区中，并让s指向该地址。
        System.out.println(s):系统加载System.class字节码文件到方法区，并且系统会默认在堆区创建System.out、System.in、System.err三个对象。
        字符串在被输出时会自动调用toString()方法。
    输出 HelloWorld
4.随机生成不重复的10个100以内数字
二面-视频面 (30min)
1.技术含量的项目，用过什么中间件？
2.MySQL主键索引和非主键索引，索引的机制上有什么不同？
3.BloomFilter算法过程
4.设计模式（写代码） 单例模式的多线程，饿汉，懒汉非安全，懒汉安全，懒汉双重锁机制，静态内部类，枚举。
5.网络tcp断开4次挥手中timewait目的？确保客户端的报文正确到达服务端
6.二分查找（写代码）
三面-视频leader面 (45min)
1.数据结构-数组和链表修改第n个元素那个快？数组
2.二分查找和二叉树查找有什么区别？
    二叉查找树基于二分查找，但其查找复杂度并不稳定，极端情况下会退化为一条链，此时复杂度为O(n)。同时，二分查找基于数组，并且要求数组严格有序，但是二叉查找树在插入时并不用保证数组严格有序。
3.HashMap底层 Hash查找，get方法，loadfacter作用？影响hash查找效率的因素。
4.继承中的父类构造函数？
    A是B的父类，在初始化B的时候，会不会调用A的无参构造函数？默认会调用
    那么A的构造函数中打印当前类的信息，那么打印的是A的信息还是B的信息？打印B的信息
5.Java中有没有统一的一个父类？Object类
    [JDK1.8源码(一)——java.lang.Object类](https://www.cnblogs.com/ysocean/p/8419559.html)
    Object类是所有类的基类，当一个类没有直接继承某个类时，默认继承Object类，也就是说任何类都直接或间接继承此类，Object类中能访问的方法在所有类中都可以调用      
6.接口和抽象类区别？
7.SpringMVC中MVC是什么意思？
8.Java中int占4个字节，范围是多少？[-2^31,2^31 -1]
9.Integer和int的区别？
10.Integer.MAX_VALUE和Integer.MIN_VALUE存在哪里？
    存在方法区，属于堆，方法取之前也称为永久态。
11.Java内存分几块?方法区属于堆吗？
    方法去在堆里，是永久态
12.Java的基础类型有哪些？8种（4个整数，2个浮点数，char,boolean）
13.基础类型和引用类型有什么区别？
14.数组使基本类型还是对象？数组调用hashcode方法会输出什么？
    对象，会输出int型的数值
15.数组和其他对象调用toString()会输出什么？
    全类名+@+16进制无符号hashcode字符串
16.Integer==和int==的区别？Integer==int会有编译错误吗？
    基本类型和包装类型进行比较的时候，是怎么做的？装箱和拆箱
17.反射是什么？
    Bean的注入是通过反射实现的。
18.依赖注入
19.用过spring的事务吗？
20.用过哪些数据库？
21.redis和mysql有什么区别？
22.数据库事务是什么？
```
## 二,秋招

## 三,春招