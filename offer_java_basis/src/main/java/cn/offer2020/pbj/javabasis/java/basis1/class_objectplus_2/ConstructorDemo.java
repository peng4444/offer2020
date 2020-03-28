package cn.offer2020.pbj.javabasis.java.basis1.class_objectplus_2;
/*
 * 构造方法与普通方法的区别
 * 构造方法是 在实例化新对象（new）的时候只调用一次
 * 普通方法时在实例化对象之后可以随意的使用
 * 一个类中至少保留一个个构造方法,多个构造方法会进行构造方法的重载
 */
class Book2{
	private String title;
	private double price;
	//默认构造方法
	public Book2() {}
	//明确定义一个构造方法
	public Book2(String t,double p) {
		title = t;
		price = p;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if(price>0) {//封装之后可以在setter方法加限制条件
			this.price = price;
		}
	}
	public void getInfo() {
		System.out.println("Titile:"+title+" Price:"+price);
	}
}

public class ConstructorDemo {

	public static void main(String[] args) {
		Book2 bk = new Book2();
		bk.setTitle("Java");
		bk.setPrice(88.8);
		bk.getInfo();
		System.out.println("-----------");
		Book2 bk2 = new Book2("MySQL",66.6);
		bk2.getInfo();
		System.out.println("-----------");
		new Book2("Linux",77.7).getInfo();//匿名对象，没有进行其他对象的引用，所以只能使用一次，之后会被GC
	}

}

