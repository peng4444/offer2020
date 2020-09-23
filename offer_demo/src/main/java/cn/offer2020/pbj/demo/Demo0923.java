package cn.offer2020.pbj.demo;

import java.util.Scanner;

/**
 * @pClassName: Demo0923
 * @author: pengbingjiang
 * @create: 2020/9/23 20:36
 * @description: TODO
 */
public class Demo0923 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String s1 = sc.next();
        String s2 = sc.nextLine();
        String[] ss1 = s1.split(" ");
        String[] ss2 = s2.split(" ");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = ss2[i].charAt(0) - 'a';
        }
        int[] dp = new int[len + 1];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        int res = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
    }
}
