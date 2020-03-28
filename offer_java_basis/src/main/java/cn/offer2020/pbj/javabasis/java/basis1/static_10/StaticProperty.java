package cn.offer2020.pbj.javabasis.java.basis1.static_10;
/*
 * 一旦属性定义上使用了static之后，只要一个对象修改了属性的内容，那么所有的对象的static属性的内容都将一起修改，
 * 也就是说static属性变成了一个公共属性，是全局数据区
 */
class Book {
	private String title;
	private double price;
	//	String pub = "清华大学出版社";
	static String pub = "清华大学出版社";
	public Book(String title, double price) {
		this.title = title;// this调用属性
		this.price = price;
	}

	public String getInfo() {
		return "Titile:" + this.title + " Price:" + this.price+" Public:"+this.pub;
	}
}
public class StaticProperty {

	public static void main(String[] args) {
		Book ba = new Book("Java",99.0);
		Book bb = new Book("JSP",99.0);
		Book bc = new Book("J",99.0);
		System.out.println(ba.getInfo());
		System.out.println(bb.getInfo());
		System.out.println(bc.getInfo());
		ba.pub = "北京大学出版社";
		System.out.println(ba.getInfo());
		System.out.println(bb.getInfo());
		System.out.println(bc.getInfo());
	}

}

