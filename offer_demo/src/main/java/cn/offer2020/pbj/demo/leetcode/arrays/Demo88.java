package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;

/**
 * @ClassName: Demo88
 * @Author: pbj
 * @Date: 2020/3/30 14:53
 * @Description: TODO 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 */
public class Demo88 {
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        //从后向前插入，
        // l 表示从后向前当前插入位置
        int l = m + n - 1;
        // i 标记数组 nums1 当前比较位置
        int i = m - 1;
        // j 标记数组 nums2 当前比较位置
        int j = n - 1;
        //将数组 nums 2 全部数据插入到数组 nums1 为止
        while (j >= 0 ) {
            //当数组 nums1 当前可比较数据位 i 大于等于 0 并且数组 nums1 当前位置值比 nums2 当前位置值大时，将数组 nums1 当前位置数组 插入到 l 位置 ，i 位指针向前挪一位
            //否则将 nums2 当前位置数据插入到 l 位置，j 位指针向前挪一位
            // 当前插入位置 l 向前挪一位
            nums1[l--] =  (i >= 0 && nums1[i] >= nums2[j]) ? nums1[l] = nums1[i--] : nums2[j--];
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m - 1;
        int len2 = n - 1;
        int len = m + n - 1;
        while (len1 >= 0 && len2 >= 0) {
            // 注意--符号在后面，表示先进行计算再减1，这种缩写缩短了代码
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        // 表示将nums2数组从下标0位置开始，拷贝到nums1数组中，从下标0位置开始，长度为len2+1
        System.arraycopy(nums2,0,nums1,0,len2+1);
    }

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
