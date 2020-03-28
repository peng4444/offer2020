package cn.offer2020.pbj.javabasis.java.basis1.demoblock_11;
/*
 * 静态块优先于构造块执行，并且不管多少个实例化对象，静态块只执行一次
 * 主要功能是为类中的static属性初始化
 */
class Book2{
	static String msg;
	public Book2() {//构造方法
		System.out.println("【A】Book类的构造方法");
	}
	{//构造块
		System.out.println("【B】Book类的构造块");
	}
	static{//静态块
		msg = "hello";
		System.out.println("【C】Book类的构造块");
	}
}

public class StaticBlock {

	public static void main(String[] args) {
		new Book2();
		new Book2();
		new Book2();
		System.out.println(Book2.msg);
	}

}
