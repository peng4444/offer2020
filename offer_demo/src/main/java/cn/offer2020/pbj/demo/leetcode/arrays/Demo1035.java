package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo1035
 * @Author: pbj
 * @Date: 2020/1/5 14:46
 * @Description: TODO 不相交的线
 */
public class Demo1035 {

    //同 最长公共子序列
    public int maxUnCrossedLines(int[] A, int[] B) {
        int n1 = A.length;
        int n2 = B.length;
        int[][] dp = new int[n1+1][n2+1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[n1][n2];
    }
}
