package cn.offer2020.pbj.demo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @pClassName: Demo0927
 * @author: pengbingjiang
 * @create: 2020/9/27 20:16
 * @description: TODO
 */
public class Demo0927 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);
        int ans = 0;
        while(sc.hasNext()){
            int qum = sc.nextInt();
            int temp = Integer.MAX_VALUE;
            for(int j = 0;j<n;j++){
                if(Math.abs(nums[j]-qum)<temp){
                    temp = Math.abs(nums[j]-qum);
                    ans = nums[j];
                }
            }
            System.out.println(ans);
        }
    }

    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        int count = 0;
        for(int i = 0;i<n-2;i++){
            for(int j = i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    if(nums[k]>=nums[j]&&nums[j]>=nums[i]){
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
