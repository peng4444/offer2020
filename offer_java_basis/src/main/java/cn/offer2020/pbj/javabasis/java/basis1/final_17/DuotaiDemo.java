package cn.offer2020.pbj.javabasis.java.basis1.final_17;

/*
 * 多态性：
 * 方法的多态性：
 * 	方法的重载：
 * 	方法的覆写：
 * 对象的多态性：
 * 	向上转型（自动完成）：父类 父类对象 = 子类实例；
 * 	向下转型（强制完成）：子类 子类对象 = （子类） 父类实例；
 */
class A4{
	public void print() {
		System.out.println("A,A类的print方法");
	}
}
class B4 extends A4{
	public void print() {
		System.out.println("B,B类的print方法");
	}
}
public class DuotaiDemo {

	public static void main(String[] args) {
		//向上转型：由于所有的子类对象实例都可以自动的向上转型，所以在于参数的统一
		//即：同一个方法针对于不同的子类可以有不同的实现
		A4 a = new B4();//向上转型
		a.print();
		System.out.println();
		//向下转型：将父类对象转换为子类对象，这样就可以使用子类的特殊功能
		B4 b = (B4) a;//向上转型
		b.print();
	}

}
