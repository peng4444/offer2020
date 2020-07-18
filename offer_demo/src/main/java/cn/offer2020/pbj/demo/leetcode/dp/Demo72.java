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

    //自顶向下
    public int minDistance1(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0;i < len1 + 1;i++){
            for(int j = 0;j < len2 + 1;j++){
                if (Math.min(i, j) == 0) {//初始化数组的第一行与第一列
                    dp[i][j] = Math.max(i, j);
                }else{
                    int ic = dp[i][j - 1] + 1;//插入字符所产生的距离
                    int dc = dp[i - 1][j] + 1;//删除字符所产生的距离
                    int sc = 0;//替换字符所产生的距离
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        sc = dp[i - 1][j - 1];//两字符相等就不用替换
                    }else {
                        sc = dp[i - 1][j - 1] + 1;//两字符不相等需要替换
                    }
                    dp[i][j] = Math.min(ic, Math.min(dc, sc));
                }
            }
        }
        return dp[len1][len2];
    }
}
