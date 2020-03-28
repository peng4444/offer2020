package cn.offer2020.pbj.javabasis.java.basis2.shuzilei_6;

import java.util.Random;

/**
 * Random类
 * Random在高并发下的缺陷以及JUC对其的优化:https://www.cnblogs.com/CodeBear/p/10748407.html
 * @author PBJ
 *
 */
public class RandomDemo {

	public static void main(String[] args) {
		Random rand = new Random();
		for(int x =0; x< 10;x++) {
			System.out.println(rand.nextInt(100)+",");
		}
		System.out.println("-------------");
		int data[] = new int [7];
		int foot = 0;
		while(foot<7) {
			int t = rand.nextInt(37);
			if(!isRepeat(data,t)) {
				data[foot++] =t;
			}
		}
		java.util.Arrays.sort(data);
		for(int x = 0;x<data.length;x++) {
			System.out.println(data[x]+" ");
		}
	}

	private static boolean isRepeat(int[] temp, int num) {
		if(num==0) {
			return true;
		}
		for(int x = 0;x<temp.length;x++) {
			if(temp[x]==num) {
				return true;
			}
		}
		return false;
	}

}
