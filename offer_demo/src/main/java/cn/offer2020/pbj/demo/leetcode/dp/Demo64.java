package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo64
 * @Author: pbj
 * @Date: 2020/4/11 10:26
 * @Description: TODO 64. 最小路径和
 */
public class Demo64 {
    //二维动态规划
    public int minPathSum2(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    dp[i][j] = grid[i][j] +  dp[i][j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                else
                    dp[i][j] = grid[i][j];
            }
        }
        return dp[0][0];
    }
    //一维动态规划
    public int minPathSum(int[][] grid) {
        if(grid.length==0||grid[0].length==0){
            return 0;
        }
        int m = grid.length,n = grid[0].length;
        int[] dp = new int[n];
        for(int i = 0;i<m;i++){
            for(int j =0;j<n;j++){
                if(j==0){
                    dp[j] = dp[j];
                }else if(i==0){
                    dp[j] = dp[j-1];
                }else{
                    dp[j] = Math.min(dp[j-1],dp[j]);
                }
                dp[j] = dp[j] + grid[i][j];
            }
        }
        return dp[n-1];
    }

    //暴力
    public int calculate(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
    }
    public int minPathSum1(int[][] grid) {
        return calculate(grid, 0, 0);
    }
}
