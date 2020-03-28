package cn.offer2020.pbj.demo.leetcode.a_dp;

/**
 * @ClassName: Demo1143
 * @Author: pbj
 * @Date: 2020/3/15 14:45
 * @Description: TODO
 */
public class Demo1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        int[][] dp = new int[c1.length+1][c2.length+1];
        for (int i = 0; i <= c1.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= c2.length; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if (c1[i-1]==c2[j-1]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else if (dp[i][j - 1] >= dp[i - 1][j]) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[c1.length][c2.length];
    }
}
