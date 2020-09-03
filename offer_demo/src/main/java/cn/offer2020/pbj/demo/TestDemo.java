package cn.offer2020.pbj.demo;

import java.util.Scanner;

public class TestDemo{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int zero = 0;
        int one = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            if (nums[i] == 0) zero++;
            if (nums[i] == 5) one++;
        }
        StringBuilder sb = new StringBuilder();
        //长度很大
        if (zero == 0)
            System.out.println(-1);
        if (zero == 1 && one < 9)
            System.out.println(-1);
        if (zero >= 1 && one > 9) {
            for (int i = 0; i < 9; i++) {
                sb.append("5");
            }
            for (int i = 0; i < zero; i++) {
                sb.append("0");
            }
            System.out.println(sb.toString());
        }
    }
}