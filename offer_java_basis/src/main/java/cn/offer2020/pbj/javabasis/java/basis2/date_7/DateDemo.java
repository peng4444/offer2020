package cn.offer2020.pbj.javabasis.java.basis2.date_7;

import java.util.Date;

/**
 * 日期处理类Date
 * @author PBJ
 *
 */
public class DateDemo {
	public static void main(String args[]) {
		Date date = new Date();
		System.out.println(date);
		long cur = System.currentTimeMillis();
		Date date1 = new Date(cur);
		System.out.println(date1);
		System.out.println(date.getTime());
	}

}
