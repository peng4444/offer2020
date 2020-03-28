package cn.offer2020.pbj.javabasis.java.basis1.java5_27;
//Java5新特性，可变参数
public class TestDemo {
	public static void main(String[] args) {
		//可变参数支持接收数组
		System.out.println(add(new int [] {1,2,3,4,5,6,7,8,9}));
		System.out.println(add(new int [] {2,3}));
		System.out.println(add(1,2,3));
		System.out.println(add(2,3));
		System.out.println(add(100));
	}
	/**
	 * 实现任意多个整数数据相加操作,(相同数据类型，多个参数)
	 * @param data 由于要接收多个整数，所有使用数组完成接收
	 * @return 多个整数数据的累加结果
	 */
	private static int add(int ... data) {
		int sum = 0;
		for(int x = 0;x< data.length;x++) {
			sum = sum + data[x];
		}
		return sum;
	}
}
