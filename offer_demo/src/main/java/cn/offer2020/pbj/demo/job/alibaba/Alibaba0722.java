package cn.offer2020.pbj.demo.job.alibaba;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @pClassName: Alibaba0722
 * @author: pengbingjiang
 * @create: 2020/7/22 15:22
 * @description: TODO
 */
public class Alibaba0722 {
    //1. 给定一个n，求[1,n]这n个数字的排列组合有多少个。条件：相邻的两个数字的绝对值不能等于1
    //思路：简单的回溯算法，注意保存上一次访问的位置用于判定绝对值
    public static void main1(String[] args){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int size = sc.nextInt();
        for (int i = 0; i < size; i++) {
            int n = sc.nextInt();
            int[] ans = new int[n];
            help(n,ans);
            for (int j = 0; j < n; j++) {
                System.out.print(ans[j]+" ");
            }
            System.out.println();
        }
    }

    private static void help(int n, int[] ans) {

    }

    //2. 给定一个数组a,数字m，求在数组a中 连续子数组中的某个元素的出现个数>=m的子数组个数。
    //没a出来就不讲思路了，求各位大佬指点。具体a的大小应该是很大的。
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long ans = 0;

    }
}
