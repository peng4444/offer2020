package cn.offer2020.pbj.javabasis.java.basis2.file_12_13_14_15_16_17;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class ScannerDemo {
	// 扫描流：Scanner
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader lineitem_br = new BufferedReader(new InputStreamReader(
				new FileInputStream("D:" + File.separator + "bigfile" + File.separator + "lineitem.tbl")));
		Scanner scan = new Scanner(
				new FileInputStream("D:" + File.separator + "bigfile" + File.separator + "orders.tbl"));
		scan.useDelimiter("\n");
		FileOutputStream output_fos = new FileOutputStream(// 输出文件位置
				new File("D:" + File.separator + "bigfile" + File.separator + "output2.tbl"));
		OutputStreamWriter output_osw = new OutputStreamWriter(output_fos, "UTF-8");
		BufferedWriter output_bw = new BufferedWriter(output_osw);
		String lineitem_line ="";
		while ((lineitem_line = lineitem_br.readLine()) != null) {
			
			while (scan.hasNextLine()) {
				String lineitem_line_1[] = lineitem_line.split("\\|", 2);
				String str[] = scan.nextLine().split("\\|", 2);
				if(str[0].equals(lineitem_line_1[0])) {
					output_bw.write(scan.next()+ lineitem_line+"\n");
					 output_bw.flush();
				}
			}
		}
		scan.close();
	}

}
