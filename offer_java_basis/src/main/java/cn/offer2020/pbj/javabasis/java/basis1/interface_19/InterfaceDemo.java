package cn.offer2020.pbj.javabasis.java.basis1.interface_19;
/*
 * 接口使用原则：
 * 接口必须要有子类，一个子类可以使用implements实现多个接口
 * 接口中子类，必须覆写所有的接口中的抽象方法
 * 接口的对象可以利用子类对象 的向上转型进行实例化操作
 * Java里面，接口的主要功能是解决单继承的局限问题
 */
class X implements A,B{
	public void print() {
		System.out.println("A接口的抽象方法");
	}
	public void get() {
		System.out.println("B接口的抽象方法");
	}
}
public class InterfaceDemo{
	public static void main(String args[]) {
		X x = new X();//实例化子类对象
		A a = x;//向上转型
		B b = x;//向上转型
		a.print();
		b.get();

		System.out.println();
		A a2 = new X();
		B b2 = (B) a;
		b.get();
		System.out.println(a instanceof A);
		System.out.println(a instanceof B);
	}
}
