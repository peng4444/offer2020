package cn.offer2020.pbj.javabasis.java.basis1.objectcompare_9;
/*
 * 对象比较一定是某一个类之间定义的功能
 * 对象比较时一定要判断是否为null，地址是否相同，属性是否相同
 */
class Book {
	private String title;
	private double price;

	public Book(String title, double price) {
		this.title = title;// this调用属性
		this.price = price;
	}

	//
	public boolean compare(Book book) {
		if(book==null) {
			return false;
		}
		if(this==book) {
			return true;
		}
		if(this.title.equals(book.title)&&this.price == book.price) {
			return true;
		}else {
			return false;
		}
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
		if (price > 0) {// 封装之后可以在setter方法加限制条件
			this.price = price;
		}
	}

	public void print() {
		System.out.println("**********");
	}

	public void getInfo() {
		this.print();// this调用本类方法
		System.out.println("Titile:" + title + " Price:" + price);
	}
}

public class TestDemo {

	public static void main(String[] args) {
		Book bk1 = new Book("Java",77.8);
		Book bk2 = new Book("java",77.8);
		if(bk1.compare(bk2)) {
			System.out.println("是同一个对象");
		}else {
			System.out.println("不是同一个对象");
		}
	}

}
