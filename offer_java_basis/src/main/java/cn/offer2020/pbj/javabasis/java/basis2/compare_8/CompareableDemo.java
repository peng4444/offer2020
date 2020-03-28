package cn.offer2020.pbj.javabasis.java.basis2.compare_8;

import java.util.Arrays;

/**
 * 比较器Comparable
 * 对象数组排序：public static void sort(Object[] a)
 * @author PBJ
 *
 */
class Book implements Comparable<Book>{
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
		this.price = price;
	}
	public Book(String title,double price) {
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}
	@Override
	public int compareTo(Book o) {
		if(this.price>o.price) {
			return 1;
		}else if(this.price<o.price) {
			return -1;
		}else {
		return 0;
		}
	}
}

public class CompareableDemo {

	public static void main(String[] args) {
		Book books [] = new Book[] {
				new Book("Java����",79.8),
				new Book("JSP����",59.8),
				new Book("Oracle����",56.8),
				new Book("�Լ�����",0.0)
		};
		Arrays.sort(books);
		System.out.println(Arrays.toString(books));
	}

}
