package cn.offer2020.pbj.demo.leetcode.dp;

import org.junit.Test;

/**
 * @ClassName: Demo1143
 * @Author: pbj
 * @Date: 2020/3/15 14:45
 * @Description: TODO 1143.最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。,可以删除
 */
public class Demo1143 {
    //DP压缩空间版O(1)
    public int longestCommonSubsequence3rd(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length(), n = text2.length();
        int[] dp = new int[n + 1];
        int tmp = 0;
        for (int i = 1; i <= m; i++) {
            int last = 0;
            for (int j = 1; j <= n; j++) {
                tmp = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[j] = last + 1;
                else dp[j] = Math.max(tmp, dp[j - 1]);
                last = tmp;
            }
        }
        return dp[n];
    }
    //DP 优化版
    public int longestCommonSubsequence2nd(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }
    //给定两个字符串s1和s2，返回这两个字符串的最长公共子序列的长度。
    public int longestCommonSubString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1;i<=m;i++){
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int ans = 0;
        for(int i = 1; i<=m;i++){
            for(int j = 1;j<=n;j++){
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    @Test
    public void test() {
        String s1 = "abcdfg";
        String s2 = "acdfb";
        int ans = longestCommonSubString(s1, s2);
        System.out.println("最长公共子串长度："+ans);
    }

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
