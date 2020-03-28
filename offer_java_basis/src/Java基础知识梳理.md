# Java
## Java基础
### 1.多线程和并发
[Java并发之AQS全面详解](https://www.cnblogs.com/Ccwwlx/p/12116668.html)
#### 1.1锁
[乐观锁和悲观锁](https://www.cnblogs.com/teach/p/9775077.html)
```markdown
乐观锁:主要强调的是每次取数据的时候，都认为别的线程或事务不会修改数据，所以不会对数据进行上锁，只有在更新数据的时候才会判断是否有线程对要操作的数据进行修改；
悲观锁:主要强调的是每次取数据的时候，都认为别的线程会修改数据，所以每次都会对数据上锁，只有获取锁的情况下才有操作数据的机会；
```
[锁开销优化以及 CAS 简单说明](https://www.cnblogs.com/cposture/p/10761396.html#_labelTop)
[Java中锁的实现与内存语义](https://www.cnblogs.com/wuqinglong/p/9962142.html)
[一句话撸完重量级锁、自旋锁、轻量级锁、偏向锁、悲观、乐观锁等各种锁](https://www.cnblogs.com/kubidemanong/p/10777928.html)
[【BAT面试题系列】面试官：你了解乐观锁和悲观锁吗？](https://www.cnblogs.com/kismetv/p/10787228.html)
### 2.同步、异步,
### 3.类集
```markdown
    数组 - > 链表 ->类集  ：类集就是Java数据结构的实现，类集就是动态对象数组   源码
    Collection
        List（有序，可重复）
            ArrayList
            LinkedList
            Vector
        Set（无序，不可重复）
            HashSet
                LinkedHashSet
            TreeSet
    Map(双列集合)
        HashTable 
            Poperties
        HashMap
            LinkedHashMap
        TreeMap
    
    集合输出 Iterator,ListIterator(双向集合输出),Enumeration,for each ,Enumeration只有在Vector接口中使用
```
[[面试必问之ArrayList](https://www.cnblogs.com/fsmly/p/11283921.html)]

[LinkedList集合解析及手写集合](https://www.cnblogs.com/hang-on/p/11469263.html)

[JAVA面试题 手写ArrayList的实现，在笔试中过关斩将?](https://www.cnblogs.com/marsitman/p/11204338.html)

[List集合根据存储对象的属性字段排序实现](https://blog.csdn.net/u013821825/article/details/61202287)

[HashMap常见面试题整理](https://www.cnblogs.com/zengcongcong/p/11295349.html)

[深入理解HashMap](https://www.cnblogs.com/fsmly/p/11235484.html)

[红黑树这个数据结构，让你又爱又恨？看了这篇，妥妥的征服它](https://www.cnblogs.com/wskwbog/p/11236136.html)

[[Java集合 HashSet的原理及常用方法](https://www.cnblogs.com/LiaHon/p/11257805.html)]

[[TreeMap 还能排序？分析下源码就明白了](https://www.cnblogs.com/wskwbog/p/11245010.html)]

[[这 3 个 Set 集合的实现有点简单，那来做个总结吧](https://www.cnblogs.com/wskwbog/p/11260056.html)]

[[HashMap、Hash Table、ConcurrentHashMap](https://www.cnblogs.com/wudidamowang666/p/11286279.html)]

[[Java集合系列(四)：HashMap、Hashtable、LinkedHashMap、TreeMap的使用方法及区别](https://www.cnblogs.com/zwwhnly/p/11304627.html)]

[刨死你系列——HashMap(jdk1.8)](https://www.cnblogs.com/Young111/p/11471049.html)

[[HashMap 实现及原理](https://www.cnblogs.com/jay-wu/p/10773976.html)]

[[jdk1.8 HashMap底层数据结构：深入解析为什么jdk1.8 HashMap的容量一定要是2的n次幂](https://www.cnblogs.com/laipimei/p/11316140.html)]

[[Array List和Linked List实现分析](https://www.cnblogs.com/fenjyang/p/11480944.html)]

[[Java 迭代接口：Iterator、ListIterator 和 Spliterator](https://www.cnblogs.com/liululee/p/11416038.html)]
[Comparable接口的实现和使用](https://www.cnblogs.com/wl-centrinc/p/11872758.html)
## JVM

