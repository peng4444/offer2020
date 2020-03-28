package cn.offer2020.pbj.javabasis.java.basis1.string_4_5;

public class StringDemo {

	public static void main(String[] args) {
		String stra = "hello";
		String strb = new String("hello");
		String strc = strb;//引用传递
		// == 是属于数值相等比较，在String类型中比较的是内存地址
		System.out.println(stra==strb);//false
		System.out.println(stra == strc);//false
		System.out.println(strb == strc);//true
		System.out.println();
		//equals方法收集专门进行字符串内容的比较
		System.out.println(stra.equals(strb));//true
		System.out.println(stra.equals(strc));//true
		System.out.println(strb.equals(strc));//true

		/*
		 * String实例化的两种方法区别
		 * 直接赋值：String str = "字符串"; ；只开辟一块堆内存空间，并且不会产生空间垃圾，并且自动保存在对象池中重复使用
		 * 构造方法：String str = new String ("字符串");:开辟两块内存空间，其中一块会变为垃圾，
		 * 并且不会自动入池，但是用户可以使用intern()方法手工入池
		 * String类型字符串内容不可变
		 */
		String str1 = "abc";
		String str2 = new String ("cba");
		String str3 = new String("abc").intern();
		System.out.println(str1==str3);//true
	}

}
