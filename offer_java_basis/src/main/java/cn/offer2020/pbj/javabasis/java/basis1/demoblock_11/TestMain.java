package cn.offer2020.pbj.javabasis.java.basis1.demoblock_11;
//通过 static代码块，跳过main方法

public class TestMain {
	static {
		System.out.println("Hello World");
		System.exit(0);
	}
	/**
	 * 主方法被跳过
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("main...........");
	}

}
