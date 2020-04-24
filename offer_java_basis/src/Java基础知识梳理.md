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

### 20200423
[口气带你踩完五个 List 的大坑，真的是处处坑啊！](https://www.cnblogs.com/goodAndyxublog/p/12758755.html)
```markdown
1.数组转List:Arrays.toList(arrays)：
        不支持增删元素，会抛出UnsupportedOperationException
        共享原始数组，修改原始数组，影响新集合；修改新集合，影响原始数组。
        返回结果为Arrays一个内部类，修复List<String> list = new ArrayList<>(Arrays.asList(arrays));
2.List.subList()：
        生成新集合也会与原始 List 互相影响。SubList持有原始List引用，导致原有List无法被释放
        ArrayList的subList结果不可强转成ArrayList。subList返回的是ArrayList的内部类SubList，并不是ArrayList而是
        ArrayList的一个视图，对于SubList子列表的所有操作最终会反映到原列表上。
3.foreach删除元素：
        foreach这种迭代方式实际就是 Iterator 迭代器实现方式 所以ArrayList可能报异常
        CopyOnWriteList写操作发生在快照上，不会发生异常。
        修复 使用 Iterator#remove 删除元素，JDK1.8 List#removeIf
4.不可变集合：
        不可变集合只能被读取，不能做任何修改，包括增加，删除，修改，从而保护不可变集合的安全。
        不可变集合仅仅是原集合的视图，原集合任何改动都会影响不可变集合。
        使用 JDK9 List#of 方法。使用 Guava immutable list两种方式防止上。
5.使用工具类Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的add/remove/clear方法会抛出
UnsupportedOperationException异常。asList的返回对象是一个Arrays内部类，并没有实现集合的修改方法。
```
## JVM

