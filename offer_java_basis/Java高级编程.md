###Java高级编程
####1.Java多线程
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
```markdown
利用对象找到对象的出处,
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
        最后总结一下，为了更好的使用反射，我们应该在项目启动的时候将反射所需要的相关配置及数据加载进内存中，在运行阶段都从缓存中
        取这些元数据进行反射操作。大家也不用惧怕反射，虚拟机在不断的优化，只要我们方法用的对，它并没有”传闻“中的那么慢，
        当我们对性能有极致追求的时候，可以考虑通过三方包，直接对字节码进行操作。
   反射的常见用法
        1.反射根据类文件看到指定类的属性，比如属性的修饰符，属性的类型，属性名称
        2.查看方法的返回类型，参数，和名字
        3.通过forName和newInstance方法加载类
        4.通过反射机制调用类的方法
```
  ####11.国际化
  #####12.文件操作类   
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
  ####13.字节流和字符流
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
  ####14.IO辅助概念
    
  ####15.打印流
             打印流：PrintStream（打印字节流） PrintWriter(打印字符流) 弥补OutPutStream输出类型不同的影响
             缓冲区流
             	 * 缓冲输入流：
             	 * 字符缓冲区流：BufferedReader，BufferedWriter
             	 * 字节缓冲区流：BufferedInputStream,BufferedOutputStream
             扫描流：Scanner InputStream的功能不足
             对象序列化：将保存在内存中的对象数据转换为二进制数据流进行操作传输的操作，一定要实现java.io.Serializable接口
                不是所有的类都需要被序列化，只有需要被传输的对象所在的的类才需要进行序列化
                https://www.cnblogs.com/chenbenbuyi/p/10741195.html
                https://www.cnblogs.com/9dragon/p/10901448.html
  
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
  
#### Java编程
```
    java面向对象编程：继承性，派生性，多态性
    Java面向切面编程 ：Java静态接口，Java动态代理，字节码提升
    Java面向元数据编程 ：泛型，反射，注解
    Java面向函数编程：函数式接口，函数式默认方法，函数式方法引用
    Java面向模块编程
```
