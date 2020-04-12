package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;

/**
 * @ClassName: Demo88
 * @Author: pbj
 * @Date: 2020/3/30 14:53
 * @Description: TODO 88. 合并两个有序数组
 */
public class Demo88 {

    //双指针
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1,j = n-1,index = m+n-1;
        while(i>=0&&j>=0){
            if(nums1[i]>nums2[j]){
                nums1[index--] = nums1[i--];
            }else{
                nums1[index--] = nums2[j--];
            }
        }
        while(i>=0){
            nums1[index--] = nums1[i--];
        }
        while(j>=0){
            nums1[index--] = nums2[j--];
        }
    }

    //方法一 : 合并后排序
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

}
