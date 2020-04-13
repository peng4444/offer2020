package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo583
 * @Author: pbj
 * @Date: 2020/4/13 21:53
 * @Description: TODO 583. 两个字符串的删除操作
 */
public class Demo583 {
    //可以转换为求两个字符串的最长公共子序列问题。
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(),len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 1;i<=len1;i++){
            for(int j = 1;j<=len2;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return len1+len2-2*dp[len1][len2];
    }

    //使用最长公共子序列
    public class Solution {
        public int minDistance(String s1, String s2) {
            return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length());
        }
        public int lcs(String s1, String s2, int m, int n) {
            if (m == 0 || n == 0)
                return 0;
            if (s1.charAt(m - 1) == s2.charAt(n - 1))
                return 1 + lcs(s1, s2, m - 1, n - 1);
            else
                return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
        }
    }

    //带记忆化的最长公共子序列
    public class Solution2 {
        public int minDistance(String s1, String s2) {
            int[][] memo = new int[s1.length() + 1][s2.length() + 1];
            return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length(), memo);
        }
        public int lcs(String s1, String s2, int m, int n, int[][] memo) {
            if (m == 0 || n == 0)
                return 0;
            if (memo[m][n] > 0)
                return memo[m][n];
            if (s1.charAt(m - 1) == s2.charAt(n - 1))
                memo[m][n] = 1 + lcs(s1, s2, m - 1, n - 1, memo);
            else
                memo[m][n] = Math.max(lcs(s1, s2, m, n - 1, memo), lcs(s1, s2, m - 1, n, memo));
            return memo[m][n];
        }
    }

    //最长公共子序列 - 动态规划
    public class Solution3 {
        public int minDistance(String s1, String s2) {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 || j == 0)
                        continue;
                    if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return s1.length() + s2.length() - 2 * dp[s1.length()][s2.length()];
        }
    }

    //不使用 LCS 的动态规划
    public class Solution4 {
        public int minDistance(String s1, String s2) {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 || j == 0)
                        dp[i][j] = i + j;
                    else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1];
                    else
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return dp[s1.length()][s2.length()];
        }
    }

    // 一维动态规划
    public class Solution5 {
        public int minDistance(String s1, String s2) {
            int[] dp = new int[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                int[] temp=new int[s2.length()+1];
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 || j == 0)
                        temp[j] = i + j;
                    else if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        temp[j] = dp[j - 1];
                    else
                        temp[j] = 1 + Math.min(dp[j], temp[j - 1]);
                }
                dp=temp;
            }
            return dp[s2.length()];
        }
    }


}
