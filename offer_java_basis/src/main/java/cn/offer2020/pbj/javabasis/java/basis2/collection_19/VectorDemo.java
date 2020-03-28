package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
/*
 * ArrayList和Vector的区别
 * ArrayList较晚推出JDK1.2，采用异步处理，非线程安全，输出有Iterator,ListIterator,Foreach
 * Vector在JDK1.0推出,采用同步处理，线程安全，输出多一个Enumeration
 * 一般使用ArrayList
 */
public class VectorDemo {

	public static void main(String[] args) {
		List<String> all = new Vector<String>();
		System.out.println("长度："+all.size()+"，算法为空："+all.isEmpty());
		all.add("Hello");
		all.add("Hello");
		all.add(" World");
		System.out.println("长度："+all.size()+"，算法为空："+all.isEmpty());
		for(int x =0;x<all.size();x++) {
			String str =all.get(x);
			System.out.println(str);
		}
		System.out.println();
		System.out.println("--Enumeration输出--");
		Vector<String> all2 = new Vector<String>();
		all2.add("A");
		all2.add("C");
		all2.add("B");
		Enumeration<String> enu = all2.elements();
		while(enu.hasMoreElements()) {
			String str = enu.nextElement();
			System.out.println(str);
		}
	}

}
