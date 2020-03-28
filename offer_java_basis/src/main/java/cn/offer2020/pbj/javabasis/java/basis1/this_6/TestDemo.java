package cn.offer2020.pbj.javabasis.java.basis1.this_6;

class A{
	private B b;
	public A() {//2
		//3
		this.b = new B(this);//4
		this.b.get();//7
	}
	public void print() {//10
		System.out.println("Hello World");
	}
}
class B{
	private A a;
	public B(A a) {//5
		this.a = a;//6
	}
	public void get() {//8
		this.a.print();//9
	}
}
public class TestDemo {

	public static void main(String[] args) {
		A temp = new A();//1
	}

}
