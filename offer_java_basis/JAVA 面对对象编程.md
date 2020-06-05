##Java基础：
###Java语言的特点：
```markdown
        足够简单;
        面对对象;
        为数不多的多线程语言;
        提供自动的垃圾回收机制;
        简单的引用来完成内存关系的匹配，避免了指针。
        任意平台的移植性--关键技术JVM。           
        面向过程：指的是针对某一个问题单独提出解决方案以及代码开发
        面向对象：以一种组件化的形式进行代码的设计，这样开发出来的代码有一个最大的好处就是可以重用。
        面向对象三大特性：        继承，封装，多态，
```
####*面试题：Java的两种声明方式 *
```markdown
        public class 类名称{} ：方法和类名称一致，一个*.java文件中只能有一个public class定义。
        class 类名称{} ：文件名称和类名称不一致，但是生成的*.class文件和定义的class名称一致，在一个*。java中可以定义多个class。
        C:\Users\Administrator>java -version
        java version "1.8.0_191"
        Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
        Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)
```     
####几个特殊的Java关键字：volatile native assert（JDK1.4） transient 
```markdown
    assert（JDK1.4）：断言，程序执行到某一行代码时一定是预期的结果
```    
###java数据类型 和运算符  
   ####*面试题：&和&&的区别， | 和|| 的区别？*
```markdown
    &和| 是位运算符 ，&&和||是逻辑运算符，不能用于位运算。
    &：所有的条件都要判断， && 当前一个判断为false时后面不用判断直接返回false
    |：所有的条件都要判断， || 当前一个判断为true时，后面一个不用判断直接返回true
      public static void main(String[] args) {
                  System.out.println("==========Method start====================");
                  int a = 10;
                  if(a>1&(a!=0)){
                      System.out.println("&：所有的条件都要判断");
                  }
                  System.out.println("====================");
                  if (a<1&&(a/0==0)){
                      System.out.println("&& 当前一个判断为false时后面不用判断直接返回false");
                  }
                  System.out.println("====================");
                  if(a>1|(a!=0)){
                      System.out.println("|：所有的条件都要判断");
                  }
                  System.out.println("====================");
                  if(a>1||(a/0==0)){
                      System.out.println("|| 当前一个判断为true时，后面一个不用判断直接返回true");
                  }
                  System.out.println("==========Method end======================");
                  System.out.println(1&2);
          //        System.out.println(1&&2); 不成立
                  System.out.println(1|2);
          //        System.out.println(1||2); 不成立
              }  
```
### Java 方法：方法定义，方法重载，方法递归调用
```markdown
方法重载是指：方法名称相同，参数的类型及个数不同，同时尽量保证返回值类型相同。
```
###1.类与对象 :
```markdown
       对象的定义两种方法(实例化) 1. A a = new A ();  2. A a = null; a = new A();
       堆内存：保存每一个对象的属性内容，需要new来开辟
       栈内存：保存的是一块堆内存的地址
       引用传递：一块堆内存可以同时被多个栈内存所指向，但是一块栈内存只能保存一个堆内存空间的地址
```   
###2.深入分析类与对象：
```markdown
       封装性 ： private声明，外部使用行为setter和getter方法  
       构造方法和普通方法的区别：构造方法是在实例化新对象（new）的时候只调用一次
                               普通方法是实例化对象之后可以随时调用
       匿名对象：没有栈指向的对象技术匿名对象，只能使用一次
``` 
###3.数组 
```markdown
        两种定义方法：数据类型 数组名称 []  = new 数据类型[长度];
        初始化：数据类型 数组名称 []  = new 数据类型[长度]{值。。。。。};
        数组有关的操作方法：
           数组拷贝： System.arraycopy(源数组名称，源数组拷贝开始索引,目标数组名称,目标数组拷贝开始索引,长度);
           数组排序： java.util.Arrays.sort(数组名称);
        对象数组：Object object[] = new Object[3];
        二维数组转置
```
###4.5.String
[为什么StringBuilder是线程不安全的？StringBuffer是线程安全的？](https://www.cnblogs.com/Jacian/p/11553320.html)
```markdown
== 是属于数值相等比较，在String类型中比较的是内存地址，equals()方法收集专门进行字符串内容的比较
        String实例化的两种方法区别
            直接赋值：String str = "字符串"; ；只开辟一块堆内存空间，并且不会产生空间垃圾，并且自动保存在对象池中重复使用
            构造方法：String str = new String ("字符串");:开辟两块内存空间，其中一块会变为垃圾，
            并且不会自动入池，但是用户可以使用intern()方法手工入池
        	String类型字符串内容不可变
        	    String类常用方法：字符与字符串
            		 * public String(char[] value)  :将字符数组变为String类对象
            		 * public String(char[] value,int offset,int count) :将部分字符数组变为String
            		 * public char charAt(int index)  :返回指定索引的字符信息
            		 * public char [] toCharArray()   :将字符串以字符数组的形式返回
            	String类常用方法： 字节与字符串
            		 * public String (byte[] bytes)  :将全部字节数组变为字符串
            		 * public String (byte [] bytes,int offset,int count) :将部分字节数组变为String
            		 * public byte[] getBytes() :将字符串变为字节数组
            		 * public byte[] getBytesz(String charsetname) throws UnsupportedEncodingException :进行编码转换
            	String类常用方法：字符串比较
            		 * public boolean equals(String anObject) :进行相等判断，区分大小写
            		 * public boolean equalsIgnoreCase(String anotherString) ：进行相等比较，不区分大小写
            		 * public int compareTo(String anotherString) 判断两个字符串的对象：=0,>0,<0
            	String类常用方法：字符串查找
            		 * public boolean contains(String s) :判断指定父内容是否存在
            		 * public int indexOf(Srting str) :由前向后查找指定字符串的位置
            		 * public int indexOf(String str ,int fromIndex) :从指定位置开始前向后查找指定字符串的位置
            		 * public int lastIndexOf(String str) :由后向前查找指定字符串的位置
            		 * public int lastIndexOf(String str,int fromIndex) :从指定位置开始后向前查找指定字符串的位置
            		 * public boolean startsWith(String prefix) :判断是否由指定字符串开头
            		 * public boolean startsWith(String prefix,int offset) :判断成指定位置开始是否由指定字符串开头
            		 * public boolean endsWith(String suffix) :判断是否由指定字符串结尾
            	String类常用方法：字符串替换
            		 * public String replaceAll(String regex,String replacement) :用新的字符串替换旧的内容
            		 * public String replaceFirst(String regex,String replacement) :以后首个满足条件的内容
                String 类常用方法：字符串截取
            		 * public String substring (int brginIndex) :从指定索引截取到结尾
            		 * public String substring (int beginIndex,int endIndex) :截取部分子字符串的数据
                String 类常用方法： 字符串拆分
            		 * public String [] split(String regex) :按照指定字符串进行全部拆分
            		 * public String [] split(String repex,int limit) :按照指定字符串进行部分拆分，长度由limit决定
                String类其他操作
            		 * public String concat(String str)  :字符串连接，与+类似
            		 * public String toLowerCase() :转小写
            		 * public String toUpperCase() :转大写
            		 * public String trim() :去掉字符串左右两边的空格，保留中间的空格
            		 * public int length() : 去掉字符串长度
            		 * public String intern() :数据入池
            		 * public boolean isEmpty() :判断字符串是否为空        
```   
### 6.this
[技术大佬：我去，你竟然还不会用 this 关键字](https://www.cnblogs.com/qing-gee/p/12950325.html)
```markdown
this关键字可以实现类属性调用，类方法的调用，当前对象的调用。
使用this()调用构造方法时只能放在构造方法首行，进行构造方法相互调用时至少保留一个构造方法没有使用this()调用其他构造方法。
```
#### this的使用
```markdown
01、消除字段歧义
在使用有参构造函数的时候，如果参数名和成员变量的名字相同，就需要使用this关键字消除歧义：this.age是指成员变量,age是指构造方法的参数。
02、引用类的其他构造方法
当一个类的构造方法有多个，并且它们之间有交集的话，就可以使用 this 关键字来调用不同的构造方法，从而减少代码量。
需要注意的是，this() 必须是构造方法中的第一条语句，否则就会报错。
03、作为参数传递
04、链式调用
05、在内部类中访问外部类对象
06、关于this与super
    1）super 关键字可用于访问父类的构造方法
    2）super 关键字可以访问父类的变量
    3）当方法发生重写时，super关键字可以访问父类的同名方法
```
###7.引用传递 :reference
```markdown
        引用传递的核心要义：同一块堆内存空间可以被不同的栈内存所指向，不同的栈内存可以对同一个堆内存进行内容的修改
        String类对象的内容一旦声明则不可改变，对象内容的改变依靠的是引用地址的改变
```    
###8.综合实战
``
``
###9.对象比较
```markdown
对象比较一定是某一个类之间定义的功能
        对象比较时一定要判断是否为null，地址是否相同，属性是否相同
```
###10.static
```markdown
    static定义属性
            一旦属性定义上使用了static之后，只要一个对象修改了属性的内容，那么所有的对象的static属性的内容都将一起修改，
            也就是说static属性变成了一个公共属性，是全局数据区。
            （static声明的属性与普通属性的最大区别是保存的内存区域的不同，还有就是static属性在没有实例化的情况下可以使用）
    static定义方法
            静态方法不需要创建对象就可以调用。
            static方法不能直接访问非static属性或者方法（必须要产生对象后才能调用），非static方法可以直接访问static属性或者方法
```
###11.代码块
```markdown
        普通代码块，构造块，静态块，同步代码块
        普通代码块：防止编写代码过多可能产生变量重名
        构造块：构造块优于构造方法执行，并且可以重复执行多次
        静态块：静态块优于构造块执行，但是不管多少次实例化，只执行一次。
        同步代码块：多线程的时候使用
```
###12.内部类
[InnerDemo1:Java内部类的四种分类以及作用](https://www.cnblogs.com/hackerstd/p/12547503.html)
```markdown
        内部类的定义：在一个类的内部定义了其他的类，内部类的最大优点是：可以方便的访问外部类的私有操作
        反之，外部类也可以通过内部类对象访问内部类的私有属性，
        使用static定义内部类，一定不可能受到外部类的实例化控制，相当于一个外部类。内部类可以在方法中定义
```
###13.链表
**面试重点**
###14.继承 : 继承性最大的特性是解决代码的重用问题
        继承的限制：
         * 1.java不允许多重继承，但是允许多层次继承
         * class A{} class B extends A{} class C extends A{}
         * 2.子类继承父类的时候，严格需要继承父类中的所有操作，但是对于所有的私有操作属于隐式继承。而所有的非私有操作属于显式继承
         * 3.在子类对象构造之前，一定会默认调用父类的构造，以保证父类对象先实例化，然后实例化子类对象。
         this和super的区别
         this()和super()不能够同时出现。子类对象构造调用之前，一定会默认调用父类的构造，以保证父类对象先实例化，然后实例化子类对象
###15.覆写
        需要考虑权限问题：被子类覆写的方法不能拥有比父类更加严格的访问控制权限
         * public > default > private,大多数的方法使用public声明
         * 当父类使用private声明时，对于子类不可见，无法调用，可以使用super.方法()来进行访问
         * this.方法() 和super.方法()的区别，
         * this.方法()会首先查找本类中是否查找可以调用的方法名称，操作则调用，
         * 不存在则查找父类中是否查找，查找则调用，不存在则出现编译出错
         * super.方法()：直接查找父类中是否查找，查找则调用，不存在则出现编译出错
         * 重载（Overloading）和覆写（Overrid）的区别
         * Overloading发生在一个类中、方法名称相同，方法的类型和参数个数不同，没有权限的限制
         * Override发生在继承关系中，方法名称，参数类型，个数相同，方法返回值相同，子类不能有比父类更加严格的权限限制
###16.综合实战：数组操作
  
###17.final 多态性
        * final关键字，final定义类，方法，属性的特点
         * 1.使用final定义的类不能有子类
         * 2.使用final定义的方法不能被子类所覆写
         * 3.使用final定义的变量就成为了常量，常量必须在定义的时候设置好内容，不能够修改
         * 多态性：
          * 方法的多态性：
          * 	方法的重载：
          * 	方法的覆写：
          * 对象的多态性：
          * 	向上转型（自动完成）：父类 父类对象 = 子类实例；  //为了参数类型的统一
          * 	向下转型（强制完成）：子类 子类对象 = （子类） 父类实例；//目的是调用子类的特殊方法
###18.抽象类
         * 抽象类必须被继承，抽象类的子类必须覆写抽象类中的全部抽象方法
         * 抽象类的对象实例化需要依靠子类完成，采用向上转型的方法处理
         单继承局限
  ####19.接口
        接口使用原则：
         * 接口必须要有子类，一个子类可以使用implements实现多个接口
         * 接口中子类，必须覆写所有的接口中的抽象方法
         * 接口的对象可以利用子类对象 的向上转型进行实例化操作
         * Java里面，接口的主要功能是解决单继承的局限问题
###19.接口
        /*
         * 接口使用原则：
         * 接口必须要有子类，一个子类可以使用implements实现多个接口
         * 接口中子类，必须覆写所有的接口中的抽象方法
         * 接口的对象可以利用子类对象 的向上转型进行实例化操作
         * Java里面，接口的主要功能是解决单继承的局限问题
         */
###20.Object
        Object类是所有类的父类，要定义一个无参构造，子类在对象实例化的时候默认调用父类的无参构造
###21.匿名内部类，包装类
        匿名内部类是在接口和抽象类的基础上的发展，匿名内部类的最大好处是帮助用户减少类的定义
                 * 基本数据类型包装类
        		 * 对象型包装类：character , Boolean
        		 * 数值类型包装类：Byte，Short, Integer, Folat ,Double ,Long, 
        		 * 操作方法：intValue(),doubleValue(),folatValue(),byteValue(),shortValue(),longValue()
        		 /*
                 		  * 字符串与基本数据类型
                 		  * Integer类： public static int parseInt(String s)
                 		  * Double类：public static double parseDouble(String s)
                 		  * Boolean类: public static boolean parseBoolean(String s)
                 		  */
###22.包
```
```
###23.访问权限控制 ：private default protected public
    类名称每一个单词的首字母大写，其余字母小写：StudentInfo
    属性名称第一个单词字小写，其余每个单词的首字母大写：studentName
    方法名称第一个单词字小写，其余每个单词的首字母大写：getName()
    常量名称使用大写字母： MSG
    包名称使用小写字母：cn.cn.cn
###24.异常
```markdown
Throwable可以用来表示任何可以作为异常抛出的类，分为两种：Error和Exception。其中Error用来表示JVM无法处理的错误，Exception分为两种：
    受检异常 ：需要用 try...catch... 语句捕获并进行处理，并且可以从异常中恢复；
    非受检异常 ：是程序运行时错误，例如除 0 会引发 Arithmetic Exception，此时程序崩溃并且无法恢复。
```
###25.类图
###27.Java5新特性
```markdown
可变参数 ： 实现任意多个整数数据相加操作,(相同数据类型，多个参数) 
private static int add(int ... data){  }
foreach循环： 每一次循环实际上都表示数组的角标增长，会取得一个数组的内容，并且将其设置给x 
for(int x : data) {  }  
静态导入：import static cn.pbj.utils.* 导入所有的静态方法
```
###28.泛型
```markdown
类在定义的时候，可以使用一个标记，此标记就表示类中属性或者方法参数的类型标记，使用的时候再动态的设置类型
        泛型解决的是想下转型所带来的安全隐患，其核心就是在扫描类或接口时不设置参数或属性的内容
        通配符:？ 可以接受任意的泛型类型，只能够取出，不能修改
```
[用了这么多年的 Java 泛型，你对它到底有多了解？](https://www.cnblogs.com/goodAndyxublog/p/12934938.html)
```markdown
Java 泛型实现方式：
    1.Java 采用类型擦除（Type erasure generics）的方式实现泛型。
    2.用大白话讲就是这个泛型只存在源码中，编译器将源码编译成字节码之时，就会把泛型『擦除』，所以字节码中并不存在泛型。
    3.并不是每一个泛型参数被擦除类型后都会变成Object类，如果泛型类型为T extends String这种方式，最终泛型擦除之后将会变成String。
类型擦除带来的缺陷：
    1.不支持基本数据类型。泛型参数被擦除之后，强制变成了Object类型。
    2.Java类型擦除式泛型实现方式无论使用效果与运行效率全面落后于C#的具现化式泛型。
    3.运行期间无法获取泛型实际类型
Java 泛型发展史：
    1.Java 泛型最早是在 JDK5 的时候才被引入，但是泛型思想最早来自来自 C++ 模板（template）。
```
###29.枚举
```markdown
enum是一个关键字，而Enum是一个抽象类
使用enum定义枚举就相当于一个类继承了Enum这个抽象类 
枚举之中定义的构造方法不能使用public声明，如果没有无参构造，请手工调用构造传递参数
枚举对象必须放在首行，随后才可以定义属性，构造和普通方法。
枚举的遍历 A[] a = A.values; [聊一聊Java的枚举enum](https://www.cnblogs.com/LiaHon/p/11283026.html)
```
###30.Annotation
```markdown
@Override 准确覆写 @Deprecated 过期声明  @SuppressWarnings压制警告
```
### 31.Java8新特性
#### [有点干货 | Jdk1.8新特性实战篇(41个案例)](https://www.cnblogs.com/xiaofuge/p/12963331.html)
[【Java8新特性】接口中的默认方法和静态方法，你都掌握了吗？](https://www.cnblogs.com/binghe001/p/13022207.html)
```markdown
接口增强，使用default与static在接口中定义方法
    1、允许在接口里面定义默认方法
        在Java8中，默认方法具有“类优先”的原则。
        若一个接口中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时，遵循如下的原则。
        1).选择父类中的方法。如果一个父类提供了具体的实现，那么接口中具有相同名称和参数的默认方法会被忽略。
        2).接口冲突。如果一个父接口提供一个默认方法，而另一个接口也提供了一个具有相同名称和参数列表的方法（不管方法是否是默认方法）， 那么必须覆盖该方法来解决冲突。
    2、允许在接口里面定义static方法，直接调用接口static方法使用。
    3、Java允许内部类访问方法参数的时候可以不加上final关键字。           
[Lambda表达式详解](https://www.cnblogs.com/haixiang/p/11029639.html)
lambda表达式有它自己的优点：（1）简洁，（2）易并行计算。尤其适用于遍历结果，循环计算数值或者赋值的时候非常方便。
   缺点:（1）若不用并行计算，很多时候计算速度没有比传统的 for 循环快。
        （2）不容易使用debug模式调试。
        （3）在 lambda 语句中直接强制类型转换不方便。
        （4）不可以在foreach中修改foreach外面的值。    
   Lamda表达式的三种方式：（参数） ->单行语句；  （参数） -> {单行语句}； （参数） ->表达式；
        避免了匿名内部类定义过多的无用操作        
Java8新特性 	方法引用
           引用静态方法： 类方法 ：：static方法名称
           引用某一个对象的方法： 实例化对象 ：：普通方法；
           引用特定类型的方法： 特定类：：普通方法；
           引用构造方法： 类名称 ：：new；
内建函数式接口
            //功能性接口(Function):public interface Function<T,R>{public R apply(T t);}
            		//此接口需要接受一个参数，并且返回一个处理结果
            		Function<String,Boolean> fun = "he3**" :: startsWith;
            		System.out.println(fun.apply("#$$")); //false 查看开头是否一样
            		//消费型接口(Consumer):public interface Consumer<T> {public void accept(T t);}
            		//此接口只是负责接受数据，并且不返回处理结果
            		Consumer<String> cons = new MyDemo() :: print;
            		//Consumer<String> cons = System.out :: println;
            		cons.accept("Hello World!");
            		//供给型接口 public String  toUpperCase();
            		Supplier<String> sup = "Hello" :: toUpperCase;
            		System.out.println(sup.get());
            		//断言型接口 String类里面有一个equalsIgnoreCase()方法
            		Predicate<String> pre = "Hello" :: equalsIgnoreCase;
            		System.out.println(pre.test("hello"));        
[【Java8新特性】关于Java8中的日期时间API，你需要掌握这些！！](https://www.cnblogs.com/binghe001/p/13028868.html)
```
#### [【Java8新特性】不了解Optional类，简历上别说你懂Java8！！](https://www.cnblogs.com/binghe001/p/12995007.html)
```markdown
什么是Optional类？
Optional类(java.util.Optional)是一个容器类，代表一个值存在或不存在，原来用null表示一个值不存在，现在Optional可以更好的表达这个概念。并且可以避免空指针异常。
Optional类常用方法：
    Optional.of(T t) : 创建一个 Optional 实例。
    Optional.empty() : 创建一个空的 Optional 实例。
    Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例。
    isPresent() : 判断是否包含值。
    orElse(T t) : 如果调用对象包含值，返回该值，否则返回t。
    orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值。
    map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()。
    flatMap(Function mapper):与 map 类似，要求返回值必须是Optional。
```
#### [【Java8新特性】重复注解与类型注解，你真的学会了吗？](https://www.cnblogs.com/binghe001/p/13033447.html)
```markdown
Java 8 主要有两点改进：类型注解和重复注解。
1.类型注解
    1）Java8的类型注解扩展了注解使用的范围。
    在java8之前，注解只能是在声明的地方所使用，java8开始，注解可以应用在任何地方。
    例如：创建类实例:new @Interned MyObject();
    类型映射:myString = (@NonNull String) str;
    implements 语句中:class UnmodifiableList<T> implements@Readonly List<@Readonly T> { ... }
    throw exception声明:void monitorTemperature() throws@Critical TemperatureException { ... }
    在Java 8里面，当类型转化甚至分配新对象的时候，都可以在声明变量或者参数的时候使用注解。Java注解可以支持任意类型。
    类型注解只是语法而不是语义，并不会影响java的编译时间，加载时间，以及运行时间，也就是说，编译成class文件的时候并不包含类型注解。
    2）新增ElementType.TYPE_USE和ElementType.TYPE_PARAMETER（在Target上）
        新增的两个注释的程序元素类型 ElementType.TYPE_USE 和 ElementType.TYPE_PARAMETER用来描述注解的新场合。
        - ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中。
        - ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中（例如：声明语句、泛型和强制转换语句中的类型）
        例如，下面的示例。
        @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
        @interface MyAnnotation {}
    3)类型注解的作用
    类型注解被用来支持在Java的程序中做强类型检查。配合第三方插件工具Checker Framework（注：此插件so easy,这里不介绍了），
    可以在编译的时候检测出runtime error（例如：UnsupportedOperationException；NumberFormatException；
    NullPointerException异常等都是runtime error），以提高代码质量。这就是类型注解的作用。
    注意：使用Checker Framework可以找到类型注解出现的地方并检查。
2.重复注解
    允许在同一声明类型（类，属性，或方法）上多次使用同一个注解。
    Java8以前的版本使用注解有一个限制是相同的注解在同一位置只能使用一次，不能使用多次。
    Java 8 引入了重复注解机制，这样相同的注解可以在同一地方使用多次。重复注解机制本身必须用 @Repeatable 注解。
    实际上，重复注解不是一个语言上的改变，只是编译器层面的改动，技术层面仍然是一样的。
    例如，我们可以使用如下示例来具体对比Java8之前的版本和Java8中的注解。
Java 8对注解处理提供了两点改进：可重复的注解及可用于类型的注解。
总体来说，比较简单，下面，我们就以实例的形式来说明Java8中的重复注解和类型注解。
```
###32.Java9新特性
  
###33.Java10新特性
  
###34.Java11新特性
  
###35.Java12新特性
  
###36.Java13新特性
        
