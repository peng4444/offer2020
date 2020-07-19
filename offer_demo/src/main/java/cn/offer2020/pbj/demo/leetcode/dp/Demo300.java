package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @ClassName: Demo300
 * @Author: pbj
 * @Date: 2019/12/31 16:40
 * @Description: TODO 300.最长上升子序列 给定一个无序的整数数组，找到其中最长上升子序列的长度。
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
        //将dp数组定义为：以nums[i]结尾的最长上升子序列的长度
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
    public int lengthOfLIS1(int[] nums) {
        /**
         dp[i]: 所有长度为i+1的递增子序列中, 最小的那个序列尾数.
         由定义知dp数组必然是一个递增数组, 可以用 maxL 来表示最长递增子序列的长度.
         对数组进行迭代, 依次判断每个数num将其插入dp数组相应的位置:
         1. num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp
         数组尾部, 并将最长递增序列长度maxL加1
         2. dp[i-1] < num <= dp[i], 只更新相应的dp[i]
         **/
        int maxL = 0;
        int[] dp = new int[nums.length];
        for(int num : nums) {
            // 二分法查找, 也可以调用库函数如binary_search
            int lo = 0, hi = maxL;
            while(lo < hi) {
                int mid = lo+(hi-lo)/2;
                if(dp[mid] < num)
                    lo = mid+1;
                else
                    hi = mid;
            }
            dp[lo] = num;
            if(lo == maxL)
                maxL++;
        }
        return maxL;
    }

    /* *
     * 功能描述: 暴力求解
     * 最简单的方法是找到所有增加的子序列，然后返回最长增加的子序列的最大长度。
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/31 17:07
     */
    public int lengthOfLIS3(int[] nums) {
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
