package cn.offer2020.pbj.demo.leetcode.greed;

import java.util.Arrays;

/**
 * @ClassName: Demo45
 * @Author: pbj
 * @Date: 2020/4/24 14:48
 * @Description: TODO 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置
 */
public class Demo45 {
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if( i == end){ //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public int jump1(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    public int jump2(int[] nums) {
        backtrack(nums, 0, 0);
        return minCount;
    }

    //保存全局变量 表示当前的最小跳跃数
    private int minCount = Integer.MAX_VALUE;

    public void backtrack(int[] a, int index, int count) {
        //递归终止条件 表示已经跳到了最后一个位置
        if (index >= a.length - 1) {
            minCount = Math.min(minCount, count);
            return;
        }
        int step = a[index];
        for (int i = 1; i <= step; i++) {
            backtrack(a, index + i, count + 1);
        }
    }


    // [2,3,1,1,4]
    //DP解法:DP的思路是 dp[i]表示走到当前位置最少需要的步骤 我们可以遍历[0,i-1] 找到能到达位置i的 同时步骤数又是最小的
    //dp[i] = Math.min(dp[j] + 1]
    public int jump3(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, nums.length + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] + j >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }
}
