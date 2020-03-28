package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamDemo {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream pu = new PrintStream(new FileOutputStream(new File("f:"+File.separator+"my.txt")));
		pu.print("Hello");
		pu.println(" World");
		pu.println(1+1);
		pu.println(1.1+1.2);
		//格式化输出:public PrintStream printf(String format, Object... args);
		String name = "aaa";
		int age = 19;
		double score = 90;
		PrintStream pu2 = new PrintStream(new FileOutputStream(new File("f:"+File.separator+"my.txt")));
		pu2.printf("姓名：%s,年龄：%d,成绩：%5.2f", name,age,score);
		//JDK1.5以后可以格式化字符串输出
		pu2.close();
		pu.close();
	}

}
