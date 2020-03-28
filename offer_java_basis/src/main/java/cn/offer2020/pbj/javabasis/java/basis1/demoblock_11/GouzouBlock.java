package cn.offer2020.pbj.javabasis.java.basis1.demoblock_11;
/*
 * 构造块的调用先与构造方法的执行，调用多个对象是构造块将执行多次
 */
class Book{
	public Book() {//构造方法
		System.out.println("【A】Book类的构造方法");
	}
	{//构造块
		System.out.println("【B】Book类的构造块");
	}
}
public class GouzouBlock {

	public static void main(String[] args) {
		new Book();
		new Book();
		new Book();
	}

}

