Java10新特性：    --我现在安装的是Java8，所以。。。
1.局部类型推断  
var i = 10;
var set = new HashSet<>();
List <String> list = new ArrayList <String>();
Stream <String> stream = getStream();
其实我们在JDK7，我们需要：List <String> list = new ArrayList <String>();
但是在JDK8，我们只需要：List <String> list = new ArrayList <>();
var aaa = null;//不允许null值
2.垃圾收集器优化 G1
3.新增73个新功能扩展API类库
copyOf方法    迭代顺序，拷贝回来的新列表，集合，不可修改
4.ByteArrayputStream:toString(charset)
重载toString方法，字节转换
5.Java.io.PrintStream java.io.PrintWrite
增加三个新的构造方法  两个参数，指定文件输出和指定文件编码
6.Java.io.Reader:transferTo 方法
成reader中读取所有的字符串，按照所读取的顺序将字符串写到指定的位置write
7.Java.util.Scanner java.util.Feomatter 新增三个构造方法两个参数，指定文件读取和指定文件编码 