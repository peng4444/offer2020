package cn.offer2020.pbj.javabasis.java.basis1.extends_14_15;
/*
 * 继承实现
 * 继承的限制：
 * 1.java不允许多重继承，但是允许多层次继承
 * class A{} class B extends A{} class C extends A{}
 * 2.子类继承父类的时候，严格需要继承父类中的所有操作，但是对于所有的私有操作属于隐式继承。而所有的非私有操作属于显式继承
 * 3.在子类对象构造之前，一定会默认调用父类的构造，以保证父类对象先实例化，然后实例化子类对象。
 */
class Person{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
class Student extends Person{//继承Person的特性
	private String school;

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}
class A{
	//	public A() {
//		System.out.println("A,A类的无参构造方法");
//	}
	public A(String title) {
		System.out.println("A,A类的有参构造方法");
	}
}
class B extends A{
	public B(String title) {
		super(title);//主要是由子类调用父类的构造方法，必须放在子类构造方法的first line
//		super();//当父类有无参构造是加不加都一样
		System.out.println("B,B类的无参构造方法");

	}
}
public class ExtendsRealize {

	public static void main(String[] args) {
		Student stu = new Student();
		stu.setName("ssl");
		stu.setAge(18);
		stu.setSchool("NCAA");
		System.out.println("姓名："+stu.getName()+"年龄："+stu.getAge()+"学校："+stu.getSchool());
		System.out.println();
//		new B();//实例化子类对象
		new B("title");
	}

}

