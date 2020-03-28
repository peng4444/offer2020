package cn.offer2020.pbj.javabasis.java.basis1.reference_7;

public class TestDemo2 {
	//String类对象的内容一旦声明则不可改变，对象内容的改变依靠的是引用地址的改变
	public static void main(String[] args) {
		String msg = "Hello";
		fun(msg);
		System.out.println(msg);
	}
	public static void fun(String temp) {
		temp = "World";
	}
}
