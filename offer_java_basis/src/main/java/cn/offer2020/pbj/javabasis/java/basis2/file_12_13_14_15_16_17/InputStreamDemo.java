package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/*
 * 字节流：
 * 字节输入流：InputStream
 * 
 */
public class InputStreamDemo {

	public static void main(String[] args) throws IOException {
		/**
		 * 读取单个字节:public abstract int read() throws IOException
		 * 读取内容保存在字节数组中:public int read(byte[] b)throws IOExeption
		 * 读取内容部分保存在字节数组中:public int read(byte[] b,int off,int len)throws IOExeption
		 * 构造方法：public FileInputStream(File file) throws FileNotFoundException
		 */
		File file = new File("f:"+File.separator+"demo"+File.separator+"test.txt");
		if(file.exists()) {
			InputStream input = new FileInputStream(file);
			byte data[] = new byte[1024];
			int len = input.read(data);
			
			System.out.println(new String(data,0,len));
			/*
			 * 利用while循环读取
			 */
			int foot = 0;
			int temp = 0;
			while((temp = input.read())!=-1) {
				data[foot++] = (byte)temp;
			}
			input.close();
			System.out.println("【"+new String(data,0,foot)+"】");
		}
	}

}
