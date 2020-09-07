package cn.offer2020.pbj.demo;

import java.util.*;

public class TestDemo {
    public static void main1(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        String str = sc.nextLine();
        String[] s = str.split(" ");
        String[] nums1 = new String[2*T];
        String[] nums2 = new String[2*T];
        int index = 0;
        for(int i = 0;i<str.length();i=i+4){
            nums1[index] = s[i];
            nums1[index+1] = s[i+1];
            nums2[index] = s[i+2];
            nums2[index+1] = s[i+3];
            index=index+2;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        char[] chars = str.toCharArray();
        int[] nums = new int[128];
        for(int i = 0;i<n;i++){
            nums[chars[i]-'a']++;
        }
        int two = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]>1) two++;
        }
        if(two==0){
            System.out.println(n);
        }else{
            System.out.println(n-two+1);
        }

    }
}