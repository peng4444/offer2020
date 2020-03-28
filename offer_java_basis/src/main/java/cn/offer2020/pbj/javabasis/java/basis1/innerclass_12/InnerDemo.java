package cn.offer2020.pbj.javabasis.java.basis1.innerclass_12;
/*
 * 内部类就是在一个类的内部继续定义一个其他内部结构类的情况
 * 内部类的最大优点是：可以方便的访问外部类的私有操作
 * 反之，外部类也可以通过内部类对象访问内部类的私有属性
 * 使用static定义内部类，一定不可能受到外部类的实例化控制，相当于一个外部类
 * 内部类可以在方法中定义
 */
class Outer{//外部类
	private String msg = "Hello";
	//	class Inner{//内部类
//		public void print() {
//			System.out.println(msg);
//		}
//	}
	public void fun() {
		//实例化内部类对象，并且调用print() 方法
		new Inner(this).print();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
class Inner{//内部类
	private Outer out;
	public Inner(Outer out) {
		this.out = out;
	}
	public void print() {
		System.out.println(this.out.getMsg());
	}
}
public class InnerDemo {

	public static void main(String[] args) {
		Outer out = new Outer();//实例化外部类对象
//		Outer.Inner in = new Outer().new Inner();
		out.fun();//调用外部类方法

	}

}
