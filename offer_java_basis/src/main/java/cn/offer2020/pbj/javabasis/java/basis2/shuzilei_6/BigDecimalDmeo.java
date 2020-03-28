package cn.offer2020.pbj.javabasis.java.basis2.shuzilei_6;

import java.math.BigDecimal;

class MyMath{
	public static double round(double num,int scale) {
		BigDecimal bigA = new BigDecimal(num);
		BigDecimal bigB = new BigDecimal(1);
		return bigA.divide( bigB, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}

public class BigDecimalDmeo {

	public static void main(String[] args) {
		System.out.println(MyMath.round(19.1412442, 2));
		System.out.println(MyMath.round(-19, 2));
		System.out.println(MyMath.round(10.2, 0));
	}

}
