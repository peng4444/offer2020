package cn.offer2020.pbj.javabasis.java.basis2.compare_8;

import java.util.Arrays;
import java.util.Comparator;

class Book2 implements Comparable<Book2>{
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
	public Book2(String title,double price) {
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}
	@Override
	public int compareTo(Book2 o) {
		if(this.price>o.price) {
			return 1;
		}else if(this.price<o.price) {
			return -1;
		}else {
		return 0;
		}
	}
}

/*public interface Comparator<T>{
	public int compare(T o1,T o2);
	public boolean equals(Object obj);
}*/
class BookComparator implements Comparator<Book2>{

	@Override
	public int compare(Book2 o1, Book2 o2) {
		if(o1.getPrice() > o2.getPrice()) {
			return 1;
		}else if(o1.getPrice() < o2.getPrice()){
			return -1;
		}else {
		return 0;
		}
	}
	
}

public class ComparatorDemo {

	public static void main(String[] args) {
		Book2 books [] = new Book2[] {
				new Book2("Java����",88.9),
				new Book2("Python����ѧϰ",189.1),
				new Book2("Oracle���ݿ�",129.0),
				new Book2("�Լ�",120000)
		};
		Arrays.sort(books,new BookComparator());
		System.out.println(Arrays.toString(books));
	}

}
