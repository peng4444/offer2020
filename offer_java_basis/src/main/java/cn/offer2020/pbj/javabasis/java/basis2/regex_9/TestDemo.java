package cn.offer2020.pbj.javabasis.java.basis2.regex_9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 正则表达式
 * @author PBJ
 *String类对于正则表达式的支持
 *正则表达式的验证
 */
public class TestDemo {

	public static void main(String[] args) throws ParseException {
		/**
		 * public boolean matchs(String regex) 正则验证，指定字符串判断是否符合给出的正则表达式结果。
		 * 1.正则表达式判断字符串中是否是数字
		 */
		String str = "123";
		System.out.println(str.matches("\\d+"));
		/**
		 * public String replaceAll(String regex,String repalcement) 全部替换。
		 * 2.字符串替换，不是小写字母的替换成空格
		 * public String replaceFirst(String regex,String repalcement) 替换首个。
		 */
		String str1 ="Bbiefih9q389e**(^$43RFF^&$57879&^$%^#DFG^rdfd54";
		String regex = "[^a-z]";
		System.out.println(str1.replaceAll(regex," "));
		/**
		 * public String [] split (String regex) 全部split
		 * public String [] split (String regex,int limit) 部分split
		 */
		String str2 = "90jeqd8e8e843fUBU(Y*^^D&^5fdue898**";
		String regex1 = "\\d+" ;//[0-9]
		String result[] = str2.split(regex1);
		for(int x = 0;x<result.length;x++) {
			System.out.println(result[x]);
		}
		/**
		 * 验证一个自发性是否是数字，如果是则将其变成double型。
		 */
		System.out.println("-------------1-------------");
		String str3 ="10.2";
		String regex2 = "\\d+(\\.\\d+)?";
		System.out.println(str3.matches(regex2));
		if(str3.matches(regex2)) {
			System.out.println(Double.parseDouble(str3));
		}
		/**
		 * 判断字符串是否是一个IP地址
		 */
		System.out.println("-------------2-------------");
		String str4 = "192.268.1.1";//???只能判断位数，不能判断大小。
		//String regex3 = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		String regex3 = "(\\d{1,3}\\.){3}\\d{1,3}";
		System.out.println(str4.matches(regex3));
		/**
		 * 判断字符串是否符合日期格式，如果是则转换为date格式
		 */
		System.out.println("-------------3--------------");
		String str5 = "2018-10-06";
		String regex4 = "\\d{4}-\\d{2}-\\d{2}";
		System.out.println(str5.matches(regex4));
		if(str.matches(regex4)) {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str5);
			System.out.println(date);
		}
		/**
		 * 判断电话号码
		 * 51283346             (\\d{7,8})
		 * 010-51283346         (\\d{3,4}-?\\d{7,8})
		 * (010)-51283346       ((\\d{3,4}-)|(\\(\\d{3,4}\\)-))?\\d{7,8}
		 */
		System.out.println("-------------4--------------");
		String str6 = "07957788";
		String regex5 = "((\\d{3,4}-)|(\\(\\d{3,4}\\)-))?\\d{7,8}";
		System.out.println(str6.matches(regex5));
		/**
		 * 验证邮箱地址
		 * 格式一： pengbingjaing4@163.com  \\w+@\\w+\\.\\w+
		 * 格式二：    [a-zA-Z][a-zA-Z0-9_\\.]{0,28}[a-zA-Z0-9]@\w+\\.(net|cn|com\\.cn|net\\.cn|org|gov|edu)
		 */
		System.out.println("-------------5--------------");
		String str7 = "pengbingjaing4@163.com";
		String regex6 = "\\w+@\\w+\\.\\w+";
		System.out.println(str7.matches(regex6));
	}
}
