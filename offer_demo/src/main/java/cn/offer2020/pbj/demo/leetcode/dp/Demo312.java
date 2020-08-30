package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @pClassName: Demo312
 * @author: pengbingjiang
 * @create: 2020/7/19 17:21
 * @description: TODO 312. 戳气球
 */
public class Demo312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n+2];
        arr[0] = arr[n+1] = 1;
        for(int i = 1;i<=n;i++){
            arr[i] = nums[i-1];
        }
        int[][] dp = new int[n+2][n+2];
        for(int left = n-1;left>=0;left--){
            for(int right = left+2;right<=n+1;right++){
                for(int k = left+1;k<=right-1;k++){
                    dp[left][right] = Math.max(dp[left][right],dp[left][k]+dp[k][right]+arr[left]*arr[k]*arr[right]);
                }
            }
        }
        return dp[0][n+1];
    }

    public int[][] rec;
    public int[] val;

    public int maxCoins1(int[] nums) {
        int n = nums.length;
        val = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = val[n + 1] = 1;
        rec = new int[n + 2][n + 2];
        for (int i = 0; i <= n + 1; i++) {
            Arrays.fill(rec[i], -1);
        }
        return solve(0, n + 1);
    }

    public int solve(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }
        if (rec[left][right] != -1) {
            return rec[left][right];
        }
        for (int i = left + 1; i < right; i++) {
            int sum = val[left] * val[i] * val[right];
            sum += solve(left, i) + solve(i, right);
            rec[left][right] = Math.max(rec[left][right], sum);
        }
        return rec[left][right];
    }

    public int maxCoins2(int[] nums) {
        int[] a = new int[nums.length+2];
        a[0] = 1;
        a[a.length-1] = 1;
        for(int i = 0; i < nums.length; i++){
            a[i+1] = nums[i];
        }
        return helper(a, 0, a.length-1);
    }

    // 每个回溯返回(i, j)区间内所能获得最大coins数
    public int helper(int[] a, int i, int j){
        int max = 0;

        // 注意每次从(i, j)中取一个气球，但不包括i和j
        // 穷尽所有放置方案，得到一个最大的方案max
        for(int k = i+1; k < j; k++){
            max = Math.max(max,
                    helper(a, i, k) +
                            a[i] * a[k] * a[j]+
                            helper(a, k, j));
        }

        return max;
    }
}
