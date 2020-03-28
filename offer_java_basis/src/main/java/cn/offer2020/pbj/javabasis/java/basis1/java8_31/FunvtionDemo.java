package cn.offer2020.pbj.javabasis.java.basis1.java8_31;//package cn.pbj2019.demo.javabasis.basis1.java8_31;
//java8新特性  	内建函数式接口
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
class MyDemo {
    public void print(String str) {
        System.out.println(str);
    }
}
public class FunvtionDemo {

    public static void main(String[] args) {
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
    }

}

