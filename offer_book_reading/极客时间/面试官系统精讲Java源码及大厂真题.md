# **第1章 基础**

## **01 开篇词：为什么学习本专栏**

### 不为了源码而读源码，只为了更好的实践

你好，我是文贺，Java 技术专家，DDD 和业务中台的资深实践者，一周面试 2～3 次的面试官。

说起自己开始阅读 Java 源码的契机，还是在第一年换工作的时候，被大厂的技术面虐的体无完肤，后来总结大厂的面试套路，发现很喜欢问 Java 底层实现，即 Java 源码，于是我花了半年时间，啃下了 Java 源码，终于进了网易。

以我个人经历来说，**阅读源码真的可以帮助你顺利过技术面，找到更好的工作**。

阅读源码还可以帮忙我们增加个人自信，学习其优良设计思想，总结出最优使用姿势，避免踩坑，甚至针对工作中的痛点进行创新改造。

而在实际工作中，你可能遇到过这些尴尬：

1. 很多 API 的使用，需要先百度，然后再复制粘贴；
2. 针对不同的场景，不知道如何设计不同类型的线程池，对 API 的使用没有太多的场景经验总结；
3. 代码 review 的时候，提不出意见，自己的代码却常常被别人吐槽；
4. 对于工作中 API 使用不方便的地方，想优化，但不知其内部实现原理，不敢动手。

通过调查发现，能绕过以上尴尬的同学，多数都是阅读过 Java 源码、对 Java 底层的构造了如指掌的。这些同学能写团队核心代码，能轻易看出代码漏洞，能总结出 Java 的设计思想，并运用到日常工作中。

**所以说阅读 Java 源码，还能让我们结合场景熟练的使用 API，基于工作中使用痛点做一些源码创新，是成为团队核心的技术基础。**

我现在陆续阅读过 Java 7、8 的源码几遍，一开始很难坚持，因为源码比较枯燥。首先不知道源码的整体架构，然后细节之处不知道为什么这么写，但最终还是啃下来了，甚至喜欢了阅读源码，于是我在想，是不是可以把我的源码阅读经验和一线工作经验结合，使本课程成为源码的阅读指南、面试指南和场景实战指南。

为了大家更易阅读，在接下来的 40 课中，**我们会先从实际的案例场景出发，对 Java 中 30+ 个核心类进行图文源码解析，并从中总结出设计思想、最优使用姿势和坑，最终以连环面试题进行巩固**。

我们对于每个源码类的文章套路为：

1. 怎么用：用场景来说明类的重要方法的使用技巧；
2. 为什么：源码解析其底层实现源码，复杂的源码会有动图解析；
3. 总结：总结出设计思想、最优使用姿势和坑，看看能否为工作中所用；
4. 面试题：总结出最新连环面试题，一题接着一题深入，可以作为面试官和面试者的面试指南。

本课程涉及到的 Java 知识非常齐全，主要有基础类、集合类、并发集合类、队列、线程、锁、线程池、Lambda 流等内容，内容几乎涵盖了工作和面试的核心，如果你对这些源码都了如指掌，面试题再千变万化，你也能应对自如。

源码解析主要以 Java 8 为主，对版本变动较大的类会做 Java 7、8 版本的比较。

学习本课程门槛较低，只需要你有一颗肯学习进步，希望成为 Java 技术专家的信心和决心即可。

本课程会有源码的 GitHub，欢迎喜欢阅读源码的同学来创建自己的分支，一起来结伴阅读源码，写上自己的感悟互相交流。

总之，我一定会把专栏写的通俗易懂，也希望面前的你通过阅读本课程，找到更好的工作，走上 Java 专家之路，成为更专业，更资深，更有影响力的技术实践创新者。

## **02 String、Long 源码解析和面试题**

### 引导语

String 和 Long 大家都很熟悉，本小节主要结合实际的工作场景，来一起看下 String 和 Long 的底层源码实现，看看平时我们使用时，有无需要注意的点，总结一下这些 API 都适用于哪些场景。



### 1 String



#### 1.1 不变性

我们常常听人说，HashMap 的 key 建议使用不可变类，比如说 String 这种不可变类。这里说的不可变指的是类值一旦被初始化，就不能再被改变了，如果被修改，将会是新的类，我们写个 demo 来演示一下。

```java
String s ="hello";
s ="world";
```

从代码上来看，s 的值好像被修改了，但从 debug 的日志来看，其实是 s 的内存地址已经被修改了，也就说 s =“world” 这个看似简单的赋值，其实已经把 s 的引用指向了新的 String，debug 的截图显示内存地址已经被修改，两张截图如下：

![图片描述](http://img.mukewang.com/5d5fc04a0001c6a508840096.png)![图片描述](http://img.mukewang.com/5d5fc06400019cc210540090.png)我们从源码上查看一下原因：

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /** The value is used for character storage. */
    private final char value[];
}    
```

我们可以看出来两点：

1. String 被 final 修饰，说明 String 类绝不可能被继承了，也就是说任何对 String 的操作方法，都不会被继承覆写；
2. String 中保存数据的是一个 char 的数组 value。我们发现 value 也是被 final 修饰的，也就是说 value 一旦被赋值，内存地址是绝对无法修改的，而且 value 的权限是 private 的，外部绝对访问不到，String 也没有开放出可以对 value 进行赋值的方法，所以说 value 一旦产生，内存地址就根本无法被修改。

以上两点就是 String 不变性的原因，充分利用了 final 关键字的特性，如果你自定义类时，希望也是不可变的，也可以模仿 String 的这两点操作。

因为 String 具有不变性，所以 String 的大多数操作方法，都会返回新的 String，如下面这种写法是不对的：

```java
String str ="hello world !!";
// 这种写法是替换不掉的，必须接受 replace 方法返回的参数才行，这样才行：str = str.replace("l","dd");
str.replace("l","dd");
```



#### 1.2 字符串乱码

在生活中，我们经常碰到这样的场景，进行二进制转化操作时，本地测试的都没有问题，到其它环境机器上时，有时会出现字符串乱码的情况，这个主要是因为在二进制转化操作时，并没有强制规定文件编码，而不同的环境默认的文件编码不一致导致的。

我们也写了一个 demo 来模仿一下字符串乱码：

```java
String str  ="nihao 你好 喬亂";
// 字符串转化成 byte 数组
byte[] bytes = str.getBytes("ISO-8859-1");
// byte 数组转化成字符串
String s2 = new String(bytes);
log.info(s2);
// 结果打印为：
nihao ?? ??
```

打印的结果为？？，这就是常见的乱码表现形式。这时候有同学说，是不是我把代码修改成 `String s2 = new String(bytes,"ISO-8859-1");` 就可以了？这是不行的。主要是因为 ISO-8859-1 这种编码对中文的支持有限，导致中文会显示乱码。唯一的解决办法，就是在所有需要用到编码的地方，都统一使用 UTF-8，对于 String 来说，getBytes 和 new String 两个方法都会使用到编码，我们把这两处的编码替换成 UTF-8 后，打印出的结果就正常了。



#### 1.3 首字母大小写

如果我们的项目被 Spring 托管的话，有时候我们会通过 `applicationContext.getBean(className);` 这种方式得到 SpringBean，这时 className 必须是要满足首字母小写的，除了该场景，在反射场景下面，我们也经常要使类属性的首字母小写，这时候我们一般都会这么做：

`name.substring(0, 1).toLowerCase() + name.substring(1);`，使用 substring 方法，该方法主要是为了截取字符串连续的一部分，substring 有两个方法：

1. `public String substring(int beginIndex, int endIndex)` beginIndex：开始位置，endIndex：结束位置；
2. `public String substring(int beginIndex)`beginIndex：开始位置，结束位置为文本末尾。

substring 方法的底层使用的是字符数组范围截取的方法 ：`Arrays.copyOfRange(字符数组, 开始位置, 结束位置);` 从字符数组中进行一段范围的拷贝。

相反的，如果要修改成首字母大写，只需要修改成 `name.substring(0, 1).toUpperCase() + name.substring(1)` 即可。



#### 1.4 相等判断

我们判断相等有两种办法，equals 和 equalsIgnoreCase。后者判断相等时，会忽略大小写，近期看见一些面试题在问：如果让你写判断两个 String 相等的逻辑，应该如何写，我们来一起看下 equals 的源码，整理一下思路：

```java
public boolean equals(Object anObject) {
    // 判断内存地址是否相同
    if (this == anObject) {
        return true;
    }
    // 待比较的对象是否是 String，如果不是 String，直接返回不相等
    if (anObject instanceof String) {
        String anotherString = (String)anObject;
        int n = value.length;
        // 两个字符串的长度是否相等，不等则直接返回不相等
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            // 依次比较每个字符是否相等，若有一个不等，直接返回不相等
            while (n-- != 0) {
                if (v1[i] != v2[i])
                    return false;
                i++;
            }
            return true;
        }
    }
    return false;
}
```

从 equals 的源码可以看出，逻辑非常清晰，完全是根据 String 底层的结构来编写出相等的代码。这也提供了一种思路给我们：如果有人问如何判断两者是否相等时，我们可以从两者的底层结构出发，这样可以迅速想到一种贴合实际的思路和方法，就像 String 底层的数据结构是 char 的数组一样，判断相等时，就挨个比较 char 数组中的字符是否相等即可。



#### 1.5 替换、删除

替换在工作中也经常使用，有 replace 替换所有字符、replaceAll 批量替换字符串、replaceFirst 替换遇到的第一个字符串三种场景。

其中在使用 replace 时需要注意，replace 有两个方法，一个入参是 char，一个入参是 String，前者表示替换所有字符，如：`name.replace('a','b')`，后者表示替换所有字符串，如：`name.replace("a","b")`，两者就是单引号和多引号的区别。

需要注意的是， replace 并不只是替换一个，是替换所有匹配到的字符或字符串哦。

写了一个 demo 演示一下三种场景：

```java
public void testReplace(){
  String str ="hello word !!";
  log.info("替换之前 :{}",str);
  str = str.replace('l','d');
  log.info("替换所有字符 :{}",str);
  str = str.replaceAll("d","l");
  log.info("替换全部 :{}",str);
  str = str.replaceFirst("l","");
  log.info("替换第一个 l :{}",str);
}
//输出的结果是：
替换之前 :hello word !!
替换所有字符 :heddo word !!
替换全部 :hello worl !!
替换第一个 :helo worl !!
```

当然我们想要删除某些字符，也可以使用 replace 方法，把想删除的字符替换成 “” 即可。



#### 1.6 拆分和合并

拆分我们使用 split 方法，该方法有两个入参数。第一个参数是我们拆分的标准字符，第二个参数是一个 int 值，叫 limit，来限制我们需要拆分成几个元素。如果 limit 比实际能拆分的个数小，按照 limit 的个数进行拆分，我们演示一个 demo：

```java
String s ="boo:and:foo";
// 我们对 s 进行了各种拆分，演示的代码和结果是：
s.split(":") 结果:["boo","and","foo"]
s.split(":",2) 结果:["boo","and:foo"]
s.split(":",5) 结果:["boo","and","foo"]
s.split(":",-2) 结果:["boo","and","foo"]
s.split("o") 结果:["b","",":and:f"]
s.split("o",2) 结果:["b","o:and:foo"]
```

从演示的结果来看，limit 对拆分的结果，是具有限制作用的，还有就是拆分结果里面不会出现被拆分的字段。

那如果字符串里面有一些空值呢，拆分的结果如下：

```java
String a =",a,,b,";
a.split(",") 结果:["","a","","b"]
```

从拆分结果中，我们可以看到，空值是拆分不掉的，仍然成为结果数组的一员，如果我们想删除空值，只能自己拿到结果后再做操作，但 Guava（Google 开源的技术工具） 提供了一些可靠的工具类，可以帮助我们快速去掉空值，如下：

```java
String a =",a, ,  b  c ,";
// Splitter 是 Guava 提供的 API 
List<String> list = Splitter.on(',')
    .trimResults()// 去掉空格
    .omitEmptyStrings()// 去掉空值
    .splitToList(a);
log.info("Guava 去掉空格的分割方法：{}",JSON.toJSONString(list));
// 打印出的结果为：
["a","b  c"]
```

从打印的结果中，可以看到去掉了空格和空值，这正是我们工作中常常期望的结果，所以推荐使用 Guava 的 API 对字符串进行分割。

合并我们使用 join 方法，此方法是静态的，我们可以直接使用。方法有两个入参，参数一是合并的分隔符，参数二是合并的数据源，数据源支持数组和 List，在使用的时候，我们发现有两个不太方便的地方：

1. 不支持依次 join 多个字符串，比如我们想依次 join 字符串 s 和 s1，如果你这么写的话 `String.join(",",s).join(",",s1)` 最后得到的是 s1 的值，第一次 join 的值被第二次 join 覆盖了；
2. 如果 join 的是一个 List，无法自动过滤掉 null 值。

而 Guava 正好提供了 API，解决上述问题，我们来演示一下：

```java
// 依次 join 多个字符串，Joiner 是 Guava 提供的 API
Joiner joiner = Joiner.on(",").skipNulls();
String result = joiner.join("hello",null,"china");
log.info("依次 join 多个字符串:{}",result);

List<String> list = Lists.newArrayList(new String[]{"hello","china",null});
log.info("自动删除 list 中空值:{}",joiner.join(list));
// 输出的结果为；
依次 join 多个字符串:hello,china
自动删除 list 中空值:hello,china
```

从结果中，我们可以看到 Guava 不仅仅支持多个字符串的合并，还帮助我们去掉了 List 中的空值，这就是我们在工作中常常需要得到的结果。



### 2 Long



#### 2.1 缓存

Long 最被我们关注的就是 Long 的缓存问题，Long 自己实现了一种缓存机制，缓存了从 -128 到 127 内的所有 Long 值，如果是这个范围内的 Long 值，就不会初始化，而是从缓存中拿，缓存初始化源码如下：

```java
private static class LongCache {
    private LongCache(){}
    // 缓存，范围从 -128 到 127，+1 是因为有个 0
    static final Long cache[] = new Long[-(-128) + 127 + 1];

    // 容器初始化时，进行加载
    static {
        // 缓存 Long 值，注意这里是 i - 128 ，所以再拿的时候就需要 + 128
        for(int i = 0; i < cache.length; i++)
            cache[i] = new Long(i - 128);
    }
}
```



### 3 面试题



#### 3.1 为什么使用 Long 时，大家推荐多使用 valueOf 方法，少使用 parseLong 方法

答：因为 Long 本身有缓存机制，缓存了 -128 到 127 范围内的 Long，valueOf 方法会从缓存中去拿值，如果命中缓存，会减少资源的开销，parseLong 方法就没有这个机制。



#### 3.2 如何解决 String 乱码的问题

答：乱码的问题的根源主要是两个：字符集不支持复杂汉字、二进制进行转化时字符集不匹配，所以在 String 乱码时我们可以这么做：

1. 所有可以指定字符集的地方强制指定字符集，比如 new String 和 getBytes 这两个地方；
2. 我们应该使用 UTF-8 这种能完整支持复杂汉字的字符集。



#### 3.3 为什么大家都说 String 是不可变的

答：主要是因为 String 和保存数据的 char 数组，都被 final 关键字所修饰，所以是不可变的，具体细节描述可以参考上文。



#### 3.4 String 一些常用操作问题，如问如何分割、合并、替换、删除、截取等等问题

答：这些都属于问 String 的基本操作题目，考察我们平时对 String 的使用熟练程度，可以参考上文。



### 总结

String 和 Long 在我们工作中使用频率很高，在面试的过程中，考官也喜欢问一些关于实际操作的问题，来考察我们的使用熟练度，所以本文中列举的一些 demo，大家可以试试手，完整的代码可以去 GitHub 上面去拉取。

## **03 Java 常用关键字理解**

### 引导语

Java 中的关键字很多，大约有 50+，在命名上我们不能和这些关键字冲突的，编译会报错，每个关键字都代表着不同场景下的不同含义，接下来我们挑选 6 个比较重要的关键字，深入学习一下。



### 1 static

意思是静态的、全局的，一旦被修饰，说明被修饰的东西在一定范围内是共享的，谁都可以访问，这时候需要注意并发读写的问题。



#### 1.1 修饰的对象

static 只能修饰类变量、方法和方法块。

**当 static 修饰类变量时**，如果该变量是 public 的话，表示该变量任何类都可以直接访问，而且无需初始化类，直接使用 **类名.static 变量** 这种形式访问即可。

这时候我们非常需要注意的一点就是线程安全的问题了，因为当多个线程同时对共享变量进行读写时，很有可能会出现并发问题，如我们定义了：`public static List list = new ArrayList();`这样的共享变量。这个 list 如果同时被多个线程访问的话，就有线程安全的问题，这时候一般有两个解决办法：

1. 把线程不安全的 ArrayList 换成 线程安全的 CopyOnWriteArrayList；
2. 每次访问时，手动加锁。

所以在使用 static 修饰类变量时，如何保证线程安全是我们常常需要考虑的。

**当 static 修饰方法时**，代表该方法和当前类是无关的，任意类都可以直接访问（如果权限是 public 的话）。

有一点需要注意的是，该方法内部只能调用同样被 static 修饰的方法，不能调用普通方法，我们常用的 util 类里面的各种方法，我们比较喜欢用 static 修饰方法，好处就是调用特别方便。

static 方法内部的变量在执行时是没有线程安全问题的。方法执行时，数据运行在栈里面，栈的数据每个线程都是隔离开的，所以不会有线程安全的问题，所以 util 类的各个 static 方法，我们是可以放心使用的。

**当 static 修饰方法块时**，我们叫做静态块，静态块常常用于在类启动之前，初始化一些值，比如：

```java
public static List<String> list = new ArrayList();
// 进行一些初始化的工作
static {
    list.add("1");
}
```

这段代码演示了静态块做一些初始化的工作，但需要注意的是，静态块只能调用同样被 static 修饰的变量，并且 static 的变量需要写在静态块的前面，不然编译也会报错。



#### 1.2 初始化时机

对于被 static 修饰的类变量、方法块和静态方法的初始化时机，我们写了一个测试 demo，如下图：
![图片描述](http://img.mukewang.com/5d5fc0be000136a322560976.png)打印出来的结果是：

父类静态变量初始化
父类静态块初始化
子类静态变量初始化
子类静态块初始化
main 方法执行
父类构造器初始化
子类构造器初始化

从结果中，我们可以看出两点：

1. 父类的静态变量和静态块比子类优先初始化；
2. 静态变量和静态块比类构造器优先初始化。

被 static 修饰的方法，在类初始化的时候并不会初始化，只有当自己被调用时，才会被执行。



### 2 final

final 的意思是不变的，一般来说用于以下三种场景：

1. 被 final 修饰的类，表明该类是无法继承的；
2. 被 final 修饰的方法，表明该方法是无法覆写的；
3. 被 final 修饰的变量，说明该变量在声明的时候，就必须初始化完成，而且以后也不能修改其内存地址。

第三点注意下，我们说的是无法修改其内存地址，并没有说无法修改其值。因为对于 List、Map 这些集合类来说，被 final 修饰后，是可以修改其内部值的，但却无法修改其初始化时的内存地址。

例子我们就不举了，1-1 小节 String 的不变性就是一个很好的例子。



### 3 try、catch、finally

这三个关键字常用于我们捕捉异常的一整套流程，try 用来确定代码执行的范围，catch 捕捉可能会发生的异常，finally 用来执行一定要执行的代码块，除了这些，我们还需要清楚，每个地方如果发生异常会怎么办，我们举一个例子来演示一下：

```java
public void testCatchFinally() {
  try {
    log.info("try is run");
    if (true) {
      throw new RuntimeException("try exception");
    }
  } catch (Exception e) {
    log.info("catch is run");
    if (true) {
      throw new RuntimeException("catch exception");
    }
  } finally {
    log.info("finally is run");
  }
}
```

这个代码演示了在 try、catch 中都遇到了异常，代码的执行顺序为：try -> catch -> finally，输出的结果如下：
![图片描述](http://img.mukewang.com/5d5fc0d40001ede717880302.png)可以看到两点：

1. finally 先执行后，再抛出 catch 的异常；
2. 最终捕获的异常是 catch 的异常，try 抛出来的异常已经被 catch 吃掉了，所以当我们遇见 catch 也有可能会抛出异常时，我们可以先打印出 try 的异常，这样 try 的异常在日志中就会有所体现。



### 4 volatile

volatile 的意思是可见的，常用来修饰某个共享变量，意思是当共享变量的值被修改后，会及时通知到其它线程上，其它线程就能知道当前共享变量的值已经被修改了。

我们再说原理之前，先说下基础知识。就是在多核 CPU 下，为了提高效率，线程在拿值时，是直接和 CPU 缓存打交道的，而不是内存。主要是因为 CPU 缓存执行速度更快，比如线程要拿值 C，会直接从 CPU 缓存中拿， CPU 缓存中没有，就会从内存中拿，所以线程读的操作永远都是拿 CPU 缓存的值。

这时候会产生一个问题，CPU 缓存中的值和内存中的值可能并不是时刻都同步，导致线程计算的值可能不是最新的，共享变量的值有可能已经被其它线程所修改了，但此时修改是机器内存的值，CPU 缓存的值还是老的，导致计算会出现问题。

这时候有个机制，就是内存会主动通知 CPU 缓存。当前共享变量的值已经失效了，你需要重新来拉取一份，CPU 缓存就会重新从内存中拿取一份最新的值。

volatile 关键字就会触发这种机制，加了 volatile 关键字的变量，就会被识别成共享变量，内存中值被修改后，会通知到各个 CPU 缓存，使 CPU 缓存中的值也对应被修改，从而保证线程从 CPU 缓存中拿取出来的值是最新的。

我们画了一个图来说明一下：
![图片描述](http://img.mukewang.com/5d5fc0ea000100a312740736.png)从图中我们可以看到，线程 1 和线程 2 一开始都读取了 C 值，CPU 1 和 CPU 2 缓存中也都有了 C 值，然后线程 1 把 C 值修改了，这时候内存的值和 CPU 2 缓存中的 C 值就不等了，内存这时发现 C 值被 volatile 关键字修饰，发现其是共享变量，就会使 CPU 2 缓存中的 C 值状态置为无效，CPU 2 会从内存中重新拉取最新的值，这时候线程 2 再来读取 C 值时，读取的已经是内存中最新的值了。



### 5 transient

transient 关键字我们常用来修饰类变量，意思是当前变量是无需进行序列化的。在序列化时，就会忽略该变量，这些在序列化工具底层，就已经对 transient 进行了支持。



### 6 default

default 关键字一般会用在接口的方法上，意思是对于该接口，子类是无需强制实现的，但自己必须有默认实现，我们举个例子如下：
![图片描述](http://img.mukewang.com/5d5fc4460001f3cb17220650.png)default 关键字被很多源码使用，我们后面会说。



### 7 面试题



#### 7.1 如何证明 static 静态变量和类无关？

答：从三个方面就可以看出静态变量和类无关。

1. 我们不需要初始化类就可直接使用静态变量；
2. 我们在类中写个 main 方法运行，即便不写初始化类的代码，静态变量都会自动初始化；
3. 静态变量只会初始化一次，初始化完成之后，不管我再 new 多少个类出来，静态变量都不会再初始化了。

不仅仅是静态变量，静态方法块也和类无关。



#### 7.2 常常看见变量和方法被 static 和 final 两个关键字修饰，为什么这么做？

答：这么做有两个目的：

1. 变量和方法于类无关，可以直接使用，使用比较方便；
2. 强调变量内存地址不可变，方法不可继承覆写，强调了方法内部的稳定性。



#### 7.3 catch 中发生了未知异常，finally 还会执行么？

答：会的，catch 发生了异常，finally 还会执行的，并且是 finally 执行完成之后，才会抛出 catch 中的异常。

不过 catch 会吃掉 try 中抛出的异常，为了避免这种情况，在一些可以预见 catch 中会发生异常的地方，先把 try 抛出的异常打印出来，这样从日志中就可以看到完整的异常了。



#### 7.4 volatile 关键字的作用和原理

答：这个上文说的比较清楚，可以参考上文。



### 总结

Java 的关键字属于比较基础的内容，我们需要清晰明确其含义，才能在后续源码阅读和工作中碰到这些关键字时了然于心，才能明白为什么会在这里使用这样的关键字。比如 String 源码是如何使用 final 关键字达到起不变性的，比如 Java 8 集合中 Map 是如何利用 default 关键字新增各种方法的，这些我们在后续内容都会提到。

## **04 Arrays、Collections、Objects 常用方法源码解析**

### 引导语

我们在工作中都会写工具类，但如何才能使写出来的工具类更好用，也是有一些技巧的。本章内容以三种平时工作中经常使用的工具类为例，从使用案例出发，再看看底层源码的实现，看看能否学习到一些工具类的技巧，以及三种工具类的实际使用场景。

> 下方是本专栏 GitHub 地址：
> 源码解析：https://github.com/luanqiu/java8
> 文章 demo：https://github.com/luanqiu/java8_demo
> 同学们有需要可以对照着来看 ：）



### 1 工具类通用的特征

再看细节之前，我们先总结一下好的工具类都有哪些通用的特征写法：

1. 构造器必须是私有的。这样的话，工具类就无法被 new 出来，因为工具类在使用的时候，无需初始化，直接使用即可，所以不会开放出构造器出来。
2. 工具类的工具方法必须被 static、final 关键字修饰。这样的话就可以保证方法不可变，并且可以直接使用，非常方便。

我们需要注意的是，尽量不在工具方法中，对共享变量有做修改的操作访问（如果必须要做的话，必须加锁），因为会有线程安全的问题。除此之外，工具类方法本身是没有线程安全问题的，可以放心使用。



### 2 Arrays

Arrays 主要对数组提供了一些高效的操作，比如说排序、查找、填充、拷贝、相等判断等等。我们选择其中两三看下，对其余操作感兴趣的同学可以到 GitHub 上查看源码解析。



#### 2.1 排序

Arrays.sort 方法主要用于排序，入参支持 int、long、double 等各种基本类型的数组，也支持自定义类的数组，下面我们写个 demo 来演示一下自定义类数组的排序：

```java
@Data
// 自定义类
class SortDTO {
  private String sortTarget;

  public SortDTO(String sortTarget) {
    this.sortTarget = sortTarget;
  }
}

@Test
public void testSort(){
  List<SortDTO> list = ImmutableList.of(
      new SortDTO("300"),
      new SortDTO("50"),
      new SortDTO("200"),
      new SortDTO("220")
  );
  // 我们先把数组的大小初始化成 list 的大小，保证能够正确执行 toArray
  SortDTO[] array = new SortDTO[list.size()];
  list.toArray(array);

  log.info("排序之前：{}", JSON.toJSONString(array));
  Arrays.sort(array, Comparator.comparing(SortDTO::getSortTarget));
  log.info("排序之后：{}", JSON.toJSONString(array));
}
输出结果为：
排序之前：[{"sortTarget":"300"},{"sortTarget":"50"},{"sortTarget":"200"},{"sortTarget":"220"}]
排序之后：[{"sortTarget":"200"},{"sortTarget":"220"},{"sortTarget":"300"},{"sortTarget":"50"}]
```

从输出的结果中可以看到，排序之后的数组已经是有顺序的了，也可以看到 sort 方法支持两个入参：要排序的数组和外部排序器。

大家都说 sort 方法排序的性能较高，主要原因是 sort 使用了双轴快速排序算法，具体算法就不细说了。



#### 2.1 二分查找法

Arrays.binarySearch 方法主要用于快速从数组中查找出对应的值。其支持的入参类型非常多，如 byte、int、long 各种类型的数组。返回参数是查找到的对应数组下标的值，如果查询不到，则返回负数。
![图片描述](http://img.mukewang.com/5d5fc4a400010d4106820685.png)我们写了一个 demo 如下：

```java
List<SortDTO> list = ImmutableList.of(
    new SortDTO("300"),
    new SortDTO("50"),
    new SortDTO("200"),
    new SortDTO("220")
);

SortDTO[] array = new SortDTO[list.size()];
list.toArray(array);

log.info("搜索之前：{}", JSON.toJSONString(array));
Arrays.sort(array, Comparator.comparing(SortDTO::getSortTarget));
log.info("先排序，结果为：{}", JSON.toJSONString(array));
int index = Arrays.binarySearch(array, new SortDTO("200"),
                    Comparator.comparing(SortDTO::getSortTarget));
if(index<0){
  throw new RuntimeException("没有找到 200");
}
log.info("搜索结果：{}", JSON.toJSONString(array[index]));

输出的结果为：
搜索之前：[{"sortTarget":"300"},{"sortTarget":"50"},{"sortTarget":"200"},{"sortTarget":"220"}]
先排序，结果为：[{"sortTarget":"200"},{"sortTarget":"220"},{"sortTarget":"300"},{"sortTarget":"50"}]
搜索结果：{"sortTarget":"200"}
```

从上述代码中我们需要注意两点：

1. 如果被搜索的数组是无序的，一定要先排序，否则二分搜索很有可能搜索不到，我们 demo 里面也先对数组进行了排序；
2. 搜索方法返回的是数组的下标值。如果搜索不到，返回的下标值就会是负数，这时我们需要判断一下正负。如果是负数，还从数组中获取数据的话，会报数组越界的错误。demo 中对这种情况进行了判断，如果是负数，会提前抛出明确的异常。

接下来，我们来看下二分法底层代码的实现：

```java
// a：我们要搜索的数组，fromIndex：从那里开始搜索，默认是0； toIndex：搜索到何时停止，默认是数组大小
// key：我们需要搜索的值 c：外部比较器
private static <T> int binarySearch0(T[] a, int fromIndex, int toIndex,
                                     T key, Comparator<? super T> c) {
    // 如果比较器 c 是空的，直接使用 key 的 Comparable.compareTo 方法进行排序
    // 假设 key 类型是 String 类型，String 默认实现了 Comparable 接口，就可以直接使用 compareTo 方法进行排序
    if (c == null) {
        // 这是另外一个方法，使用内部排序器进行比较的方法
        return binarySearch0(a, fromIndex, toIndex, key);
    }
    int low = fromIndex;
    int high = toIndex - 1;
    // 开始位置小于结束位置，就会一直循环搜索
    while (low <= high) {
        // 假设 low =0，high =10，那么 mid 就是 5，所以说二分的意思主要在这里，每次都是计算索引的中间值
        int mid = (low + high) >>> 1;
        T midVal = a[mid];
        // 比较数组中间值和给定的值的大小关系
        int cmp = c.compare(midVal, key);
        // 如果数组中间值小于给定的值，说明我们要找的值在中间值的右边
        if (cmp < 0)
            low = mid + 1;
        // 我们要找的值在中间值的左边
        else if (cmp > 0)
            high = mid - 1;
        else
        // 找到了
            return mid; // key found
    }
    // 返回的值是负数，表示没有找到
    return -(low + 1);  // key not found.
}
```

二分的主要意思是每次查找之前，都找到中间值，然后拿我们要比较的值和中间值比较，根据结果修改比较的上限或者下限，通过循环最终找到相等的位置索引，以上代码实现比较简洁，大家可以在自己理解的基础上，自己复写一遍。



#### 2.2 拷贝

数组拷贝我们经常遇到，有时需要拷贝整个数组，有时需要拷贝部分，比如 ArrayList 在 add（扩容） 或 remove（删除元素不是最后一个） 操作时，会进行一些拷贝。拷贝整个数组我们可以使用 copyOf 方法，拷贝部分我们可以使用 copyOfRange 方法，以 copyOfRange 为例，看下底层源码的实现：

```java
// original 原始数组数据
// from 拷贝起点
// to 拷贝终点
public static char[] copyOfRange(char[] original, int from, int to) {
    // 需要拷贝的长度
    int newLength = to - from;
    if (newLength < 0)
        throw new IllegalArgumentException(from + " > " + to);
    // 初始化新数组
    char[] copy = new char[newLength];
    // 调用 native 方法进行拷贝，参数的意思分别是：
    // 被拷贝的数组、从数组那里开始、目标数组、从目的数组那里开始拷贝、拷贝的长度
    System.arraycopy(original, from, copy, 0,
                     Math.min(original.length - from, newLength));
    return copy;
}
```

从源码中，我们发现，Arrays 的拷贝方法，实际上底层调用的是 System.arraycopy 这个 native 方法，如果你自己对底层拷贝方法比较熟悉的话，也可以直接使用。



### 3 Collections

Collections 是为了方便使用集合而产生的工具类，Arrays 方便数组使用，Collections 是方便集合使用。

Collections 也提供了 sort 和 binarySearch 方法，sort 底层使用的就是 Arrays.sort 方法，binarySearch 底层是自己重写了二分查找算法，实现的逻辑和 Arrays 的二分查找算法完全一致，这两个方法上 Collections 和 Arrays 的内部实现很类似，接下来我们来看下 Collections 独有的特性。



#### 3.1 求集合中最大、小值

提供了 max 方法来取得集合中的最大值，min 方法来取得集合中的最小值，max 和 min 方法很相似的，我们以 max 方法为例来说明一下，max 提供了两种类型的方法，一个需要传外部排序器，一个不需要传排序器，但需要集合中的元素强制实现 Comparable 接口，后者的泛型定义很有意思，我们来看下（从右往左看）：
![图片描述](http://img.mukewang.com/5d5fc50a0001220c19701126.png)从这段源码中，我们可以学习到两点：

1. max 方法泛型 T 定义得非常巧妙，意思是泛型必须继承 Object 并且实现 Comparable 的接口。一般让我们来定义的话，我们可以会在方法里面去判断有无实现 Comparable 的接口，这种是在运行时才能知道结果。而这里泛型直接定义了必须实现 Comparable 接口，在编译的时候就可告诉使用者，当前类没有实现 Comparable 接口，使用起来很友好；
2. 给我们提供了实现两种排序机制的好示例：自定义类实现 Comparable 接口和传入外部排序器。两种排序实现原理类似，但实现有所差别，我们在工作中如果需要些排序的工具类时，可以效仿。



#### 3.2 多种类型的集合

Collections 对原始集合类进行了封装，提供了更好的集合类给我们，一种是线程安全的集合，一种是不可变的集合，针对 List、Map、Set 都有提供，我们先来看下线程安全的集合：

##### 3.2.1 线程安全的集合

线程安全的集合方法都是 synchronized 打头的，如下：
![图片描述](http://img.mukewang.com/5d5fc5230001a8ba11460942.png)从方法命名我们都可以看出来，底层是通过 synchronized 轻量锁来实现的，我们以 synchronizedList 为例来说明下底层的实现：
![图片描述](http://img.mukewang.com/5d5fc55a0001a96b14201306.png)可以看到 List 的所有操作方法都被加上了 synchronized 锁，所以多线程对集合同时进行操作，是线程安全的。

##### 3.2.1 不可变的集合

得到不可变集合的方法都是以 unmodifiable 开头的。这类方法的意思是，我们会从原集合中，得到一个不可变的新集合，新集合只能访问，无法修改；一旦修改，就会抛出异常。这主要是因为只开放了查询方法，其余任何修改操作都会抛出异常，我们以 unmodifiableList 为例来看下底层实现机制：
![图片描述](http://img.mukewang.com/5d5fc56c000106c613981318.png)

##### 3.2.2 小结

以上两种 List 其实解决了工作中的一些困惑，比如说 ArrayList 是线程不安全的，然后其内部数组很容易被修改，有的时候，我们希望 List 一旦生成后，就不能被修改，Collections 对 List 重新进行了封装，提供了两种类型的集合封装形式，从而解决了工作中的一些烦恼，如果你平时使用 List 时有一些烦恼，也可以学习此种方式，自己对原始集合进行封装，来解决 List 使用过程中的不方便。



### 4 Objects

对于 Objects，我们经常使用的就是两个场景，相等判断和判空。



#### 4.1 相等判断

Objects 有提供 equals 和 deepEquals 两个方法来进行相等判断，前者是判断基本类型和自定义类的，后者是用来判断数组的，我们来看下底层的源码实现：
![图片描述](http://img.mukewang.com/5d5fc5830001b26a23601336.png)从源码中，可以看出 Objects 对基本类型和复杂类型的对象，都有着比较细粒度的判断，可以放心使用。



#### 4.2 为空判断

![图片描述](http://img.mukewang.com/5d5fc59b00010ebc10820386.png)Objects 提供了各种关于空的一些判断，isNull 和 nonNull 对于对象是否为空返回 Boolean 值，requireNonNull 方法更加严格，如果一旦为空，会直接抛出异常，我们需要根据生活的场景选择使用。



### 5 面试题



#### 5.1 工作中有没有遇到特别好用的工具类，如何写好一个工具类

答：有的，像 Arrays 的排序、二分查找、Collections 的不可变、线程安全集合类、Objects 的判空相等判断等等工具类，好的工具类肯定很好用，比如说使用 static final 关键字对方法进行修饰，工具类构造器必须是私有等等手段来写好工具类。



#### 5.2 写一个二分查找算法的实现

答：可以参考 Arrays 的 binarySearch 方法的源码实现。



#### 5.3 如果我希望 ArrayList 初始化之后，不能被修改，该怎么办

答：可以使用 Collections 的 unmodifiableList 的方法，该方法会返回一个不能被修改的内部类集合，这些集合类只开放查询的方法，对于调用修改集合的方法会直接抛出异常。



### 总结

从三大工具类中，我们不仅学习到了如何写好一个工具类，还熟悉了三大工具类的具体使用姿势，甚至了解了其底层的源码实现，有兴趣的话，可以自己也可以仿照写个好用的工具类加深学习。

# **第2章 集合**

## **05 ArrayList 源码解析和设计思路**

### 引导语

ArrayList 我们几乎每天都会使用到，但真正面试的时候，发现还是有不少人对源码细节说不清楚，给面试官留下比较差的印象，本小节就和大家一起看看面试中和 ArrayList 相关的源码。



### 1 整体架构

ArrayList 整体架构比较简单，就是一个数组结构，比较简单，如下图：
![图片描述](http://img.mukewang.com/5d5fc5f80001e20e15080238.png)图中展示是长度为 10 的数组，从 1 开始计数，index 表示数组的下标，从 0 开始计数，elementData 表示数组本身，源码中除了这两个概念，还有以下三个基本概念：

- DEFAULT_CAPACITY 表示数组的初始大小，默认是 10，这个数字要记住；
- size 表示当前数组的大小，类型 int，没有使用 volatile 修饰，非线程安全的；
- modCount 统计当前数组被修改的版本次数，数组结构有变动，就会 +1。

**类注释**

看源码，首先要看类注释，我们看看类注释上面都说了什么，如下：

- 允许 put null 值，会自动扩容；
- size、isEmpty、get、set、add 等方法时间复杂度都是 O (1)；
- 是非线程安全的，多线程情况下，推荐使用线程安全类：Collections#synchronizedList；
- 增强 for 循环，或者使用迭代器迭代过程中，如果数组大小被改变，会快速失败，抛出异常。

除了上述注释中提到的 4 点，初始化、扩容的本质、迭代器等问题也经常被问，接下来我们从源码出发，一一解析。



### 2 源码解析



#### 2.1 初始化

我们有三种初始化办法：无参数直接初始化、指定大小初始化、指定初始数据初始化，源码如下：

```java
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

//无参数直接初始化，数组大小为空
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

//指定初始数据初始化
public ArrayList(Collection<? extends E> c) {
    //elementData 是保存数组的容器，默认为 null
    elementData = c.toArray();
    //如果给定的集合（c）数据有值
    if ((size = elementData.length) != 0) {
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        //如果集合元素类型不是 Object 类型，我们会转成 Object
        if (elementData.getClass() != Object[].class) {
            elementData = Arrays.copyOf(elementData, size, Object[].class);
        }
    } else {
        // 给定集合（c）无值，则默认空数组
        this.elementData = EMPTY_ELEMENTDATA;
    }
}
```

除了源码的中文注释，我们补充两点：

1：ArrayList 无参构造器初始化时，默认大小是空数组，并不是大家常说的 10，10 是在第一次 add 的时候扩容的数组值。

2：指定初始数据初始化时，我们发现一个这样子的注释 see 6260652，这是 Java 的一个 bug，意思是当给定集合内的元素不是 Object 类型时，我们会转化成 Object 的类型。一般情况下都不会触发此 bug，只有在下列场景下才会触发：ArrayList 初始化之后（ArrayList 元素非 Object 类型），再次调用 toArray 方法，得到 Object 数组，并且往 Object 数组赋值时，才会触发此 bug，代码和原因如图：
![图片描述](http://img.mukewang.com/5d5fc6100001109518100714.png)官方查看文档地址：https://bugs.java.com/bugdatabase/view_bug.do?bug_id=6260652，问题在 Java 9 中被解决。



#### 2.2 新增和扩容实现

新增就是往数组中添加元素，主要分成两步：

- 判断是否需要扩容，如果需要执行扩容操作；
- 直接赋值。

两步源码体现如下：

```java
public boolean add(E e) {
  //确保数组大小是否足够，不够执行扩容，size 为当前数组的大小
  ensureCapacityInternal(size + 1);  // Increments modCount!!
  //直接赋值，线程不安全的
  elementData[size++] = e;
  return true;
}
```

我们先看下扩容（ensureCapacityInternal）的源码：

```java
private void ensureCapacityInternal(int minCapacity) {
  //如果初始化数组大小时，有给定初始值，以给定的大小为准，不走 if 逻辑
  if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
    minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
  }
  //确保容积足够
  ensureExplicitCapacity(minCapacity);
}
private void ensureExplicitCapacity(int minCapacity) {
  //记录数组被修改
  modCount++;
  // 如果我们期望的最小容量大于目前数组的长度，那么就扩容
  if (minCapacity - elementData.length > 0)
    grow(minCapacity);
}
//扩容，并把现有数据拷贝到新的数组里面去
private void grow(int minCapacity) {
  int oldCapacity = elementData.length;
  // oldCapacity >> 1 是把 oldCapacity 除以 2 的意思
  int newCapacity = oldCapacity + (oldCapacity >> 1);

  // 如果扩容后的值 < 我们的期望值，扩容后的值就等于我们的期望值
  if (newCapacity - minCapacity < 0)
    newCapacity = minCapacity;

  // 如果扩容后的值 > jvm 所能分配的数组的最大值，那么就用 Integer 的最大值
  if (newCapacity - MAX_ARRAY_SIZE > 0)
    newCapacity = hugeCapacity(minCapacity);
 
  // 通过复制进行扩容
  elementData = Arrays.copyOf(elementData, newCapacity);
}
```

注解应该比较详细，我们需要注意的四点是：

- 扩容的规则并不是翻倍，是原来容量大小 + 容量大小的一半，直白来说，扩容后的大小是原来容量的 1.5 倍；
- ArrayList 中的数组的最大值是 Integer.MAX_VALUE，超过这个值，JVM 就不会给数组分配内存空间了。
- 新增时，并没有对值进行严格的校验，所以 ArrayList 是允许 null 值的。

从新增和扩容源码中，下面这点值得我们借鉴：

- 源码在扩容的时候，有数组大小溢出意识，就是说扩容后数组的大小下界不能小于 0，上界不能大于 Integer 的最大值，这种意识我们可以学习。

扩容完成之后，赋值是非常简单的，直接往数组上添加元素即可：elementData [size++] = e。也正是通过这种简单赋值，没有任何锁控制，所以这里的操作是线程不安全的，对于新增和扩容的实现，画了一个动图，如下：
![图片描述](http://img.mukewang.com/5d5fc62e000112c203600240.gif)



#### 2.3 扩容的本质

扩容是通过这行代码来实现的：`Arrays.copyOf(elementData, newCapacity);`，这行代码描述的本质是数组之间的拷贝，扩容是会先新建一个符合我们预期容量的新数组，然后把老数组的数据拷贝过去，我们通过 System.arraycopy 方法进行拷贝，此方法是 native 的方法，源码如下：

```java
/**
 * @param src     被拷贝的数组
 * @param srcPos  从数组那里开始
 * @param dest    目标数组
 * @param destPos 从目标数组那个索引位置开始拷贝
 * @param length  拷贝的长度 
 * 此方法是没有返回值的，通过 dest 的引用进行传值
 */
public static native void arraycopy(Object src, int srcPos,
                                    Object dest, int destPos,
                                    int length);
```

我们可以通过下面这行代码进行调用，newElementData 表示新的数组：

```java
System.arraycopy(elementData, 0, newElementData, 0,Math.min(elementData.length,newCapacity))
```



#### 2.4 删除

ArrayList 删除元素有很多种方式，比如根据数组索引删除、根据值删除或批量删除等等，原理和思路都差不多，我们选取根据值删除方式来进行源码说明：

```java
public boolean remove(Object o) {
  // 如果要删除的值是 null，找到第一个值是 null 的删除
  if (o == null) {
    for (int index = 0; index < size; index++)
      if (elementData[index] == null) {
        fastRemove(index);
        return true;
      }
  } else {
    // 如果要删除的值不为 null，找到第一个和要删除的值相等的删除
    for (int index = 0; index < size; index++)
      // 这里是根据  equals 来判断值相等的，相等后再根据索引位置进行删除
      if (o.equals(elementData[index])) {
        fastRemove(index);
        return true;
      }
  }
  return false;
}
```

我们需要注意的两点是：

- 新增的时候是没有对 null 进行校验的，所以删除的时候也是允许删除 null 值的；
- 找到值在数组中的索引位置，是通过 equals 来判断的，如果数组元素不是基本类型，需要我们关注 equals 的具体实现。

上面代码已经找到要删除元素的索引位置了，下面代码是根据索引位置进行元素的删除：

```java
private void fastRemove(int index) {
  // 记录数组的结构要发生变动了
  modCount++;
  // numMoved 表示删除 index 位置的元素后，需要从 index 后移动多少个元素到前面去
  // 减 1 的原因，是因为 size 从 1 开始算起，index 从 0开始算起
  int numMoved = size - index - 1;
  if (numMoved > 0)
    // 从 index +1 位置开始被拷贝，拷贝的起始位置是 index，长度是 numMoved
    System.arraycopy(elementData, index+1, elementData, index, numMoved);
  //数组最后一个位置赋值 null，帮助 GC
  elementData[--size] = null;
}
```

从源码中，我们可以看出，某一个元素被删除后，为了维护数组结构，我们都会把数组后面的元素往前移动，下面动图也演示了其过程：
![图片描述](http://img.mukewang.com/5d5fc643000142a403600240.gif)



#### 2.5 迭代器

如果要自己实现迭代器，实现 java.util.Iterator 类就好了，ArrayList 也是这样做的，我们来看下迭代器的几个总要的参数：

```java
int cursor;// 迭代过程中，下一个元素的位置，默认从 0 开始。
int lastRet = -1; // 新增场景：表示上一次迭代过程中，索引的位置；删除场景：为 -1。
int expectedModCount = modCount;// expectedModCount 表示迭代过程中，期望的版本号；modCount 表示数组实际的版本号。
```

迭代器一般来说有三个方法：

- hasNext 还有没有值可以迭代
- next 如果有值可以迭代，迭代的值是多少
- remove 删除当前迭代的值

我们来分别看下三个方法的源码：

**hasNext**

```java
public boolean hasNext() {
  return cursor != size;//cursor 表示下一个元素的位置，size 表示实际大小，如果两者相等，说明已经没有元素可以迭代了，如果不等，说明还可以迭代
}
```

**next**

```java
public E next() {
  //迭代过程中，判断版本号有无被修改，有被修改，抛 ConcurrentModificationException 异常
  checkForComodification();
  //本次迭代过程中，元素的索引位置
  int i = cursor;
  if (i >= size)
    throw new NoSuchElementException();
  Object[] elementData = ArrayList.this.elementData;
  if (i >= elementData.length)
    throw new ConcurrentModificationException();
  // 下一次迭代时，元素的位置，为下一次迭代做准备
  cursor = i + 1;
  // 返回元素值
  return (E) elementData[lastRet = i];
}
// 版本号比较
final void checkForComodification() {
  if (modCount != expectedModCount)
    throw new ConcurrentModificationException();
}
```

从源码中可以看到，next 方法就干了两件事情，第一是检验能不能继续迭代，第二是找到迭代的值，并为下一次迭代做准备（cursor+1）。

**remove**

```java
public void remove() {
  // 如果上一次操作时，数组的位置已经小于 0 了，说明数组已经被删除完了
  if (lastRet < 0)
    throw new IllegalStateException();
  //迭代过程中，判断版本号有无被修改，有被修改，抛 ConcurrentModificationException 异常
  checkForComodification();

  try {
    ArrayList.this.remove(lastRet);
    cursor = lastRet;
    // -1 表示元素已经被删除，这里也防止重复删除
    lastRet = -1;
    // 删除元素时 modCount 的值已经发生变化，在此赋值给 expectedModCount
    // 这样下次迭代时，两者的值是一致的了
    expectedModCount = modCount;
  } catch (IndexOutOfBoundsException ex) {
    throw new ConcurrentModificationException();
  }
}
```

这里我们需要注意的两点是：

- lastRet = -1 的操作目的，是防止重复删除操作
- 删除元素成功，数组当前 modCount 就会发生变化，这里会把 expectedModCount 重新赋值，下次迭代时两者的值就会一致了



#### 2.6 时间复杂度

从我们上面新增或删除方法的源码解析，对数组元素的操作，只需要根据数组索引，直接新增和删除，所以时间复杂度是 O (1)。



#### 2.7 线程安全

我们需要强调的是，只有当 ArrayList 作为共享变量时，才会有线程安全问题，当 ArrayList 是方法内的局部变量时，是没有线程安全的问题的。

ArrayList 有线程安全问题的本质，是因为 ArrayList 自身的 elementData、size、modConut 在进行各种操作时，都没有加锁，而且这些变量的类型并非是可见（volatile）的，所以如果多个线程对这些变量进行操作时，可能会有值被覆盖的情况。

类注释中推荐我们使用 Collections#synchronizedList 来保证线程安全，SynchronizedList 是通过在每个方法上面加上锁来实现，虽然实现了线程安全，但是性能大大降低，具体实现源码：

```java
public boolean add(E e) {
    synchronized (mutex) {// synchronized 是一种轻量锁，mutex 表示一个当前 SynchronizedList
        return c.add(e);
    }
}
```



### 总结

本文从 ArrayList 整体架构出发，落地到初始化、新增、扩容、删除、迭代等核心源码实现，我们发现 ArrayList 其实就是围绕底层数组结构，各个 API 都是对数组的操作进行封装，让使用者无需感知底层实现，只需关注如何使用即可。

## **06 LinkedList 源码解析**

### 引导语

LinkedList 适用于集合元素先入先出和先入后出的场景，在队列源码中被频繁使用，面试也经常问到，本小节让我们通过源码来加深对 LinkedList 的了解。



### 1 整体架构

LinkedList 底层数据结构是一个双向链表，整体结构如下图所示：
![图片描述](http://img.mukewang.com/5d5fc67a0001f59212400288.png)上图代表了一个双向链表结构，链表中的每个节点都可以向前或者向后追溯，我们有几个概念如下：

- 链表每个节点我们叫做 Node，Node 有 prev 属性，代表前一个节点的位置，next 属性，代表后一个节点的位置；
- first 是双向链表的头节点，它的前一个节点是 null。
- last 是双向链表的尾节点，它的后一个节点是 null；
- 当链表中没有数据时，first 和 last 是同一个节点，前后指向都是 null；
- 因为是个双向链表，只要机器内存足够强大，是没有大小限制的。

链表中的元素叫做 Node，我们看下 Node 的组成部分：

```java
private static class Node<E> {
    E item;// 节点值
    Node<E> next; // 指向的下一个节点
    Node<E> prev; // 指向的前一个节点

    // 初始化参数顺序分别是：前一个节点、本身节点值、后一个节点
    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```



### 2 源码解析



#### 2.1 追加（新增）

追加节点时，我们可以选择追加到链表头部，还是追加到链表尾部，add 方法默认是从尾部开始追加，addFirst 方法是从头部开始追加，我们分别来看下两种不同的追加方式：

**从尾部追加（add）**

```java
// 从尾部开始追加节点
void linkLast(E e) {
    // 把尾节点数据暂存
    final Node<E> l = last;
    // 新建新的节点，初始化入参含义：
    // l 是新节点的前一个节点，当前值是尾节点值
    // e 表示当前新增节点，当前新增节点后一个节点是 null
    final Node<E> newNode = new Node<>(l, e, null);
    // 新建节点追加到尾部
    last = newNode;
    //如果链表为空（l 是尾节点，尾节点为空，链表即空），头部和尾部是同一个节点，都是新建的节点
    if (l == null)
        first = newNode;![图片描述](//img.mukewang.com/5d5fc69600013e4803600240.gif)
    //否则把前尾节点的下一个节点，指向当前尾节点。
    else
        l.next = newNode;
    //大小和版本更改
    size++;
    modCount++;
}
```

从源码上来看，尾部追加节点比较简单，只需要简单地把指向位置修改下即可，我们做个动图来描述下整个过程：
![图片描述](http://img.mukewang.com/5d5fc6a300013e4803600240.gif)
**从头部追加（addFirst）**

```java
// 从头部追加
private void linkFirst(E e) {
    // 头节点赋值给临时变量
    final Node<E> f = first;
    // 新建节点，前一个节点指向null，e 是新建节点，f 是新建节点的下一个节点，目前值是头节点的值
    final Node<E> newNode = new Node<>(null, e, f);
    // 新建节点成为头节点
    first = newNode;
    // 头节点为空，就是链表为空，头尾节点是一个节点
    if (f == null)
        last = newNode;
    //上一个头节点的前一个节点指向当前节点
    else
        f.prev = newNode;
    size++;
    modCount++;
}
```

头部追加节点和尾部追加节点非常类似，只是前者是移动头节点的 prev 指向，后者是移动尾节点的 next 指向。



#### 2.2 节点删除

节点删除的方式和追加类似，我们可以选择从头部删除，也可以选择从尾部删除，删除操作会把节点的值，前后指向节点都置为 null，帮助 GC 进行回收。

**从头部删除**

```java
//从头删除节点 f 是链表头节点
private E unlinkFirst(Node<E> f) {
    // 拿出头节点的值，作为方法的返回值
    final E element = f.item;
    // 拿出头节点的下一个节点
    final Node<E> next = f.next;
    //帮助 GC 回收头节点
    f.item = null;
    f.next = null;
    // 头节点的下一个节点成为头节点
    first = next;
    //如果 next 为空，表明链表为空
    if (next == null)
        last = null;
    //链表不为空，头节点的前一个节点指向 null
    else
        next.prev = null;
    //修改链表大小和版本
    size--;
    modCount++;
    return element;
}
```

从尾部删除节点代码也是类似的，就不贴了。

**从源码中我们可以了解到，链表结构的节点新增、删除都非常简单，仅仅把前后节点的指向修改下就好了，所以 LinkedList 新增和删除速度很快。**



#### 2.3 节点查询

链表查询某一个节点是比较慢的，需要挨个循环查找才行，我们看看 LinkedList 的源码是如何寻找节点的：

```java
// 根据链表索引位置查询节点
Node<E> node(int index) {
    // 如果 index 处于队列的前半部分，从头开始找，size >> 1 是 size 除以 2 的意思。
    if (index < (size >> 1)) {
        Node<E> x = first;
        // 直到 for 循环到 index 的前一个 node 停止
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    } else {// 如果 index 处于队列的后半部分，从尾开始找
        Node<E> x = last;
        // 直到 for 循环到 index 的后一个 node 停止
        for (int i = size - 1; i > index; i--)
            x = x.prev;
        return x;
    }
}
```

从源码中我们可以发现，LinkedList 并没有采用从头循环到尾的做法，而是采取了简单二分法，首先看看 index 是在链表的前半部分，还是后半部分。如果是前半部分，就从头开始寻找，反之亦然。通过这种方式，使循环的次数至少降低了一半，提高了查找的性能，这种思想值得我们借鉴。



#### 2.4 方法对比

LinkedList 实现了 Queue 接口，在新增、删除、查询等方面增加了很多新的方法，这些方法在平时特别容易混淆，在链表为空的情况下，返回值也不太一样，我们列一个表格，方便大家记录：

| 方法含义 | 返回异常  | 返回特殊值 | 底层实现                                         |
| :------- | :-------- | :--------- | :----------------------------------------------- |
| 新增     | add(e)    | offer(e)   | 底层实现相同                                     |
| 删除     | remove()  | poll(e)    | 链表为空时，remove 会抛出异常，poll 返回 null。  |
| 查找     | element() | peek()     | 链表为空时，element 会抛出异常，peek 返回 null。 |

PS：Queue 接口注释建议 add 方法操作失败时抛出异常，但 LinkedList 实现的 add 方法一直返回 true。
LinkedList 也实现了 Deque 接口，对新增、删除和查找都提供从头开始，还是从尾开始两种方向的方法，比如 remove 方法，Deque 提供了 removeFirst 和 removeLast 两种方向的使用方式，但当链表为空时的表现都和 remove 方法一样，都会抛出异常。



#### 2.5 迭代器

因为 LinkedList 要实现双向的迭代访问，所以我们使用 Iterator 接口肯定不行了，因为 Iterator 只支持从头到尾的访问。Java 新增了一个迭代接口，叫做：ListIterator，这个接口提供了向前和向后的迭代方法，如下所示：

| 迭代顺序         | 方法                                 |
| :--------------- | :----------------------------------- |
| 从尾到头迭代方法 | hasPrevious、previous、previousIndex |
| 从头到尾迭代方法 | hasNext、next、nextIndex             |

LinkedList 实现了 ListIterator 接口，如下图所示：

```java
// 双向迭代器
private class ListItr implements ListIterator<E> {
    private Node<E> lastReturned;//上一次执行 next() 或者 previos() 方法时的节点位置
    private Node<E> next;//下一个节点
    private int nextIndex;//下一个节点的位置
    //expectedModCount：期望版本号；modCount：目前最新版本号
    private int expectedModCount = modCount;
    …………
}
```

我们先来看下从头到尾方向的迭代：

```java
// 判断还有没有下一个元素
public boolean hasNext() {
    return nextIndex < size;// 下一个节点的索引小于链表的大小，就有
}

// 取下一个元素
public E next() {
    //检查期望版本号有无发生变化
    checkForComodification();
    if (!hasNext())//再次检查
        throw new NoSuchElementException();
    // next 是当前节点，在上一次执行 next() 方法时被赋值的。
    // 第一次执行时，是在初始化迭代器的时候，next 被赋值的
    lastReturned = next;
    // next 是下一个节点了，为下次迭代做准备
    next = next.next;
    nextIndex++;
    return lastReturned.item;
}
```

上述源码的思路就是直接取当前节点的下一个节点，而从尾到头迭代稍微复杂一点，如下：

```java
// 如果上次节点索引位置大于 0，就还有节点可以迭代
public boolean hasPrevious() {
    return nextIndex > 0;
}
// 取前一个节点
public E previous() {
    checkForComodification();
    if (!hasPrevious())
        throw new NoSuchElementException();
    // next 为空场景：1:说明是第一次迭代，取尾节点(last);2:上一次操作把尾节点删除掉了
    // next 不为空场景：说明已经发生过迭代了，直接取前一个节点即可(next.prev)
    lastReturned = next = (next == null) ? last : next.prev;
    // 索引位置变化
    nextIndex--;
    return lastReturned.item;
}
```

这里复杂点体现在需要判断 next 不为空和为空的场景，代码注释中有详细的描述。

**迭代器删除**

LinkedList 在删除元素时，也推荐通过迭代器进行删除，删除过程如下：

```java
public void remove() {
    checkForComodification();
    // lastReturned 是本次迭代需要删除的值，分以下空和非空两种情况：
    // lastReturned 为空，说明调用者没有主动执行过 next() 或者 previos()，直接报错
    // lastReturned 不为空，是在上次执行 next() 或者 previos()方法时赋的值
    if (lastReturned == null)
        throw new IllegalStateException();
    Node<E> lastNext = lastReturned.next;
    //删除当前节点
    unlink(lastReturned);
    // next == lastReturned 的场景分析：从尾到头递归顺序，并且是第一次迭代，并且要删除最后一个元素的情况下
    // 这种情况下，previous() 方法里面设置了 lastReturned = next = last,所以 next 和 lastReturned会相等
    if (next == lastReturned)
        // 这时候 lastReturned 是尾节点，lastNext 是 null，所以 next 也是 null，这样在 previous() 执行时，发现 next 是 null，就会把尾节点赋值给 next
        next = lastNext;
    else
        nextIndex--;
    lastReturned = null;
    expectedModCount++;
}
```



### 总结

LinkedList 适用于要求有顺序、并且会按照顺序进行迭代的场景，主要是依赖于底层的链表结构，在面试中的频率还是蛮高的，相信理清楚上面的源码后，应对面试应该没有问题。

## **07 List 源码会问哪些面试题**

### 引导语

List 作为工作中最常见的集合类型，在面试过程中，也是经常会被问到各种各样的面试题，一般来说，只要你看过源码，心中对 List 的总体结构和细节有所了解的话，基本问题都不大。



### 1 面试题



#### 1.1 说说你自己对 ArrayList 的理解？

很多面试官喜欢这样子开头，考察面试同学对 ArrayList 有没有总结经验，介于 ArrayList 内容很多，建议先回答总体架构，再从某个细节出发作为突破口，比如这样：
ArrayList 底层数据结构是个数组，其 API 都做了一层对数组底层访问的封装，比如说 add 方法的过程是……（这里可以引用我们在 ArrayList 源码解析中 add 的过程）。

一般面试官看你回答得井井有条，并且没啥漏洞的话，基本就不会深究了，这样面试的主动权就掌握在自己手里面了，如果你回答得支支吾吾，那么面试官可能就会开启自己面试的套路了。

说说你自己对 LinkedList 的理解也是同样套路。



#### 1.2 扩容类问题

##### 1.2.1 ArrayList 无参数构造器构造，现在 add 一个值进去，此时数组的大小是多少，下一次扩容前最大可用大小是多少？

答：此处数组的大小是 1，下一次扩容前最大可用大小是 10，因为 ArrayList 第一次扩容时，是有默认值的，默认值是 10，在第一次 add 一个值进去时，数组的可用大小被扩容到 10 了。

##### 1.2.2 如果我连续往 list 里面新增值，增加到第 11 个的时候，数组的大小是多少？

答：这里的考查点就是扩容的公式，当增加到 11 的时候，此时我们希望数组的大小为 11，但实际上数组的最大容量只有 10，不够了就需要扩容，扩容的公式是：oldCapacity + (oldCapacity>> 1)，oldCapacity 表示数组现有大小，目前场景计算公式是：10 + 10 ／2 = 15，然后我们发现 15 已经够用了，所以数组的大小会被扩容到 15。

##### 1.2.3 数组初始化，被加入一个值后，如果我使用 addAll 方法，一下子加入 15 个值，那么最终数组的大小是多少？

答：第一题中我们已经计算出来数组在加入一个值后，实际大小是 1，最大可用大小是 10 ，现在需要一下子加入 15 个值，那我们期望数组的大小值就是 16，此时数组最大可用大小只有 10，明显不够，需要扩容，扩容后的大小是：10 + 10 ／2 = 15，这时候发现扩容后的大小仍然不到我们期望的值 16，这时候源码中有一种策略如下：

```java
// newCapacity 本次扩容的大小，minCapacity 我们期望的数组最小大小
// 如果扩容后的值 < 我们的期望值，我们的期望值就等于本次扩容的大小
if (newCapacity - minCapacity < 0)
    newCapacity = minCapacity;
```

所以最终数组扩容后的大小为 16。

##### 1.2.4 现在我有一个很大的数组需要拷贝，原数组大小是 5k，请问如何快速拷贝？

答：因为原数组比较大，如果新建新数组的时候，不指定数组大小的话，就会频繁扩容，频繁扩容就会有大量拷贝的工作，造成拷贝的性能低下，所以回答说新建数组时，指定新数组的大小为 5k 即可。

##### 1.2.5 为什么说扩容会消耗性能？

答：扩容底层使用的是 System.arraycopy 方法，会把原数组的数据全部拷贝到新数组上，所以性能消耗比较严重。

##### 1.2.6 源码扩容过程有什么值得借鉴的地方？

答：有两点：

- 是扩容的思想值得学习，通过自动扩容的方式，让使用者不用关心底层数据结构的变化，封装得很好，1.5 倍的扩容速度，可以让扩容速度在前期缓慢上升，在后期增速较快，大部分工作中要求数组的值并不是很大，所以前期增长缓慢有利于节省资源，在后期增速较快时，也可快速扩容。
- 扩容过程中，有数组大小溢出的意识，比如要求扩容后的数组大小，不能小于 0，不能大于 Integer 的最大值。

这两点在我们平时设计和写代码时都可以借鉴。



### 2 删除类问题

##### 2.1 有一个 ArrayList，数据是 2、3、3、3、4，中间有三个 3，现在我通过 for (int i=0;i<list.size ();i++) 的方式，想把值是 3 的元素删除，请问可以删除干净么？最终删除的结果是什么，为什么？删除代码如下：

```java
List<String> list = new ArrayList<String>() {{
  add("2");
  add("3");
  add("3");
  add("3");
  add("4");
}};
for (int i = 0; i < list.size(); i++) {
  if (list.get(i).equals("3")) {
    list.remove(i);
  }
}
```

答：不能删除干净，最终删除的结果是 2、3、4，有一个 3 删除不掉，原因我们看下图：
![图片描述](http://img.mukewang.com/5d5fc748000129db13361068.png)从图中我们可以看到，每次删除一个元素后，该元素后面的元素就会往前移动，而此时循环的 i 在不断地增长，最终会使每次删除 3 的后一个 3 被遗漏，导致删除不掉。

##### 2.2 还是上面的 ArrayList 数组，我们通过增强 for 循环进行删除，可以么？

答：不可以，会报错。因为增强 for 循环过程其实调用的就是迭代器的 next () 方法，当你调用 list#remove () 方法进行删除时，modCount 的值会 +1，而这时候迭代器中的 expectedModCount 的值却没有变，导致在迭代器下次执行 next () 方法时，expectedModCount != modCount 就会报 ConcurrentModificationException 的错误。

##### 2.3 还是上面的数组，如果删除时使用 Iterator.remove () 方法可以删除么，为什么？

答：可以的，因为 Iterator.remove () 方法在执行的过程中，会把最新的 modCount 赋值给 expectedModCount，这样在下次循环过程中，modCount 和 expectedModCount 两者就会相等。

##### 2.4 以上三个问题对于 LinkedList 也是同样的结果么？

答：是的，虽然 LinkedList 底层结构是双向链表，但对于上述三个问题，结果和 ArrayList 是一致的。



### 3 对比类问题

##### 3.1 ArrayList 和 LinkedList 有何不同？

答：可以先从底层数据结构开始说起，然后以某一个方法为突破口深入，比如：最大的不同是两者底层的数据结构不同，ArrayList 底层是数组，LinkedList 底层是双向链表，两者的数据结构不同也导致了操作的 API 实现有所差异，拿新增实现来说，ArrayList 会先计算并决定是否扩容，然后把新增的数据直接赋值到数组上，而 LinkedList 仅仅只需要改变插入节点和其前后节点的指向位置关系即可。

##### 3.2 ArrayList 和 LinkedList 应用场景有何不同

答：ArrayList 更适合于快速的查找匹配，不适合频繁新增删除，像工作中经常会对元素进行匹配查询的场景比较合适，LinkedList 更适合于经常新增和删除，对查询反而很少的场景。

##### 3.3 ArrayList 和 LinkedList 两者有没有最大容量

答：ArrayList 有最大容量的，为 Integer 的最大值，大于这个值 JVM 是不会为数组分配内存空间的，LinkedList 底层是双向链表，理论上可以无限大。但源码中，LinkedList 实际大小用的是 int 类型，这也说明了 LinkedList 不能超过 Integer 的最大值，不然会溢出。

##### 3.4 ArrayList 和 LinkedList 是如何对 null 值进行处理的

答：ArrayList 允许 null 值新增，也允许 null 值删除。删除 null 值时，是从头开始，找到第一值是 null 的元素删除；LinkedList 新增删除时对 null 值没有特殊校验，是允许新增和删除的。

##### 3.5 ArrayList 和 LinedList 是线程安全的么，为什么？

答：当两者作为非共享变量时，比如说仅仅是在方法里面的局部变量时，是没有线程安全问题的，只有当两者是共享变量时，才会有线程安全问题。主要的问题点在于多线程环境下，所有线程任何时刻都可对数组和链表进行操作，这会导致值被覆盖，甚至混乱的情况。

如果有线程安全问题，在迭代的过程中，会频繁报 ConcurrentModificationException 的错误，意思是在我当前循环的过程中，数组或链表的结构被其它线程修改了。

##### 3.6 如何解决线程安全问题？

Java 源码中推荐使用 Collections#synchronizedList 进行解决，Collections#synchronizedList 的返回值是 List 的每个方法都加了 synchronized 锁，保证了在同一时刻，数组和链表只会被一个线程所修改，或者采用 CopyOnWriteArrayList 并发 List 来解决，这个类我们后面会说。



### 4 其它类型题目

##### 4.1 你能描述下双向链表么？

答：如果和面试官面对面沟通的话，你可以去画一下，可以把 《LinkedList 源码解析》中的 LinkedList 的结构画出来，如果是电话面试，可以这么描述：双向链表中双向的意思是说前后节点之间互相有引用，链表的节点我们称为 Node。Node 有三个属性组成：其前一个节点，本身节点的值，其下一个节点，假设 A、B 节点相邻，A 节点的下一个节点就是 B，B 节点的上一个节点就是 A，两者互相引用，在链表的头部节点，我们称为头节点。头节点的前一个节点是 null，尾部称为尾节点，尾节点的后一个节点是 null，如果链表数据为空的话，头尾节点是同一个节点，本身是 null，指向前后节点的值也是 null。

##### 4.2 描述下双向链表的新增和删除

答：如果是面对面沟通，最好可以直接画图，如果是电话面试，可以这么描述：

新增：我们可以选择从链表头新增，也可以选择从链表尾新增，如果是从链表尾新增的话，直接把当前节点追加到尾节点之后，本身节点自动变为尾节点。

删除：把删除节点的后一个节点的 prev 指向其前一个节点，把删除节点的前一个节点的 next 指向其后一个节点，最后把删除的节点置为 null 即可。



### 总结

List 在工作中经常遇到，熟读源码不仅仅是为了应对面试，也为了在工作中使用起来得心应手，如果想更深入了解 List，可以看一遍 ArrayList 源码之后，自己重新实现一个 List。这样的话，就会对 List 底层的数据结构和操作细节理解更深。

## **08 HashMap 源码解析**

### 引导语

HashMap 源码很长，面试的问题也非常多，但这些面试问题，基本都是从源码中衍生出来的，所以我们只需要弄清楚其底层实现原理，回答这些问题就会游刃有余。



### 1 整体架构

HashMap 底层的数据结构主要是：数组 + 链表 + 红黑树。其中当链表的长度大于等于 8 时，链表会转化成红黑树，当红黑树的大小小于等于 6 时，红黑树会转化成链表，整体的数据结构如下：
![图片描述](http://img.mukewang.com/5d5fc7cc0001ec3211040928.png)图中左边竖着的是 HashMap 的数组结构，数组的元素可能是单个 Node，也可能是个链表，也可能是个红黑树，比如数组下标索引为 2 的位置就是一个链表，下标索引为 9 的位置对应的就是红黑树，具体细节我们下文再说。



#### 1.1 类注释

从 HashMap 的类注释中，我们可以得到如下信息：

- 允许 null 值，不同于 HashTable ，是线程不安全的；
- load factor（影响因子） 默认值是 0.75， 是均衡了时间和空间损耗算出来的值，较高的值会减少空间开销（扩容减少，数组大小增长速度变慢），但增加了查找成本（hash 冲突增加，链表长度变长），不扩容的条件：数组容量 > 需要的数组大小 /load factor；
- 如果有很多数据需要储存到 HashMap 中，建议 HashMap 的容量一开始就设置成足够的大小，这样可以防止在其过程中不断的扩容，影响性能；
- HashMap 是非线程安全的，我们可以自己在外部加锁，或者通过 Collections#synchronizedMap 来实现线程安全，Collections#synchronizedMap 的实现是在每个方法上加上了 synchronized 锁；
- 在迭代过程中，如果 HashMap 的结构被修改，会快速失败。



#### 1.2 常见属性

```java
 //初始容量为 16
 static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

 //最大容量
 static final int MAXIMUM_CAPACITY = 1 << 30;

 //负载因子默认值
 static final float DEFAULT_LOAD_FACTOR = 0.75f;
 
 //桶上的链表长度大于等于8时，链表转化成红黑树
 static final int TREEIFY_THRESHOLD = 8;

 //桶上的红黑树大小小于等于6时，红黑树转化成链表
 static final int UNTREEIFY_THRESHOLD = 6;

 //当数组容量大于 64 时，链表才会转化成红黑树
 static final int MIN_TREEIFY_CAPACITY = 64;

 //记录迭代过程中 HashMap 结构是否发生变化，如果有变化，迭代时会 fail-fast
 transient int modCount;

 //HashMap 的实际大小，可能不准(因为当你拿到这个值的时候，可能又发生了变化)
 transient int size;

 //存放数据的数组
 transient Node<K,V>[] table;

 // 扩容的门槛，有两种情况
 // 如果初始化时，给定数组大小的话，通过 tableSizeFor 方法计算，数组大小永远接近于 2 的幂次方，比如你给定初始化大小 19，实际上初始化大小为 32，为 2 的 5 次方。
 // 如果是通过 resize 方法进行扩容，大小 = 数组容量 * 0.75
 int threshold;

 //链表的节点
 static class Node<K,V> implements Map.Entry<K,V> {
 
 //红黑树的节点
 static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
```



### 2 新增

新增 key，value 大概的步骤如下：

1. 空数组有无初始化，没有的话初始化；
2. 如果通过 key 的 hash 能够直接找到值，跳转到 6，否则到 3；
3. 如果 hash 冲突，两种解决方案：链表 or 红黑树；
4. 如果是链表，递归循环，把新元素追加到队尾；
5. 如果是红黑树，调用红黑树新增的方法；
6. 通过 2、4、5 将新元素追加成功，再根据 onlyIfAbsent 判断是否需要覆盖；
7. 判断是否需要扩容，需要扩容进行扩容，结束。

我们来画一张示意图来描述下：
![图片描述](http://img.mukewang.com/5d5fc7e200016af809121188.jpg)代码细节如下：

```java
// 入参 hash：通过 hash 算法计算出来的值。
// 入参 onlyIfAbsent：false 表示即使 key 已经存在了，仍然会用新值覆盖原来的值，默认为 false
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    // n 表示数组的长度，i 为数组索引下标，p 为 i 下标位置的 Node 值
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //如果数组为空，使用 resize 方法初始化
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // 如果当前索引位置是空的，直接生成新的节点在当前索引位置上
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    // 如果当前索引位置有值的处理方法，即我们常说的如何解决 hash 冲突
    else {
        // e 当前节点的临时变量
        Node<K,V> e; K k;
        // 如果 key 的 hash 和值都相等，直接把当前下标位置的 Node 值赋值给临时变量
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // 如果是红黑树，使用红黑树的方式新增
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        // 是个链表，把新节点放到链表的尾端
        else {
            // 自旋
            for (int binCount = 0; ; ++binCount) {
                // e = p.next 表示从头开始，遍历链表
                // p.next == null 表明 p 是链表的尾节点
                if ((e = p.next) == null) {
                    // 把新节点放到链表的尾部 
                    p.next = newNode(hash, key, value, null);
                    // 当链表的长度大于等于 8 时，链表转红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1)
                        treeifyBin(tab, hash);
                    break;
                }
                // 链表遍历过程中，发现有元素和新增的元素相等，结束循环
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                //更改循环的当前元素，使 p 在遍历过程中，一直往后移动。
                p = e;
            }
        }
        // 说明新节点的新增位置已经找到了
        if (e != null) {
            V oldValue = e.value;
            // 当 onlyIfAbsent 为 false 时，才会覆盖值 
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            // 返回老值
            return oldValue;
        }
    }
    // 记录 HashMap 的数据结构发生了变化
    ++modCount;
    //如果 HashMap 的实际大小大于扩容的门槛，开始扩容
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

新增的流程上面应该已经表示很清楚了，接下来我们来看看链表和红黑树新增的细节：



#### 2.1 链表的新增

链表的新增比较简单，就是把当前节点追加到链表的尾部，和 LinkedList 的追加实现一样的。

当链表长度大于等于 8 时，此时的链表就会转化成红黑树，转化的方法是：treeifyBin，此方法有一个判断，当链表长度大于等于 8，并且整个数组大小大于 64 时，才会转成红黑树，当数组大小小于 64 时，只会触发扩容，不会转化成红黑树，转化成红黑树的过程也比较简单，具体转化的过程源码可以去 github：https://github.com/luanqiu/java8 上面去查看。

可能面试的时候，有人问你为什么是 8，这个答案在源码中注释有说，中文翻译过来大概的意思是：

链表查询的时间复杂度是 O (n)，红黑树的查询复杂度是 O (log (n))。在链表数据不多的时候，使用链表进行遍历也比较快，只有当链表数据比较多的时候，才会转化成红黑树，但红黑树需要的占用空间是链表的 2 倍，考虑到转化时间和空间损耗，所以我们需要定义出转化的边界值。

在考虑设计 8 这个值的时候，我们参考了泊松分布概率函数，由泊松分布中得出结论，链表各个长度的命中概率为：

```java
* 0:    0.60653066
* 1:    0.30326533
* 2:    0.07581633
* 3:    0.01263606
* 4:    0.00157952
* 5:    0.00015795
* 6:    0.00001316
* 7:    0.00000094
* 8:    0.00000006
```

意思是，当链表的长度是 8 的时候，出现的概率是 0.00000006，不到千万分之一，所以说正常情况下，链表的长度不可能到达 8 ，而一旦到达 8 时，肯定是 hash 算法出了问题，所以在这种情况下，为了让 HashMap 仍然有较高的查询性能，所以让链表转化成红黑树，我们正常写代码，使用 HashMap 时，几乎不会碰到链表转化成红黑树的情况，毕竟概念只有千万分之一。



#### 2.2 红黑树新增节点过程

1. 首先判断新增的节点在红黑树上是不是已经存在，判断手段有如下两种：

   1.1. 如果节点没有实现 Comparable 接口，使用 equals 进行判断；

   1.2. 如果节点自己实现了 Comparable 接口，使用 compareTo 进行判断。

2. 新增的节点如果已经在红黑树上，直接返回；不在的话，判断新增节点是在当前节点的左边还是右边，左边值小，右边值大；

3. 自旋递归 1 和 2 步，直到当前节点的左边或者右边的节点为空时，停止自旋，当前节点即为我们新增节点的父节点；

4. 把新增节点放到当前节点的左边或右边为空的地方，并于当前节点建立父子节点关系；

5. 进行着色和旋转，结束。

具体源码如下：

```java
//入参 h：key 的hash值
final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                               int h, K k, V v) {
    Class<?> kc = null;
    boolean searched = false;
    //找到根节点
    TreeNode<K,V> root = (parent != null) ? root() : this;
    //自旋
    for (TreeNode<K,V> p = root;;) {
        int dir, ph; K pk;
        // p hash 值大于 h，说明 p 在 h 的右边
        if ((ph = p.hash) > h)
            dir = -1;
        // p hash 值小于 h，说明 p 在 h 的左边
        else if (ph < h)
            dir = 1;
        //要放进去key在当前树中已经存在了(equals来判断)
        else if ((pk = p.key) == k || (k != null && k.equals(pk)))
            return p;
        //自己实现的Comparable的话，不能用hashcode比较了，需要用compareTo
        else if ((kc == null &&
                  //得到key的Class类型，如果key没有实现Comparable就是null
                  (kc = comparableClassFor(k)) == null) ||
                  //当前节点pk和入参k不等
                 (dir = compareComparables(kc, k, pk)) == 0) {
            if (!searched) {
                TreeNode<K,V> q, ch;
                searched = true;
                if (((ch = p.left) != null &&
                     (q = ch.find(h, k, kc)) != null) ||
                    ((ch = p.right) != null &&
                     (q = ch.find(h, k, kc)) != null))
                    return q;
            }
            dir = tieBreakOrder(k, pk);
        }

        TreeNode<K,V> xp = p;
        //找到和当前hashcode值相近的节点(当前节点的左右子节点其中一个为空即可)
        if ((p = (dir <= 0) ? p.left : p.right) == null) {
            Node<K,V> xpn = xp.next;
            //生成新的节点
            TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
            //把新节点放在当前子节点为空的位置上
            if (dir <= 0)
                xp.left = x;
            else
                xp.right = x;
            //当前节点和新节点建立父子，前后关系
            xp.next = x;
            x.parent = x.prev = xp;
            if (xpn != null)
                ((TreeNode<K,V>)xpn).prev = x;
            //balanceInsertion 对红黑树进行着色或旋转，以达到更多的查找效率，着色或旋转的几种场景如下
            //着色：新节点总是为红色；如果新节点的父亲是黑色，则不需要重新着色；如果父亲是红色，那么必须通过重新着色或者旋转的方法，再次达到红黑树的5个约束条件
            //旋转： 父亲是红色，叔叔是黑色时，进行旋转
            //如果当前节点是父亲的右节点，则进行左旋
            //如果当前节点是父亲的左节点，则进行右旋
          
            //moveRootToFront 方法是把算出来的root放到根节点上
            moveRootToFront(tab, balanceInsertion(root, x));
            return null;
        }
    }
}
```

红黑树的新增，要求大家对红黑树的数据结构有一定的了解。面试的时候，一般只会问到新增节点到红黑树上大概是什么样的一个过程，着色和旋转的细节不会问，因为很难说清楚，但我们要清楚着色指的是给红黑树的节点着上红色或黑色，旋转是为了让红黑树更加平衡，提高查询的效率，总的来说都是为了满足红黑树的 5 个原则：

1. 节点是红色或黑色
2. 根是黑色
3. 所有叶子都是黑色
4. 从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点
5. 从每个叶子到根的所有路径上不能有两个连续的红色节点



### 3 查找

HashMap 的查找主要分为以下三步：

- 根据 hash 算法定位数组的索引位置，equals 判断当前节点是否是我们需要寻找的 key，是的话直接返回，不是的话往下。
- 判断当前节点有无 next 节点，有的话判断是链表类型，还是红黑树类型。
- 分别走链表和红黑树不同类型的查找方法。

链表查找的关键代码是：

```java
// 采用自旋方式从链表中查找 key，e 初始为为链表的头节点
do {
    // 如果当前节点 hash 等于 key 的 hash，并且 equals 相等，当前节点就是我们要找的节点
    // 当 hash 冲突时，同一个 hash 值上是一个链表的时候，我们是通过 equals 方法来比较 key 是否相等的
    if (e.hash == hash &&
        ((k = e.key) == key || (key != null && key.equals(k))))
        return e;
    // 否则，把当前节点的下一个节点拿出来继续寻找
} while ((e = e.next) != null);
```

红黑树查找的代码很多，我们大概说下思路，实际步骤比较复杂，可以去 github 上面去查看源码：

1. 从根节点递归查找；
2. 根据 hashcode，比较查找节点，左边节点，右边节点之间的大小，根本红黑树左小右大的特性进行判断；
3. 判断查找节点在第 2 步有无定位节点位置，有的话返回，没有的话重复 2，3 两步；
4. 一直自旋到定位到节点位置为止。

如果红黑树比较平衡的话，每次查找的次数就是树的深度。



### 总结

HashMap 的内容虽然较多，但大多数 api 都只是对数组 + 链表 + 红黑树这种数据结构进行封装而已，本小节我们从新增和查找两个角度进行了源码的深入分析，分析了是如何对数组、链表和红黑树进行操作的。想了解更多，可以前往 github 上查看更多源码。

## **09 TreeMap 和 LinkedHashMap 核心源码解析**

### 引导语

在熟悉 HashMap 之后，本小节我们来看下 TreeMap 和 LinkedHashMap，看看 TreeMap 是如何根据 key 进行排序的，LinkedHashMap 是如何用两种策略进行访问的。



### 1 知识储备

在了解 TreeMap 之前，我们来看下日常工作中排序的两种方式，作为我们学习的基础储备，两种方式的代码如下：

```java
public class TreeMapDemo {

  @Data
  // DTO 为我们排序的对象
  class DTO implements Comparable<DTO> {
    private Integer id;
    public DTO(Integer id) {
      this.id = id;
    }

    @Override
    public int compareTo(DTO o) {
      //默认从小到大排序
      return id - o.getId();
    }
  }

  @Test
  public void testTwoComparable() {
    // 第一种排序，从小到大排序，实现 Comparable 的 compareTo 方法进行排序
    List<DTO> list = new ArrayList<>();
    for (int i = 5; i > 0; i--) {
      list.add(new DTO(i));
    }
    Collections.sort(list);
    log.info(JSON.toJSONString(list));

    // 第二种排序，从大到小排序，利用外部排序器 Comparator 进行排序
    Comparator comparator = (Comparator<DTO>) (o1, o2) -> o2.getId() - o1.getId();
    List<DTO> list2 = new ArrayList<>();
    for (int i = 5; i > 0; i--) {
      list2.add(new DTO(i));
    }
    Collections.sort(list,comparator);
    log.info(JSON.toJSONString(list2));
  }
}
```

第一种排序输出的结果从小到大，结果是：[{“id”:1},{“id”:2},{“id”:3},{“id”:4},{“id”:5}]；

第二种输出的结果恰好相反，结果是：[{“id”:5},{“id”:4},{“id”:3},{“id”:2},{“id”:1}]。

以上两种就是分别通过 Comparable 和 Comparator 两者进行排序的方式，而 TreeMap 利用的也是此原理，从而实现了对 key 的排序，我们一起来看下。



### 2 TreeMap 整体架构

TreeMap 底层的数据结构就是红黑树，和 HashMap 的红黑树结构一样。

不同的是，TreeMap 利用了红黑树左节点小，右节点大的性质，根据 key 进行排序，使每个元素能够插入到红黑树大小适当的位置，维护了 key 的大小关系，适用于 key 需要排序的场景。

因为底层使用的是平衡红黑树的结构，所以 containsKey、get、put、remove 等方法的时间复杂度都是 log(n)。

#### 2.1 属性

TreeMap 常见的属性有：

```java
//比较器，如果外部有传进来 Comparator 比较器，首先用外部的
//如果外部比较器为空，则使用 key 自己实现的 Comparable#compareTo 方法
//比较手段和上面日常工作中的比较 demo 是一致的
private final Comparator<? super K> comparator;

//红黑树的根节点
private transient Entry<K,V> root;

//红黑树的已有元素大小
private transient int size = 0;

//树结构变化的版本号，用于迭代过程中的快速失败场景
private transient int modCount = 0;

//红黑树的节点
static final class Entry<K,V> implements Map.Entry<K,V> {}
```

#### 2.2 新增节点

我们来看下 TreeMap 新增节点的步骤：

1. 判断红黑树的节点是否为空，为空的话，新增的节点直接作为根节点，代码如下：

   ```java
   Entry<K,V> t = root;
   //红黑树根节点为空，直接新建
   if (t == null) {
       // compare 方法限制了 key 不能为 null
       compare(key, key); // type (and possibly null) check
       // 成为根节点
       root = new Entry<>(key, value, null);
       size = 1;
       modCount++;
       return null;
   }
   ```

2. 根据红黑树左小右大的特性，进行判断，找到应该新增节点的父节点，代码如下：

   ```java
   Comparator<? super K> cpr = comparator;
   if (cpr != null) {
       //自旋找到 key 应该新增的位置，就是应该挂载那个节点的头上
       do {
           //一次循环结束时，parent 就是上次比过的对象
           parent = t;
           // 通过 compare 来比较 key 的大小
           cmp = cpr.compare(key, t.key);
           //key 小于 t，把 t 左边的值赋予 t，因为红黑树左边的值比较小，循环再比
           if (cmp < 0)
               t = t.left;
           //key 大于 t，把 t 右边的值赋予 t，因为红黑树右边的值比较大，循环再比
           else if (cmp > 0)
               t = t.right;
           //如果相等的话，直接覆盖原值
           else
               return t.setValue(value);
           // t 为空，说明已经到叶子节点了
       } while (t != null);
   }
   ```

3. 在父节点的左边或右边插入新增节点，代码如下：

   ```java
   //cmp 代表最后一次对比的大小，小于 0 ，代表 e 在上一节点的左边
   if (cmp < 0)
       parent.left = e;
   //cmp 代表最后一次对比的大小，大于 0 ，代表 e 在上一节点的右边，相等的情况第二步已经处理了。
   else
       parent.right = e;
   ```

4. 着色旋转，达到平衡，结束。

从源码中，我们可以看到：

1. 新增节点时，就是利用了红黑树左小右大的特性，从根节点不断往下查找，直到找到节点是 null 为止，节点为 null 说明到达了叶子结点；
2. 查找过程中，发现 key 值已经存在，直接覆盖；
3. TreeMap 是禁止 key 是 null 值的。

类似的，TreeMap 查找也是类似的原理，有兴趣的同学可以去 github 上面去查看源码。

#### 2.3 小结

TreeMap 相对来说比较简单，红黑树和 HashMap 比较类似，比较关键的是通过 compare 来比较 key 的大小，然后利用红黑树左小右大的特性，为每个 key 找到自己的位置，从而维护了 key 的大小排序顺序。



### 3 LinkedHashMap 整体架构

HashMap 是无序的，TreeMap 可以按照 key 进行排序，那有木有 Map 是可以维护插入的顺序的呢？接下来我们一起来看下 LinkedHashMap。

LinkedHashMap 本身是继承 HashMap 的，所以它拥有 HashMap 的所有特性，再此基础上，还提供了两大特性：

- 按照插入顺序进行访问；
- 实现了访问最少最先删除功能，其目的是把很久都没有访问的 key 自动删除。

接着我们来看下上述两大特性。

#### 3.1 按照插入顺序访问

##### 3.1.1 LinkedHashMap 链表结构

我们看下 LinkedHashMap 新增了哪些属性，以达到了链表结构的：

```java
// 链表头
transient LinkedHashMap.Entry<K,V> head;

// 链表尾
transient LinkedHashMap.Entry<K,V> tail;

// 继承 Node，为数组的每个元素增加了 before 和 after 属性
static class Entry<K,V> extends HashMap.Node<K,V> {
    Entry<K,V> before, after;
    Entry(int hash, K key, V value, Node<K,V> next) {
        super(hash, key, value, next);
    }
}

// 控制两种访问模式的字段，默认 false
// true 按照访问顺序，会把经常访问的 key 放到队尾
// false 按照插入顺序提供访问
final boolean accessOrder;
```

从上述 Map 新增的属性可以看到，LinkedHashMap 的数据结构很像是把 LinkedList 的每个元素换成了 HashMap 的 Node，像是两者的结合体，也正是因为增加了这些结构，从而能把 Map 的元素都串联起来，形成一个链表，而链表就可以保证顺序了，就可以维护元素插入进来的顺序。

##### 3.1.2 如何按照顺序新增

LinkedHashMap 初始化时，默认 accessOrder 为 false，就是会按照插入顺序提供访问，插入方法使用的是父类 HashMap 的 put 方法，不过覆写了 put 方法执行中调用的 newNode/newTreeNode 和 afterNodeAccess 方法。

newNode/newTreeNode 方法，控制新增节点追加到链表的尾部，这样每次新节点都追加到尾部，即可保证插入顺序了，我们以 newNode 源码为例：

```java
// 新增节点，并追加到链表的尾部
Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
    // 新增节点
    LinkedHashMap.Entry<K,V> p =
        new LinkedHashMap.Entry<K,V>(hash, key, value, e);
    // 追加到链表的尾部
    linkNodeLast(p);
    return p;
}
// link at the end of list
private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
    LinkedHashMap.Entry<K,V> last = tail;
    // 新增节点等于位节点
    tail = p;
    // last 为空，说明链表为空，首尾节点相等
    if (last == null)
        head = p;
    // 链表有数据，直接建立新增节点和上个尾节点之间的前后关系即可
    else {
        p.before = last;
        last.after = p;
    }
}
```

LinkedHashMap 通过新增头节点、尾节点，给每个节点增加 before、after 属性，每次新增时，都把节点追加到尾节点等手段，在新增的时候，就已经维护了按照插入顺序的链表结构了。

##### 3.1.3 按照顺序访问

LinkedHashMap 只提供了单向访问，即按照插入的顺序从头到尾进行访问，不能像 LinkedList 那样可以双向访问。

我们主要通过迭代器进行访问，迭代器初始化的时候，默认从头节点开始访问，在迭代的过程中，不断访问当前节点的 after 节点即可。

Map 对 key、value 和 entity（节点） 都提供出了迭代的方法，假设我们需要迭代 entity，就可使用 `LinkedHashMap.entrySet().iterator()` 这种写法直接返回 LinkedHashIterator ，LinkedHashIterator 是迭代器，我们调用迭代器的 nextNode 方法就可以得到下一个节点，迭代器的源码如下：

```java
// 初始化时，默认从头节点开始访问
LinkedHashIterator() {
    // 头节点作为第一个访问的节点
    next = head;
    expectedModCount = modCount;
    current = null;
}

final LinkedHashMap.Entry<K,V> nextNode() {
    LinkedHashMap.Entry<K,V> e = next;
    if (modCount != expectedModCount)// 校验
        throw new ConcurrentModificationException();
    if (e == null)
        throw new NoSuchElementException();
    current = e;
    next = e.after; // 通过链表的 after 结构，找到下一个迭代的节点
    return e;
}
```

在新增节点时，我们就已经维护了元素之间的插入顺序了，所以迭代访问时非常简单，只需要不断的访问当前节点的下一个节点即可。

#### 3.2 访问最少删除策略

##### 3.2.1 demo

这种策略也叫做 LRU（Least recently used,最近最少使用），大概的意思就是经常访问的元素会被追加到队尾，这样不经常访问的数据自然就靠近队头，然后我们可以通过设置删除策略，比如当 Map 元素个数大于多少时，把头节点删除，我们写个 demo 方便大家理解。demo 如下，完整代码可到 github 上查看：

```java
public void testAccessOrder() {
  // 新建 LinkedHashMap
  LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>(4,0.75f,true) {
    {
      put(10, 10);
      put(9, 9);
      put(20, 20);
      put(1, 1);
    }

    @Override
    // 覆写了删除策略的方法，我们设定当节点个数大于 3 时，就开始删除头节点
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
      return size() > 3;
    }
  };

  log.info("初始化：{}",JSON.toJSONString(map));
  Assert.assertNotNull(map.get(9));
  log.info("map.get(9)：{}",JSON.toJSONString(map));
  Assert.assertNotNull(map.get(20));
  log.info("map.get(20)：{}",JSON.toJSONString(map));

}
```

打印出来的结果如下：

```java
初始化：{9:9,20:20,1:1}
map.get(9)：{20:20,1:1,9:9}
map.get(20)：{1:1,9:9,20:20}
```

可以看到，map 初始化的时候，我们放进去四个元素，但结果只有三个元素，10 不见了，这个主要是因为我们覆写了 removeEldestEntry 方法，我们实现了如果 map 中元素个数大于 3 时，我们就把队头的元素删除，当 put(1, 1) 执行的时候，正好把队头的 10 删除，这个体现了达到我们设定的删除策略时，会自动的删除头节点。

当我们调用 map.get(9) 方法时，元素 9 移动到队尾，调用 map.get(20) 方法时， 元素 20 被移动到队尾，这个体现了经常被访问的节点会被移动到队尾。

这个例子就很好的说明了访问最少删除策略，接下来我们看下原理。

##### 3.2.2 元素被转移到队尾

我们先来看下为什么 get 时，元素会被移动到队尾：

```java
public V get(Object key) {
    Node<K,V> e;
    // 调用 HashMap  get 方法
    if ((e = getNode(hash(key), key)) == null)
        return null;
    // 如果设置了 LRU 策略
    if (accessOrder)
    // 这个方法把当前 key 移动到队尾
        afterNodeAccess(e);
    return e.value;
}
```

从上述源码中，可以看到，通过 afterNodeAccess 方法把当前访问节点移动到了队尾，其实不仅仅是 get 方法，执行 getOrDefault、compute、computeIfAbsent、computeIfPresent、merge 方法时，也会这么做，通过不断的把经常访问的节点移动到队尾，那么靠近队头的节点，自然就是很少被访问的元素了。

##### 3.2.3 删除策略

上述 demo 我们在执行 put 方法时，发现队头元素被删除了，LinkedHashMap 本身是没有 put 方法实现的，调用的是 HashMap 的 put 方法，但 LinkedHashMap 实现了 put 方法中的调用 afterNodeInsertion 方法，这个方式实现了删除，我们看下源码：

```java
// 删除很少被访问的元素，被 HashMap 的 put 方法所调用
void afterNodeInsertion(boolean evict) { 
    // 得到元素头节点
    LinkedHashMap.Entry<K,V> first;
    // removeEldestEntry 来控制删除策略，如果队列不为空，并且删除策略允许删除的情况下，删除头节点
    if (evict && (first = head) != null && removeEldestEntry(first)) {
        K key = first.key;
        // removeNode 删除头节点
        removeNode(hash(key), key, null, false, true);
    }
}
```

##### 3.3 小结

LinkedHashMap 提供了两个很有意思的功能：按照插入顺序访问和删除最少访问元素策略，简单地通过链表的结构就实现了，设计得非常巧妙。



### 总结

本小节主要说了 TreeMap 和 LinkedHashMap 的的数据结构，分析了两者的核心内容源码，我们发现两者充分利用了底层数据结构的特性，TreeMap 利用了红黑树左小右大的特性进行排序，LinkedHashMap 在 HashMap 的基础上简单地加了链表结构，就形成了节点的顺序，非常巧妙，很有意思，大家可以在看源码的过程中，可以多想想设计思路，说不定会有不一样的感悟。

## **10 Map源码会问哪些面试题**

### 引导语

Map 在面试中，占据了很大一部分的面试题目，其中以 HashMap 为主，这些面试题目有的可以说得清楚，有的很难说清楚，如果是面对面面试的话，建议画一画。



### 1 Map 整体数据结构类问题



#### 1.1 说一说 HashMap 底层数据结构

答：HashMap 底层是数组 + 链表 + 红黑树的数据结构，数组的主要作用是方便快速查找，时间复杂度是 O(1)，默认大小是 16，数组的下标索引是通过 key 的 hashcode 计算出来的，数组元素叫做 Node，当多个 key 的 hashcode 一致，但 key 值不同时，单个 Node 就会转化成链表，链表的查询复杂度是 O(n)，当链表的长度大于等于 8 并且数组的大小超过 64 时，链表就会转化成红黑树，红黑树的查询复杂度是 O(log(n))，简单来说，最坏的查询次数相当于红黑树的最大深度。



#### 1.2 HashMap、TreeMap、LinkedHashMap 三者有啥相同点，有啥不同点？

答：相同点：

1. 三者在特定的情况下，都会使用红黑树；
2. 底层的 hash 算法相同；
3. 在迭代的过程中，如果 Map 的数据结构被改动，都会报 ConcurrentModificationException 的错误。

不同点：

1. HashMap 数据结构以数组为主，查询非常快，TreeMap 数据结构以红黑树为主，利用了红黑树左小右大的特点，可以实现 key 的排序，LinkedHashMap 在 HashMap 的基础上增加了链表的结构，实现了插入顺序访问和最少访问删除两种策略;
2. 由于三种 Map 底层数据结构的差别，导致了三者的使用场景的不同，TreeMap 适合需要根据 key 进行排序的场景，LinkedHashMap 适合按照插入顺序访问，或需要删除最少访问元素的场景，剩余场景我们使用 HashMap 即可，我们工作中大部分场景基本都在使用 HashMap；
3. 由于三种 map 的底层数据结构的不同，导致上层包装的 api 略有差别。



#### 1.3 说一下 Map 的 hash 算法

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
key 在数组中的位置公式：tab[(n - 1) & hash]
```

如上代码是 HashMap 的hash 算法。

这其实是一个数学问题，源码中就是通过以上代码来计算 hash 的，首先计算出 key 的 hashcode，因为 key 是 Object，所以会根据 key 的不同类型进行 hashcode 的计算，接着计算 h ^ (h >>> 16) ，这么做的好处是使大多数场景下，算出来的 hash 值比较分散。

一般来说，hash 值算出来之后，要计算当前 key 在数组中的索引下标位置时，可以采用取模的方式，就是索引下标位置 = hash 值 % 数组大小，这样做的好处，就是可以保证计算出来的索引下标值可以均匀的分布在数组的各个索引位置上，但取模操作对于处理器的计算是比较慢的，数学上有个公式，当 b 是 2 的幂次方时，a % b = a &（b-1），所以此处索引位置的计算公式我们可以更换为： (n-1) & hash。

此问题可以延伸出三个小问题：

1：为什么不用 key % 数组大小，而是需要用 key 的 hash 值 % 数组大小。

答：如果 key 是数字，直接用 key % 数组大小是完全没有问题的，但我们的 key 还有可能是字符串，是复杂对象，这时候用 字符串或复杂对象 % 数组大小是不行的，所以需要先计算出 key 的 hash 值。

2：计算 hash 值时，为什么需要右移 16 位？

答：hash 算法是 h ^ (h >>> 16)，为了使计算出的 hash 值更分散，所以选择先将 h 无符号右移 16 位，然后再于 h 异或时，就能达到 h 的高 16 位和低 16 位都能参与计算，减少了碰撞的可能性。

3：为什么把取模操作换成了 & 操作？

答：key.hashCode() 算出来的 hash 值还不是数组的索引下标，为了随机的计算出索引的下表位置，我们还会用 hash 值和数组大小进行取模，这样子计算出来的索引下标比较均匀分布。

取模操作处理器计算比较慢，处理器对 & 操作就比较擅长，换成了 & 操作，是有数学上证明的支撑，为了提高了处理器处理的速度。

4：为什么提倡数组大小是 2 的幂次方？

答：因为只有大小是 2 的幂次方时，才能使 hash 值 % n(数组大小) == (n-1) & hash 公式成立。



#### 1.4 为解决 hash 冲突，大概有哪些办法。

答：1：好的 hash 算法，细问的话复述一下上题的 hash 算法;

2：自动扩容，当数组大小快满的时候，采取自动扩容，可以减少 hash 冲突;

3：hash 冲突发生时，采用链表来解决;

4：hash 冲突严重时，链表会自动转化成红黑树，提高遍历速度。

网上列举的一些其它办法，如开放定址法，尽量不要说，因为这些方法资料很少，实战用过的人更少，如果你没有深入研究的话，面试官让你深入描述一下很难说清楚，反而留下不好的印象，说 HashMap 现有的措施就足够了。



### 2 HashMap 源码细节类问题



#### 2.1 HashMap 是如何扩容的？

答：扩容的时机：

1. put 时，发现数组为空，进行初始化扩容，默认扩容大小为 16;
2. put 成功后，发现现有数组大小大于扩容的门阀值时，进行扩容，扩容为老数组大小的 2 倍;

扩容的门阀是 threshold，每次扩容时 threshold 都会被重新计算，门阀值等于数组的大小 * 影响因子（0.75）。

新数组初始化之后，需要将老数组的值拷贝到新数组上，链表和红黑树都有自己拷贝的方法。



#### 2.2 hash 冲突时怎么办？

答：hash 冲突指的是 key 值的 hashcode 计算相同，但 key 值不同的情况。

如果桶中元素原本只有一个或已经是链表了，新增元素直接追加到链表尾部；

如果桶中元素已经是链表，并且链表个数大于等于 8 时，此时有两种情况：

1. 如果此时数组大小小于 64，数组再次扩容，链表不会转化成红黑树;
2. 如果数组大小大于 64 时，链表就会转化成红黑树。

这里不仅仅判断链表个数大于等于 8，还判断了数组大小，数组容量小于 64 没有立即转化的原因，猜测主要是因为红黑树占用的空间比链表大很多，转化也比较耗时，所以数组容量小的情况下冲突严重，我们可以先尝试扩容，看看能否通过扩容来解决冲突的问题。



#### 2.3 为什么链表个数大于等于 8 时，链表要转化成红黑树了？

答：当链表个数太多了，遍历可能比较耗时，转化成红黑树，可以使遍历的时间复杂度降低，但转化成红黑树，有空间和转化耗时的成本，我们通过泊松分布公式计算，正常情况下，链表个数出现 8 的概念不到千万分之一，所以说正常情况下，链表都不会转化成红黑树，这样设计的目的，是为了防止非正常情况下，比如 hash 算法出了问题时，导致链表个数轻易大于等于 8 时，仍然能够快速遍历。

延伸问题：红黑树什么时候转变成链表。

答：当节点的个数小于等于 6 时，红黑树会自动转化成链表，主要还是考虑红黑树的空间成本问题，当节点个数小于等于 6 时，遍历链表也很快，所以红黑树会重新变成链表。



#### 2.4 HashMap 在 put 时，如果数组中已经有了这个 key，我不想把 value 覆盖怎么办？取值时，如果得到的 value 是空时，想返回默认值怎么办？

答：如果数组有了 key，但不想覆盖 value ，可以选择 putIfAbsent 方法，这个方法有个内置变量 onlyIfAbsent，内置是 true ，就不会覆盖，我们平时使用的 put 方法，内置 onlyIfAbsent 为 false，是允许覆盖的。

取值时，如果为空，想返回默认值，可以使用 getOrDefault 方法，方法第一参数为 key，第二个参数为你想返回的默认值，如 map.getOrDefault(“2”,“0”)，当 map 中没有 key 为 2 的值时，会默认返回 0，而不是空。



#### 2.5 通过以下代码进行删除，是否可行？

```java
HashMap<String,String > map = Maps.newHashMap();
map.put("1","1");
map.put("2","2");
map.forEach((s, s2) -> map.remove("1"));
```

答：不行，会报错误 ConcurrentModificationException，原因如下图：

![图片描述](http://img.mukewang.com/5d763e330001e64710380560.png)
建议使用迭代器的方式进行删除，原理同 ArrayList 迭代器原理，我们在《List 源码会问那些面试题》中有说到。



#### 2.6 描述一下 HashMap get、put 的过程

答：我们在源码解析中有说，可以详细描述下源码的实现路径，说不清楚的话，可以画一画。



### 3 其它 Map 面试题



#### 3.1 DTO 作为 Map 的 key 时，有无需要注意的点？

答：DTO 就是一个数据载体，可以看做拥有很多属性的 Java 类，我们可以对这些属性进行 get、set 操作。

看是什么类型的 Map，如果是 HashMap 的话，一定需要覆写 equals 和 hashCode 方法，因为在 get 和 put 的时候，需要通过 equals 方法进行相等的判断；如果是 TreeMap 的话，DTO 需要实现 Comparable 接口，因为 TreeMap 会使用 Comparable 接口进行判断 key 的大小；如果是 LinkedHashMap 的话，和 HashMap 一样的。



#### 3.2 LinkedHashMap 中的 LRU 是什么意思，是如何实现的。

答：LRU ，英文全称：Least recently used，中文叫做最近最少访问，在 LinkedHashMap 中，也叫做最少访问删除策略，我们可以通过 removeEldestEntry 方法设定一定的策略，使最少被访问的元素，在适当的时机被删除，原理是在 put 方法执行的最后，LinkedHashMap 会去检查这种策略，如果满足策略，就删除头节点。

保证头节点就是最少访问的元素的原理是：LinkedHashMap 在 get 的时候，都会把当前访问的节点，移动到链表的尾部，慢慢的，就会使头部的节点都是最少被访问的元素。



#### 3.3 为什么推荐 TreeMap 的元素最好都实现 Comparable 接口？但 key 是 String 的时候，我们却没有额外的工作呢？

答：因为 TreeMap 的底层就是通过排序来比较两个 key 的大小的，所以推荐 key 实现 Comparable 接口，是为了往你希望的排序顺序上发展， 而 String 本身已经实现了 Comparable 接口，所以使用 String 时，我们不需要额外的工作，不仅仅是 String ，其他包装类型也都实现了 Comparable 接口，如 Long、Double、Short 等等。



### 总结

Map 的面试题主要是 HashMap 为主，会问很多源码方面的东西，TreeMap 和 LinkedHashMap 主要以功能和场景为主，作为加分项。
Map 的面试题型很多，但只要弄懂原理，题目再多变化，回答起来都会比较简单。

## **11 HashSet、TreeSet 源码解析**

### 引导语

HashSet、TreeSet 两个类是在 Map 的基础上组装起来的类，我们学习的侧重点，主要在于 Set 是如何利用 Map 现有的功能，来达成自己的目标的，也就是说如何基于现有的功能进行创新，然后再看看一些改变的小细节是否值得我们学习。



### 1 HashSet



#### 1.1 类注释

看源码先看类注释上，我们可以得到的信息有：

1. 底层实现基于 HashMap，所以迭代时不能保证按照插入顺序，或者其它顺序进行迭代；
2. add、remove、contanins、size 等方法的耗时性能，是不会随着数据量的增加而增加的，这个主要跟 HashMap 底层的数组数据结构有关，不管数据量多大，不考虑 hash 冲突的情况下，时间复杂度都是 O (1)；
3. 线程不安全的，如果需要安全请自行加锁，或者使用 Collections.synchronizedSet；
4. 迭代过程中，如果数据结构被改变，会快速失败的，会抛出 ConcurrentModificationException 异常。

我们之前也看过 List、Map 的类注释，我们发现 2、3、4 点信息在类注释中都有提到，所以如果有人问 List、Map、 Set 三者的共同点，那么就可以说 2、3、4 三点。



#### 1.2 HashSet 是如何组合 HashMap 的

刚才是从类注释 1 中看到，HashSet 的实现是基于 HashMap 的，在 Java 中，要基于基础类进行创新实现，有两种办法：

- 继承基础类，覆写基础类的方法，比如说继承 HashMap , 覆写其 add 的方法；
- 组合基础类，通过调用基础类的方法，来复用基础类的能力。

HashSet 使用的就是组合 HashMap，其优点如下：

1. 继承表示父子类是同一个事物，而 Set 和 Map 本来就是想表达两种事物，所以继承不妥，而且 Java 语法限制，子类只能继承一个父类，后续难以扩展。
2. 组合更加灵活，可以任意的组合现有的基础类，并且可以在基础类方法的基础上进行扩展、编排等，而且方法命名可以任意命名，无需和基础类的方法名称保持一致。

我们在工作中，如果碰到类似问题，我们的原则也是尽量多用组合，少用继承。

组合就是把 HashMap 当作自己的一个局部变量，以下是 HashSet 的组合实现：

```java
// 把 HashMap 组合进来，key 是 Hashset 的 key，value 是下面的 PRESENT
private transient HashMap<E,Object> map;
// HashMap 中的 value
private static final Object PRESENT = new Object();
```

从这两行代码中，我们可以看出两点：

1. 我们在使用 HashSet 时，比如 add 方法，只有一个入参，但组合的 Map 的 add 方法却有 key，value 两个入参，相对应上 Map 的 key 就是我们 add 的入参，value 就是第二行代码中的 PRESENT，此处设计非常巧妙，用一个默认值 PRESENT 来代替 Map 的 Value；
2. 如果 HashSet 是被共享的，当多个线程访问的时候，就会有线程安全问题，因为在后续的所有操作中，并没有加锁。

HashSet 在以 HashMap 为基础进行实现的时候，首先选择组合的方式，接着使用默认值来代替了 Map 中的 Value 值，设计得非常巧妙，给使用者的体验很好，使用起来简单方便，我们在工作中也可以借鉴这种思想，可以把底层复杂实现包装一下，一些默认实现可以自己吃掉，使吐出去的接口尽量简单好用。

#### 1.2.1 初始化

HashSet 的初始化比较简单，直接 new HashMap 即可，比较有意思的是，当有原始集合数据进行初始化的情况下，会对 HashMap 的初始容量进行计算，源码如下：

```java
// 对 HashMap 的容量进行了计算
public HashSet(Collection<? extends E> c) {
    map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
    addAll(c);
}
```

上述代码中：Math.max ((int) (c.size ()/.75f) + 1, 16)，就是对 HashMap 的容量进行了计算，翻译成中文就是 取括号中两个数的最大值（期望的值 / 0.75+1，默认值 16），从计算中，我们可以看出 HashSet 的实现者对 HashMap 的底层实现是非常清楚的，主要体现在两个方面：

1. 和 16 比较大小的意思是说，如果给定 HashMap 初始容量小于 16 ，就按照 HashMap 默认的 16 初始化好了，如果大于 16，就按照给定值初始化。
2. HashMap 扩容的伐值的计算公式是：Map 的容量 * 0.75f，一旦达到阀值就会扩容，此处用 (int) (c.size ()/.75f) + 1 来表示初始化的值，这样使我们期望的大小值正好比扩容的阀值还大 1，就不会扩容，符合 HashMap 扩容的公式。

从简单的构造器中，我们就可以看出要很好的组合 api 接口，并没有那么简单，我们可能需要去了解一下被组合的 api 底层的实现，这样才能用好 api。

同时这种写法，也提供了一种思路给我们，如果有人问你，往 HashMap 拷贝大集合时，如何给 HashMap 初始化大小时，完全可以借鉴这种写法：取最大值（期望的值 / 0.75 + 1，默认值 16）。

至于 HashSet 的其他方法就比较简单了，就是对 Map 的 api 进行了一些包装，如下的 add 方法实现：

```java
public boolean add(E e) {
    // 直接使用 HashMap 的 put 方法，进行一些简单的逻辑判断
    return map.put(e, PRESENT)==null;
}
```

从 add 方法中，我们就可以看到组合的好处，方法的入参、名称、返回值都可以自定义，如果是继承的话就不行了。

#### 1.2.2 小结

HashSet 具体实现值得我们借鉴的地方主要有如下地方，我们平时写代码的时候，完全可以参考参考：

1. 对组合还是继承的分析和把握；
2. 对复杂逻辑进行一些包装，使吐出去的接口尽量简单好用；
3. 组合其他 api 时，尽量多对组合的 api 多些了解，这样才能更好的使用 api；
4. HashMap 初始化大小值的模版公式：取括号内两者的最大值（期望的值 / 0.75+1，默认值 16）。



### 2 TreeSet

TreeSet 大致的结构和 HashSet 相似，底层组合的是 TreeMap，所以继承了 TreeMap key 能够排序的功能，迭代的时候，也可以按照 key 的排序顺序进行迭代，我们主要来看复用 TreeMap 时，复用的两种思路：

##### 2.1 复用 TreeMap 的思路一

场景一： TreeSet 的 add 方法，我们来看下其源码：

```java
public boolean add(E e) {
    return m.put(e, PRESENT)==null;
}
```

可以看到，底层直接使用的是 HashMap 的 put 的能力，直接拿来用就好了。

##### 2.2 复用 TreeMap 的思路二

场景二：需要迭代 TreeSet 中的元素，那应该也是像 add 那样，直接使用 HashMap 已有的迭代能力，比如像下面这样：

```java
// 模仿思路一的方式实现
public Iterator<E> descendingIterator() {
    // 直接使用 HashMap.keySet 的迭代能力
    return m.keySet().iterator();
}
```

这种是思路一的实现方式，TreeSet 组合 TreeMap，直接选择 TreeMap 的底层能力进行包装，但 TreeSet 实际执行的思路却完全相反，我们看源码：

```java
// NavigableSet 接口，定义了迭代的一些规范，和一些取值的特殊方法
// TreeSet 实现了该方法，也就是说 TreeSet 本身已经定义了迭代的规范
public interface NavigableSet<E> extends SortedSet<E> {
    Iterator<E> iterator();
    E lower(E e);
}  
// m.navigableKeySet() 是 TreeMap 写了一个子类实现了 NavigableSet
// 接口，实现了 TreeSet 定义的迭代规范
public Iterator<E> iterator() {
    return m.navigableKeySet().iterator();
}
```

TreeMap 中对 NavigableSet 接口的实现源码截图如下：

![图片描述](http://img.mukewang.com/5d763f6e00018e0114461250.png)从截图中（截图是在 TreeMap 中），我们可以看出 TreeMap 实现了 TreeSet 定义的各种特殊方法。

我们可以看到，这种思路是 TreeSet 定义了接口的规范，TreeMap 负责去实现，实现思路和思路一是相反的。

我们总结下 TreeSet 组合 TreeMap 实现的两种思路：

1. TreeSet 直接使用 TreeMap 的某些功能，自己包装成新的 api。
2. TreeSet 定义自己想要的 api，自己定义接口规范，让 TreeMap 去实现。

方案 1 和 2 的调用关系，都是 TreeSet 调用 TreeMap，但功能的实现关系完全相反，第一种是功能的定义和实现都在 TreeMap，TreeSet 只是简单的调用而已，第二种 TreeSet 把接口定义出来后，让 TreeMap 去实现内部逻辑，TreeSet 负责接口定义，TreeMap 负责具体实现，这样子的话因为接口是 TreeSet 定义的，所以实现一定是 TreeSet 最想要的，TreeSet 甚至都不用包装，可以直接把返回值吐出去都行。

我们思考下这两种复用思路的原因：

1. 像 add 这些简单的方法，我们直接使用的是思路 1，主要是 add 这些方法实现比较简单，没有复杂逻辑，所以 TreeSet 自己实现起来比较简单；
2. 思路 2 主要适用于复杂场景，比如说迭代场景，TreeSet 的场景复杂，比如要能从头开始迭代，比如要能取第一个值，比如要能取最后一个值，再加上 TreeMap 底层结构比较复杂，TreeSet 可能并不清楚 TreeMap 底层的复杂逻辑，这时候让 TreeSet 来实现如此复杂的场景逻辑，TreeSet 就搞不定了，不如接口让 TreeSet 来定义，让 TreeMap 去负责实现，TreeMap 对底层的复杂结构非常清楚，实现起来既准确又简单。

##### 2.3 小结

TreeSet 对 TreeMap 的两种不同复用思路，很重要，在工作中经常会遇到，特别是思路二，比如说 dubbo 的泛化调用，DDD 中的依赖倒置等等，原理都是 TreeSet 第二种的复用思想。



### 3 面试题

HashSet 和 TreeSet 的面试概率比不上 List 和 Map，但只要有机会，并把本文的内容表达出来，绝对是加分项，因为现在 List 和 Map 面试题太多，面试官认为你能答的出来是应该的，但只要你有机会对 HashSet 和 TreeSet 说出本文见解，并且说自己是看源码时领悟到的，绝对肯定是加分项，这些就是你超过面试官预期的惊喜，以下是一些常用的题目：



#### 3.1 TreeSet 有用过么，平时都在什么场景下使用？

答：有木有用过如实回答就好了，我们一般都是在需要把元素进行排序的时候使用 TreeSet，使用时需要我们注意元素最好实现 Comparable 接口，这样方便底层的 TreeMap 根据 key 进行排序。



#### 3.2 追问，如果我想实现根据 key 的新增顺序进行遍历怎么办？

答：要按照 key 的新增顺序进行遍历，首先想到的应该就是 LinkedHashMap，而 LinkedHashSet 正好是基于 LinkedHashMap 实现的，所以我们可以选择使用 LinkedHashSet。



#### 3.3 追问，如果我想对 key 进行去重，有什么好的办法么？

答：我们首先想到的是 TreeSet，TreeSet 底层使用的是 TreeMap，TreeMap 在 put 的时候，如果发现 key 是相同的，会把 value 值进行覆盖，所有不会产生重复的 key ，利用这一特性，使用 TreeSet 正好可以去重。



#### 3.4 说说 TreeSet 和 HashSet 两个 Set 的内部实现结构和原理？

答： HashSet 底层对 HashMap 的能力进行封装，比如说 add 方法，是直接使用 HashMap 的 put 方法，比较简单，但在初始化的时候，我看源码有一些感悟：说一下 HashSet 小结的四小点。

TreeSet 主要是对 TreeMap 底层能力进行封装复用，我发现了两种非常有意思的复用思路，重复 TreeSet 两种复用思路。



### 总结

本小节主要说了 Set 源码中两处亮点：

1. HashSet 对组合的 HashMap 类扩容的门阀值的深入了解和设计，值得我们借鉴；
2. TreeSet 对 TreeMap 两种复用思路，值得我们学习，特别是第二种复用思路。

HashSet 和 TreeSet 不会是面试的重点，但通过以上两点，可以让我们给面试官一种精益求精的感觉，成为加分项。

## **12 彰显细节：看集合源码对我们实际工作的帮助和应用**

本节中，我们先跳出源码的视角，来看看集合类的类图，看看在设计层面上，是否有可疑借鉴之处，接着通过源码来找找工作中的集合坑，提前扫雷。



### 1 集合类图

![图片描述](http://img.mukewang.com/5d7ef50d00018a8315460536.png)上图是目前我们已学的集合类图，大概可以看出以下几点：

1. 每个接口做的事情非常明确，比如 Serializable，只负责序列化，Cloneable 只负责拷贝，Map 只负责定义 Map 的接口，整个图看起来虽然接口众多，但职责都很清晰；
2. 复杂功能通过接口的继承来实现，比如 ArrayList 通过实现了 Serializable、Cloneable、RandomAccess、AbstractList、List 等接口，从而拥有了序列化、拷贝、对数组各种操作定义等各种功能；
3. 上述类图只能看见继承的关系，组合的关系还看不出来，比如说 Set 组合封装 Map 的底层能力等。

上述设计的最大好处是，每个接口能力职责单一，众多的接口变成了接口能力的积累，假设我们想再实现一个数据结构类，我们就可以从这些已有的能力接口中，挑选出能满足需求的能力接口，进行一些简单的组装，从而加快开发速度。

这种思想在平时的工作中也经常被使用，我们会把一些通用的代码块抽象出来，沉淀成代码块池，碰到不同的场景的时候，我们就从代码块池中，把我们需要的代码块提取出来，进行简单的编排和组装，从而实现我们需要的场景功能。



### 2 集合工作中一些注意事项



#### 2.1 线程安全

我们说集合都是非线程安全的，这里说的非线程安全指的是集合类作为共享变量，被多线程读写的时候，才是不安全的，如果要实现线程安全的集合，在类注释中，JDK 统一推荐我们使用 Collections.synchronized* 类， Collections 帮我们实现了 List、Set、Map 对应的线程安全的方法， 如下图：
![图片描述](http://img.mukewang.com/5d7ef4d400018def13540844.png)图中实现了各种集合类型的线程安全的方法，我们以 synchronizedList 为例，从源码上来看下，Collections 是如何实现线程安全的：

```java
// mutex 就是我们需要锁住的对象
final Object mutex;  
static class SynchronizedList<E>
        extends SynchronizedCollection<E>
        implements List<E> {
        private static final long serialVersionUID = -7754090372962971524L;
        // 这个 List 就是我们需要保证线程安全的类
        final List<E> list;
        SynchronizedList(List<E> list, Object mutex) {
            super(list, mutex);
            this.list = list;
        }
				// 我们可以看到，List 的所有操作都使用了 synchronized 关键字，来进行加锁
				// synchronized 是一种悲观锁，能够保证同一时刻，只能有一个线程能够获得锁
        public E get(int index) {
            synchronized (mutex) {return list.get(index);}
        }
        public E set(int index, E element) {
            synchronized (mutex) {return list.set(index, element);}
        }
        public void add(int index, E element) {
            synchronized (mutex) {list.add(index, element);}
        }
…………
}      
```

从源码中我们可以看到 Collections 是通过 synchronized 关键字给 List 操作数组的方法加上锁，来实现线程安全的。



#### 2.2 集合性能

集合的单个操作，一般都没有性能问题，性能问题主要出现的批量操作上。

##### 2.2.1 批量新增

在 List 和 Map 大量数据新增的时候，我们不要使用 for 循环 + add/put 方法新增，这样子会有很大的扩容成本，我们应该尽量使用 addAll 和 putAll 方法进行新增，以 ArrayList 为例写了一个 demo 如下，演示了两种方案的性能对比：

```java
@Test
public void testBatchInsert(){
  // 准备拷贝数据
  ArrayList<Integer> list = new ArrayList<>();
  for(int i=0;i<3000000;i++){
    list.add(i);
  }

  // for 循环 + add
  ArrayList<Integer> list2 = new ArrayList<>();
  long start1 = System.currentTimeMillis();
  for(int i=0;i<list.size();i++){
    list2.add(list.get(i));
  }
  log.info("单个 for 循环新增 300 w 个，耗时{}",System.currentTimeMillis()-start1);

  // 批量新增
  ArrayList<Integer> list3 = new ArrayList<>();
  long start2 = System.currentTimeMillis();
  list3.addAll(list);
  log.info("批量新增 300 w 个，耗时{}",System.currentTimeMillis()-start2);
}
```

最后打印出来的日志为：

16:52:59.865 [main] INFO demo.one.ArrayListDemo - 单个 for 循环新增 300 w 个，耗时1518
16:52:59.880 [main] INFO demo.one.ArrayListDemo - 批量新增 300 w 个，耗时8

可以看到，批量新增方法性能是单个新增方法性能的 189 倍，主要原因在于批量新增，只会扩容一次，大大缩短了运行时间，而单个新增，每次到达扩容阀值时，都会进行扩容，在整个过程中就会不断的扩容，浪费了很多时间，我们来看下批量新增的源码：

```java
public boolean addAll(Collection<? extends E> c) {
  Object[] a = c.toArray();
  int numNew = a.length;
  // 确保容量充足，整个过程只会扩容一次
  ensureCapacityInternal(size + numNew); 
  // 进行数组的拷贝
  System.arraycopy(a, 0, elementData, size, numNew);
  size += numNew;
  return numNew != 0;
}
```

以上是 ArrayList 批量新增的演示，我们可以看到，整个批量新增的过程中，只扩容了一次，HashMap 的 putAll 方法也是如此，整个新增过程只会扩容一次，大大缩短了批量新增的时间，提高了性能。

所以如果有人问你当碰到集合批量拷贝，批量新增场景，如何提高新增性能的时候 ，就可以从目标集合初始化方面应答。

这里也提醒了我们，在容器初始化的时候，最好能给容器赋上初始值，这样可以防止在 put 的过程中不断的扩容，从而缩短时间，上章 HashSet 的源码给我们演示了，给 HashMap 赋初始值的公式为：取括号内两者的最大值（期望的值/0.75+1，默认值 16）。

##### 2.2.2 批量删除

批量删除 ArrayList 提供了 removeAll 的方法，HashMap 没有提供批量删除的方法，我们一起来看下 removeAll 的源码实现，是如何提高性能的：

```java
// 批量删除，removeAll 方法底层调用的是 batchRemove 方法
// complement 参数默认是 false,false 的意思是数组中不包含 c 中数据的节点往头移动
// true 意思是数组中包含 c 中数据的节点往头移动，这个是根据你要删除数据和原数组大小的比例来决定的
// 如果你要删除的数据很多，选择 false 性能更好，当然 removeAll 方法默认就是 false。
private boolean batchRemove(Collection<?> c, boolean complement) {
  final Object[] elementData = this.elementData;
  // r 表示当前循环的位置、w 位置之前都是不需要被删除的数据，w 位置之后都是需要被删除的数据
  int r = 0, w = 0;
  boolean modified = false;
  try {
    // 从 0 位置开始判断，当前数组中元素是不是要被删除的元素，不是的话移到数组头
    for (; r < size; r++)
      if (c.contains(elementData[r]) == complement)
        elementData[w++] = elementData[r];
  } finally {
    // r 和 size 不等，说明在 try 过程中发生了异常，在 r 处断开
    // 把 r 位置之后的数组移动到 w 位置之后(r 位置之后的数组数据都是没有判断过的数据，这样不会影响没有判断的数据，判断过的数据可以被删除)
    if (r != size) {
      System.arraycopy(elementData, r,
                       elementData, w,
                       size - r);
      w += size - r;
    }
    // w != size 说明数组中是有数据需要被删除的
    // 如果 w、size 相等，说明没有数据需要被删除
    if (w != size) {
      // w 之后都是需要删除的数据，赋值为空，帮助 gc。
      for (int i = w; i < size; i++)
        elementData[i] = null;
      modCount += size - w;
      size = w;
      modified = true;
    }
  }
  return modified;
}
```

我们看到 ArrayList 在批量删除时，如果程序执行正常，只有一次 for 循环，如果程序执行异常，才会加一次拷贝，而单个 remove 方法，每次执行的时候都会进行数组的拷贝（当删除的元素正好是数组最后一个元素时除外），当数组越大，需要删除的数据越多时，批量删除的性能会越差，所以在 ArrayList 批量删除时，强烈建议使用 removeAll 方法进行删除。



#### 2.3 集合的一些坑

1. 当集合的元素是自定义类时，自定义类强制实现 equals 和 hashCode 方法，并且两个都要实现。

在集合中，除了 TreeMap 和 TreeSet 是通过比较器比较元素大小外，其余的集合类在判断索引位置和相等时，都会使用到 equals 和 hashCode 方法，这个在之前的源码解析中，我们有说到，所以当集合的元素是自定义类时，我们强烈建议覆写 equals 和 hashCode 方法，我们可以直接使用 IDEA 工具覆写这两个方法，非常方便；

1. 所有集合类，在 for 循环进行删除时，如果直接使用集合类的 remove 方法进行删除，都会快速失败，报 ConcurrentModificationException 的错误，所以在任意循环删除的场景下，都建议使用迭代器进行删除；
2. 我们把数组转化成集合时，常使用 Arrays.asList(array)，这个方法有两个坑，代码演示坑为：

```java
public void testArrayToList(){
  Integer[] array = new Integer[]{1,2,3,4,5,6};
  List<Integer> list = Arrays.asList(array);

  // 坑1：修改数组的值，会直接影响原 list
  log.info("数组被修改之前，集合第一个元素为：{}",list.get(0));
  array[0] = 10;
  log.info("数组被修改之前，集合第一个元素为：{}",list.get(0));

  // 坑2：使用 add、remove 等操作 list 的方法时，
  // 会报 UnsupportedOperationException 异常
  list.add(7);
}
坑 1：数组被修改后，会直接影响到新 List 的值。
坑 2：不能对新 List 进行 add、remove 等操作，否则运行时会报 UnsupportedOperationException 错误。
```

我们来看下 Arrays.asList 的源码实现，就能知道问题所在了，源码如下图：

![图片描述](http://img.mukewang.com/5d7ef4ad0001a58020880820.png)从上图中，我们可以发现，Arrays.asList 方法返回的 List 并不是 java.util.ArrayList，而是自己内部的一个静态类，该静态类直接持有数组的引用，并且没有实现 add、remove 等方法，这些就是坑 1 和 2 的原因。

1. 集合 List 转化成数组，我们通常使用 toArray 这个方法，这个方法很危险，稍微不注意，就踩进大坑，我们示例代码如下：

```java
  public void testListToArray(){
    List<Integer> list = new ArrayList<Integer>(){{
      add(1);
      add(2);
      add(3);
      add(4);
    }};

    // 下面这行被注释的代码这么写是无法转化成数组的，无参 toArray 返回的是 Object[],
    // 无法向下转化成 List<Integer>，编译都无法通过
    // List<Integer> list2 = list.toArray();

    // 演示有参 toArray 方法，数组大小不够时，得到数组为 null 情况
    Integer[] array0 = new Integer[2];
    list.toArray(array0);
    log.info("toArray 数组大小不够，array0 数组[0] 值是{},数组[1] 值是{},",array0[0],array0[1]);
		
    // 演示数组初始化大小正好，正好转化成数组
    Integer[] array1 = new Integer[list.size()];
    list.toArray(array1);
    log.info("toArray 数组大小正好，array1 数组[3] 值是{}",array1[3]);

    // 演示数组初始化大小大于实际所需大小，也可以转化成数组
    Integer[] array2 = new Integer[list.size()+2];
    list.toArray(array2);
    log.info("toArray 数组大小多了，array2 数组[3] 值是{}，数组[4] 值是{}",array2[3],array2[4]);
  }
19:33:07.687 [main] INFO demo.one.ArrayListDemo - toArray 数组大小不够，array0 数组[0] 值是null,数组[1] 值是null,
19:33:07.697 [main] INFO demo.one.ArrayListDemo - toArray 数组大小正好，array1 数组[3] 值是4
19:33:07.697 [main] INFO demo.one.ArrayListDemo - toArray 数组大小多了，array2 数组[3] 值是4，数组[4] 值是null
```

toArray 的无参方法，无法强转成具体类型，这个编译的时候，就会有提醒，我们一般都会去使用带有参数的 toArray 方法，这时就有一个坑，如果参数数组的大小不够，这时候返回的数组值竟然是空，上述代码中的 array0 的返回值就体现了这点，但我们去看 toArray 源码，发现源码中返回的是 4 个大小值的数据，返回的并不是空，源码如下：

```java
// List 转化成数组
public <T> T[] toArray(T[] a) {
  // 如果数组长度不够，按照 List 的大小进行拷贝，return 的时候返回的都是正确的数组
  if (a.length < size)
    // Make a new array of a's runtime type, but my contents:
    return (T[]) Arrays.copyOf(elementData, size, a.getClass());
  System.arraycopy(elementData, 0, a, 0, size);
  // 数组长度大于 List 大小的，赋值为 null
  if (a.length > size)
    a[size] = null;
  return a;
}
```

从源码中，我们丝毫看不出为什么 array0 的元素值为什么是 null，最后我们去看方法的注释，发现是这样子描述的：

```java
If the list fits in the specified array, it is returned therein.  Otherwise, a new array is
 allocated with the runtime type of the specified array and the size of this list。
```

翻译过来的意思就是说：如果返回的数组大小和申明的数组大小一致，那么就会正常返回，否则，一个新数组就会被分配返回。

所以我们在使用有参 toArray 方法时，申明的数组大小一定要大于等于 List 的大小，如果小于的话，你会得到一个空数组。



### 3 总结

本小节，我们详细描述了集合的线程安全、性能优化和日常工作中一些坑，这些问题我们在工作中经常会碰到，稍不留神就会引发线上故障，面试的时候也经常会通过这些问题，来考察大家的工作经验，所以阅读本章时，建议大家自己动手试一试，加深印象。

## **13 差异对比：集合在 Java 7 和 8 有何不同和改进**

### 引导语

Java 8 在 Java 7 的基础上，做了一些改进和优化，但我们在平时工作中，或者直接升级到 Java 8 的过程中，我们好像无需做任何兼容逻辑，那么 Java 8 底层是如何处理的呢，在改进的同时，是如何优雅兼容 Java 老版本，让使用者无需感知，接下来我们通过对比 Java 7 和 8 的差异，来展示 Java 8 是如何优雅升级的。



### 1 通用区别



#### 1.1 所有集合都新增了forEach 方法

List、Set、Map 在 Java 8 版本中都增加了 forEach 的方法，方法的入参是 Consumer，Consumer 是一个函数式接口，可以简单理解成允许一个入参，但没有返回值的函数式接口，我们以 ArrayList 的 forEach 的源码为例，来看下方法是如何实现的 ：

```java
@Override
public void forEach(Consumer<? super E> action) {
  // 判断非空
  Objects.requireNonNull(action);
  // modCount的原始值被拷贝
  final int expectedModCount = modCount;
  final E[] elementData = (E[]) this.elementData;
  final int size = this.size;
  // 每次循环都会判断数组有没有被修改，一旦被修改，停止循环
  for (int i=0; modCount == expectedModCount && i < size; i++) {
    // 执行循环内容，action 代表我们要干的事情
    action.accept(elementData[i]);
  }
  // 数组如果被修改了，抛异常
  if (modCount != expectedModCount) {
    throw new ConcurrentModificationException();
  }
}
```

从这段源码中，很容易产生两个问题：

1、action.accept 到底是个啥？

action.accept 就是你在 for 循环中要干的事情，你可以进行任何事情，比如我们打印一句话，如下：

```java
public void testForEach(){
  List<Integer> list = new ArrayList<Integer>(){{
    add(1);
    add(3);
    add(2);
    add(4);
  }};
  // value 是每次循环的入参，就是 list 中的每个元素
  list.forEach( value->log.info("当前值为：{}",value));
}
输出为：
当前值为：1
当前值为：3
当前值为：2
当前值为：4
```

log.info(“当前值为：{}”,value) 就是我们要干的事情，就是 action。

2.、forEach 方法上打了 @Override 注解，说明该方法是被继承实现的，该方法是被定义在 Iterable 接口上的，Java 7 和 8 的 ArrayList 都实现了该接口，但我们在 Java 7 的 ArrayList 并没有发现有实现该方法，编译器也木有报错，这个主要是因为 Iterable 接口的 forEach 方法被加上了 default 关键字，这个关键字只会出现在接口类中，被该关键字修饰的方法无需强制要求子类继承，但需要自己实现默认实现，我们看下源码：
![图片描述](http://img.mukewang.com/5d70798d00010b6111940364.png)
不仅仅是 forEach 这一个方法是这么干的，List、Set、Map 接口中很多新增的方法都是这么干的，通过 default 关键字，可以让 Java 7 的集合子类无需实现 Java 8 中新增的方法。

如果想在接口中新增一个方法，但又不想子类强制实现该方法时，可以给该方法加上 default 关键字，这个在实际工作中，也经常使用到，算是重构的小技巧吧。



### 1.2 List 区别



#### 1.2.1 ArrayList

1. ArrayList 无参初始化时，Java 7 是直接初始化 10 的大小，Java 8 去掉了这个逻辑，初始化时是空数组，在第一次 add 时才开始按照 10 进行扩容，下图是源码的差异对比图：
   ![图片描述](http://img.mukewang.com/5d7079470001ebfd20080254.png)List 其它方面 java7 和 8 并没有改动。



### 1.3 Map 区别



#### 1.3.1 HashMap

1. 和 ArrayList 一样，Java 8 中 HashMap 在无参构造器中，丢弃了 Java 7 中直接把数组初始化 16 的做法，而是采用在第一次新增的时候，才开始扩容数组大小；
2. hash 算法计算公式不同，Java 8 的 hash 算法更加简单，代码更加简洁；
3. Java 8 的 HashMap 增加了红黑树的数据结构，这个是 Java 7 中没有的，Java 7 只有数组 + 链表的结构，Java 8 中提出了数组 + 链表 + 红黑树的结构，一般 key 是 Java 的 API 时，比如说 String 这些 hashcode 实现很好的 API，很少出现链表转化成红黑树的情况，因为 String 这些 API 的 hash 算法够好了，只有当 key 是我们自定义的类，而且我们覆写的 hashcode 算法非常糟糕时，才会真正使用到红黑树，提高我们的检索速度。

也是因为 Java 8 新增了红黑树，所以几乎所有操作数组的方法的实现，都发生了变动，比如说 put、remove 等操作，可以说 Java 8 的 HashMap 几乎重写了一遍，所以 Java 7 的很多问题都被 Java 8 解决了，比如扩容时极小概率死锁，丢失数据等等。

1. 新增了一些好用的方法，比如 getOrDefault，我们看下源码，非常简单：

```java
// 如果 key 对应的值不存在，返回期望的默认值 defaultValue
public V getOrDefault(Object key, V defaultValue) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? defaultValue : e.value;
}
```

还有 putIfAbsent(K key, V value) 方法，意思是，如果 map 中存在 key 了，那么 value 就不会覆盖，如果不存在 key ，新增成功。

还有 compute 方法，意思是允许我们把 key 和 value 的值进行计算后，再 put 到 map 中，为防止 key 值不存在造成未知错误，map 还提供了 computeIfPresent 方法，表示只有在 key 存在的时候，才执行计算，demo 如下：

```java
  @Test
  public void compute(){
    HashMap<Integer,Integer> map = Maps.newHashMap();
    map.put(10,10);
    log.info("compute 之前值为：{}",map.get(10));
    map.compute(10,(key,value) -> key * value);
    log.info("compute 之后值为：{}",map.get(10));
    // 还原测试值
    map.put(10,10);

    // 如果为 11 的 key 不存在的话，需要注意 value 为空的情况，下面这行代码就会报空指针
    //  map.compute(11,(key,value) -> key * value);
    
    // 为了防止 key 不存在时导致的未知异常，我们一般有两种办法
    // 1：自己判断空指针
    map.compute(11,(key,value) -> null == value ? null : key * value);
    // 2：computeIfPresent 方法里面判断
    map.computeIfPresent(11,(key,value) -> key * value);
    log.info("computeIfPresent 之后值为：{}",map.get(11));
  }
结果是：
compute 之前值为：10
compute 之后值为：100
computeIfPresent 之后值为：null（这个结果中，可以看出，使用 computeIfPresent 避免了空指针）
```

上述 Java 8 新增的几种方法非常好用，在实际工作中，可以大大减少我们的代码量，computeIfPresent 的源码就不贴了，有兴趣可以去 github 上面查看，主要的实现原理如下：

- 找到 key 对应的老值，会分别从数组、链表、红黑树中找；
- 根据 key 和老值进行计算，得到新值；
- 用新值替换掉老值，可能是普通替换、链表替换或红黑树替换。

#### 1.3.2 LinkedHashMap

由于 Java 8 的底层数据有变动，导致 HashMap 操作数据的方法几乎重写，也使 LinkedHashMap 的实现名称上有所差异，原理上都相同，我们看下面的图，左边是 Java 7，右边是 Java 8。
![图片描述](http://img.mukewang.com/5d7078fe0001a6b724721322.png)从图中，我们发现 LinkedHashMap 的方法名有所修改，底层的实现逻辑其实都差不多的。



### 1.4 其他区别



#### 1.4.1 Arrays 提供了很多 parallel 开头的方法。

Java 8 的 Arrays 提供了一些 parallel 开头的方法，这些方法支持并行的计算，在数据量大的时候，会充分利用 CPU ，提高计算效率，比如说 parallelSort 方法，方法底层有判断，只有数据量大于 8192 时，才会真正走并行的实现，在实际的实验中，并行计算的确能够快速的提高计算速度。



### 1.5 面试题

1. Java 8 在 List、Map 接口上新增了很多方法，为什么 Java 7 中这些接口的实现者不需要强制实现这些方法呢？

答：主要是因为这些新增的方法被 default 关键字修饰了，default 一旦修饰接口上的方法，我们需要在接口的方法中写默认实现，并且子类无需强制实现这些方法，所以 Java 7 接口的实现者无需感知。

1. Java 8 中有新增很多实用的方法，你在平时工作中有使用过么？

答：有的，比如说 getOrDefault、putIfAbsent、computeIfPresent 方法等等，具体使用细节参考上文。

1. 说说 computeIfPresent 方法的使用姿势？

答：computeIfPresent 是可以对 key 和 value 进行计算后，把计算的结果重新赋值给 key，并且如果 key 不存在时，不会报空指针，会返回 null 值。

1. Java 8 集合新增了 forEach 方法，和普通的 for 循环有啥不同？

答：新增的 forEach 方法的入参是函数式的接口，比如说 Consumer 和 BiConsumer，这样子做的好处就是封装了 for 循环的代码，让使用者只需关注实现每次循环的业务逻辑，简化了重复的 for 循环代码，使代码更加简洁，普通的 for 循环，每次都需要写重复的 for 循环代码，forEach 把这种重复的计算逻辑吃掉了，使用起来更加方便。

1. HashMap 8 和 7 有啥区别？

答：HashMap 8 和 7 的差别太大了，新增了红黑树，修改了底层数据逻辑，修改了 hash 算法，几乎所有底层数组变动的方法都重写了一遍，可以说 Java 8 的 HashMap 几乎重新了一遍。



### 总结

总体来说，List 方面是小改动，HashMap 几乎重写了一套，所有的集合都新增了函数式的方法，比如说 forEach，也新增了很多好用的函数，比如说 getOrDefault，这些函数可以大大减少我们的代码量，让我们把关注点聚焦在业务逻辑的实现上，这其实是一种思想，把繁琐重复的计算逻辑抽取出来，从计算逻辑中扩展出业务逻辑的口子，让使用者只专心关注业务逻辑的实现即可。

想要了解更多差异，也可直接前往 JDK 8 新特性查看，地址为：http://openjdk.java.net/projects/jdk8/features#103。

## **14 简化工作：Guava Lists Maps 实际工作运用和源码**

### 引导语

在日常工作中，我们经常会使用一些三方的 API 来简化我们的工作，Guava 就是其中一种，Guava 是 Google 开源的技术框架，使用率高，社区活跃度也很高。

本小节我们从工作中对 Guava 集合的使用入手，然后深入的看下其底层的实现，最后总结其设计思想，感兴趣的同学也可以下载源码学习，GitHub 地址：https://github.com/google/guava，源码中 guava 的文件夹为其源码。



### 1 运用工厂模式进行初始化

在集合类初始化方面，Guava 比 Java 原生的 API 更加好用，还发明了很多新的功能，比如说在 JDK 7 之前，我们新建集合类时，声明和初始化都必须写上泛型说明，像这样：`List<泛型> list = new ArrayList<泛型>();` ， JDK 7 之后有所改变，我们只需要在声明处写上泛型说明，像这样：`List<泛型> list = new ArrayList<>();`。

Guava 提供了更加方便的使用姿势，采用了工厂模式，把集合创建的逻辑交给了工厂，开发者无需关注工厂底层是如何创建的，只需要关心，工厂能产生什么，代码于是变成了这样：`List<泛型> list = Lists.newArrayList();`，Lists 就是 Guava 提供出来的，方便操作 List 的工具类。

这种写法其实就是一种简单的工厂模式，只需要定义好工厂的入参和出参，就能对外隐藏其内部的创建逻辑，提供更加方便的使用体验。

当然除了 Lists，Guava 还提供了很多其他实用工具，如 Maps、Sets，接下来我们分别来看下这些常用工具的使用和原理。



### 2 Lists



#### 2.1 初始化

Lists 最大的功能是能帮助我们进行 List 的初始化，比如我们刚说的 newArrayList 这种：

```java
List<String> list = Lists.newArrayList();
public static <E> ArrayList<E> newArrayList() {
  return new ArrayList<>();
}
// 这种底层是帮助我们写好了泛型，E 代表泛型，表示当前返回的泛型类型和声明的一致即可，在编译的时候，会把泛型 E 转化成我们声明的 String。
```

如果你清楚 List 的大小，我们也可以这样做：

```java
// 可以预估 list 的大小为 20
List<String> list = Lists.newArrayListWithCapacity(20);
// 不太肯定 list 大小是多少，但期望是大小是 20 上下。
List<String> list = Lists.newArrayListWithExpectedSize(20);
```

newArrayListWithCapacity(20) 方法内部实现是：`new ArrayList<>(20);`，而 newArrayListWithExpectedSize 方法内部实现是对 List 大小有一个计算公式的，计算公式为：`5L + arraySize + (arraySize / 10)` ，arraySize 表示传进来的值，公式简化下就是 5 + 11/10 * arraySize，因为这个方法表示期望的大小，所以这里取的约是期望值的十分之十一，比传进来的值约大十分之一，所以根据 20 最终计算出来的值是 27。

Lists 在初始化的时候，还支持传迭代器的入参（只适合小数据量的迭代器的入参），源码如下：

```java
public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
  ArrayList<E> list = newArrayList();
  // addAll 方法底层其实通过迭代器进行 for 循环添加
  Iterators.addAll(list, elements);
  return list;
}
```

从 Lists 对 List 初始化进行包装的底层源码来看，底层源码非常简单的，但我们还是愿意使用这种方式的包装，主要是因为这种工厂模式的包装，使我们的使用姿势更加优雅，使用起来更加方便。



#### 2.2 分组和反转排序

除了初始化之外，Lists 还提供了两个比较实用的功能，分组和反转排序功能，我们分别来演示一下：

```java
// 演示反转排序
public void testReverse(){
  List<String> list = new ArrayList<String>(){{
    add("10");
    add("20");
    add("30");
    add("40");
  }};
  log.info("反转之前："+JSON.toJSONString(list));
  list = Lists.reverse(list);
  log.info("反转之后："+JSON.toJSONString(list));
}
// 打印出来的结果为：
反转之前：["10","20","30","40"]
反转之后：["40","30","20","10"]
```

reverse 方法底层实现非常巧妙，底层覆写了 List 原生的 get(index) 方法，会把传进来的 index 进行 (size - 1) - index 的计算，使计算得到的索引位置和 index 位置正好相反，这样当我们 get 时，数组索引位置的 index 已经是相反的位置了，达到了反转排序的效果，其实底层并没有进行反转排序，只是在计算相反的索引位置，通过计算相反的索引位置这样简单的设计，得到了反转排序的效果，很精妙。

在工作中，有时候我们需要把一个大的 list 进行切分，然后再把每份丢给线程池去运行，最后将每份运行的结果汇总，Lists 工具类就提供了一个对 list 进行切分分组的方法，演示 demo 如下：

```java
// 分组
public void testPartition(){
  List<String> list = new ArrayList<String>(){{
    add("10");
    add("20");
    add("30");
    add("40");
  }};
  log.info("分组之前："+JSON.toJSONString(list));
   List<List<String>> list2 = Lists.partition(list,3);
  log.info("分组之后："+JSON.toJSONString(list2));
}
输出结果为：
分组之前：["10","20","30","40"]
分组之后：[["10","20","30"],["40"]]
```

partition 方法的第二个参数的意思，你想让分组后的 List 包含几个元素，这个方法的底层实现其实就是 subList 方法。

有一点需要我们注意的是这两个方法返回的 List 并不是 ArrayList，是自定义的 List，所以对于 ArrayList 的有些功能可能并不支持，使用的时候最好能看下源码，看看底层有无支持。



#### 2.3 小结

Lists 上述的方法大大的方便了我们进行开发，简化了使用姿势，但其内部实现却非常简单巧妙，比如说 reverse 方法可以输出相反排序的 List，但底层并没有实现排序，只是计算了索引位置的相反值而已，这点值得我们学习。



### 3 Maps



#### 3.1 初始化

Maps 也是有着各种初始化 Map 的各种方法，原理不说了，和 Lists 类似，我们演示下如何使用：

```java
Map<String,String> hashMap = Maps.newHashMap();
Map<String,String> linkedHashMap = Maps.newLinkedHashMap();
// 这里 Map 的初始化大小公式和 HashSet 初始化公式类似，还记得 HashSet 初始化 HashMap 时，经典的计算初始大小的公式么：取最大值（期望的值 / 0.75 + 1，默认值 16），newHashMapWithExpectedSize 方法底层也是这么算的初始化大小的
Map<String,String> withExpectedSizeHashMap = Maps.newHashMapWithExpectedSize(20);
```



#### 3.2 difference

Maps 提供了一个特别有趣也很实用的方法：difference，此方法的目的是比较两个 Map 的差异，入参就是两个 Map，比较之后能够返回四种差异：

1. 左边 Map 独有 key。
2. 右边 Map 独有 key。
3. 左右边 Map 都有 key，并且 value 相等。
4. 左右边 Map 都有 key，但是 value 不等。

我们用代码来演示一下：

```java
// ImmutableMap.of 也是 Guava 提供初始化 Map 的方法，入参格式为 k1,v1,k2,v2,k3,v3……
Map<String,String> leftMap = ImmutableMap.of("1","1","2","2","3","3");
Map<String,String> rightMap = ImmutableMap.of("2","2","3","30","4","4");
MapDifference difference = Maps.difference(leftMap, rightMap);
log.info("左边 map 独有 key：{}",difference.entriesOnlyOnLeft());
log.info("右边 map 独有 key：{}",difference.entriesOnlyOnRight());
log.info("左右边 map 都有 key，并且 value 相等：{}",difference.entriesInCommon());
log.info("左右边 map 都有 key，但 value 不等：{}",difference.entriesDiffering());
最后打印结果为：
左边 map 独有 key：{1=1}
右边 map 独有 key：{4=4}
左右边 map 都有 key，并且 value 相等：{2=2}
左右边 map 都有 key，但 value 不等：{3=(3, 30)}
```

从这个 demo 我们可以看到此方法的强大威力，我们在工作中经常遇到 Map 或者 List 间比较差异的任务，我们就可以直接使用该方法进行对比，List 可以先转化成 Map。

而且 difference 底层的实现也算是最优的实现了，只需要循环一遍，就可得到上述四种差异结果，源码解析如下：

```java
// 对比两个 map 的差异
private static <K, V> void doDifference(
    Map<? extends K, ? extends V> left,
    Map<? extends K, ? extends V> right,
    Equivalence<? super V> valueEquivalence,
    // key 只在左边 map 出现
    Map<K, V> onlyOnLeft,
    // key 只在右边 map 出现，调用 doDifference 方法前已经包含了全部右边的值
    Map<K, V> onlyOnRight,
    // key 在左右 map 中都出现过，并且 value 都相等
    Map<K, V> onBoth,
    // key 在左右 map 中都出现过，但 value 不等
    Map<K, MapDifference.ValueDifference<V>> differences) {
  // 以左边 map 为基准进行循环
  for (Entry<? extends K, ? extends V> entry : left.entrySet()) {
    K leftKey = entry.getKey();
    V leftValue = entry.getValue();
    // 右边 map 包含左边的 key
    if (right.containsKey(leftKey)) {
      // onlyOnRight 已经包含全部右边的值 所以需要删除当前 key
      V rightValue = onlyOnRight.remove(leftKey);
      // key 相等，并且 value 值也相等
      if (valueEquivalence.equivalent(leftValue, rightValue)) {
        onBoth.put(leftKey, leftValue);
      // key 相等，但 value 值不等
      } else {
        differences.put(leftKey, ValueDifferenceImpl.create(leftValue, rightValue));
      }
    // 右边 map 不包含左边的 key，就是左边 map 独有的 key
    } else {
      onlyOnLeft.put(leftKey, leftValue);
    }
  }
}
```

这是一种比较优秀的，快速比对的算法，可以好好看下上面的源码，然后把这种算法背下来，或者自己再次实现一次。

Sets 的使用方式和 Lists 和 Maps 很类似，没有太大的亮点，我们就不说了。



### 4 总结

这一小节主要都是实战内容，在实际工作中可以用起来。

在 Guava 对集合的设计中，有两个大点是非常值得我们学习的：

1. Lists、Maps 的出现给我们提供了更方便的使用姿势和方法，我们在实际工作中，如果碰到特别繁琐，或者特别难用的 API，我们也可以进行一些包装，使更好用，这个是属于在解决目前的痛点的问题上进行创新，是非常值得提倡的一件事情，往往可以帮助你拿到更好的绩效。
2. 如果有人问你，List 或者 Map 高效的差异排序算法，完全可以参考 Maps.difference 的内部实现，该方法只使用了一次循环，就可得到所有的相同或不同结果，这种算法在我们工作中也经常被使用。

了解更多，可以直接前往 Guava 的代码库查看：https://github.com/google/guava

# **第3章 并发集合类**

## **15 CopyOnWriteArrayList 源码解析和设计思路**

### 引导语

在 ArrayList 的类注释上，JDK 就提醒了我们，如果要把 ArrayList 作为共享变量的话，是线程不安全的，推荐我们自己加锁或者使用 Collections.synchronizedList 方法，其实 JDK 还提供了另外一种线程安全的 List，叫做 CopyOnWriteArrayList，这个 List 具有以下特征：

1. 线程安全的，多线程环境下可以直接使用，无需加锁；
2. 通过锁 + 数组拷贝 + volatile 关键字保证了线程安全；
3. 每次数组操作，都会把数组拷贝一份出来，在新数组上进行操作，操作成功之后再赋值回去。



### 1 整体架构

从整体架构上来说，CopyOnWriteArrayList 数据结构和 ArrayList 是一致的，底层是个数组，只不过 CopyOnWriteArrayList 在对数组进行操作的时候，基本会分四步走：

1. 加锁；
2. 从原数组中拷贝出新数组；
3. 在新数组上进行操作，并把新数组赋值给数组容器；
4. 解锁。

除了加锁之外，CopyOnWriteArrayList 的底层数组还被 volatile 关键字修饰，意思是一旦数组被修改，其它线程立马能够感知到，代码如下：

```
private transient volatile Object[] array;
```

整体上来说，CopyOnWriteArrayList 就是利用锁 + 数组拷贝 + volatile 关键字保证了 List 的线程安全。



#### 1.1 类注释

我们看看从 CopyOnWriteArrayList 的类注释上能得到哪些信息：

1. 所有的操作都是线程安全的，因为操作都是在新拷贝数组上进行的；
2. 数组的拷贝虽然有一定的成本，但往往比一般的替代方案效率高；
3. 迭代过程中，不会影响到原来的数组，也不会抛出 ConcurrentModificationException 异常。

接着我们来看下 CopyOnWriteArrayList 的核心方法源码。



### 2 新增

新增有很多种情况，比如说：新增到数组尾部、新增到数组某一个索引位置、批量新增等等，操作的思路还是我们开头说的四步，我们拿新增到数组尾部的方法举例，来看看底层源码的实现：

```java
// 添加元素到数组尾部
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    // 加锁
    lock.lock();
    try {
        // 得到所有的原数组
        Object[] elements = getArray();
        int len = elements.length;
        // 拷贝到新数组里面，新数组的长度是 + 1 的，因为新增会多一个元素
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        // 在新数组中进行赋值，新元素直接放在数组的尾部
        newElements[len] = e;
        // 替换掉原来的数组
        setArray(newElements);
        return true;
    // finally 里面释放锁，保证即使 try 发生了异常，仍然能够释放锁   
    } finally {
        lock.unlock();
    }
}
```

从源码中，我们发现整个 add 过程都是在持有锁的状态下进行的，通过加锁，来保证同一时刻只能有一个线程能够对同一个数组进行 add 操作。

除了加锁之外，还会从老数组中创建出一个新数组，然后把老数组的值拷贝到新数组上，这时候就有一个问题：都已经加锁了，为什么需要拷贝数组，而不是在原来数组上面进行操作呢，原因主要为：

1. volatile 关键字修饰的是数组，如果我们简单的在原来数组上修改其中某几个元素的值，是无法触发可见性的，我们必须通过修改数组的内存地址才行，也就说要对数组进行重新赋值才行。
2. 在新的数组上进行拷贝，对老数组没有任何影响，只有新数组完全拷贝完成之后，外部才能访问到，降低了在赋值过程中，老数组数据变动的影响。

简单 add 操作是直接添加到数组的尾部，接着我们来看下指定位置添加元素的关键源码（部分源码）：

```java
// len：数组的长度、index：插入的位置
int numMoved = len - index;
// 如果要插入的位置正好等于数组的末尾，直接拷贝数组即可
if (numMoved == 0)
    newElements = Arrays.copyOf(elements, len + 1);
else {
// 如果要插入的位置在数组的中间，就需要拷贝 2 次
// 第一次从 0 拷贝到 index。
// 第二次从 index+1 拷贝到末尾。
    newElements = new Object[len + 1];
    System.arraycopy(elements, 0, newElements, 0, index);
    System.arraycopy(elements, index, newElements, index + 1,
         numMoved);
}
// index 索引位置的值是空的，直接赋值即可。
newElements[index] = element;
// 把新数组的值赋值给数组的容器中
setArray(newElements);
```

从源码中可以看到，当插入的位置正好处于末尾时，只需要拷贝一次，当插入的位置处于中间时，此时我们会把原数组一分为二，进行两次拷贝操作。

最后还有个批量新增操作，源码我们就不贴了，底层也是拷贝数组的操作。



#### 2.1 小结

从 add 系列方法可以看出，CopyOnWriteArrayList 通过加锁 + 数组拷贝+ volatile 来保证了线程安全，每一个要素都有着其独特的含义：

1. 加锁：保证同一时刻数组只能被一个线程操作；
2. 数组拷贝：保证数组的内存地址被修改，修改后触发 volatile 的可见性，其它线程可以立马知道数组已经被修改；
3. volatile：值被修改后，其它线程能够立马感知最新值。

3 个要素缺一不可，比如说我们只使用 1 和 3 ，去掉 2，这样当我们修改数组中某个值时，并不会触发 volatile 的可见特性的，只有当数组内存地址被修改后，才能触发把最新值通知给其他线程的特性。



### 3 删除

接着我们来看下指定数组索引位置删除的源码：

```java
// 删除某个索引位置的数据
public E remove(int index) {
    final ReentrantLock lock = this.lock;
    // 加锁
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        // 先得到老值
        E oldValue = get(elements, index);
        int numMoved = len - index - 1;
        // 如果要删除的数据正好是数组的尾部，直接删除
        if (numMoved == 0)
            setArray(Arrays.copyOf(elements, len - 1));
        else {
            // 如果删除的数据在数组的中间，分三步走
            // 1. 设置新数组的长度减一，因为是减少一个元素
            // 2. 从 0 拷贝到数组新位置
            // 3. 从新位置拷贝到数组尾部
            Object[] newElements = new Object[len - 1];
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(elements, index + 1, newElements, index,
                             numMoved);
            setArray(newElements);
        }
        return oldValue;
    } finally {
        lock.unlock();
    }
}
```

步骤分为三步：

1. 加锁；
2. 判断删除索引的位置，从而进行不同策略的拷贝；
3. 解锁。

代码整体的结构风格也比较统一：锁 + try finally +数组拷贝，锁被 final 修饰的，保证了在加锁过程中，锁的内存地址肯定不会被修改，finally 保证锁一定能够被释放，数组拷贝是为了删除其中某个位置的元素。



### 4 批量删除

数组的批量删除很有意思，接下来我们来看下 CopyOnWriteArrayList 的批量删除的实现过程：

```java
// 批量删除包含在 c 中的元素
public boolean removeAll(Collection<?> c) {
    if (c == null) throw new NullPointerException();
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        // 说明数组有值，数组无值直接返回 false
        if (len != 0) {
            // newlen 表示新数组的索引位置，新数组中存在不包含在 c 中的元素
            int newlen = 0;
            Object[] temp = new Object[len];
            // 循环，把不包含在 c 里面的元素，放到新数组中
            for (int i = 0; i < len; ++i) {
                Object element = elements[i];
                // 不包含在 c 中的元素，从 0 开始放到新数组中
                if (!c.contains(element))
                    temp[newlen++] = element;
            }
            // 拷贝新数组，变相的删除了不包含在 c 中的元素
            if (newlen != len) {
                setArray(Arrays.copyOf(temp, newlen));
                return true;
            }
        }
        return false;
    } finally {
        lock.unlock();
    }
}
```

从源码中，我们可以看到，我们并不会直接对数组中的元素进行挨个删除，而是先对数组中的值进行循环判断，把我们不需要删除的数据放到临时数组中，最后临时数组中的数据就是我们不需要删除的数据。

不知道大家有木有似曾相识的感觉，ArrayList 的批量删除的思想也是和这个类似的，所以我们在需要删除多个元素的时候，最好都使用这种批量删除的思想，而不是采用在 for 循环中使用单个删除的方法，单个删除的话，在每次删除的时候都会进行一次数组拷贝(删除最后一个元素时不会拷贝)，很消耗性能，也耗时，会导致加锁时间太长，并发大的情况下，会造成大量请求在等待锁，这也会占用一定的内存。



### 5 其它方法



#### 5.1 indexOf

indexOf 方法的主要用处是查找元素在数组中的下标位置，如果元素存在就返回元素的下标位置，元素不存在的话返回 -1，不但支持 null 值的搜索，还支持正向和反向的查找，我们以正向查找为例，通过源码来说明一下其底层的实现方式：

```java
// o：我们需要搜索的元素
// elements：我们搜索的目标数组
// index：搜索的开始位置
// fence：搜索的结束位置
private static int indexOf(Object o, Object[] elements,
                           int index, int fence) {
    // 支持对 null 的搜索
    if (o == null) {
        for (int i = index; i < fence; i++)
            // 找到第一个 null 值，返回下标索引的位置
            if (elements[i] == null)
                return i;
    } else {
        // 通过 equals 方法来判断元素是否相等
        // 如果相等，返回元素的下标位置
        for (int i = index; i < fence; i++)
            if (o.equals(elements[i]))
                return i;
    }
    return -1;
}
```

indexOf 方法在 CopyOnWriteArrayList 内部使用也比较广泛，比如在判断元素是否存在时（contains），在删除元素方法中校验元素是否存在时，都会使用到 indexOf 方法，indexOf 方法通过一次 for 循环来查找元素，我们在调用此方法时，需要注意如果找不到元素时，返回的是 -1，所以有可能我们会对这个特殊值进行判断。



#### 5.2 迭代

在 CopyOnWriteArrayList 类注释中，明确说明了，在其迭代过程中，即使数组的原值被改变，也不会抛出 ConcurrentModificationException 异常，其根源在于数组的每次变动，都会生成新的数组，不会影响老数组，这样的话，迭代过程中，根本就不会发生迭代数组的变动，我们截几个图说明一下：

1. 迭代是直接持有原有数组的引用，也就是说迭代过程中，一旦原有数组的值内存地址发生变化，必然会影响到迭代过程，下图源码演示的是 CopyOnWriteArrayList 的迭代方法，我们可以看到迭代器是直接持有原数组的引用：
   ![图片描述](http://img.mukewang.com/5d88354300010c5111460592.png)
2. 我们写了一个 demo，在 CopyOnWriteArrayList 迭代之后，往 CopyOnWriteArrayList 里面新增值，从下图中可以看到在 CopyOnWriteArrayList 迭代之前，数组的内存地址是 962，请记住这个数字：
   ![图片描述](http://img.mukewang.com/5d8835800001ffca14040700.png)
3. CopyOnWriteArrayList 迭代之后，我们使用 add(“50”) 代码给数组新增一个数据后，数组内存地址发生了变化，内存地址从原来的 962 变成了 968，这是因为 CopyOnWriteArrayList 的 add 操作，会生成新的数组，所以数组的内存地址发生了变化：
   ![图片描述](http://img.mukewang.com/5d8835ac0001af7d13980550.png)
4. 迭代继续进行时，我们发现迭代器中的地址仍然是迭代之前引用的地址，是 962，而不是新的数组的内存地址：
   ![图片描述](http://img.mukewang.com/5d8835c20001596615780740.png)

从上面 4 张截图，我们可以得到迭代过程中，即使 CopyOnWriteArrayList 的结构发生变动了，也不会抛出 ConcurrentModificationException 异常的原因：CopyOnWriteArrayList 迭代持有的是老数组的引用，而 CopyOnWriteArrayList 每次的数据变动，都会产生新的数组，对老数组的值不会产生影响，所以迭代也可以正常进行。



### 6 总结

当我们需要在线程不安全场景下使用 List 时，建议使用 CopyOnWriteArrayList，CopyOnWriteArrayList 通过锁 + 数组拷贝 + volatile 之间的相互配合，实现了 List 的线程安全，我们抛弃 Java 的这种实现，如果让我们自己实现，你又将如何实现呢？

## **16 ConcurrentHashMap 源码解析和设计思路**

### 引导语

当我们碰到线程不安全场景下，需要使用 Map 的时候，我们第一个想到的 API 估计就是 ConcurrentHashMap，ConcurrentHashMap 内部封装了锁和各种数据结构来保证访问 Map 是线程安全的，接下来我们一一来看下，和 HashMap 相比，多了哪些数据结构，又是如何保证线程安全的。



### 1 类注释

我们从类注释上大概可以得到如下信息：

1. 所有的操作都是线程安全的，我们在使用时，无需再加锁；
2. 多个线程同时进行 put、remove 等操作时并不会阻塞，可以同时进行，和 HashTable 不同，HashTable 在操作时，会锁住整个 Map；
3. 迭代过程中，即使 Map 结构被修改，也不会抛 ConcurrentModificationException 异常；
4. 除了数组 + 链表 + 红黑树的基本结构外，新增了转移节点，是为了保证扩容时的线程安全的节点；
5. 提供了很多 Stream 流式方法，比如说：forEach、search、reduce 等等。

从类注释中，我们可以看出 ConcurrentHashMap 和 HashMap 相比，新增了转移节点的数据结构，至于底层如何实现线程安全，转移节点的具体细节，暂且看不出来，接下来我们细看源码。



### 2 结构

虽然 ConcurrentHashMap 的底层数据结构，和方法的实现细节和 HashMap 大体一致，但两者在类结构上却没有任何关联，我们看下 ConcurrentHashMap 的类图：
![图片描述](http://img.mukewang.com/5d883afd0001a01a04670199.png)
看 ConcurrentHashMap 源码，我们会发现很多方法和代码和 HashMap 很相似，有的同学可能会问，为什么不继承 HashMap 呢？继承的确是个好办法，但尴尬的是，ConcurrentHashMap 都是在方法中间进行一些加锁操作，也就是说加锁把方法切割了，继承就很难解决这个问题。

ConcurrentHashMap 和 HashMap 两者的相同之处：

1. 数组、链表结构几乎相同，所以底层对数据结构的操作思路是相同的（只是思路相同，底层实现不同）；
2. 都实现了 Map 接口，继承了 AbstractMap 抽象类，所以大多数的方法也都是相同的，HashMap 有的方法，ConcurrentHashMap 几乎都有，所以当我们需要从 HashMap 切换到 ConcurrentHashMap 时，无需关心两者之间的兼容问题。

不同之处：

1. 红黑树结构略有不同，HashMap 的红黑树中的节点叫做 TreeNode，TreeNode 不仅仅有属性，还维护着红黑树的结构，比如说查找，新增等等；ConcurrentHashMap 中红黑树被拆分成两块，TreeNode 仅仅维护的属性和查找功能，新增了 TreeBin，来维护红黑树结构，并负责根节点的加锁和解锁；
2. 新增 ForwardingNode （转移）节点，扩容的时候会使用到，通过使用该节点，来保证扩容时的线程安全。



### 3 put

ConcurrentHashMap 在 put 方法上的整体思路和 HashMap 相同，但在线程安全方面写了很多保障的代码，我们先来看下大体思路：

1. 如果数组为空，初始化，初始化完成之后，走 2；
2. 计算当前槽点有没有值，没有值的话，cas 创建，失败继续自旋（for 死循环），直到成功，槽点有值的话，走 3；
3. 如果槽点是转移节点(正在扩容)，就会一直自旋等待扩容完成之后再新增，不是转移节点走 4；
4. 槽点有值的，先锁定当前槽点，保证其余线程不能操作，如果是链表，新增值到链表的尾部，如果是红黑树，使用红黑树新增的方法新增；
5. 新增完成之后 check 需不需要扩容，需要的话去扩容。

具体源码如下：

```java
final V putVal(K key, V value, boolean onlyIfAbsent) {
    if (key == null || value == null) throw new NullPointerException();
    //计算hash
    int hash = spread(key.hashCode());
    int binCount = 0;
    for (Node<K,V>[] tab = table;;) {
        Node<K,V> f; int n, i, fh;
        //table是空的，进行初始化
        if (tab == null || (n = tab.length) == 0)
            tab = initTable();
        //如果当前索引位置没有值，直接创建
        else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
            //cas 在 i 位置创建新的元素，当 i 位置是空时，即能创建成功，结束for自循，否则继续自旋
            if (casTabAt(tab, i, null,
                         new Node<K,V>(hash, key, value, null)))
                break;                   // no lock when adding to empty bin
        }
        //如果当前槽点是转移节点，表示该槽点正在扩容，就会一直等待扩容完成
        //转移节点的 hash 值是固定的，都是 MOVED
        else if ((fh = f.hash) == MOVED)
            tab = helpTransfer(tab, f);
        //槽点上有值的
        else {
            V oldVal = null;
            //锁定当前槽点，其余线程不能操作，保证了安全
            synchronized (f) {
                //这里再次判断 i 索引位置的数据没有被修改
                //binCount 被赋值的话，说明走到了修改表的过程里面
                if (tabAt(tab, i) == f) {
                    //链表
                    if (fh >= 0) {
                        binCount = 1;
                        for (Node<K,V> e = f;; ++binCount) {
                            K ek;
                            //值有的话，直接返回
                            if (e.hash == hash &&
                                ((ek = e.key) == key ||
                                 (ek != null && key.equals(ek)))) {
                                oldVal = e.val;
                                if (!onlyIfAbsent)
                                    e.val = value;
                                break;
                            }
                            Node<K,V> pred = e;
                            //把新增的元素赋值到链表的最后，退出自旋
                            if ((e = e.next) == null) {
                                pred.next = new Node<K,V>(hash, key,
                                                          value, null);
                                break;
                            }
                        }
                    }
                    //红黑树，这里没有使用 TreeNode,使用的是 TreeBin，TreeNode 只是红黑树的一个节点
                    //TreeBin 持有红黑树的引用，并且会对其加锁，保证其操作的线程安全
                    else if (f instanceof TreeBin) {
                        Node<K,V> p;
                        binCount = 2;
                        //满足if的话，把老的值给oldVal
                        //在putTreeVal方法里面，在给红黑树重新着色旋转的时候
                        //会锁住红黑树的根节点
                        if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                       value)) != null) {
                            oldVal = p.val;
                            if (!onlyIfAbsent)
                                p.val = value;
                        }
                    }
                }
            }
            //binCount不为空，并且 oldVal 有值的情况，说明已经新增成功了
            if (binCount != 0) {
                // 链表是否需要转化成红黑树
                if (binCount >= TREEIFY_THRESHOLD)
                    treeifyBin(tab, i);
                if (oldVal != null)
                    return oldVal;
                //这一步几乎走不到。槽点已经上锁，只有在红黑树或者链表新增失败的时候
                //才会走到这里，这两者新增都是自旋的，几乎不会失败
                break;
            }
        }
    }
    //check 容器是否需要扩容，如果需要去扩容，调用 transfer 方法去扩容
    //如果已经在扩容中了，check有无完成
    addCount(1L, binCount);
    return null;
}
```

源码中都有非常详细的注释，就不解释了，我们重点说一下，ConcurrentHashMap 在 put 过程中，采用了哪些手段来保证线程安全。



#### 3.1 数组初始化时的线程安全

数组初始化时，首先通过自旋来保证一定可以初始化成功，然后通过 CAS 设置 SIZECTL 变量的值，来保证同一时刻只能有一个线程对数组进行初始化，CAS 成功之后，还会再次判断当前数组是否已经初始化完成，如果已经初始化完成，就不会再次初始化，通过自旋 + CAS + 双重 check 等手段保证了数组初始化时的线程安全，源码如下：

```java
//初始化 table，通过对 sizeCtl 的变量赋值来保证数组只能被初始化一次
private final Node<K,V>[] initTable() {
    Node<K,V>[] tab; int sc;
    //通过自旋保证初始化成功
    while ((tab = table) == null || tab.length == 0) {
        // 小于 0 代表有线程正在初始化，释放当前 CPU 的调度权，重新发起锁的竞争
        if ((sc = sizeCtl) < 0)
            Thread.yield(); // lost initialization race; just spin
        // CAS 赋值保证当前只有一个线程在初始化，-1 代表当前只有一个线程能初始化
        // 保证了数组的初始化的安全性
        else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
            try {
                // 很有可能执行到这里的时候，table 已经不为空了，这里是双重 check
                if ((tab = table) == null || tab.length == 0) {
                    // 进行初始化
                    int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                    @SuppressWarnings("unchecked")
                    Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                    table = tab = nt;
                    sc = n - (n >>> 2);
                }
            } finally {
                sizeCtl = sc;
            }
            break;
        }
    }
    return tab;
}
```



#### 3.2 新增槽点值时的线程安全

此时为了保证线程安全，做了四处优化：

1. 通过自旋死循环保证一定可以新增成功。

在新增之前，通过 `for (Node[] tab = table;;)` 这样的死循环来保证新增一定可以成功，一旦新增成功，就可以退出当前死循环，新增失败的话，会重复新增的步骤，直到新增成功为止。

1. 当前槽点为空时，通过 CAS 新增。

Java 这里的写法非常严谨，没有在判断槽点为空的情况下直接赋值，因为在判断槽点为空和赋值的瞬间，很有可能槽点已经被其他线程赋值了，所以我们采用 CAS 算法，能够保证槽点为空的情况下赋值成功，如果恰好槽点已经被其他线程赋值，当前 CAS 操作失败，会再次执行 for 自旋，再走槽点有值的 put 流程，这里就是自旋 + CAS 的结合。

1. 当前槽点有值，锁住当前槽点。

put 时，如果当前槽点有值，就是 key 的 hash 冲突的情况，此时槽点上可能是链表或红黑树，我们通过锁住槽点，来保证同一时刻只会有一个线程能对槽点进行修改，截图如下：

![图片描述](http://img.mukewang.com/5d883acd0001833304130067.png)

1. 红黑树旋转时，锁住红黑树的根节点，保证同一时刻，当前红黑树只能被一个线程旋转，代码截图如下：
   ![图片描述](http://img.mukewang.com/5d883adb000166bb05880443.png)

通过以上 4 点，保证了在各种情况下的新增（不考虑扩容的情况下），都是线程安全的，通过自旋 + CAS + 锁三大姿势，实现的很巧妙，值得我们借鉴。



#### 3.3 扩容时的线程安全

ConcurrentHashMap 的扩容时机和 HashMap 相同，都是在 put 方法的最后一步检查是否需要扩容，如果需要则进行扩容，但两者扩容的过程完全不同，ConcurrentHashMap 扩容的方法叫做 transfer，从 put 方法的 addCount 方法进去，就能找到 transfer 方法，transfer 方法的主要思路是：

1. 首先需要把老数组的值全部拷贝到扩容之后的新数组上，先从数组的队尾开始拷贝；
2. 拷贝数组的槽点时，先把原数组槽点锁住，保证原数组槽点不能操作，成功拷贝到新数组时，把原数组槽点赋值为转移节点；
3. 这时如果有新数据正好需要 put 到此槽点时，发现槽点为转移节点，就会一直等待，所以在扩容完成之前，该槽点对应的数据是不会发生变化的；
4. 从数组的尾部拷贝到头部，每拷贝成功一次，就把原数组中的节点设置成转移节点；
5. 直到所有数组数据都拷贝到新数组时，直接把新数组整个赋值给数组容器，拷贝完成。

关键源码如下：

```java
// 扩容主要分 2 步，第一新建新的空数组，第二移动拷贝每个元素到新数组中去
// tab：原数组，nextTab：新数组
private final void transfer(Node<K,V>[] tab, Node<K,V>[] nextTab) {
    // 老数组的长度
    int n = tab.length, stride;
    if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
        stride = MIN_TRANSFER_STRIDE; // subdivide range
    // 如果新数组为空，初始化，大小为原数组的两倍，n << 1
    if (nextTab == null) {            // initiating
        try {
            @SuppressWarnings("unchecked")
            Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n << 1];
            nextTab = nt;
        } catch (Throwable ex) {      // try to cope with OOME
            sizeCtl = Integer.MAX_VALUE;
            return;
        }
        nextTable = nextTab;
        transferIndex = n;
    }
    // 新数组的长度
    int nextn = nextTab.length;
    // 代表转移节点，如果原数组上是转移节点，说明该节点正在被扩容
    ForwardingNode<K,V> fwd = new ForwardingNode<K,V>(nextTab);
    boolean advance = true;
    boolean finishing = false; // to ensure sweep before committing nextTab
    // 无限自旋，i 的值会从原数组的最大值开始，慢慢递减到 0
    for (int i = 0, bound = 0;;) {
        Node<K,V> f; int fh;
        while (advance) {
            int nextIndex, nextBound;
            // 结束循环的标志
            if (--i >= bound || finishing)
                advance = false;
            // 已经拷贝完成
            else if ((nextIndex = transferIndex) <= 0) {
                i = -1;
                advance = false;
            }
            // 每次减少 i 的值
            else if (U.compareAndSwapInt
                     (this, TRANSFERINDEX, nextIndex,
                      nextBound = (nextIndex > stride ?
                                   nextIndex - stride : 0))) {
                bound = nextBound;
                i = nextIndex - 1;
                advance = false;
            }
        }
        // if 任意条件满足说明拷贝结束了
        if (i < 0 || i >= n || i + n >= nextn) {
            int sc;
            // 拷贝结束，直接赋值，因为每次拷贝完一个节点，都在原数组上放转移节点，所以拷贝完成的节点的数据一定不会再发生变化。
            // 原数组发现是转移节点，是不会操作的，会一直等待转移节点消失之后在进行操作。
            // 也就是说数组节点一旦被标记为转移节点，是不会再发生任何变动的，所以不会有任何线程安全的问题
            // 所以此处直接赋值，没有任何问题。
            if (finishing) {
                nextTable = null;
                table = nextTab;
                sizeCtl = (n << 1) - (n >>> 1);
                return;
            }
            if (U.compareAndSwapInt(this, SIZECTL, sc = sizeCtl, sc - 1)) {
                if ((sc - 2) != resizeStamp(n) << RESIZE_STAMP_SHIFT)
                    return;
                finishing = advance = true;
                i = n; // recheck before commit
            }
        }
        else if ((f = tabAt(tab, i)) == null)
            advance = casTabAt(tab, i, null, fwd);
        else if ((fh = f.hash) == MOVED)
            advance = true; // already processed
        else {
            synchronized (f) {
                // 进行节点的拷贝
                if (tabAt(tab, i) == f) {
                    Node<K,V> ln, hn;
                    if (fh >= 0) {
                        int runBit = fh & n;
                        Node<K,V> lastRun = f;
                        for (Node<K,V> p = f.next; p != null; p = p.next) {
                            int b = p.hash & n;
                            if (b != runBit) {
                                runBit = b;
                                lastRun = p;
                            }
                        }
                        if (runBit == 0) {
                            ln = lastRun;
                            hn = null;
                        }
                        else {
                            hn = lastRun;
                            ln = null;
                        }
                        // 如果节点只有单个数据，直接拷贝，如果是链表，循环多次组成链表拷贝
                        for (Node<K,V> p = f; p != lastRun; p = p.next) {
                            int ph = p.hash; K pk = p.key; V pv = p.val;
                            if ((ph & n) == 0)
                                ln = new Node<K,V>(ph, pk, pv, ln);
                            else
                                hn = new Node<K,V>(ph, pk, pv, hn);
                        }
                        // 在新数组位置上放置拷贝的值
                        setTabAt(nextTab, i, ln);
                        setTabAt(nextTab, i + n, hn);
                        // 在老数组位置上放上 ForwardingNode 节点
                        // put 时，发现是 ForwardingNode 节点，就不会再动这个节点的数据了
                        setTabAt(tab, i, fwd);
                        advance = true;
                    }
                    // 红黑树的拷贝
                    else if (f instanceof TreeBin) {
                        // 红黑树的拷贝工作，同 HashMap 的内容，代码忽略
                        …………
                        // 在老数组位置上放上 ForwardingNode 节点
                        setTabAt(tab, i, fwd);
                        advance = true;
                    }
                }
            }
        }
    }
}
```

扩容中的关键点，就是如何保证是线程安全的，小结有如下几点：

1. 拷贝槽点时，会把原数组的槽点锁住；
2. 拷贝成功之后，会把原数组的槽点设置成转移节点，这样如果有数据需要 put 到该节点时，发现该槽点是转移节点，会一直等待，直到扩容成功之后，才能继续 put，可以参考 put 方法中的 helpTransfer 方法；
3. 从尾到头进行拷贝，拷贝成功就把原数组的槽点设置成转移节点。
4. 等扩容拷贝都完成之后，直接把新数组的值赋值给数组容器，之前等待 put 的数据才能继续 put。

扩容方法还是很有意思的，通过在原数组上设置转移节点，put 时碰到转移节点时会等待扩容成功之后才能 put 的策略，来保证了整个扩容过程中肯定是线程安全的，因为数组的槽点一旦被设置成转移节点，在没有扩容完成之前，是无法进行操作的。



### 4 get

ConcurrentHashMap 读的话，就比较简单，先获取数组的下标，然后通过判断数组下标的 key 是否和我们的 key 相等，相等的话直接返回，如果下标的槽点是链表或红黑树的话，分别调用相应的查找数据的方法，整体思路和 HashMap 很像，源码如下：

```java
public V get(Object key) {
    Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
    //计算hashcode
    int h = spread(key.hashCode());
    //不是空的数组 && 并且当前索引的槽点数据不是空的
    //否则该key对应的值不存在，返回null
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (e = tabAt(tab, (n - 1) & h)) != null) {
        //槽点第一个值和key相等，直接返回
        if ((eh = e.hash) == h) {
            if ((ek = e.key) == key || (ek != null && key.equals(ek)))
                return e.val;
        }
        //如果是红黑树或者转移节点，使用对应的find方法
        else if (eh < 0)
            return (p = e.find(h, key)) != null ? p.val : null;
        //如果是链表，遍历查找
        while ((e = e.next) != null) {
            if (e.hash == h &&
                ((ek = e.key) == key || (ek != null && key.equals(ek))))
                return e.val;
        }
    }
    return null;
}
```



### 5 总结

本文摘取 ConcurrentHashMap 两个核心的方法讲解了一下，特别是 put 方法，采取了很多手段来保证了线程安全，是平时面试时的重中之重，大家可以尝试 debug 来调试一下源码，其他方法感兴趣的话，可以尝试去 GitHub 上去查看源码。

## **17 并发 List、Map源码面试题**

### 引导语

并发 List 和 Map 是技术面时常问的问题，问的问题也都比较深入，有很多问题都是面试官自创的，市面上找不到，所以说通过背题的方式，这一关大部分是过不了的，只有我们真正理解了 API 内部的实现，阅读过源码，才能自如应对各种类型的面试题，接着我们来看一下并发 List、Map 源码相关的面试题集。



### 1 CopyOnWriteArrayList 相关



#### 1.1 和 ArrayList 相比有哪些相同点和不同点？

答：相同点：底层的数据结构是相同的，都是数组的数据结构，提供出来的 API 都是对数组结构进行操作，让我们更好的使用。

不同点：后者是线程安全的，在多线程环境下使用，无需加锁，可直接使用。



#### 1.2 CopyOnWriteArrayList 通过哪些手段实现了线程安全？

答：主要有：1. 数组容器被 volatile 关键字修饰，保证了数组内存地址被任意线程修改后，都会通知到其他线程；

1. 对数组的所有修改操作，都进行了加锁，保证了同一时刻，只能有一个线程对数组进行修改，比如我在 add 时，就无法 remove；
2. 修改过程中对原数组进行了复制，是在新数组上进行修改的，修改过程中，不会对原数组产生任何影响。

通过以上三点保证了线程安全。



#### 1.3 在 add 方法中，对数组进行加锁后，不是已经是线程安全了么，为什么还需要对老数组进行拷贝？

答：的确，对数组进行加锁后，能够保证同一时刻，只有一个线程能对数组进行 add，在同单核 CPU 下的多线程环境下肯定没有问题，但我们现在的机器都是多核 CPU，如果我们不通过复制拷贝新建数组，修改原数组容器的内存地址的话，是无法触发 volatile 可见性效果的，那么其他 CPU 下的线程就无法感知数组原来已经被修改了，就会引发多核 CPU 下的线程安全问题。

假设我们不复制拷贝，而是在原来数组上直接修改值，数组的内存地址就不会变，而数组被 volatile 修饰时，必须当数组的内存地址变更时，才能及时的通知到其他线程，内存地址不变，仅仅是数组元素值发生变化时，是无法把数组元素值发生变动的事实，通知到其它线程的。



#### 1.4 对老数组进行拷贝，会有性能损耗，我们平时使用需要注意什么么？

答：主要有：

1. 在批量操作时，尽量使用 addAll、removeAll 方法，而不要在循环里面使用 add、remove 方法，主要是因为 for 循环里面使用 add 、remove 的方式，在每次操作时，都会进行一次数组的拷贝(甚至多次)，非常耗性能，而 addAll、removeAll 方法底层做了优化，整个操作只会进行一次数组拷贝，由此可见，当批量操作的数据越多时，批量方法的高性能体现的越明显。



#### 1.5 为什么 CopyOnWriteArrayList 迭代过程中，数组结构变动，不会抛出ConcurrentModificationException 了

答：主要是因为 CopyOnWriteArrayList 每次操作时，都会产生新的数组，而迭代时，持有的仍然是老数组的引用，所以我们说的数组结构变动，是用新数组替换了老数组，老数组的结构并没有发生变化，所以不会抛出异常了。



#### 1.6 插入的数据正好在 List 的中间，请问两种 List 分别拷贝数组几次？为什么？

答：ArrayList 只需拷贝一次，假设插入的位置是 2，只需要把位置 2 （包含 2）后面的数据都往后移动一位即可，所以拷贝一次。

CopyOnWriteArrayList 拷贝两次，因为 CopyOnWriteArrayList 多了把老数组的数据拷贝到新数组上这一步，可能有的同学会想到这种方式：先把老数组拷贝到新数组，再把 2 后面的数据往后移动一位，这的确是一种拷贝的方式，但 CopyOnWriteArrayList 底层实现更加灵活，而是：把老数组 0 到 2 的数据拷贝到新数组上，预留出新数组 2 的位置，再把老数组 3～ 最后的数据拷贝到新数组上，这种拷贝方式可以减少我们拷贝的数据，虽然是两次拷贝，但拷贝的数据却仍然是老数组的大小，设计的非常巧妙。



### 2 ConcurrentHashMap 相关



#### 2.1ConcurrentHashMap 和 HashMap 的相同点和不同点

答：相同点：1. 都是数组 + 链表 +红黑树的数据结构，所以基本操作的思想相同；

1. 都实现了 Map 接口，继承了 AbstractMap 抽象类，所以两者的方法大多都是相似的，可以互相切换。

不同点：1. ConcurrentHashMap 是线程安全的，在多线程环境下，无需加锁，可直接使用；

1. 数据结构上，ConcurrentHashMap 多了转移节点，主要用于保证扩容时的线程安全。



#### 2.2 ConcurrentHashMap 通过哪些手段保证了线程安全。

答：主要有以下几点：

1. 储存 Map 数据的数组被 volatile 关键字修饰，一旦被修改，立马就能通知其他线程，因为是数组，所以需要改变其内存值，才能真正的发挥出 volatile 的可见特性；
2. put 时，如果计算出来的数组下标索引没有值的话，采用无限 for 循环 + CAS 算法，来保证一定可以新增成功，又不会覆盖其他线程 put 进去的值；
3. 如果 put 的节点正好在扩容，会等待扩容完成之后，再进行 put ，保证了在扩容时，老数组的值不会发生变化；
4. 对数组的槽点进行操作时，会先锁住槽点，保证只有当前线程才能对槽点上的链表或红黑树进行操作；
5. 红黑树旋转时，会锁住根节点，保证旋转时的线程安全。



#### 2.3 描述一下 CAS 算法在 ConcurrentHashMap 中的应用？

答：CAS 其实是一种乐观锁，一般有三个值，分别为：赋值对象，原值，新值，在执行的时候，会先判断内存中的值是否和原值相等，相等的话把新值赋值给对象，否则赋值失败，整个过程都是原子性操作，没有线程安全问题。

ConcurrentHashMap 的 put 方法中，有使用到 CAS ，是结合无限 for 循环一起使用的，步骤如下：

1. 计算出数组索引下标，拿出下标对应的原值；
2. CAS 覆盖当前下标的值，赋值时，如果发现内存值和 1 拿出来的原值相等，执行赋值，退出循环，否则不赋值，转到 3；
3. 进行下一次 for 循环，重复执行 1，2，直到成功为止。

可以看到这样做的好处，第一是不会盲目的覆盖原值，第二是一定可以赋值成功。



#### 2.4 ConcurrentHashMap 是如何发现当前槽点正在扩容的。

答：ConcurrentHashMap 新增了一个节点类型，叫做转移节点，当我们发现当前槽点是转移节点时（转移节点的 hash 值是 -1），即表示 Map 正在进行扩容。



#### 2.5 发现槽点正在扩容时，put 操作会怎么办？

答：无限 for 循环，或者走到扩容方法中去，帮助扩容，一直等待扩容完成之后，再执行 put 操作。



#### 2.6 两种 Map 扩容时，有啥区别？

答：区别很大，HashMap 是直接在老数据上面进行扩容，多线程环境下，会有线程安全的问题，而 ConcurrentHashMap 就不太一样，扩容过程是这样的：

1. 从数组的队尾开始拷贝；
2. 拷贝数组的槽点时，先把原数组槽点锁住，拷贝成功到新数组时，把原数组槽点赋值为转移节点；
3. 从数组的尾部拷贝到头部，每拷贝成功一次，就把原数组的槽点设置成转移节点；
4. 直到所有数组数据都拷贝到新数组时，直接把新数组整个赋值给数组容器，拷贝完成。

简单来说，通过扩容时给槽点加锁，和发现槽点正在扩容就等待的策略，保证了 ConcurrentHashMap 可以慢慢一个一个槽点的转移，保证了扩容时的线程安全，转移节点比较重要，平时问的人也比较多。



#### 2.7 ConcurrentHashMap 在 Java 7 和 8 中关于线程安全的做法有啥不同？

答：非常不一样，拿 put 方法为例，Java 7 的做法是：

1. 把数组进行分段，找到当前 key 对应的是那一段；
2. 将当前段锁住，然后再根据 hash 寻找对应的值，进行赋值操作。

Java 7 的做法比较简单，缺点也很明显，就是当我们需要 put 数据时，我们会锁住改该数据对应的某一段，这一段数据可能会有很多，比如我只想 put 一个值，锁住的却是一段数据，导致这一段的其他数据都不能进行写入操作，大大的降低了并发性的效率。Java 8 解决了这个问题，从锁住某一段，修改成锁住某一个槽点，提高了并发效率。

不仅仅是 put，删除也是，仅仅是锁住当前槽点，缩小了锁的范围，增大了效率。



### 3 总结

因为目前大多数公司都已经在使用 Java 8 了，所以大部分面试内容还是以 Java 8 的 API 为主，特别是 CopyOnWriteArrayList 和 ConcurrentHashMap 两个 API，文章毕竟篇幅有限，建议大家多多阅读剩余源码。

## **18 场景集合：并发 List、Map的应用场景**

### 引导语

并发 List、Map 使用最多的就是 CopyOnWriteArrayList 和 ConcurrentHashMap，在考虑 API 时，我们也无需迟疑，这两个并发类在安全和性能方面都很好，我们都可以直接使用。

并发的场景很多，但归根结底其实就是共享变量被多个线程同时访问，也就是说 CopyOnWriteArrayList 或 ConcurrentHashMap 会被作为共享变量，本节我们会以流程引擎为案例，现身说法，增加一下大家的工作经验积累。

流程引擎在实际工作中经常被使用，其主要功能就是对我们需要完成的事情，进行编排和组装，比如在淘宝下单流程中，我们一共会执行 20 个 Spring Bean，流程引擎就可以帮助我们调起 20 个 Spring Bean，并帮助我们去执行，本文介绍的重点在于如何使用 Map + List 来设计流程引擎的数据结构，以及其中需要注意到的线程安全的问题。



### 1 嵌套 Map，简单流程引擎

市面上有很多流程引擎，比如说 Activiti、Flowable、Camunda 等等，功能非常齐全，但我们本小节只实现一种最最简单的流程引擎，只要能对我们需要完成的事情进行编排，并能依次的调用就行。



#### 1.1 流程引擎设计思路

我们认为每个流程都会做 4 个阶段的事情，阶段主要是指在整个流程中，大概可以分为几个大的步骤，每个阶段可以等同为大的步骤，分别如下：

1. 参数校验，主要是对流程的入参数进行校验；
2. 业务校验，主要是对当前流程中的业务进行逻辑校验；
3. 事务中落库，主要把数据落库，控制事务；
4. 事务后事件，我们在数据落库，事务提交之后，可能会做一些其他事情，比如说发消息出来等等。

以上每个大的阶段，都会做一些粒度较细的事情，比如说业务校验，我们可能会对两个业务对象进行校验，那么此时业务校验阶段就会做两件事情，每件具体的事情，我们叫做领域行为，在实际项目中，一个领域行为一般都是一个 Spring Bean。

综上所述，流程引擎嵌套数据结构就是：流程 -> 阶段 -> 领域行为，前者对应后者，都是一对一或者一对多的关系。

我们以在淘宝上买东西时，下单为例，下单指的是我们在淘宝选择好了商品和优惠券后，点击购买按钮时触发的动作。

为了方便举例，我们假设在淘宝上买电视和电影票，在后端，会分别对应着两个下单流程，我们画图示意一下：
![图片描述](http://img.mukewang.com/5d88856f000149cd12740928.png)
上图中，左右两个黑色长方形大框代表着两个流程，流程下面有多个阶段，阶段用蓝色表示，每个阶段下面有多个领域行为，用红色表示。

可以看到两个流程中，都包含有四个阶段，阶段都是相同的，但每个阶段中的领域行为，有的相同，有的却是特有的。

三个概念，每个概念层层嵌套，整体组装起来，用来表示一个流程，那么这个数据结构，我们应该如何表示呢？

使用 Map + List 即可！



#### 1.2 数据结构的定义

流程的数据结构定义分成两步：

1. 定义出阶段、领域行为基础概念；
2. 把阶段、领域行为、流程概念组合起来，定义出流程的数据结构。

首先给阶段定义一个枚举，如下 StageEnum 代表流程中的阶段或步骤：

```java
public enum StageEnum {
  PARAM_VALID("PARAM_VALID", "参数校验"),

  BUSINESS_VALID("BUSINESS_VALID", "业务校验"),

  IN_TRANSACTION("IN_TRANSACTION", "事务中落库"),

  AFTER_TRANSACTION("AFTER_TRANSACTION", "事务后事件"),
  ;

  private String code;
  private String desc;

  StageEnum(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
```

领域行为我们无需定义，目前通用的技术框架都是 Spring Boot，领域行为都是 Spring Bean，为了简单起见，我们给领域行为定义了一个接口，每个领域行为都要实现这个接口，方便我们编排，接口如下：

```java
/**
 * 领域行为
 * author  wenhe
 * date 2019/8/11
 */
public interface DomainAbilityBean {

  /**
   * 领域行为的方法入口
   */
  FlowContent invoke(FlowContent content);

}
```

接着我们使用 Map + List 来定义流程，定义如下：

```java
/**
 * 第一个 key 是流程的名字
 * 第二个 map 的 key 是阶段，为 StageEnum 枚举，值为多个领域行为的集合
 */
Map<String,Map<StageEnum,List<DomainAbilityBean>>> flowMap
```

至此，我们定义出了，简单流程引擎的数据结构，流程引擎看着很复杂，利用 Map + List 的组合，就巧妙的定义好了。



### 2 容器初始化时，本地缓存使用

我们定义好 Map 后，我们就需要去使用他，我们使用的大体步骤为：

1. 项目启动时，把所有的流程信息初始化到 flowMap(刚刚定义的流程的数据结构叫做 flowMap) 中去，可能是从数据库中加载，也可能是从 xml 文件中加载；
2. 查找流程时，直接从 flowMap 中获取即可。



#### 2.1 初始化

以上两步最为关键的点就是 flowMap 必须是可以随时访问到的，所有我们会把 flowMap 作为共享变量使用，也就是会被 static final 关键字所修饰，我们首先来 mock 一下把所有信息初始化到 flowMap 中去的代码，如下：

```java
@Component
public class FlowCenter {

  /**
   * flowMap 是共享变量，方便访问，并且是 ConcurrentHashMap
   */
  public static final Map<String, Map<StageEnum, List<DomainAbilityBean>>> flowMap
      = Maps.newConcurrentMap();

  /**
   * PostConstruct 注解的意思就是
   * 在容器启动成功之后，执行 init 方法，初始化 flowMap
   */
  @PostConstruct
  public void init() {
      // 初始化 flowMap，可能是从数据库，或者 xml 文件中加载 map
  }

}
```

以上代码，关键地方在于三点：

1. flowMap 被 static final 修饰，是个共享变量，方便访问；
2. flowMap 是 ConcurrentHashMap，所以我们所有的操作都无需加锁，比如我们在 init 方法中，对 flowMap 进行初始化，就无需加锁，因为 ConcurrentHashMap 本身已经保证了线程安全；
3. 这里我们初始化的时机是在容器启动的时候，在实际的工作中，我们经常在容器启动的时候，把不会经常发生变动的数据，放到类似 List、Map 这样的共享变量中，这样当我们频繁要使用的时候，直接从内存中读取即可。



#### 2.2 使用

那我们实际使用的时候，只需要告诉 flowMap 当前是那个流程的那个阶段，就可以返回该流程该阶段下面的所有领域行为了，我们写了一个流程引擎使用的工具类入口，如下：

```java
// 流程引擎对外的 API
public class FlowStart {

  /**
   * 流程引擎开始
   *
   * @param flowName 流程的名字
   */
  public void start(String flowName, FlowContent content) {
    invokeParamValid(flowName, content);
    invokeBusinessValid(flowName, content);
    invokeInTramsactionValid(flowName, content);
    invokeAfterTramsactionValid(flowName, content);
  }
  // 执行参数校验
  private void invokeParamValid(String flowName, FlowContent content) {
    stageInvoke(flowName, StageEnum.PARAM_VALID, content);
  }
  // 执行业务校验
  private void invokeBusinessValid(String flowName, FlowContent content) {
    stageInvoke(flowName, StageEnum.BUSINESS_VALID, content);
  }
  // 执行事务中
  private void invokeInTramsactionValid(String flowName, FlowContent content) {
    stageInvoke(flowName, StageEnum.IN_TRANSACTION, content);
  }
  // 执行事务后
  private void invokeAfterTramsactionValid(String flowName, FlowContent content) {
    stageInvoke(flowName, StageEnum.AFTER_TRANSACTION, content);
  }
		
  // 批量执行 Spring Bean
  private void stageInvoke(String flowName, StageEnum stage, FlowContent content) {
    List<DomainAbilityBean>
        domainAbilitys =
        FlowCenter.flowMap.getOrDefault(flowName, Maps.newHashMap()).get(stage);
    if (CollectionUtils.isEmpty(domainAbilitys)) {
      throw new RuntimeException("找不到该流程对应的领域行为" + flowName);
    }
    for (DomainAbilityBean domainAbility : domainAbilitys) {
      domainAbility.invoke(content);
    }
  }

}
```

从代码中可以看到，我们在流程引擎的入口，只要根据参数校验、业务校验、事务中、事务后四个阶段，从 flowMap 中得到领域行为的集合，然后对领域行为进行顺序执行即可。

我们在使用时，直接使用上述类的 start 方法即可。

当然以上演示的流程引擎只是一个大的框架，还有很多地方需要改进的地方，比如如何找到 flowName，如何初始化 flowMap，但这些都不是本节重点，本节主要想通过流程引擎案例来说明几点：

1. 把 List 和 Map 作为共享变量非常常见，就像咱们这种项目启动时，从数据库中把数据捞出来，然后封装成 List 或 Map 的结构，这样做的优点就是节约资源，不用每次用的时候都去查数据库，直接从内存中获取即可；
2. 并发场景下，我们可以放心的使用 CopyOnWriteArrayList 和 ConcurrentHashMap 两个并发类，首先用 static final 对两者进行修饰，使其成为共享变量，接着在写入或者查询的时候，无需加锁，两个 API 内部已经实现了加锁的功能了；
3. 有一点需要澄清一下，就是 CopyOnWriteArrayList 和 ConcurrentHashMap 只能作为单机的共享变量，如果是分布式系统，多台机器的情况下，这样做不行了，需要使用分布式缓存了。



### 3 总结

本节内容，以流程引擎为例，说明了如何使用 Map + List 的嵌套结构设计流程引擎，以及在并发情况下，如何安全的使用 List 和 Map。

本案列是高并发项目的真实案例，感兴趣的同学可以在此流程引擎框架基础上进行细节补充，实现可运行的流程引擎。

# **第4章 队列**

## **19 LinkedBlockingQueue 源码解析**

### 引导语

说到队列，大家的反应可能是我从来都没有用过，应该是不重要的 API 吧。如果这么想，那就大错特错了，我们平时使用到的线程池、读写锁、消息队列等等技术和框架，底层原理都是队列，所以我们万万不可轻视队列，队列是很多高级 API 的基础，学好队列，对自己深入 Java 学习非常重要。

本文主要以 LinkedBlockingQueue 队列为例，详细描述一下底层具体的实现。



### 1 整体架构

LinkedBlockingQueue 中文叫做链表阻塞队列，这个命名很好，从命名上就知道其底层数据结构是链表，并且队列是可阻塞的。接下来，我们就从整体结构上看看 LinkedBlockingQueue。



#### 1.1 类图

首先我们来看下 LinkedBlockingQueue 类图，如下：
![图片描述](http://img.mukewang.com/5d9db50c00011ed210740736.png)从类图中，我们大概可以看出两条路径：

1. AbstractQueue -> AbstractCollection -> Collection ->Iterable 这条路径依赖，主要是想复用 Collection 和 迭代器的一些操作，这些我们在说集合的时候，都知道这些类是干什么，能干什么，就不细说了；
2. BlockingQueue -> Queue -> Collection，BlockingQueue 和 Queue 是新出来的两个接口，我们重点说一下。

Queue 是最基础的接口，几乎所有的队列实现类都会实现这个接口，该接口定义出了队列的三大类操作：

新增操作：

1. add 队列满的时候抛出异常；
2. offer 队列满的时候返回 false。

查看并删除操作：

1. remove 队列空的时候抛异常；
2. poll 队列空的时候返回 null。

只查看不删除操作：

1. element 队列空的时候抛异常；
2. peek 队列空的时候返回 null。

一共 6 种方法，除了以上分类方法，也可以分成两类：

1. 遇到队列满或空的时候，抛异常，如 add、remove、element；
2. 遇到队列满或空的时候，返回特殊值，如 offer、poll、peek。

实际上，这些都比较难记忆。每次需要使用的时候，我都会看会源码，才能想起这个方法是抛异常还是返回特殊值。

BlockingQueue 在 Queue 的基础上加上了阻塞的概念，比如一直阻塞，还是阻塞一段时间。为了方便记忆，我们画一个表格，如下：

|                         | 抛异常  | 特殊值           | 一直阻塞 | 阻塞一段时间               |
| :---------------------- | :------ | :--------------- | :------- | :------------------------- |
| 新增操作–队列满         | add     | offer 返回 false | put      | offer 过超时时间返回 false |
| 查看并删除操作–队列空   | remove  | poll 返回 null   | take     | poll 过超时时间返回 null   |
| 只查看不删除操作–队列空 | element | peek 返回 null   | 暂无     | 暂无                       |

PS: remove 方法，BlockingQueue 类注释中定义的是抛异常，但 LinkedBlockingQueue 中 remove 方法实际是返回 false。
从表格中可以看到，在新增和查看并删除两大类操作上，BlockingQueue 增加了阻塞的功能，而且可以选择一直阻塞，或者阻塞一段时间后，返回特殊值。



#### 1.2 类注释

我们看看从 LinkedBlockingQueue 的类注释中能得到那些信息：

1. 基于链表的阻塞队列，其底层的数据结构是链表；
2. 链表维护先入先出队列，新元素被放在队尾，获取元素从队头部拿；
3. 链表大小在初始化的时候可以设置，默认是 Integer 的最大值；
4. 可以使用 Collection 和 Iterator 两个接口的所有操作，因为实现了两者的接口。



#### 1.3 内部构成

LinkedBlockingQueue 内部构成简单来说，分成三个部分：链表存储 + 锁 + 迭代器，我们来看下源码。

```java
// 链表结构 begin
//链表的元素
static class Node<E> {
    E item;

    //当前元素的下一个，为空表示当前节点是最后一个
    Node<E> next;

    Node(E x) { item = x; }
}

//链表的容量，默认 Integer.MAX_VALUE
private final int capacity;

//链表已有元素大小，使用 AtomicInteger，所以是线程安全的
private final AtomicInteger count = new AtomicInteger();

//链表头
transient Node<E> head;

//链表尾
private transient Node<E> last;
// 链表结构 end

// 锁 begin
//take 时的锁
private final ReentrantLock takeLock = new ReentrantLock();

// take 的条件队列，condition 可以简单理解为基于 ASQ 同步机制建立的条件队列
private final Condition notEmpty = takeLock.newCondition();

// put 时的锁，设计两把锁的目的，主要为了 take 和 put 可以同时进行
private final ReentrantLock putLock = new ReentrantLock();

// put 的条件队列
private final Condition notFull = putLock.newCondition();
// 锁 end

// 迭代器 
// 实现了自己的迭代器
private class Itr implements Iterator<E> {
………………
}
```

从代码上来看，结构是非常清晰的，三种结构各司其职：

1. 链表的作用是为了保存当前节点，节点中的数据可以是任意东西，是一个泛型，比如说队列被应用到线程池时，节点就是线程，比如队列被应用到消息队列中，节点就是消息，节点的含义主要看队列被使用的场景；
2. 锁有 take 锁和 put 锁，是为了保证队列操作时的线程安全，设计两种锁，是为了 take 和 put 两种操作可以同时进行，互不影响。



#### 1.4 初始化

初始化有三种方式：

1. 指定链表容量大小；
2. 不指定链表容量大小，默认是 Integer 的最大值；
3. 对已有集合数据进行初始化。

源码如下：

```java
// 不指定容量，默认 Integer 的最大值
public LinkedBlockingQueue() {
    this(Integer.MAX_VALUE);
}
// 指定链表容量大小，链表头尾相等，节点值（item）都是 null
public LinkedBlockingQueue(int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException();
    this.capacity = capacity;
    last = head = new Node<E>(null);
}

// 已有集合数据进行初始化
public LinkedBlockingQueue(Collection<? extends E> c) {
    this(Integer.MAX_VALUE);
    final ReentrantLock putLock = this.putLock;
    putLock.lock(); // Never contended, but necessary for visibility
    try {
        int n = 0;
        for (E e : c) {
            // 集合内的元素不能为空
            if (e == null)
                throw new NullPointerException();
            // capacity 代表链表的大小，在这里是 Integer 的最大值
            // 如果集合类的大小大于 Integer 的最大值，就会报错
            // 其实这个判断完全可以放在 for 循环外面，这样可以减少 Integer 的最大值次循环(最坏情况)
            if (n == capacity)
                throw new IllegalStateException("Queue full");
            enqueue(new Node<E>(e));
            ++n;
        }
        count.set(n);
    } finally {
        putLock.unlock();
    }
}
```

对于初始化源码，我们说明两点：

1. 初始化时，容量大小是不会影响性能的，只影响在后面的使用，因为初始化队列太小，容易导致没有放多少就会报队列已满的错误；
2. 在对给定集合数据进行初始化时，源码给了一个不优雅的示范，我们不反对在每次 for 循环的时候，都去检查当前链表的大小是否超过容量，但我们希望在 for 循环开始之前就做一步这样的工作。举个列子，给定集合大小是 1 w，链表大小是 9k，按照现在代码实现，只能在 for 循环 9k 次时才能发现，原来给定集合的大小已经大于链表大小了，导致 9k 次循环都是在浪费资源，还不如在 for 循环之前就 check 一次，如果 1w > 9k，直接报错即可。



### 2 阻塞新增

新增有多种方法，如：add、put、offer，三者的区别上文有说。我们拿 put 方法为例，put 方法在碰到队列满的时候，会一直阻塞下去，直到队列不满时，并且自己被唤醒时，才会继续去执行，源码如下：

```java
// 把e新增到队列的尾部。
// 如果有可以新增的空间的话，直接新增成功，否则当前线程陷入等待
public void put(E e) throws InterruptedException {
    // e 为空，抛出异常
    if (e == null) throw new NullPointerException();
    // 预先设置 c 为 -1，约定负数为新增失败
    int c = -1;
    Node<E> node = new Node<E>(e);
    final ReentrantLock putLock = this.putLock;
    final AtomicInteger count = this.count;
    // 设置可中断锁
    putLock.lockInterruptibly();
    try {
        // 队列满了
        // 当前线程阻塞，等待其他线程的唤醒(其他线程 take 成功后就会唤醒此处被阻塞的线程)
        while (count.get() == capacity) {
            // await 无限等待
            notFull.await();
        }

        // 队列没有满，直接新增到队列的尾部
        enqueue(node);

        // 新增计数赋值,注意这里 getAndIncrement 返回的是旧值
        // 这里的 c 是比真实的 count 小 1 的
        c = count.getAndIncrement();

        // 如果链表现在的大小 小于链表的容量，说明队列未满
        // 可以尝试唤醒一个 put 的等待线程
        if (c + 1 < capacity)
            notFull.signal();

    } finally {
        // 释放锁
        putLock.unlock();
    }
    // c==0，代表队列里面有一个元素
    // 会尝试唤醒一个take的等待线程
    if (c == 0)
        signalNotEmpty();
}
// 入队，把新元素放到队尾
private void enqueue(Node<E> node) {
    last = last.next = node;
}
```

从源码中我们可以总结以下几点：

1. 往队列新增数据，第一步是上锁，所以新增数据是线程安全的；
2. 队列新增数据，简单的追加到链表的尾部即可；
3. 新增时，如果队列满了，当前线程是会被阻塞的，阻塞的底层使用是锁的能力，底层实现其它也和队列相关，原理我们在锁章节会说到；
4. 新增数据成功后，在适当时机，会唤起 put 的等待线程（队列不满时），或者 take 的等待线程（队列不为空时），这样保证队列一旦满足 put 或者 take 条件时，立马就能唤起阻塞线程，继续运行，保证了唤起的时机不被浪费。

以上就是 put 方法的原理，至于 offer 方法阻塞超过一端时间后，仍未成功，就会直接返回默认值的实现，和 put 方法相比只修改了几行代码，如下截图：

![图片描述](http://img.mukewang.com/5d9db53e000110c724481374.png)



### 3 阻塞删除

删除的方法也很多，我们主要看两个关键问题：

1. 删除的原理是怎样的；
2. 查看并删除和只查看不删除两种的区别是如何实现的。

首先我们来看第一个问题，我们以 take 方法为例，说明一下查看并删除的底层源码：

```java
// 阻塞拿数据
public E take() throws InterruptedException {
    E x;
    // 默认负数，代表失败
    int c = -1;
    // count 代表当前链表数据的真实大小
    final AtomicInteger count = this.count;
    final ReentrantLock takeLock = this.takeLock;
    takeLock.lockInterruptibly();
    try {
        // 空队列时，阻塞，等待其他线程唤醒
        while (count.get() == 0) {
            notEmpty.await();
        }
        // 非空队列，从队列的头部拿一个出来
        x = dequeue();
        // 减一计算，注意 getAndDecrement 返回的值是旧值
        // c 比真实的 count 大1
        c = count.getAndDecrement();
        
        // 如果队列里面有值，从 take 的等待线程里面唤醒一个。
        // 意思是队列里面有值啦,唤醒之前被阻塞的线程
        if (c > 1)
            notEmpty.signal();
    } finally {
        // 释放锁
        takeLock.unlock();
    }
    // 如果队列空闲还剩下一个，尝试从 put 的等待线程中唤醒一个
    if (c == capacity)
        signalNotFull();
    return x;
}
// 队头中取数据
private E dequeue() {
    Node<E> h = head;
    Node<E> first = h.next;
    h.next = h; // help GC
    head = first;
    E x = first.item;
    first.item = null;// 头节点指向 null，删除
    return x;
}
```

整体流程和 put 很相似，都是先上锁，然后从队列的头部拿出数据，如果队列为空，会一直阻塞到队列有值为止。

而查看不删除元素更加简单，直接把队列头的数据拿出来即可，我们以 peek 为例，源码如下：

```java
// 查看并不删除元素，如果队列为空，返回 null
public E peek() {
    // count 代表队列实际大小，队列为空，直接返回 null
    if (count.get() == 0)
        return null;
    final ReentrantLock takeLock = this.takeLock;
    takeLock.lock();
    try {
        // 拿到队列头
        Node<E> first = head.next;
        // 判断队列头是否为空，并返回
        if (first == null)
            return null;
        else
            return first.item;
    } finally {
        takeLock.unlock();
    }
}
```

可以看出，查看并删除，和查看不删除两者从队头拿数据的逻辑不太一致，从而导致一个会删除，一个不会删除队头数据。



### 4 总结

本文通过 LinkedBlockingQueue 的源码，来介绍了下链表队列，当队列满和空的场景下，新增和删除数据时，队列有啥变化。

队列本身就是一个阻塞工具，我们可以把这个工具应用到各种阻塞场景中，比如说队列应用到线程池，当线程池跑满时，我们把新的请求都放到阻塞队列中等待；队列应用到消息队列，当消费者处理能力有限时，我们可以把消息放到队列中等待，让消费者慢慢消费；每应用到一个新的场景中，都是一个新的技术工具，所以学好队列，用处很大。

## **20 SynchronousQueue 源码解析**

### 引导语

SynchronousQueue 是比较独特的队列，其本身是没有容量大小，比如我放一个数据到队列中，我是不能够立马返回的，我必须等待别人把我放进去的数据消费掉了，才能够返回。SynchronousQueue 在消息队列技术中间件中被大量使用，本文就来从底层实现来看下 SynchronousQueue 到底是如何做到的。



### 1 整体架构

SynchronousQueue 的整体设计比较抽象，在内部抽象出了两种算法实现，一种是先入先出的队列，一种是后入先出的堆栈，两种算法被两个内部类实现，而直接对外的 put，take 方法的实现就非常简单，都是直接调用两个内部类的 transfer 方法进行实现，整体的调用关系如下图所示：

![图片描述](http://img.mukewang.com/5da53a4a0001ce6924601406.png)



#### 1.1 类注释

源码的类注释往往能给我带来很多疑问和有用的信息，我们来看下类注释都说了什么：

1. 队列不存储数据，所以没有大小，也无法迭代；
2. 插入操作的返回必须等待另一个线程完成对应数据的删除操作，反之亦然；
3. 队列由两种数据结构组成，分别是后入先出的堆栈和先入先出的队列，堆栈是非公平的，队列是公平的。

看到类注释，大家是不是有一些疑问，比如第二点是如何做到的？堆栈又是如何实现的呢？接下来我们一点一点揭晓。



#### 1.2 类图

SynchronousQueue 整体类图和 LinkedBlockingQueue 相似，都是实现了 BlockingQueue 接口，但因为其不储存数据结构，有一些方法是没有实现的，比如说 isEmpty、size、contains、remove 和迭代等方法，这些方法都是默认实现，如下截图：

![图片描述](http://img.mukewang.com/5da53a3e0001e3c213841432.png)



#### 1.3 结构细节

SynchronousQueue 底层结构和其它队列完全不同，有着独特的两种数据结构：队列和堆栈，我们一起来看下数据结构：

```java
    // 堆栈和队列共同的接口
    // 负责执行 put or take
    abstract static class Transferer<E> {
        // e 为空的，会直接返回特殊值，不为空会传递给消费者
        // timed 为 true，说明会有超时时间
        abstract E transfer(E e, boolean timed, long nanos);
    }

    // 堆栈 后入先出 非公平
    // Scherer-Scott 算法
    static final class TransferStack<E> extends Transferer<E> {
    }

    // 队列 先入先出 公平
    static final class TransferQueue<E> extends Transferer<E> {
    }

    private transient volatile Transferer<E> transferer;

    // 无参构造器默认为非公平的
    public SynchronousQueue(boolean fair) {
        transferer = fair ? new TransferQueue<E>() : new TransferStack<E>();
    }
```

从源码中我们可以得到几点：

1. 堆栈和队列都有一个共同的接口，叫做 Transferer，该接口有个方法：transfer，该方法很神奇，会承担 take 和 put 的双重功能；
2. 在我们初始化的时候，是可以选择是使用堆栈还是队列的，如果你不选择，默认的就是堆栈，类注释中也说明了这一点，堆栈的效率比队列更高。

接下来我们来看下堆栈和队列的具体实现。



### 2 非公平的堆栈



#### 2.1 堆栈的结构

首先我们来介绍下堆栈的整体结构，如下：

![图片描述](http://img.mukewang.com/5da53a31000147a007280674.png)

从上图中我们可以看到，我们有一个大的堆栈池，池的开口叫做堆栈头，put 的时候，就往堆栈池中放数据。take 的时候，就从堆栈池中拿数据，两者操作都是在堆栈头上操作数据，从图中可以看到，越靠近堆栈头，数据越新，所以每次 take 的时候，都会拿到堆栈头的最新数据，这就是我们说的后入先出，也就是非公平的。

图中 SNode 就是源码中栈元素的表示，我们看下源码：

```java
static final class SNode {
    // 栈的下一个，就是被当前栈压在下面的栈元素
    volatile SNode next;
    // 节点匹配，用来判断阻塞栈元素能被唤醒的时机
    // 比如我们先执行 take，此时队列中没有数据，take 被阻塞了，栈元素为 SNode1
    // 当有 put 操作时，会把当前 put 的栈元素赋值给 SNode1 的 match 属性，并唤醒 take 操作
    // 当 take 被唤醒，发现 SNode1 的 match 属性有值时，就能拿到 put 进来的数据，从而返回
    volatile SNode match;
    // 栈元素的阻塞是通过线程阻塞来实现的，waiter 为阻塞的线程
    volatile Thread waiter;
    // 未投递的消息，或者未消费的消息
    Object item;             
} 
```



#### 2.2 入栈和出栈

入栈指的是使用 put 等方法，把数据放到堆栈池中，出栈指的使用 take 等方法，把数据从堆栈池中拿出来，操作的对象都是堆栈头，虽然两者的一个是从堆栈头拿数据，一个是放数据，但底层实现的方法却是同一个，源码如下：

```java
// transfer 方法思路比较复杂，因为 take 和 put 两个方法都揉在了一起
@SuppressWarnings("unchecked")
E transfer(E e, boolean timed, long nanos) {
    SNode s = null; // constructed/reused as needed
    // e 为空，说明是 take 方法，不为空是 put 方法
    int mode = (e == null) ? REQUEST : DATA;
    // 自旋
    for (;;) {
        // 拿出头节点，有几种情况
        // 1：头节点为空，说明队列中还没有数据
        // 2：头节点不为空，并且是 take 类型的，说明头节点线程正等着拿数据。
        // 3：头节点不为空，并且是 put 类型的，说明头节点线程正等着放数据。
        SNode h = head;
        // 栈头为空，说明队列中还没有数据。
        // 栈头不为空，并且栈头的类型和本次操作一致，比如都是 put，那么就把
        // 本次 put 操作放到该栈头的前面即可，让本次 put 能够先执行
        if (h == null || h.mode == mode) {  // empty or same-mode
            // 设置了超时时间，并且 e 进栈或者出栈要超时了，
            // 就会丢弃本次操作，返回 null 值。
            // 如果栈头此时被取消了，丢弃栈头，取下一个节点继续消费
            if (timed && nanos <= 0) {      // can't wait
                // 栈头操作被取消
                if (h != null && h.isCancelled())
                    // 丢弃栈头，把栈头后一个元素作为栈头
                    casHead(h, h.next);     // pop cancelled node
                //栈头是空的，直接返回 null
                else
                    return null;
            // 没有超时，直接把 e 作为新的栈头
            } else if (casHead(h, s = snode(s, e, h, mode))) {
                // e 等待出栈，一种是空队列 take，一种是 put
                SNode m = awaitFulfill(s, timed, nanos);
                if (m == s) {               // wait was cancelled
                    clean(s);
                    return null;
                }
                // 本来 s 是栈头的，现在 s 不是栈头了，s 后面又来了一个数，把新的数据作为栈头
                if ((h = head) != null && h.next == s)
                    casHead(h, s.next);     // help s's fulfiller
                return (E) ((mode == REQUEST) ? m.item : s.item);
            }
        // 栈头正在等待其他线程 put 或 take
        // 比如栈头正在阻塞，并且是 put 类型，而此次操作正好是 take 类型，走此处
        } else if (!isFulfilling(h.mode)) { // try to fulfill
            // 栈头已经被取消，把下一个元素作为栈头
            if (h.isCancelled())            // already cancelled
                casHead(h, h.next);         // pop and retry
            // snode 方法第三个参数 h 代表栈头，赋值给 s 的 next 属性
            else if (casHead(h, s=snode(s, e, h, FULFILLING|mode))) {
                for (;;) { // loop until matched or waiters disappear
                    // m 就是栈头，通过上面 snode 方法刚刚赋值
                    SNode m = s.next;       // m is s's match
                    if (m == null) {        // all waiters are gone
                        casHead(s, null);   // pop fulfill node
                        s = null;           // use new node next time
                        break;              // restart main loop
                    }
                    SNode mn = m.next;
                     // tryMatch 非常重要的方法，两个作用：
                     // 1 唤醒被阻塞的栈头 m，2 把当前节点 s 赋值给 m 的 match 属性
                     // 这样栈头 m 被唤醒时，就能从 m.match 中得到本次操作 s
                     // 其中 s.item 记录着本次的操作节点，也就是记录本次操作的数据
                    if (m.tryMatch(s)) {
                        casHead(s, mn);     // pop both s and m
                        return (E) ((mode == REQUEST) ? m.item : s.item);
                    } else                  // lost match
                        s.casNext(m, mn);   // help unlink
                }
            }
        } else {                            // help a fulfiller
            SNode m = h.next;               // m is h's match
            if (m == null)                  // waiter is gone
                casHead(h, null);           // pop fulfilling node
            else {
                SNode mn = m.next;
                if (m.tryMatch(h))          // help match
                    casHead(h, mn);         // pop both h and m
                else                        // lost match
                    h.casNext(m, mn);       // help unlink
            }
        }
    }
}
```

从源码中密密麻麻的注释，我们就可以看出来此方法比较复杂，我们总结一下大概的操作思路：

1. 判断是 put 方法还是 take 方法；
2. 判断栈头数据是否为空，如果为空或者栈头的操作和本次操作一致，是的话走 3，否则走 5；
3. 判断操作有无设置超时时间，如果设置了超时时间并且已经超时，返回 null，否则走 4；
4. 如果栈头为空，把当前操作设置成栈头，或者栈头不为空，但栈头的操作和本次操作相同，也把当前操作设置成栈头，并看看其它线程能否满足自己，不能满足则阻塞自己。比如当前操作是 take，但队列中没有数据，则阻塞自己；
5. 如果栈头已经是阻塞住的，需要别人唤醒的，判断当前操作能否唤醒栈头，可以唤醒走 6，否则走 4；
6. 把自己当作一个节点，赋值到栈头的 match 属性上，并唤醒栈头节点；
7. 栈头被唤醒后，拿到 match 属性，就是把自己唤醒的节点的信息，返回。

在整个过程中，有一个节点阻塞的方法，实现原理如下：

```java
SNode awaitFulfill(SNode s, boolean timed, long nanos) {
    // deadline 死亡时间，如果设置了超时时间的话，死亡时间等于当前时间 + 超时时间，否则就是 0
    final long deadline = timed ? System.nanoTime() + nanos : 0L;
    Thread w = Thread.currentThread();
    // 自旋的次数，如果设置了超时时间，会自旋 32 次，否则自旋 512 次。
    // 比如本次操作是 take 操作，自选次数后，仍没有其他线程 put 数据进来
    // 就会阻塞，有超时时间的，会阻塞固定的时间，否则一致阻塞下去
    int spins = (shouldSpin(s) ?
                 (timed ? maxTimedSpins : maxUntimedSpins) : 0);
    for (;;) {
        // 当前线程有无被打断，如果过了超时时间，当前线程就会被打断
        if (w.isInterrupted())
            s.tryCancel();

        SNode m = s.match;
        if (m != null)
            return m;
        if (timed) {
            nanos = deadline - System.nanoTime();
            // 超时了，取消当前线程的等待操作
            if (nanos <= 0L) {
                s.tryCancel();
                continue;
            }
        }
        // 自选次数减少 1
        if (spins > 0)
            spins = shouldSpin(s) ? (spins-1) : 0;
        // 把当前线程设置成 waiter，主要是通过线程来完成阻塞和唤醒
        else if (s.waiter == null)
            s.waiter = w; // establish waiter so can park next iter
        else if (!timed)
            // 通过 park 进行阻塞，这个我们在锁章节中会说明
            LockSupport.park(this);
        else if (nanos > spinForTimeoutThreshold)
            LockSupport.parkNanos(this, nanos);
    }
}
```

从节点阻塞代码中，我们可以发现，其阻塞的策略，并不是一上来就阻塞住，而是在自旋一定次数后，仍然没有其它线程来满足自己的要求时，才会真正的阻塞住。



### 3 公平的队列

首先我们来看一下队列中的每个元素的组成：

```java
/** 队列头 */
transient volatile QNode head;
/** 队列尾 */
transient volatile QNode tail;

// 队列的元素
static final class QNode {
    // 当前元素的下一个元素
    volatile QNode next;         
    // 当前元素的值，如果当前元素被阻塞住了，等其他线程来唤醒自己时，其他线程
    // 会把自己 set 到 item 里面
    volatile Object item;         // CAS'ed to or from null
    // 可以阻塞住的当前线程
    volatile Thread waiter;       // to control park/unpark
    // true 是 put，false 是 take
    final boolean isData;
}  
```

公平的队列主要使用的是 TransferQueue 内部类的 transfer 方法，我们一起来看下源码：

```java
E transfer(E e, boolean timed, long nanos) {

    QNode s = null; // constructed/reused as needed
    // true 是 put，false 是 get
    boolean isData = (e != null);

    for (;;) {
        // 队列头和尾的临时变量,队列是空的时候，t=h
        QNode t = tail;
        QNode h = head;
        // tail 和 head 没有初始化时，无限循环
        // 虽然这种 continue 非常耗cpu，但感觉不会碰到这种情况
        // 因为 tail 和 head 在 TransferQueue 初始化的时候，就已经被赋值空节点了
        if (t == null || h == null)
            continue;
        // 首尾节点相同，说明是空队列
        // 或者尾节点的操作和当前节点操作一致
        if (h == t || t.isData == isData) {
            QNode tn = t.next;
            // 当 t 不是 tail 时，说明 tail 已经被修改过了
            // 因为 tail 没有被修改的情况下，t 和 tail 必然相等
            // 因为前面刚刚执行赋值操作： t = tail
            if (t != tail)
                continue;
            // 队尾后面的值还不为空，t 还不是队尾，直接把 tn 赋值给 t，这是一步加强校验。
            if (tn != null) {
                advanceTail(t, tn);
                continue;
            }
            //超时直接返回 null
            if (timed && nanos <= 0)        // can't wait
                return null;
            //构造node节点
            if (s == null)
                s = new QNode(e, isData);
            //如果把 e 放到队尾失败，继续递归放进去
            if (!t.casNext(null, s))        // failed to link in
                continue;

            advanceTail(t, s);              // swing tail and wait
            // 阻塞住自己
            Object x = awaitFulfill(s, e, timed, nanos);
            if (x == s) {                   // wait was cancelled
                clean(t, s);
                return null;
            }

            if (!s.isOffList()) {           // not already unlinked
                advanceHead(t, s);          // unlink if head
                if (x != null)              // and forget fields
                    s.item = s;
                s.waiter = null;
            }
            return (x != null) ? (E)x : e;
        // 队列不为空，并且当前操作和队尾不一致
        // 也就是说当前操作是队尾是对应的操作
        // 比如说队尾是因为 take 被阻塞的，那么当前操作必然是 put
        } else {                            // complementary-mode
            // 如果是第一次执行，此处的 m 代表就是 tail
            // 也就是这行代码体现出队列的公平，每次操作时，从头开始按照顺序进行操作
            QNode m = h.next;               // node to fulfill
            if (t != tail || m == null || h != head)
                continue;                   // inconsistent read

            Object x = m.item;
            if (isData == (x != null) ||    // m already fulfilled
                x == m ||                   // m cancelled
                // m 代表栈头
                // 这里把当前的操作值赋值给阻塞住的 m 的 item 属性
                // 这样 m 被释放时，就可得到此次操作的值
                !m.casItem(x, e)) {         // lost CAS
                advanceHead(h, m);          // dequeue and retry
                continue;
            }
            // 当前操作放到队头
            advanceHead(h, m);              // successfully fulfilled
            // 释放队头阻塞节点
            LockSupport.unpark(m.waiter);
            return (x != null) ? (E)x : e;
        }
    }
}
```

源码比较复杂，我们需要搞清楚的是，线程被阻塞住后，当前线程是如何把自己的数据传给阻塞线程的。为了方便说明，我们假设线程 1 往队列中 take 数据 ，被阻塞住了，变成阻塞线程 A ，然后线程 2 开始往队列中 put 数据 B，大致的流程是这样的：

1. 线程 1 从队列中拿数据，发现队列中没有数据，于是被阻塞，成为 A ；
2. 线程 2 往队尾 put 数据，会从队尾往前找到第一个被阻塞的节点，假设此时能找到的就是节点 A，然后线程 B 把将 put 的数据放到节点 A 的 item 属性里面，并唤醒线程 1；
3. 线程 1 被唤醒后，就能从 A.item 里面拿到线程 2 put 的数据了，线程 1 成功返回。

从这个过程中，我们能看出公平主要体现在，每次 put 数据的时候，都 put 到队尾上，而每次拿数据时，并不是直接从堆头拿数据，而是从队尾往前寻找第一个被阻塞的线程，这样就会按照顺序释放被阻塞的线程。



### 4 总结

SynchronousQueue 源码比较复杂，建议大家进行源码的 debug 来学习源码，为大家准备了调试类：SynchronousQueueDemo，大家可以下载源码自己调试一下，这样学起来应该会更加轻松一点。

## **21 DelayQueue 源码解析**

### 引导语

之前我们说的阻塞队列，都是资源足够时立马执行。本章我们说的队列比较特殊，是一种延迟队列，意思是延迟执行，并且可以设置延迟多久之后执行，比如设置过 5 秒钟之后再执行，在一些延迟执行的场景被大量使用，比如说延迟对账等等。



### 1 整体设计

DelayQueue 延迟队列底层使用的是锁的能力，比如说要在当前时间往后延迟 5 秒执行，那么当前线程就会沉睡 5 秒，等 5 秒后线程被唤醒时，如果能获取到资源的话，线程即可立马执行。原理上似乎很简单，但内部实现却很复杂，有很多难点，比如当运行资源不够，多个线程同时被唤醒时，如何排队等待？比如说在何时阻塞？何时开始执行等等？接下来我们从源码角度来看下是如何实现的。



#### 1.1 类注释

类注释上比较简单，只说了三个概念：

1. 队列中元素将在过期时被执行，越靠近队头，越早过期；
2. 未过期的元素不能够被 take；
3. 不允许空元素。

这三个概念，其实就是三个问题，下文我们会一一看下这三点是如何实现的。



#### 1.2 类图

DelayQueue 的类图和之前的队列一样，不多说，关键是 DelayQueue 类上是有泛型的，如下：

```java
public class DelayQueue<E extends Delayed> extends AbstractQueue<E>
    implements BlockingQueue<E> {
```

从泛型中可以看出，DelayQueue 中的元素必须是 Delayed 的子类，Delayed 是表达延迟能力的关键接口，其继承了 Comparable 接口，并定义了还剩多久过期的方法，如下：

```java
public interface Delayed extends Comparable<Delayed> {
    long getDelay(TimeUnit unit);
}
```

也就是说 DelayQueue 队列中的元素必须是实现 Delayed 接口和 Comparable 接口的，并覆写了 getDelay 方法和 compareTo 的方法才行，不然在编译时，编译器就会提醒我们元素必须强制实现 Delayed 接口。

除此之外 DelayQueue 还大量使用了 PriorityQueue 队列的大量功能，这个和 SynchronousQueue 队列很像，大量复用了其它基础类的逻辑，代码示例如下：

![图片描述](http://img.mukewang.com/5da56dcc0001306c13701376.png)PriorityQueue 中文叫做优先级队列，在此处的作用就是可以根据过期时间做优先级排序，让先过期的可以先执行，用来实现类注释中的第一点。

这里的复用的思想还是蛮重要的，我们在源码中经常会遇到这种思想，比如说 LinkedHashMap 复用 HashMap 的能力，Set 复用 Map 的能力，还有此处的 DelayQueue 复用 PriorityQueue 的能力。小结一下，如果想要复用需要做到哪些：

1. 需要把能遇见可复用的功能尽量抽象，并开放出可扩展的地方，比如说 HashMap 在操作数组的方法中，都给 LinkedHashMap 开放出很多 after 开头的方法，便于 LinkedHashMap 进行排序、删除等等；
2. 采用组合或继承两种手段进行复用，比如 LinkedHashMap 采用的继承、 Set 和 DelayQueue 采用的组合，组合的意思就是把可复用的类给依赖进来。



### 2 演示

为了方便大家理解，写了一个演示的 demo，演示了一下：

```java
public class DelayQueueDemo {
	// 队列消息的生产者
  static class Product implements Runnable {
    private final BlockingQueue queue;
    public Product(BlockingQueue queue) {
      this.queue = queue;
    }
    
    @Override
    public void run() {
      try {
        log.info("begin put");
        long beginTime = System.currentTimeMillis();
        queue.put(new DelayedDTO(System.currentTimeMillis() + 2000L,beginTime));//延迟 2 秒执行
        queue.put(new DelayedDTO(System.currentTimeMillis() + 5000L,beginTime));//延迟 5 秒执行
        queue.put(new DelayedDTO(System.currentTimeMillis() + 1000L * 10,beginTime));//延迟 10 秒执行
        log.info("end put");
      } catch (InterruptedException e) {
        log.error("" + e);
      }
    }
  }
	// 队列的消费者
  static class Consumer implements Runnable {
    private final BlockingQueue queue;
    public Consumer(BlockingQueue queue) {
      this.queue = queue;
    }

    @Override
    public void run() {
      try {
        log.info("Consumer begin");
        ((DelayedDTO) queue.take()).run();
        ((DelayedDTO) queue.take()).run();
        ((DelayedDTO) queue.take()).run();
        log.info("Consumer end");
      } catch (InterruptedException e) {
        log.error("" + e);
      }
    }
  }

  @Data
  // 队列元素，实现了 Delayed 接口
  static class DelayedDTO implements Delayed {
    Long s;
    Long beginTime;
    public DelayedDTO(Long s,Long beginTime) {
      this.s = s;
      this.beginTime =beginTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
      return unit.convert(s - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
      return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public void run(){
      log.info("现在已经过了{}秒钟",(System.currentTimeMillis() - beginTime)/1000);
    }
  }
	// demo 运行入口
  public static void main(String[] args) throws InterruptedException {
    BlockingQueue q = new DelayQueue();
    DelayQueueDemo.Product p = new DelayQueueDemo.Product(q);
    DelayQueueDemo.Consumer c = new DelayQueueDemo.Consumer(q);
    new Thread(c).start();
    new Thread(p).start();
  }
}
打印出来的结果如下：
06:57:50.544 [Thread-0] Consumer begin
06:57:50.544 [Thread-1] begin put
06:57:50.551 [Thread-1] end put
06:57:52.554 [Thread-0] 延迟了2秒钟才执行
06:57:55.555 [Thread-0] 延迟了5秒钟才执行
06:58:00.555 [Thread-0] 延迟了10秒钟才执行
06:58:00.556 [Thread-0] Consumer end
```

写这个代码的目的主要想演示一下延迟执行的例子，我们大概的思路是：

1. 新建队列的元素，如 DelayedDTO，必须实现 Delayed 接口，我们在 getDelay 方法中实现了现在离过期时间还剩多久的方法。
2. 定义队列元素的生产者，和消费者，对应着代码中的 Product 和 Consumer。
3. 对生产者和消费者就行初始化和管理，对应着我们的 main 方法。

虽然这只是一个简单的 demo，但实际工作中，我们使用 DelayQueue 基本上就是这种思想，只不过写代码的时候会更加通用和周全，接下来我们来看下 DelayQueue 是如何实现 put 和 take 的。



### 3 放数据

我们以 put 为例，put 调用的是 offer 的方法，offer 的源码如下：

```java
public boolean offer(E e) {
    final ReentrantLock lock = this.lock;
    // 上锁
    lock.lock();
    try {
        // 使用 PriorityQueue 的扩容，排序等能力
        q.offer(e);
        // 如果恰好刚放进去的元素正好在队列头
        // 立马唤醒 take 的阻塞线程，执行 take 操作
        // 如果元素需要延迟执行的话，可以使其更快的沉睡计时
        if (q.peek() == e) {
            leader = null;
            available.signal();
        }
        return true;
    } finally {
        // 释放锁
        lock.unlock();
    }
}
```

可以看到其实底层使用到的是 PriorityQueue 的 offer 方法，我们来看下：

```java
// 新增元素
public boolean offer(E e) {
    // 如果是空元素的话，抛异常
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    // 队列实际大小大于容量时，进行扩容
    // 扩容策略是：如果老容量小于 64，2 倍扩容，如果大于 64，50 % 扩容
    if (i >= queue.length)
        grow(i + 1);
    size = i + 1;
    // 如果队列为空，当前元素正好处于队头
    if (i == 0)
        queue[0] = e;
    else
    // 如果队列不为空，需要根据优先级进行排序
        siftUp(i, e);
    return true;
}
// 按照从小到大的顺序排列
    private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        // k 是当前队列实际大小的位置
        while (k > 0) {
            // 对 k 进行减倍
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            // 如果 x 比 e 大，退出，把 x 放在 k 位置上
            if (key.compareTo((E) e) >= 0)
                break;
            // x 比 e 小，继续循环，直到找到 x 比队列中元素大的位置
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
```

可以看到，PriorityQueue 的 offer 方法主要做了三件事情：

1. 对新增元素进行判空；
2. 对队列进行扩容，扩容策略和集合的扩容策略很相近；
3. 根据元素的 compareTo 方法进行排序，我们希望最终排序的结果是从小到大的，因为我们想让队头的都是过期的数据，我们需要在 compareTo 方法里面实现：通过每个元素的过期时间进行排序，如下：

```java
(int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
```

这样便可实现越快过期的元素越能排到队头。

可以看到，新增数据时，只是使用到了 compareTo 方法，来对队列中的元素进行排序，接下来我们看下，取数据时，是如何操作的。



### 4 拿数据

取数据时，如果发现有元素的过期时间到了，就能拿出数据来，如果没有过期元素，那么线程就会一直阻塞，我们以 take 为例子，来看一下核心源码：

```java
for (;;) {
    // 从队头中拿数据出来
    E first = q.peek();
    // 如果为空，说明队列中，没有数据，阻塞住
    if (first == null)
        available.await();
    else {
        // 获取队头数据的过期时间
        long delay = first.getDelay(NANOSECONDS);
        // 如果过期了，直接返回队头数据
        if (delay <= 0)
            return q.poll();
        // 引用置为 null ，便于 gc，这样可以让线程等待时，回收 first 变量
        first = null;
        // leader 不为空的话，表示当前队列元素之前已经被设置过阻塞时间了
        // 直接阻塞当前线程等待。
        if (leader != null)
            available.await();
        else {
          // 之前没有设置过阻塞时间，按照一定的时间进行阻塞
            Thread thisThread = Thread.currentThread();
            leader = thisThread;
            try {
                // 进行阻塞
                available.awaitNanos(delay);
            } finally {
                if (leader == thisThread)
                    leader = null;
            }
        }
    }
}
```

可以看到阻塞等待的功能底层使用的是锁的能力，这个我们在后面章节中会说到。

以上演示的 take 方法是会无限阻塞，直到队头的过期时间到了才会返回，如果不想无限阻塞，可以尝试 poll 方法，设置超时时间，在超时时间内，队头元素还没有过期的话，就会返回 null。



### 5 总结

DelayQueue 是非常有意思的队列，底层使用了排序和超时阻塞实现了延迟队列，排序使用的是 PriorityQueue 排序能力，超时阻塞使用得是锁的等待能力，可以看出 DelayQueue 其实就是为了满足延迟执行的场景，在已有 API 的基础上进行了封装，我们在工作中，可以学习这种思想，对已有的功能能复用的尽量复用，减少开发的工作量。

## **22 ArrayBlockingQueue 源码解析**

### 引导语

本小节我们来介绍本章最后一个队列：ArrayBlockingQueue。按照字面翻译，中文叫做数组阻塞队列，从名称上看，我们就比较清楚此阻塞队列底层使用的是数组。一说到数组，大家可能会想到 ArrayList 和 HashMap，举新增场景来说 ArrayList 通过 size ++ 找到新增的数组下标位置，HashMap 通过 hash 算法计算出下标位置，那么 ArrayBlockingQueue 是不是也是这两种方法呢？都不是，ArrayBlockingQueue 使用的是一种非常奇妙的方式，我们一起拭目以待。

全文为了方便说明，队头的说法就是数组头，队尾的说法就是数组尾。



### 1 整体架构

我们从类注释上可以得到一些有用的信息：

#### 1.1 类注释

1. 有界的阻塞数组，容量一旦创建，后续大小无法修改；
2. 元素是有顺序的，按照先入先出进行排序，从队尾插入数据数据，从队头拿数据；
3. 队列满时，往队列中 put 数据会被阻塞，队列空时，往队列中拿数据也会被阻塞。

从类注释上可以看出 ArrayBlockingQueue 和一般的数组结构的类不太一样，是不能够动态扩容的，如果队列满了或者空时，take 和 put 都会被阻塞。



#### 1.2 数据结构

```java
// 队列存放在 object 的数组里面
// 数组大小必须在初始化的时候手动设置，没有默认大小
final Object[] items;

// 下次拿数据的时候的索引位置
int takeIndex;

// 下次放数据的索引位置
int putIndex;

// 当前已有元素的大小
int count;

// 可重入的锁
final ReentrantLock lock;

// take的队列
private final Condition notEmpty;

// put的队列
private final Condition notFull;
```

以上代码有两个关键的字段，takeIndex 和 putIndex，分别表示下次拿数据和放数据的索引位置。所以说在新增数据和拿数据时，都无需计算，就能知道应该新增到什么位置，应该从什么位置拿数据。



### 2 初始化

初始化时，有两个重要的参数：数组的大小、是否是公平，源码如下：

```java
public ArrayBlockingQueue(int capacity, boolean fair) {
    if (capacity <= 0)
        throw new IllegalArgumentException();
    this.items = new Object[capacity];
    lock = new ReentrantLock(fair);
    // 队列不为空 Condition，在 put 成功时使用
    notEmpty = lock.newCondition();
    // 队列不满 Condition，在 take 成功时使用
    notFull =  lock.newCondition();
}
```

从源码中我们可以看出，第二个参数是否公平，主要用于读写锁是否公平，如果是公平锁，那么在锁竞争时，就会按照先来先到的顺序，如果是非公平锁，锁竞争时随机的。

对于锁公平和非公平，我们举个例子：比如说现在队列是满的，还有很多线程执行 put 操作，必然会有很多线程阻塞等待，当有其它线程执行 take 时，会唤醒等待的线程，如果是公平锁，会按照阻塞等待的先后顺序，依次唤醒阻塞的线程，如果是非公平锁，会随机唤醒沉睡的线程。

所以说队列满很多线程执行 put 操作时，如果是公平锁，数组元素新增的顺序就是阻塞线程被释放的先后顺序，是有顺序的，而非公平锁，由于阻塞线程被释放的顺序是随机的，所以元素插入到数组的顺序也就不会按照插入的顺序了。

队列空时，也是一样的道理。

ArrayBlockingQueue 通过锁的公平和非公平，轻松实现了数组元素的插入顺序的问题。如果要实现这个功能，你会怎么做呢？会想到利用锁的功能么？其实这种思想我们在文中多次提到，当我们需要完成一件事情时，首先看看已有的 API 能不能满足，如果可以的话，通过继承和组合的方式来实现，ArrayBlockingQueue 就是组合了锁的功能。

初始化时，如果给定了原始数据的话，一定要注意原始数据的大小一定要小于队列的容量，否则会抛异常，如下图所示：

![图片描述](http://img.mukewang.com/5da9962c0001338b13541200.png)
我们写了一个 demo，报错如下：

![图片描述](http://img.mukewang.com/5da9963b00018d9022401200.png)



### 3 新增数据

数据新增都会按照 putIndex 的位置进行新增，源码如下：

```java
// 新增，如果队列满，无限阻塞
public void put(E e) throws InterruptedException {
    // 元素不能为空
    checkNotNull(e);
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
        // 队列如果是满的，就无限等待
        // 一直等待队列中有数据被拿走时，自己被唤醒
        while (count == items.length)
            notFull.await();
        enqueue(e);
    } finally {
        lock.unlock();
    }
}

private void enqueue(E x) {
    // assert lock.getHoldCount() == 1; 同一时刻只能一个线程进行操作此方法
    // assert items[putIndex] == null;
    final Object[] items = this.items;
    // putIndex 为本次插入的位置
    items[putIndex] = x;
    // ++ putIndex 计算下次插入的位置
    // 如果下次插入的位置，正好等于队尾，下次插入就从 0 开始
    if (++putIndex == items.length)
        putIndex = 0;
    count++;
    // 唤醒因为队列空导致的等待线程
    notEmpty.signal();
}
```

从源码中，我们可以看出，其实新增就两种情况：

1. 本次新增的位置居中，直接新增，下图演示的是 putIndex 在数组下标为 5 的位置，还不到队尾，那么可以直接新增，计算下次新增的位置应该是 6；
   ![图片描述](http://img.mukewang.com/5da996520001b78412100296.png)
2. 新增的位置到队尾了，那么下次新增时就要从头开始了，示意图如下：
   ![图片描述](http://img.mukewang.com/5dc385f60001236510260354.png)

上面这张图演示的就是这行代码：`if (++putIndex == items.length) putIndex = 0;`

可以看到当新增到队尾时，下次新增会重新从队头重新开始。



### 4 拿数据

拿数据都是从队头开始拿数据，源码如下：

```java
public E take() throws InterruptedException {
    final ReentrantLock lock = this.lock;
    lock.lockInterruptibly();
    try {
        // 如果队列为空，无限等待
        // 直到队列中有数据被 put 后，自己被唤醒
        while (count == 0)
            notEmpty.await();
        // 从队列中拿数据
        return dequeue();
    } finally {
        lock.unlock();
    }
}

private E dequeue() {
    final Object[] items = this.items;
    // takeIndex 代表本次拿数据的位置，是上一次拿数据时计算好的
    E x = (E) items[takeIndex];
    // 帮助 gc
    items[takeIndex] = null;
    // ++ takeIndex 计算下次拿数据的位置
    // 如果正好等于队尾的话，下次就从 0 开始拿数据
    if (++takeIndex == items.length)
        takeIndex = 0;
    // 队列实际大小减 1
    count--;
    if (itrs != null)
        itrs.elementDequeued();
    // 唤醒被队列满所阻塞的线程
    notFull.signal();
    return x;
}
```

从源码中可以看出，每次拿数据的位置就是 takeIndex 的位置，在找到本次该拿的数据之后，会把 takeIndex 加 1，计算下次拿数据时的索引位置，有个特殊情况是，如果本次拿数据的位置已经是队尾了，那么下次拿数据的位置就要从头开始，就是从 0 开始了。



### 5 删除数据

删除数据很有意思，我们一起来看下核心源码：

```java
// 一共有两种情况：
// 1：删除位置和 takeIndex 的关系：删除位置和 takeIndex 一样，比如 takeIndex 是 2， 而要删除的位置正好也是 2，那么就把位置 2 的数据置为 null ,并重新计算 takeIndex 为 3。
// 2：找到要删除元素的下一个，计算删除元素和 putIndex 的关系
// 如果下一个元素不是 putIndex，就把下一个元素往前移动一位
// 如果下一个元素是 putIndex，把 putIndex 的值修改成删除的位置
void removeAt(final int removeIndex) {
    final Object[] items = this.items;
    // 情况1 如果删除位置正好等于下次要拿数据的位置
    if (removeIndex == takeIndex) {
        // 下次要拿数据的位置直接置空
        items[takeIndex] = null;
        // 要拿数据的位置往后移动一位
        if (++takeIndex == items.length)
            takeIndex = 0;
        // 当前数组的大小减一
        count--;
        if (itrs != null)
            itrs.elementDequeued();
    // 情况 2
    } else {
        final int putIndex = this.putIndex;
        for (int i = removeIndex;;) {
            // 找到要删除元素的下一个
            int next = i + 1;
            if (next == items.length)
                next = 0;
            // 下一个元素不是 putIndex
            if (next != putIndex) {
                // 下一个元素往前移动一位
                items[i] = items[next];
                i = next;
            // 下一个元素是 putIndex
            } else {
                // 删除元素
                items[i] = null;
                // 下次放元素时，应该从本次删除的元素放
                this.putIndex = i;
                break;
            }
        }
        count--;
        if (itrs != null)
            itrs.removedAt(removeIndex);
    }
    notFull.signal();
}
```

删除数据的情况比较复杂，一共有两种情况，第一种情况是 takeIndex == removeIndex，我们画个示意图来看下处理方式：

![图片描述](http://img.mukewang.com/5da9967c0001f1a112600970.png)
第二种情况又分两种：

1. 如果 removeIndex + 1 != putIndex 的话，就把下一个元素往前移动一位，示意图如下：
   ![图片描述](http://img.mukewang.com/5da996880001782712280958.png)
2. 如果 removeIndex + 1 == putIndex 的话，就把 putIndex 的值修改成删除的位置，示意图如下：

![图片描述](http://img.mukewang.com/5da9969c0001254d12300940.png)

ArrayBlockingQueue 的删除方法其实还蛮复杂的，需要考虑到很多特殊的场景。



### 6 总结

ArrayBlockingQueue 底层是有界的数组，整体来说，和其它队列差别不多，需要注意的是，当 takeIndex、putIndex 到队尾的时候，都会重新从 0 开始循环，这点是比较特殊的，在我们学习源码时，需要特别注意。

## **23 队列在源码方面的面试题**

### 引导语

队列在源码方面的面试题，一般面试官会从锁，线程池等知识点作为问题入口，慢慢的问到队列，由于锁、线程池咱们还没有学习到，所以本章就直奔主题，从队列入手，看看队列都有哪些面试题（队列种类很多，本文在说队列的通用特征时，都是在说其大部分队列的通用特征，如有某种队列特征不符，不在一一说明）。



### 1 面试题



#### 1.1 说说你对队列的理解，队列和集合的区别。

答：对队列的理解：

1. 首先队列本身也是个容器，底层也会有不同的数据结构，比如 LinkedBlockingQueue 是底层是链表结构，所以可以维持先入先出的顺序，比如 DelayQueue 底层可以是队列或堆栈，所以可以保证先入先出，或者先入后出的顺序等等，底层的数据结构不同，也造成了操作实现不同；
2. 部分队列（比如 LinkedBlockingQueue ）提供了暂时存储的功能，我们可以往队列里面放数据，同时也可以从队列里面拿数据，两者可以同时进行；
3. 队列把生产数据的一方和消费数据的一方进行解耦，生产者只管生产，消费者只管消费，两者之间没有必然联系，队列就像生产者和消费者之间的数据通道一样，如 LinkedBlockingQueue；
4. 队列还可以对消费者和生产者进行管理，比如队列满了，有生产者还在不停投递数据时，队列可以使生产者阻塞住，让其不再能投递，比如队列空时，有消费者过来拿数据时，队列可以让消费者 hodler 住，等有数据时，唤醒消费者，让消费者拿数据返回，如 ArrayBlockingQueue；
5. 队列还提供阻塞的功能，比如我们从队列拿数据，但队列中没有数据时，线程会一直阻塞到队列有数据可拿时才返回。

队列和集合的区别：

1. 和集合的相同点，队列（部分例外）和集合都提供了数据存储的功能，底层的储存数据结构是有些相似的，比如说 LinkedBlockingQueue 和 LinkedHashMap 底层都使用的是链表，ArrayBlockingQueue 和 ArrayList 底层使用的都是数组。

2. 和集合的区别：

   2.1 部分队列和部分集合底层的存储结构很相似的，但两者为了完成不同的事情，提供的 API 和其底层的操作实现是不同的。

   2.2 队列提供了阻塞的功能，能对消费者和生产者进行简单的管理，队列空时，会阻塞消费者，有其他线程进行 put 操作后，会唤醒阻塞的消费者，让消费者拿数据进行消费，队列满时亦然。

   2.3 解耦了生产者和消费者，队列就像是生产者和消费者之间的管道一样，生产者只管往里面丢，消费者只管不断消费，两者之间互不关心。



#### 1.2 哪些队列具有阻塞的功能，大概是如何阻塞的？

答：队列主要提供了两种阻塞功能，如下：

1. LinkedBlockingQueue 链表阻塞队列和 ArrayBlockingQueue 数组阻塞队列是一类，前者容量是 Integer 的最大值，后者数组大小固定，两个阻塞队列都可以指定容量大小，当队列满时，如果有线程 put 数据，线程会阻塞住，直到有其他线程进行消费数据后，才会唤醒阻塞线程继续 put，当队列空时，如果有线程 take 数据，线程会阻塞到队列不空时，继续 take。
2. SynchronousQueue 同步队列，当线程 put 时，必须有对应线程把数据消费掉，put 线程才能返回，当线程 take 时，需要有对应线程进行 put 数据时，take 才能返回，反之则阻塞，举个例子，线程 A put 数据 A1 到队列中了，此时并没有任何的消费者，线程 A 就无法返回，会阻塞住，直到有线程消费掉数据 A1 时，线程 A 才能返回。



#### 1.3 底层是如何实现阻塞的？

答：队列本身并没有实现阻塞的功能，而是利用 Condition 的等待唤醒机制，阻塞底层实现就是更改线程的状态为沉睡，细节我们在锁小节会说到。



#### 1.4 LinkedBlockingQueue 和 ArrayBlockingQueue 有啥区别。

答：相同点：

1. 两者的阻塞机制大体相同，比如在队列满、空时，线程都会阻塞住。

不同点：

1. LinkedBlockingQueue 底层是链表结构，容量默认是 Interge 的最大值，ArrayBlockingQueue 底层是数组，容量必须在初始化时指定。
2. 两者的底层结构不同，所以 take、put、remove 的底层实现也就不同。



#### 1.5 往队列里面 put 数据是线程安全的么？为什么？

答：是线程安全的，在 put 之前，队列会自动加锁，put 完成之后，锁会自动释放，保证了同一时刻只会有一个线程能操作队列的数据，以 LinkedBlockingQueue 为例子，put 时，会加 put 锁，并只对队尾 tail 进行操作，take 时，会加 take 锁，并只对队头 head 进行操作，remove 时，会同时加 put 和 take 锁，所以各种操作都是线程安全的，我们工作中可以放心使用。



#### 1.6 take 的时候也会加锁么？既然 put 和 take 都会加锁，是不是同一时间只能运行其中一个方法。

答：1：是的，take 时也会加锁的，像 LinkedBlockingQueue 在执行 take 方法时，在拿数据的同时，会把当前数据删除掉，就改变了链表的数据结构，所以需要加锁来保证线程安全。

2：这个需要看情况而言，对于 LinkedBlockingQueue 来说，队列的 put 和 take 都会加锁，但两者的锁是不一样的，所以两者互不影响，可以同时进行的，对于 ArrayBlockingQueue 而言，put 和 take 是同一个锁，所以同一时刻只能运行一个方法。



#### 1.7 工作中经常使用队列的 put、take 方法有什么危害，如何避免。

答：当队列满时，使用 put 方法，会一直阻塞到队列不满为止。

当队列空时，使用 take 方法，会一直阻塞到队列有数据为止。

两个方法都是无限（永远、没有超时时间的意思）阻塞的方法，容易使得线程全部都阻塞住，大流量时，导致机器无线程可用，所以建议在流量大时，使用 offer 和 poll 方法来代替两者，我们只需要设置好超时阻塞时间，这两个方法如果在超时时间外，还没有得到数据的话，就会返回默认值（LinkedBlockingQueue 为例），这样就不会导致流量大时，所有的线程都阻塞住了。

这个也是生产事故常常发生的原因之一，尝试用 put 和 take 方法，在平时自测中根本无法发现，对源码不熟悉的同学也不会意识到会有问题，当线上大流量打进来时，很有可能会发生故障，所以我们平时工作中使用队列时，需要谨慎再谨慎。



#### 1.8 把数据放入队列中后，有木有办法让队列过一会儿再执行？

答：可以的，DelayQueue 提供了这种机制，可以设置一段时间之后再执行，该队列有个唯一的缺点，就是数据保存在内存中，在重启和断电的时候，数据容易丢失，所以定时的时间我们都不会设置很久，一般都是几秒内，如果定时的时间需要设置很久的话，可以考虑采取延迟队列中间件（这种中间件对数据会进行持久化，不怕断电的发生）进行实现。



#### 1.9 DelayQueue 对元素有什么要求么，我把 String 放到队列中去可以么？

答：DelayQueue 要求元素必须实现 Delayed 接口，Delayed 本身又实现了 Comparable 接口，Delayed 接口的作用是定义还剩下多久就会超时，给使用者定制超时时间的，Comparable 接口主要用于对元素之间的超时时间进行排序的，两者结合，就可以让越快过期的元素能够排在前面。

所以把 String 放到 DelayQueue 中是不行的，编译都无法通过，DelayQueue 类在定义的时候，是有泛型定义的，泛型类型必须是 Delayed 接口的子类才行。



#### 1.10 DelayQueue 如何让快过期的元素先执行的？

答：DelayQueue 中的元素都实现 Delayed 和 Comparable 接口的，其内部会使用 Comparable 的 compareTo 方法进行排序，我们可以利用这个功能，在 compareTo 方法中实现过期时间和当前时间的差，这样越快过期的元素，计算出来的差值就会越小，就会越先被执行。



#### 1.11 如何查看 SynchronousQueue 队列的大小？

答：此题是个陷进题，题目首先设定了 SynchronousQueue 是可以查看大小的，实际上 SynchronousQueue 本身是没有容量的，所以也无法查看其容量的大小，其内部的 size 方法都是写死的返回 0。



#### 1.12 SynchronousQueue 底层有几种数据结构，两者有何不同？

答：底层有两种数据结构，分别是队列和堆栈。

两者不同点：

1. 队列维护了先入先出的顺序，所以最先进去队列的元素会最先被消费，我们称为公平的，而堆栈则是先入后出的顺序，最先进入堆栈中的数据可能会最后才会被消费，我们称为不公平的。
2. 两者的数据结构不同，导致其 take 和 put 方法有所差别，具体的可以看 《 SynchronousQueue 源码解析 》章节。



#### 1.13 假设 SynchronousQueue 底层使用的是堆栈，线程 1 执行 take 操作阻塞住了，然后有线程 2 执行 put 操作，问此时线程 2 是如何把 put 的数据传递给 take 的？

答：这是一个好问题，也是理解 SynchronousQueue 的核心问题。

首先线程 1 被阻塞住，此时堆栈头就是线程 1 了，此时线程 2 执行 put 操作，会把 put 的数据赋值给堆栈头的 match 属性，并唤醒线程 1，线程 1 被唤醒后，拿到堆栈头中的 match 属性，就能够拿到 put 的数据了。

严格上说并不是 put 操作直接把数据传递给了 take，而是 put 操作改变了堆栈头的数据，从而 take 可以从堆栈头上直接拿到数据，堆栈头是 take 和 put 操作之间的沟通媒介。



#### 1.14 如果想使用固定大小的队列，有几种队列可以选择，有何不同？

答：可以使用 LinkedBlockingQueue 和 ArrayBlockingQueue 两种队列。

前者是链表，后者是数组，链表新增时，只要建立起新增数据和链尾数据之间的关联即可，数组新增时，需要考虑到索引的位置（takeIndex 和 putIndex 分别记录着下次拿数据、放数据的索引位置），如果增加到了数组最后一个位置，下次就要重头开始新增。



#### 1.15 ArrayBlockingQueue 可以动态扩容么？用到数组最后一个位置时怎么办？

答：不可以的，虽然 ArrayBlockingQueue 底层是数组，但不能够动态扩容的。

假设 put 操作用到了数组的最后一个位置，那么下次 put 就需要从数组 0 的位置重新开始了。

假设 take 操作用到数组的最后一个位置，那么下次 take 的时候也会从数组 0 的位置重新开始。



#### 1.16 ArrayBlockingQueue take 和 put 都是怎么找到索引位置的？是利用 hash 算法计算得到的么？

答：ArrayBlockingQueue 有两个属性，为 takeIndex 和 putIndex，分别标识下次 take 和 put 的位置，每次 take 和 put 完成之后，都会往后加一，虽然底层是数组，但和 HashMap 不同，并不是通过 hash 算法计算得到的。



### 2 总结

队列是锁、线程池等复杂 API 的基础，很多面试官都会在问这些 API 时冷不防的问你队列的知识，如果你回答不好，面试官可能会认为你仅仅是用过锁和线程池，但却对其底层的原理和实现了解的不够全面，所以说队列还是蛮重要的，但队列的源码比较复杂，建议大家可以尝试 debug 的方式来理解源码。

## **24 举一反三：队列在 Java 其它源码中的应用**

### 引导语

队列除了提供 API 供开发者使用外，自身也和 Java 中其他 API 紧密结合，比如线程池和锁，线程池直接使用了队列的 API，锁借鉴了队列的思想，重新实现了队列，线程池和锁都是我们工作中经常使用的 API，也是面试官常问的 API，队列在两者的实现上发挥着至关重要的作用，接下来我们一起来看下。



### 1 队列和线程池的结合



#### 1.1 队列在线程池中的作用

线程池大家应该都使用过，比如我们想新建一个固定大小的线程池，并让运行的线程打印一句话出来，我们会这么写代码：

```java
ExecutorService executorService = Executors.newFixedThreadPool(10);
// submit 是提交任务的意思
// Thread.currentThread() 得到当前线程
executorService.submit(() -> System.out.println(Thread.currentThread().getName() + " is run"));
// 打印结果(我们打印出了当前线程的名字)：
pool-1-thread-1 is run
```

代码中的 Executors 是并发的工具类，主要是为了帮助我们更方便的构造线程池的，其中 newFixedThreadPool 方法表示会构造出固定大小的线程池，我们给的入参是 10，代表线程池最大可以构造 10 个线程出来。

在实际的工作中，我们对流量的大小是无法控制的，这里我们设定的最大是 10 个线程，但如果一下子来了 100 个请求，这时候 10 个线程肯定是忙不过来了，那么剩余的 90 个请求怎么办呢？

这时候就需要队列出马了，我们会把线程无法消化的数据放到队列中去，让数据在队列中排队，等线程有能力消费了，再从队列中拿出来慢慢去消费。

我们画一个图释义一下：

![图片描述](https://raw.githubusercontent.com/woshiamiaojiang/image-hosting/master/5db11ff30001cf3511460486.png)

上图右边表示 10 个线程正在全力消费请求，左边表示剩余请求正在队列中排队，等待消费。

由此可见，队列在线程池中占有很重要的地位，当线程池中的线程忙不过来的时候，请求都可以在队列中等待，从而慢慢地消费。

接下来我们来看下，线程池到底用到了那几种队列类型，分别起的什么作用。



#### 1.2 线程池中使用到的队列的类型

##### 1.2.1 LinkedBlockingQueue 队列的使用

刚刚我们说的 newFixedThreadPool 是一种固定大小的线程池，意思是当线程池初始化好后，线程池里面的线程大小是不会变的了（线程池默认设置是不会回收核心线程数的），我们来看下 newFixedThreadPool 的源码：

```java
// ThreadPoolExecutor 初始化时，第一个参数表示 coreSize，第二个参数是 maxSize，coreSize == maxSize,
// 表示线程池初始化时，线程大小已固定，所以叫做固定(Fixed)线程池。 
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
```

源码中可以看到初始化了 ThreadPoolExecutor，ThreadPoolExecutor 是线程池的 API，我们在线程池章节会细说，它的第五个构造参数就是队列，线程池根据场景会选择不同的队列，此处使用的是 LinkedBlockingQueue，并且是默认参数的 Queue，这说明此阻塞队列的最大容量是 Integer 的最大值，也就是说当线程池的处理能力有限时，阻塞队列中最大可以存放 Integer 最大值个任务。

但我们在实际工作中，常常不建议直接使用 newFixedThreadPool，主要是因为其使用的是 LinkedBlockingQueue 的默认构造器，队列容量太大了，在要求实时响应的请求中，队列容量太大往往危害也很大。

比如说我们用上述的线程池，线程 10 个，队列是 Integer 的最大值，当并发流量很大时，比如来了 1w/qps 请求，这时候 10 个线程根本消费不完，就会有很多请求被阻塞在队列中，虽然 10 个线程仍然在不断地消费，但需要消费完队列中的所有数据是需要时间的，假设需要 3 秒才能全部消费完，而这些实时请求都是有超时时间的，默认超时时间是 2 秒，当时间到达 2 秒时，请求已经超时了，返回报错，可这时候队列中的任务还有很多都在等待消费呢，即使后来消费完成，也无法返回给调用方了。

以上情况就会造成，调用方看到接口是超时报错返回的，但服务端的任务其实还在排队执行，过了 3 秒后，服务端的任务可能都会执行成功，但调用方已经无法感知了，调用方再次调用时，就会发现其实这笔请求已经成功了。

如果调用方是从页面发起的，那么体验就会更差，页面上第一次调用页面报错，用户重新刷新页面时，页面显示上次的请求已经成功了，这个就是很不好的体验了。

所以我们希望队列的大小不要设置成那么大，可以根据实际的消费情况来设置队列的大小，这样就可以保证在接口超时前，队列中排队的请求可以执行完。

场景比较复杂，为了方便理解，我们画了一个图，把整个流程释义一下：
![图片描述](http://img.mukewang.com/5db120020001e74f15260518.png)

这种问题，在实际工作中已经属于非常严重的生产事故了，我们使用时一定要小心。

和 newFixedThreadPool 相同的是，newSingleThreadExecutor 方法底层使用的也是 LinkedBlockingQueue，newSingleThreadExecutor 线程池底层线程只会有一个，这代表着这个线程池一次只能处理一个请求，其余的请求都会在队列中排队等待执行，我们看下 newSingleThreadExecutor 的源码实现：

```java
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        // 前两个参数规定了这个线程池一次只能消费一个线程
        // 第五个参数使用的是 LinkedBlockingQueue,说明当请求超过单线程消费能力时，就会排队
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}
```

可以看到，底层使用的也是 LinkedBlockingQueue 的默认参数，也就是说排队的最大值是 Integer 的最大值。

##### 1.2.2 SynchronousQueue 队列

除了 newFixedThreadPool 方法，在线程池新建时，还有其他的几个方法也对应着不同的队列，我们一起来看下 newCachedThreadPool，newCachedThreadPool 底层对应的是 SynchronousQueue 队列，源码如下：

```java
public static ExecutorService newCachedThreadPool() {
    // 第五个参数是 SynchronousQueue
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
```

SynchronousQueue 队列是没有大小限制的，请求多少队列都能承受的住，可以说这是他的优点，缺点就是每次往队列里面 put 数据时，并不能立马返回，而是需要等待有线程 take 数据之后，才能正常返回，如果请求量大，而消费能力较差时，就会导致大量请求被 hodler 住，必须等到慢慢消费完成之后才能被释放，所以在平时工作使用中也需要慎重。

##### 1.2.3 DelayedWorkQueue

newScheduledThreadPool 代表定时任务线程池，底层源码如下：
![图片描述](http://img.mukewang.com/5db12015000125e222240760.png)

截图从左往右我们可以看到，底层队列使用的是 DelayedWorkQueue 延迟队列，说明线程池底层延时的功能就是 DelayedWorkQueue 队列提供的，新的延迟请求都先到队列中去，延迟时间到了，线程池自然就能从队列中拿出线程进行执行了。

newSingleThreadScheduledExecutor 方法也是和 newScheduledThreadPool 一样的，使用 DelayedWorkQueue 的延迟功能，只不过前者是单个线程执行。



#### 1.3 小结

从线程池的源码中，我们可以看到：

1. 队列在线程池的设计中，起着缓冲数据，延迟执行数据的作用，当线程池消费能力有限时，可以让请求进行排队，让线程池可以慢慢消费。
2. 线程池根据不同的场景，选择使用了 DelayedWorkQueue、SynchronousQueue、LinkedBlockingQueue 多种队列，从而实现自己不同的功能，比如使用 DelayedWorkQueue 的延迟功能来实现定时执行线程池。



### 2 队列和锁的结合

我们平时写锁代码的时候都这么写：

```java
ReentrantLock lock = new ReentrantLock();
try{
    lock.lock();
    // do something
}catch(Exception e){
  //throw Exception;
}finally {
    lock.unlock();
}
```

初始化锁 -> 加锁 -> 执行业务逻辑 -> 释放锁，这是正常的流程，但我们知道同一时刻只能有一个线程才能获得锁的，那么此时其他获取不到锁的线程该怎么办呢？

等待，其他获取不到锁的线程，都会到一个等待队列中去等待，等待锁被释放掉时，再去竞争锁，我们画一个示意图。

![图片描述](data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC)

图中红色标识的就是同步队列，获取不到锁的线程都会到同步队列中去排队，当锁被释放后，同步队列中的线程就又开始去竞争锁。

可以看出队列在锁中起的作用之一，就是帮助管理获取不到锁的线程，让这些线程可以耐心的等待。

同步队列并没有使用现有的队列的 API 去实现，但底层的结构，思想和目前队列是一致的，所以我们学好队列章节，对理解锁的同步队列，用处非常大。



### 3 总结

队列的数据结构真的很重要，在线程池和锁两个重量级 API 中起着非常重要的作用，我们要非常清楚队列底层的大体的数据结构，了解数据是如何入队的，如何出队的，队列这章也是比较复杂的，建议大家多多 debug，我们 github 上也提供了一些 debug 的 demo，大家可以尝试调试起来。

## **25 整体设计：队列设计思想、工作中使用场景**

#### 引导语

本章我们学习了 LinkedBlockingQueue、ArrayBlockingQueue、SynchronousQueue、DelayQueue 四种队列，四种队列底层数据结构各不相同，使用场景也不相同，本章我们从设计思想和使用场景两个大的方向做一些对比和总结。



### 1 设计思想

首先我们画出队列的总体设计图：
![图片描述](http://img.mukewang.com/5db148170001daa312560972.png)

从图中我们可以看出几点：

1. 队列解耦了生产者和消费者，提供了生产者和消费者间关系的多种形式，比如 LinkedBlockingQueue、ArrayBlockingQueue 两种队列就把解耦了生产者和消费者，比如 SynchronousQueue 这种就把生产者和消费者相互对应（生产者的消息被消费者开始消费之后，生产者才能返回，为了方便理解，使用相互对应这个词）；
2. 不同的队列有着不同的数据结构，有链表（LinkedBlockingQueue）、数组（ArrayBlockingQueue）、堆栈（SynchronousQueue）等；
3. 不同的数据结构，决定了入队和出队的姿势是不同的。

接下来我们分别按照这几个方面来总结分析一下。



#### 1.1 队列的数据结构

链表结构的队列就是 LinkedBlockingQueue，其特征如下：

1. 初始大小默认是 Integer 的最大值，也可以设置初始大小；
2. 链表元素通过 next 属性关联下一个元素；
3. 新增是从链表的尾部新增，拿是从链表头开始拿。

数组结构的队列是 ArrayBlockingQueue，特征如下：

1. 容量大小是固定的，不能动态扩容；
2. 有 takeIndex 和 putIndex 两个索引记录下次拿和新增的位置；
3. 当 takeIndex 和 putIndex 到达数组的最后一个位置时，下次都是从 0 开始循环。

SynchronousQueue 有着两种数据结构，分别是队列和堆栈，特征如下：

1. 队列保证了先入先出的数据结构，体现了公平性；
2. 堆栈是先入后出的数据结构，是不公平的，但性能高于先入先出。



#### 1.2 入队和出队的方式

不同的队列有着不同的数据结构，导致其入队和出队的方式也不同：

1. 链表是入队是直接追加到队尾，出队是从链表头拿数据；
2. 数组是有 takeIndex 和 putIndex 两个索引位置记录下次拿和取的位置，如总体设计图，入队直接指向了 putIndex，出队指向了 takeIndex；
3. 堆栈主要都是围绕栈头进行入栈和出栈的。



#### 1.3 生产者和消费者之间的通信机制

从四种队列我们可以看出来生产者和消费者之间有两种通信机制，一种是强关联，一种是无关联。

强关联主要是指 SynchronousQueue 队列，生产者往队列中 put 数据，如果这时候没有消费者消费的话，生产者就会一直阻塞住，是无法返回的；消费者来队列里取数据，如果这时候队列中没有数据，消费者也会一直阻塞住，所以 SynchronousQueue 队列模型中，生产者和消费者是强关联的，如果只有其中一方存在，只会阻塞，是无法传递数据的。

无关联主要是说有数据存储功能的队列，比如说 LinkedBlockingQueue 和 ArrayBlockingQueue，只要队列容器不满，生产者就能放成功，生产者就可以直接返回，和有无消费者一点关系都没有，生产者和消费者完全解耦，通过队列容器的储存功能进行解耦。



### 2 工作中的使用场景

在日常工作中，我们需要根据队列的特征来匹配业务场景，从而决定使用哪种队列，我们总结下各个队列适合使用的场景：



#### 2.1 LinkedBlockingQueue

适合对生产的数据大小不定（时高时低），数据量较大的场景，比如说我们在淘宝上买东西，点击下单按钮时，对应着后台的系统叫做下单系统，下单系统会把下单请求都放到一个线程池里面，这时候我们初始化线程池时，一般会选择 LinkedBlockingQueue，并且设置一个合适的大小，此时选择 LinkedBlockingQueue 主要原因在于：在不高于我们设定的阈值内，队列里面的大小可大可小，不会有任何性能损耗，正好符合下单流量的特点，时大时小。

一般工作中，我们大多数都会选择 LinkedBlockingQueue 队列，但会设置 LinkedBlockingQueue 的最大容量，如果初始化时直接使用默认的 Integer 的最大值，当流量很大，而消费者处理能力很差时，大量请求都会在队列中堆积，会大量消耗机器的内存，就会降低机器整体性能甚至引起宕机，一旦宕机，在队列中的数据都会消失，因为队列的数据是保存在内存中的，一旦机器宕机，内存中的数据都会消失的，所以使用 LinkedBlockingQueue 队列时，建议还是要根据日常的流量设置合适的队列的大小。



#### 2.2 ArrayBlockingQueue

一般用于生产数据固定的场景，比如说系统每天会进行对账，对账完成之后，会固定的产生 100 条对账结果，因为对账结果固定，我们就可以使用 ArrayBlockingQueue 队列，大小可以设置成 100。



#### 2.3 DelayQueue

延迟队列，在工作中经常遇到，主要用于任务不想立马执行，想等待一段时间才执行的场景。

比如说延迟对账，我们在工作中曾经遇到过这样的场景：我们在淘宝上买东西，弹出支付宝付款页面，在我们输入指纹的瞬间，流程主要是前端 -》交易后端 -》支付后端，交易后端调用支付后端主要是为了把我们支付宝的钱划给商家，而交易调用支付的过程中，有小概率的情况，因为网络抖动会发生超时的情况，这时候就需要通过及时的对账来解决这个事情（对账只是解决这个问题的手段之一），我们简单画一个流程图：
![图片描述](http://img.mukewang.com/5db1482c0001d1fd11880618.png)

这是一个真实场景，为了方便描述，已经大大简化了，再说明几点：

1. 交易调用支付的接口，这个接口的作用就是为了把小美的 800 元转给商家小明；
2. 接口调用超时，此时交易系统并不知道 800 有没有成功转给小明，当然想知道的方式有很多，我们选择了对账的方式，对账的目的就是为了知道当前 800 元有没有成功转给小明；
3. 延迟对账的目的，因为支付系统把 800 元转给商家小明也是需要时间的，如果超时之后立马对账，可能转账的动作还在进行中，导致对账的结果不准确，所以需要延迟几秒后再去对账；
4. 对账之后的结果有几种，比如已经成功的把 800 元转给小明了，这时候需要把对账结果告诉交易系统，交易系统更新数据，前端就能够显示转账成功了。

在这个案列中，延迟对账的核心技术就是 DelayQueue，我们大概这么做的：新建对账任务，设置 3 秒之后执行，把任务放到 DelayQueue 中，过了 3 秒之后，就会自动执行对账任务了。

DelayQueue 延迟执行的功能就在这个场景中得到应用。



### 3 总结

我们不会为了阅读源码而读源码，我们读源码的最初目的，是为了提高我们的技术深度，最终目的是为了在不同的场景中，能够选择合适的技术进行落地，本章中解释的一些队列的场景，我们在工作中其实都会遇到，特别是在使用线程池时，使用哪种队列是我们必须思考的一个问题，所以本章先比较了各个队列的适合使用场景，然后举了几个案列进行具体分析，希望大家也能把技术具体落地到实际工作中，使技术推动、辅助业务。

## **26 惊叹面试官：由浅入深手写队列**

### 引导语

现在不少大厂面试的时候会要求手写代码，我曾经看过一个大厂面试时，要求在线写代码，题目就是：在不使用 Java 现有队列 API 的情况下，手写出一个队列的实现出来，队列的数据结构，入队和出队方式都自己定义。

这题其实考察的有几个点：

1. 考察你对队列的内部结构熟不熟悉；
2. 考察你定义 API 的功底；
3. 考察写代码的基本功，代码风格。

本章就和大家一起，结合以上几点，手写一个队列出来，一起来熟悉一下思路和过程，完整队列代码见：demo.four.DIYQueue 和 demo.four.DIYQueueDemo



### 1 接口定义

在实现队列之前，我们首先需要定义出队列的接口，就是我们常说的 API，API 是我们队列的门面，定义时主要原则就是简单和好用。

我们这次实现的队列只定义放数据和拿数据两个功能，接口定义如下：

```java
/**
* 定义队列的接口，定义泛型，可以让使用者放任意类型到队列中去
* author  wenhe
* date 2019/9/1
*/
public interface Queue<T> {

  /**
   * 放数据
   * @param item 入参
   * @return true 成功、false 失败
   */
  boolean put(T item);

  /**
   * 拿数据，返回一个泛型值
   * @return
   */
  T take();

  // 队列中元素的基本结构
  class Node<T> {
    // 数据本身
    T item;
    // 下一个元素
    Node<T> next;

    // 构造器
    public Node(T item) {
      this.item = item;
    }
  }
}
```

有几点我们说明下：

1. 定义接口时，一定要写注释，接口的注释，方法的注释等等，这样别人看我们的接口时，会轻松很多‘；
2. 定义接口时，要求命名简洁明了，最好让别人一看见命名就知道这个接口是干啥的，比如我们命名为 Queue，别人一看就清楚这个接口是和队列相关的；
3. 用好泛型，因为我们不清楚放进队列中的到底都是那些值，所以我们使用了泛型 T，表示可以在队列中放任何值；
4. 接口里面无需给方法写上 public 方法，因为接口中的方法默认都是 public 的，你写上编译器也会置灰，如下图：
   ![图片描述](http://img.mukewang.com/5db14faa0001928904140484.png)
5. 我们在接口中定义了基础的元素 Node，这样队列子类如果想用的话，可以直接使用，增加了复用的可能性。



### 2 队列子类实现

接着我们就要开始写子类实现了，我们准备写个最常用的链表数据结构的队列。



#### 2.1 数据结构

底层数据结构我们采用链表，一说到链表，大家应该马上就会想到三个关键要素：链表头、链表尾和链表元素，我们也实现了，代码如下：

```java
/**
 * 队列头
 */
private volatile Node<T> head;

/**
 * 队列尾
 */
private volatile Node<T> tail;

/**
 * 自定义队列元素
 */
class DIYNode extends Node<T>{
  public DIYNode(T item) {
    super(item);
  }
}
```

除了这些元素之外，我们还有队列容器的容量大小、队列目前的使用大小、放数据锁、拿数据锁等等，代码如下：

```java
/**
 * 队列的大小，使用 AtomicInteger 来保证其线程安全
 */
private AtomicInteger size = new AtomicInteger(0);

/**
 * 容量
 */
private final Integer capacity;

/**
 * 放数据锁
 */
private ReentrantLock putLock = new ReentrantLock();

/**
 * 拿数据锁
 */
private ReentrantLock takeLock = new ReentrantLock();
```



#### 2.2 初始化

我们提供了使用默认容量（Integer 的最大值）和指定容量两种方式，代码如下：

```java
/**
 * 无参数构造器，默认最大容量是 Integer.MAX_VALUE
 */
public DIYQueue() {
  capacity = Integer.MAX_VALUE;
  head = tail = new DIYNode(null);
}

/**
 * 有参数构造器，可以设定容量的大小
 * @param capacity
 */
public DIYQueue(Integer capacity) {
  // 进行边界的校验
  if(null == capacity || capacity < 0){
    throw new IllegalArgumentException();
  }
  this.capacity = capacity;
  head = tail = new DIYNode(null);
}
```



#### 2.3 put 方法的实现

```java
public boolean put(T item) {
  // 禁止空数据
  if(null == item){
    return false;
  }
  try{
    // 尝试加锁，500 毫秒未获得锁直接被打断
    boolean lockSuccess = putLock.tryLock(300, TimeUnit.MILLISECONDS);
	if(!lockSuccess){
	  return false;
	}
    // 校验队列大小
    if(size.get() >= capacity){
      log.info("queue is full");
      return false;
    }
    // 追加到队尾
    tail = tail.next = new DIYNode(item);
    // 计数
    size.incrementAndGet();
    return true;
  } catch (InterruptedException e){
    log.info("tryLock 500 timeOut", e);
    return false;
  } catch(Exception e){
    log.error("put error", e);
    return false;
  } finally {
    putLock.unlock();
  }
}
```

put 方法的实现有几点我们需要注意的是：

1. 注意 try catch finally 的节奏，catch 可以捕捉多种类型的异常，我们这里就捕捉了超时异常和未知异常，在 finally 里面一定记得要释放锁，不然锁不会自动释放的，这个一定不能用错，体现了我们代码的准确性；
2. 必要的逻辑检查还是需要的，比如入参是否为空的空指针检查，队列是否满的临界检查，这些检查代码可以体现出我们逻辑的严密性；
3. 在代码的关键地方加上日志和注释，这点也是非常重要的，我们不希望关键逻辑代码注释和日志都没有，不利于阅读代码和排查问题；
4. 注意线程安全，此处实现我们除了加锁之外，对于容量的大小（size）我们选择线程安全的计数类：AtomicInteger，来保证了线程安全；
5. 加锁的时候，我们最好不要使用永远阻塞的方法，我们一定要用带有超时时间的阻塞方法，此处我们设置的超时时间是 300 毫秒，也就是说如果 300 毫秒内还没有获得锁，put 方法直接返回 false，当然时间大小你可以根据情况进行设置；
6. 根据不同的情况设置不同的返回值，put 方法返回的是 false，在发生异常时，我们可以选择返回 false，或者直接抛出异常；
7. put 数据时追加到队尾的，所以我们只需要把新数据转化成 DIYNode，放到队列的尾部即可。



#### 2.4 take 方法的实现

take 方法和 put 方法的实现非常类似，只不过 take 是从头部拿取数据，代码实现如下：

```java
public T take() {
  // 队列是空的，返回 null
  if(size.get() == 0){
    return null;
  }
  try {
    // 拿数据我们设置的超时时间更短
    boolean lockSuccess = takeLock.tryLock(200,TimeUnit.MILLISECONDS);
	if(!lockSuccess){
	    throw new RuntimeException("加锁失败");
	}
    // 把头结点的下一个元素拿出来
    Node expectHead = head.next;
    // 把头结点的值拿出来
    T result = head.item;
    // 把头结点的值置为 null，帮助 gc
    head.item = null;
    // 重新设置头结点的值
    head = (DIYNode) expectHead;
    size.decrementAndGet();
    // 返回头结点的值
    return result;
  } catch (InterruptedException e) {
    log.info(" tryLock 200 timeOut",e);
  } catch (Exception e) {
    log.info(" take error ",e);
  }finally {
      takeLock.unlock();
 }
  return null;
}
```

通过以上几步，我们的队列已经写完了，完整代码见：demo.four.DIYQueue。



### 3 测试

API 写好了，接下来我们要针对 API 写一些场景测试和单元测试，我们先写个场景测试，看看 API 能否跑通，代码如下：

```java
@Slf4j
public class DIYQueueDemo {
	// 我们需要测试的队列
  private final static Queue<String> queue = new DIYQueue<>();
	// 生产者
  class Product implements Runnable{
    private final String message;

    public Product(String message) {
      this.message = message;
    }

    @Override
    public void run() {
      try {
        boolean success = queue.put(message);
        if (success) {
          log.info("put {} success", message);
          return;
        }
        log.info("put {} fail", message);
      } catch (Exception e) {
        log.info("put {} fail", message);
      }
    }
  }
	// 消费者
  class Consumer implements Runnable{
    @Override
    public void run() {
      try {
        String message = (String) queue.take();
        log.info("consumer message :{}",message);
      } catch (Exception e) {
        log.info("consumer message fail",e);
      }
    }
  }
	// 场景测试
  @Test
  public void testDIYQueue() throws InterruptedException {
    ThreadPoolExecutor executor =
        new ThreadPoolExecutor(10,10,0,TimeUnit.MILLISECONDS,
                               new LinkedBlockingQueue<>());
    for (int i = 0; i < 1000; i++) {
        // 是偶数的话，就提交一个生产者，奇数的话提交一个消费者
        if(i % 2 == 0){
          executor.submit(new Product(i+""));
          continue;
        }
        executor.submit(new Consumer());
    }
    Thread.sleep(10000);
  }
```

代码测试的场景比较简单，从 0 开始循环到 1000，如果是偶数，就让生产者去生产数据，并放到队列中，如果是奇数，就让消费者去队列中拿数据出来进行消费，运行之后的结果如下：

![图片描述](http://img.mukewang.com/5db14fc70001617f08660711.png)
从显示的结果来看，咱们写的 DIYQueue 没有太大的问题，当然如果想大规模的使用，还需要详细的单元测试和性能测试。



### 4 总结

通过本章的学习，不知道你有没有一种队列很简单的感觉，其实队列本身就很简单，没有想象的那么复杂。

只要我们懂得了队列的基本原理，清楚几种常用的数据结构，手写队列问题其实并不大，你也赶紧来试一试吧。

# **第5章 线程**

## **27 Thread 源码解析**

### 引导语

从本章开始我们开始学习线程的知识，线程是非常有趣的一个章节，大多数同学对于线程 API，属于不用就忘，到用时需要百度的情况，希望通过本小节的源码阅读，能够加深对线程的印象。

本小节主要三章，本章主要说线程的基本概念、使用姿势、Thread 和 Runnable 的源码；Future、ExecutorService 源码解析章节主要说异步线程执行；押宝线程源码面试题章节主要说说常遇到的源码面试题。

由于线程的概念很多，所以本章会先介绍很多线程的基本概念，说清楚后再解析源码，不然有些同学会看不懂，大家见谅。



### 1 类注释



#### 1.1 Thread

1. 每个线程都有优先级，高优先级的线程可能会优先执行；

2. 父线程创建子线程后，优先级、是否是守护线程等属性父子线程是一致的；

3. JVM 启动时，通常都启动 MAIN 非守护线程，以下任意一个情况发生时，线程就会停止：

   退出方法被调用，并且安全机制允许这么做（比如调用 Thread.interrupt 方法）；

   所有非守护线程都消亡，或者从运行的方法正常返回，或者运行的方法抛出了异常；

4. 每个线程都有名字，多个线程可能具有相同的名字，Thread 有的构造器如果没有指定名字，会自动生成一个名字。



### 2 线程的基本概念

我们接下来介绍一下线程的基本概念：



#### 2.1 线程的状态

网上有各种介绍线程状态的文章，我们这里说线程的状态是从源码的角度，源码中一共列举了六种状态，如下图：
![图片描述](http://img.mukewang.com/5db92718000105e912540550.png)

我们解析一下这个图：

1. NEW 表示线程创建成功，但没有运行，在 new Thread 之后，没有 start 之前，线程的状态都是 NEW；
2. 当我们运行 strat 方法，子线程被创建成功之后，子线程的状态变成 RUNNABLE，RUNNABLE 表示线程正在运行中；
3. 子线程运行完成、被打断、被中止，状态都会从 RUNNABLE 变成 TERMINATED，TERMINATED 表示线程已经运行结束了；
4. 如果线程正好在等待获得 monitor lock 锁，比如在等待进入 synchronized 修饰的代码块或方法时，会从 RUNNABLE 变成 BLOCKED，BLOCKED 表示阻塞的意思；
5. WAITING 和 TIMED_WAITING 类似，都表示在遇到 Object#wait、Thread#join、LockSupport#park 这些方法时，线程就会等待另一个线程执行完特定的动作之后，才能结束等待，只不过 TIMED_WAITING 是带有等待时间的（可以看下面的 join 方法的 demo）。

再次重申，这 6 种状态并不是线程所有的状态，只是在 Java 源码中列举出的 6 种状态， Java 线程的处理方法都是围绕这 6 种状态的。



#### 2.2 优先级

优先级代表线程执行的机会的大小，优先级高的可能先执行，低的可能后执行，在 Java 源码中，优先级从低到高分别是 1 到 10，线程默认 new 出来的优先级都是 5，源码如下：

```java
// 最低优先级
public final static int MIN_PRIORITY = 1;

// 普通优先级，也是默认的
public final static int NORM_PRIORITY = 5;

// 最大优先级
public final static int MAX_PRIORITY = 10;
```



#### 2.3 守护线程

我们默认创建的线程都是非守护线程。创建守护线程时，需要将 Thread 的 daemon 属性设置成 true，守护线程的优先级很低，当 JVM 退出时，是不关心有无守护线程的，即使还有很多守护线程，JVM 仍然会退出，我们在工作中，可能会写一些工具做一些监控的工作，这时我们都是用守护子线程去做，这样即使监控抛出异常，但因为是子线程，所以也不会影响到业务主线程，因为是守护线程，所以 JVM 也无需关注监控是否正在运行，该退出就退出，所以对业务不会产生任何影响。



#### 2.4 ClassLoader

ClassLoader 我们可以简单理解成类加载器，就是把类从文件、二进制数组、URL 等位置加载成可运行 Class。



### 3 线程两种初始化方式

无返回值的线程主要有两种初始化方式：



#### 3.1 继承 Thread，成为 Thread 的子类

```java
// 继承 Thread，实现其 run 方法
class MyThread extends Thread{
  @Override
  public void run() {
    log.info(Thread.currentThread().getName());
  }
}
@Test
// 调用 start 方法即可，会自动调用到 run 方法的
public void extendThreadInit(){
  new MyThread().start();
}
```

上述代码打印出的线程名称是：Thread-0，而主线程的名字是：Thread [main,5,main]，由此可见，的确是开了一个子线程来执行打印的操作。

我们一起来看下 start 的底层源码：

```java
// 该方法可以创建一个新的线程出来
public synchronized void start() {
    // 如果没有初始化，抛异常
    if (threadStatus != 0)
        throw new IllegalThreadStateException();
    group.add(this);
    // started 是个标识符，我们在做一些事情的时候，经常这么写
    // 动作发生之前标识符是 false，发生完成之后变成 true
    boolean started = false;
    try {
        // 这里会创建一个新的线程，执行完成之后，新的线程已经在运行了，既 target 的内容已经在运行了
        start0();
        // 这里执行的还是主线程
        started = true;
    } finally {
        try {
            // 如果失败，把线程从线程组中删除
            if (!started) {
                group.threadStartFailed(this);
            }
         // Throwable 可以捕捉一些 Exception 捕捉不到的异常，比如说子线程抛出的异常
        } catch (Throwable ignore) {
            /* do nothing. If start0 threw a Throwable then
              it will be passed up the call stack */
        }
    }
}
// 开启新线程使用的是 native 方法
private native void start0();
```



#### 3.2 实现 Runnable 接口，作为 Thread 的入参

```java
Thread thread = new Thread(new Runnable() {
  @Override
  public void run() {
    log.info("{} begin run",Thread.currentThread().getName());
  }
});
// 开一个子线程去执行
thread.start();
// 不会新起线程，是在当前主线程上继续运行
thread.run();
```

这种就是实现 Runnable 的接口，并作为 Thread 构造器的入参，我们调用时使用了两种方式，可以根据情况选择使用 start 或 run 方法，使用 start 会开启子线程来执行 run 里面的内容，使用 run 方法执行的还是主线程。

我们来看下 run 方法的源码：

```java
// 简单的运行，不会新起线程，target 是 Runnable
public void run() {
    if (target != null) {
        target.run();
    }
}
```

源码中的 target 就是在 new Thread 时，赋值的 Runnable。



### 4 线程初始化

线程初始化的源码有点长，我们只看比较重要的代码 (不重要的被我删掉了)，如下：

```java
// 无参构造器，线程名字自动生成
public Thread() {
    init(null, null, "Thread-" + nextThreadNum(), 0);
}
// g 代表线程组，线程组可以对组内的线程进行批量的操作，比如批量的打断 interrupt
// target 是我们要运行的对象
// name 我们可以自己传，如果不传默认是 "Thread-" + nextThreadNum()，nextThreadNum 方法返回的是自增的数字
// stackSize 可以设置堆栈的大小
private void init(ThreadGroup g, Runnable target, String name,
                  long stackSize, AccessControlContext acc) {
    if (name == null) {
        throw new NullPointerException("name cannot be null");
    }

    this.name = name.toCharArray();
    // 当前线程作为父线程
    Thread parent = currentThread();
    this.group = g;
    // 子线程会继承父线程的守护属性
    this.daemon = parent.isDaemon();
    // 子线程继承父线程的优先级属性
    this.priority = parent.getPriority();
    // classLoader
    if (security == null || isCCLOverridden(parent.getClass()))
        this.contextClassLoader = parent.getContextClassLoader();
    else
        this.contextClassLoader = parent.contextClassLoader;
    this.inheritedAccessControlContext =
            acc != null ? acc : AccessController.getContext();
    this.target = target;
    setPriority(priority);
    // 当父线程的 inheritableThreadLocals 的属性值不为空时
    // 会把 inheritableThreadLocals 里面的值全部传递给子线程
    if (parent.inheritableThreadLocals != null)
        this.inheritableThreadLocals =
            ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
    this.stackSize = stackSize;
    /* Set thread ID */
    // 线程 id 自增
    tid = nextThreadID();
}
```

从初始化源码中可以看到，很多属性，子线程都是直接继承父线程的，包括优先性、守护线程、inheritableThreadLocals 里面的值等等。



### 5 线程其他操作



#### 5.1 join

join 的意思就是当前线程等待另一个线程执行完成之后，才能继续操作，我们写了一个 demo，如下：

```java
@Test
public void join() throws Exception {
  Thread main = Thread.currentThread();
  log.info("{} is run。",main.getName());
  Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
      log.info("{} begin run",Thread.currentThread().getName());
      try {
        Thread.sleep(30000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info("{} end run",Thread.currentThread().getName());
    }
  });
  // 开一个子线程去执行
  thread.start();
  // 当前主线程等待子线程执行完成之后再执行
  thread.join();
  log.info("{} is end", Thread.currentThread());
}
```

执行的结果，就是主线程在执行 thread.join (); 代码后会停住，会等待子线程沉睡 30 秒后再执行，这里的 join 的作用就是让主线程等待子线程执行完成，我们画一个图示意一下：
![图片描述](http://img.mukewang.com/5db927370001f7e411520448.png)

从图中可以看出，主线程一直等待子线程沉睡 30s 后才继续执行，在等待期间，主线程的状态也是 TIMED_WAITING。



#### 5.2 yield

yield 是个 native 方法，底层代码如下：

```java
public static native void yield();
```

意思是当前线程做出让步，放弃当前 cpu，让 cpu 重新选择线程，避免线程过度使用 cpu，我们在写 while 死循环的时候，预计短时间内 while 死循环可以结束的话，可以在循环里面使用 yield 方法，防止 cpu 一直被 while 死循环霸占。

有点需要说明的是，让步不是绝不执行，重新竞争时，cpu 也有可能重新选中自己。



#### 5.3 sleep

sleep 也是 native 方法，可以接受毫秒的一个入参，也可以接受毫秒和纳秒的两个入参，意思是当前线程会沉睡多久，沉睡时不会释放锁资源，所以沉睡时，其它线程是无法得到锁的。

接受毫秒和纳秒两个入参时，如果给定纳秒大于等于 0.5 毫秒，算一个毫秒，否则不算。



#### 5.4 interrupt

interrupt 中文是打断的意思，意思是可以打断中止正在运行的线程，比如：

1. Object#wait ()、Thread#join ()、Thread#sleep (long) 这些方法运行后，线程的状态是 WAITING 或 TIMED_WAITING，这时候打断这些线程，就会抛出 InterruptedException 异常，使线程的状态直接到 TERMINATED；
2. 如果 I/O 操作被阻塞了，我们主动打断当前线程，连接会被关闭，并抛出 ClosedByInterruptException 异常；

我们举一个例子来说明如何打断 WAITING 的线程，代码如下：

```java
@Test
public void testInterrupt() throws InterruptedException {
  Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
      log.info("{} begin run",Thread.currentThread().getName());
      try {
        log.info("子线程开始沉睡 30 s");
        Thread.sleep(30000L);
      } catch (InterruptedException e) {
        log.info("子线程被打断");
        e.printStackTrace();
      }
      log.info("{} end run",Thread.currentThread().getName());
    }
  });
  // 开一个子线程去执行
  thread.start();
  Thread.sleep(1000L);
  log.info("主线程等待 1s 后，发现子线程还没有运行成功，打断子线程");
  thread.interrupt();
}
```

例子主要说的是，主线程会等待子线程执行 1s，如果 1s 内子线程还没有执行完，就会打断子线程，子线程被打断后，会抛出 InterruptedException 异常，执行结束，运行的结果如下图：

![图片描述](http://img.mukewang.com/5db9274b0001eb9718840506.png)



### 6 总结

本章主要介绍了线程的基本概念、状态、无返回值线程的初始化方式和线程的常用操作，这些知识也是工作中常用的，也是大家都必须了解的，为后面的学习打下基础。

## **28 Future、ExecutorService 源码解析**

### 引导语

本章和大家一起看下有返回值的线程如何创建，两种线程 API 之间如何关联，介绍一下和线程相关的其余 API。



### 1 整体架构

画了一个关于线程 API 之间关系的依赖图，如下：
![图片描述](http://img.mukewang.com/5db928f50001c19520623172.png)

在上一章节，我们说了 Thread 和 Runnable，本小节我们按照这个图把剩下的几个 API 也说完，然后把 API 之间的关系理清楚。

为了方便大家更好的理解，我们首先看一个 demo，这个场景说的是我们往线程池里面提交一个有返回值的线程，代码如下：

```java
// 首先我们创建了一个线程池
ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
                                                     new LinkedBlockingQueue<>());
// futureTask 我们叫做线程任务，构造器的入参是 Callable
FutureTask futureTask = new FutureTask(new Callable<String> () {
  @Override
  public String call() throws Exception {
    Thread.sleep(3000);
    // 返回一句话
    return "我是子线程"+Thread.currentThread().getName();
  }
});
// 把任务提交到线程池中，线程池会分配线程帮我们执行任务
executor.submit(futureTask);
// 得到任务执行的结果
String result = (String) futureTask.get();
log.info("result is {}",result);
```

从上面这个 demo 中，我们大概可以看出各个 API 的作用：

1. Callable 定义我们需要做的事情，是可以有返回值的；
2. FutureTask 我们叫做任务，入参是 Callable，是对 Callable 的包装，方便线程池的使用；
3. 最后通过 FutureTask.get() 得到子线程的计算结果。

接着我们分别来看看各种 API 的底层实现。



### 2 Callable

Callable 是一个接口，约定了线程要做的事情，和 Runnable 一样，不过这个线程任务是有返回值的，我们来看下接口定义：

```java
public interface Callable<V> {
    V call() throws Exception;
}
```

返回值是一个泛型，可以定义成任何类型，但我们使用的时候，都不会直接使用 Callable，而是会结合 FutureTask 一起使用。



### 3 FutureTask

FutureTask 我们可以当做是线程运行的具体任务，从上图中，我们可以看到 FutureTask 实现了 RunnableFuture 接口，源码如下：

```java
public class FutureTask<V> implements RunnableFuture<V> {
}
```

而 RunnableFuture 又实现了 Runnable, Future 两个接口，接下来我们先看 Future，再看 RunnableFuture，最后看 FutureTask。



#### 3.1 Future

我们刚才说 Callable 是可以返回子线程执行结果的，在获取结果的时候，就需要用到 Future 接口了。

Future 接口注释上写了这些：

1. 定义了异步计算的接口，提供了计算是否完成的 check、等待完成和取回等多种方法；
2. 如果想得到结果可以使用 get 方法，此方法(无参方法)会一直阻塞到异步任务计算完成；
3. 取消可以使用 cancel 方法，但一旦任务计算完成，就无法被取消了。

Future 接口定义了这些方法：

```java
// 如果任务已经成功了，或已经取消了，是无法再取消的，会直接返回取消成功(true)
// 如果任务还没有开始进行时，发起取消，是可以取消成功的。
// 如果取消时，任务已经在运行了，mayInterruptIfRunning 为 true 的话，就可以打断运行中的线程
// mayInterruptIfRunning 为 false，表示不能打断直接返回
boolean cancel(boolean mayInterruptIfRunning);

// 返回线程是否已经被取消了，true 表示已经被取消了
// 如果线程已经运行结束了，isCancelled 和 isDone 返回的都是 true
boolean isCancelled();

// 线程是否已经运行结束了
boolean isDone();

// 等待结果返回
// 如果任务被取消了，抛 CancellationException 异常
// 如果等待过程中被打断了，抛 InterruptedException 异常
V get() throws InterruptedException, ExecutionException;

// 等待，但是带有超时时间的，如果超时时间外仍然没有响应，抛 TimeoutException 异常
V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
```

从接口上可以看出，Future 定义了各种方法对任务进行了管理，比如说取消任务，得到任务的计算结果等等。



#### 3.2 RunnableFuture

RunnableFuture 也是一个接口，定义如下：

```java
public interface RunnableFuture<V> extends Runnable, Future<V> {
    void run();
}
```

RunnableFuture 接口的最大目的，是让 Future 可以对 Runnable 进行管理，可以取消 Runnable，查看 Runnable 是否完成等等。



#### 3.3 统一 Callable 和 Runnable

我们现在清楚了，新建任务有两种方式，一种是无返回值的 Runnable，一种是有返回值的 Callable，但对 Java 其他 API 来说使用起来并不是很方便，没有一个统一的接口，比如说线程池在提交任务时，是不是应该针对 Runnable 和 Callable 两种情况提供不同的实现思路呢？所以 FutureTask 出现了，FutureTask 实现了 RunnableFuture 接口，又集合了 Callable（Callable 是 FutureTask 的属性），还提供了两者一系列的转化方法，这样 FutureTask 就统一了 Callable 和 Runnable，我们一起来细看下。



##### 3.3.1 FutureTask 的类定义

```java
public class FutureTask<V> implements RunnableFuture<V> {}
```

从类定义上可以看出来 FutureTask 实现了 RunnableFuture 接口，也就是说间接实现了 Runnnable 接口（RunnableFuture 实现了 Runnnable 接口），就是说 FutureTask 本身就是个 Runnnable，同时 FutureTask 也实现了 Future，也就是说 FutureTask 具备对任务进行管理的功能（Future 具备对任务进行管理的功能）。



##### 3.3.2 FutureTask 的属性

我们一起来看下 FutureTask 有哪些重要属性：

```java
// 任务状态
private volatile int state;
private static final int NEW          = 0;//线程任务创建
private static final int COMPLETING   = 1;//任务执行中
private static final int NORMAL       = 2;//任务执行结束
private static final int EXCEPTIONAL  = 3;//任务异常
private static final int CANCELLED    = 4;//任务取消成功
private static final int INTERRUPTING = 5;//任务正在被打断中
private static final int INTERRUPTED  = 6;//任务被打断成功

// 组合了 Callable 
private Callable<V> callable;
// 异步线程返回的结果
private Object outcome; 
// 当前任务所运行的线程
private volatile Thread runner;
// 记录调用 get 方法时被等待的线程
private volatile WaitNode waiters;
```

从属性上我们明显看到 Callable 是作为 FutureTask 的属性之一，这也就让 FutureTask 具备了转化 Callable 和 Runnable 的功能，接着我们看下 FutureTask 的构造器，看看两者是如何转化的。



##### 3.3.3 FutureTask 的构造器

FutureTask 有两个构造器，分别接受 Callable 和 Runnable，如下：

```java
// 使用 Callable 进行初始化
public FutureTask(Callable<V> callable) {
    if (callable == null)
        throw new NullPointerException();
    this.callable = callable;
    // 任务状态初始化
    this.state = NEW;       // ensure visibility of callable
}

// 使用 Runnable 初始化，并传入 result 作为返回结果。
// Runnable 是没有返回值的，所以 result 一般没有用，置为 null 就好了
public FutureTask(Runnable runnable, V result) {
    // Executors.callable 方法把 runnable 适配成 RunnableAdapter，RunnableAdapter 实现了 callable，所以也就是把 runnable 直接适配成了 callable。
    this.callable = Executors.callable(runnable, result);
    this.state = NEW;       // ensure visibility of callable
}
```

Runnable 的两个构造器，只有一个目的，就是把入参都转化成 Callable，那么为什么不都转化成 Runnnable 呢？主要是因为 Callable 的功能比 Runnnable 丰富，Callable 有返回值，而 Runnnable 没有。

我们注意到入参是 Runnable 的构造器，会使用 Executors.callable 方法来把 Runnnable 转化成 Callable，Runnnable 和 Callable 两者都是接口，两者之间是无法进行转化的，所以 Java 新建了一个转化类：RunnableAdapter 来进行转化，我们来看下转化的逻辑：

```java
// 转化 Runnable 成 Callable 的工具类
static final class RunnableAdapter<T> implements Callable<T> {
    final Runnable task;
    final T result;
    RunnableAdapter(Runnable task, T result) {
        this.task = task;
        this.result = result;
    }
    public T call() {
        task.run();
        return result;
    }
}
```

我们可以看到：

1. 首先 RunnableAdapter 实现了 Callable，所以 RunnableAdapter 就是 Callable；
2. 其次 Runnable 是 RunnableAdapter 的一个属性，在构造 RunnableAdapter 的时候会传进来，并且在 call 方法里面调用 Runnable 的 run 方法。

这是一个典型的适配模型，我们要把 Runnable 适配成 Callable，首先要实现 Callable 的接口，接着在 Callable 的 call 方法里面调用被适配对象（Runnable）的方法。

FutureTask 构造器设计很巧妙，将 Runnable 和 Callable 灵活的打通，向内和向外只提供功能更加丰富的 Callable 接口，值得我们学习。



##### 3.3.4 FutureTask 对 Future 接口方法的实现

我们主要看几个关键的方法实现源码。

###### 3.3.4.1 get

get 有无限阻塞和带超时时间两种方法，我们通常建议使用带超时时间的方法，源码如下：

```java
public V get(long timeout, TimeUnit unit)
    throws InterruptedException, ExecutionException, TimeoutException {
    if (unit == null)
        throw new NullPointerException();
    int s = state;
    // 如果任务已经在执行中了，并且等待一定的时间后，仍然在执行中，直接抛出异常
    if (s <= COMPLETING &&
        (s = awaitDone(true, unit.toNanos(timeout))) <= COMPLETING)
        throw new TimeoutException();
    // 任务执行成功，返回执行的结果
    return report(s);
}
// 等待任务执行完成
private int awaitDone(boolean timed, long nanos)
    throws InterruptedException {
    // 计算等待终止时间，如果一直等待的话，终止时间为 0
    final long deadline = timed ? System.nanoTime() + nanos : 0L;
    WaitNode q = null;
    // 不排队
    boolean queued = false;
    // 无限循环
    for (;;) {
        // 如果线程已经被打断了，删除，抛异常
        if (Thread.interrupted()) {
            removeWaiter(q);
            throw new InterruptedException();
        }
        // 当前任务状态
        int s = state;
        // 当前任务已经执行完了，返回
        if (s > COMPLETING) {
            // 当前任务的线程置空
            if (q != null)
                q.thread = null;
            return s;
        }
        // 如果正在执行，当前线程让出 cpu，重新竞争，防止 cpu 飙高
        else if (s == COMPLETING) // cannot time out yet
            Thread.yield();
            // 如果第一次运行，新建 waitNode，当前线程就是 waitNode 的属性
        else if (q == null)
            q = new WaitNode();
            // 默认第一次都会执行这里，执行成功之后，queued 就为 true，就不会再执行了
            // 把当前 waitNode 当做 waiters 链表的第一个
        else if (!queued)
            queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
                                                 q.next = waiters, q);
            // 如果设置了超时时间，并过了超时时间的话，从 waiters 链表中删除当前 wait
        else if (timed) {
            nanos = deadline - System.nanoTime();
            if (nanos <= 0L) {
                removeWaiter(q);
                return state;
            }
            // 没有过超时时间，线程进入 TIMED_WAITING 状态
            LockSupport.parkNanos(this, nanos);
        }
        // 没有设置超时时间，进入 WAITING 状态
        else
            LockSupport.park(this);
    }
}
```

get 方法虽然名字叫做 get，但却做了很多 wait 的事情，当发现任务还在进行中，没有完成时，就会阻塞当前进程，等待任务完成后再返回结果值。阻塞底层使用的是 LockSupport.park 方法，使当前线程进入 WAITING 或 TIMED_WAITING 状态。

###### 3.3.4.2 run

```java
/**
 * run 方法可以直接被调用
 * 也可以开启新的线程进行调用
 */
public void run() {
    // 状态不是任务创建，或者当前任务已经有线程在执行了，直接返回
    if (state != NEW ||
        !UNSAFE.compareAndSwapObject(this, runnerOffset,
                                     null, Thread.currentThread()))
        return;
    try {
        Callable<V> c = callable;
        // Callable 不为空，并且已经初始化完成
        if (c != null && state == NEW) {
            V result;
            boolean ran;
            try {
                // 调用执行
                result = c.call();
                ran = true;
            } catch (Throwable ex) {
                result = null;
                ran = false;
                setException(ex);
            }
            // 给 outcome 赋值
            if (ran)
                set(result);
        }
    } finally {
        runner = null;
        int s = state;
        if (s >= INTERRUPTING)
            handlePossibleCancellationInterrupt(s);
    }
}
```

run 方法我们再说明几点：

1. run 方法是没有返回值的，通过给 outcome 属性赋值（set(result)），get 时就能从 outcome 属性中拿到返回值；
2. FutureTask 两种构造器，最终都转化成了 Callable，所以在 run 方法执行的时候，只需要执行 Callable 的 call 方法即可，在执行 c.call() 代码时，如果入参是 Runnable 的话， 调用路径为 c.call() -> RunnableAdapter.call() -> Runnable.run()，如果入参是 Callable 的话，直接调用。

###### 3.3.4.3 cancel

```java
// 取消任务，如果正在运行，尝试去打断
public boolean cancel(boolean mayInterruptIfRunning) {
    if (!(state == NEW &&//任务状态不是创建 并且不能把 new 状态置为取消，直接返回 false
          UNSAFE.compareAndSwapInt(this, stateOffset, NEW,
              mayInterruptIfRunning ? INTERRUPTING : CANCELLED)))
        return false;
    // 进行取消操作，打断可能会抛出异常，选择 try finally 的结构
    try {    // in case call to interrupt throws exception
        if (mayInterruptIfRunning) {
            try {
                Thread t = runner;
                if (t != null)
                    t.interrupt();
            } finally { // final state
                //状态设置成已打断
                UNSAFE.putOrderedInt(this, stateOffset, INTERRUPTED);
            }
        }
    } finally {
        // 清理线程
        finishCompletion();
    }
    return true;
}
```



### 4 总结

大家现在可以回头看看一开始我们贴出来的图，看看自己照着图能否想起来各个 API 的作用，比如 Callable 是干啥的，FutureTask 又有什么作用，Runnable 和 Calllable 之间又是如何关联起来，几个 API 之间的关系的确很复杂，FutureTask 是关键，通过 FutureTask 把 Runnnable、Callable、Future 都串起来了，使 FutureTask 具有三者的功能，统一了 Runnnable 和 Callable，更方便使用。

## **29 押宝线程源码面试题**

### 引导语

关于线程方面的面试题，大部分都是概念题，我们需要大概的清楚这些概念，和面试官达成共识即可，本章我们一起来看下这些面试题，对前两章的学习进行巩固。



### 1 面试题



#### 1.1 创建子线程时，子线程是得不到父线程的 ThreadLocal，有什么办法可以解决这个问题？

答：这道题主要考察线程的属性和创建过程，可以这么回答。

可以使用 InheritableThreadLocal 来代替 ThreadLocal，ThreadLocal 和 InheritableThreadLocal 都是线程的属性，所以可以做到线程之间的数据隔离，在多线程环境下我们经常使用，但在有子线程被创建的情况下，父线程 ThreadLocal 是无法传递给子线程的，但 InheritableThreadLocal 可以，主要是因为在线程创建的过程中，会把

InheritableThreadLocal 里面的所有值传递给子线程，具体代码如下：

```java
// 当父线程的 inheritableThreadLocals 的值不为空时
// 会把 inheritableThreadLocals 里面的值全部传递给子线程
if (parent.inheritableThreadLocals != null)
    this.inheritableThreadLocals =
        ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
```



#### 1.2 线程创建有几种实现方式？

答：主要有三种，分成两大类，第一类是子线程没有返回值，第二类是子线程有返回值。

无返回值的线程有两种写法，第一种是继承 Thread，可以这么写：

```java
class MyThread extends Thread{
  @Override
  public void run() {
    log.info(Thread.currentThread().getName());
  }
}
@Test
public void extendThreadInit(){
  new MyThread().start();
}
```

第二种是实现 Runnable 接口，并作为 Thread 构造器的入参，代码如下：

```java
Thread thread = new Thread(new Runnable() {
  @Override
  public void run() {
    log.info("{} begin run",Thread.currentThread().getName());
  }
});
// 开一个子线程去执行
thread.start();
```

这两种都会开一个子线程去执行任务，并且是没有返回值的，如果需要子线程有返回值，需要使用 Callable 接口，但 Callable 接口是无法直接作为 Thread 构造器的入参的，必须结合 FutureTask 一起使用，可以这样写代码：

```java
@Test
public void testThreadByCallable() throws ExecutionException, InterruptedException {
  FutureTask futureTask = new FutureTask(new Callable<String> () {
    @Override
    public String call() throws Exception {
      Thread.sleep(3000);
      String result = "我是子线程"+Thread.currentThread().getName();
      log.info("子线程正在运行：{}",Thread.currentThread().getName());
      return result;
    }
  });
  new Thread(futureTask).start();
  log.info("返回的结果是 {}",futureTask.get());
}
```

把 FutureTask 作为 Thread 的入参就可以了，FutureTask 组合了 Callable ，使我们可以使用 Callable，并且 FutureTask 实现了 Runnable 接口，使其可以作为 Thread 构造器的入参，还有 FutureTask 实现了 Future，使其对任务有一定的管理功能。



#### 1.3 子线程 1 去等待子线程 2 执行完成之后才能执行，如何去实现？

答：这里考察的就是 Thread.join 方法，我们可以这么做：

```java
@Test
public void testJoin2() throws Exception {
  Thread thread2 = new Thread(new Runnable() {
    @Override
    public void run() {
      log.info("我是子线程 2,开始沉睡");
      try {
        Thread.sleep(2000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info("我是子线程 2，执行完成");
    }
  });
  Thread thread1 = new Thread(new Runnable() {
    @Override
    public void run() {
      log.info("我是子线程 1，开始运行");
      try {
      log.info("我是子线程 1，我在等待子线程 2");
      // 这里是代码关键  
      thread2.join();
      log.info("我是子线程 1，子线程 2 执行完成，我继续执行");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info("我是子线程 1，执行完成");
    }
  });
  thread1.start();
  thread2.start();
  Thread.sleep(100000);
}
```

子线程 1 需要等待子线程 2，只需要子线程 1 运行的时候，调用子线程 2 的 join 方法即可，这样线程 1 执行到 join 代码时，就会等待线程 2 执行完成之后，才会继续执行。



#### 1.4 守护线程和非守护线程的区别？如果我想在项目启动的时候收集代码信息，请问是守护线程好，还是非守护线程好，为什么？

答：两者的主要区别是，在 JVM 退出时，JVM 是不会管守护线程的，只会管非守护线程，如果非守护线程还有在运行的，JVM 就不会退出，如果没有非守护线程了，但还有守护线程的，JVM 直接退出。

如果需要在项目启动的时候收集代码信息，就需要看收集工作是否重要了，如果不太重要，又很耗时，就应该选择守护线程，这样不会妨碍 JVM 的退出，如果收集工作非常重要的话，那么就需要非守护进程，这样即使启动时发生未知异常，JVM 也会等到代码收集信息线程结束后才会退出，不会影响收集工作。



#### 1.5 线程 start 和 run 之间的区别。

答：调用 Thread.start 方法会开一个新的线程，run 方法不会。



#### 1.6 Thread、Runnable、Callable 三者之间的区别。

答：Thread 实现了 Runnable，本身就是 Runnable，但同时负责线程创建、线程状态变更等操作。

Runnable 是无返回值任务接口，Callable 是有返回值任务接口，如果任务需要跑起来，必须需要 Thread 的支持才行，Runnable 和 Callable 只是任务的定义，具体执行还需要靠 Thread。



#### 1.7 线程池 submit 有两个方法，方法一可接受 Runnable，方法二可接受 Callable，但两个方法底层的逻辑却是同一套，这是如何适配的。

答：问题考察点在于 Runnable 和 Callable 之间是如何转化的，可以这么回答。

Runnable 和 Callable 是通过 FutureTask 进行统一的，FutureTask 有个属性是 Callable，同时也实现了 Runnable 接口，两者的统一转化是在 FutureTask 的构造器里实现的，FutureTask 的最终目标是把 Runnable 和 Callable 都转化成 Callable，Runnable 转化成 Callable 是通过 RunnableAdapter 适配器进行实现的。

线程池的 submit 底层的逻辑只认 FutureTask，不认 Runnable 和 Callable 的差异，所以只要都转化成 FutureTask，底层实现都会是同一套。

具体 Runnable 转化成 Callable 的代码和逻辑可以参考上一章，有非常详细的描述。



#### 1.8 Callable 能否丢给 Thread 去执行？

答：可以的，可以新建 Callable，并作为 FutureTask 的构造器入参，然后把 FutureTask 丢给 Thread 去执行即可。



#### 1.9 FutureTask 有什么作用(谈谈对 FutureTask 的理解)。

答：作用如下：

1. 组合了 Callable，实现了 Runnable，把 Callable 和 Runnnable 串联了起来。
2. 统一了有参任务和无参任务两种定义方式，方便了使用。
3. 实现了 Future 的所有方法，对任务有一定的管理功能，比如说拿到任务执行结果，取消任务，打断任务等等。



#### 1.10 聊聊对 FutureTask 的 get、cancel 方法的理解

答：get 方法主要作用是得到 Callable 异步任务执行的结果，无参 get 会一直等待任务执行完成之后才返回，有参 get 方法可以设定固定的时间，在设定的时间内，如果任务还没有执行成功，直接返回异常，在实际工作中，建议多多使用 get 有参方法，少用 get 无参方法，防止任务执行过慢时，多数线程都在等待，造成线程耗尽的问题。

cancel 方法主要用来取消任务，如果任务还没有执行，是可以取消的，如果任务已经在执行过程中了，你可以选择不取消，或者直接打断执行中的任务。

两个方法具体的执行步骤和原理见上一章节源码解析。



#### 1.11 Thread.yield 方法在工作中有什么用？

答：yield 方法表示当前线程放弃 cpu，重新参与到 cpu 的竞争中去，再次竞争时，自己有可能得到 cpu 资源，也有可能得不到，这样做的好处是防止当前线程一直霸占 cpu。

我们在工作中可能会写一些 while 自旋的代码，如果我们一直 while 自旋，不采取任何手段，我们会发现 cpu 一直被当前 while 循环占用，如果能预见 while 自旋时间很长，我们会设置一定的判断条件，让当前线程陷入阻塞，如果能预见 while 自旋时间很短，我们通常会使用 Thread.yield 方法，使当前自旋线程让步，不一直霸占 cpu，比如这样：

```java
boolean stop = false;
while (!stop){
  // dosomething
  Thread.yield();
}
```



#### 1.12 wait()和sleep()的相同点和区别?

答：相同点：

1. 两者都让线程进入到 TIMED_WAITING 状态，并且可以设置等待的时间。

不同点：

1. wait 是 Object 类的方法，sleep 是 Thread 类的方法。
2. sleep 不会释放锁，沉睡的时候，其它线程是无法获得锁的，但 wait 会释放锁。



#### 1.13 写一个简单的死锁 demo

```java
// 共享变量 1
private static final Object share1 = new Object();
// 共享变量 2
private static final Object share2 = new Object();
@Test
public void testDeadLock() throws InterruptedException {
  // 初始化线程 1，线程 1 需要在锁定 share1 共享资源的情况下再锁定 share2
  Thread thread1 = new Thread(() -> {
    synchronized (share1){
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      synchronized (share2){
        log.info("{} is run",Thread.currentThread().getName());
      }
    }
  });

  // 初始化线程 2，线程 2 需要在锁定 share2 共享资源的情况下再锁定 share1
  Thread thread2 = new Thread(() -> {
    synchronized (share2){
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      synchronized (share1){
        log.info("{} is run",Thread.currentThread().getName());
      }
    }
  });
  // 当线程 1、2 启动后，都在等待对方锁定的资源，但都得不到，造成死锁
  thread1.start();
  thread2.start();
  Thread.sleep(1000000000);
}
```



### 2 总结

线程章节算是中等难度，我们需要清楚线程的概念，线程如何初始化，线程的状态变更等等问题，这些知识点都是线程池、锁的基础，学好线程后，再学习线程池和锁就会轻松很多。



# **第6章 锁**

## **30 AbstractQueuedSynchronizer 源码解析（上）**

### 引导语

AbstractQueuedSynchronizer 中文翻译叫做同步器，简称 AQS，是各种各样锁的基础，比如说 ReentrantLock、CountDownLatch 等等，这些我们经常用的锁底层实现都是 AQS，所以学好 AQS 对于后面理解锁的实现是非常重要的。

锁章节的内容是这么安排的：

1：AQS 源码非常多，我们会分成两个小节来说，先把底层原理弄清楚；

2：我们平时用不到 AQS，只会接触到 ReentrantLock、CountDownLatch 这些锁，我们以两个锁为例子，讲解下源码，因为 AQS 只要弄懂了，所有的锁你只要清楚锁的目的，就能够利用 AQS 去实现它；

3：总结一下锁的面试题；

4：总结一下锁在工作中有哪些使用场景，举几个实际的例子，看看锁使用时，有哪些注意事项；

5：最后我们自己来实现一个锁，看看如果我们自己来实现锁，有哪些步骤，需要注意哪些事项。

ps：本章内容需要大量队列基础知识，没有看过第四章节队列的同学，建议先阅读下队列章节。



### 1 整体架构

首先我们来看一下 AQS 的整体架构图，如下：
![图片描述](http://img.mukewang.com/5dc37d400001cb6f21120846.png)

这个图总结了 AQS 整体架构的组成，和部分场景的动态流向，图中两个点说明一下，方便大家观看。

1. AQS 中队列只有两个：同步队列 + 条件队列，底层数据结构两者都是链表；
2. 图中有四种颜色的线代表四种不同的场景，1、2、3 序号代表看的顺序。

AQS 本身就是一套锁的框架，它定义了获得锁和释放锁的代码结构，所以如果要新建锁，只要继承 AQS，并实现相应方法即可。

接下来我们一起来看下这个图中各个细节点。



#### 1.1 类注释

首先我们来看一下，从 AQS 类注释上，我们可以得到哪些信息：

1. 提供了一种框架，自定义了先进先出的同步队列，让获取不到锁的线程能进入同步队列中排队；
2. 同步器有个状态字段，我们可以通过状态字段来判断能否得到锁，此时设计的关键在于依赖安全的 atomic value 来表示状态（虽然注释是这个意思，但实际上是通过把状态声明为 volatile，在锁里面修改状态值来保证线程安全的）；
3. 子类可以通过给状态 CAS 赋值来决定能否拿到锁，可以定义那些状态可以获得锁，哪些状态表示获取不到锁（比如定义状态值是 0 可以获得锁，状态值是 1 就获取不到锁）；
4. 子类可以新建非 public 的内部类，用内部类来继承 AQS，从而实现锁的功能；
5. AQS 提供了排它模式和共享模式两种锁模式。排它模式下：只有一个线程可以获得锁，共享模式可以让多个线程获得锁，子类 ReadWriteLock 实现了两种模式；
6. 内部类 ConditionObject 可以被用作 Condition，我们通过 new ConditionObject () 即可得到条件队列；
7. AQS 实现了锁、排队、锁队列等框架，至于如何获得锁、释放锁的代码并没有实现，比如 tryAcquire、tryRelease、tryAcquireShared、tryReleaseShared、isHeldExclusively 这些方法，AQS 中默认抛 UnsupportedOperationException 异常，都是需要子类去实现的；
8. AQS 继承 AbstractOwnableSynchronizer 是为了方便跟踪获得锁的线程，可以帮助监控和诊断工具识别是哪些线程持有了锁；
9. AQS 同步队列和条件队列，获取不到锁的节点在入队时是先进先出，但被唤醒时，可能并不会按照先进先出的顺序执行。

AQS 的注释还有很多很多，以上 9 点是挑选出来稍微比较重要的注释总结。



#### 1.2 类定义

AQS 类定义代码如下：

```java
public abstract class AbstractQueuedSynchronizer
    extends AbstractOwnableSynchronizer
    implements java.io.Serializable {
```

可以看出两点：

1. AQS 是个抽象类，就是给各种锁子类继承用的，AQS 定义了很多如何获得锁，如何释放锁的抽象方法，目的就是为了让子类去实现；
2. 继承了 AbstractOwnableSynchronizer，AbstractOwnableSynchronizer 的作用就是为了知道当前是那个线程获得了锁，方便监控用的，代码如下：
   ![图片描述](http://img.mukewang.com/5dc37d20000197d121001410.png)



#### 1.3 基本属性

AQS 的属性可简单分为四类：同步器简单属性、同步队列属性、条件队列属性、公用 Node。



##### 1.3.1 简单属性

首先我们来看一下简单属性有哪些：

```java
// 同步器的状态，子类会根据状态字段进行判断是否可以获得锁
// 比如 CAS 成功给 state 赋值 1 算得到锁，赋值失败为得不到锁， CAS 成功给 state 赋值 0 算释放锁，赋值失败为释放失败
// 可重入锁，每次获得锁 +1，每次释放锁 -1
private volatile int state;

// 自旋超时阀值，单位纳秒
// 当设置等待时间时才会用到这个属性
static final long spinForTimeoutThreshold = 1000L;
```

最重要的就是 state 属性，是 int 属性的，所有继承 AQS 的锁都是通过这个字段来判断能不能获得锁，能不能释放锁。



##### 1.3.2 同步队列属性

首先我们介绍以下同步队列：当多个线程都来请求锁时，某一时刻有且只有一个线程能够获得锁（排它锁），那么剩余获取不到锁的线程，都会到同步队列中去排队并阻塞自己，当有线程主动释放锁时，就会从同步队列头开始释放一个排队的线程，让线程重新去竞争锁。

所以同步队列的主要作用阻塞获取不到锁的线程，并在适当时机释放这些线程。

同步队列底层数据结构是个双向链表，我们从源码中可以看到链表的头尾，如下：

```java
// 同步队列的头。
private transient volatile Node head;

// 同步队列的尾
private transient volatile Node tail;
```

源码中的 Node 是同步队列中的元素，但 Node 被同步队列和条件队列公用，所以我们在说完条件队列之后再说 Node。



##### 1.3.3 条件队列的属性

首先我们介绍下条件队列：条件队列和同步队列的功能一样，管理获取不到锁的线程，底层数据结构也是链表队列，但条件队列不直接和锁打交道，但常常和锁配合使用，是一定的场景下，对锁功能的一种补充。

条件队列的属性如下：

```java
// 条件队列，从属性上可以看出是链表结构
public class ConditionObject implements Condition, java.io.Serializable {
    private static final long serialVersionUID = 1173984872572414699L;
    // 条件队列中第一个 node
    private transient Node firstWaiter;
    // 条件队列中最后一个 node
    private transient Node lastWaiter;
}  
```

ConditionObject 我们就称为条件队列，我们需要使用时，直接 new ConditionObject () 即可。

ConditionObject 是实现 Condition 接口的，Condition 接口相当于 Object 的各种监控方法，比如 Object#wait ()、Object#notify、Object#notifyAll 这些方法，我们可以先这么理解，后面会细说。



##### 1.3.4 Node

Node 非常重要，即是同步队列的节点，又是条件队列的节点，在入队的时候，我们用 Node 把线程包装一下，然后把 Node 放入两个队列中，我们看下 Node 的数据结构，如下：

```java
static final class Node {
    /**
     * 同步队列单独的属性
     */
    //node 是共享模式
    static final Node SHARED = new Node();

    //node 是排它模式
    static final Node EXCLUSIVE = null;

    // 当前节点的前节点
    // 节点 acquire 成功后就会变成head
    // head 节点不能被 cancelled
    volatile Node prev;

    // 当前节点的下一个节点
    volatile Node next;

    /**
     * 两个队列共享的属性
     */
    // 表示当前节点的状态，通过节点的状态来控制节点的行为
    // 普通同步节点，就是 0 ，条件节点是 CONDITION -2
    volatile int waitStatus;

    // waitStatus 的状态有以下几种
    // 被取消
    static final int CANCELLED =  1;

    // SIGNAL 状态的意义：同步队列中的节点在自旋获取锁的时候，如果前一个节点的状态是 SIGNAL，那么自己就可以阻塞休息了，否则自己一直自旋尝试获得锁
    static final int SIGNAL    = -1;

    // 表示当前 node 正在条件队列中，当有节点从同步队列转移到条件队列时，状态就会被更改成 CONDITION
    static final int CONDITION = -2;

    // 无条件传播,共享模式下，该状态的进程处于可运行状态
    static final int PROPAGATE = -3;

    // 当前节点的线程
    volatile Thread thread;

    // 在同步队列中，nextWaiter 并不真的是指向其下一个节点，我们用 next 表示同步队列的下一个节点，nextWaiter 只是表示当前 Node 是排它模式还是共享模式
    // 但在条件队列中，nextWaiter 就是表示下一个节点元素
    Node nextWaiter;
}
```

从 Node 的结构中，我们需要重点关注 waitStatus 字段，Node 的很多操作都是围绕着 waitStatus 字段进行的。

Node 的 pre、next 属性是同步队列中的链表前后指向字段，nextWaiter 是条件队列中下一个节点的指向字段，但在同步队列中，nextWaiter 只是一个标识符，表示当前节点是共享还是排它模式。



##### 1.3.5 共享锁和排它锁的区别

排它锁的意思是同一时刻，只能有一个线程可以获得锁，也只能有一个线程可以释放锁。

共享锁可以允许多个线程获得同一个锁，并且可以设置获取锁的线程数量。



#### 1.4 Condition

刚才我们看条件队列 ConditionObject 时，发现其是实现 Condition 接口的，现在我们一起来看下 Condition 接口，其类注释上是这么写的：

1. 当 lock 代替 synchronized 来加锁时，Condition 就可以用来代替 Object 中相应的监控方法了，比如 Object#wait ()、Object#notify、Object#notifyAll 这些方法；
2. 提供了一种线程协作方式：一个线程被暂停执行，直到被其它线程唤醒；
3. Condition 实例是绑定在锁上的，通过 Lock#newCondition 方法可以产生该实例；
4. 除了特殊说明外，任意空值作为方法的入参，都会抛出空指针；
5. Condition 提供了明确的语义和行为，这点和 Object 监控方法不同。

类注释上甚至还给我们举了一个例子：

假设我们有一个有界边界的队列，支持 put 和 take 方法，需要满足：
1：如果试图往空队列上执行 take，线程将会阻塞，直到队列中有可用的元素为止；
2：如果试图往满的队列上执行 put，线程将会阻塞，直到队列中有空闲的位置为止。

1、2 中线程阻塞都会到条件队列中去阻塞。

take 和 put 两种操作如果依靠一个条件队列，那么每次只能执行一种操作，所以我们可以新建两个条件队列，这样就可以分别执行操作了，看了这个需求，是不是觉得很像我们第三章学习的队列？实际上注释上给的 demo 就是我们学习过的队列，篇幅有限，感兴趣的可以看看 ConditionDemo 这个测试类。

除了类注释，Condition 还定义出一些方法，这些方法奠定了条件队列的基础，方法主要有：

```java
void await() throws InterruptedException;
```

这个方法的主要作用是：使当前线程一直等待，直到被 signalled 或被打断。

当以下四种情况发生时，条件队列中的线程将被唤醒

1. 有线程使用了 signal 方法，正好唤醒了条件队列中的当前线程；
2. 有线程使用了 signalAll 方法；
3. 其它线程打断了当前线程，并且当前线程支持被打断；
4. 被虚假唤醒 (即使没有满足以上 3 个条件，wait 也是可能被偶尔唤醒，虚假唤醒定义可以参考： https://en.wikipedia.org/wiki/Spurious_wakeup)。

被唤醒时，有一点需要注意的是：线程从条件队列中苏醒时，必须重新获得锁，才能真正被唤醒，这个我们在说源码的时候，也会强调这个。

await 方法还有带等待超时时间的，如下：

```java
// 返回的 long 值表示剩余的给定等待时间，如果返回的时间小于等于 0 ，说明等待时间过了
// 选择纳秒是为了避免计算剩余等待时间时的截断误差
long awaitNanos(long nanosTimeout) throws InterruptedException;

// 虽然入参可以是任意单位的时间，但底层仍然转化成纳秒
boolean await(long time, TimeUnit unit) throws InterruptedException;
```

除了等待方法，还是唤醒线程的两个方法，如下：

```java
// 唤醒条件队列中的一个线程，在被唤醒前必须先获得锁
void signal();

// 唤醒条件队列中的所有线程
void signalAll();
```

至此，AQS 基本的属性就已经介绍完了，接着让我们来看一看 AQS 的重要方法。



### 2 同步器的状态

在同步器中，我们有两个状态，一个叫做 state，一个叫做 waitStatus，两者是完全不同的概念：

1. state 是锁的状态，是 int 类型，子类继承 AQS 时，都是要根据 state 字段来判断有无得到锁，比如当前同步器状态是 0，表示可以获得锁，当前同步器状态是 1，表示锁已经被其他线程持有，当前线程无法获得锁；
2. waitStatus 是节点（Node）的状态，种类很多，一共有初始化 (0)、CANCELLED (1)、SIGNAL (-1)、CONDITION (-2)、PROPAGATE (-3)，各个状态的含义可以见上文。

这两个状态我们需要牢记，不要混淆了。



### 3 获取锁

获取锁最直观的感受就是使用 Lock.lock () 方法来获得锁，最终目的是想让线程获得对资源的访问权。

Lock 一般是 AQS 的子类，lock 方法根据情况一般会选择调用 AQS 的 acquire 或 tryAcquire 方法。

acquire 方法 AQS 已经实现了，tryAcquire 方法是等待子类去实现，acquire 方法制定了获取锁的框架，先尝试使用 tryAcquire 方法获取锁，获取不到时，再入同步队列中等待锁。tryAcquire 方法 AQS 中直接抛出一个异常，表明需要子类去实现，子类可以根据同步器的 state 状态来决定是否能够获得锁，接下来我们详细看下 acquire 的源码解析。

acquire 也分两种，一种是排它锁，一种是共享锁，我们一一来看下：



#### 3.1 acquire 排它锁

```java
// 排它模式下，尝试获得锁
public final void acquire(int arg) {
    // tryAcquire 方法是需要实现类去实现的，实现思路一般都是 cas 给 state 赋值来决定是否能获得锁
    if (!tryAcquire(arg) &&
        // addWaiter 入参代表是排他模式
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```

以上代码的主要步骤是（流程见整体架构图中红色场景）：

1. 尝试执行一次 tryAcquire，如果成功直接返回，失败走 2；
2. 线程尝试进入同步队列，首先调用 addWaiter 方法，把当前线程放到同步队列的队尾；
3. 接着调用 acquireQueued 方法，两个作用，1：阻塞当前节点，2：节点被唤醒时，使其能够获得锁；
4. 如果 2、3 失败了，打断线程。



##### 3.1.1 addWaiter

代码很少，每个方法都是关键，接下来我们先来看下 addWaiter 的源码实现：

```java
// 方法主要目的：node 追加到同步队列的队尾
// 入参 mode 表示 Node 的模式（排它模式还是共享模式）
// 出参是新增的 node
// 主要思路：
// 新 node.pre = 队尾
// 队尾.next = 新 node
private Node addWaiter(Node mode) {
    // 初始化 Node
    Node node = new Node(Thread.currentThread(), mode);
    // 这里的逻辑和 enq 一致，enq 的逻辑仅仅多了队尾是空，初始化的逻辑
    // 这个思路在 java 源码中很常见，先简单的尝试放一下，成功立马返回，如果不行，再 while 循环
    // 很多时候，这种算法可以帮忙解决大部分的问题，大部分的入队可能一次都能成功，无需自旋
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    //自旋保证node加入到队尾
    enq(node);
    return node;
}

// 线程加入同步队列中方法，追加到队尾
// 这里需要重点注意的是，返回值是添加 node 的前一个节点
private Node enq(final Node node) {
    for (;;) {
        // 得到队尾节点
        Node t = tail;
        // 如果队尾为空，说明当前同步队列都没有初始化，进行初始化
        // tail = head = new Node();
        if (t == null) {
            if (compareAndSetHead(new Node()))
                tail = head;
        // 队尾不为空，将当前节点追加到队尾
        } else {
            node.prev = t;
            // node 追加到队尾
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return t;
            }
        }
    }
}
```

如果之前学习过队列的同学，对这个方法应该感觉毫不吃力，就是把新的节点追加到同步队列的队尾。

其中有一点值得我们学习的地方，是在 addWaiter 方法中，并没有进入方法后立马就自旋，而是先尝试一次追加到队尾，如果失败才自旋，因为大部分操作可能一次就会成功，这种思路在我们写自旋的时候可以借鉴。



##### 3.1.2 acquireQueued

下一步就是要阻塞当前线程了，是 acquireQueued 方法来实现的，我们来看下源码实现：

```java
// 主要做两件事情：
// 1：通过不断的自旋尝试使自己前一个节点的状态变成 signal，然后阻塞自己。
// 2：获得锁的线程执行完成之后，释放锁时，会把阻塞的 node 唤醒,node 唤醒之后再次自旋，尝试获得锁
// 返回 false 表示获得锁成功，返回 true 表示失败
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        // 自旋
        for (;;) {
            // 选上一个节点
            final Node p = node.predecessor();
            // 有两种情况会走到 p == head：
            // 1:node 之前没有获得锁，进入 acquireQueued 方法时，才发现他的前置节点就是头节点，于是尝试获得一次锁；
            // 2:node 之前一直在阻塞沉睡，然后被唤醒，此时唤醒 node 的节点正是其前一个节点，也能走到 if
            // 如果自己 tryAcquire 成功，就立马把自己设置成 head，把上一个节点移除
            // 如果 tryAcquire 失败，尝试进入同步队列
            if (p == head && tryAcquire(arg)) {
                // 获得锁，设置成 head 节点
                setHead(node);
                //p被回收
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }

            // shouldParkAfterFailedAcquire 把 node 的前一个节点状态置为 SIGNAL
            // 只要前一个节点状态是 SIGNAL了，那么自己就可以阻塞(park)了
            // parkAndCheckInterrupt 阻塞当前线程
            if (shouldParkAfterFailedAcquire(p, node) &&
                // 线程是在这个方法里面阻塞的，醒来的时候仍然在无限 for 循环里面，就能再次自旋尝试获得锁
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        // 如果获得node的锁失败，将 node 从队列中移除
        if (failed)
            cancelAcquire(node);
    }
}
```

此方法的注释还是很清楚的，我们接着看下此方法的核心：shouldParkAfterFailedAcquire，这个方法的主要目的就是把前一个节点的状态置为 SIGNAL，只要前一个节点的状态是 SIGNAL，当前节点就可以阻塞了（parkAndCheckInterrupt 就是使节点阻塞的方法），源码如下：

```java
// 当前线程可以安心阻塞的标准，就是前一个节点线程状态是 SIGNAL 了。
// 入参 pred 是前一个节点，node 是当前节点。

// 关键操作：
// 1：确认前一个节点是否有效，无效的话，一直往前找到状态不是取消的节点。
// 2: 把前一个节点状态置为 SIGNAL。
// 1、2 两步操作，有可能一次就成功，有可能需要外部循环多次才能成功（外面是个无限的 for 循环），但最后一定是可以成功的
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
    int ws = pred.waitStatus;
    // 如果前一个节点 waitStatus 状态已经是 SIGNAL 了，直接返回，不需要在自旋了
    if (ws == Node.SIGNAL)
        /*
         * This node has already set status asking a release
         * to signal it, so it can safely park.
         */
        return true;
    // 如果当前节点状态已经被取消了。
    if (ws > 0) {
        /*
         * Predecessor was cancelled. Skip over predecessors and
         * indicate retry.
         */
        // 找到前一个状态不是取消的节点，因为把当前 node 挂在有效节点身上
        // 因为节点状态是取消的话，是无效的，是不能作为 node 的前置节点的，所以必须找到 node 的有效节点才行
        do {
            node.prev = pred = pred.prev;
        } while (pred.waitStatus > 0);
        pred.next = node;
    // 否则直接把节点状态置 为SIGNAL
    } else {
        /*
         * waitStatus must be 0 or PROPAGATE.  Indicate that we
         * need a signal, but don't park yet.  Caller will need to
         * retry to make sure it cannot acquire before parking.
         */
        compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
    }
    return false;
}
```

acquire 整个过程非常长，代码也非常多，但注释很清楚，可以一行一行仔细看看代码。

总结一下，acquire 方法大致分为三步：

1. 使用 tryAcquire 方法尝试获得锁，获得锁直接返回，获取不到锁的走 2；
2. 把当前线程组装成节点（Node），追加到同步队列的尾部（addWaiter）；
3. 自旋，使同步队列中当前节点的前置节点状态为 signal 后，然后阻塞自己。

整体的代码结构比较清晰，一些需要注意的点，都用注释表明了，强烈建议阅读下源码。



#### 3.2 acquireShared 获取共享锁

acquireShared 整体流程和 acquire 相同，代码也很相似，重复的源码就不贴了，我们就贴出来不一样的代码来，也方便大家进行比较：

1. 第一步尝试获得锁的地方，有所不同，排它锁使用的是 tryAcquire 方法，共享锁使用的是 tryAcquireShared 方法，如下图：
   ![图片描述](http://img.mukewang.com/5dc37cd30001522218420446.png)
2. 第二步不同，在于节点获得排它锁时，仅仅把自己设置为同步队列的头节点即可（setHead 方法），但如果是共享锁的话，还会去唤醒自己的后续节点，一起来获得该锁（setHeadAndPropagate 方法），不同之处如下（左边排它锁，右边共享锁）：
   ![图片描述](http://img.mukewang.com/5dc37cc40001100018630914.png)

接下来我们一起来看下 setHeadAndPropagate 方法的源码：

```java
// 主要做两件事情
// 1:把当前节点设置成头节点
// 2:看看后续节点有无正在等待，并且也是共享模式的，有的话唤醒这些节点
private void setHeadAndPropagate(Node node, int propagate) {
    Node h = head; // Record old head for check below
    // 当前节点设置成头节点
    setHead(node);
    /*
     * Try to signal next queued node if:
     *   Propagation was indicated(表示指示) by caller,
     *     or was recorded (as h.waitStatus either before
     *     or after setHead) by a previous operation
     *     (note: this uses sign-check of waitStatus because
     *      PROPAGATE status may transition to SIGNAL.)
     * and
     *   The next node is waiting in shared mode,
     *     or we don't know, because it appears null
     *
     * The conservatism(保守) in both of these checks may cause
     * unnecessary wake-ups, but only when there are multiple
     * racing acquires/releases, so most need signals now or soon
     * anyway.
     */
    // propagate > 0 表示已经有节点获得共享锁了
    if (propagate > 0 || h == null || h.waitStatus < 0 ||
        (h = head) == null || h.waitStatus < 0) {
        Node s = node.next;
        //共享模式，还唤醒头节点的后置节点
        if (s == null || s.isShared())
            doReleaseShared();
    }
}

// 释放后置共享节点
private void doReleaseShared() {
    /*
     * Ensure that a release propagates, even if there are other
     * in-progress acquires/releases.  This proceeds in the usual
     * way of trying to unparkSuccessor of head if it needs
     * signal. But if it does not, status is set to PROPAGATE to
     * ensure that upon release, propagation continues.
     * Additionally, we must loop in case a new node is added
     * while we are doing this. Also, unlike other uses of
     * unparkSuccessor, we need to know if CAS to reset status
     * fails, if so rechecking.
     */
    for (;;) {
        Node h = head;
        // 还没有到队尾，此时队列中至少有两个节点
        if (h != null && h != tail) {
            int ws = h.waitStatus;
            // 如果队列状态是 SIGNAL ，说明后续节点都需要唤醒
            if (ws == Node.SIGNAL) {
                // CAS 保证只有一个节点可以运行唤醒的操作
                if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0))
                    continue;            // loop to recheck cases
                // 进行唤醒操作
                unparkSuccessor(h);
            }
            else if (ws == 0 &&
                     !compareAndSetWaitStatus(h, 0, Node.PROPAGATE))
                continue;                // loop on failed CAS
        }
        // 第一种情况，头节点没有发生移动，结束。
        // 第二种情况，因为此方法可以被两处调用，一次是获得锁的地方，一处是释放锁的地方，
        // 加上共享锁的特性就是可以多个线程获得锁，也可以释放锁，这就导致头节点可能会发生变化，
        // 如果头节点发生了变化，就继续循环，一直循环到头节点不变化时，结束循环。
        if (h == head)                   // loop if head changed
            break;
    }
}
```

这个就是共享锁独特的地方，当一个线程获得锁后，它就会去唤醒排在它后面的其它节点，让其它节点也能够获得锁。



### 4 总结

AQS 的内容实在太多了，这只是 AQS 的上篇，但内容长度已经超过了我们平时章节的三倍了，所以不得不分节，下一章仍然是 AQS，主要讲解锁的释放和条件队列两大部分。

## **31 AbstractQueuedSynchronizer 源码解析（下）**

### 引导语

AQS 的内容太多，所以我们分成了两个章节，没有看过 AQS 上半章节的同学可以回首看一下哈，上半章节里面说了很多锁的基本概念，基本属性，如何获得锁等等，本章我们主要聊下如何释放锁和同步队列两大部分。



### 1 释放锁

释放锁的触发时机就是我们常用的 Lock.unLock () 方法，目的就是让线程释放对资源的访问权（流程见整体架构图紫色路线）。

释放锁也是分为两类，一类是排它锁的释放，一类是共享锁的释放，我们分别来看下。



#### 1.1 释放排它锁 release

排它锁的释放就比较简单了，从队头开始，找它的下一个节点，如果下一个节点是空的，就会从尾开始，一直找到状态不是取消的节点，然后释放该节点，源码如下：

```java
// unlock 的基础方法
public final boolean release(int arg) {
    // tryRelease 交给实现类去实现，一般就是用当前同步器状态减去 arg，如果返回 true 说明成功释放锁。
    if (tryRelease(arg)) {
        Node h = head;
        // 头节点不为空，并且非初始化状态
        if (h != null && h.waitStatus != 0)
            // 从头开始唤醒等待锁的节点
            unparkSuccessor(h);
        return true;
    }
    return false;
}

// 很有意思的方法，当线程释放锁成功后，从 node 开始唤醒同步队列中的节点
// 通过唤醒机制,保证线程不会一直在同步队列中阻塞等待
private void unparkSuccessor(Node node) {
    // node 节点是当前释放锁的节点，也是同步队列的头节点
    int ws = node.waitStatus;
    // 如果节点已经被取消了，把节点的状态置为初始化
    if (ws < 0)
        compareAndSetWaitStatus(node, ws, 0);

    // 拿出 node 节点的后面一个节点
    Node s = node.next;
    // s 为空，表示 node 的后一个节点为空
    // s.waitStatus 大于0，代表 s 节点已经被取消了
    // 遇到以上这两种情况，就从队尾开始，向前遍历，找到第一个 waitStatus 字段不是被取消的
    if (s == null || s.waitStatus > 0) {
        s = null;
        // 这里从尾迭代，而不是从头开始迭代是有原因的。
        // 主要是因为节点被阻塞的时候，是在 acquireQueued 方法里面被阻塞的，唤醒时也一定会在 acquireQueued 方法里面被唤醒，唤醒之后的条件是，判断当前节点的前置节点是否是头节点，这里是判断当前节点的前置节点，所以这里必须使用从尾到头的迭代顺序才行，目的就是为了过滤掉无效的前置节点，不然节点被唤醒时，发现其前置节点还是无效节点，就又会陷入阻塞。
        for (Node t = tail; t != null && t != node; t = t.prev)
            // t.waitStatus <= 0 说明 t 没有被取消，肯定还在等待被唤醒
            if (t.waitStatus <= 0)
                s = t;
    }
    // 唤醒以上代码找到的线程
    if (s != null)
        LockSupport.unpark(s.thread);
}
```



#### 1.2 释放共享锁 releaseShared

释放共享锁的方法是 releaseShared，主要分成两步：

1. tryReleaseShared 尝试释放当前共享锁，失败返回 false，成功走 2；
2. 唤醒当前节点的后续阻塞节点，这个方法我们之前看过了，线程在获得共享锁的时候，就会去唤醒其后面的节点，方法名称为：doReleaseShared。

我们一起来看下 releaseShared 的源码：

```java
// 共享模式下，释放当前线程的共享锁
public final boolean releaseShared(int arg) {
    if (tryReleaseShared(arg)) {
        // 这个方法就是线程在获得锁时，唤醒后续节点时调用的方法
        doReleaseShared();
        return true;
    }
    return false;
}
```



### 2 条件队列的重要方法

在看条件队列的方法之前，我们先得弄明白为什么有了同步队列，还需要条件队列？

主要是因为并不是所有场景一个同步队列就可以搞定的，在遇到锁 + 队列结合的场景时，就需要 Lock + Condition 配合才行，先使用 Lock 来决定哪些线程可以获得锁，哪些线程需要到同步队列里面排队阻塞；获得锁的多个线程在碰到队列满或者空的时候，可以使用 Condition 来管理这些线程，让这些线程阻塞等待，然后在合适的时机后，被正常唤醒。

同步队列 + 条件队列联手使用的场景，最多被使用到锁 + 队列的场景中。

所以说条件队列也是不可或缺的一环。

接下来我们来看一下条件队列一些比较重要的方法，以下方法都在 ConditionObject 内部类中。



#### 2.1 入队列等待 await

获得锁的线程，如果在碰到队列满或空的时候，就会阻塞住，这个阻塞就是用条件队列实现的，这个动作我们叫做入条件队列，方法名称为 await，流程见整体架构图中深绿色箭头流向，我们一起来看下 await 的源码：

```java
// 线程入条件队列
public final void await() throws InterruptedException {
    if (Thread.interrupted())
        throw new InterruptedException();
    // 加入到条件队列的队尾
    Node node = addConditionWaiter();
    // 标记位置 A
    // 加入条件队列后，会释放 lock 时申请的资源，唤醒同步队列队列头的节点
    // 自己马上就要阻塞了，必须马上释放之前 lock 的资源，不然自己不被唤醒的话，别的线程永远得不到该共享资源了
    int savedState = fullyRelease(node);
    int interruptMode = 0;
    // 确认node不在同步队列上，再阻塞，如果 node 在同步队列上，是不能够上锁的
    // 目前想到的只有两种可能：
    // 1:node 刚被加入到条件队列中，立马就被其他线程 signal 转移到同步队列中去了
    // 2:线程之前在条件队列中沉睡，被唤醒后加入到同步队列中去
    while (!isOnSyncQueue(node)) {
        // this = AbstractQueuedSynchronizer$ConditionObject
        // 阻塞在条件队列上
        LockSupport.park(this);
        if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
            break;
    }
    // 标记位置 B
    // 其他线程通过 signal 已经把 node 从条件队列中转移到同步队列中的数据结构中去了
    // 所以这里节点苏醒了，直接尝试 acquireQueued
    if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
        interruptMode = REINTERRUPT;
    if (node.nextWaiter != null) // clean up if cancelled
        // 如果状态不是CONDITION，就会自动删除
        unlinkCancelledWaiters();
    if (interruptMode != 0)
        reportInterruptAfterWait(interruptMode);
}
```

await 方法有几点需要特别注意：

1. 上述代码标记位置 A 处，节点在准备进入条件队列之前，一定会先释放当前持有的锁，不然自己进去条件队列了，其余的线程都无法获得锁了；
2. 上述代码标记位置 B 处，此时节点是被 Condition.signal 或者 signalAll 方法唤醒的，此时节点已经成功的被转移到同步队列中去了（整体架构图中蓝色流程），所以可以直接执行 acquireQueued 方法；
3. Node 在条件队列中的命名，源码喜欢用 Waiter 来命名，所以我们在条件队列中看到 Waiter，其实就是 Node。

await 方法中有两个重要方法：addConditionWaiter 和 unlinkCancelledWaiters，我们一一看下。



##### 2.1.1 addConditionWaiter

addConditionWaiter 方法主要是把节点放到条件队列中，方法源码如下：

```java
// 增加新的 waiter 到队列中，返回新添加的 waiter
// 如果尾节点状态不是 CONDITION 状态，删除条件队列中所有状态不是 CONDITION 的节点
// 如果队列为空，新增节点作为队列头节点，否则追加到尾节点上
private Node addConditionWaiter() {
    Node t = lastWaiter;
    // If lastWaiter is cancelled, clean out.
    // 如果尾部的 waiter 不是 CONDITION 状态了，删除
    if (t != null && t.waitStatus != Node.CONDITION) {
        unlinkCancelledWaiters();
        t = lastWaiter;
    }
    // 新建条件队列 node
    Node node = new Node(Thread.currentThread(), Node.CONDITION);
    // 队列是空的，直接放到队列头
    if (t == null)
        firstWaiter = node;
    // 队列不为空，直接到队列尾部
    else
        t.nextWaiter = node;
    lastWaiter = node;
    return node;
}
```

整体过程比较简单，就是追加到队列的尾部，其中有个重要方法叫做 unlinkCancelledWaiters，这个方法会删除掉条件队列中状态不是 CONDITION 的所有节点，我们来看下 unlinkCancelledWaiters 方法的源码，如下：



##### 2.1.2 unlinkCancelledWaiters

```java
// 会检查尾部的 waiter 是不是已经不是CONDITION状态了
// 如果不是，删除这些 waiter
private void unlinkCancelledWaiters() {
    Node t = firstWaiter;
    // trail 表示上一个状态,这个字段作用非常大，可以把状态都是 CONDITION 的 node 串联起来，即使 node 之间有其他节点都可以
    Node trail = null;
    while (t != null) {
        Node next = t.nextWaiter;
        // 当前node的状态不是CONDITION，删除自己
        if (t.waitStatus != Node.CONDITION) {
            //删除当前node
            t.nextWaiter = null;
            // 如果 trail 是空的，咱们循环又是从头开始的，说明从头到当前节点的状态都不是 CONDITION
            // 都已经被删除了，所以移动队列头节点到当前节点的下一个节点
            if (trail == null)
                firstWaiter = next;
            // 如果找到上次状态是CONDITION的节点的话，先把当前节点删掉，然后把自己挂到上一个状态是 CONDITION 的节点上
            else
                trail.nextWaiter = next;
            // 遍历结束，最后一次找到的CONDITION节点就是尾节点
            if (next == null)
                lastWaiter = trail;
        }
        // 状态是 CONDITION 的 Node
        else
            trail = t;
        // 继续循环，循环顺序从头到尾
        t = next;
    }
}
```

为了方便大家理解这个方法，画了一个释义图，如下：
![图片描述](http://img.mukewang.com/5dba408600017eaf07211157.png)



#### 2.2 单个唤醒 signal

signal 方法是唤醒的意思，比如之前队列满了，有了一些线程因为 take 操作而被阻塞进条件队列中，突然队列中的元素被线程 A 消费了，线程 A 就会调用 signal 方法，唤醒之前阻塞的线程，会从条件队列的头节点开始唤醒（流程见整体架构图中蓝色部分），源码如下：

```java
// 唤醒阻塞在条件队列中的节点
public final void signal() {
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    // 从头节点开始唤醒
    Node first = firstWaiter;
    if (first != null)
        // doSignal 方法会把条件队列中的节点转移到同步队列中去
        doSignal(first);
}
// 把条件队列头节点转移到同步队列去
private void doSignal(Node first) {
    do {
        // nextWaiter为空，说明到队尾了
        if ( (firstWaiter = first.nextWaiter) == null)
            lastWaiter = null;
        // 从队列头部开始唤醒，所以直接把头节点.next 置为 null，这种操作其实就是把 node 从条件队列中移除了
        // 这里有个重要的点是，每次唤醒都是从队列头部开始唤醒，所以把 next 置为 null 没有关系，如果唤醒是从任意节点开始唤醒的话，就会有问题，容易造成链表的割裂
        first.nextWaiter = null;
        // transferForSignal 方法会把节点转移到同步队列中去
        // 通过 while 保证 transferForSignal 能成功
        // 等待队列的 node 不用管他，在 await 的时候，会自动清除状态不是 Condition 的节点(通过 unlinkCancelledWaiters 方法)
        // (first = firstWaiter) != null  = true 的话，表示还可以继续循环， = false 说明队列中的元素已经循环完了
    } while (!transferForSignal(first) &&
             (first = firstWaiter) != null);
}   
```

我们来看下最关键的方法：transferForSignal。

```java
// 返回 true 表示转移成功， false 失败
// 大概思路：
// 1. node 追加到同步队列的队尾
// 2. 将 node 的前一个节点状态置为 SIGNAL，成功直接返回，失败直接唤醒
// 可以看出来 node 的状态此时是 0 了
final boolean transferForSignal(Node node) {
    /*
     * If cannot change waitStatus, the node has been cancelled.
     */
    // 将 node 的状态从 CONDITION 修改成初始化，失败返回 false
    if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
        return false;

    // 当前队列加入到同步队列，返回的 p 是 node 在同步队列中的前一个节点
    // 看命名是 p，实际是 pre 单词的缩写
    Node p = enq(node);
    int ws = p.waitStatus;
    // 状态修改成 SIGNAL，如果成功直接返回
    // 把当前节点的前一个节点修改成 SIGNAL 的原因，是因为 SIGNAL 本身就表示当前节点后面的节点都是需要被唤醒的
    if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
        // 如果 p 节点被取消，或者状态不能修改成SIGNAL，直接唤醒
        LockSupport.unpark(node.thread);
    return true;
}
```

整个源码下来，我们可以看到，唤醒条件队列中的节点，实际上就是把条件队列中的节点转移到同步队列中，并把其前置节点状态置为 SIGNAL。



#### 2.3 全部唤醒 signalAll

signalAll 的作用是唤醒条件队列中的全部节点，源码如下：

```java
    public final void signalAll() {
        if (!isHeldExclusively())
            throw new IllegalMonitorStateException();
        // 拿到头节点
        Node first = firstWaiter;
        if (first != null)
            // 从头节点开始唤醒条件队列中所有的节点
            doSignalAll(first);
    }
    // 把条件队列所有节点依次转移到同步队列去
    private void doSignalAll(Node first) {
        lastWaiter = firstWaiter = null;
        do {
            // 拿出条件队列队列头节点的下一个节点
            Node next = first.nextWaiter;
            // 把头节点从条件队列中删除
            first.nextWaiter = null;
            // 头节点转移到同步队列中去
            transferForSignal(first);
            // 开始循环头节点的下一个节点
            first = next;
        } while (first != null);
    }
```

从源码中可以看出，其本质就是 for 循环调用 transferForSignal 方法，将条件队列中的节点循环转移到同步队列中去。



### 3 总结

AQS 源码终于说完了，你都懂了么，可以在默默回忆一下 AQS 架构图，看看这张图现在能不能看懂了。![图片描述](http://img.mukewang.com/5dba40350001cb6f21120846.png)

## **32 ReentrantLock 源码解析**

### 引导语

上两小节我们学习了 AQS，本章我们就要来学习一下第一个 AQS 的实现类：ReentrantLock，看看其底层是如何组合 AQS ，实现了自己的那些功能。

本章的描述思路是先描述清楚 ReentrantLock 的构成组件，然后使用加锁和释放锁的方法把这些组件串起来。



### 1 类注释

ReentrantLock 中文我们习惯叫做可重入互斥锁，可重入的意思是同一个线程可以对同一个共享资源重复的加锁或释放锁，互斥就是 AQS 中的排它锁的意思，只允许一个线程获得锁。

我们来一起来看下类注释上都有哪些重要信息：

1. 可重入互斥锁，和 synchronized 锁具有同样的功能语义，但更有扩展性；
2. 构造器接受 fairness 的参数，fairness 是 ture 时，保证获得锁时的顺序，false 不保证；
3. 公平锁的吞吐量较低，获得锁的公平性不能代表线程调度的公平性；
4. tryLock() 无参方法没有遵循公平性，是非公平的（lock 和 unlock 都有公平和非公平，而 tryLock 只有公平锁，所以单独拿出来说一说）。

我们补充一下第二点，ReentrantLock 的公平和非公平，是针对获得锁来说的，如果是公平的，可以保证同步队列中的线程从头到尾的顺序依次获得锁，非公平的就无法保证，在释放锁的过程中，我们是没有公平和非公平的说法的。



### 2 类结构

ReentrantLock 类本身是不继承 AQS 的，实现了 Lock 接口，如下：

```java
public class ReentrantLock implements Lock, java.io.Serializable {}
```

Lock 接口定义了各种加锁，释放锁的方法，接口有如下几个：

```java
// 获得锁方法，获取不到锁的线程会到同步队列中阻塞排队
void lock();
// 获取可中断的锁
void lockInterruptibly() throws InterruptedException;
// 尝试获得锁，如果锁空闲，立马返回 true，否则返回 false
boolean tryLock();
// 带有超时等待时间的锁，如果超时时间到了，仍然没有获得锁，返回 false
boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
// 释放锁
void unlock();
// 得到新的 Condition
Condition newCondition();
```

ReentrantLock 就负责实现这些接口，我们使用时，直接面对的也是这些方法，这些方法的底层实现都是交给 Sync 内部类去实现的，Sync 类的定义如下：

```java
abstract static class Sync extends AbstractQueuedSynchronizer {}
```

Sync 继承了 AbstractQueuedSynchronizer ，所以 Sync 就具有了锁的框架，根据 AQS 的框架，Sync 只需要实现 AQS 预留的几个方法即可，但 Sync 也只是实现了部分方法，还有一些交给子类 NonfairSync 和 FairSync 去实现了，NonfairSync 是非公平锁，FairSync 是公平锁，定义如下：

```java
// 同步器 Sync 的两个子类锁
static final class FairSync extends Sync {}
static final class NonfairSync extends Sync {}
```

几个类整体的结构如下：
![图片描述](http://img.mukewang.com/5dc3836d0001e9e407620694.png)

图中 Sync、NonfairSync、FairSync 都是静态内部类的方式实现的，这个也符合 AQS 框架定义的实现标准。



### 3 构造器

ReentrantLock 构造器有两种，代码如下：

```java
// 无参数构造器，相当于 ReentrantLock(false)，默认是非公平的
public ReentrantLock() {
    sync = new NonfairSync();
}

public ReentrantLock(boolean fair) {
    sync = fair ? new FairSync() : new NonfairSync();
}
```

无参构造器默认构造是非公平的锁，有参构造器可以选择。

从构造器中可以看出，公平锁是依靠 FairSync 实现的，非公平锁是依靠 NonfairSync 实现的。



### 4 Sync 同步器

Sync 表示同步器，继承了 AQS，UML 图如下：
![图片描述](http://img.mukewang.com/5dc3834e00010f2b14020610.png)

从 UML 图中可以看出，lock 方法是个抽象方法，留给 FairSync 和 NonfairSync 两个子类去实现，我们一起来看下剩余重要的几个方法。



#### 4.1 nonfairTryAcquire

```java
// 尝试获得非公平锁
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    // 同步器的状态是 0，表示同步器的锁没有人持有
    if (c == 0) {
        // 当前线程持有锁
        if (compareAndSetState(0, acquires)) {
            // 标记当前持有锁的线程是谁
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    // 如果当前线程已经持有锁了，同一个线程可以对同一个资源重复加锁，代码实现的是可重入锁
    else if (current == getExclusiveOwnerThread()) {
        // 当前线程持有锁的数量 + acquires
        int nextc = c + acquires;
        // int 是有最大值的，<0 表示持有锁的数量超过了 int 的最大值
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    //否则线程进入同步队列
    return false;
}
```

以上代码有三点需要注意：

1. 通过判断 AQS 的 state 的状态来决定是否可以获得锁，0 表示锁是空闲的；
2. else if 的代码体现了可重入加锁，同一个线程对共享资源重入加锁，底层实现就是把 state + 1，并且可重入的次数是有限制的，为 Integer 的最大值；
3. 这个方法是非公平的，所以只有非公平锁才会用到，公平锁是另外的实现。

无参的 tryLock 方法调用的就是此方法，tryLock 的方法源码如下：

```java
public boolean tryLock() {
    // 入参数是 1 表示尝试获得一次锁
    return sync.nonfairTryAcquire(1);
}
```



#### 4.1 tryRelease

```java
// 释放锁方法，非公平和公平锁都使用
protected final boolean tryRelease(int releases) {
    // 当前同步器的状态减去释放的个数，releases 一般为 1
    int c = getState() - releases;
    // 当前线程根本都不持有锁，报错
    if (Thread.currentThread() != getExclusiveOwnerThread())
        throw new IllegalMonitorStateException();
    boolean free = false;
    // 如果 c 为 0，表示当前线程持有的锁都释放了
    if (c == 0) {
        free = true;
        setExclusiveOwnerThread(null);
    }
    // 如果 c 不为 0，那么就是可重入锁，并且锁没有释放完，用 state 减去 releases 即可，无需做其他操作
    setState(c);
    return free;
}
```

tryRelease 方法是公平锁和非公平锁都公用的，在锁释放的时候，是没有公平和非公平的说法的。

从代码中可以看到，锁最终被释放的标椎是 state 的状态为 0，在重入加锁的情况下，需要重入解锁相应的次数后，才能最终把锁释放，比如线程 A 对共享资源 B 重入加锁 5 次，那么释放锁的话，也需要释放 5 次之后，才算真正的释放该共享资源了。



### 5 FairSync 公平锁

FairSync 公平锁只实现了 lock 和 tryAcquire 两个方法，lock 方法非常简单，如下：

```java
// acquire 是 AQS 的方法，表示先尝试获得锁，失败之后进入同步队列阻塞等待
final void lock() {
    acquire(1);
}
```

tryAcquire 方法是 AQS 在 acquire 方法中留给子类实现的抽象方法，FairSync 中实现的源码如下：

```java
protected final boolean tryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
        // hasQueuedPredecessors 是实现公平的关键
        // 会判断当前线程是不是属于同步队列的头节点的下一个节点(头节点是释放锁的节点)
        // 如果是(返回false)，符合先进先出的原则，可以获得锁
        // 如果不是(返回true)，则继续等待
        if (!hasQueuedPredecessors() &&
            compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    // 可重入锁
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0)
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    return false;
}
```

代码和 Sync 的 nonfairTryAcquire 方法实现类似，唯一不同的是在获得锁时使用 hasQueuedPredecessors 方法体现了其公平性。



### 6 NonfairSync 非公平锁

NonfairSync 底层实现了 lock 和 tryAcquire 两个方法，如下:

```java
// 加锁
final void lock() {
    // cas 给 state 赋值
    if (compareAndSetState(0, 1))
        // cas 赋值成功，代表拿到当前锁，记录拿到锁的线程
        setExclusiveOwnerThread(Thread.currentThread());
    else
        // acquire 是抽象类AQS的方法,
        // 会再次尝试获得锁，失败会进入到同步队列中
        acquire(1);
}
// 直接使用的是 Sync.nonfairTryAcquire 方法 
protected final boolean tryAcquire(int acquires) {
    return nonfairTryAcquire(acquires);
}
```



### 7 如何串起来

以上内容主要说了 ReentrantLock 的基本结构，比较零散，那么这些零散的结构如何串联起来呢？我们是通过 lock、tryLock、unlock 这三个 API 将以上几个类串联起来，我们来一一看下。



#### 7.1 lock 加锁

lock 的代码实现：

```java
public void lock() {
    sync.lock();
}
```

其底层的调用关系(只是简单表明调用关系，并不是完整分支图)如下：
![图片描述](http://img.mukewang.com/5dc38319000106d713060430.png)



#### 7.2 tryLock 尝试加锁

tryLock 有两个方法，一种是无参的，一种提供了超时时间的入参，两种内部是不同的实现机制，代码分别如下:

```java
// 无参构造器
public boolean tryLock() {
    return sync.nonfairTryAcquire(1);
}
// timeout 为超时的时间，在时间内，仍没有得到锁，会返回 false
public boolean tryLock(long timeout, TimeUnit unit)
        throws InterruptedException {
    return sync.tryAcquireNanos(1, unit.toNanos(timeout));
}
```

接着我们一起看下两个 tryLock 的调用关系图，下图显示的是无参 tryLock 的调用关系图，如下：
![图片描述](http://img.mukewang.com/5dc382ff00012db705660184.png)

我们需要注意的是 tryLock 无参方法底层走的就是非公平锁实现，没有公平锁的实现。

下图展示的是带有超时时间的有参 tryLock 的调用实现图：
![图片描述](http://img.mukewang.com/5dc3824f0001906d08120370.png)



#### 7.3 unlock 释放锁

unlock 释放锁的方法，底层调用的是 Sync 同步器的 release 方法，release 是 AQS 的方法，分成两步：

1. 尝试释放锁，如果释放失败，直接返回 false；
2. 释放成功，从同步队列的头节点的下一个节点开始唤醒，让其去竞争锁。

第一步就是我们上文中 Sync 的 tryRelease 方法（4.1），第二步 AQS 已经实现了。

unLock 的源码如下：

```java
// 释放锁
public void unlock() {
    sync.release(1);
}
```



#### 7.4 Condition

ReentrantLock 对 Condition 并没有改造，直接使用 AQS 的 ConditionObject 即可。



### 8 总结

这就是我们在研究完 AQS 源码之后，碰到的第一个锁，是不是感觉很简单，AQS 搭建了整个锁架构，子类锁只需要根据场景，实现 AQS 对应的方法即可，不仅仅是 ReentrantLock 是这样，JUC 中的其它锁也都是这样，只要对 AQS 了如指掌，锁其实非常简单。



## **33 CountDownLatch、Atomic 等其它源码解析**

### 引导语

本小节和大家一起来看看 CountDownLatch 和 Atomic 打头的原子操作类，CountDownLatch 的源码非常少，看起来比较简单，但 CountDownLatch 的实际应用却不是很容易；Atomic 原子操作类就比较好理解和应用，接下来我们分别来看一下。



### 1 CountDownLatch

CountDownLatch 中文有的叫做计数器，也有翻译为计数锁，其最大的作用不是为了加锁，而是通过计数达到等待的功能，主要有两种形式的等待：

1. 让一组线程在全部启动完成之后，再一起执行（先启动的线程需要阻塞等待后启动的线程，直到一组线程全部都启动完成后，再一起执行）；
2. 主线程等待另外一组线程都执行完成之后，再继续执行。

我们会举一个示例来演示这两种情况，但在这之前，我们先来看看 CountDownLatch 的底层源码实现，这样就会清晰一点，不然一开始就来看示例，估计很难理解。

CountDownLatch 有两个比较重要的 API，分别是 await 和 countDown，管理着线程能否获得锁和锁的释放（也可以称为对 state 的计数增加和减少）。



#### 1.1 await

await 我们可以叫做等待，也可以叫做加锁，有两种不同入参的方法，源码如下：

```java
public void await() throws InterruptedException {
    sync.acquireSharedInterruptibly(1);
}
// 带有超时时间的，最终都会转化成毫秒
public boolean await(long timeout, TimeUnit unit)
    throws InterruptedException {
    return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
}
```

两个方法底层使用的都是 sync，sync 是一个同步器，是 CountDownLatch 的内部类实现的，如下：

```java
private static final class Sync extends AbstractQueuedSynchronizer {}
```

可以看出来 Sync 继承了 AbstractQueuedSynchronizer，具备了同步器的通用功能。

无参 await 底层使用的是 acquireSharedInterruptibly 方法，有参的使用的是 tryAcquireSharedNanos 方法，这两个方法都是 AQS 的方法，底层实现很相似，主要分成两步：

1. 使用子类的 tryAcquireShared 方法尝试获得锁，如果获取了锁直接返回，获取不到锁走 2；
2. 获取不到锁，用 Node 封装一下当前线程，追加到同步队列的尾部，等待在合适的时机去获得锁。

第二步是 AQS 已经实现了，第一步 tryAcquireShared 方法是交给 Sync 实现的，源码如下：

```java
// 如果当前同步器的状态是 0 的话，表示可获得锁
protected int tryAcquireShared(int acquires) {
    return (getState() == 0) ? 1 : -1;
}
```

获得锁的代码也很简单，直接根据同步器的 state 字段来进行判断，但还是有两点需要注意一下：

1. 获得锁时，state 的值不会发生变化，像 ReentrantLock 在获得锁时，会把 state + 1，但 CountDownLatch 不会；
2. CountDownLatch 的 state 并不是 AQS 的默认值 0，而是可以赋值的，是在 CountDownLatch 初始化的时候赋值的，代码如下：

```java
// 初始化,count 代表 state 的初始化值
public CountDownLatch(int count) {
    if (count < 0) throw new IllegalArgumentException("count < 0");
    // new Sync 底层代码是 state = count;
    this.sync = new Sync(count);
}
```

这里的初始化的 count 和一般的锁意义不太一样，count 表示我们希望等待的线程数，在两种不同的等待场景中，count 有不同的含义：

1. 让一组线程在全部启动完成之后，再一起执行的等待场景下， count 代表一组线程的个数；
2. 主线程等待另外一组线程都执行完成之后，再继续执行的等待场景下，count 代表一组线程的个数。

所以我们可以把 count 看做我们希望等待的一组线程的个数，可能我们是等待一组线程全部启动完成，可能我们是等待一组线程全部执行完成。



#### 1.2 countDown

countDown 中文翻译为倒计时，每调用一次，都会使 state 减一，底层调用的方法如下：

```java
public void countDown() {
    sync.releaseShared(1);
}
```

releaseShared 是 AQS 定义的方法，方法主要分成两步：

1. 尝试释放锁（tryReleaseShared），锁释放失败直接返回，释放成功走 2；
2. 释放当前节点的后置等待节点。

第二步 AQS 已经实现了，第一步是 Sync 实现的，我们一起来看下 tryReleaseShared 方法的实现源码：

```java
// 对 state 进行递减，直到 state 变成 0；
// state 递减为 0 时，返回 true，其余返回 false
protected boolean tryReleaseShared(int releases) {
    // 自旋保证 CAS 一定可以成功
    for (;;) {
        int c = getState();
        // state 已经是 0 了，直接返回 false
        if (c == 0)
            return false;
        // 对 state 进行递减
        int nextc = c-1;
        if (compareAndSetState(c, nextc))
            return nextc == 0;
    }
}
```

从源码中可以看到，只有到 count 递减到 0 时，countDown 才会返回 true。



#### 1.3 示例

看完 CountDownLatch 两个重要 API 后，我们来实现文章开头说的两个功能：

1. 让一组线程在全部启动完成之后，再一起执行；
2. 主线程等待另外一组线程都执行完成之后，再继续执行。

代码在 CountDownLatchDemo 类中，大家可以调试看看，源码如下：

```java
public class CountDownLatchDemo {

  // 线程任务
  class Worker implements Runnable {
    // 定义计数锁用来实现功能 1
    private final CountDownLatch startSignal;
    // 定义计数锁用来实现功能 2
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
      this.startSignal = startSignal;
      this.doneSignal = doneSignal;
    }
		// 子线程做的事情
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName()+" begin");
        // await 时有两点需要注意：await 时 state 不会发生变化，2：startSignal 的state初始化是 1，所以所有子线程都是获取不到锁的，都需要到同步队列中去等待，达到先启动的子线程等待后面启动的子线程的结果
        startSignal.await();
        doWork();
        // countDown 每次会使 state 减一，doneSignal 初始化为 9，countDown 前 8 次执行都会返回 false (releaseShared 方法)，执行第 9 次时，state 递减为 0，会 countDown 成功，表示所有子线程都执行完了，会释放 await 在 doneSignal 上的主线程
        doneSignal.countDown();
        System.out.println(Thread.currentThread().getName()+" end");
      } catch (InterruptedException ex) {
      } // return;
    }

    void doWork() throws InterruptedException {
      System.out.println(Thread.currentThread().getName()+"sleep 5s …………");
      Thread.sleep(5000l);
    }
  }

  @Test
  public void test() throws InterruptedException {
    // state 初始化为 1 很关键，子线程是不断的 await，await 时 state 是不会变化的，并且发现 state 都是 1，所有线程都获取不到锁
    // 造成所有线程都到同步队列中去等待，当主线程执行 countDown 时，就会一起把等待的线程给释放掉
    CountDownLatch startSignal = new CountDownLatch(1);
    // state 初始化成 9，表示有 9 个子线程执行完成之后，会唤醒主线程
    CountDownLatch doneSignal = new CountDownLatch(9);

    for (int i = 0; i < 9; ++i) // create and start threads
    {
      new Thread(new Worker(startSignal, doneSignal)).start();
    }
    System.out.println("main thread begin");
    // 这行代码唤醒 9 个子线程，开始执行(因为 startSignal 锁的状态是 1，所以调用一次 countDown 方法就可以释放9个等待的子线程)
    startSignal.countDown();
    // 这行代码使主线程陷入沉睡，等待 9 个子线程执行完成之后才会继续执行(就是等待子线程执行 doneSignal.countDown())
    doneSignal.await();           
    System.out.println("main thread end");
  }
}
执行结果：
Thread-0 begin
Thread-1 begin
Thread-2 begin
Thread-3 begin
Thread-4 begin
Thread-5 begin
Thread-6 begin
Thread-7 begin
Thread-8 begin
main thread begin
Thread-0sleep 5s …………
Thread-1sleep 5s …………
Thread-4sleep 5s …………
Thread-3sleep 5s …………
Thread-2sleep 5s …………
Thread-8sleep 5s …………
Thread-7sleep 5s …………
Thread-6sleep 5s …………
Thread-5sleep 5s …………
Thread-0 end
Thread-1 end
Thread-4 end
Thread-3 end
Thread-2 end
Thread-8 end
Thread-7 end
Thread-6 end
Thread-5 end
main thread end
```

从执行结果中，可以看出已经实现了以上两个功能，实现比较绕，大家可以根据注释，debug 看一看。



### 2 Atomic 原子操作类

Atomic 打头的原子操作类有很多，涉及到 Java 常用的数字类型的，基本都有相应的 Atomic 原子操作类，如下图所示：
![图片描述](http://img.mukewang.com/5dc384550001ccb314920926.png)

Atomic 打头的原子操作类，在高并发场景下，都是线程安全的，我们可以放心使用。

我们以 AtomicInteger 为例子，来看下主要的底层实现：

```java
private volatile int value;

// 初始化
public AtomicInteger(int initialValue) {
    value = initialValue;
}
// 得到当前值
public final int get() {
    return value;
}
// 自增 1，并返回自增之前的值    
public final int getAndIncrement() {
    return unsafe.getAndAddInt(this, valueOffset, 1);
}
// 自减 1，并返回自增之前的值    
public final int getAndDecrement() {
    return unsafe.getAndAddInt(this, valueOffset, -1);
}
```

从源码中，我们可以看到，线程安全的操作方法，底层都是使用 unsafe 方法实现，以上几个 unsafe 方法不是使用 Java 实现的，都是线程安全的。

AtomicInteger 是对 int 类型的值进行自增自减，那如果 Atomic 的对象是个自定义类怎么办呢，Java 也提供了自定义对象的原子操作类，叫做 AtomicReference。AtomicReference 类可操作的对象是个泛型，所以支持自定义类，其底层是没有自增方法的，操作的方法可以作为函数入参传递，源码如下：

```java
// 对 x 执行 accumulatorFunction 操作
// accumulatorFunction 是个函数，可以自定义想做的事情
// 返回老值
public final V getAndAccumulate(V x,
                                BinaryOperator<V> accumulatorFunction) {
    // prev 是老值，next 是新值
    V prev, next;
    // 自旋 + CAS 保证一定可以替换老值
    do {
        prev = get();
        // 执行自定义操作
        next = accumulatorFunction.apply(prev, x);
    } while (!compareAndSet(prev, next));
    return prev;
}
```



### 3 总结

CountDownLatch 的源码实现简单，但真的要用好还是不简单的，其使用场景比较复杂，建议同学们可以 debug 一下 CountDownLatchDemo，在增加实战能力基础上，增加底层的理解能力。



## **34 只求问倒：连环相扣系列锁面试题**

### 引导语

面试中，问锁主要是两方面：锁的日常使用场景 + 锁原理，锁的日常使用场景主要考察对锁 API 的使用熟练度，看看你是否真的使用过这些 API，而不是纸上谈兵，锁原理主要就是问 AQS 底层的源码原理了，如果问得更加深入的话，可能会现场让你实现一个简单的锁，简单要求的会让你直接使用 AQS API，复杂要求的可能需要重新实现 AQS。

接下来我们一起看一看关于锁的常见源码面试题。



### 1 AQS 相关面试题



#### 1.1 说说自己对 AQS 的理解？

答：回答这样的问题的时候，面试官主要考察的是你对 AQS 的知识有没有系统的整理，建议回答的方向是由大到小，由全到细，由使用到原理。

如果和面试官面对面的话，可以边说边画出我们在 AQS 源码解析上中画出的整体架构图，并且可以这么说：

1. AQS 是一个锁框架，它定义了锁的实现机制，并开放出扩展的地方，让子类去实现，比如我们在 lock 的时候，AQS 开放出 state 字段，让子类可以根据 state 字段来决定是否能够获得锁，对于获取不到锁的线程 AQS 会自动进行管理，无需子类锁关心，这就是 lock 时锁的内部机制，封装的很好，又暴露出子类锁需要扩展的地方；
2. AQS 底层是由同步队列 + 条件队列联手组成，同步队列管理着获取不到锁的线程的排队和释放，条件队列是在一定场景下，对同步队列的补充，比如获得锁的线程从空队列中拿数据，肯定是拿不到数据的，这时候条件队列就会管理该线程，使该线程阻塞；
3. AQS 围绕两个队列，提供了四大场景，分别是：获得锁、释放锁、条件队列的阻塞，条件队列的唤醒，分别对应着 AQS 架构图中的四种颜色的线的走向。

以上三点都是 AQS 全局方面的描述，接着你可以问问面试官要不要说细一点，可以的话，按照 AQS 源码解析上下两篇，把四大场景都说一下就好了。

这样说的好处是很多的：

1. 面试的主动权把握在自己手里，而且都是自己掌握的知识点；
2. 由全到细的把 AQS 全部说完，会给面试官一种你对 AQS 了如指掌的感觉，再加上全部说完耗时会很久，面试时间又很有限，面试官就不会再问关于 AQS 一些刁钻的问题了，这样 AQS 就可以轻松过关。

当然如果你对 AQS 了解的不是很深，那么就大概回答下 AQS 的大体架构就好了，就不要说的特别细，免得给自己挖坑。



#### 1.2 多个线程通过锁请求共享资源，获取不到锁的线程怎么办？

答：加锁(排它锁)主要分为以下四步：

1. 尝试获得锁，获得锁了直接返回，获取不到锁的走到 2；
2. 用 Node 封装当前线程，追加到同步队列的队尾，追加到队尾时，又有两步，如 3 和 4；
3. 自旋 + CAS 保证前一个节点的状态置为 signal；
4. 阻塞自己，使当前线程进入等待状态。

获取不到锁的线程会进行 2、3、4 步，最终会陷入等待状态，这个描述的是排它锁。



#### 1.3 问题 1.2 中，排它锁和共享锁的处理机制是一样的么？

答：排它锁和共享锁在问题 1.2 中的 2、3、4 步骤都是一样的， 不同的是在于第一步，线程获得排它锁的时候，仅仅把自己设置为同步队列的头节点即可，但如果是共享锁的话，还会去唤醒自己的后续节点，一起来获得该锁。



#### 1.4 共享锁和排它锁的区别？

答：排它锁的意思是同一时刻，只能有一个线程可以获得锁，也只能有一个线程可以释放锁。

共享锁可以允许多个线程获得同一个锁，并且可以设置获取锁的线程数量，共享锁之所以能够做到这些，是因为线程一旦获得共享锁，把自己设置成同步队列的头节点后，会自动的去释放头节点后等待获取共享锁的节点，让这些等待节点也一起来获得共享锁，而排它锁就不会这么干。



#### 1.5 排它锁和共享锁说的是加锁时的策略，那么锁释放时有排它锁和共享锁的策略么？

答：是的，排它锁和共享锁，主要体现在加锁时，多个线程能否获得同一个锁。

但在锁释放时，是没有排它锁和共享锁的概念和策略的，概念仅仅针对锁获取。



#### 1.6 描述下同步队列？

答：同步队列底层的数据结构就是双向的链表，节点叫做 Node，头节点叫做 head，尾节点叫做 tail，节点和节点间的前后指向分别叫做 prev、next，如果是面对面面试的话，可以画一下 AQS 整体架构图中的同步队列。

同步队列的作用：阻塞获取不到锁的线程，并在适当时机释放这些线程。

实现的大致过程：当多个线程都来请求锁时，某一时刻有且只有一个线程能够获得锁（排它锁），那么剩余获取不到锁的线程，都会到同步队列中去排队并阻塞自己，当有线程主动释放锁时，就会从同步队列中头节点开始释放一个排队的线程，让线程重新去竞争锁。



#### 1.7 描述下线程入、出同步队列的时机和过程？

答：(排它锁为例)从 AQS 整体架构图中，可以看出同步队列入队和出队都是有两个箭头指向，所以入队和出队的时机各有两个。

同步队列入队时机：

1. 多个线程请求锁，获取不到锁的线程需要到同步队列中排队阻塞；
2. 条件队列中的节点被唤醒，会从条件队列中转移到同步队列中来。

同步队列出队时机：

1. 锁释放时，头节点出队；
2. 获得锁的线程，进入条件队列时，会释放锁，同步队列头节点开始竞争锁。

四个时机的过程可以参考 AQS 源码解析，1 参考 acquire 方法执行过程，2 参考 signal 方法，3 参考 release 方法，4 参考 await 方法。



#### 1.8 为什么 AQS 有了同步队列之后，还需要条件队列？

答：的确，一般情况下，我们只需要有同步队列就好了，但在上锁后，需要操作队列的场景下，一个同步队列就搞不定了，需要条件队列进行功能补充，比如当队列满时，执行 put 操作的线程会进入条件队列等待，当队列空时，执行 take 操作的线程也会进入条件队列中等待，从一定程度上来看，条件队列是对同步队列的场景功能补充。



#### 1.9 描述一下条件队列中的元素入队和出队的时机和过程？

答：入队时机：执行 await 方法时，当前线程会释放锁，并进入到条件队列。

出队时机：执行 signal、signalAll 方法时，节点会从条件队列中转移到同步队列中。

具体的执行过程，可以参考源码解析中 await 和 signal 方法。



#### 1.10 描述一下条件队列中的节点转移到同步队列中去的时机和过程？

答：时机：当有线程执行 signal、signalAll 方法时，从条件队列的头节点开始，转移到同步队列中去。

过程主要是以下几步：

1. 找到条件队列的头节点，头节点 next 属性置为 null，从条件队列中移除了；
2. 头节点追加到同步队列的队尾；
3. 头节点状态（waitStatus）从 CONDITION 修改成 0（初始化状态）；
4. 将节点的前一个节点状态置为 SIGNAL。



#### 1.11 线程入条件队列时，为什么需要释放持有的锁？

答：原因很简单，如果当前线程不释放锁，一旦跑去条件队里中阻塞了，后续所有的线程都无法获得锁，正确的场景应该是：当前线程释放锁，到条件队列中去阻塞后，其他线程仍然可以获得当前锁。



### 2 AQS 子类锁面试题



#### 2.1 你在工作中如何使用锁的，写一个看一看？

答：这个照实说就好了，具体 demo 可以参考：demo.sixth.ConditionDemo。



#### 2.1 如果我要自定义锁，大概的实现思路是什么样子的？

答：现在有很多类似的问题，比如让你自定义队列，自定义锁等等，面试官其实并不是想让我们重新造一个轮子，而是想考察一下我们对于队列、锁理解的深度，我们只需要选择自己最熟悉的 API 描述一下就好了，所以这题我们可以选择 ReentrantLock 来描述一下实现思路：

1. 新建内部类继承 AQS，并实现 AQS 的 tryAcquire 和 tryRelease 两个方法，在 tryAcquire 方法里面实现控制能否获取锁，比如当同步器状态 state 是 0 时，即可获得锁，在 tryRelease 方法里面控制能否释放锁，比如将同步器状态递减到 0 时，即可释放锁；
2. 对外提供 lock、release 两个方法，lock 表示获得锁的方法，底层调用 AQS 的 acquire 方法，release 表示释放锁的方法，底层调用 AQS 的 release 方法。



#### 2.2 描述 ReentrantLock 两大特性：可重入性和公平性？底层分别如何实现的？

答：可重入性说的是线程可以对共享资源重复加锁，对应的，释放时也可以重复释放，对于 ReentrantLock 来说，在获得锁的时候，state 会加 1，重复获得锁时，不断的对 state 进行递增即可，比如目前 state 是 4，表示线程已经对共享资源加锁了 4 次，线程每次释放共享资源的锁时，state 就会递减 1，直到递减到 0 时，才算真正释放掉共享资源。

公平性和非公平指的是同步队列中的线程得到锁的机制，如果同步队列中的线程按照阻塞的顺序得到锁，我们称之为公平的，反之是非公平的，公平的底层实现是 ReentrantLock 的 tryAcquire 方法（调用的是 AQS 的 hasQueuedPredecessors 方法）里面实现的，要释放同步队列的节点时（或者获得锁时），判断当前线程节点是不是同步队列的头节点的后一个节点，如果是就释放，不是则不能释放，通过这种机制，保证同步队列中的线程得到锁时，是按照从头到尾的顺序的。



#### 2.3 如果一个线程需要等待一组线程全部执行完之后再继续执行，有什么好的办法么？是如何实现的？

答：CountDownLatch 就提供了这样的机制，比如一组线程有 5 个，只需要在初始化 CountDownLatch 时，给同步器的 state 赋值为 5，主线程执行 CountDownLatch.await ，子线程都执行 CountDownLatch.countDown 即可。



#### 2.4 Atomic 原子操作类可以保证线程安全，如果操作的对象是自定义的类的话，要如何做呢？

答： Java 为这种情况提供了一个 API：AtomicReference，AtomicReference 类可操作的对象是个泛型，所以支持自定义类。



### 3 总结

关于 AQS 和锁场景的面试题，其实网上也很多，各个大厂出的题目也都不一样，但考察问题的本质都是一致的，如果把 AQS 架构图中，AQS 的组成和四种颜色箭头的发起时机，调用过程都弄清楚了，回答 AQS 的各种问题都会游刃有余。

## **35 经验总结：各种锁在工作中使用场景和细节**

### 引导语

本章主要说一说锁在工作中的使用场景，主要以 synchronized 和 CountDownLatch 为例，会分别描述一下这两种锁的使用场景和姿势。



### 1 synchronized

synchronized 是可重入的排它锁，和 ReentrantLock 锁功能相似，任何使用 synchronized 的地方，几乎都可以使用 ReentrantLock 来代替，两者最大的相似点就是：可重入 + 排它锁，两者的区别主要有这些：

1. ReentrantLock 的功能更加丰富，比如提供了 Condition，可以打断的加锁 API、能满足锁 + 队列的复杂场景等等；
2. ReentrantLock 有公平锁和非公平锁之分，而 synchronized 都是非公平锁；
3. 两者的使用姿势也不同，ReentrantLock 需要申明，有加锁和释放锁的 API，而 synchronized 会自动对代码块进行加锁释放锁的操作，synchronized 使用起来更加方便。

synchronized 和 ReentrantLock 功能相近，所以我们就以 synchronized 举例。



#### 1.1 共享资源初始化

在分布式的系统中，我们喜欢把一些死的配置资源在项目启动的时候加锁到 JVM 内存里面去，这样请求在拿这些共享配置资源时，就可直接从内存里面拿，不必每次都从数据库中拿，减少了时间开销。

一般这样的共享资源有：死的业务流程配置 + 死的业务规则配置。

共享资源初始化的步骤一般为：项目启动 -> 触发初始化动作 ->单线程从数据库中捞取数据 -> 组装成我们需要的数据结构 -> 放到 JVM 内存中。

在项目启动时，为了防止共享资源被多次加载，我们往往会加上排它锁，让一个线程加载共享资源完成之后，另外一个线程才能继续加载，此时的排它锁我们可以选择 synchronized 或者 ReentrantLock，我们以 synchronized 为例，写了 mock 的代码，如下：

```java
  // 共享资源
  private static final Map<String, String> SHARED_MAP = Maps.newConcurrentMap();
  // 有无初始化完成的标志位
  private static boolean loaded = false;

  /**
   * 初始化共享资源
   */
  @PostConstruct
  public void init(){
    if(loaded){
      return;
    }
    synchronized (this){
      // 再次 check
      if(loaded){
        return;
      }
      log.info("SynchronizedDemo init begin");
      // 从数据库中捞取数据，组装成 SHARED_MAP 的数据格式
      loaded = true;
      log.info("SynchronizedDemo init end");
    }
  }
```

不知道大家有没有从上述代码中发现 @PostConstruct 注解，@PostConstruct 注解的作用是在 Spring 容器初始化时，再执行该注解打上的方法，也就是说上图说的 init 方法触发的时机，是在 Spring 容器启动的时候。

大家可以下载演示代码，找到 DemoApplication 启动文件，在 DemoApplication 文件上右击 run，就可以启动整个 Spring Boot 项目，在 init 方法上打上断点就可以调试了。

我们在代码中使用了 synchronized 来保证同一时刻，只有一个线程可以执行初始化共享资源的操作，并且我们加了一个共享资源加载完成的标识位（loaded），来判断是否加载完成了，如果加载完成，那么其它加载线程直接返回。

如果把 synchronized 换成 ReentrantLock 也是一样的实现，只不过需要显示的使用 ReentrantLock 的 API 进行加锁和释放锁，使用 ReentrantLock 有一点需要注意的是，我们需要在 try 方法块中加锁，在 finally 方法块中释放锁，这样保证即使 try 中加锁后发生异常，在 finally 中也可以正确的释放锁。

有的同学可能会问，不是可以直接使用了 ConcurrentHashMap 么，为什么还需要加锁呢？的确 ConcurrentHashMap 是线程安全的，但它只能够保证 Map 内部数据操作时的线程安全，是无法保证多线程情况下，查询数据库并组装数据的整个动作只执行一次的，我们加 synchronized 锁住的是整个操作，保证整个操作只执行一次。

完整 demo 见 SynchronizedDemo。



### 2 CountDownLatch



#### 2.1 场景

1：小明在淘宝上买了一个商品，觉得不好，把这个商品退掉(商品还没有发货，只退钱)，我们叫做单商品退款，单商品退款在后台系统中运行时，整体耗时 30 毫秒。

2：双 11，小明在淘宝上买了 40 个商品，生成了同一个订单（实际可能会生成多个订单，为了方便描述，我们说成一个），第二天小明发现其中 30 个商品是自己冲动消费的，需要把 30 个商品一起退掉。



#### 2.2 实现

此时后台只有单商品退款的功能，没有批量商品退款的功能（30 个商品一次退我们称为批量），为了快速实现这个功能，同学 A 按照这样的方案做的：for 循环调用 30 次单商品退款的接口，在 qa 环境测试的时候发现，如果要退款 30 个商品的话，需要耗时：30 * 30 = 900 毫秒，再加上其它的逻辑，退款 30 个商品差不多需要 1 秒了，这个耗时其实算很久了，当时同学 A 提出了这个问题，希望大家帮忙看看如何优化整个场景的耗时。

同学 B 当时就提出，你可以使用线程池进行执行呀，把任务都提交到线程池里面去，假如机器的 CPU 是 4 核的，最多同时能有 4 个单商品退款可以同时执行，同学 A 觉得很有道理，于是准备修改方案，为了便于理解，我们把两个方案都画出来，对比一下：
![图片描述](http://img.mukewang.com/5dc386970001fb8b12120716.png)

同学 A 于是就按照演变的方案去写代码了，过了一天，抛出了一个问题：向线程池提交了 30 个任务后，主线程如何等待 30 个任务都执行完成呢？因为主线程需要收集 30 个子任务的执行情况，并汇总返回给前端。

大家可以先不往下看，自己先思考一下，我们前几章说的那种锁可以帮助解决这个问题？

CountDownLatch 可以的，CountDownLatch 具有这种功能，让主线程去等待子任务全部执行完成之后才继续执行。

此时还有一个关键，我们需要知道子线程执行的结果，所以我们用 Runnable 作为线程任务就不行了，因为 Runnable 是没有返回值的，我们需要选择 Callable 作为任务。

我们写了一个 demo，首先我们来看一下单个商品退款的代码：

```java
// 单商品退款，耗时 30 毫秒，退款成功返回 true，失败返回 false
@Slf4j
public class RefundDemo {

  /**
   * 根据商品 ID 进行退款
   * @param itemId
   * @return
   */
  public boolean refundByItem(Long itemId) {
    try {
      // 线程沉睡 30 毫秒，模拟单个商品退款过程
      Thread.sleep(30);
      log.info("refund success,itemId is {}", itemId);
      return true;
    } catch (Exception e) {
      log.error("refundByItemError,itemId is {}", itemId);
      return false;
    }
  }
}
```

接着我们看下 30 个商品的批量退款，代码如下：

```java
@Slf4j
public class BatchRefundDemo {
	// 定义线程池
  public static final ExecutorService EXECUTOR_SERVICE =
      new ThreadPoolExecutor(10, 10, 0L,
                                TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<>(20));
  @Test
  public void batchRefund() throws InterruptedException {
    // state 初始化为 30 
    CountDownLatch countDownLatch = new CountDownLatch(30);
    RefundDemo refundDemo = new RefundDemo();

    // 准备 30 个商品
    List<Long> items = Lists.newArrayListWithCapacity(30);
    for (int i = 0; i < 30; i++) {
      items.add(Long.valueOf(i+""));
    }

    // 准备开始批量退款
    List<Future> futures = Lists.newArrayListWithCapacity(30);
    for (Long item : items) {
      // 使用 Callable，因为我们需要等到返回值
      Future<Boolean> future = EXECUTOR_SERVICE.submit(new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
          boolean result = refundDemo.refundByItem(item);
          // 每个子线程都会执行 countDown，使 state -1 ，但只有最后一个才能真的唤醒主线程
          countDownLatch.countDown();
          return result;
        }
      });
      // 收集批量退款的结果
      futures.add(future);
    }

    log.info("30 个商品已经在退款中");
    // 使主线程阻塞，一直等待 30 个商品都退款完成，才能继续执行
    countDownLatch.await();
    log.info("30 个商品已经退款完成");
    // 拿到所有结果进行分析
    List<Boolean> result = futures.stream().map(fu-> {
      try {
        // get 的超时时间设置的是 1 毫秒，是为了说明此时所有的子线程都已经执行完成了
        return (Boolean) fu.get(1,TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        e.printStackTrace();
      }
      return false;
    }).collect(Collectors.toList());
    // 打印结果统计
    long success = result.stream().filter(r->r.equals(true)).count();
    log.info("执行结果成功{},失败{}",success,result.size()-success);
  }
}
```

上述代码只是大概的底层思路，真实的项目会在此思路之上加上请求分组，超时打断等等优化措施。

我们来看一下执行的结果:
![图片描述](http://img.mukewang.com/5dc386a80001238109010819.png)

从执行的截图中，我们可以明显的看到 CountDownLatch 已经发挥出了作用，主线程会一直等到 30 个商品的退款结果之后才会继续执行。

接着我们做了一个不严谨的实验（把以上代码执行很多次，求耗时平均值），通过以上代码，30 个商品退款完成之后，整体耗时大概在 200 毫秒左右。

而通过 for 循环单商品进行退款，大概耗时在 1 秒左右，前后性能相差 5 倍左右，for 循环退款的代码如下：

```java
long begin1 = System.currentTimeMillis();
for (Long item : items) {
  refundDemo.refundByItem(item);
}
log.info("for 循环单个退款耗时{}",System.currentTimeMillis()-begin1);
```

性能的巨大提升是线程池 + 锁两者结合的功劳。



### 3 总结

本章举了实际工作中的两个小案列，看到了 CountDownLatch 和 synchronized（ReentrantLock） 是如何结合实际需求进行落地的，特别是 CountDownLatch 的案列，使用线程池 + 锁结合的方式大大提高了生产效率，所以在工作中如果你也遇到相似的场景，可以毫不犹豫地用起来。

## **36 从容不迫：重写锁的设计结构和细节**

### 引导语

有的面试官喜欢让同学在说完锁的原理之后，让你重写一个新的锁，要求现场在白板上写出大概的思路和代码逻辑，这种面试题目，蛮难的，我个人觉得其侧重点主要是两个部分：

1. 考察一下你对锁原理的理解是如何来的，如果你对源码没有解读过的话，只是看看网上的文章，或者背面试题，也是能够说出大概的原理，但你很难现场写出一个锁的实现代码，除非你真的看过源码，或者有和锁相关的项目经验；
2. 我们不需要创造，我们只需要模仿 Java 锁中现有的 API 进行重写即可。

如果你看过源码，这道题真的很简单，你可以挑选一个你熟悉的锁进行模仿。

在锁章节中我们之前说的都是排它锁，这小节我们以共享锁作为案列，自定义一个共享锁。



### 1 需求

一般自定义锁的时候，我们都是根据需求来进行定义的，不可能凭空定义出锁来，说到共享锁，大家可能会想到很多场景，比如说对于共享资源的读锁可以是共享的，比如对于数据库链接的共享访问，比如对于 Socket 服务端的链接数是可以共享的，场景有很多，我们选择共享访问数据库链接这个场景来定义一个锁。



### 2 详细设计

假定(以下设想都为假定)我们的数据库是单机 mysql，只能承受 10 个链接，创建数据库链接时，我们是通过最原始 JDBC 的方式，我们用一个接口把用 JDBC 创建链接的过程进行了封装，这个接口我们命名为：创建链接接口。

共享访问数据库链接的整体要求如下：所有请求加在一起的 mysql 链接数，最大不能超过 10（包含 10），一旦超过 10，直接报错。

在这个背景下，我们进行了下图的设计：
![图片描述](http://img.mukewang.com/5dc386f5000160e808680456.png)

这个设计最最关键的地方，就是我们通过能否获得锁，来决定是否可以得到 mysql 链接，如果能获得锁，那么就能得到链接，否则直接报错。

接着我们一起来看下落地的代码：



#### 2.1 定义锁

首先我们需要定义一个锁出来，定义时需要有两个元素：

1. 锁的定义：同步器 Sync；
2. 锁对外提供的加锁和解锁的方法。

共享锁的代码实现如下：

```java
// 共享不公平锁
public class ShareLock implements Serializable{
	// 同步器
  private final Sync sync;
  // 用于确保不能超过最大值
  private final int maxCount;

  /**
   * 初始化时给同步器 sync 赋值
   * count 代表可以获得共享锁的最大值
   */
  public ShareLock(int count) {
    this.sync = new Sync(count);
    maxCount = count;
  }

  /**
   * 获得锁
   * @return true 表示成功获得锁，false 表示失败
   */
  public boolean lock(){
    return sync.acquireByShared(1);
  }

  /**
   * 释放锁
   * @return true 表示成功释放锁，false 表示失败
   */
  public boolean unLock(){
    return sync.releaseShared(1);
  }
}  
```

从上述代码中可以看出，加锁和释放锁的实现，都依靠同步器 Sync 的底层实现。

唯一需要注意的是，锁需要规定好 API 的规范，主要是两方面：

1. API 需要什么，就是锁在初始化的时候，你需要传哪些参数给我，在 ShareLock 初始化时，需要传最大可共享锁的数目；
2. 需要定义自身的能力，即定义每个方法的入参和出参。在 ShareLock 的实现中，加锁和释放锁的入参都没有，是方法里面写死的 1，表示每次方法执行，只能加锁一次或释放锁一次，出参是布尔值，true 表示加锁或释放锁成功，false 表示失败，底层使用的都是 Sync 非公平锁。

以上这种思考方式是有方法论的，就是我们在思考一个问题时，可以从两个方面出发：API 是什么？API 有什么能力？



#### 2.2 定义同步器 Sync

Sync 直接继承 AQS ，代码如下：

```java
class Sync extends AbstractQueuedSynchronizer {

  // 表示最多有 count 个共享锁可以获得
  public Sync(int count) {
    setState(count);
  }

  // 获得 i 个锁
  public boolean acquireByShared(int i) {
    // 自旋保证 CAS 一定可以成功
    for(;;){
      if(i<=0){
        return false;
      }
      int state = getState();
      // 如果没有锁可以获得，直接返回 false
      if(state <=0 ){
        return false;
      }
      int expectState = state - i;
      // 如果要得到的锁不够了，直接返回 false
      if(expectState < 0 ){
        return false;
      }
      // CAS 尝试得到锁,CAS 成功获得锁，失败继续 for 循环
      if(compareAndSetState(state,expectState)){
        return true;
      }
    }
  }

  // 释放 i 个锁
  @Override
  protected boolean tryReleaseShared(int arg) {
    for(;;){
      if(arg<=0){
        return false;
      }
      int state = getState();
      int expectState = state + arg;
      // 超过了 int 的最大值，或者 expectState 超过了我们的最大预期
      if(expectState < 0 || expectState > maxCount){
        log.error("state 超过预期，当前 state is {},计算出的 state is {}",state
        ,expectState);
        return false;
      }
      if(compareAndSetState(state, expectState)){
        return true;
      }
    }
  }
}
```

整个代码比较清晰，我们需要注意的是：

1. 边界的判断，比如入参是否非法，释放锁时，会不会出现预期的 state 非法等边界问题，对于此类问题我们都需要加以判断，体现出思维的严谨性；
2. 加锁和释放锁，需要用 for 自旋 + CAS 的形式，来保证当并发加锁或释放锁时，可以重试成功。写 for 自旋时，我们需要注意在适当的时机要 return，不要造成死循环，CAS 的方法 AQS 已经提供了，不要自己写，我们自己写的 CAS 方法是无法保证原子性的。



#### 2.3 通过能否获得锁来决定能否得到链接

锁定义好了，我们需要把锁和获取 Mysql 链接结合起来，我们写了一个 Mysql 链接的工具类，叫做 MysqlConnection，其主要负责两大功能：

1. 通过 JDBC 建立和 Mysql 的链接；
2. 结合锁，来防止请求过大时，Mysql 的总链接数不能超过 10 个。

首先我们看下 MysqlConnection 初始化的代码：

```java
public class MysqlConnection {
  private final ShareLock lock;
  
  // maxConnectionSize 表示最大链接数
  public MysqlConnection(int maxConnectionSize) {
    lock = new ShareLock(maxConnectionSize);
  }
}
```

我们可以看到，在初始化时，需要制定最大的链接数是多少，然后把这个数值传递给锁，因为最大的链接数就是 ShareLock 锁的 state 值。

接着为了完成 1，我们写了一个 private 的方法：

```java
// 得到一个 mysql 链接，底层实现省略
private Connection getConnection(){}
```

然后我们实现 2，代码如下：

```java
// 对外获取 mysql 链接的接口
// 这里不用try finally 的结构，获得锁实现底层不会有异常
// 即使出现未知异常，也无需释放锁
public Connection getLimitConnection() {
  if (lock.lock()) {
    return getConnection();
  }
  return null;
}

// 对外释放 mysql 链接的接口
public boolean releaseLimitConnection() {
  return lock.unLock();
}
```

逻辑也比较简单，加锁时，如果获得了锁，就能返回 Mysql 的链接，释放锁时，在链接关闭成功之后，调用 releaseLimitConnection 方法即可，此方法会把锁的 state 状态加一，表示链接被释放了。

以上步骤，针对 Mysql 链接限制的场景锁就完成了。



### 3 测试

锁写好了，接着我们来测试一下，我们写了一个测试的 demo，代码如下：

```java
public static void main(String[] args) {
  log.info("模仿开始获得 mysql 链接");
  MysqlConnection mysqlConnection = new MysqlConnection(10);
  log.info("初始化 Mysql 链接最大只能获取 10 个");
  for(int i =0 ;i<12;i++){
    if(null != mysqlConnection.getLimitConnection()){
      log.info("获得第{}个数据库链接成功",i+1);
    }else {
      log.info("获得第{}个数据库链接失败：数据库连接池已满",i+1);
    }
  }
  log.info("模仿开始释放 mysql 链接");
  for(int i =0 ;i<12;i++){
    if(mysqlConnection.releaseLimitConnection()){
      log.info("释放第{}个数据库链接成功",i+1);
    }else {
      log.info("释放第{}个数据库链接失败",i+1);
    }
  }
  log.info("模仿结束");
}
```

以上代码逻辑如下：

1. 获得 Mysql 链接逻辑：for 循环获取链接，1~10 都可以获得链接，11~12 获取不到链接，因为链接被用完了；
2. 释放锁逻辑：for 循环释放链接，1~10 都可以释放成功，11~12 释放失败。

我们看下运行结果，如下图：
![图片描述](http://img.mukewang.com/5dc3870a0001d0bd10370766.png)

从运行的结果，可以看出，我们实现的 ShareLock 锁已经完成了 Mysql 链接共享的场景了。



### 4 总结

同学们阅读到这里不知道有没有两点感受：

1. 重写锁真的很简单，最关键的是要和场景完美贴合，能满足业务场景的锁才是好锁；
2. 锁其实只是来满足业务场景的，本质都是 AQS，所以只要 AQS 学会了，在了解清楚场景的情况下，重写锁都不难的。

锁章节最核心的就是 AQS 源码解析的两章，只要我们把 AQS 弄懂了，其余锁的实现，只要稍微看下源码实现，几乎马上就能知道其底层实现的原理，大多数都是通过操作 state 来完成不同的场景需求，所以还是建议大家多看 AQS 源码，多 debug AQS 源码，只要 AQS 弄清楚了，锁都很简单。

# **第7章 线程池**

## **37 ThreadPoolExecutor 源码解析**

### 引导语

线程池我们在工作中经常会用到。在请求量大时，使用线程池，可以充分利用机器资源，增加请求的处理速度，本章节我们就和大家一起来学习线程池。

本章的基础是第四章队列和第五章线程，没有看过这两章的同学可以先看一看。

本章的顺序，先说源码，弄懂原理，接着看一看面试题，最后看看实际工作中是如何运用线程池的。



### 1 整体架构图

我们画了线程池的整体图，如下：
![图片描述](http://img.mukewang.com/5dd217480001b8bf20181042.png)

本小节主要就按照这个图来进行 ThreadPoolExecutor 源码的讲解，大家在看各个方法时，可以结合这个图一起看。



#### 1.1 类结构

首先我们来看一下 ThreadPoolExecutor 的类结构，如下图：
![图片描述](http://img.mukewang.com/5dd21737000158ad06020618.png)

从上图中，我们从命名上来看，都有 Executor 的共同命名，Executor 的中文意思为执行的意思，表示对提供的任务进行执行，我们在第五章线程中学习到了几种任务：Runnable、Callable、FutureTask，之前我们都是使用 Thread 来执行这些任务的，除了 Thread，这些 Executor 命名的类和接口也是可以执行这几种任务的，接下来我们大概的看下这几个类的大概含义：

1. Executor：定义 execute 方法来执行任务，入参是 Runnable，无出参：
   ![图片描述](http://img.mukewang.com/5dd217240001c99515280682.png)
2. ExecutorService：Executor 的功能太弱，ExecutorService 丰富了对任务的执行和管理的功能，主要代码如下：

```java
// 关闭，不会接受新的任务，也不会等待未完成的任务
// 如果需要等待未完成的任务，可以使用 awaitTermination 方法
void shutdown();
// executor 是否已经关闭了，返回值 true 表示已关闭
boolean isShutdown();
// 所有的任务是否都已经终止，是的话，返回 true
boolean isTerminated();
// 在超时时间内，等待剩余的任务终止
boolean awaitTermination(long timeout, TimeUnit unit)
    throws InterruptedException;
// 提交有返回值的任务，使用 get 方法可以阻塞等待任务的执行结果返回
<T> Future<T> submit(Callable<T> task);
// 提交没有返回值的任务，如果使用 get 方法的话，任务执行完之后得到的是 null 值
Future<?> submit(Runnable task);
// 给定任务集合，返回已经执行完成的 Future 集合，每个返回的 Future 都是 isDone = true 的状态
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
    throws InterruptedException;
// 给定任务中有一个执行成功就返回，如果抛异常，其余未完成的任务将被取消
<T> T invokeAny(Collection<? extends Callable<T>> tasks)
    throws InterruptedException, ExecutionException;
```

1. AbstractExecutorService 是一个抽象类，封装了 Executor 的很多通用功能，比如：

```java
// 把 Runnable 转化成 RunnableFuture
// RunnableFuture 是一个接口，实现了 Runnable 和 Future
// FutureTask 是 RunnableFuture 的实现类，主要是对任务进行各种管理
// Runnable + Future => RunnableFuture => FutureTask
protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
    return new FutureTask<T>(runnable, value);
}
protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
    return new FutureTask<T>(callable);
}
// 提交无返回值的任务
public Future<?> submit(Runnable task) {
    if (task == null) throw new NullPointerException();
    // ftask 其实是 FutureTask
    RunnableFuture<Void> ftask = newTaskFor(task, null);
    execute(ftask);
    return ftask;
}
// 提交有返回值的任务
public <T> Future<T> submit(Callable<T> task) {
    if (task == null) throw new NullPointerException();
    // ftask 其实是 FutureTask
    RunnableFuture<T> ftask = newTaskFor(task);
    execute(ftask);
    return ftask;
}
```

有几个点需要注意下：

1. FutureTask 我们在第五章有说，其本身就是一个任务，而且具备对任务管理的功能，比如可以通过 get 方法拿到任务的执行结果；
2. submit 方法是我们平时使用线程池时提交任务的方法，支持 Runable 和 Callable 两种任务的提交，方法中 execute 方法是其子类 ThreadPoolExecutor 实现的，不管是那种任务入参，execute 方法最终执行的任务都是 FutureTask；
3. ThreadPoolExecutor 继承了 AbstractExecutorService 抽象类，具备以上三个类的所有功能。



#### 1.2 类注释

ThreadPoolExecutor 的类注释有很多，我们选取关键的注释如下：

1. ExecutorService 使用线程池中的线程执行提交的任务，线程池我们可以使用 Executors 进行配置；
2. 线程池解决两个问题：1：通过减少任务间的调度开销 (主要是通过线程池中的线程被重复使用的方式)，来提高大量任务时的执行性能；2：提供了一种方式来管理线程和消费，维护基本数据统计等工作，比如统计已完成的任务数；
3. Executors 为常用的场景设定了可直接初始化线程池的方法，比如 Executors#newCachedThreadPool 无界的线程池，并且可以自动回收；Executors#newFixedThreadPool 固定大小线程池；Executors#newSingleThreadExecutor 单个线程的线程池；
4. 为了在各种上下文中使用线程池，线程池提供可供扩展的参数设置：1：coreSize：当新任务提交时，发现运行的线程数小于 coreSize，一个新的线程将被创建，即使这时候其它工作线程是空闲的，可以通过 getCorePoolSize 方法获得 coreSize；2：maxSize: 当任务提交时，coreSize < 运行线程数 <= maxSize，但队列没有满时，任务提交到队列中，如果队列满了，在 maxSize 允许的范围内新建线程；
5. 一般来说，coreSize 和 maxSize 在线程池初始化时就已经设定了，但我们也可以通过 setCorePoolSize、setMaximumPoolSize 方法动态的修改这两个值；
6. 默认的，core threads 需要到任务提交后才创建的，但我们可以分别使用 prestartCoreThread、prestartAllCoreThreads 两个方法来提前创建一个、所有的 core threads；
7. 新的线程被默认 ThreadFactory 创建时，优先级会被限制成 NORM_PRIORITY，默认会被设置成非守护线程，这个和新建线程的继承是不同的；
8. Keep-alive times 参数的作用：1：如果当前线程池中有超过 coreSize 的线程；2：并且线程空闲的时间超过 keepAliveTime，当前线程就会被回收，这样可以避免线程没有被使用时的资源浪费；
9. 通过 setKeepAliveTime 方法可以动态的设置 keepAliveTime 的值；
10. 如果设置 allowCoreThreadTimeOut 为 ture 的话，core thread 空闲时间超过 keepAliveTime 的话，也会被回收；
11. 线程池新建时，有多种队列可供选择，比如：1：SynchronousQueue，为了避免任务被拒绝，要求线程池的 maxSize 无界，缺点是当任务提交的速度超过消费的速度时，可能出现无限制的线程增长；2：LinkedBlockingQueue，无界队列，未消费的任务可以在队列中等待；3：ArrayBlockingQueue，有界队列，可以防止资源被耗尽；
12. 队列的维护：提供了 getQueue () 方法方便我们进行监控和调试，严禁用于其他目的，remove 和 purge 两个方法可以对队列中的元素进行操作；
13. 在 Executor 已经关闭或对最大线程和最大队列都使用饱和时，可以使用 RejectedExecutionHandler 类进行异常捕捉，有如下四种处理策略：ThreadPoolExecutor.AbortPolicy、ThreadPoolExecutor.DiscardPolicy、ThreadPoolExecutor.CallerRunsPolicy、ThreadPoolExecutor.DiscardOldestPolicy；
14. 线程池提供了很多可供扩展的钩子函数，比如有：1：提供在每个任务执行之前 beforeExecute 和执行之后 afterExecute 的钩子方法，主要用于操作执行环境，比如初始化 ThreadLocals、收集统计数据、添加日志条目等；2: 如果在执行器执行完成之后想干一些事情，可以实现 terminated 方法，如果钩子方法执行时发生异常，工作线程可能会失败并立即终止。

可以看到 ThreadPoolExecutor 的注释是非常多的，也是非常重要的，我们很多面试的题目，在注释上都能找到答案。



#### 1.3 ThreadPoolExecutor 重要属性

接下来我们来看一看 ThreadPoolExecutor 都有哪些重要属性，如下：

```java
//ctl 线程池状态控制字段，由两部分组成：
//1:workerCount  wc 工作线程数，我们限制 workerCount 最大到(2^29)-1，大概 5 亿个线程
//2:runState rs 线程池的状态，提供了生命周期的控制，源码中有很多关于状态的校验，状态枚举如下：
//RUNNING（-536870912）：接受新任务或者处理队列里的任务。
//SHUTDOWN（0）：不接受新任务，但仍在处理已经在队列里面的任务。
//STOP（-536870912）：不接受新任务，也不处理队列中的任务，对正在执行的任务进行中断。
//TIDYING（1073741824）： 所以任务都被中断，workerCount 是 0，整理状态
//TERMINATED（1610612736）： terminated() 已经完成的时候

//runState 之间的转变过程：
//RUNNING -> SHUTDOWN：调用 shudown(),finalize()
//(RUNNING or SHUTDOWN) -> STOP：调用shutdownNow()
//SHUTDOWN -> TIDYING -> workerCount ==0
//STOP -> TIDYING -> workerCount ==0
//TIDYING -> TERMINATED -> terminated() 执行完成之后
private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
private static final int COUNT_BITS = Integer.SIZE - 3;// 29
private static final int CAPACITY   = (1 << COUNT_BITS) - 1;// =(2^29)-1=536870911

// Packing and unpacking ctl
private static int ctlOf(int rs, int wc) { return rs | wc; }
private static int workerCountOf(int c)  { return c & CAPACITY; }
private static int runStateOf(int c)     { return c & ~CAPACITY; }

// runState is stored in the high-order bits
private static final int RUNNING    = -1 << COUNT_BITS;//-536870912
private static final int SHUTDOWN   =  0 << COUNT_BITS;//0
private static final int STOP       =  1 << COUNT_BITS;//-536870912
private static final int TIDYING    =  2 << COUNT_BITS;//1073741824
private static final int TERMINATED =  3 << COUNT_BITS;//1610612736

// 已完成任务的计数
volatile long completedTasks;
// 线程池最大容量
private int largestPoolSize;
// 已经完成的任务数
private long completedTaskCount;
// 用户可控制的参数都是 volatile 修饰的
// 可以使用 threadFactory 创建 thread
// 创建失败一般不抛出异常，只有在 OutOfMemoryError 时候才会
private volatile ThreadFactory threadFactory;
// 饱和或者运行中拒绝任务的 handler 处理类
private volatile RejectedExecutionHandler handler;
// 线程存活时间设置
private volatile long keepAliveTime;
// 设置 true 的话，核心线程空闲 keepAliveTime 时间后，也会被回收
private volatile boolean allowCoreThreadTimeOut;
// coreSize
private volatile int corePoolSize;
// maxSize 最大限制 (2^29)-1
private volatile int maximumPoolSize;
// 默认的拒绝策略
private static final RejectedExecutionHandler defaultHandler =
    new AbortPolicy();

// 队列会 hold 住任务，并且利用队列的阻塞的特性，来保持线程的存活周期
private final BlockingQueue<Runnable> workQueue;

// 大多数情况下是控制对 workers 的访问权限
private final ReentrantLock mainLock = new ReentrantLock();
private final Condition termination = mainLock.newCondition();

// 包含线程池中所有的工作线程
private final HashSet<Worker> workers = new HashSet<Worker>();
```

属性也是非常多，为了方便理解线程池的状态扭转，画了一个图：
![图片描述](http://img.mukewang.com/5dd217040001096216760424.png)

Worker 我们可以理解成线程池中任务运行的最小单元，Worker 的大致结构如下：

```java
// 线程池中任务执行的最小单元
// Worker 继承 AQS，具有锁功能
// Worker 实现 Runnable，本身是一个可执行的任务
private final class Worker
    extends AbstractQueuedSynchronizer
    implements Runnable
{
    // 任务运行的线程
    final Thread thread;

    // 需要执行的任务
    Runnable firstTask;

    // 非常巧妙的设计,Worker本身是个 Runnable,把自己作为任务传递给 thread
    // 内部有个属性又设置了 Runnable
    Worker(Runnable firstTask) {
        setState(-1); // inhibit interrupts until runWorker
        this.firstTask = firstTask;
        // 把 Worker 自己作为 thread 运行的任务
        this.thread = getThreadFactory().newThread(this);
    }

   /** Worker 本身是 Runnable，run 方法是 Worker 执行的入口， runWorker 是外部的方法 */
    public void run() {
        runWorker(this);
    }

    private static final long serialVersionUID = 6138294804551838833L;

    // Lock methods
    // 0 代表没有锁住，1 代表锁住
    protected boolean isHeldExclusively() {
        return getState() != 0;
    }
    // 尝试加锁，CAS 赋值为 1，表示锁住
    protected boolean tryAcquire(int unused) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }
    // 尝试释放锁，释放锁没有 CAS 校验，可以任意的释放锁
    protected boolean tryRelease(int unused) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock()        { acquire(1); }
    public boolean tryLock()  { return tryAcquire(1); }
    public void unlock()      { release(1); }
    public boolean isLocked() { return isHeldExclusively(); }

    void interruptIfStarted() {
        Thread t;
        if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
            try {
                t.interrupt();
            } catch (SecurityException ignore) {
            }
        }
    }
}
```

理解 Worker 非常关键，主要有以下几点：

1. Worker 很像是任务的代理，在线程池中，最小的执行单位就是 Worker，所以 Worker 实现了 Runnable 接口，实现了 run 方法；
2. 在 Worker 初始化时 this.thread = getThreadFactory ().newThread (this) 这行代码比较关键，它把当前 Worker 作为线程的构造器入参，我们在后续的实现中会发现这样的代码：Thread t = w.thread;t.start ()，此时的 w 是 Worker 的引用申明，此处 t.start 实际上执行的就是 Worker 的 run 方法；
3. Worker 本身也实现了 AQS，所以其本身也是一个锁，其在执行任务的时候，会锁住自己，任务执行完成之后，会释放自己。



### 2 线程池的任务提交

线程池的任务提交从 submit 方法说起，submit 方法是 AbstractExecutorService 抽象类定义的，主要做了两件事情：

1. 把 Runnable 和 Callable 都转化成 FutureTask，这个我们之前看过源码了；
2. 使用 execute 方法执行 FutureTask。

execute 方法是 ThreadPoolExecutor 中的方法，源码如下：

```java
public void execute(Runnable command) {
    if (command == null)
        throw new NullPointerException();
    int c = ctl.get();
    // 工作的线程小于核心线程数，创建新的线程，成功返回，失败不抛异常
    if (workerCountOf(c) < corePoolSize) {
        if (addWorker(command, true))
            return;
        // 线程池状态可能发生变化
        c = ctl.get();
    }
    // 工作的线程大于等于核心线程数，或者新建线程失败
    // 线程池状态正常，并且可以入队的话，尝试入队列
    if (isRunning(c) && workQueue.offer(command)) {
        int recheck = ctl.get();
        // 如果线程池状态异常 尝试从队列中移除任务，可以移除的话就拒绝掉任务
        if (!isRunning(recheck) && remove(command))
            reject(command);
        // 发现可运行的线程数是 0，就初始化一个线程，这里是个极限情况，入队的时候，突然发现
        // 可用线程都被回收了
        else if (workerCountOf(recheck) == 0)
            // Runnable是空的，不会影响新增线程，但是线程在 start 的时候不会运行
            // Thread.run() 里面有判断
            addWorker(null, false);
    }
    // 队列满了，开启线程到 maxSize，如果失败直接拒绝,
    else if (!addWorker(command, false))
        reject(command);
}
```

execute 方法执行的就是整体架构图的左半边的逻辑，其中多次调用 addWorker 方法，addWorker 方法的作用是新建一个 Worker，我们一起来看下源码：

```java
// 结合线程池的情况看是否可以添加新的 worker
// firstTask 不为空可以直接执行，为空执行不了，Thread.run()方法有判断，Runnable为空不执行
// core 为 true 表示线程最大新增个数是 coresize，false 表示最大新增个数是 maxsize
// 返回 true 代表成功，false 失败
// break retry 跳到retry处，且不再进入循环
// continue retry 跳到retry处，且再次进入循环
private boolean addWorker(Runnable firstTask, boolean core) {
    retry:
    // 先是各种状态的校验
    for (;;) {
        int c = ctl.get();
        int rs = runStateOf(c);
        // Check if queue empty only if necessary.
        // rs >= SHUTDOWN 说明线程池状态不正常
        if (rs >= SHUTDOWN &&
            ! (rs == SHUTDOWN &&
               firstTask == null &&
               ! workQueue.isEmpty()))
            return false;

        for (;;) {
            int wc = workerCountOf(c);
            // 工作中的线程数大于等于容量，或者大于等于 coreSize or maxSize
            if (wc >= CAPACITY ||
                wc >= (core ? corePoolSize : maximumPoolSize))
                return false;
            if (compareAndIncrementWorkerCount(c))
                // break 结束 retry 的 for 循环
                break retry;
            c = ctl.get();  // Re-read ctl
            // 线程池状态被更改
            if (runStateOf(c) != rs)
                // 跳转到retry位置
                continue retry;
            // else CAS failed due to workerCount change; retry inner loop
        }
    }

    boolean workerStarted = false;
    boolean workerAdded = false;
    Worker w = null;
    try {
        // 巧妙的设计，Worker 本身是个 Runnable.
        // 在初始化的过程中，会把 worker 丢给 thread 去初始化
        w = new Worker(firstTask);
        final Thread t = w.thread;
        if (t != null) {
            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                // Recheck while holding lock.
                // Back out on ThreadFactory failure or if
                // shut down before lock acquired.
                int rs = runStateOf(ctl.get());
                if (rs < SHUTDOWN ||
                    (rs == SHUTDOWN && firstTask == null)) {
                    if (t.isAlive()) // precheck that t is startable
                        throw new IllegalThreadStateException();
                    workers.add(w);
                    int s = workers.size();
                    if (s > largestPoolSize)
                        largestPoolSize = s;
                    workerAdded = true;
                }
            } finally {
                mainLock.unlock();
            }
            if (workerAdded) {
                // 启动线程，实际上去执行 Worker.run 方法
                t.start();
                workerStarted = true;
            }
        }
    } finally {
        if (! workerStarted)
            addWorkerFailed(w);
    }
    return workerStarted;
}
```

addWorker 方法首先是执行了一堆校验，然后使用 new Worker (firstTask) 新建了 Worker，最后使用 t.start () 执行 Worker，上文我们说了 Worker 在初始化时的关键代码：this.thread = getThreadFactory ().newThread (this)，Worker（this） 是作为新建线程的构造器入参的，所以 t.start () 会执行到 Worker 的 run 方法上，源码如下：

```java
public void run() {
    runWorker(this);
}
```

runWorker 方法是非常重要的方法，我们一起看下源码实现：

```java
final void runWorker(Worker w) {
    Thread wt = Thread.currentThread();
    Runnable task = w.firstTask;
    //帮助gc回收
    w.firstTask = null;
    w.unlock(); // allow interrupts
    boolean completedAbruptly = true;
    try {
        // task 为空的情况：
        // 1：任务入队列了，极限情况下，发现没有运行的线程，于是新增一个线程；
        // 2：线程执行完任务执行，再次回到 while 循环。
        // 如果 task 为空，会使用 getTask 方法阻塞从队列中拿数据，如果拿不到数据，会阻塞住
        while (task != null || (task = getTask()) != null) {
            //锁住 worker
            w.lock();
            // 线程池 stop 中,但是线程没有到达中断状态，帮助线程中断
            if ((runStateAtLeast(ctl.get(), STOP) ||
                 (Thread.interrupted() &&
                  runStateAtLeast(ctl.get(), STOP))) &&
                !wt.isInterrupted())
                wt.interrupt();
            try {
                //执行 before 钩子函数
                beforeExecute(wt, task);
                Throwable thrown = null;
                try {
                    //同步执行任务
                    task.run();
                } catch (RuntimeException x) {
                    thrown = x; throw x;
                } catch (Error x) {
                    thrown = x; throw x;
                } catch (Throwable x) {
                    thrown = x; throw new Error(x);
                } finally {
                    //执行 after 钩子函数,如果这里抛出异常，会覆盖 catch 的异常
                    //所以这里异常最好不要抛出来
                    afterExecute(task, thrown);
                }
            } finally {
                //任务执行完成，计算解锁
                task = null;
                w.completedTasks++;
                w.unlock();
            }
        }
        completedAbruptly = false;
    } finally {
        //做一些抛出异常的善后工作
        processWorkerExit(w, completedAbruptly);
    }
}
```

这个方法执行的逻辑是架构图中的标红部分：
![图片描述](http://img.mukewang.com/5dd216d900012bde09340436.png)

我们聚焦一下这行代码：task.run () 此时的 task 是什么呢？此时的 task 是 FutureTask 类，所以我们继续追索到 FutureTask 类的 run 方法的源码，如下：

```java
/**
 * run 方法可以直接被调用
 * 也可以由线程池进行调用
 */
public void run() {
    // 状态不是任务创建，或者当前任务已经有线程在执行了
    if (state != NEW ||
        !UNSAFE.compareAndSwapObject(this, runnerOffset,
                                     null, Thread.currentThread()))
        return;
    try {
        Callable<V> c = callable;
        // Callable 不为空，并且已经初始化完成
        if (c != null && state == NEW) {
            V result;
            boolean ran;
            try {
                // 调用执行
                result = c.call();
                ran = true;
            } catch (Throwable ex) {
                result = null;
                ran = false;
                setException(ex);
            }
            // 给 outcome 赋值
            if (ran)
                set(result);
        }
    } finally {
        // runner must be non-null until state is settled to
        // prevent concurrent calls to run()
        runner = null;
        // state must be re-read after nulling runner to prevent
        // leaked interrupts
        int s = state;
        if (s >= INTERRUPTING)
            handlePossibleCancellationInterrupt(s);
    }
}
```

run 方法中有两行关键代码：

1. result = c.call () 这行代码是真正执行业务代码的地方；
2. set (result) 这里是给 outCome 赋值，这样 Future.get 方法执行时，就可以从 outCome 中拿值，这个我们在《Future、ExecutorService 源码解析》章节中都有说到。

至此，submit 方法就执行完成了，整体流程比较复杂，我们画一个图释义一下任务提交执行的主流![图片描述](http://img.mukewang.com/5dd216c00001511716040222.png)程：



### 3 线程执行完任务之后都在干啥

线程执行完任务之后，是消亡还是干什么呢？这是一个值得思考的问题，我们可以从源码中找到答案，从 ThreadPoolExecutor 的 runWorker 方法中，不知道有没有同学注意到一个 while 循环，我们截图释义一下：
![图片描述](http://img.mukewang.com/5dd216af0001d19319661304.png)
这个 while 循环有个 getTask 方法，getTask 的主要作用是阻塞从队列中拿任务出来，如果队列中有任务，那么就可以拿出来执行，如果队列中没有任务，这个线程会一直阻塞到有任务为止（或者超时阻塞），下面我们一起来看下 getTask 方法，源码如下：

```java
// 从阻塞队列中拿任务
private Runnable getTask() {
    boolean timedOut = false; // Did the last poll() time out?

    for (;;) {
        int c = ctl.get();
        int rs = runStateOf(c);

        //线程池关闭 && 队列为空，不需要在运行了，直接放回
        if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
            decrementWorkerCount();
            return null;
        }

        int wc = workerCountOf(c);

        // Are workers subject to culling?
        // true  运行的线程数大于 coreSize || 核心线程也可以被灭亡
        boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

        // 队列以 LinkedBlockingQueue 为例，timedOut 为 true 的话说明下面 poll 方法执行返回的是 null
        // 说明在等待 keepAliveTime 时间后，队列中仍然没有数据
        // 说明此线程已经空闲了 keepAliveTime 了
        // 再加上 wc > 1 || workQueue.isEmpty() 的判断
        // 所以使用 compareAndDecrementWorkerCount 方法使线程池数量减少 1
        // 并且直接 return，return 之后，此空闲的线程会自动被回收
        if ((wc > maximumPoolSize || (timed && timedOut))
            && (wc > 1 || workQueue.isEmpty())) {
            if (compareAndDecrementWorkerCount(c))
                return null;
            continue;
        }

        try {
            // 从队列中阻塞拿 worker
            Runnable r = timed ?
                workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                workQueue.take();
            if (r != null)
                return r;
            // 设置已超时，说明此时队列没有数据
            timedOut = true;
        } catch (InterruptedException retry) {
            timedOut = false;
        }
    }
}
```

代码有两处关键：

1. 使用队列的 poll 或 take 方法从队列中拿数据，根据队列的特性，队列中有任务可以返回，队列中无任务会阻塞；
2. 方法中的第二个 if 判断，说的是在满足一定条件下（条件看注释），会减少空闲的线程，减少的手段是使可用线程数减一，并且直接 return，直接 return 后，该线程就执行结束了，JVM 会自动回收该线程。



### 4 总结

本章节主要以 submit 方法为主线阐述了 ThreadPoolExecutor 的整体架构和底层源码，只要有队列和线程的基础知识的话，理解 ThreadPoolExecutor 并不复杂。ThreadPoolExecutor 还有一些其他的源码，比如说拒绝请求的策略、得到各种属性、设置各种属性等等方法，这些方法都比较简单，感兴趣的同学可以自己去看一看。

## **38 线程池源码面试题**

### 引导语

线程池在日常面试中占比很大，主要是因为线程池内容涉及的知识点较广，比如涉及到队列、线程、锁等等，所以很多面试官喜欢把线程池作为问题的起点，然后延伸到其它内容，由于我们专栏已经说过队列、线程、锁面试题了，所以本章面试题还是以线程池为主。



### 1：说说你对线程池的理解？

答：答题思路从大到小，从全面到局部，总的可以这么说，线程池结合了锁、线程、队列等元素，在请求量较大的环境下，可以多线程的处理请求，充分的利用了系统的资源，提高了处理请求的速度，细节可以从以下几个方面阐述：

1. ThreadPoolExecutor 类结构；
2. ThreadPoolExecutor coreSize、maxSize 等重要属性；
3. Worker 的重要作用；
4. submit 的整个过程。

通过以上总分的描述，应该可以说清楚对线程池的理解了，如果是面对面面试的话，可以边说边画出线程池的整体架构图（见《ThreadPoolExecutor 源码解析》）。



### 2：ThreadPoolExecutor、Executor、ExecutorService、Runnable、Callable、FutureTask 之间的关系？

答：以上 6 个类可以分成两大类：一种是定义任务类，一种是执行任务类。

1. 定义任务类：Runnable、Callable、FutureTask。Runnable 是定义无返回值的任务，Callable 是定义有返回值的任务，FutureTask 是对 Runnable 和 Callable 两种任务的统一，并增加了对任务的管理功能；
2. 执行任务类：ThreadPoolExecutor、Executor、ExecutorService。Executor 定义最基本的运行接口，ExecutorService 是对其功能的补充，ThreadPoolExecutor 提供真正可运行的线程池类，三个类定义了任务的运行机制。

日常的做法都是先根据定义任务类定义出任务来，然后丢给执行任务类去执行。



### 3：说一说队列在线程池中起的作用？

答：作用如下：

1. 当请求数大于 coreSize 时，可以让任务在队列中排队，让线程池中的线程慢慢的消费请求，实际工作中，实际线程数不可能等于请求数，队列提供了一种机制让任务可排队，起一个缓冲区的作用；
2. 当线程消费完所有的线程后，会阻塞的从队列中拿数据，通过队列阻塞的功能，使线程不消亡，一旦队列中有数据产生后，可立马被消费。



### 4：结合请求不断增加时，说一说线程池构造器参数的含义和表现？

答：线程池构造器各个参数的含义如下：

1. coreSize 核心线程数；
2. maxSize 最大线程数；
3. keepAliveTime 线程空闲的最大时间；
4. queue 有多种队列可供选择，比如：1：SynchronousQueue，为了避免任务被拒绝，要求线程池的 maxSize 无界，缺点是当任务提交的速度超过消费的速度时，可能出现无限制的线程增长；2：LinkedBlockingQueue，无界队列，未消费的任务可以在队列中等待；3：ArrayBlockingQueue，有界队列，可以防止资源被耗尽；
5. 线程新建的 ThreadFactory 可以自定义，也可以使用默认的 DefaultThreadFactory，DefaultThreadFactory 创建线程时，优先级会被限制成 NORM_PRIORITY，默认会被设置成非守护线程；
6. 在 Executor 已经关闭或对最大线程和最大队列都使用饱和时，可以使用 RejectedExecutionHandler 类进行异常捕捉，有如下四种处理策略：ThreadPoolExecutor.AbortPolicy、ThreadPoolExecutor.DiscardPolicy、ThreadPoolExecutor.CallerRunsPolicy、ThreadPoolExecutor.DiscardOldestPolicy。

当请求不断增加时，各个参数起的作用如下：

1. 请求数 < coreSize：创建新的线程来处理任务；
2. coreSize <= 请求数 && 能够成功入队列：任务进入到队列中等待被消费；
3. 队列已满 && 请求数 < maxSize：创建新的线程来处理任务；
4. 队列已满 && 请求数 >= maxSize：使用 RejectedExecutionHandler 类拒绝请求。



### 5：coreSize 和 maxSize 可以动态设置么，有没有规则限制？

答：一般来说，coreSize 和 maxSize 在线程池初始化时就已经设定了，但我们也可以通过 setCorePoolSize、setMaximumPoolSize 方法动态的修改这两个值。

setCorePoolSize 的限制见如下源码：

```java
// 如果新设置的值小于 coreSize,多余的线程在空闲时会被回收（不保证一定可以回收成功）
// 如果大于 coseSize，会新创建线程
public void setCorePoolSize(int corePoolSize) {
    if (corePoolSize < 0)
        throw new IllegalArgumentException();
    int delta = corePoolSize - this.corePoolSize;
    this.corePoolSize = corePoolSize;
    // 活动的线程大于新设置的核心线程数
    if (workerCountOf(ctl.get()) > corePoolSize)
        // 尝试将可以获得锁的 worker 中断，只会循环一次
        // 最后并不能保证活动的线程数一定小于核心线程数
        interruptIdleWorkers();
    // 设置的核心线程数大于原来的核心线程数
    else if (delta > 0) {
        // 并不清楚应该新增多少线程，取新增核心线程数和等待队列数据的最小值，够用就好
        int k = Math.min(delta, workQueue.size());
        // 新增线程直到k，如果期间等待队列空了也不会再新增
        while (k-- > 0 && addWorker(null, true)) {
            if (workQueue.isEmpty())
                break;
        }
    }
}
```

setMaximumPoolSize 的限制见如下源码：

```java
// 如果 maxSize 大于原来的值，直接设置。
// 如果 maxSize 小于原来的值，尝试干掉一些 worker
public void setMaximumPoolSize(int maximumPoolSize) {
    if (maximumPoolSize <= 0 || maximumPoolSize < corePoolSize)
        throw new IllegalArgumentException();
    this.maximumPoolSize = maximumPoolSize;
    if (workerCountOf(ctl.get()) > maximumPoolSize)
        interruptIdleWorkers();
}
```



### 6：说一说对于线程空闲回收的理解，源码中如何体现的？

答：空闲线程回收的时机：如果线程超过 keepAliveTime 时间后，还从阻塞队列中拿不到任务（这种情况我们称为线程空闲），当前线程就会被回收，如果 allowCoreThreadTimeOut 设置成 true，core thread 也会被回收，直到还剩下一个线程为止，如果 allowCoreThreadTimeOut 设置成 false，只会回收非 core thread 的线程。

线程在任务执行完成之后，之所有没有消亡，是因为阻塞的从队列中拿任务，在 keepAliveTime 时间后都没有拿到任务的话，就会打断阻塞，线程直接返回，线程的生命周期就结束了，JVM 会回收掉该线程对象，所以我们说的线程回收源码体现就是让线程不在队列中阻塞，直接返回了，可以见 ThreadPoolExecutor 源码解析章节第三小节的源码解析。



### 7：如果我想在线程池任务执行之前和之后，做一些资源清理的工作，可以么，如何做？

答：可以的，ThreadPoolExecutor 提供了一些钩子函数，我们只需要继承 ThreadPoolExecutor 并实现这些钩子函数即可。在线程池任务执行之前实现 beforeExecute 方法，执行之后实现 afterExecute 方法。



### 8：线程池中的线程创建，拒绝请求可以自定义实现么？如何自定义？

答：可以自定义的，线程创建默认使用的是 DefaultThreadFactory，自定义话的只需要实现 ThreadFactory 接口即可；拒绝请求也是可以自定义的，实现 RejectedExecutionHandler 接口即可；在 ThreadPoolExecutor 初始化时，将两个自定义类作为构造器的入参传递给 ThreadPoolExecutor 即可。



### 9：说说你对 Worker 的理解？

答：详见《ThreadPoolExecutor 源码解析》中 1.4 小节。



### 10：说一说 submit 方法执行的过程？

答：详见《ThreadPoolExecutor 源码解析》中 2 小节。



### 11：说一说线程执行任务之后，都在干啥？

答：线程执行任务完成之后，有两种结果：

1. 线程会阻塞从队列中拿任务，没有任务的话无限阻塞；
2. 线程会阻塞从队列中拿任务，没有任务的话阻塞一段时间后，线程返回，被 JVM 回收。



### 12：keepAliveTime 设置成负数或者是 0，表示无限阻塞？

答：这种是不对的，如果 keepAliveTime 设置成负数，在线程池初始化时，就会直接报 IllegalArgumentException 的异常，而设置成 0，队列如果是 LinkedBlockingQueue 的话，执行 workQueue.poll (keepAliveTime, TimeUnit.NANOSECONDS) 方法时，如果队列中没有任务，会直接返回 null，导致线程立马返回，不会无限阻塞。

如果想无限阻塞的话，可以把 keepAliveTime 设置的很大，把 TimeUnit 也设置的很大，接近于无限阻塞。



### 13：说一说 Future.get 方法是如何拿到线程的执行结果的？

答：我们需要明确几点：

1. submit 方法的返回结果实际上是 FutureTask，我们平时都是针对接口编程，所以使用的是 Future.get 来拿到线程的执行结果，实际上是 FutureTask.get ，其方法底层是从 FutureTask 的 outcome 属性拿值的；
2. 《ThreadPoolExecutor 源码解析》中 2 小节中详细说明了 submit 方法最终会把线程的执行结果赋值给 outcome。

结合 1、2，当线程执行完成之后，自然就可以从 FutureTask 的 outcome 属性中拿到值。



### 14：总结

如果我们弄清楚 ThreadPoolExecutor 的原理之后，线程池的面试题都很简单，所以建议大家多看看 《ThreadPoolExecutor 源码解析》这小节。

## **39 经验总结：不同场景，如何使用线程池**

### 引导语

ThreadPoolExecutor 初始化时，主要有如下几个参数：

```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
```

大家对这几个参数应该都很熟悉了，虽然参数很少，但实际工作中却有很多门道，大多数的问题主要集中在线程大小的设置，队列大小的设置两方面上，接下来我们一起看看工作中，如何初始化 ThreadPoolExecutor。



### 1 coreSize == maxSize

我相信很多人都看过，或自己写过这样的代码：

```java
ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 600000L, TimeUnit.DAYS,
                                                     new LinkedBlockingQueue());
```

这行代码主要展示了在初始化 ThreadPoolExecutor 的时候，coreSize 和 maxSize 是相等的，这样设置的话，随着请求的不断增加，会是这样的现象：

1. 请求数 < coreSize 时，新增线程；
2. 请求数 >= coreSize && 队列不满时，添加任务入队；
3. 队列满时，此时因为 coreSize 和 maxSize 相等，任务会被直接拒绝。

这么写的最大目的：是想让线程一下子增加到 maxSize，并且不要回收线程，防止线程回收，避免不断增加回收的损耗，一般来说业务流量都有波峰低谷，在流量低谷时，线程不会被回收；流量波峰时，maxSize 的线程可以应对波峰，不需要慢慢初始化到 maxSize 的过程。

这样设置有两个前提条件：

1. allowCoreThreadTimeOut 我们采取默认 false，而不会主动设置成 true，allowCoreThreadTimeOut 是 false 的话，当线程空闲时，就不会回收核心线程；
2. keepAliveTime 和 TimeUnit 我们都会设置很大，这样线程空闲的时间就很长，线程就不会轻易的被回收。

我们现在机器的资源都是很充足的，我们不用去担心线程空闲会浪费机器的资源，所以这种写法目前是很常见的。



### 2 maxSize 无界 + SynchronousQueue

在线程池选择队列时，我们也会看到有同学选择 SynchronousQueue，SynchronousQueue 我们在 《SynchronousQueue 源码解析》章节有说过，其内部有堆栈和队列两种形式，默认是堆栈的形式，其内部是没有存储的容器的，放元素和拿元素是一一对应的，比如我使用 put 方法放元素，如果此时没有对应的 take 操作的话，put 操作就会阻塞，需要有线程过来执行 take 操作后，put 操作才会返回。

基于此特点，如果要使用 SynchronousQueue 的话，我们需要尽量将 maxSize 设置大一点，这样就可以接受更多的请求。

假设我们设置 maxSize 是 10 的话，选择 SynchronousQueue 队列，假设所有请求都执行 put 操作，没有请求执行 take 操作，前 10 个 put 请求会消耗 10 个线程，都阻塞在 put 操作上，第 11 个请求过来后，请求就会被拒绝，所以我们才说尽量把 maxSize 设置大一点，防止请求被拒绝。

maxSize 无界 + SynchronousQueue 这样的组合方式优缺点都很明显：

优点：当任务被消费时，才会返回，这样请求就能够知道当前请求是已经在被消费了，如果是其他的队列的话，我们只知道任务已经被提交成功了，但无法知道当前任务是在被消费中，还是正在队列中堆积。

缺点：

1. 比较消耗资源，大量请求到来时，我们会新建大量的线程来处理请求；
2. 如果请求的量难以预估的话，maxSize 的大小也很难设置。



### 3 maxSize 有界 + Queue 无界

在一些对实时性要求不大，但流量忽高忽低的场景下，可以使用 maxSize 有界 + Queue 无界的组合方式。

比如我们设置 maxSize 为 20，Queue 选择默认构造器的 LinkedBlockingQueue，这样做的优缺点如下：

优点：

1. 电脑 cpu 固定的情况下，每秒能同时工作的线程数是有限的，此时开很多的线程其实也是浪费，还不如把这些请求放到队列中去等待，这样可以减少线程之间的 CPU 的竞争；
2. LinkedBlockingQueue 默认构造器构造出来的链表的最大容量是 Integer 的最大值，非常适合流量忽高忽低的场景，当流量高峰时，大量的请求被阻塞在队列中，让有限的线程可以慢慢消费。

缺点：流量高峰时，大量的请求被阻塞在队列中，对于请求的实时性难以保证，所以当对请求的实时性要求较高的场景，不能使用该组合。



### 4 maxSize 有界 + Queue 有界

这种组合是对 3 缺点的补充，我们把队列从无界修改成有界，只要排队的任务在要求的时间内，能够完成任务即可。

这种组合需要我们把线程和队列的大小进行配合计算，保证大多数请求都可以在要求的时间内，有响应返回。



### 5 keepAliveTime 设置无穷大

有些场景下我们不想让空闲的线程被回收，于是就把 keepAliveTime 设置成 0，实际上这种设置是错误的，当我们把 keepAliveTime 设置成 0 时，线程使用 poll 方法在队列上进行超时阻塞时，会立马返回 null，也就是空闲线程会立马被回收。

所以如果我们想要空闲的线程不被回收，我们可以设置 keepAliveTime 为无穷大值，并且设置 TimeUnit 为时间的大单位，比如我们设置 keepAliveTime 为 365，TimeUnit 为 TimeUnit.DAYS，意思是线程空闲 1 年内都不会被回收。

在实际的工作中，机器的内存一般都够大，我们合理设置 maxSize 后，即使线程空闲，我们也不希望线程被回收，我们常常也会设置 keepAliveTime 为无穷大。



### 6 线程池的公用和独立

在实际工作中，某一个业务下的所有场景，我们都不会公用一个线程池，一般有以下几个原则：

1. 查询和写入不公用线程池，互联网应用一般来说，查询量远远大于写入的量，如果查询和写入都要走线程池的话，我们一定不要公用线程池，也就是说查询走查询的线程池，写入走写入的线程池，如果公用的话，当查询量很大时，写入的请求可能会到队列中去排队，无法及时被处理；
2. 多个写入业务场景看情况是否需要公用线程池，原则上来说，每个业务场景都独自使用自己的线程池，绝不共用，这样在业务治理、限流、熔断方面都比较容易，一旦多个业务场景公用线程池，可能就会造成业务场景之间的互相影响，现在的机器内存都很大，每个写入业务场景独立使用自己的线程池也是比较合理的；
3. 多个查询业务场景是可以公用线程池的，查询的请求一般来说有几个特点：查询的场景多、rt 时间短、查询的量比较大，如果给每个查询场景都弄一个单独的线程池的话，第一个比较耗资源，第二个很难定义线程池中线程和队列的大小，比较复杂，所以多个相似的查询业务场景是可以公用线程池的。



### 7 如何算线程大小和队列大小

在实际的工作中，我们使用线程池时，需要慎重考虑线程的大小和队列的大小，主要从几个方面入手：

1. 根据业务进行考虑，初始化线程池时，我们需要考虑所有业务涉及的线程池，如果目前所有的业务同时都有很大流量，那么在对于当前业务设置线程池时，我们尽量把线程大小、队列大小都设置小，如果所有业务基本上都不会同时有流量，那么就可以稍微设置大一点；
2. 根据业务的实时性要求，如果实时性要求高的话，我们把队列设置小一点，coreSize == maxSize，并且设置 maxSize 大一点，如果实时性要求低的话，就可以把队列设置大一点。

假设现在机器上某一时间段只会运行一种业务，业务的实时性要求较高，每个请求的平均 rt 是 200ms，请求超时时间是 2000ms，机器是 4 核 CPU，内存 16G，一台机器的 qps 是 100，这时候我们可以模拟一下如何设置：

1. 4 核 CPU，假设 CPU 能够跑满，每个请求的 rt 是 200ms，就是 200 ms 能执行 4 条请求，2000ms 内能执行 2000/200 * 4 = 40 条请求；
2. 200 ms 能执行 4 条请求，实际上 4 核 CPU 的性能远远高于这个，我们可以拍脑袋加 10 条，也就是说 2000ms 内预估能够执行 50 条；
3. 一台机器的 qps 是 100，此时我们计算一台机器 2 秒内最多处理 50 条请求，所以此时如果不进行 rt 优化的话，我们需要加至少一台机器。

线程池可以大概这么设置：

```java
ThreadPoolExecutor executor = new ThreadPoolExecutor(15, 15, 365L, TimeUnit.DAYS,
                                                     new LinkedBlockingQueue(35));
```

线程数最大为 15，队列最大为 35，这样机器差不多可以在 2000ms 内处理最大的请求 50 条，当然根据你机器的性能和实时性要求，你可以调整线程数和队列的大小占比，只要总和小于 50 即可。

以上只是很粗糙的设置，在实际的工作中，还需要根据实际情况不断的观察和调整。



### 总结

线程池设置非常重要，我们尽量少用 Executors 类提供的各种初始化线程池的方法，多根据业务的量，实时性要求来计算机器的预估承载能力，设置预估的线程和队列大小，并且根据实时请求不断的调整线程池的大小值。

## **40 打动面试官：线程池流程编排中的运用实战**

### 引导语

在线程池的面试中，面试官除了喜欢问 ThreadPoolExecutor 的底层源码外，还喜欢问你有没有在实际的工作中用过 ThreadPoolExecutor，我们在并发集合类的《场景集合：并发 List、Map 的应用场景》一文中说过一种简单的流程引擎，如果没有看过的同学，可以返回去看一下。

本章就在流程引擎的基础上运用 ThreadPoolExecutor，使用线程池实现 SpringBean 的异步执行。



### 1 流程引擎关键代码回顾

《场景集合：并发 List、Map 的应用场景》文中流程引擎执行 SpringBean 的核心代码为：

```java
  // 批量执行 Spring Bean
  private void stageInvoke(String flowName, StageEnum stage, FlowContent content) {
    List<DomainAbilityBean>
        domainAbilitys =
        FlowCenter.flowMap.getOrDefault(flowName, Maps.newHashMap()).get(stage);
    if (CollectionUtils.isEmpty(domainAbilitys)) {
      throw new RuntimeException("找不到该流程对应的领域行为" + flowName);
    }
    for (DomainAbilityBean domainAbility : domainAbilitys) {
      // 执行 Spring Bean
      domainAbility.invoke(content);
    }
  }
```

入参是 flowName（流程名称）、stage（阶段）、content（上下文），其中 stage 中会执行很多 SpringBean，SpringBean 被执行的代码是 domainAbility.invoke(content)。



### 2 异步执行 SpringBean

从上述代码中，我们可以看到所有的 SpringBean 都是串行执行的，效率较低，我们在实际业务中发现，有的 SpringBean 完全可以异步执行，这样既能完成业务请求，又能减少业务处理的 rt，对于这个需求，我们条件反射的有了两个想法：

1. 需要新开线程来异步执行 SpringBean，可以使用 Runable 或者 Callable；
2. 业务请求量很大，我们不能每次来一个请求，就开一个线程，我们应该让线程池来管理异步执行的线程。

于是我们决定使用线程池来完成这个需求。



### 3 如何区分异步的 SpringBean

我们的 SpringBean 都是实现 DomainAbilityBean 这个接口的，接口定义如下：

```java
public interface DomainAbilityBean {

  /**
   * 领域行为的方法入口
   */
  FlowContent invoke(FlowContent content);
}
```

从接口定义上来看，没有预留任何地方来标识该 SpringBean 应该是同步执行还是异步执行，这时候我们可以采取注解的方式，我们新建一个注解，只要 SpringBean 上有该注解，表示该 SpringBean 应该异步执行，否则应该同步执行，新建的注解如下：

```java
/**
* 异步 SpringBean 执行注解
 * SpringBean 需要异步执行的话，就打上该注解
*author  wenhe
*date 2019/10/7
*/
@Target(ElementType.TYPE)// 表示该注解应该打在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AsyncComponent {

}
```

接着我们新建了两个 SpringBean，并在其中一个 SpringBean 上打上异步的注解，并且打印出执行 SpringBean 的线程名称，如下图：
![图片描述](http://img.mukewang.com/5dd5fd640001cd7618201354.png)

图中实现了两个 SpringBean：BeanOne 和 BeanTwo，其中 BeanTwo 被打上了 AsyncComponent 注解，表明 BeanTwo 应该被异步执行，两个 SpringBean 都打印出执行的线程的名称。



### 4 mock 流程引擎数据中心

《场景集合：并发 List、Map 的应用场景》一文中，我们说可以从数据库中加载出流程引擎需要的数据，此时我们 mock 一下，mock 的代码如下：

```java
@Component
public class FlowCenter {

  /**
   * flowMap 是共享变量，方便访问
   */
  public static final Map<String, Map<StageEnum, List<DomainAbilityBean>>> flowMap
      = Maps.newConcurrentMap();

  /**
   * PostConstruct 注解的意思就是
   * 在容器启动成功之后，初始化 flowMap
   */
  @PostConstruct
  public void init() {
      // 初始化 flowMap mock
    Map<StageEnum, List<DomainAbilityBean>> stageMap = flowMap.getOrDefault("flow1",Maps.newConcurrentMap());
    for (StageEnum value : StageEnum.values()) {
      List<DomainAbilityBean> domainAbilitys = stageMap.getOrDefault(value, Lists.newCopyOnWriteArrayList());
      if(CollectionUtils.isEmpty(domainAbilitys)){
        domainAbilitys.addAll(ImmutableList.of(
            ApplicationContextHelper.getBean(BeanOne.class),
            ApplicationContextHelper.getBean(BeanTwo.class)
        ));
        stageMap.put(value,domainAbilitys);
      }
    }
    flowMap.put("flow1",stageMap);
    // 打印出加载完成之后的数据结果
    log.info("init success,flowMap is {}", JSON.toJSONString(flowMap));
  }
}
```



### 5 新建线程池

在以上操作完成之后，只剩下最后一步了，之前我们执行 SpringBean 时，是这行代码：domainAbility.invoke(content);

现在我们需要区分 SpringBean 是否是异步的，如果是异步的，丢到线程池中去执行，如果是同步的，仍然使用原来的方法进行执行，于是我们把这些逻辑封装到一个工具类中，工具类如下：

```java
/**
 * 组件执行器
 * author  wenhe
 * date 2019/10/7
 */
public class ComponentExecutor {
	// 我们新建了一个线程池
  private static ExecutorService executor = new ThreadPoolExecutor(15, 15,
                                                                   365L, TimeUnit.DAYS,
                                                                   new LinkedBlockingQueue<>());
	// 如果 SpringBean 上有 AsyncComponent 注解，表示该 SpringBean 需要异步执行，就丢到线程池中去
  public static final void run(DomainAbilityBean component, FlowContent content) {
    // 判断类上是否有 AsyncComponent 注解
    if (AnnotationUtils.isAnnotationPresent(AsyncComponent.class, AopUtils.getTargetClass(component))) {
      // 提交到线程池中
      executor.submit(() -> { component.invoke(content); });
      return;
    }
    // 同步 SpringBean 直接执行。
    component.invoke(content);
  }
}
```

我们把原来的执行代码替换成使用组件执行器执行，如下图：
![图片描述](http://img.mukewang.com/5dd5fd4a0001878c16481102.png)



### 6 测试

以上步骤完成之后，简单的流程引擎就已经完成了，我们简单地在项目启动的时候加上测试，代码如下：
![图片描述](http://img.mukewang.com/5dd5fd3d0001c5c416681156.png)

更严谨的做法，是会写单元测试来测试流程引擎，为了快一点，我们直接在项目启动类上加上了测试代码。

运行之后的关键结果如下：

```java
[main] demo.sixth.SynchronizedDemo: SynchronizedDemo init begin
[main] demo.sixth.SynchronizedDemo: SynchronizedDemo init end
[main] demo.three.flow.FlowCenter : init success,flowMap is {"flow1":{"PARAM_VALID":[{},{}],"AFTER_TRANSACTION":[{"$ref":"$.flow1.PARAM_VALID[0]"},{"$ref":"$.flow1.PARAM_VALID[1]"}],"BUSINESS_VALID":[{"$ref":"$.flow1.PARAM_VALID[0]"},{"$ref":"$.flow1.PARAM_VALID[1]"}],"IN_TRANSACTION":[{"$ref":"$.flow1.PARAM_VALID[0]"},{"$ref":"$.flow1.PARAM_VALID[1]"}]}}
o.s.j.e.a.AnnotationMBeanExporter  : Registering beans for JMX exposure on startup
[main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
[main] demo.DemoApplication : Started DemoApplication in 5.377 seconds (JVM running for 6.105)
[main] demo.three.flow.BeanOne : BeanOne is run,thread name is main
[main] demo.three.flow.BeanOne : BeanOne is run,thread name is main
[pool-1-thread-1] demo.three.flow.BeanTwo : BeanTwo is run,thread name is pool-1-thread-1
[main] demo.three.flow.BeanOne : BeanOne is run,thread name is main
[pool-1-thread-2] demo.three.flow.BeanTwo : BeanTwo is run,thread name is pool-1-thread-2
[pool-1-thread-3] demo.three.flow.BeanTwo : BeanTwo is run,thread name is pool-1-thread-3
[main] demo.three.flow.BeanOne : BeanOne is run,thread name is main
[pool-1-thread-4] demo.three.flow.BeanTwo : BeanTwo is run,thread name is pool-1-thread-4
```

从运行结果中，我们可以看到 BeanTwo 已经被多个不同的线程异步执行了。



### 总结

这是一个线程池在简单流程引擎上的运用实站，虽然这个流程引擎看起来比较简单，但在实际工作中，还是非常好用的，大家可以把代码拉下来，自己尝试一下，调试一下参数，比如当我新增 SpringBean 的时候，流程引擎的表现如何。

# **第8章 Lambda 流**

## **41 突破难点：如何看 Lambda 源码**

### 引导语

大家都知道 Java8 中新增了 Lambda 表达式，使用 Lambda 表达式可以对代码进行大量的优化，用几行代码就可以做很多事情，本章以 Lambda 为例，第一小节说明一下其底层的执行原理，第二小节说明一下 Lambda 流在工作中常用的姿势。



### 1 Demo

首先我们来看一个 Lambda 表达式的 Demo，如下图：
![图片描述](http://img.mukewang.com/5dd5fe830001211007440492.png)

代码比较简单，就是新起一个线程打印一句话，但对于图中 () -> System.out.println ( “ lambda is run “ ) 这种代码，估计很多同学都感觉到很困惑，Java 是怎么识别这种代码的？

如果我们修改成匿名内部类的写法，就很清楚，大家都能看懂，如下图：
![图片描述](http://img.mukewang.com/5dd5fe980001358906980558.png)

那是不是说 () -> System.out.println ( “ lambda is run “ ) 这种形式的代码，其实就是建立了内部类呢？其实这就是最简单 Lambda 表达式，我们是无法通过 IDEA 看到源码和其底层结构的，下面我们就来介绍几种可看到其底层实现的方式。



### 2 异常判断法

我们可以在代码执行中主动抛出异常，打印出堆栈，堆栈会说明其运行轨迹，一般这种方法简单高效，基本上可以看到很多情况下的隐藏代码，我们来试一下，如下图：
![图片描述](http://img.mukewang.com/5dd5fea9000187c107670699.png)

从异常的堆栈中，我们可以看到 JVM 自动给当前类建立了内部类（错误堆栈中出现多次的 $ 表示有内部类），内部类的代码在执行过程中，抛出了异常，但这里显示的代码是 Unknown Source，所以我们也无法 debug 进去，一般情况下，异常都能暴露出代码执行的路径，我们可以打好断点后再次运行，但对于 Lambda 表达式而言，通过异常判断法我们只清楚有内部类，但无法看到内部类中的源码。



### 3 javap 命令法

javap 是 Java 自带的可以查看 class 字节码文件的工具，安装过 Java 基础环境的电脑都可以直接执行 javap 命令，如下图：
![图片描述](http://img.mukewang.com/5dd5fec0000181d909200570.png)

命令选项中，我们主要是用-v -verbose 这个命令，可以完整输出字节码文件的内容。

接下来我们使用 javap 命令查看下 Lambda.class 文件，在讲解的过程中，我们会带上一些关于 class 文件的知识。

我们在命令窗口中找到 Lambda.class 所在的位置，执行命令：javap -verbose Lambda.class，然后你会看到一长串的东西，这些叫做汇编指令，接下来我们来一一讲解下（ 所有的参考资料来自 [Java 虚拟机规范](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4)，不再一一引用说明）：

汇编指令中我们很容易找到 Constant pool 打头的一长串类型，我们叫做常量池，官方英文叫做 Run-Time Constant Pool，我们简单理解成一个装满常量的 table ，table 中包含编译时明确的数字和文字，类、方法和字段的类型信息等等。table 中的每个元素叫做 cp*info，cp*info 由唯一标识 ( tag ) + 名称组成，目前 tag 的类型一共有：
![图片描述](http://img.mukewang.com/5dd5fedc00012eb605020734.png)

贴出我们解析出来的部分图：
![图片描述](http://img.mukewang.com/5dd5fee90001bf8118760941.png)

1. 图中 Constant pool 字样代表当前信息是常量池；
2. 每行都是一个 `cp_info` ，第一列的 #1 代表是在常量池下标为 1 的位置 ；
3. 每行的第二列，是 `cp_info` 的唯一标识 ( tag ) ，比如 Methodref 对应着上表中的 CONSTANT_Methodref（上上图中表格中 value 对应 10 的 tag），代表当前行是表示方法的描述信息的，比如说方法的名称，入参类型，出参数类型等，具体的含义在 Java 虚拟机规范中都可以查询到，Methodref 的截图如下：
   ![图片描述](http://img.mukewang.com/5dd5ff070001ff6219441450.png)
4. 每行的第三列，如果是具体的值的话，直接显示具体的值，如果是复杂的值的话，会显示 `cp_info` 的引用，比如说图中标红 2 处，引用两个 13 和 14 位置的 `cp_info`，13 表示方法名字是 init，14 表示方法无返回值，结合起来表示方法的名称和返回类型，就是一个无参构造器；
5. 每行的第四列，就是具体的值了。

对于比较重要的 cp_info 类型我们说明下其含义：

1. InvokeDynamic 表示动态的调用方法，后面我们会详细说明；
2. Fieldref 表示字段的描述信息，如字段的名称、类型；
3. NameAndType 是对字段和方法类型的描述；
4. MethodHandle 方法句柄，动态调用方法的统称，在编译时我们不知道具体是那个方法，但运行时肯定会知道调用的是那个方法；
5. MethodType 动态方法类型，只有在动态运行时才会知道其方法类型是什么。

我们从上上图中标红的 3 处，发现 Ljava/lang/invoke/MethodHandles$Lookup，java/lang/invoke/LambdaMetafactory.metafactory 类似这样的代码，MethodHandles 和 LambdaMetafactory 都是 java.lang.invoke 包下面的重要方法，invoke 包主要实现了动态语言的功能，我们知道 java 语言属于静态编译语言，在编译的时候，类、方法、字段等等的类型都已经确定了，而 invoke 实现的是一种动态语言，也就是说编译的时候并不知道类、方法、字段是什么类型，只有到运行的时候才知道。

比如这行代码：Runnable runnable = () -> System.out.println(“lambda is run”); 在编译器编译的时候 () 这个括号编译器并不知道是干什么的，只有在运行的时候，才会知道原来这代表着的是 Runnable.run() 方法。invoke 包里面很多类，都是为了代表这些 () 的，我们称作为方法句柄（ MethodHandler ），在编译的时候，编译器只知道这里是个方法句柄，并不知道实际上执行什么方法，只有在执行的时候才知道，那么问题来了，JVM 执行的时候，是如何知道 () 这个方法句柄，实际上是执行 Runnable.run() 方法的呢？

首先我们看下 simple 方法的汇编指令：
![图片描述](http://img.mukewang.com/5dd5ff170001644407650528.png)

从上图中就可以看出 simple 方法中的 () -> System.out.println(“lambda is run”) 代码中的 ()，实际上就是 Runnable.run 方法。

我们追溯到 # 2 常量池，也就是上上图中标红 1 处，InvokeDynamic 表示这里是个动态调用，调用的是两个常量池的 cp_info，位置是 #0:#37 ，我们往下找 #37 代表着是 // run:()Ljava/lang/Runnable，这里表明了在 JVM 真正执行的时候，需要动态调用 Runnable.run() 方法，从汇编指令上我们可以看出 () 实际上就是 Runnable.run()，下面我们 debug 来证明一下。

我们在上上图中 3 处发现了 LambdaMetafactory.metafactory 的字样，通过查询官方文档，得知该方法正是执行时， 链接到真正代码的关键，于是我们在 metafactory 方法中打个断点 debug 一下，如下图：
![图片描述](http://img.mukewang.com/5dd5ff2d0001ae9417980859.png)

metafactory 方法入参 caller 代表实际发生动态调用的位置，invokedName 表示调用方法名称，invokedType 表示调用的多个入参和出参，samMethodType 表示具体的实现者的参数，implMethod 表示实际上的实现者，instantiatedMethodType 等同于 implMethod。

以上内容总结一下：

1：从汇编指令的 simple 方法中，我们可以看到会执行 Runnable.run 方法；

2：在实际的运行时，JVM 碰到 simple 方法的 invokedynamic 指令，会动态调用 LambdaMetafactory.metafactory 方法，执行具体的 Runnable.run 方法。

所以可以把 Lambda 表达值的具体执行归功于 invokedynamic JVM 指令，正是因为这个指令，才可以做到虽然编译时不知道要干啥，但动态运行时却能找到具体要执行的代码。

接着我们看一下在汇编指令输出的最后，我们发现了异常判断法中发现的内部类，如下图：
![图片描述](http://img.mukewang.com/5dd5ff450001783a13491048.png)

上图中箭头很多，一层一层的表达清楚了当前内部类的所有信息。



### 4 总结

我们总结一下，Lambda 表达式执行主要是依靠 invokedynamic 的 JVM 指令来实现，咱们演示的类的全路径为：demo.eight.Lambda 感兴趣的同学可以自己尝试一下。

## **42 常用的 Lambda 表达式使用场景解析和应用**

### 引导语

我们日常工作中，Lambda 使用比较多的场景，就是 List 或 Map 下的 Lambda 流操作，往往几行代码可以帮助我们实现多层 for 循环嵌套的复杂代码，接下来我们把 Lambda 流的常用方法用案列讲解一下。



### 1 数据准备

本文演示的所有代码都在 demo.eight.LambdaExpressionDemo 中，首先我们需要准备一些测试的数据，如下：

```java
@Data
// 学生数据结构
class StudentDTO implements Serializable {

  private static final long serialVersionUID = -7716352032236707189L;

  public StudentDTO() {
  }

  public StudentDTO(Long id, String code, String name, String sex, Double scope,
                    List<Course> learningCources) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.sex = sex;
    this.scope = scope;
    this.learningCources = learningCources;
  }

  /**
   * id
   */
  private Long id;
  /**
   * 学号 唯一标识
   */
  private String code;
  /**
   * 学生名字
   */
  private String name;

  /**
   * 性别
   */
  private String sex;

  /**
   * 分数
   */
  private Double scope;

  /**
   * 要学习的课程
   */
  private List<Course> learningCources;
}

@Data
// 课程数据结构
class Course implements Serializable {

  private static final long serialVersionUID = 2896201730223729591L;

  /**
   * 课程 ID
   */
  private Long id;

  /**
   * 课程 name
   */
  private String name;

  public Course(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
// 初始化数据
private final List<StudentDTO> students = new ArrayList<StudentDTO>(){
  {
    // 添加学生数据
    add(new StudentDTO(1L,"W199","小美","WM",100D,new ArrayList<Course>(){
      {
        // 添加学生学习的课程
        add(new Course(300L,"语文"));
        add(new Course(301L,"数学"));
        add(new Course(302L,"英语"));
      }
    }));
    add(new StudentDTO(2L,"W25","小美","WM",100D,Lists.newArrayList()));
    add(new StudentDTO(3L,"W3","小名","M",90D,new ArrayList<Course>(){
      {
        add(new Course(300L,"语文"));
        add(new Course(304L,"体育"));
      }
    }));
    add(new StudentDTO(4L,"W1","小蓝","M",10D,new ArrayList<Course>(){
      {
        add(new Course(301L,"数学"));
        add(new Course(305L,"美术"));
      }
    }));
  }
};
```

请大家稍微看下数据结构，不然看下面案例跑出来的结果会有些吃力。



### 2 常用方法



#### 2.1 Filter

Filter 为过滤的意思，只要满足 Filter 表达式的数据就可以留下来，不满足的数据被过滤掉，源码如下图：
![图片描述](http://img.mukewang.com/5dd6009100012e5d20500742.png)

我们写了一个 demo，如下：

```java
public void testFilter() {
  // list 在下图中进行了初始化
  List<String> newList = list.stream()
      // 过滤掉我们希望留下来的值
      // StringUtils.equals(str,"hello") 表示我们希望字符串是 hello 能留下来
      // 其他的过滤掉
      .filter(str -> StringUtils.equals(str, "hello"))
      // Collectors.toList() 帮助我们构造最后的返回结果
      .collect(Collectors.toList());
  log.info("TestFilter result is {}", JSON.toJSONString(newList));
}
```

运行结果如下：
![图片描述](http://img.mukewang.com/5dd6007d0001804022520840.png)



#### 2.2 map

map 方法可以让我们进行一些流的转化，比如原来流中的元素是 A，通过 map 操作，可以使返回的流中的元素是 B，源码如下图：
![图片描述](http://img.mukewang.com/5dd6006b00015baf20920956.png)

我们写了一个 demo，如下：

```java
public void testMap() {
  // 得到所有学生的学号
  // 这里 students.stream() 中的元素是 StudentDTO，通过 map 方法转化成 String 的流
  List<String> codes = students.stream()
      //StudentDTO::getCode 是 s->s.getCode() 的简写
      .map(StudentDTO::getCode)
      .collect(Collectors.toList());
  log.info("TestMap 所有学生的学号为 {}", JSON.toJSONString(codes));
}
// 运行结果为：TestMap 所有学生的学号为 ["W199","W25","W3","W1"]
```



#### 2.3 mapToInt

mapToInt 方法的功能和 map 方法一样，只不过 mapToInt 返回的结果已经没有泛型，已经明确是 int 类型的流了，源码如下：
![图片描述](http://img.mukewang.com/5dd600580001e53d19820916.png)

我们写了一个 demo，如下：

```java
public void testMapToInt() {
  List<Integer> ids = students.stream()
      .mapToInt(s->Integer.valueOf(s.getId()+""))
      // 一定要有 mapToObj，因为 mapToInt 返回的是 IntStream，因为已经确定是 int 类型了
      // 所有没有泛型的，而 Collectors.toList() 强制要求有泛型的流，所以需要使用 mapToObj
      // 方法返回有泛型的流
      .mapToObj(s->s)
      .collect(Collectors.toList());
  log.info("TestMapToInt result is {}", JSON.toJSONString(ids));

  // 计算学生总分
  Double sumScope = students.stream()
      .mapToDouble(s->s.getScope())
      // DoubleStream/IntStream 有许多 sum（求和）、min（求最小值）、max（求最大值）、average（求平均值）等方法
      .sum();
  log.info("TestMapToInt 学生总分为： is {}", sumScope);
}
```

运行结果如下：

TestMapToInt result is [1,2,3,4]
TestMapToInt 学生总分为： is 300.0



#### 2.4 flatMap

flatMap 方法也是可以做一些流的转化，和 map 方法不同的是，其明确了 Function 函数的返回值的泛型是流，源码如下：
![图片描述](http://img.mukewang.com/5dd6002d0001f35415580441.png)

写了一个 demo，如下：

```java
public void testFlatMap(){
  // 计算学生所有的学习课程，flatMap 返回 List<课程> 格式
  List<Course> courses = students.stream().flatMap(s->s.getLearningCources().stream())
      .collect(Collectors.toList());
  log.info("TestMapToInt flatMap 计算学生的所有学习课程如下 {}", JSON.toJSONString(courses));

  // 计算学生所有的学习课程，map 返回两层课程嵌套格式
  List<List<Course>> courses2 = students.stream().map(s->s.getLearningCources())
      .collect(Collectors.toList());
  log.info("TestMapToInt map 计算学生的所有学习课程如下 {}", JSON.toJSONString(courses2));

  List<Stream<Course>> courses3 = students.stream().map(s->s.getLearningCources().stream())
      .collect(Collectors.toList());
  log.info("TestMapToInt map 计算学生的所有学习课程如下  {}", JSON.toJSONString(courses3));
}
```

运行结果如下：
![图片描述](http://img.mukewang.com/5dd6000a00011d6317510627.png)



#### 2.5 distinct

distinct 方法有去重的功能，我们写了一个 demo，如下：

```java
public void testDistinct(){
  // 得到学生所有的名字，要求是去重过的
  List<String> beforeNames = students.stream().map(StudentDTO::getName).collect(Collectors.toList());
  log.info("TestDistinct 没有去重前的学生名单 {}",JSON.toJSONString(beforeNames));

  List<String> distinctNames = beforeNames.stream().distinct().collect(Collectors.toList());
  log.info("TestDistinct 去重后的学生名单 {}",JSON.toJSONString(distinctNames));

  // 连起来写
  List<String> names = students.stream()
      .map(StudentDTO::getName)
      .distinct()
      .collect(Collectors.toList());
  log.info("TestDistinct 去重后的学生名单 {}",JSON.toJSONString(names));
}
```

运行结果如下：
![图片描述](http://img.mukewang.com/5dd5fff000012e9a14840706.png)



#### 2.6 Sorted

Sorted 方法提供了排序的功能，并且允许我们自定义排序，demo 如下：

```java
public void testSorted(){
  // 学生按照学号排序
  List<String> beforeCodes = students.stream().map(StudentDTO::getCode).collect(Collectors.toList());
  log.info("TestSorted 按照学号排序之前 {}",JSON.toJSONString(beforeCodes));

  List<String> sortedCodes = beforeCodes.stream().sorted().collect(Collectors.toList());
  log.info("TestSorted 按照学号排序之后 is {}",JSON.toJSONString(sortedCodes));

  // 直接连起来写
  List<String> codes = students.stream()
      .map(StudentDTO::getCode)
      // 等同于 .sorted(Comparator.naturalOrder()) 自然排序
      .sorted()
      .collect(Collectors.toList());
  log.info("TestSorted 自然排序 is {}",JSON.toJSONString(codes));

  // 自定义排序器
  List<String> codes2 = students.stream()
      .map(StudentDTO::getCode)
      // 反自然排序
      .sorted(Comparator.reverseOrder())
      .collect(Collectors.toList());
  log.info("TestSorted 反自然排序 is {}",JSON.toJSONString(codes2));
}
```

运行结果如下：
![图片描述](http://img.mukewang.com/5dd5ffd300010f9711840242.png)



#### 2.7 peek

peek 方法很简单，我们在 peek 方法里面做任意没有返回值的事情，比如打印日志，如下：

```java
students.stream().map(StudentDTO::getCode)
    .peek(s -> log.info("当前循环的学号是{}",s))
    .collect(Collectors.toList());
```



#### 2.8 limit

limit 方法会限制输出值个数，入参是限制的个数大小，demo 如下：

```java
public void testLimit(){
  List<String> beforeCodes = students.stream().map(StudentDTO::getCode).collect(Collectors.toList());
  log.info("TestLimit 限制之前学生的学号为 {}",JSON.toJSONString(beforeCodes));

  List<String> limitCodes = beforeCodes.stream()
      .limit(2L)
      .collect(Collectors.toList());
  log.info("TestLimit 限制最大限制 2 个学生的学号 {}",JSON.toJSONString(limitCodes));

  // 直接连起来写
  List<String> codes = students.stream()
      .map(StudentDTO::getCode)
      .limit(2L)
      .collect(Collectors.toList());
  log.info("TestLimit 限制最大限制 2 个学生的学号 {}",JSON.toJSONString(codes));
}
```

输出结果如下：
![图片描述](http://img.mukewang.com/5dd5ffc00001238213120222.png)



#### 2.9 reduce

reduce 方法允许我们在循环里面叠加计算值，我们写了 demo 如下：

```java
public void testReduce(){
  // 计算一下学生的总分数
  Double sum = students.stream()
      .map(StudentDTO::getScope)
      // scope1 和 scope2 表示循环中的前后两个数
      .reduce((scope1,scope2) -> scope1+scope2)
      .orElse(0D);
  log.info("总成绩为 {}",sum);

  Double sum1 = students.stream()
      .map(StudentDTO::getScope)
      // 第一个参数表示成绩的基数，会从 100 开始加
      .reduce(100D,(scope1,scope2) -> scope1+scope2);
  log.info("总成绩为 {}",sum1);
}
```

运行结果如下：
![图片描述](http://img.mukewang.com/5dd5ffaa00012ffc10480667.png)

第二个计算出来的总成绩多了 100，是因为第二个例子中 reduce 是从基数 100 开始累加的。



#### 2.10 findFirst

findFirst 表示匹配到第一个满足条件的值就返回，demo 如下：

```java
// 找到第一个叫小美同学的 ID
@Test
public void testFindFirst(){
  Long id = students.stream()
      .filter(s->StringUtils.equals(s.getName(),"小美"))
       // 同学中有两个叫小美的，这里匹配到第一个就返回
      .findFirst()
      .get().getId();
  
  log.info("testFindFirst 小美同学的 ID {}",id);

  // 防止空指针
  Long id2 = students.stream()
      .filter(s->StringUtils.equals(s.getName(),"小天"))
      .findFirst()
      // orElse 表示如果 findFirst 返回 null 的话，就返回 orElse 里的内容
      .orElse(new StudentDTO()).getId();
  log.info("testFindFirst 小天同学的 ID {}",id2);

  Optional<StudentDTO> student= students.stream()
      .filter(s->StringUtils.equals(s.getName(),"小天"))
      .findFirst();
  // isPresent 为 true 的话，表示 value != null，即 student.get() != null
  if(student.isPresent()){
    log.info("testFindFirst 小天同学的 ID {}",student.get().getId());
    return;
  }
  log.info("testFindFirst 找不到名为小天的同学");
}
```

运行结果如下：
![图片描述](http://img.mukewang.com/5dd5ff9300014ba507750842.png)



#### 2.11 groupingBy && toMap

groupingBy 是能够根据字段进行分组，toMap 是把 List 的数据格式转化成 Map 的格式，我们写了一个 demo，如下：

```java
@Test
public void testListToMap(){
  // 学生根据名字进行分类
  Map<String, List<StudentDTO>> map1 = students.stream()
      .collect(Collectors.groupingBy(StudentDTO::getName));
  log.info("testListToMap groupingBy 学生根据名字进行分类 result is Map<String,List<StudentDTO>> {}",
           JSON.toJSONString(map1));

  // 统计姓名重名的学生有哪些
  Map<String, Set<String>> map2 = students.stream()
      .collect(Collectors.groupingBy(StudentDTO::getName,
                                  Collectors.mapping(StudentDTO::getCode,Collectors.toSet())));
  log.info("testListToMap groupingBy 统计姓名重名结果 is {}",
           JSON.toJSONString(map2));

  // 学生转化成学号为 key 的 map
  Map<String, StudentDTO> map3 = students.stream()
       //第一个入参表示 map 中 key 的取值
       //第二个入参表示 map 中 value 的取值
       //第三个入参表示，如果前后的 key 是相同的，是覆盖还是不覆盖，(s1,s2)->s1 表示不覆盖，(s1,s2)->s2 表示覆盖
      .collect(Collectors.toMap(s->s.getCode(),s->s,(s1,s2)->s1));
  log.info("testListToMap groupingBy 学生转化成学号为 key 的 map result is{}",
           JSON.toJSONString(map3));

}
```

运行结果如下：
![图片描述](http://img.mukewang.com/5dd5ff7900011ff615250743.png)



### 3 总结

本文我们介绍了 12 种 Lambda 表达式常用的方法，大家可以找到 LambdaExpressionDemo 类，自己 debug 下，这样你在工作中遇到复杂数据结构转化时，肯定会得心应手了。



# **第9章 其他**

## **43 ThreadLocal 源码解析**

### 引导语

ThreadLocal 提供了一种方式，让在多线程环境下，每个线程都可以拥有自己独特的数据，并且可以在整个线程执行过程中，从上而下的传递。



### 1 用法演示

可能很多同学没有使用过 ThreadLocal，我们先来演示下 ThreadLocal 的用法，demo 如下：

```java
/**
 * ThreadLocal 中保存的数据是 Map
 */
static final ThreadLocal<Map<String, String>> context = new ThreadLocal<>();

@Test
public void testThread() {
  // 从上下文中拿出 Map
  Map<String, String> contextMap = context.get();
  if (CollectionUtils.isEmpty(contextMap)) {
    contextMap = Maps.newHashMap();
  }

  contextMap.put("key1", "value1");
  context.set(contextMap);
  log.info("key1，value1被放到上下文中");
	// 从上下文中拿出刚才放进去的数据
  getFromComtext();
}

private String getFromComtext() {
  String value1 = context.get().get("key1");
  log.info("从 ThreadLocal 中取出上下文，key1 对应的值为：{}", value1);
  return value1;
}
//运行结果:
demo.ninth.ThreadLocalDemo - key1，value1被放到上下文中
demo.ninth.ThreadLocalDemo - 从 ThreadLocal 中取出上下文，key1 对应的值为：value1
```

从运行结果中可以看到，key1 对应的值已经从上下文中拿到了。

getFromComtext 方法是没有接受任何入参的，通过 context.get().get(“key1”) 这行代码就从上下文中拿到了 key1 的值，接下来我们一起来看下 ThreadLocal 底层是如何实现上下文的传递的。



### 2 类结构



#### 2.1 类泛型

ThreadLocal 定义类时带有泛型，说明 ThreadLocal 可以储存任意格式的数据，源码如下：

```java
public class ThreadLocal<T> {}
```



#### 2.2 关键属性

ThreadLocal 有几个关键属性，我们一一看下：

```java
// threadLocalHashCode 表示当前 ThreadLocal 的 hashCode，用于计算当前 ThreadLocal 在 ThreadLocalMap 中的索引位置
private final int threadLocalHashCode = nextHashCode();
// 计算 ThreadLocal 的 hashCode 值(就是递增)
private static int nextHashCode() {
    return nextHashCode.getAndAdd(HASH_INCREMENT);
}
// static + AtomicInteger 保证了在一台机器中每个 ThreadLocal 的 threadLocalHashCode 是唯一的
// 被 static 修饰非常关键，因为一个线程在处理业务的过程中，ThreadLocalMap 是会被 set 多个 ThreadLocal 的，多个 ThreadLocal 就依靠 threadLocalHashCode 进行区分
private static AtomicInteger nextHashCode = new AtomicInteger();
```

还有一个重要属性：ThreadLocalMap，当一个线程有多个 ThreadLocal 时，需要一个容器来管理多个 ThreadLocal，ThreadLocalMap 的作用就是这个，管理线程中多个 ThreadLocal。



#### 2.2.1 ThreadLocalMap

ThreadLocalMap 本身就是一个简单的 Map 结构，key 是 ThreadLocal，value 是 ThreadLocal 保存的值，底层是数组的数据结构，源码如下：

```java
static class ThreadLocalMap {
        // 数组中的每个节点值，WeakReference 是弱引用，当没有引用指向时，会直接被回收
        static class Entry extends WeakReference<ThreadLocal<?>> {
            // 当前 ThreadLocal 关联的值
            Object value;
            // WeakReference 的引用 referent 就是 ThreadLocal
            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
        // 数组的初始化大小
        private static final int INITIAL_CAPACITY = 16;
        // 存储 ThreadLocal 的数组
        private Entry[] table;
        // 扩容的阈值，默认是数组大小的三分之二
        private int threshold;
}
```

从源码中看到 ThreadLocalMap 其实就是一个简单的 Map 结构，底层是数组，有初始化大小，也有扩容阈值大小，数组的元素是 Entry，Entry 的 key 就是 ThreadLocal 的引用，value 是 ThreadLocal 的值。



### 3 ThreadLocal 是如何做到线程之间数据隔离的

ThreadLocal 是线程安全的，我们可以放心使用，主要因为是 ThreadLocalMap 是线程的属性，我们看下线程 Thread 的源码，如下：
![图片描述](http://img.mukewang.com/5dd600f00001758909700419.png)

从上图中，我们可以看到 ThreadLocals.ThreadLocalMap 和 InheritableThreadLocals.ThreadLocalMap 分别是线程的属性，所以每个线程的 ThreadLocals 都是隔离独享的。

父线程在创建子线程的情况下，会拷贝 inheritableThreadLocals 的值，但不会拷贝 threadLocals 的值，源码如下：
![图片描述](http://img.mukewang.com/5dd600e30001251418360590.png)

从上图中我们可以看到，在线程创建时，会把父线程的 inheritableThreadLocals 属性值进行拷贝。



### 4 set 方法

set 方法的主要作用是往当前 ThreadLocal 里面 set 值，假如当前 ThreadLocal 的泛型是 Map，那么就是往当前 ThreadLocal 里面 set map，源码如下：

```java
// set 操作每个线程都是串行的，不会有线程安全的问题
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    // 当前 thradLocal 之前有设置值，直接设置，否则初始化
    if (map != null)
        map.set(this, value);
    // 初始化ThreadLocalMap
    else
        createMap(t, value);
}
```

代码逻辑比较清晰，我们在一起来看下 ThreadLocalMap.set 的源码，如下：

```java
private void set(ThreadLocal<?> key, Object value) {
    Entry[] tab = table;
    int len = tab.length;
    // 计算 key 在数组中的下标，其实就是 ThreadLocal 的 hashCode 和数组大小-1取余
    int i = key.threadLocalHashCode & (len-1);

    // 整体策略：查看 i 索引位置有没有值，有值的话，索引位置 + 1，直到找到没有值的位置
    // 这种解决 hash 冲突的策略，也导致了其在 get 时查找策略有所不同，体现在 getEntryAfterMiss 中
    for (Entry e = tab[i];
         e != null;
         // nextIndex 就是让在不超过数组长度的基础上，把数组的索引位置 + 1
         e = tab[i = nextIndex(i, len)]) {
        ThreadLocal<?> k = e.get();
        // 找到内存地址一样的 ThreadLocal，直接替换
        if (k == key) {
            e.value = value;
            return;
        }
        // 当前 key 是 null，说明 ThreadLocal 被清理了，直接替换掉
        if (k == null) {
            replaceStaleEntry(key, value, i);
            return;
        }
    }
    // 当前 i 位置是无值的，可以被当前 thradLocal 使用
    tab[i] = new Entry(key, value);
    int sz = ++size;
    // 当数组大小大于等于扩容阈值(数组大小的三分之二)时，进行扩容
    if (!cleanSomeSlots(i, sz) && sz >= threshold)
        rehash();
}
```

上面源码我们注意几点：

1. 是通过递增的 AtomicInteger 作为 ThreadLocal 的 hashCode 的；
2. 计算数组索引位置的公式是：hashCode 取模数组大小，由于 hashCode 不断自增，所以不同的 hashCode 大概率上会计算到同一个数组的索引位置（但这个不用担心，在实际项目中，ThreadLocal 都很少，基本上不会冲突）；
3. 通过 hashCode 计算的索引位置 i 处如果已经有值了，会从 i 开始，通过 +1 不断的往后寻找，直到找到索引位置为空的地方，把当前 ThreadLocal 作为 key 放进去。

好在日常工作中使用 ThreadLocal 时，常常只使用 1~2 个 ThreadLocal，通过 hash 计算出重复的数组的概率并不是很大。

set 时的解决数组元素位置冲突的策略，也对 get 方法产生了影响，接着我们一起来看一下 get 方法。



### 5 get 方法

get 方法主要是从 ThreadLocalMap 中拿到当前 ThreadLocal 储存的值，源码如下：

```java
public T get() {
    // 因为 threadLocal 属于线程的属性，所以需要先把当前线程拿出来
    Thread t = Thread.currentThread();
    // 从线程中拿到 ThreadLocalMap
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        // 从 map 中拿到 entry，由于 ThreadLocalMap 在 set 时的 hash 冲突的策略不同，导致拿的时候逻辑也不太一样
        ThreadLocalMap.Entry e = map.getEntry(this);
        // 如果不为空，读取当前 ThreadLocal 中保存的值
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    // 否则给当前线程的 ThreadLocal 初始化，并返回初始值 null
    return setInitialValue();
}
```

接着我们来看下 ThreadLocalMap 的 getEntry 方法，源码如下：

```java
// 得到当前 thradLocal 对应的值，值的类型是由 thradLocal 的泛型决定的
// 由于 thradLocalMap set 时解决数组索引位置冲突的逻辑，导致 thradLocalMap get 时的逻辑也是对应的
// 首先尝试根据 hashcode 取模数组大小-1 = 索引位置 i 寻找，找不到的话，自旋把 i+1，直到找到索引位置不为空为止
private Entry getEntry(ThreadLocal<?> key) {
    // 计算索引位置：ThreadLocal 的 hashCode 取模数组大小-1
    int i = key.threadLocalHashCode & (table.length - 1);
    Entry e = table[i];
    // e 不为空，并且 e 的 ThreadLocal 的内存地址和 key 相同，直接返回，否则就是没有找到，继续通过 getEntryAfterMiss 方法找
    if (e != null && e.get() == key)
        return e;
    else
    // 这个取数据的逻辑，是因为 set 时数组索引位置冲突造成的  
        return getEntryAfterMiss(key, i, e);
}
// 自旋 i+1，直到找到为止
private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e) {
    Entry[] tab = table;
    int len = tab.length;
    // 在大量使用不同 key 的 ThreadLocal 时，其实还蛮耗性能的
    while (e != null) {
        ThreadLocal<?> k = e.get();
        // 内存地址一样，表示找到了
        if (k == key)
            return e;
        // 删除没用的 key
        if (k == null)
            expungeStaleEntry(i);
        // 继续使索引位置 + 1
        else
            i = nextIndex(i, len);
        e = tab[i];
    }
    return null;
}
```

get 逻辑源码中注释已经写的很清楚了，我们就不重复说了。



### 6 扩容

ThreadLocalMap 中的 ThreadLocal 的个数超过阈值时，ThreadLocalMap 就要开始扩容了，我们一起来看下扩容的逻辑：

```java
//扩容
private void resize() {
    // 拿出旧的数组
    Entry[] oldTab = table;
    int oldLen = oldTab.length;
    // 新数组的大小为老数组的两倍
    int newLen = oldLen * 2;
    // 初始化新数组
    Entry[] newTab = new Entry[newLen];
    int count = 0;
    // 老数组的值拷贝到新数组上
    for (int j = 0; j < oldLen; ++j) {
        Entry e = oldTab[j];
        if (e != null) {
            ThreadLocal<?> k = e.get();
            if (k == null) {
                e.value = null; // Help the GC
            } else {
                // 计算 ThreadLocal 在新数组中的位置
                int h = k.threadLocalHashCode & (newLen - 1);
                // 如果索引 h 的位置值不为空，往后+1，直到找到值为空的索引位置
                while (newTab[h] != null)
                    h = nextIndex(h, newLen);
                // 给新数组赋值
                newTab[h] = e;
                count++;
            }
        }
    }
    // 给新数组初始化下次扩容阈值，为数组长度的三分之二
    setThreshold(newLen);
    size = count;
    table = newTab;
}
```

源码注解也比较清晰，我们注意两点：

1. 扩容后数组大小是原来数组的两倍；
2. 扩容时是绝对没有线程安全问题的，因为 ThreadLocalMap 是线程的一个属性，一个线程同一时刻只能对 ThreadLocalMap 进行操作，因为同一个线程执行业务逻辑必然是串行的，那么操作 ThreadLocalMap 必然也是串行的。



### 7 总结

ThreadLocal 是非常重要的 API，我们在写一个中间件的时候经常会用到，比如说流程引擎中上下文的传递，调用链ID的传递等等，非常好用，但坑也很多。



## **44 场景实战：ThreadLocal 在上下文传值场景下的实践**

### 开篇语

我们在 《打动面试官：线程池流程编排中的运用实战》一文中将流程引擎简单地完善了一下，本文在其基础上继续进行改造，建议同学可以先看看 GitHub 上的代码，或者看看之前的文章。



### 1 回顾

流程引擎编排的对象，我们称为组件（就是 SpringBean），之前我们给组件定义了通用的接口，组件实现时就实现这个接口，代码如下：
![图片描述](http://img.mukewang.com/5dd603980001289a16401246.png)

我们定义了 DomainAbilityBean 接口，入参和出参都是 FlowContent，FlowContent 我们称为上下文。



### 2 ThreadLocal 实现

上下文传参除了 FlowContent 实现外，ThreadLocal 也是可以实现的，我们来演示一下：



#### 2.1 定义 ThreadLocal 上下文工具类

首先我们使用 ThreadLocal 定义了上下文工具类，并且定义了 put、get 方法，方便使用，代码如下：

```java
public class ContextCache implements Serializable {

  private static final long serialVersionUID = 2136539028591849277L;

  // 使用 ThreadLocal 缓存上下文信息
  public static final ThreadLocal<Map<String,String>> CACHE = new ThreadLocal<>();

  /**
   * 放数据
   * @param sourceKey
   */
  public static final void putAttribute(String sourceKey,String value){
    Map<String,String> cacheMap = CACHE.get();
    if(null == cacheMap){
      cacheMap = new HashMap<>();
    }
    cacheMap.put(sourceKey,value);
    CACHE.set(cacheMap);
  }

  /**
   * 拿数据
   * @param sourceKey
   */
  public static final String getAttribute(String sourceKey){
    Map<String,String> cacheMap = CACHE.get();
    if(null == cacheMap){
      return null;
    }
    return cacheMap.get(sourceKey);
  }

}
```

如果你想往 ThreadLocal 放数据，调用 ContextCache.putAttribute 方法，如果想从 ThreadLocal 拿数据，调用 ContextCache.getAttribute 方法即可。

我们写了两个组件，一个组件放数据，一个组件拿数据，如下：

![图片描述](http://img.mukewang.com/5dd6037500016aa914521260.png)
我们把两个 SpringBean 注册到流程注册中心中，让其按照先执行 BeanThree 再执行 BeanFive 的顺序进行执行，运行 DemoApplication 类的 main 方法进行执行，执行结果如下：

![图片描述](http://img.mukewang.com/5dd6033f0001448417820228.png)

从打印的日志可以看到，在 Spring 容器管理的 SpringBean 中，ThreadLocal 也是可以储存中间缓存值的。



### 3 开启子线程

我们做一个实验，我们在 BeanFive 中开启子线程，然后再从 ThreadLocal 中拿值，看看能否拿到值，BeanFive 的代码修改成如下：

![图片描述](http://img.mukewang.com/5dd6031e000120b717060666.png)
我们再来运行一下，打印的日志如下：
![图片描述](http://img.mukewang.com/5dd602f60001501620040500.png)

从打印的日志中，我们发现在子线程中从 ThreadLocal 取值时，并没有取得值，这个原因主要是我们之前说的，线程在创建的时候，并不会把父线程的 ThreadLocal 中的值拷贝给子线程的 ThreadLocal，解决方案就是把 ThreadLocal 修改成 InheritableThreadLocal，代码修改如下：

![图片描述](http://img.mukewang.com/5dd602b800013f6f20360392.png)

我们再次运行，结果如下：

![图片描述](http://img.mukewang.com/5dd602950001394415020216.png)
从运行结果看，我们成功的在子线程中拿到值。



### 4 线程池 + ThreadLocal

如果是拿数据的 springBean 是丢给线程池执行的，我们能够成功的从 ThreadLocal 中拿到数据么？

首先我们在放数据的 springBean 中，把放的值修改成随机的，接着拿数据的 SpringBean 修改成异步执行，代码修改如下：

![图片描述](http://img.mukewang.com/5dd6026f0001812a19221304.png)
为了能快速看到效果，我们把线程池的 coreSize 和 maxSize 全部修改成 3，并让任务沉睡一段时间，这样三个线程肯定消费不完任务，大量任务都会到队列中去排队，我们修改一下测试脚本，如下：
![图片描述](http://img.mukewang.com/5dd602380001a82413940340.png)

我们期望的结果：

1. 线程池中执行的 BeanFive 可以成功从 ThreadLocal 中拿到数据；
2. 能够从 ThreadLocal 拿到正确的数据，比如 BeanThree 刚放进 key1，value5，那么期望在 BeanFive 中根据 key1 能拿出 value5，而不是其它值。

我们运行一下，结果如下：

![图片描述](http://img.mukewang.com/5dd6021f00010d9a20260874.png)
从结果中可以看到，并没有符合我们的预期，我们往 ThreadLocal 中 put 进很多值，但最后拿出来的值却很多都是 value379，都为最后 put 到 ThreadLocal 中的值。

这个原因主要是 ThreadLocal 存储的 HashMap 的引用都是同一个，main 主线程可以修改 HashMap 中的值，子线程从 ThreadLocal 中拿值时，也是从 HashMap 中拿值，从而导致不能把 put 的值通过 ThreadLocal 正确的传递给子线程。

为了证明是这个原因，我们在从 ThreadLocal 放、拿值的地方，把 HashMap 的内存地址都打印出来，改动代码如下：
![图片描述](http://img.mukewang.com/5dd601ed00012b0118101350.png)

我们再次运行测试代码，运行的结果如下：

![图片描述](http://img.mukewang.com/5dd601cf0001c0bd10150820.png)
从测试结果中可以看到，不管是主线程还是子线程和 ThreadLocal 进行交互时，HashMap 都是同一个，也就是说 ThreadLocal 中保存的 HashMap 是共享的，这就导致了线程安全的问题，子线程读取到的值就会混乱掉。



### 5 解决方案

针对这个问题，我们提出了一种解决方案，在把任务提交到线程池时，我们进行 HashMap 的拷贝，这样子线程的 HashMap 和 main 线程的 HashMap 就不同了，可以解决上面的问题。

我们提交任务时， 使用的是 Runnable，要实现 HashMap 的拷贝的话，我们需要把 Runnable 进行一层包装，包装的代码如下：

![图片描述](http://img.mukewang.com/5dd601610001af8b08020916.png)
运行结果如下:
![图片描述](http://img.mukewang.com/5dd6013f0001763b09370823.png)

从运行结果中可以看出，线程池拿出来的 value 已经是正确的了。



### 6 总结

本文通过 ThreadLocal 来改造流程引擎中的上下文传递，希望能够加深大家对 ThreadLocal 的认识和使用技巧，有兴趣的同学可以把我们的代码下载下来，跑跑看。

## **45 Socket 源码及面试题**

### 引导语

Socket 中文翻译叫套接字，可能很多工作四五年的同学都没有用过这个 API，但只要用到这个 API 时，必然是在重要的工程的核心代码处。

大家平时基本都在用开源的各种 rpc 框架，比如说 Dubbo、gRPC、Spring Cloud 等等，很少需要手写网络调用，以下三小节可以帮助大家补充这块的内容，当你真正需要的时候，可以作为手册示例。

本文和《ServerSocket 源码及面试题》一文主要说 Socket 和 ServerSocket 的源码，《工作实战：Socket 结合线程池的使用》这章主要说两个 API 在实际工作中如何落地。



### 1 Socket 整体结构

Socket 的结构非常简单，Socket 就像一个壳一样，将套接字初始化、创建连接等各种操作包装了一下，其底层实现都是 SocketImpl 实现的，Socket 本身的业务逻辑非常简单。

Socket 的属性不多，有套接字的状态，SocketImpl，读写的状态等等，源码如下图：
![图片描述](http://img.mukewang.com/5dd60470000164ca04960264.png)

套接字的状态变更都是有对应操作方法的，比如套接字新建（createImpl 方法）后，状态就会更改成 created = true，连接（connect）之后，状态更改成 connected = true 等等。



### 2 初始化

Socket 的构造器比较多，可以分成两大类：

1. 指定代理类型（Proxy）创建套节点，一共有三种类型为：DIRECT（直连）、HTTP（HTTP、FTP 高级协议的代理）、SOCKS（SOCKS 代理），三种不同的代码方式对应的 SocketImpl 不同，分别是：PlainSocketImpl、HttpConnectSocketImpl、SocksSocketImpl，除了类型之外 Proxy 还指定了地址和端口；
2. 默认 SocksSocketImpl 创建，并且需要在构造器中传入地址和端口，源码如下：

```java
// address 代表IP地址，port 表示套接字的端口
// address 我们一般使用 InetSocketAddress，InetSocketAddress 有 ip+port、域名+port、InetAddress 等初始化方式
public Socket(InetAddress address, int port) throws IOException {
    this(address != null ? new InetSocketAddress(address, port) : null,
         (SocketAddress) null, true);
}
```

这里的 address 可以是 ip 地址或者域名，比如说 127.0.0.1 或者 www.wenhe.com。

我们一起看一下这个构造器调用的 this 底层构造器的源码：

```java
// stream 为 true 时，表示为stream socket 流套接字，使用 TCP 协议，比较稳定可靠，但占用资源多
// stream 为 false 时，表示为datagram socket 数据报套接字，使用 UDP 协议，不稳定，但占用资源少
private Socket(SocketAddress address, SocketAddress localAddr,
               boolean stream) throws IOException {
    setImpl();

    // backward compatibility
    if (address == null)
        throw new NullPointerException();

    try {
        // 创建 socket
        createImpl(stream);
        // 如果 ip 地址不为空，绑定地址
        if (localAddr != null)
            // create、bind、connect 也是 native 方法
            bind(localAddr);
        connect(address);
    } catch (IOException | IllegalArgumentException | SecurityException e) {
        try {
            close();
        } catch (IOException ce) {
            e.addSuppressed(ce);
        }
        throw e;
    }
}
```

从源码中可以看出：

1. 在构造 Socket 的时候，你可以选择 TCP 或 UDP，默认是 TCP；
2. 如果构造 Socket 时，传入地址和端口，那么在构造的时候，就会尝试在此地址和端口上创建套接字；
3. Socket 的无参构造器只会初始化 SocksSocketImpl，并不会和当前地址端口绑定，需要我们手动的调用 connect 方法，才能使用当前地址和端口；
4. Socket 我们可以理解成网络沟通的语言层次的抽象，底层网络创建、连接和关闭，仍然是 TCP 或 UDP 本身网络协议指定的标准，Socket 只是使用 Java 语言做了一层封装，从而让我们更方便地使用。



### 3 connect 连接服务端

connect 方法主要用于 Socket 客户端连接上服务端，如果底层是 TCP 层协议的话，就是通过三次握手和服务端建立连接，为客户端和服务端之间的通信做好准备，底层源码如下：

```java
public void connect(SocketAddress endpoint, int timeout) throws IOException {
}
```

connect 方法要求有两个入参，第一个入参是 SocketAddress，表示服务端的地址，我们可以使用 InetSocketAddress 进行初始化，比如：new InetSocketAddress(“www.wenhe.com”, 2000)。

第二入参是超时时间的意思（单位毫秒），表示客户端连接服务端的最大等待时间，如果超过当前等待时间，仍然没有成功建立连接，抛 SocketTimeoutException 异常，如果是 0 的话，表示无限等待。



### 4 Socket 常用设置参数

Socket 的常用设置参数在 SocketOptions 类中都可以找到，接下来我们来一一分析下，以下理解大多来自类注释和网络。



#### 4.1 setTcpNoDelay

此方法是用来设置 TCP_NODELAY 属性的，属性的注释是这样的：此设置仅仅对 TCP 生效，主要为了禁止使用 Nagle 算法，true 表示禁止使用，false 表示使用，默认是 false。

对于 Nagle 算法，我们引用维基百科上的解释：

> **纳格算法**是以减少数据包发送量来增进 [TCP/IP] 网络的性能，它由约翰·纳格任职于[Ford Aerospace](https://zh.wikipedia.org/w/index.php?title=Ford_Aerospace&action=edit&redlink=1)时命名。
>
> 纳格的文件[[注 1\]](https://zh.wikipedia.org/wiki/納格算法#cite_note-1)描述了他所谓的“小数据包问题”－某个应用程序不断地提交小单位的数据，且某些常只占1[字节](https://zh.wikipedia.org/wiki/字节)大小。因为[TCP](https://zh.wikipedia.org/wiki/傳輸控制協議)数据包具有40[字节](https://zh.wikipedia.org/wiki/字节)的标头信息（TCP与IPv4各占20字节），这导致了41字节大小的数据包只有1字节的可用信息，造成庞大的浪费。这种状况常常发生于[Telnet](https://zh.wikipedia.org/wiki/Telnet)工作阶段－大部分的键盘操作会产生1字节的数据并马上提交。更糟的是，在慢速的网络连线下，这类的数据包会大量地在同一时点传输，造成[壅塞碰撞](https://zh.wikipedia.org/w/index.php?title=壅塞碰撞&action=edit&redlink=1)。
>
> 纳格算法的工作方式是合并（[coalescing](https://zh.wiktionary.org/wiki/en:Coalesce)）一定数量的输出数据后一次提交。特别的是，只要有已提交的数据包尚未确认，发送者会持续缓冲数据包，直到累积一定数量的数据才提交。

总结算法开启关闭的场景：

1. 如果 Nagle 算法关闭，对于小数据包，比如一次鼠标移动，点击，客户端都会立马和服务端交互，实时响应度非常高，但频繁的通信却很占用不少网络资源；
2. 如果 Nagle 算法开启，算法会自动合并小数据包，等到达到一定大小（MSS）后，才会和服务端交互，优点是减少了通信次数，缺点是实时响应度会低一些。

Socket 创建时，默认是开启 Nagle 算法的，可以根据实时性要求来选择是否关闭 Nagle 算法。



#### 4.2 setSoLinger

setSoLinger 方法主要用来设置 SO_LINGER 属性值的。

注释上大概是这个意思：在我们调用 close 方法时，默认是直接返回的，但如果给 SO_LINGER 赋值，就会阻塞 close 方法，在 SO_LINGER 时间内，等待通信双方发送数据，如果时间过了，还未结束，将发送 TCP RST 强制关闭 TCP 。

我们看一下 setSoLinger 源码：

```java
// on 为 false，表示不启用延时关闭，true 的话表示启用延时关闭
// linger 为延时的时间，单位秒
public void setSoLinger(boolean on, int linger) throws SocketException {
    // 检查是否已经关闭
    if (isClosed())
        throw new SocketException("Socket is closed");
    // 不启用延时关闭
    if (!on) {
        getImpl().setOption(SocketOptions.SO_LINGER, new Boolean(on));
    // 启用延时关闭，如果 linger 为 0，那么会立即关闭
    // linger 最大为 65535 秒，约 18 小时
    } else {
        if (linger < 0) {
            throw new IllegalArgumentException("invalid value for SO_LINGER");
        }
        if (linger > 65535)
            linger = 65535;
        getImpl().setOption(SocketOptions.SO_LINGER, new Integer(linger));
    }
}
```



#### 4.3 setOOBInline

setOOBInline 方法主要使用设置 SO_OOBINLINE 属性。

注释上说：如果希望接受 TCP urgent data（TCP 紧急数据）的话，可以开启该选项，默认该选项是关闭的，我们可以通过 Socket#sendUrgentData 方法来发送紧急数据。

查询了很多资料，都建议尽可能的去避免设置该值，禁止使用 TCP 紧急数据。



#### 4.4 setSoTimeout

setSoTimeout 方法主要是用来设置 SO_TIMEOUT 属性的。

注释上说：用来设置阻塞操作的超时时间，阻塞操作主要有：

1. ServerSocket.accept() 服务器等待客户端的连接；
2. SocketInputStream.read() 客户端或服务端读取输入超时；
3. DatagramSocket.receive()。

我们必须在必须在阻塞操作之前设置该选项， 如果时间到了，操作仍然在阻塞，会抛出 InterruptedIOException 异常（Socket 会抛出 SocketTimeoutException 异常，不同的套接字抛出的异常可能不同）。

对于 Socket 来说，超时时间如果设置成 0，表示没有超时时间，阻塞时会无限等待。



#### 4.5 setSendBufferSize

setSendBufferSize 方法主要用于设置 SO_SNDBUF 属性的，入参是 int 类型，表示设置发送端（输出端）的缓冲区的大小，单位是字节。

入参 size 必须大于 0，否则会抛出 IllegalArgumentException 异常。

一般我们都是采取默认的，如果值设置太小，很有可能导致网络交互过于频繁，如果值设置太大，那么交互变少，实时性就会变低。



#### 4.6 setReceiveBufferSize

setReceiveBufferSize 方法主要用来设置 SO_RCVBUF 属性的，入参是 int 类型，表示设置接收端的缓冲区的大小，单位是字节。

入参 size 必须大于 0，否则会抛出 IllegalArgumentException 异常。

一般来说，在套接字建立连接之后，我们可以随意修改窗口大小，但是当窗口大小大于 64k 时，需要注意：

1. 必须在 Socket 连接客户端之前设置缓冲值；
2. 必须在 ServerSocket 绑定本地地址之前设置缓冲值。



#### 4.7 setKeepAlive

setKeepAlive 方法主要用来设置 SO_KEEPALIVE 属性，主要是用来探测服务端的套接字是否还是存活状态，默认设置是 false，不会触发这个功能。

如果 SO_KEEPALIVE 开启的话，TCP 自动触发功能：如果两小时内，客户端和服务端的套接字之间没有任何通信，TCP 会自动发送 keepalive 探测给对方，对方必须响应这个探测（假设是客户端发送给服务端），预测有三种情况：

1. 服务端使用预期的 ACK 回复，说明一切正常；
2. 服务端回复 RST，表示服务端处于死机或者重启状态，终止连接；
3. 没有得到服务端的响应（会尝试多次），表示套接字已经关闭了。



#### 4.8 setReuseAddress

setReuseAddress 方法主要用来设置 SO_REUSEADDR 属性，入参是布尔值，默认是 false。

套接字在关闭之后，会等待一段时间之后才会真正的关闭，如果此时有新的套接字前来绑定同样的地址和端口时，如果 setReuseAddress 为 true 的话，就可以绑定成功，否则绑定失败。



### 5 总结

如果平时一直在做业务代码，Socket 可能用到的很少，但面试问到网络协议时，或者以后有机会做做中间件的时候，就会有大概率会接触到 Socket，所以多学学，作为知识储备也蛮好的。

## **46 ServerSocket 源码及面试题**

### 引导语

上一小节我们学习了 Socket，本文我们来看看服务端套接字 API：ServerSocket，本文学习完毕之后，我们就可以把客服端 Socket 和服务端 ServerSocket 串联起来，做一个真实的网络通信的 demo 了。



### 1 类属性

ServerSocket 的主要作用，是作为服务端的套接字，接受客户端套接字传递过来的信息，并把响应回传给客户端，其属性非常简单，如下：

```java
private boolean created = false;// 已创建
private boolean bound = false;// 绑定
private boolean closed = false;// 已关闭
// 底层的功能都依靠 SocketImpl 来实现
private SocketImpl impl;
```

ServerSocket 和 Socket 一样，底层都是依靠 SocketImpl 的能力，而 SocketImpl 底层能力的实现基本上都是 native 方法实现的。



### 2 初始化

初始化大概可以分成两类：无参构造器和有参构造器。

1. 无参构造器做的事情比较简单，只指定了 SocketImpl 为 SocksSocketImpl 类；
2. 有参构造器有几种初始化的形式，我们一起来看一下参数最多的构造器的源码。

```java
public ServerSocket(int port, int backlog, InetAddress bindAddr) throws IOException {
    // 默认是 SocksSocketImpl 实现
    setImpl();
    // 端口必须大于 0，小于 65535
    if (port < 0 || port > 0xFFFF)
        throw new IllegalArgumentException(
                   "Port value out of range: " + port);
    // 最大可连接数如果小于1，那么采取默认的 50
    if (backlog < 1)
      backlog = 50;
    try {
        // 底层 navtive 方法
        bind(new InetSocketAddress(bindAddr, port), backlog);
    } catch(SecurityException e) {
        close();
        throw e;
    } catch(IOException e) {
        close();
        throw e;
    }
}
```

入参 port 指的是 ServerSocket 需要绑定本地那个端口。

入参 backlog 指的是服务端接受客户端连接队列的最大长度，这里需要注意的是，这里并不是限制客户端连接的个数，我们在 JDK8 版本下做过实验，我们把服务端的 backlog 设置成 1，并且变慢服务端的处理速度，当服务端并发请求过来时，并不是第二个请求过来就拒绝连接，我们在实际工作中，最好也不要用 backlog 来限制客户端连接的个数。

还有点需要注意的是 backlog 小于 1 时，backlog 会被设置成默认的 50。

入参 InetAddress 表示 ip 地址。



### 3 bind

bind 方法主要作用是把 ServerSocket 绑定到本地的端口上，只有当我们使用无参构造器初始化 ServerSocket 时，才会用到这个方法，如果使用有参构造器的话，在初始化时就已经绑定到本地的端口上了。

配合无参构造器，一般我们这么用：

```
// 进行初始化
ServerSocket serverSocket = new ServerSocket();
// 进行绑定
serverSocket.bind(new InetSocketAddress("localhost", 7007));
```



### 4 accept

accept 方法主要是用来 ServerSocket 接受来自客户端的套接字的，如果此时没有来自客户端的请求时，该方法就会一直阻塞，如果有通过 setSoTimeout 方法设置超时时间，那么 accept 只会在超时间内阻塞，过了超时时间就会抛出异常。

bind 和 accept 方法底层都是 native 方法实现，我们就不看源码了。



### 5 面试题



#### 5.1 说说你对 Socket 和 ServerSocket 的理解？

答：两者我们都可以称为套接字，底层基于 TCP/UDP 协议，套接字对底层协议进行了封装，让我们使用时更加方便，Socket 常被使用在客户端，用于向服务端请求数据和接受响应，ServerSocket 常用于在服务端，用于接受客户端的请求并进行处理，两者其底层使用都是依靠 SocketImpl 的子类的 native 方法。



#### 5.2 说说对 SocketOptions 中的 SO_TIMEOUT 的理解？

答：SocketOptions 类有很多属性设置，比如 SO_TIMEOUT 、SO_LINGER 等等，这些问题说一下自己的理解即可，可以参考 《Socket 源码及面试题》 中对各种属性的解析。



#### 5.3 在构造 Socket 的时候，我可以选择 TCP 或 UDP 么？应该如何选择？

答：可以的，Socket 有三个参数的构造器，第三个参数表示你想使用 TCP 还是 UDP。



#### 5.4 TCP 有自动检测服务端是否存活的机制么？有没有更好的办法？

答：有的，我们可以通过 setKeepAlive 方法来激活该功能，如果两小时内，客户端和服务端的套接字之间没有任何通信，TCP 会自动发送 keepalive 探测给服务端，预测服务端有三种情况：

1. 服务端使用预期的 ACK 回复，说明一切正常；
2. 服务端回复 RST，表示服务端处于死机或者重启状态，终止连接；
3. 没有得到服务端的响应（会尝试多次），表示套接字已经关闭了。

但我们并不建议使用这种方式，我们可以自己起一个定时任务，定时的访问服务端的特殊接口，如果服务端返回的数据和预期一致，说明服务端是存活的。



### 总结

Socket 和 ServerSocket 在源码方面没啥特别可说的地方，基本都是一些设置，底层实现都是 native 的方法，但面试官会从此延伸到一些网络协议方面的知识，因为这已经超出本专栏的范畴了，感兴趣的同学可以自行百度。

## **47 工作实战：Socket 结合线程池的使用**

### 引导语

Socket 面试最终题一般都是让你写一个简单的客户端和服务端通信的例子，本文就带大家一起来写这个 demo。



### 1 要求

1. 可以使用 Socket 和 ServiceSocket 以及其它 API；
2. 写一个客户端和服务端之间 TCP 通信的例子；
3. 服务端处理任务需要异步处理；
4. 因为服务端处理能力很弱，只能同时处理 5 个请求，当第六个请求到达服务器时，需要服务器返回明确的错误信息：服务器太忙了，请稍后重试~。

需求比较简单，唯一复杂的地方在于第四点，我们需要对客户端的请求量进行控制，首先我们需要确认的是，我们是无法控制客户端发送的请求数的，所以我们只能从服务端进行改造，比如从服务端进行限流。

有的同学可能很快想到，我们应该使用 ServerSocket 的 backlog 的属性，把其设置成 5，但我们在上一章中说到 backlog 并不能准确代表限制的客户端连接数，而且我们还要求服务端返回具体的错误信息，即使 backlog 生效，也只会返回固定的错误信息，不是我们定制的错误信息。

我们好好想想，线程池似乎可以做这个事情，我们可以把线程池的 coreSize 和 maxSize 都设置成 4，把队列大小设置成 1，这样服务端每次收到请求后，会先判断一下线程池中的队列有没有数据，如果有的话，说明当前服务器已经马上就要处理第五个请求了，当前请求就是第六个请求，应该被拒绝。

正好线程池的加入也可以满足第三点，服务端的任务可以异步执行。



### 2 客户端代码

客户端的代码比较简单，直接向服务器请求数据即可，代码如下：

```java
public class SocketClient {
  private static final Integer SIZE = 1024;
  private static final ThreadPoolExecutor socketPoll = new ThreadPoolExecutor(50, 50,
                                                                               365L,
                                                                               TimeUnit.DAYS,
                                                                               new LinkedBlockingQueue<>(400));

  @Test
  public void test() throws InterruptedException {
    // 模拟客户端同时向服务端发送 6 条消息
    for (int i = 0; i < 6; i++) {
      socketPoll.submit(() -> {
        send("localhost", 7007, "nihao");
      });
    }
    Thread.sleep(1000000000);
  }
  /**
   * 发送tcp
   *
   * @param domainName 域名
   * @param port       端口
   * @param content    发送内容
   */
  public static String send(String domainName, int port, String content) {
    log.info("客户端开始运行");
    Socket socket = null;
    OutputStream outputStream = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    InputStream is = null;
    StringBuffer response = null;
    try {
      if (StringUtils.isBlank(domainName)) {
        return null;
      }
      // 无参构造器初始化 Socket，默认底层协议是 TCP
      socket = new Socket();
      socket.setReuseAddress(true);
      // 客户端准备连接服务端，设置超时时间 10 秒
      socket.connect(new InetSocketAddress(domainName, port), 10000);
      log.info("TCPClient 成功和服务端建立连接");
      // 准备发送消息给服务端
      outputStream = socket.getOutputStream();
      // 设置 UTF 编码，防止乱码
      byte[] bytes = content.getBytes(Charset.forName("UTF-8"));
      // 输出字节码
      segmentWrite(bytes, outputStream);
      // 关闭输出
      socket.shutdownOutput();
      log.info("TCPClient 发送内容为 {}",content);

      // 等待服务端的返回
      socket.setSoTimeout(50000);//50秒还没有得到数据，直接断开连接
      // 得到服务端的返回流
      is = socket.getInputStream();
      isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
      // 从流中读取返回值
      response = segmentRead(br);
      // 关闭输入流
      socket.shutdownInput();

      //关闭各种流和套接字
      close(socket, outputStream, isr, br, is);
      log.info("TCPClient 接受到服务端返回的内容为 {}",response);
      return response.toString();
    } catch (ConnectException e) {
      log.error("TCPClient-send socket连接失败", e);
      throw new RuntimeException("socket连接失败");
    } catch (Exception e) {
      log.error("TCPClient-send unkown errror", e);
      throw new RuntimeException("socket连接失败");
    } finally {
      try {
        close(socket, outputStream, isr, br, is);
      } catch (Exception e) {
        // do nothing
      }
    }
  }

  /**
   * 关闭各种流
   *
   * @param socket
   * @param outputStream
   * @param isr
   * @param br
   * @param is
   * @throws IOException
   */
  public static void close(Socket socket, OutputStream outputStream, InputStreamReader isr,
                           BufferedReader br, InputStream is) throws IOException {
    if (null != socket && !socket.isClosed()) {
      try {
        socket.shutdownOutput();
      } catch (Exception e) {
      }
      try {
        socket.shutdownInput();
      } catch (Exception e) {
      }
      try {
        socket.close();
      } catch (Exception e) {
      }
    }
    if (null != outputStream) {
      outputStream.close();
    }
    if (null != br) {
      br.close();
    }
    if (null != isr) {
      isr.close();
    }
    if (null != is) {
      is.close();
    }
  }

  /**
   * 分段读
   *
   * @param br
   * @throws IOException
   */
  public static StringBuffer segmentRead(BufferedReader br) throws IOException {
    StringBuffer sb = new StringBuffer();
    String line;
    while ((line = br.readLine()) != null) {
      sb.append(line);
    }
    return sb;
  }

  /**
   * 分段写
   *
   * @param bytes
   * @param outputStream
   * @throws IOException
   */
  public static void segmentWrite(byte[] bytes, OutputStream outputStream) throws IOException {
    int length = bytes.length;
    int start, end = 0;
    for (int i = 0; end != bytes.length; i++) {
      start = i == 0 ? 0 : i * SIZE;
      end = length > SIZE ? start + SIZE : bytes.length;
      length -= SIZE;
      outputStream.write(bytes, start, end - start);
      outputStream.flush();
    }
  }

}
```

客户端代码中我们也用到了线程池，主要是为了并发模拟客户端一次性发送 6 个请求，按照预期服务端在处理第六个请求的时候，会返回特定的错误信息给客户端。

以上代码主要方法是 send 方法，主要处理像服务端发送数据，并处理服务端的响应。



### 3 服务端代码

服务端的逻辑分成两个部分，第一部分是控制客户端的请求个数，当超过服务端的能力时，拒绝新的请求，当服务端能力可响应时，放入新的请求，第二部分是服务端任务的执行逻辑。



#### 3.1 对客户端请求进行控制

```java
public class SocketServiceStart {

  /**
   * 服务端的线程池，两个作用
   * 1：让服务端的任务可以异步执行
   * 2：管理可同时处理的服务端的请求数
   */
  private static final ThreadPoolExecutor collectPoll = new ThreadPoolExecutor(4, 4,
                                                                               365L,
                                                                               TimeUnit.DAYS,
                                                                               new LinkedBlockingQueue<>(
                                                                                   1));

  @Test
  public void test(){
    start();
  }

  /**
   * 启动服务端
   */
  public static final void start() {
    log.info("SocketServiceStart 服务端开始启动");
    try {
      // backlog  serviceSocket处理阻塞时，客户端最大的可创建连接数，超过客户端连接不上
      // 当线程池能力处理满了之后，我们希望尽量阻塞客户端的连接
//      ServerSocket serverSocket = new ServerSocket(7007,1,null);
      // 初始化服务端
      ServerSocket serverSocket = new ServerSocket();
      serverSocket.setReuseAddress(true);
//      serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 80));
      serverSocket.bind(new InetSocketAddress("localhost", 7007));
      log.info("SocketServiceStart 服务端启动成功");
      // 自旋，让客户端一直在取客户端的请求，如果客户端暂时没有请求，会一直阻塞
      while (true) {
        // 接受客户端的请求
        Socket socket = serverSocket.accept();

        // 如果队列中有数据了，说明服务端已经到了并发处理的极限了，此时需要返回客户端有意义的信息
        if (collectPoll.getQueue().size() >= 1) {
          log.info("SocketServiceStart 服务端处理能力到顶，需要控制客户端的请求");
          //返回处理结果给客户端
          rejectRequest(socket);
          continue;
        }
        try {
          // 异步处理客户端提交上来的任务
          collectPoll.submit(new SocketService(socket));
        } catch (Exception e) {
          socket.close();
        }
      }
    } catch (Exception e) {
      log.error("SocketServiceStart - start error", e);
      throw new RuntimeException(e);
    } catch (Throwable e) {
      log.error("SocketServiceStart - start error", e);
      throw new RuntimeException(e);
    }
  }
	// 返回特定的错误码给客户端
  public static void rejectRequest(Socket socket) throws IOException {
    OutputStream outputStream = null;
    try{
      outputStream = socket.getOutputStream();
      byte[] bytes = "服务器太忙了，请稍后重试~".getBytes(Charset.forName("UTF-8"));
      SocketClient.segmentWrite(bytes, outputStream);
      socket.shutdownOutput();
    }finally {
      //关闭流
      SocketClient.close(socket,outputStream,null,null,null);
    }
  }


}
```

我们使用 collectPoll.getQueue().size() >= 1 来判断目前服务端是否已经到达处理的极限了，如果队列中有一个任务正在排队，说明当前服务端已经超负荷运行了，新的请求应该拒绝掉，如果队列中没有数据，说明服务端还可以接受新的请求。

以上代码注释详细，就不累赘说了。



#### 3.2 服务端任务的处理逻辑

服务端的处理逻辑比较简单，主要步骤是：从客户端的 Socket 中读取输入，进行处理，把响应返回给客户端。

我们使用线程沉睡 2 秒来模拟服务端的处理逻辑，代码如下：

```java
public class SocketService implements Runnable {

  private Socket socket;

  public SocketService() {
  }

  public SocketService(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    log.info("SocketService 服务端任务开始执行");
    OutputStream outputStream = null;
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    try {
      //接受消息
      socket.setSoTimeout(10000);// 10秒还没有得到数据，直接断开连接
      is = socket.getInputStream();
      isr = new InputStreamReader(is,"UTF-8");
      br = new BufferedReader(isr);
      StringBuffer sb = SocketClient.segmentRead(br);
      socket.shutdownInput();
      log.info("SocketService accept info is {}", sb.toString());

      //服务端处理 模拟服务端处理耗时
      Thread.sleep(2000);
      String response  = sb.toString();

      //返回处理结果给客户端
      outputStream = socket.getOutputStream();
      byte[] bytes = response.getBytes(Charset.forName("UTF-8"));
      SocketClient.segmentWrite(bytes, outputStream);
      socket.shutdownOutput();

      //关闭流
      SocketClient.close(socket,outputStream,isr,br,is);
      log.info("SocketService 服务端任务执行完成");
    } catch (IOException e) {
      log.error("SocketService IOException", e);
    } catch (Exception e) {
      log.error("SocketService Exception", e);
    } finally {
      try {
        SocketClient.close(socket,outputStream,isr,br,is);
      } catch (IOException e) {
        log.error("SocketService IOException", e);
      }
    }
  }
}
```



### 4 测试

测试的时候，我们必须先启动服务端，然后再启动客户端，首先我们启动服务端，打印日志如下：
![图片描述](http://img.mukewang.com/5dd604f40001b33022720244.png)

接着我们启动客户端，打印日志如下：
![图片描述](http://img.mukewang.com/5dd604e50001dc9423161292.png)

我们最后看一下服务端的运行日志：
![图片描述](http://img.mukewang.com/5dd604d40001040e22901054.png)

从以上运行结果中，我们可以看出得出的结果是符合我们预期的，服务端在请求高峰时，能够并发处理5个请求，其余请求可以用正确的提示进行拒绝。



### 5 总结

所以代码集中在 SocketClient、SocketServiceStart、SocketService 中，启动的顺序为先启动 SocketServiceStart，后运行 SocketClient，感兴趣的同学可以自己 debug 下，加深印象。

# **第10章 专栏总结**

## **48 一起看过的 Java 源码和面试真题**

### 不为了源码而读源码，只为了更好的实践

持续几个月，我们的专栏终于结束了，这篇总结篇，我们又想回到当初写这篇专栏的初心：我们不为读源码而读源码，只是为了更好的实践。

我刚工作的时候，就有一些大佬推荐我来阅读 Java 源码，那时候的我懵懵懂懂，只觉得大佬说的是对的，于是就去读，当时的目的很简单，主要是两个：一个是应付面试，一个是想让自己更强。

当时边工作边读源码，一开始真心是一点都看不懂，逻辑都看得很迷糊，更不用说去探究作者为什么这么写，用到哪些设计模式了，但也不知道为什么，还是咬牙把源码都读完了。

读完之后，还是比较骄傲的，虽然说读完之后，很多细节都不记得了，但不知道为啥，总是有股莫名的自信，原来自己已经是读过源码的人了，而且在平时的工作中，用到一些 API 时，脑海中突然就会蹦出一些火花来：比如说初始化 List、Map 时如何初始化其大小；比如说如何根据场景来设置线程池；比如说如何根据业务写出优雅的锁，这时候就会自我感觉代码写的好，其实我一直有个理念：只有紧密贴合业务，能帮助解决业务复杂度的代码才是好代码，读了第一遍 Java 源码之后，突然就有了这种感觉，对自己写的代码也越来越有自信了。

随着工作年龄的增加，又陆续读过几次 Java 源码，现在除了对自己写代码的自信，还多了一种帮助别人的自信，在同事遇到困难，或者 代码 review 时，一些漏洞，你很容易就看出来，不知不觉你就会成为团队中的技术专家。

所以我们才一直强调，我们读源码真心是为了更好的实践，这种好处当你认真读完源码之后，慢慢就会感受到了。



### 同学们的问题

在这几个月内，我收到很多问题，但比较频繁的是两类问题，第一类问题主要是说自己看不懂源码，问我怎么办？这个问题其实我也没有答案，Java 源码本身就很枯燥，它并不是电视剧，也不是小说，它是需要我们静下心来，一行一行琢磨思考的东西，同学们都很聪明，不是看不懂，只是不想静下心来罢了。当然当代码太复杂时，我们只看也是不行的，需要亲自动手 debug。

第二类问题主要是作者为什么这么写？这个问题其实也很难回答，但问这些问题的同学，我基本都一一作答了，在和这些同学交流的过程中，发现一小部分同学的确对源码很有研究，我也受益匪浅，但大部分同学其实并没有搞懂源码本身的逻辑，试想如果在没有搞懂源码本身的逻辑下，又如何去猜测并理解原作者用代码的本意呢？恐怕很难，所以还是建议大家先把源码本身逻辑弄懂后，再去推测本意和设计模型，不然这又会成为你阅读源码的阻碍（话虽然难听，但是真心的建议）。



### 感谢

这是我第一篇在慕课网的专栏，非常感谢慕课网的编辑和商务，给我了很多帮助。

当然最要感激的是各位同学，虽然我们一直内部强调，专栏不求快，只求质量和内容，但 Java 源码实在博大精深，由于我个人的理解问题和笔误，的确出现了一些理解不当的地方和笔误，在同学们的包容和指正下，我们也一起做了更正，所以非常感谢同学的包容和指正，真心的感谢大家的包容，谢谢。

最后留下我的个人微信（luanqiu0）吧，真心想交流后端技术和架构的可以加我，当然其它的我也不会了，再次谢谢各位同学一起走过我们的专栏，来过，看过，希望你能有所收获，谢谢。