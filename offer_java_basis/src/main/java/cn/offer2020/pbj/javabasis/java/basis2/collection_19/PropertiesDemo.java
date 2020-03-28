package cn.offer2020.pbj.javabasis.java.basis2.collection_19;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		 * Properties是Hashtable的子类
		 * 设置属性：public Object setProperty(String key,String value)
		 * 取得数据：public String getProperty(String key)
		 * 取得属性：public String getProperty(String key,String defaultValue)
		 */
		Properties pro = new Properties();
//		pro.setProperty("BJ","北京");
//		pro.setProperty("TJ","天津");
		//属性内容保存到文件
//		pro.store(new FileOutputStream(new File("E:"+File.separator+"area.properties")), "Area Info");
//		System.out.println(pro.getProperty("BJ"));
//		System.out.println(pro.getProperty("Gz"));
//		System.out.println(pro.getProperty("GZ", "没有此记录"));
		//从文件读取属性内容
		pro.load(new FileInputStream(new File("E:"+File.separator+"area.properties")));
	}

}
