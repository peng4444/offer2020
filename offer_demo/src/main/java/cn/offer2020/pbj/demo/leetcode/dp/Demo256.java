package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @ClassName: Demo256
 * @Author: pbj
 * @Date: 2020/5/6 15:06
 * @Description: TODO 256. Paint House 粉刷房子 265. Paint House II 粉刷房子II
 */
public class Demo256 {
    public static int minCost(int[][] costs) {
        int len = costs.length;
        if(costs != null && len == 0) return 0;
        int[][] dp = costs;
        for(int i = 1; i < len; i++){
            dp[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
            dp[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
            dp[i][2] = costs[i][2] + Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(dp[len - 1][0], Math.min(dp[len - 1][1], dp[len - 1][2]));
    }

    public static void main(String args[]) {
        int[][] costs = new int[][]{{14,2,11},{11,14,5},{14,3,10}};
        System.out.println(minCost(costs));
    }

    public int minCost1(int[][] costs) {
        if(costs != null && costs.length == 0) return 0;
        // 直接用原数组
        for(int i = 1; i < costs.length; i++){
            // 涂第一种颜色的话，上一个房子就不能涂第一种颜色，这样我们要在上一个房子的第二和第三个颜色的最小开销中找最小的那个加上
            costs[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
            // 涂第二或者第三种颜色同理
            costs[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] = costs[i][2] + Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        // 返回涂三种颜色中开销最小的那个
        return Math.min(costs[costs.length - 1][0], Math.min(costs[costs.length - 1][1], costs[costs.length - 1][2]));
    }
    //优化前
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n][k];

        for (int i = 1; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < k; ++i) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < k; ++j) {
                for (int m = 0; m < k; ++m) {
                    if (m != j) {
                        dp[i][m] = Math.min(dp[i][m], dp[i - 1][j] + costs[i][m]);
                    }
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {
            result = Math.min(result, dp[n - 1][i]);
        }

        return result;
    }
    //优化后
    public int minCostII1(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int n = costs.length, k = costs[0].length;
        int[][] dp = new int[n][k];

        for (int i = 1; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < k; ++i) {
            dp[0][i] = costs[0][i];
        }

        for (int i = 1; i < n; ++i) {
            // min1 表示的是最大值，min2 表示的是次大值
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int l = 0; l < k; ++l) {
                if (min1 > dp[i - 1][l]) {
                    min2 = min1;
                    min1 = dp[i - 1][l];
                    minIndex = l;
                } else if (min2 > dp[i - 1][l]) {
                    min2 = dp[i - 1][l];
                }
            }

            for (int j = 0; j < k; ++j) {
                if (minIndex != j) {
                    dp[i][j] = Math.min(dp[i][j], min1 + costs[i][j]);
                } else {
                    dp[i][j] = Math.min(dp[i][j], min2 + costs[i][j]);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < k; ++i) {
            result = Math.min(result, dp[n - 1][i]);
        }

        return result;
    }
}
