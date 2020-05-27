package cn.offer2020.pbj.demo.leetcode.ainputoutput;

import java.util.Scanner;

/**
 * @ClassName: Main1
 * @Author: pbj
 * @Date: 2020/5/27 17:37
 * @Description: TODO [java笔试中常见输入情景](https://blog.csdn.net/sinat_23588337/article/details/96300197)
 * [Java编程笔试中常用输入格式](https://blog.csdn.net/gaoyueace/article/details/89306365)
 */
public class Main1 {
    public static void main(String[] args) {
        System.out.println("=====开始输出：=====");
        Scanner sc = new Scanner(System.in);
        //输入整数  4
        int n = sc.nextInt();
        // 输入浮点数 4.4
        double number = sc.nextDouble();
        //输入字符串
        String aline = sc.nextLine();
        char[] chars = aline.toCharArray();
        System.out.println("======开始输出：======");
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]+" ");
        }
        System.out.println();
        //输入整数数组 1 2 3 4
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        //输入一行整数
        String ss = sc.nextLine();
        for (int i = 0; i < n; i++) {

        }
        //输入字符串
        String str = sc.nextLine();
        String[] strs = str.split(" ");
        //转为字符串数组
        int[] ints = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            ints[i] = Integer.parseInt(strs[i]);
        }
        //输入字符串数组
        int cnt = sc.nextInt();
        sc.nextLine();
        String[] strs1 = new String[cnt];
        for (int i = 0; i < cnt; i++) {
            strs1[i] = sc.nextLine();
        }
        System.out.println("=====================");
        System.out.println("======开始输出：======");
        System.out.println(n);
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        //矩阵列长度 + 输入数字矩阵
        int count = Integer.parseInt(sc.nextLine())-1;
        int[][] num = new int[count][2];
        for(int i=0;i<count;i++){
            String temp = sc.nextLine();
            String[] ss1 = temp.trim().split(" ");
            num[i][0] = Integer.parseInt(ss1[0]);
            num[i][1] = Integer.parseInt(ss1[1]);
        }




    }
}
