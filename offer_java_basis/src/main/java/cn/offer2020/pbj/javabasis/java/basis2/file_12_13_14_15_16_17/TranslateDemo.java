package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class TranslateDemo {
	/*
	 * 转换流：InputStreamReader,OutputStreamWriter
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("f:"+File.separator+"my.txt");
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream output = new FileOutputStream(file);
		//将OutputStream类对象传递给OutputStreamWriter类的构造方法，然后向上转型为Writer
		Writer out = new OutputStreamWriter(output);
		out.write("Hello World");
		out.flush();
		out.close();
		output.close();
	}

}
