package cn.offer2020.pbj.javabasis.java.basis1.demoblock_11;

public class PutongBlock {

	public static void main(String[] args) {
		{
			//普通代码块
			int num = 10;//局部变量
			System.out.println("num = "+num);
		}
		int num = 100;//全局变量
		System.out.println("num = "+num);
	}

}
