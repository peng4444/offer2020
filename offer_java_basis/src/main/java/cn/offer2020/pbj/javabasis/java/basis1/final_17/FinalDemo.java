package cn.offer2020.pbj.javabasis.java.basis1.final_17;
/*
 * final关键字，final定义类，方法，属性的特点
 * 1.使用final定义的类不能有子类
 * 2.使用final定义的方法不能被子类所覆写
 * 3。使用final定义的变量就成为了常量，常量必须在定义的时候设置好内容，不能够修改
 */
final class A{

}
//class B extends A{//是错误的
//
//}
class A2{
	public final void fun() {

	}
}
class B2 extends A2{
//	public void fun() {//是错误的
//
//	}
}
class A3{
	final double good = 100.0;
	public final void fun() {
//		good = 1.2;//是错误的
	}
}
public class FinalDemo {

	public static void main(String[] args) {

	}

}

