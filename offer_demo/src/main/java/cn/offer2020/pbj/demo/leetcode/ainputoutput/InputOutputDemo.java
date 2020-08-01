package cn.offer2020.pbj.demo.leetcode.ainputoutput;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @pClassName: InputOutputDemo
 * @author: pengbingjiang
 * @create: 2020/7/18 21:53
 * @description: TODO
 */
public class InputOutputDemo {

    //输入 12 15 两个数，输出两个数的和
    // 12 15
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println("输出两个数的和："+a+b);
        }
    }
    //输入n个数的和
    // 4
    // 1 2 3 4
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.print("输出一维数组：");
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i]+" ");
        }
    }

    //输入2个数，有多组，如果输入 0 0 则结束输入
    // 1 5
    // 2 3
    // 1 0
    // 0 1
    // 0 0
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == 0 && b == 0) {
                break;
            }
            System.out.println("输出两个数的和："+(a+b));
        }
    }

    //输入二维数组
    // 输入 m n 接着输入m行n列数据
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.nextLine();//用来跳过行列后的回车符
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = sc.nextInt();
            }
        }
        System.out.println("输出二维数组：");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(nums[i][j]+ " ");
            }
            System.out.println();
        }
    }

}
