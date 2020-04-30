###Java高级编程
####1.Java多线程
[多线程系列](https://www.cnblogs.com/weechang/tag/%E5%A4%9A%E7%BA%BF%E7%A8%8B/) 
[==>> Java并发编程](https://www.cnblogs.com/hongshaodian/category/1663273.html)
>> 实现多线程的方式
```markdown
1.继承Thread类 Thread类是Runnable接口的子类
2.实现runnable接口，避免了单继承局限，更好的描述了数据共享
3.实现Callable接口
实现 Runnable 和 Callable 接口的类只能当做一个可以在线程中运行的任务，不是真正意义上的线程，因此最后还需要通过Thread来调用。
可以说任务是通过线程驱动从而执行的。调用Thread类的start()方法，不仅仅是要启动多线程的执行代码，而且要根据不同的操作系统去进行资源的分配
```
>> 线程的五种状态: 新建，可运行，阻塞，无限期等待，限期等待，死亡
####2.线程常见操作方法
>> 多线程的常用操作方法
```markdown
构造方法： public Thread(Runnable target,String name);
设置名字： public final void setName(String name);
取得名字： public final String getName();
取得当前线程对象： public static Tread currentThread();
线程休眠：public static void sleep(long millis) throws InterruptedException 
设置优先级：public final void setPriority(int newPriority);
取得优先级: public final int getPriority();
最高优先级： public static final int MAX_PRIORITY,10;
中等优先级： public static final int NORM_PRIORITY,5;
最低优先级： public static final int MIN_RIORITY,1;
```
[JAVA线程及简单同步实现的原理解析](https://www.cnblogs.com/lijizhi/p/10775748.html)
####3.线程的同步和死锁
[如何优雅地中止线程？](https://www.cnblogs.com/wupeixuan/p/12578851.html)
```markdown
线程的同步和死锁 （多线程访问同一个资源时需要考虑）
                    线程同步使用synchronized关键字
                        同步代码块
                        同步方法
sleep()和wait()的区别
            sleep是Thread类定义的方法，wait()是Object类定义的方法
            sleep()可以设置休眠时间，时间一到就自动重启
            而wait()需要等待notify()进行唤醒
```
####4.综合实战：生产者和消费者
####5.Java基础类库
        *String与StringBuffer相互转换--
         * 利用StringBuffer类的构造方法 public StringBuffer(String str)
         * 利用append()方法，public StringBuffer append(String str);
         * StringBuffer类变成String类：toString()方法
         面试题：String StringBuffer StringBuilder的区别
         String类一旦声明则不可改变，而StringBuffer与StringBuilder声明的内容可以改变
         StringBuilder类提供的方法是同步方法（final修饰），属于线程安全的操作
         StringBuffer类中的方法都是异步方法，属于线程不安全的操作。
         Runtime
             public long totalMemory()返回所有的可用内存空间
             * public long maxMemory() 返回最大可用的内存空间
             * public long freeMemory() 返回空闲内存空间
         System类
            * 数组拷贝：public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
             * 取得当前的系统时间：public static long currentTimeMillis()
             * @author PBJ
             *finalize()方法：protected void finalize() throws Throwable
             *在对象回收时就算抛出了如何异常，也不会影响到整个程序的正常执行
         面试题：final finally finalize的区别
            final：关键字，定义不能被继承的类，、不能被覆写的方法，常量
            finally：关键字，异常的统一出口
            finalize:方法，Object提供的方法，指的是对象回收前的收尾方法，就算抛出了如何异常，也不会影响到整个程序的正常执行
         对象克隆： protected Object clone() throws CloneNotSupportException
####6.数字操作类
            [Random在高并发下的缺陷以及JUC对其的优化](https://www.cnblogs.com/CodeBear/p/10748407.html)
            四舍五入: public static long rund(double a)
            随机数：Random rend = new Random(); rend.nextInt(100);
            大整数操作类：BigInteger 构造方法：public BigInteger(String str);
            大浮点数：BigDecimal 构造方法：public BigDecimal(String str)
                                构造方法：public BigDecimal(double val)
####7.日期操作类
            日期处理类Date
                有参构造：public Date(long date);接收long型数据 public long getTime();转换为long型
            日期格式化 SimpleDateFormat
             * 构造方法：public SimpleDateFormat(String patten)
             * 将Date转换为String :public final String frmat(Date date)
             * 将String转换为Date:public Date parse(String source) throws ParseException
             Calender主要是进行日期计算
####8.比较器
            Arrays类
             * 二分查找法：public static int binarySearch(数据类型[]a,数据类型 key)
             * 数组比较 public static boolean equals(数据类型[]a,数据类型 []b)
             * 填充数组 public static void fill(数据类型 []a,数据类型 val)
             * 将数组变为字符串输出：public static String toString(数据类型[]a)
             比较器Comparable
              * 对象数组排序：public static void sort(Object[] a)
              比较器：Comparator
           面试题：Compare和Comparator的区别
            https://www.cnblogs.com/tanshaoshenghao/p/10753022.html
            如果对象数组要进行排序那么必须设置排序规则，可以使用Comparable或者Comparator接口实现
            java.lang.Comparable是在类定义的时候实现好的接口，这样本类的对象数组就可以进行排序
                public int compareTo()方法
            java.util.Comparator是一个专门定义一个指定类的比较规则，属于挽救的比较操作，里面有两个方法
                public int compare() , public boolean equals()
####9.正则表达式
        正则表达式标记
            所有的正则可以使用的标记都在 java.util.regex.Pattern面定义
            1、单个字符（数量：1）
            字符：表示由一位字符所组成
            \\：表示转义字符“＼”
            \t：表示一个“\t”符号
            \n：匹配换行（\n）符号
            2、字符集（数量：1）
            [abc]：表示可能是字符a或者是字符b或者是字符c中的任意一位：
            [^Aabc]：表示不是a、b、c中的任意一位
            [a－z]：所有的小写字母
            [a－zA－Z]：表示任意的一位字母，不区分大小写：
            [0－9]：表示任意的一位数字
            3、简化的字符集表达式（数量：1）
            .:表示任意的一位字符；
            \d：等价于“[0-9]”属手筒化写法
            \D：等价于“［^0－9］”，属于简化写法
            \s：表示任意的空白字符，例如：“\t”、“\n
            \S：表示任意的非空白字符；
            \w：等价于“［a－zA－Z0－9］”，表示由任意的字母、数字、所组成
            \W：等价于“[^a－A－Z0－9]”，表示不是由任意的字母、数字、所组成
            4、边界匹配（不要在java中使用，在 Javascript里使用）：
            ^：正则的开始
            $：正则的结束
            5、数量表达
            正则？：表示此正则可以出现0次或1次
            正叫+:表示此正则可以出现次士次以上
            正则＊：表示此正则可以出现0次、1次或多次；
            正则{n}：表示此正则正好出现n次；
            正则{n,}：表示此正则出现n次以上（包含n次）
            正则{n,m}：表示此正则出现n～m次
            6、逻辑运算：
            正则1正则2：正则1判断完成之后继续判断正则2
            正则1|正则2：正则1或者是正则2有一组满足即可：
            （正则）：将多个正则作为一组，可以为这一组单独设置出现的次数。
            String类对于正则的支持
            java.util.regex包支持
####10.反射机制
>> 利用对象找到对象的出处
[java反射机制精讲](https://www.cnblogs.com/swzx-1213/p/12597159.html)
```markdown
        Class类对象实例化
            取得class对象：public final Class<?> getClass();//获取对象的类的完整名称
            调用Object类中的getClass()方法
            实例化Class类对象：public static Class<?> forName(String className) throw ClassNotFoundException
        反射实例化对象
            实例化对象方法：public T new Instance() throws InstantiationException,IllgalAccessException
        反射调用构造
        反射调用方法
        反射调用成员
[如何提高使用Java反射的效率？](https://www.cnblogs.com/coding-night/p/10772631.html)
最后总结一下，为了更好的使用反射，我们应该在项目启动的时候将反射所需要的相关配置及数据加载进内存中，
在运行阶段都从缓存中取这些元数据进行反射操作。大家也不用惧怕反射，虚拟机在不断的优化，只要我们方法用的对，
它并没有”传闻“中的那么慢，当我们对性能有极致追求的时候，可以考虑通过三方包，直接对字节码进行操作。
   反射的常见用法
        1.反射根据类文件看到指定类的属性，比如属性的修饰符，属性的类型，属性名称
        2.查看方法的返回类型，参数，和名字
        3.通过forName和newInstance方法加载类
        4.通过反射机制调用类的方法
反射的优点：
    可扩展性 ：应用程序可以利用全限定名创建可扩展对象的实例，来使用来自外部的用户自定义类。
    类浏览器和可视化开发环境 ：一个类浏览器需要可以枚举类的成员。可视化开发环境（如IDE）可以从利用反射中可用的类型信息中受益，以帮助程序员编写正确的代码。
    调试器和测试工具:调试器需要能够检查一个类里的私有成员。测试工具可以利用反射来自动地调用类里定义的可被发现的API定义，以确保一组测试中有较高的代码覆盖率。
反射的缺点：
    尽管反射非常强大，但也不能滥用。如果一个功能可以不用反射完成，那么最好就不用。在我们使用反射技术时，下面几条内容应该牢记于心。
    性能开销 ：反射涉及了动态类型的解析，所以 JVM 无法对这些代码进行优化。因此，反射操作的效率要比那些
    非反射操作低得多。我们应该避免在经常被执行的代码或对性能要求很高的程序中使用反射。
    安全限制 ：使用反射技术要求程序必须在一个没有安全限制的环境中运行。如果一个程序必须在有安全限制的环境中运行，如 Applet，那么这就是个问题了。
    内部暴露 ：由于反射允许代码执行一些在正常情况下不被允许的操作（比如访问私有的属性和方法），所以使
    用反射可能会导致意料之外的副作用，这可能导致代码功能失调并破坏可移植性。反射代码破坏了抽象性，因此当平台发生改变的时候，代码的行为就有可能也随着变化。
```
####11.国际化
####12.文件操作类   
```markdown
[](https://www.cnblogs.com/czwbig/p/10007201.html)
        Java.io包中包括五个核心类:File InputStream OutputStream Reader Writer  一个核心接口:Serializable
            File类 [](https://www.cnblogs.com/czwbig/p/10011718.html)
                检查文件或目录是否存在。
                如果目录不存在，创建一个目录。
                读取文件的长度。
                重命名或移动文件。
                删除文件。
                检查路径是文件还是目录。
                读取目录中的文件列表
```
####13.字节流和字符流
```markdown
字节流（JDK1.0）：InputStream OutputStream[](https://www.cnblogs.com/czwbig/p/10007289.html)
                InputStream：数据读操作 OutputStream：数据写操作
            字符流（JDK1.1）：Reader：字符输出流 Writer：字符输入流
            字节流和字符流是区别:字节流直接与终端进行交互，字符流需要将数据仅供缓冲器处理后来才输出
             * OutputStream即使没有关闭输出流，也可以正常输出，但是Writer输出流没有关闭，代表缓冲区中的内容不会清空，
             * 不能输出。可以使用flush()方法强制清空缓冲区
             * 处理中文的时候，请优先考虑字符流，没有中文问题，建议使用字节流
             转换流：InputStreamReader OutputStreamWriter
                //将OutputStream类对象传递给OutputStreamWriter类的构造方法，然后向上转型为Writer
                		Writer out = new OutputStreamWriter(output);
             内存流
             	 * 字节内存流： ByteArrayInputStream BytearrayOutputStream
             	 * 字符内存流：CharArrayReader CharArrayWriter
```
####14.IO辅助概念
    
####15.打印流,缓冲区流，扫描流和序列化
##### 15.1 打印流
```markdown
打印流：PrintStream（打印字节流） PrintWriter(打印字符流) 弥补OutPutStream输出类型不同的影响
```
##### 15.2 缓冲区流
```markdown
缓冲区流
* 缓冲输入流：
* 字符缓冲区流：BufferedReader，BufferedWriter
* 字节缓冲区流：BufferedInputStream,BufferedOutputStream
```
##### 15.3 扫描流
```markdown
扫描流：Scanner InputStream的功能不足
```
##### 15.4 序列化
[Java程序员必备：序列化全方位解析](https://www.cnblogs.com/jay-huaxiao/p/12730437.html)-- 
[java序列化，看这篇就够了](https://www.cnblogs.com/9dragon/p/10901448.html) --
[序列化/反序列化 -写入任意对象与读出的相关问题](https://www.cnblogs.com/huxiaobai/p/11607989.html)
###### 15.4.1 序列化的几个问题？
```markdown
1、什么是序列化？ 
将某个对象保存到磁盘上或者通过网络传输，那么这个类应该实现Serializable接口或者Externalizable接口之一。
序列化：把Java对象转换为字节序列的过程 -- 反序列：把字节序列恢复为Java对象的过程
对象序列化：将保存在内存中的对象数据转换为二进制数据流进行操作传输的操作，一定要实现java.io.Serializable接口
不是所有的类都需要被序列化，只有需要被传输的对象所在的的类才需要进行序列化。
2、为什么需要序列化？
Java对象是运行在JVM的堆内存中的，如果想在JVM停止后，把这些对象保存到磁盘或者通过网络传输到另一远程机器，
怎么办呢？磁盘这些硬件可不认识Java对象，它们只认识二进制这些机器语言，所以我们就要把这些对象转化为字节数组，这个过程就是序列化啦~
3、序列化的用途
1） 序列化机制可以让对象地保存到硬盘上，减轻内存压力的同时，也起了持久化的作用；
2） 序列化机制让Java对象在网络传输不再是天方夜谭。
```
###### 15.4.2 Java序列化常用API
```markdown
java.io.ObjectOutputStream 使用ObjectOutputStream类的writeObject方法，实现序列化
java.io.ObjectInputStream 使用ObjectInputStream类的readObject方法，实现反序列化
java.io.Serializable 
java.io.Externalizable
```
###### 15.4.3 序列化的使用
###### 15.4.4 日常开发序列化的一些注意点
```markdown
1.static静态变量和transient 修饰的字段是不会被序列化的
静态（static）成员变量是属于类级别的，而序列化是针对对象的~所以不能序列化哦。transient关键字，它可以阻止修饰的字段被序列化
到文件中，在被反序列化后，transient 字段的值被设为初始值，比如int型的值会被设置为 0，对象型初始值会被设置为null。
2.serialVersionUID问题
JAVA序列化的机制是通过判断类的serialVersionUID来验证版本是否一致的。
阿里开发手册，强制要求序列化类新增属性时，不能修改serialVersionUID字段~
3.如果某个序列化类的成员变量是对象类型，则该对象类型的类必须实现序列化
如果一个可序列化的类的成员不是基本类型，也不是String类型，那这个引用类型也必须是可序列化的；否则，会导致此类不能序列化。
4.子类实现了序列化，父类没有实现序列化，父类中的字段丢失问题
子类实现了Serializable，父类没有实现Serializable接口的话，父类不会被序列化。
```
###### 15.4.5 序列化常见面试题
```markdown
1.序列化的底层是怎么实现的？
2.序列化时，如何让某些成员不要序列化？ 可以用transient关键字修饰
3.在 Java 中,Serializable 和 Externalizable 有什么区别
Externalizable继承了Serializable，给我们提供 writeExternal() 和 readExternal() 方法, 让我们可以控制
Java的序列化机制, 不依赖于Java的默认序列化。正确实现 Externalizable 接口可以显著提高应用程序的性能。
4.serialVersionUID有什么用？
JAVA序列化的机制是通过判断类的serialVersionUID来验证版本是否一致的。在进行反序列化时，JVM会把传来的字节流中的serialVersionUID
和本地相应实体类的serialVersionUID进行比较，如果相同，反序列化成功，如果不相同，就抛出InvalidClassException异常。
5.是否可以自定义序列化过程, 或者是否可以覆盖 Java 中的默认序列化过程？
可以的
6.在 Java 序列化期间,哪些变量未序列化？
static静态变量和transient 修饰的字段是不会被序列化的。静态（static）成员变量是属于类级别的，
而序列化是针对对象的。transient关键字修字段饰，可以阻止该字段被序列化到文件中。
```
####16.System类对IO的支持
  
####17.IO高级应用
  
####18.网络编程
  
####19.类集简介 
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
####20.JDBC简介
  
#### 21.Java编程
```
java面向对象编程：继承性，派生性，多态性
Java面向切面编程 ：Java静态接口，Java动态代理，字节码提升
Java面向元数据编程 ：泛型，反射，注解
Java面向函数编程：函数式接口，函数式默认方法，函数式方法引用
Java面向模块编程    
```

#### Java的自动装箱和拆箱
[Java的自动装箱和拆箱](cnblogs.com/lewisyoung/p/12769084.html)
```markdown
自动装箱就是讲基础类型转换成包装类型，自动拆箱就是讲包装类型转换成基础类型。
在Integer i1 = i0;时，系统执行的是 Integer.valueOf 的方法，在int i2 = i1;时，系统执行了 Integer.intValue 方法
在 equals 的时候，int 参数先被自动装箱为 Integer 类型，然后进行比较。在 == 比较时，Integer 类型自动拆箱。
```

