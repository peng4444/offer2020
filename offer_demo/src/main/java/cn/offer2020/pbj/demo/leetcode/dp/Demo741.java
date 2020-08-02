package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @pClassName: Demo741
 * @author: pengbingjiang
 * @create: 2020/8/2 12:16
 * @description: TODO
 */
public class Demo741 {
    //自底向上
    public int cherryPickup(int[][] grid) {
        int N = grid.length;
        int[][] dp = new int[N][N];
        for (int[] row: dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];

        for (int t = 1; t <= 2*N - 2; ++t) {
            int[][] dp2 = new int[N][N];
            for (int[] row: dp2) Arrays.fill(row, Integer.MIN_VALUE);

            for (int i = Math.max(0, t-(N-1)); i <= Math.min(N-1, t); ++i) {
                for (int j = Math.max(0, t-(N-1)); j <= Math.min(N-1, t); ++j) {
                    if (grid[i][t-i] == -1 || grid[j][t-j] == -1) continue;
                    int val = grid[i][t-i];
                    if (i != j) val += grid[j][t-j];
                    for (int pi = i-1; pi <= i; ++pi)
                        for (int pj = j-1; pj <= j; ++pj)
                            if (pi >= 0 && pj >= 0)
                                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + val);
                }
            }
            dp = dp2;
        }
        return Math.max(0, dp[N-1][N-1]);
    }

    int[][] grid;
    int[][][] dp;
    int n;
    public int cherryPickup1(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.dp = new int[n][n][n];
        for (int i = 0;i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        return Math.max(0, backtrack(n - 1, n - 1, n - 1));

    }

    int backtrack(int x1, int y1, int x2 ){
        int y2 = x1 - x2 + y1;
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) return -1;
        if (grid[x1][y1] < 0 || grid[x2][y2] < 0) return -1;
        if (x1 == 0 && y1 == 0) return grid[x1][y1];
        if (dp[x1][y1][x2] != Integer.MIN_VALUE) return dp[x1][y1][x2];
        int res = Math.max(Math.max(backtrack(x1 - 1, y1, x2 - 1),backtrack(x1 - 1, y1, x2)), Math.max(backtrack(x1, y1 - 1, x2), backtrack(x1, y1 - 1, x2 - 1)));
        if (res < 0) return dp[x1][y1][x2] = -1;
        res += grid[x1][y1];
        if (x1 != x2) res += grid[x2][y2];
        return dp[x1][y1][x2] = res;
    }
}
