package cn.offer2020.pbj.demo.job.ByteDance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @pClassName: Main070403
 * @author: pengbingjiang
 * @create: 2020/7/5 09:30
 * @description: TODO Leetcode41
 */
public class Main070403 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
         Arrays.sort(nums);
         for(int i = 0; i < nums.length; i++){
             if(nums[i] != i) System.out.println("result:"+i);;
         }
        System.out.println();
    }
}
