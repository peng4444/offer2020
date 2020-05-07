# Java
## 面对对象思想
### 三大特性
```markdown
封装:利用抽象数据类型将数据和基于数据的操作封装在一起，使其构成一个不可分割的独立实体。数据被保护在抽象数据类型的内部，尽可能地隐藏内部的细节，
只保留一些对外的接口使其与外部发生联系。用户无需关心对象内部的细节，但可以通过对象对外提供的接口来访问该对象。
优点：
    减少耦合：可以独立地开发、测试、优化、使用、理解和修改
    减轻维护的负担：可以更容易被程序员理解，并且在调试的时候可以不影响其他模块
    有效地调节性能：可以通过剖析来确定哪些模块影响了系统的性能
    提高软件的可重用性
    降低了构建大型系统的风险：即使整个系统不可用，但是这些独立的模块却有可能是可用的
继承:
    继承应该遵循里氏替换原则，子类对象必须能够替换掉所有父类对象。
    Cat 可以当做 Animal 来使用，也就是说可以使用 Animal 引用 Cat 对象。父类引用指向子类对象称为 向上转型 。
多态:多态分为编译时多态和运行时多态：
    编译时多态主要指方法的重载
    运行时多态指程序中定义的对象引用所指向的具体类型在运行期间才确定
运行时多态有三个条件：
    继承
    覆盖（重写）
    向上转型
```
### 类图
```markdown
泛化关系 (Generalization):用来描述继承关系，在 Java 中使用 extends 关键字。
实现关系 (Realization):用来实现一个接口，在 Java 中使用 implements 关键字。
聚合关系 (Aggregation):表示整体由部分组成，但是整体和部分不是强依赖的，整体不存在了部分还是会存在。
组合关系 (Composition):和聚合不同，组合中整体和部分是强依赖的，整体不存在了部分也不存在了。比如公司和部门，公司没了部门就不存
                        在了。但是公司和员工就属于聚合关系了，因为公司没了员工还在。
关联关系 (Association):表示不同类对象之间有关联，这是一种静态关系，与运行过程的状态无关，在最开始就可以确定。
        因此也可以用 1 对1、多对1、多对多这种关联关系来表示。比如学生和学校就是一种关联关系，一个学校可以有很多学生，
        但是一个学生只属于一个学校，因此这是一种多对一的关系，在运行开始之前就可以确定。
依赖关系 (Dependency)：和关联关系不同的是，依赖关系是在运行过程中起作用的。A 类和 B 类是依赖关系主要有三种形式：
        A 类是 B 类方法的局部变量；
        A 类是 B 类方法当中的一个参数；
        A 类向 B 类发送消息，从而影响 B 类发生变化。
```
### 设计原则
```markdown
1. 单一责任原则:修改一个类的原因应该只有一个。
    换句话说就是让一个类只负责一件事，当这个类需要做过多事情的时候，就需要分解这个类。
    如果一个类承担的职责过多，就等于把这些职责耦合在了一起，一个职责的变化可能会削弱这个类完成其它职责的能
力。
2. 开放封闭原则:类应该对扩展开放，对修改关闭。
    扩展就是添加新功能的意思，因此该原则要求在添加新功能时不需要修改代码。
    符合开闭原则最典型的设计模式是装饰者模式，它可以动态地将责任附加到对象上，而不用去修改类的代码。
3. 里氏替换原则:子类对象必须能够替换掉所有父类对象。
    继承是一种 IS-A 关系，子类需要能够当成父类来使用，并且需要比父类更特殊。
    如果不满足这个原则，那么各个子类的行为上就会有很大差异，增加继承体系的复杂度。
4. 接口分离原则:不应该强迫客户依赖于它们不用的方法。
    因此使用多个专门的接口比使用单一的总接口要好。
5. 依赖倒置原则:高层模块不应该依赖于低层模块，二者都应该依赖于抽象；抽象不应该依赖于细节，细节应该依赖于抽象。
    高层模块包含一个应用程序中重要的策略选择和业务模块，如果高层模块依赖于低层模块，那么低层模块的改动就会
    直接影响到高层模块，从而迫使高层模块也需要改动。
1. 迪米特法则
迪米特法则又叫作最少知识原则（Least Knowledge Principle简写LKP），就是说一个对象应当对其他对象有尽可能少的了解，不和陌生人说话。
2. 合成复用原则
尽量使用对象组合，而不是通过继承来达到复用的目的。
3. 共同封闭原则
一起修改的类，应该组合在一起（同一个包里）。如果必须修改应用程序里的代码，我们希望所有的修改都发生在一个包里（修改关闭），而不是遍布在很多包里。
4. 稳定抽象原则
最稳定的包应该是最抽象的包，不稳定的包应该是具体的包，即包的抽象程度跟它的稳定性成正比。
5. 稳定依赖原则
包之间的依赖关系都应该是稳定方向依赖的，包要依赖的包要比自己更具有稳定性。
```
## Java基础
### 1.数据类型
```markdown
byte/8
char/16
short/16
int/32
float/32
long/64
double/64
boolean/~
boolean 只有两个值：true、false，可以使用1bit 来存储，但是具体大小没有明确规定。JVM会在编译时期将
boolean 类型的数据转换为int，使用1来表示true，0表示false。JVM支持boolean数组，但是是通过读写byte数组来实现的。
包装类型:基本类型都有对应的包装类型，基本类型与其对应的包装类型之间的赋值使用自动装箱与拆箱完成。
缓存池:new Integer(123) 与 Integer.valueOf(123) 的区别在于：    
    new Integer(123) 每次都会新建一个对象；
    Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
    valueOf() 方法的实现比较简单，就是先判断值是否在缓存池中，如果在的话就直接返回缓存池的内容。
    在 Java 8 中，Integer 缓存池的大小默认为 -128~127。
float与double：Java不能隐式执行向下转型，因为这会使得精度降低。
```
### 2.String 被声明为 final，因此它不可被继承。
```markdown
String被声明为final，因此它不可被继承。在 Java 8 中，String内部使用char数组存储数据。
在Java9之后，String类的实现改用byte数组存储字符串，同时使用coder来标识使用了哪种编码。
value数组被声明为final，这意味着value数组初始化之后就不能再引用其它数组。并且String内部没有改变value数组的方法，因此可以保证String不可变。
不可变的好处    
    1. 可以缓存 hash 值
    因为String的hash值经常被使用，例如String用做HashMap的key。不可变的特性可以使得hash值也不可变，因此只需要进行一次计算。
    2. String Pool(字符串常量池)的需要
    如果一个String对象已经被创建过了，那么就会从String Pool中取得引用。只有String是不可变的，才可能使用String Pool。
    当一个字符串调用 intern()方法时，如果StringPool中已经存在一个字符串和该字符串值相等（使用equals() 方法进行确定），
    那么就会返回 String Pool中字符串的引用；否则，就会在String Pool中添加一个新的字符串，并返回这个新字符串的引用。
    3. 安全性
    String经常作为参数，String不可变性可以保证参数不可变。例如在作为网络连接参数的情况下如果String是可变的，
    那么在网络连接过程中，String被改变，改变String对象的那一方以为现在连接的是其它主机，而实际情况却不一定是。
    4. 线程安全
    String不可变性天生具备线程安全，可以在多个线程中安全地使用。
String 不可变；StringBuffer 和 StringBuilder 可变
String不可变，因此是线程安全的；StringBuilder不是线程安全的；StringBuffer是线程安全的，内部使用synchronized进行同步。
```
### 3.Object 的方法 clone
```markdown
clone():是Object的protected方法，它不是public，一个类不显式去重写clone()，其它类就不能直接去调用该类实例的clone()方法。
浅拷贝:拷贝对象和原始对象的引用类型引用同一个对象。
深拷贝:拷贝对象和原始对象的引用类型引用不同对象。
clone()的替代方案:最好不要去使用 clone()，可以使用拷贝构造函数或者拷贝工厂来拷贝一个对象。
```
### 4. final和static
```markdown
final:声明数据为常量，可以是编译时常量，也可以是在运行时被初始化后不能被改变的常量。
    对于基本类型，final 使数值不变；
    对于引用类型，final 使引用不变，也就不能引用其它对象，但是被引用的对象本身是可以修改的。
    声明方法不能被子类重写。
    声明类不允许被继承。
static:静态变量：又称为类变量，也就是说这个变量属于类的，类所有的实例都共享静态变量，可以直接通过类名来访问它。静态变量在内存中只存在一份。
       实例变量：每创建一个实例就会产生一个实例变量，它与该实例同生共死。
       静态方法在类加载的时候就存在了，它不依赖于任何实例。所以静态方法必须有实现，也就是说它不能是抽象方法。
       静态语句块在类初始化时运行一次。
       非静态内部类依赖于外部类的实例，而静态内部类不需要。静态内部类不能访问外部类的非静态的变量和方法。
       在使用静态变量和方法时不用再指明 ClassName，从而简化代码，但可读性大大降低。
       静态变量和静态语句块优先于实例变量和普通语句块，静态变量和静态语句块的初始化顺序取决于它们在代码中的顺序。
```
## Java高级
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
### 2.同步、异步
### 3.Java容器
#### 1.Java容器集合
[java集合详解](https://www.cnblogs.com/yanzezhong/p/12808089.html)
```markdown
数组 - > 链表 ->类集  ：类集就是Java数据结构的实现，类集就是动态对象数组   源码
    Collection
        List（有序，可重复）
            ArrayList:基于动态数组实现，支持随机访问。
            LinkedList:基于双向链表实现，只能顺序访问，但是可以快速地在链表中间插入和删除元素。还可以用作栈、队列和双向队列。
            Vector:和 ArrayList 类似，但它是线程安全的。
        Set（无序，不可重复）
            HashSet:HashSet 查找的时间复杂度为 O(1)，TreeSet 则为 O(logN)。基于哈希表实现，支持快速查找，但不支持有序性操作。
                    并且失去了元素的插入顺序信息，也就是说使用 Iterator 遍历 HashSet 得到的结果是不确定的。
            LinkedHashSet:具有 HashSet 的查找效率，且内部使用双向链表维护元素的插入顺序。
            SortedSet接口
                TreeSet:基于红黑树实现，支持有序性操作，例如根据一个范围查找元素的操作。但是查找效率不如
        Queue
            LinkedList:可以用它来实现双向队列。
            PriorityQueue:基于堆结构实现，可以用它来实现优先队列。
    Map(双列集合)
        HashTable：和HashMap类似，但它是线程安全的，这意味着同一时刻多个线程可以同时写入HashTable并且不会导致数据不一致。
        它是遗留类，不应该去使用它。现在可以使用ConcurrentHashMap来支持线程安全，并且ConcurrentHashMap的效率会更高，因为ConcurrentHashMap引入了分段锁。 
            Poperties
        HashMap：基于哈希表实现。
        LinkedHashMap：继承HashMap实现Map接口，使用双向链表来维护元素的顺序，顺序为插入顺序或者最近最少使用（LRU）顺序。
        SortedMap接口
            TreeMap：基于红黑树实现。
            
集合输出 Iterator,ListIterator(双向集合输出),Enumeration,for each ,Enumeration只有在Vector接口中使用
```
#### 2.Java容器中的设计模式
[设计模式 - 迭代器模式详解及其在ArrayList中的应用](https://www.cnblogs.com/songjilong/p/12807345.html)
```markdown
迭代器模式：Collection继承了Iterable接口，iterator()方法能够产生一个Iterator对象，通过这个对象就可以迭代遍历Collection中的元素。
            从 JDK1.5之后可以使用foreach方法来遍历实现了Iterable接口的聚合对象。
适配器模式：java.util.Arrays#asList()可以把数组类型转换为List类型。
            应该注意的是asList()的参数为泛型的变长参数，不能使用基本类型数组作为参数，只能使用相应的包装类型数组。
```

[[面试必问之ArrayList](https://www.cnblogs.com/fsmly/p/11283921.html)]

[LinkedList集合解析及手写集合](https://www.cnblogs.com/hang-on/p/11469263.html)

[JAVA面试题 手写ArrayList的实现，在笔试中过关斩将?](https://www.cnblogs.com/marsitman/p/11204338.html)

[List集合根据存储对象的属性字段排序实现](https://blog.csdn.net/u013821825/article/details/61202287)

[HashMap常见面试题整理](https://www.cnblogs.com/zengcongcong/p/11295349.html)

[深入理解HashMap](https://www.cnblogs.com/fsmly/p/11235484.html)

[红黑树这个数据结构，让你又爱又恨？看了这篇，妥妥的征服它](https://www.cnblogs.com/wskwbog/p/11236136.html)

[[Java集合HashSet的原理及常用方法](https://www.cnblogs.com/LiaHon/p/11257805.html)]

[[TreeMap 还能排序？分析下源码就明白了](https://www.cnblogs.com/wskwbog/p/11245010.html)]

[[这 3 个 Set集合的实现有点简单，那来做个总结吧](https://www.cnblogs.com/wskwbog/p/11260056.html)]

[[HashMap、HashTable、ConcurrentHashMap](https://www.cnblogs.com/wudidamowang666/p/11286279.html)]

[[Java集合系列(四)：HashMap、Hashtable、LinkedHashMap、TreeMap的使用方法及区别](https://www.cnblogs.com/zwwhnly/p/11304627.html)]

[刨死你系列——HashMap(jdk1.8)](https://www.cnblogs.com/Young111/p/11471049.html)

[[HashMap 实现及原理](https://www.cnblogs.com/jay-wu/p/10773976.html)]

[[jdk1.8 HashMap底层数据结构：深入解析为什么jdk1.8 HashMap的容量一定要是2的n次幂](https://www.cnblogs.com/laipimei/p/11316140.html)]

[[Array List和Linked List实现分析](https://www.cnblogs.com/fenjyang/p/11480944.html)]

[[Java 迭代接口：Iterator、ListIterator 和 Spliterator](https://www.cnblogs.com/liululee/p/11416038.html)]

[Comparable接口的实现和使用](https://www.cnblogs.com/wl-centrinc/p/11872758.html)

#### [1.口气带你踩完五个 List 的大坑，真的是处处坑啊！](https://www.cnblogs.com/goodAndyxublog/p/12758755.html)
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
### 4.Java IO
```markdown
Java 的 I/O 大概可以分成以下几类：
    磁盘操作：File
    字节操作：InputStream 和 OutputStream
    字符操作：Reader 和 Writer
    对象操作：Serializable
    网络操作：Socket
    新的输入/输出：NIO
```
#### 装饰者模式
```markdown
Java I/O 使用了装饰者模式来实现。以 InputStream 为例，
    InputStream是抽象组件；
    FileInputStream是InputStream的子类，属于具体组件，提供了字节流的输入操作；
    FilterInputStream属于抽象装饰者，装饰者用于装饰组件，为组件提供额外的功能。例如BufferedInputStream为FileInputStream提供缓存的功能。
```


