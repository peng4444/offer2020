package cn.offer2020.pbj.javabasis.java.basis1.class_object_1;
/**
 * Java中的堆内存栈内存
 * @author PBJ
 * 数据结构的栈和堆
 * 栈是后进先出性质的数据结构,堆是一种经过排序的树形数据结构，每个结点都有一个值
 */
public class HeadStack {
	/*
	 * 堆内存：保存每一个对象的属性内容，堆内存需要用关键字new才可以开辟
	 * 栈内存：保存的是一块堆内存的地址，但是为了分析方便，可以简单的理解栈内存保存的是对象名字
	 */
	public static void main(String[] args) {
		int a = 1;
		int b = 1;
		a = 2;
		System.out.println(a);//2
		System.out.println(b);//1

		TT t = new TT("T");
		TT t1 = t;
		t.setName("TT");

		System.out.println(t.getName());//TT
		System.out.println(t1.getName());//TT
	}
}

class TT {
	private String name;

	public TT(String name) {
		this.name = name;
	}

	public void setName(String name1) {
	       this.name = name1;
	}

	public String getName() {
		return this.name;
	}
}
