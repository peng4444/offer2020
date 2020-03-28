package cn.offer2020.pbj.javabasis.java.basis2.collection_19;


import java.util.*;

/**
 * Set接口常用子类 HashSet,TreeSet    不可重复  
 * 无重复元素（只判断，无法验证），HashSet无序，TreeSet自动排序,默认从大到小
 * LinkedHashSet：是HashSet的子类，无序，不可重复
 * @author PBJ
 * 参考资料：https://blog.csdn.net/from_heat/article/details/84306793
 */
public class SetDemo {

	public static void main(String[] args) {
		System.out.println("--HashSet--");
		Set<String> all = new HashSet<String>();
		all.add("Hello");
		all.add("Hello");
		all.add(" World");
		System.out.println(all);
		System.out.println();
		/*HashSet Iterator*/
		Iterator<String> it1 = all.iterator();
		while (it1.hasNext()) {
			System.out.println(it1.next());
		}
		System.out.println("--TreeSet--");
		Set<String> all2 = new TreeSet<String>();
		all2.add("X");
		all2.add("B");
		all2.add("C");
		all2.add("A");
		all2.add("B");
		System.out.println(all2);
		for (String treeset : all2) {
			System.out.println(treeset);
		}
		System.out.println("--LinkedHashSet--");
		Set<String> all3 = new LinkedHashSet<String>();
		all3.add("X");
		all3.add("S");
		all3.add("O");
		all3.add("Z");
		all3.add("O");
		all3.add("S");
		System.out.println(all3);
	}

}
