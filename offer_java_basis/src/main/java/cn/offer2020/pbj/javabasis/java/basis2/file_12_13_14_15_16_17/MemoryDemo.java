package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MemoryDemo {
	/*
	 * 内存流
	 * 字节内存流： ByteArrayInputStream BytearrayOutputStream
	 * 字符内存流：CharArrayReader CharArrayWriter
	 */
	public static void main(String[] args) throws IOException {
		//利用ByteArrayOutputstream实现多个文件的同时读取
		File file1 = new File("f:"+File.separator+"my1.txt");
		File file2 = new File("f:"+File.separator+"my2.txt");
		InputStream inputA = new FileInputStream(file1);
		InputStream inputB = new FileInputStream(file2); 
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int temp = 0;
		while((temp = inputA.read())!=-1) {
			output.write(temp);
		}
		while((temp = inputB.read())!=-1) {
			output.write(temp);
		}
		byte data [] = output.toByteArray();
		output.close();
		inputA.close();
		inputB.close();
		System.out.println(new String(data));
	}

}
