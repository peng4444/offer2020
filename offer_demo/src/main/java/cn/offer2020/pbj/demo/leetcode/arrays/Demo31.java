package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;

/**
 * @ClassName: Demo38
 * @Author: pbj
 * @Date: 2020/1/6 09:26
 * @Description: TODO 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 */
public class Demo31 {

    /* *
     * 功能描述: 即从该序列尾部开始遍历，直到当前元素（假设位置为i）比该元素前面的元素大的时候停止。
     * 然后从i道最后一个元素序列中找到比第i-1个元素大的最小元素进行交换，最后把最后i个元素从小到大排序即可。
     * 时间复杂度O(N),空间复杂度O(1)
     * @param: [nums]
     * @return: void
     * @auther: pbj
     * @date: 2020/1/6 9:45
     */
    public void nextPermutation1(int[] nums) {
        int i = nums.length-2;
        while (i >= 0 && nums[i] > nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /* *不错的解法
     * 功能描述: 从数组右侧向左开始遍历，找是否存在nums[i]>nums[i-1]的情况，
     * 如果不存在这种nums[i]>nums[i-1]情况 ，for循环会遍历到i==0（也就是没有下一个排列），此时按题意排成有序Arrays.sort()
     * 如果存在，则将从下标i到nums.length()的部分排序，然后在排过序的这部分中遍历找到第一个大于nums[i-1]的数，并与nums[i-1]交换位置
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/1/6 9:51
     */
    class Solution {
        public void nextPermutation(int[] nums) {
            int len = nums.length;
            for (int i = len - 1; i >= 0; i--) {
                if (i == 0) {
                    Arrays.sort(nums);
                    return;
                } else {
                    if (nums[i] > nums[i - 1]) {
                        Arrays.sort(nums, i, len);
                        for (int j = i; j <len; j++) {
                            if (nums[j] > nums[i - 1]) {
                                int temp = nums[j];
                                nums[j] = nums[i - 1];
                                nums[i - 1] = temp;
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    //自己的垃圾解法 通过百分之五十
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
                break;
            }
        }
    }


}
