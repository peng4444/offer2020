package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamDemo {

	public static void main(String[] args) throws IOException {
		/**
		 * 输出单个字节：public abstract void write(int b)throws IOExeption;
		 * 输出全部字节数组：public void write(byte[] b )throws IOException;
		 * 输出部分字节数组: public void write(byte[] b,int off,int len)throws IOException
		 * 创建或者覆盖已有文件:public FileOutputStream(File file)throws FileNotFoundException
		 * 文件内容追加: public FileOutputStream(File file,boolean append) throws FileNotFoundException
		 */
		//1.定义文件要输出的路径
		File file = new File("f:"+File.separator+"demo"+File.separator+"test.txt");
		// 假如文件目录不存在，创建
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		//2.应该使用OutputStream和其子类进行对象的实例化，此事目录存在，文件不存在
		OutputStream output = new FileOutputStream(file);

//		OutputStream output1 = new FileOutputStream(file,true); //FileOutputStream实现文件内容追加
		//3.要进行文件内容的输出
		String str = "好好学习，天天向上。";
		/*
		 * OutputStream里面的输出操作
		 */
		byte data [] = str.getBytes();
		//1
		output.write(data);
		//output.write(data,6,6);//部分输出
//		output1.write(data);//内容输出   （三种方式）
		//采用单字节数组输出
		for(int x = 0;x<data.length;x++) {
			output.write(data[x]);
		}
		output.close();
	}

}
