package cn.offer2020.pbj.javabasis.java.basis1.java8_31;//package cn.pbj2019.demo.javabasis.basis1.java8_31;
/**
 * Java8新特性 	方法引用
 * 引用静态方法： 类方法 ：：static方法名称
 * 引用某一个对象的方法： 实例化对象 ：：普通方法；
 * 引用特定类型的方法： 特定类：：普通方法；
 * 引用构造方法： 类名称 ：：new；
 * @author PBJ
 *
 */
public class Method {

    public static void main(String[] args) {
        //引用静态方法：在string类里面有一个valueOf()方法：public static String valueOf(int x);
        //即：将String.valueOf()方法变成了IMessage接口里面的zhuanhuan()方法
        IMessage4 <Integer,String > msg = String :: valueOf;
        String str  = msg.zhuanhuan(1000);
        System.out.println(str.replaceAll("0", "9"));
        //普通方法引用
        IMessage5<String> msg2 = "Hello" :: toUpperCase;
        String str2 = msg2.upper();
        System.out.println(str2);
        //特定方法的引用
        IMessage6<String> msg3 = String :: compareTo;
        System.out.println(msg3.compare("A", "B"));//-1
        //引用构造
        IMessage7<Book> msg4 = Book :: new ;
        Book book = msg4.create("java",99.0);
        System.out.println(book);
    }
}
interface IMessage4<P,R>{
    public R zhuanhuan(P p) ;
}
interface IMessage5<R>{
    public R upper();
}
@FunctionalInterface   //此为函数式接口，只能够定义一个方法
interface IMessage6<P>{
    public int compare(P p1,P p2);
}
@FunctionalInterface   //此为函数式接口，只能够定义一个方法
interface IMessage7<C>{
    public C create (String t ,double p);
}
class Book{
    private String title;
    private double price;
    public Book(String title,double price) {
        this.title  = title;
        this.price  = price;
    }
    @Override
    public String toString() {
        return "book name:"+this.title+"	book price:"+this.price;
    }
}
