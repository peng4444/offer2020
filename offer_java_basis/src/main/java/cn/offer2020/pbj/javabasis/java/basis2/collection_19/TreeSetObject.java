package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.Set;
import java.util.TreeSet;

/*
 * 在集合里面比较对象大小，排序
 * 在非排序的情况下，只要是判断重复元素永远依靠的是hashCode()和equals()
 */
class Book2 implements Comparable<Book2>{
	private String title;
	private double price;
	public Book2(String title,double price) {
		this.title = title;
		this.price = price;
	}
	
	public String toString() {
		return "书名："+this.title+" 价格："+this.price+"\n";
	}
	
	public int compareTo(Book2 o) {
		if(this.price > o.price) {
			return 1;
		}else if(this.price  < o.price) {
			return 0;
		}else {
			return this.title.compareToIgnoreCase(o.title);
		}
	}
	
}
public class TreeSetObject {

	public static void main(String[] args) {
		Set<Book2> all = new TreeSet<Book2>();
		all.add(new Book2("Java",99.9));
		all.add(new Book2("Java",99.0));
		all.add(new Book2("MySQL",99.9));
		all.add(new Book2("Java",99.0));
		System.out.println(all);
	}

}
