package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionDemo {

	public static void main(String[] args) {
		/**
		 * Collection接口基本不用，单值保存的集合
		 * public boolean add(E e) 向集合里面保存数据
		 * public boolean addAll(Collection<? extends E> c) 追加一个集合
		 * public void clear() 清空集合
		 * public boolean contains (Object obj) 判断是否包含指定的内容，需要equals() 的支持
		 * public boolean isEmpty() 判断是否是空集
		 * public boolean remove(Object o) 删除对象，需要equals支持
		 * public int sze() 取得集合中的保存的元素个数
		 * public Object[] toArray() 将集合变成对象数组保存
		 * public Iterator<E> itorator() 为Iterator接口 实例化
		 */
		Collection<String> all = new ArrayList<String>();
		all.add("Hello");
		all.add("Hello");
		all.add(" World");
		System.out.println(all.size());
		System.out.println(all.isEmpty());
		System.out.println(all.contains("World"));
		System.out.println();
		Object obj[]= all.toArray();
		for(int x =0;x<all.size();x++) {
			System.out.print(obj[x]+" -- ");
		}
		
		System.out.println();
		all.remove("Hello");
		System.out.println("-------Iterator输出---------");
		Iterator<String> iter = all.iterator();
		while(iter.hasNext()) {
			String str = iter.next();
			System.out.print(str+" -- ");
		}
		Collection<String> all2 = new ArrayList<String>();
		System.out.println("--判断remove的顺序--");
		all2.add("A");
		all2.add("B");
		all2.add("C");
		all2.add("C");
		all2.add("B");
		all2.add("A");
		Iterator<String> iter2 = all2.iterator();
		while(iter2.hasNext()) {
			String str = iter2.next();
			System.out.print(str+" -- ");
		}
		System.out.println();
		all2.remove("B");//remove 先进先出，类似于队列，
		System.out.println("--remove之后的顺序--");
		Iterator<String> iter3 = all2.iterator();
		while(iter3.hasNext()) {
			String str = iter3.next();
			System.out.print(str+" -- ");
		}
		System.out.println();
		System.out.println("--Iterator remove之后--");
		iter3.remove();//Iterator的remove()方法,去除最后一个元素
		System.out.println(all2);
		System.out.println();
		System.out.println(all2.retainAll(all));//true 从列表all2中移除未包含在指定all中的所有元素
		System.out.println("--retainAll之后--");
		//由于all2 和all没有交集，所有all2为空，all不变
		Iterator<String> iter4 = all2.iterator();
		System.out.println(iter4.hasNext());//false all2为空
		Iterator<String> iter5 = all.iterator();
		while(iter5.hasNext()) {
			String str = iter5.next();
			System.out.print(str+" -- ");//Hello --  World -- 
		}
		System.out.println();
		System.out.println(all2.containsAll(all));//false all2不包含all
		System.out.println(all2.removeAll(all));//false  all2中无法删除所有的all
		System.out.println(all2.addAll(all)); //true  all全部加入all2
		//all 就和all2 有相同的元素     
		//无法从列表all2中移除未包含在指定all中的所有元素
		System.out.println(all2.retainAll(all));//false	
	}
	
}
