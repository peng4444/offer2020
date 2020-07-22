# 百度面试题

### [0721百度java一面面经](https://www.nowcoder.com/discuss/456273)
```markdown
1.   最开始主要根据你的项目问一些问题，你觉得你项目中最大的亮点是什么，巴拉巴拉
2.   讲一下JDK、JRE、JVM分别是什么以及他们之间的关系 
3.   Java有哪些数据类型，分别占多少个字节？【V】
4.   抽象类与接口之间的区别 【V】
5.   聊一下集合类
6.   数据库的悲观锁、乐观锁
7.   一致性hash
8.   JVM内存区域
9.   讲一下类加载有哪些类型，以及class类加载和forname加载有哪些区别
10. 讲一下关键词native
11. 数据库的慢查询日志、执行计划、索引、B树和B+树的区别
12. 数据库中的搜索引擎了解吗？说一下有什么以及他们的区别
13. Redis的集群
14. 聊了下设计模式，单例和观察者模式，代理模式等等，一般会问你熟悉哪些代理模式
15. 死锁的四个必要条件
16. 说一下二叉搜索树如何转变为链表
17. 说一下KMP算法
18. 手撕堆排序以及一个死锁的案例
```
### [百度提前批一面凉经](https://www.nowcoder.com/discuss/456835)
```markdown
刚面了百度一面，本来约的七点，但是我这边网不好，然后面试官等了一段时间，感觉面试官都很好，不会的还会跟你解释，我只想说我是真的菜。话不多说直接上面经。
1.JVM,JRE,JDK的作用与区别；
2.Java中8种基本数据类型有哪些，它们占用的字节数分别是多少；【V】
3.接口与抽象类的区别；
4.创建线程有几种方法，它们的优缺点分别是什么；
5.加载类的方式有哪些；
6.类加载的流程是什么，每个步骤的作用；
7.class.forName加载类与classloader加载类有什么区别；
8.静态变量的加载与普通变量的加载有什么区别(静态变量走类加载流程，普通变量在堆中加载)；
9.说一下Java中的类加载器有哪些；
10.说一下Java内存模型包含哪些内容，哪些是线程私有的，哪些是线程共有的；
11.说一下进程与线程的区别；
12.如何杀死一个进程中的线程，可以说思路，也可以说指令；
13.说一下什么是死锁，如何避免死锁；
14.说一下乐观锁与悲观锁的原理，并说明它们分别用在哪些地方；
15.Redis集群如何进行数据同步；
16.MySQL的数据引擎有哪些；
17.MyIsam与Innodb的区别；
18.MyIsam中如何判断一个查询是慢查询；
19.如何优化查询；
20.为何不建议使用limit；
21.设计模式了解吗，说两个项目中用到的设计模式，并且说明使用之前与使用之后有了哪些优化；
22.Java中native关键字使用过吗，说说为什么要使用native关键字；
23.算法题：1.写一个死锁；2.写堆排序。
虽然问题看起来都很简单，但是后来聊了一下，面试官说你答的都太浅了，说明基础不牢固，八种基本数据类型数半天没数明白我也是醉醉的，面的我怀疑人生，不说了，补基础去。
```
### [百度秋招0929](https://www.nowcoder.com/discuss/290473)
```markdown
百度面经
一面
看了一眼我的项目，问是不是github上面的，我说是。。
面试官：你还挺老实，直接承认了
然后就没有问过我的项目了。。
1.spring BeanFactory和FactoryBean
2.spring bean生命周期
3.ConcurrentHashMap size()过程（我说我没看过这部分源码，面试官说可以自己发挥）
4.Synchronized ReentrantLock锁
5.写代码，多线程，三个方法A，B，C，循环打印十次A、B、C。没写出来，后面换了一道先执行完A、B再执行C
6.二叉树每个节点添加一个next指针，指向同一层的下一个节点（层次遍历，但是面试官说他觉得深度遍历更简单）
7.一个有障碍物的迷宫，求小球是否可以从左上角滚到右下角（有墙，可以向四个方向滚动，但会一直滚动到撞墙为止）
8.数据库隔离级别，innodb默认隔离级别，什么是幻读，innodb能不能解决幻读，怎么解决

（只记得这么多了。。）
最后他问我innodb怎么解决幻读的时候我直接说我忘了，面试官就笑了说你挺牛逼啊。。。当时就觉得凉了。。

二面（是个小姐姐）
1.同步和阻塞的区别
2.redis缓存穿透，缓存击穿，怎么解决
3.tcp协议的状态
4.线程池核心参数
5.线程池shutdown和shutdownnow的区别，实现原理
6.写代码，正则表达式匹配（剑指offer原题）

三面（全程被吊打）
从简历开始，说我的项目太简单没必要写，不如写自己研究方向
具体的问题记不太清了，还是偏技术的，我说的最多的就是不好意思/不清楚/不知道/不了解
（当时只想快点结束让我走吧）
两道代码题：
1.二叉树层次遍历
2.n个骰子，抛出点数和为m的概率（写了一个暴力的，他说让我回去看看动态规划。。）
结束的时候我问面试官这个是技术面吗，面试官说综合面，我问为什么不问我hr的问题，，，
面试官：我不看重这个，那我们就随便聊聊hr的问题吧。。
然后就随便聊了一下人生就结束了。。
```
### [0927百度面经，已拿offer](https://www.nowcoder.com/discuss/287101)
```markdown
百度现场一面45分钟
1.写代码判断A是不是B的子树
2.写代码判断一颗二叉树是不是平衡二叉树
3.手写sql
4.sql优化思路
5.redis的哨兵模式怎么做的，一主二从部署设置几个哨兵
6.springBoot的优点
7.applicationContext有哪些实现
8.垃圾回收算法
9.jvm调优
百度二面45分钟
1.对象引用的四个级别
2.spring的ioc和aop原理
3.jdk***和cglib实现有什么不同？效率上相比呢？
4.spring的事务？有哪些常用传播行为？默认是哪个？你平常开发怎么用的？
5.synchronized的3种使用方式的区别以及实现原理
6.volatile原理，JMM中的每个工作内存是在操作系统分配的还是在jvm分配的。
写个单例的DCL，为什么有synchronized还需要volatile？
7.Mybatis中#和{}的区别
8.http请求报文有哪些内容
9.https原理
设计题：百度有十几万个代码库，每个代码库对应一个路径，这个路径最多4-5层。设计存储结构和查找算法，给你一个路径要在十几万个路径中快速的找到这个路径。要求空间复杂度和时间复杂度尽可能低

百度三面（50分钟）

1.讲项目，针对项目问一些点。

2.问实习情况，为什么没转正，有什么收获。等等

3.然后就说技术前面评价还可以就不问了，要看我有没有想法，学习能力，可塑造性。然后就开始被各种怼，反正面完郁闷的很。

三面完还发帖子吐槽，也不知道最后为什么过了。最后好像被分到什么移动生态部
```
### [百度Java实习面经](https://www.nowcoder.com/discuss/405764?type=2)
```markdown
一面 50分钟左右
说说你常用的集合?

回答了ArrayList和HashMap, 然后问了源码实现
说说Redis数据结构?

具体讲讲跳表?

这里问了为啥跳表max_level是32, 没回答上
具体讲讲Dict?

这里问到了Dict啥时候会发生rehash, 我也不知道...
代码题

二叉树后序遍历
讲讲MySQL聚簇引擎?

讲讲MVCC?

A事务读出了一条记录, 此时B事务把这条记录删除并提交, 问A事务还能读到这条记录吗?

读已提交隔离级别 不能读到
可重复读隔离级别 能读到 (结合MVCC讲)
项目相关 (问得很简单)

讲讲线程池核心参数?

反问

部门是做什么的?
主要是做广告, 然后后面的搞忘了
总结

​ 一面面试官是我体验最好的一次面试, 回答HashMap和跳表源码的时候一直夸我学得深入, 身为菜鸡的我得到了极大的鼓励. 写代码题的时候还帮我指出了错误, 非常非常好的一个面试官!

 一面完排队30分钟左右进入二面

二面 50分钟左右
讲讲HashMap扩容阈值?

比较下Synchronized和ReentrantLock

讲讲volatile关键字

AQS内部公平锁和非公平锁的实现

这个没看过源码, 没答上来
代码题

手写快速排序
在快速排序的基础上改成找第K大/第K小
快改完了说下一题, 有点无语...
跳台阶问题
这里面试官没描述清楚, 然后翻车了
讲讲你都怎么使用Redis的?

我这里说了说项目里面的用法
Spring Transaction失效场景

不会...
总结

​ 这一面主要考了代码, 不过基本全翻车了, 问到的源码刚好是我没看的, 还好给了三面

      二面完排队30分钟左右进入三面

三面 30~40分钟左右
大学经历

讲讲投入精力最多的项目

技术方面的优点缺点

非技术方面的优点缺点

低谷的经历

逆袭的经历

破坏规则而开心的经历

有多少offer?

0
想过来北京发展吗?

能接受24小时待命工作吗?

实习时间

代码题

两个字符串求最长公共子串(要求输出该字符串)
反问

转正率
回答说能力够强就不用担心... (跟没回答差不多)
总结

  有些问题属实有点尴尬, 其余还好

总总结
      百度一次全部面完体验挺好; 二面面得挺差, 希望能有offer!
```