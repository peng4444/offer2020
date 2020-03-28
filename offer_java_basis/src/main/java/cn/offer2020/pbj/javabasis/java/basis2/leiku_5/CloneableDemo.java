package cn.offer2020.pbj.javabasis.java.basis2.leiku_5;
/**
 * 对象克隆： protected Object clone() throws CloneNotSupportException
 * @author PBJ
 *
 */
class Book implements Cloneable{
	private String title;
	private double price;
	public Book(String title,double price) {
		this.title = title;
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
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

public class CloneableDemo {

	public static void main(String[] args) throws CloneNotSupportedException {
		Book book1 = new Book("java����",79.9);
		Book book2 = (Book) book1.clone();//��¡
		book2.setTitle("JSP");
		System.out.println(book1);
		System.out.println(book2);

	}

}
