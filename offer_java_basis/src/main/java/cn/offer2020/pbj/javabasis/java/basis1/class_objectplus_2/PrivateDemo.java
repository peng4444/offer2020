package cn.offer2020.pbj.javabasis.java.basis1.class_objectplus_2;

class Book{
	private String title;
	private double price;
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

public class PrivateDemo {

	public static void main(String[] args) {
		Book bk = new Book();
		bk.setTitle("Java");
		bk.setPrice(88.8);
		bk.getInfo();
	}

}
