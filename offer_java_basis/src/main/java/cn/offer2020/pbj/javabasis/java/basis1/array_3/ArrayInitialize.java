package cn.offer2020.pbj.javabasis.java.basis1.array_3;

public class ArrayInitialize {

	public static void main(String[] args) {
		/*
		 * 声明并且开辟数组 --动态初始化
		 * 数据类型 数组名称 [] = new 数据类型[长度];
		 * 数据类型 [] 数据名称 = new 数据类型[长度];
		 *	分步完成：
		 *	声明数组 ： 数据类型 数组名称 [] = null;
		 *	开辟数组： 数组名称 = new 数据类型[长度];
		 */
		int data[] = new int [3];
		int data2[] = null;
		data2 = new int [4];
		data[0] = 10;
		data[1] = 20;
		data[2] = 30;
		for(int i = 0;i< data.length;i++) {
			System.out.print(data[i] + "  ");
		}
		//数组属于引用类型，所以可以发生引用传递
		int temp[] = data;
		temp[0]  = 0;
		for(int i = 0;i< data.length;i++) {
			System.out.print(data[i]+"  ");
		}
		/*
		 * 一维数组静态初始化
		 * 数据类型 数据名称 [] = {1,2,3};
		 * 数据类型 数据名称 [] = new 数据类型[] {1,2,3};
		 */
		int data3[] = new int [] {1,2,3};
		for(int i = 0;i< data3.length;i++) {
			System.out.print(data3[i]+"  ");
		}
	}

}

