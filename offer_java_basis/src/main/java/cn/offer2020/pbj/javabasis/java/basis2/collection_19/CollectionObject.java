package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.ArrayList;
import java.util.List;
/*
 * 在集合里面保存对象
 */
class Book {
	private String title;
	private double price;
	public Book(String title,double price) {
		this.title = title;
		this.price = price;
	}
	public boolean equalsObject(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book book = (Book) obj;
		if (this.title.equals(book.title) && this.price == book.price) {
			return false;
		}
		return false;
	}
	public String toString() {
		return "书名："+this.title+" 价格："+this.price+"\n";
	}
}

public class CollectionObject {

	public static void main(String[] args) {
		List<Book> all = new ArrayList<Book>();
		all.add(new Book("Java",99.9));
		all.add(new Book("Java",99.0));
		all.add(new Book("MySQL",99.9));
		System.out.println(all);
		all.remove(1);
		System.out.println(all);
		all.set(1, new Book("Java",99.0));//set只能在已经存在的索引里面设置，不能越界
		System.out.println(all);
	}

}
