package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * Collection :是集合操作接口
 * Collections : 是集合操作的工具类，可以进行 List Set Map集合的操作
 */
public class CollectionsTool {

	public static void main(String[] args) {
		List<String> all = new ArrayList<String>();
		Collections.addAll(all,"A","B","C","D");
		Collections.reverse(all);
		System.out.println(all);
	}

}
