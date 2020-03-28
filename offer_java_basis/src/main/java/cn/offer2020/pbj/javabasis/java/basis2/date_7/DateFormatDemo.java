package cn.offer2020.pbj.javabasis.java.basis2.date_7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *日期格式化
 * 构造方法：public SimpleDateFormat(String patten)
 * 将Date转换为String :public final String frmat(Date date)
 * 将String转换为Date:public Date parse(String source) throws ParseException
 * @author PBJ
 *
 */
public class DateFormatDemo {

	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String str = sdf.format(date);
		System.out.println(str);
		String str1 = "2022-2-22 22:22:22.222";
		Date date1 = sdf.parse(str1);
		System.out.println(date1);
		
	}

}
