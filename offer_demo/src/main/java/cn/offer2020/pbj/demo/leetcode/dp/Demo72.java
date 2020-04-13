package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo72
 * @Author: pbj
 * @Date: 2020/4/13 22:03
 * @Description: TODO 72. 编辑距离 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 */
public class Demo72 {

    public int minDistance(String word1, String word2) {
        if(word1==null||word2==null){
            return 0;
        }
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 1;i<=m;i++){
            dp[i][0] = i;
        }
        for(int i = 1;i<=n;i++){
            dp[0][i] = i;
        }
        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
            }
        }
        return dp[m][n];
    }
}
