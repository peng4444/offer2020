package cn.offer2020.pbj.javabasis.java.basis1.this_6;

/*
 * this关键字可以实现：类属性的调用，类方法的调用，表示当前对象
 * 使用this（） 调用构造方法形式的代码只能够放在构造方法的首行
 * 进行构造方法的互相调用时，一定要留下一个出口，即：至少有一个构造方法没有被this（）调用其他构造方法
 */
public class ThisDemo {

	public static void main(String[] args) {
		Book bk = new Book("Java",88.8);
		bk.getInfo();

	}

}
class Book{
	private String title;
	private double price;
	public Book(String title,double price) {
		this.title = title;//this调用属性
		this.price = price;
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
	public void print() {
		System.out.println("**********");
	}
	public void getInfo() {
		this.print();//this调用本类方法
		System.out.println("Titile:"+title+" Price:"+price);
	}
}
