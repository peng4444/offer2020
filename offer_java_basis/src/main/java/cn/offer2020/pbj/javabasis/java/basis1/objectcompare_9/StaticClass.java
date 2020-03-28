package cn.offer2020.pbj.javabasis.java.basis1.objectcompare_9;

class Book2 {
	private String title;
	private double price;
	private static String pub = "清华大学出版社";
	public Book2(String title, double price) {
		this.title = title;// this调用属性
		this.price = price;
	}
	public static void setPub(String p) {
		pub = p;
	}
	public String getInfo() {
		return "Titile:" + this.title + " Price:" + this.price+" Public:"+this.pub;
	}
}

public class StaticClass {

	public static void main(String[] args) {
		//static方法可以不需要创建对象就可以调用
		Book2.setPub("北京大学出版社");
		Book2 ba = new Book2("Java",99.0);
		Book2 bb = new Book2("JSP",99.0);
		Book2 bc = new Book2("J",99.0);
		System.out.println(ba.getInfo());
		System.out.println(bb.getInfo());
		System.out.println(bc.getInfo());
	}

}
