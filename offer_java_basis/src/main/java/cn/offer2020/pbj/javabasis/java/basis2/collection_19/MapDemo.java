package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Collection每次只保存一个对象，而Map主要负责保存一对对象的信息
 *   子类 ：HashMap(子类：LinkedHashMap), HashTable(子类：Properties), TreeMap
 *   HashTable的key和value不允许为空
 *   HashMap和Hashtable的区别？
 *   HashMap较晚推出JDK1.2，采用异步处理，非线程安全，在多线程并发的环境下，使用HashMap时就必须要自己增加同步处理，key有序，key value 允许为空
 *   HashTable在JDK1.0推出,采用同步处理，线程安全，每个方法中都加入了Synchronize方法，key无序，key value都不允许为空
  *      初始容量和扩充容量不同，hash值算法不同
 *   JDK1.8 HashMap引入了红黑树，链表超过最大长度(8)，将链表改为红黑树再添加元素
 *	  一般使用HashMap
 *	  如果你不需要线程安全，那么使用HashMap，如果需要线程安全，那么使用ConcurrentHashMap。
 *	 HashTable已经被淘汰了，不要在新的代码中再使用它。
 * @author PBJ
 * 	public V put(K key,V value)  向集合中保存数据
 * 	public V get(Object key)     根据key查找对应的value数据
 * 	public set<Map.Entry<K,V>> entrySet() 将Map集合转换成Set集合
 * 	public Set<K> keySet()  取出全部的key, 
 * 	Map存放数据的目的是查找，而Collection存放数据的目的是为了输出
 */
public class MapDemo {

	public static void main(String[] args) {
		Map <String,Integer>  map = new HashMap<String,Integer>();
		map.put("一", 1);
		map.put("二", 2);
		map.put("三", 3);
		map.put("三", 33);
		map.put(null, 4);
		map.put(null, null);//HashMap key value 允许为空
		/*
		 * HashMap : key-value 无序，且不重复,新内容会进行覆盖
		 */
		System.out.println(map);
		System.out.println(map.get("三"));
		System.out.println(map.get(null));
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next()+"--");
		}
		System.out.println();
		Map <String,Integer>  map2 = new Hashtable<String,Integer>();//key无序
		map2.put("A", 1);
		map2.put("B", 2);
		map2.put("C", 3);
		map2.put("B", 4);
//		map2.put("D", null); /Hashtable key value都不允许为空
		System.out.println(map2);
		System.out.println();
		Map <String,Integer>  map3 = new TreeMap<String,Integer>();//key有序
		map3.put("A", 1);
		map3.put("B", 2);
		map3.put("C", 3);
		map3.put("B", 4);
		System.out.println("TreeMap"+map3);
		System.out.println();
		Map <String,Integer>  map4 = new LinkedHashMap<String,Integer>();//key有序
		map4.put("A", 1);
		map4.put("B", 2);
		map4.put("C", 3);
		map4.put("B", 4);
		System.out.println(map4);
		System.out.println();
		Map <String,Integer>  map5 = new HashMap<String,Integer>();//key有序，迭代HashMap的顺序并不是HashMap放置的顺序，也就是无序。
		map5.put("A", 1);
		map5.put("B", 2);
		map5.put("C", 3);
		map5.put("B", 4);
		map5.put("E", 3);
		map5.put("D", 4);
		System.out.println(map5);
		System.out.println();
		Map <String,Integer>  map6 = new ConcurrentHashMap<String,Integer>();//key有序，是插入顺序或者是访问顺序。通过维护一个双向链表实现。
		map6.put("A", 1);
		map6.put("B", 2);
		map6.put("C", 3);
		map6.put("B", 4);
		map6.put("E", 3);
		map6.put("D", 4);
		System.out.println(map6);
	}
}
