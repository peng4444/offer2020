package cn.offer2020.pbj.demo;

import java.util.*;

public class TestDemo {
    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);
        if(m>=n) System.out.println(nums[0]+x);
        else{
            for(int i = 0;i<m;i++){
                nums[i] +=x;
            }
            int min = Integer.MAX_VALUE;
            for(int i = 0;i<n;i++){
                min = Math.min(min,nums[i]);
            }
            System.out.println(min);
        }
    }
}