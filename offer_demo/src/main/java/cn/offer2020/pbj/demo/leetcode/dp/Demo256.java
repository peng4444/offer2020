package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo256
 * @Author: pbj
 * @Date: 2020/5/6 15:06
 * @Description: TODO 256. Paint House 粉刷房子
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
}
