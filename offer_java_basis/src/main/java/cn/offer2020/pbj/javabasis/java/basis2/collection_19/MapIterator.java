package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapIterator {

	public static void main(String[] args) {
		/*
		 * Map集合利用Iterator接口输出的步骤
		 * 1.利用Map接口的entrySet()方法将Map集合变为set集合，里面的泛型是Map.Entry
		 * 2.利用set集合中的iterator()方法将Set集合进行Iterator输出
		 * public Set<Map.Entry<K,V>> entrySet()
		 */
		Map <String,Integer>  map = new Hashtable<String,Integer>();
		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);
		map.put("B", 4);
		//利用Map接口的entrySet()方法将Map集合变为set集合
		Set<Map.Entry<String,Integer>> set = map.entrySet();
		Iterator<Map.Entry<String,Integer>> iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry<String, Integer> me = iter.next();
			System.out.println(me.getKey()+"--->"+me.getValue());
		}
	}

}
