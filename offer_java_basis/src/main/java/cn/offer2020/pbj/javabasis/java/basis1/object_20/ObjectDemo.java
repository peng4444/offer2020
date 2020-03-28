package cn.offer2020.pbj.javabasis.java.basis1.object_20;

class Book extends Object{}
public class ObjectDemo {
	/*
	 * Object类是所有类的父类，要定义一个无参构造，子类在对象实例化的时候默认调用父类的无参构造
	 */
	public static void main(String[] args) {
		Object obja = new Book();//向上转型
		Object objb = new Book();//向上转型
		Book b = (Book) obja;//向下转型
//		String s = (String) objb;//向下转型
		//取得对象：public string toString(),一般输出需要覆写toString方法
		Book bk = new Book();
		String sk = "Hello";
		System.out.println(bk);
		System.out.println(bk.toString());
		System.out.println(sk);
		//Object类equals()方法在对象比较时调用
		//....
		//Object 类 接收引用类型
		//Object类接收接口对象
	}

}

