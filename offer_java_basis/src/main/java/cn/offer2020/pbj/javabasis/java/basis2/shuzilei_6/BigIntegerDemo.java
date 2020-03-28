package cn.offer2020.pbj.javabasis.java.basis2.shuzilei_6;

import java.math.BigInteger;

public class BigIntegerDemo {

	public static void main(String[] args) {
		System.out.println(Double.MAX_VALUE*Double.MAX_VALUE);//Infinity
		BigInteger bigA = new BigInteger("12442902393207320");
		BigInteger bigB = new BigInteger("13424926496296929");
		System.out.println("�ӷ�������"+bigA.add(bigB));
		System.out.println("����������"+bigA.subtract(bigB));
		System.out.println("�˷�������"+bigA.multiply(bigB));
		System.out.println("����������"+bigA.divide(bigB));
		//
		BigInteger result [] = bigA.divideAndRemainder(bigB);
		System.out.println("�̣�"+result[0]+"������"+result[1]);

	}

}
