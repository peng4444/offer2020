package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.HashMap;
import java.util.Map;
/*
 * 使用自定定义的key
 * 在使用Map集合的时候，尽量使String定义key，不要使用自定义的类型去作为key
 */
class Book3 {
	private String title;

	public Book3(String title) {
		this.title = title;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book3 book = (Book3) obj;
		if (title == null) {
			if (book.title != null) {
				return false;
			}else if(! title.equals(book.title)) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return "书名：" + this.title;
	}
}

public class Mapkey {

	public static void main(String[] args) {
		Map<Book3,String> map = new HashMap<Book3,String>();
		map.put(new Book3("Java Book"), new String("Java"));
		System.out.println(map.get(new Book3("Java Book")));
	}

}
