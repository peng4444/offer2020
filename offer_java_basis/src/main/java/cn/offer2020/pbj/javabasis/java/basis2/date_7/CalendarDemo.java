package cn.offer2020.pbj.javabasis.java.basis2.date_7;

import java.util.Calendar;

/**
 *Calender主要是进行日期计算
 * @author PBJ
 *
 */
public class CalendarDemo {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		StringBuffer buf = new StringBuffer();
		buf.append(cal.get(Calendar.YEAR)+4).append("-");
		buf.append(cal.get(Calendar.MONTH)-7).append("-");
		buf.append(cal.get(Calendar.DAY_OF_MONTH)+18).append(" ");
		buf.append(cal.get(Calendar.HOUR_OF_DAY)).append(":");
		buf.append(cal.get(Calendar.MINUTE)).append(":");
		buf.append(cal.get(Calendar.SECOND));
		System.out.println(buf);

	}

}
