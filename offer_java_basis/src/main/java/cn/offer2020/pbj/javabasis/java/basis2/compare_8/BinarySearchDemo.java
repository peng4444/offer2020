package cn.offer2020.pbj.javabasis.java.basis2.compare_8;

import java.util.Arrays;

/**
 * Arrays类
 * 二分查找法：public static int binarySearch(数据类型[]a,数据类型 key)
 * 数组比较 public static boolean equals(数据类型[]a,数据类型 []b)
 * 填充数组 public static void fill(数据类型 []a,数据类型 val)
 * 将数组变为字符串输出：public static String toString(数据类型[]a)
 * @author PBJ
 *
 */
public class BinarySearchDemo {

	public static void main(String[] args) {
		int data[] = new int[] {1,5,3,2,6,7,4};
		Arrays.sort(data);
		System.out.println(Arrays.binarySearch(data, 7));
		int dataA[] = new int [] {1,2,3};
		int dataB[] = new int [] {1,2,3};
		System.out.println(Arrays.equals(dataA, dataB));
		int data2[] = new int[10];
		Arrays.fill(data2, 3);
		System.out.println(Arrays.toString(data2));
	}

}
