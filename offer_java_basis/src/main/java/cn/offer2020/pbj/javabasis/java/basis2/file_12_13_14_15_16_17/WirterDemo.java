package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
/**
 * 字节流和字符流是区别:字节流直接与终端进行交互，字符流需要将数据仅供缓冲器处理后来才输出
 * OutputStream即使没有关闭输出流，也可以正常输出，但是Writer输出流没有关闭，代表缓冲区中的内容不会清空，
 * 不能输出。可以使用flush()方法强制清空缓冲区
 * 处理中文的时候，请优先考虑字符流，没有中文问题，建议使用字节流
 * @author PBJ
 *
 */
public class WirterDemo {

	public static void main(String[] args) throws IOException {
		/**
		 * 字符输出流：Writer
		 * 输出全部支符数组:public void write(char [] cbuf)throws IOExeption
		 * 输出字符串: public void write (String str) throws IOException
		 */
		File file = new File("f:"+File.separator+"demo"+File.separator+"test.txt");
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		Writer out  = new FileWriter(file);
		String str = "\n努力,加油。";
		out.write(str);
		String str2 = "追加的。。。";
		out.append(str2 );
		out.flush();
		out.close();
		//字符输入流： Reader
		File file2 = new File("f:"+File.separator+"demo"+File.separator+"test.txt");
		if(file2.exists()) {
			Reader in = new FileReader(file2);
			char data[] = new char[1024];
			int len = in.read(data);
			in.close();
			System.out.println(new String(data,0,len));
		}
	}

}
