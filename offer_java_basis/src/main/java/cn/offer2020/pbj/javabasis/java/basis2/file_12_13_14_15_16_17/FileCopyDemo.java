package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyDemo {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		if(args.length !=2) {
			System.out.println("命令执行出错");
			System.exit(1);//查询退出
		}
		//输入正确
		File inFile = new File(args[1]);
		if(!inFile.exists()) {
			System.out.println("源文件不存在，请确认执行路径：");
			System.exit(1);
		}
		//源文件正确
		File outFile = new File(args[2]);
		if(!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		//实现文件内容的拷贝
		InputStream input = new FileInputStream(inFile);
		OutputStream output = new FileOutputStream(outFile);
		//实现文件拷贝
		int temp = 0;
		byte data [] = new byte [1024];
		while((temp = input.read(data))!=-1) {
			output.write(data,0,temp);
		}
		input.close();
		output.close();
		long end = System.currentTimeMillis();
		System.out.println("拷贝花费时间："+(end - start));
	}

	//实现文件复制
	public static void copyFile(String src, String dist) throws IOException {
		FileInputStream in = new FileInputStream(src);
		FileOutputStream out = new FileOutputStream(dist);
		byte[] buffer = new byte[20 * 1024];
		int cnt;
		// read() 最多读取 buffer.length 个字节
		// 返回的是实际读取的个数
		// 返回 -1 的时候表示读到 eof，即文件尾
		while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
			out.write(buffer, 0, cnt);
		}
		in.close();
		out.close();
	}
}
