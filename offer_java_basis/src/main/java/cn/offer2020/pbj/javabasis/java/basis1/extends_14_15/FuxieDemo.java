package cn.offer2020.pbj.javabasis.java.basis1.extends_14_15;
/*
 * 覆写
 * 需要考虑权限问题：被子类覆写的方法不能拥有比父类更加严格的访问控制权限
 * public > default > private,大多数的方法使用public声明
 * 当父类使用private声明时，对于子类不可见，无法调用，可以使用super.方法()来进行访问
 * this.方法() 和super.方法()的区别，
 * this.方法()会首先查找本类中是否查找可以调用的方法名称，操作则调用，
 * 不存在则查找父类中是否查找，查找则调用，不存在则出现编译出错
 * super.方法()：直接查找父类中是否查找，查找则调用，不存在则出现编译出错
 * 重载（Overloading）和覆写（Overrid）的区别
 * Overloading发生在一个类中、方法名称相同，方法的类型和参数个数不同，没有权限的限制
 * Override发生在继承关系中，方法名称，参数类型，个数相同，方法返回值相同，子类不能有比父类更加严格的权限限制
 */
class A2{
	String info = "Hello";
	public void fun () {
		System.out.println("A,A类的无参构造方法");
	}
}
class B2 extends A2{
	String info = "100";
	public void fun() {//此处为覆写,子类覆盖了父类的方法
		System.out.println("B,B类的无参构造方法");
		System.out.println("----属性的覆盖----");
		System.out.println(super.info);//Hello
		System.out.println(this.info);//100
	}
}

public class FuxieDemo {

	public static void main(String[] args) {
		B2 b = new B2();
		b.fun();
	}

}
