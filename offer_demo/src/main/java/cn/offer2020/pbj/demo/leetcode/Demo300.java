package cn.offer2020.pbj.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName: Demo300
 * @Author: pbj
 * @Date: 2019/12/31 16:40
 * @Description: TODO 最长上升子序列 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 */
public class Demo300 {

    /* *
     * 功能描述: dp
     * 时间复杂度O(N^2)
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/31 16:55
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 1;
        int[] dp = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /* *
     * 功能描述: dp+二分查找
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/31 17:13
     */
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    /* *
     * 功能描述: 暴力求解
     * 最简单的方法是找到所有增加的子序列，然后返回最长增加的子序列的最大长度。
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/31 17:07
     */
    public int lengthOfLIS1(int[] nums) {
        return lengthOfLIS(nums, Integer.MIN_VALUE, 0);
    }

    public int lengthOfLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthOfLIS(nums, nums[curpos], curpos + 1);
        }
        int notaken = lengthOfLIS(nums, prev, curpos + 1);
        return Math.max(taken, notaken);
    }
}
