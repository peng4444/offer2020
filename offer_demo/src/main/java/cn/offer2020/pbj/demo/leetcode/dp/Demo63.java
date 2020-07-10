package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo63
 * @Author: pbj
 * @Date: 2020/6/16 20:47
 * @Description: TODO 62. 不同路径II
 */
public class Demo63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i][j - 1] == 1) dp[j] = 0;
                else {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }

    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];
        boolean iflag = false;
        boolean jflag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    if (i == 0) iflag = true;
                    if (j == 0) jflag = true;
                } else if (i == 0) {
                    dp[i][j] = iflag ? 0 : 1;
                } else if (j == 0) {
                    dp[i][j] = jflag ? 0 : 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
