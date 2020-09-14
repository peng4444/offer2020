package cn.offer2020.pbj.demo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @pClassName: Demo2222
 * @author: pengbingjiang
 * @create: 2020/9/7 21:01
 * @description: TODO
 */
public class Demo2222 {
    public static int sort (String inData) {
        // write code here
        String[] str = inData.split(" ");
        int len = str.length;
        int[] nums = new int[len];
        int[] nums1 = new int[len];
        for(int i = 0;i<str.length;i++){
            nums[i] = Integer.valueOf(str[i]);
            nums1[i] = Integer.valueOf(str[i]);
        }
        int j = 0;
        int count = 0;
        Arrays.sort(nums1);
        for (int i = 0; i < len; i++) {
            if(nums[i]==nums1[j]) {
                j++;
                count++;
            }
        }
        return len-count;
    }

    public static void main(String[] args) {
        String str = new String("19 5 9 25");
        System.out.println(sort(str));
    }
}
