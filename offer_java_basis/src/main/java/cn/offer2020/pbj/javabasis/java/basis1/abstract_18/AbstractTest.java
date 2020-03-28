package cn.offer2020.pbj.javabasis.java.basis1.abstract_18;
abstract class A{//抽象类
	public void fun() {
		System.out.println("存在方法体的方法");
	}
	public abstract void print();//抽象方法
}
class B extends A{
	public void print() {
		System.out.println("抽象类必须被继承，抽象类的子类必须覆写抽象类中的全部抽象方法");
	}
}

abstract class A2{//外部抽象类不允许使用static声明
	static abstract class B2{//内部类允许使用static声明，相当于一个外部抽象类，使用 “外部类。内部类”的形式
		public abstract void print();
	}
}
class X extends A2.B2{
	public void print() {
		System.out.println("X类覆写A2.B2类");
	}
}
abstract class A3{
	public static void print() {
		System.out.println("抽象类的static方法可以直接调用，不需要实例化");
	}
}
/*
 * 抽象类必须被继承，抽象类的子类必须覆写抽象类中的全部抽象方法
 * 抽象类的对象实例化需要依靠子类完成，采用向上转型的方法处理
 */
public class AbstractTest {

	public static void main(String[] args) {
//		A  a = new A(); //抽象类无法实例化
//		a.fun();
//		a.print();
		B b = new B();
		b.fun();
		b.print();
		//抽象类的对象实例化需要依靠子类完成，采用向上转型的方法处理
		A a = new B();
		a.print();

		System.out.println();
		//
		A2.B2 ab = new X();//向上转型
		ab.print();
		//
		System.out.println();
		A3.print();
	}

}

