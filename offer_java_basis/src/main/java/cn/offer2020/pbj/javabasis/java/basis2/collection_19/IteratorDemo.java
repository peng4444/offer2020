package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 集合输出 Iterator,ListIterator,Enumeration,for each ,Enumeration只有在Vector接口中使用
 * @author PBJ
 *public Iterator<E> iterator()  输出集合
 *双向迭代:ListIterator
 *判断是否有前一个元素 : public boolean hasPrevious()
 *取得前一个元素: public E previous()
 *
 */
public class IteratorDemo {

	public static void main(String[] args) {
		List<String> all = new ArrayList<String>();
		all.add("A");
		all.add("B");
		all.add("C");
		Iterator<String> iter = all.iterator();
		while(iter.hasNext()) {
			String str = iter.next();
			System.out.println(str);
		}
		System.out.println("ListIterator,双向输出");
		System.out.print("由前向后输出：");
		ListIterator<String> iter2 = all.listIterator();
		while(iter2.hasNext()) {
			String str = iter2.next();
			System.out.print(str+",");
		}
		System.out.print("\n由后向前输出：");
		while(iter2.hasPrevious()) {
			System.out.print(iter2.previous()+",");
		}
		System.out.println();
		System.out.println("--foreach输出--");
		for(String str : all) {
			System.out.print(str+"--");
		}
		
	}

}
