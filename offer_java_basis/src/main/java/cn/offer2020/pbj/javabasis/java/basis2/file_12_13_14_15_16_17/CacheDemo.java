package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CacheDemo {
	/*
	 * 缓冲区流
	 * 缓冲输入流：
	 * 字符缓冲区流：BufferedReader，BufferedWriter
	 * 字节缓冲区流：BufferedInputStream,BufferedOutputStream
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		boolean flag = true;
		while(flag) {
			System.out.println("请输入年龄：");
			String str = buf.readLine();
			if(str.matches("\\d{1,3}")) {
				System.out.println("年龄是："+Integer.parseInt(str));
				flag = false;
			}else {
				System.out.println("年龄输入错误，应该由数字组成");
			}
		}
	}

}
