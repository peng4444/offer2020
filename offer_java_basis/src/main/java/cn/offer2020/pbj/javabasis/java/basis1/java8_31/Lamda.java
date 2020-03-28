package cn.offer2020.pbj.javabasis.java.basis1.java8_31;//package cn.pbj2019.demo.javabasis.basis1.java8_31;
/*
 * lambda表达式有它自己的优点：（1）简洁，（2）易并行计算。尤其适用于遍历结果，循环计算数值或者赋值的时候非常方便。
	缺点:（1）若不用并行计算，很多时候计算速度没有比传统的 for 循环快。
　　 	（2）不容易使用debug模式调试。
　　 	（3）在 lambda 语句中直接强制类型转换不方便。
　　 	（4）不可以在foreach中修改foreach外面的值。
 */
public class Lamda {
    /**
     * Lamda表达式的三种方式：（参数） ->单行语句；  （参数） -> {单行语句}； （参数） ->表达式；
     * @param args
     * 避免了内部类定义过多的无用操作
     */
    public static void main(String[] args) {
//1		fun(() ->System.out.println("Hello World"));//Lamda表达式，函数式编程,不需要创建匿名内部类
        //传统的匿名内部类
//1		fun(new IMessage() {
//			public void print() {
//				System.out.println("Hello World");
//			}
//		});
//2
        fun((s) ->{
            s = s.toUpperCase();
            System.out.println(s);
        });
//3
        fun((s1,s2) -> s1+s2);
    }
    public static void fun(IMessage2 msg) {
//1		msg.print();
        msg.print2("hello");
    }
    public static void fun(IMessage3 msg) {
        System.out.println(msg.add(10,20));
    }
}
interface IMessage2{
    //1	public void print();
    public void print2(String str);//有参的单行语句
}
interface IMessage3{
    public int add(int x,int y);
}
