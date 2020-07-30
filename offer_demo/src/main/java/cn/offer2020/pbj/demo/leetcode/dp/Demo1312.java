package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @pClassName: Demo1312
 * @author: pengbingjiang
 * @create: 2020/7/30 17:46
 * @description: TODO 1312.让字符串成为回文串的最少插入次数(找到最大回文子串)
 * 相当于求最长回文子序列，构不成最长回文子序列的字符就需要插入相同的
 *      dp[i][j] 表示s[i..j]中最长回文子序列的长度
 *      若s[i] == s[j]，dp[i][j] = dp[i + 1][j - 1] + 2
 *      否则dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
 */
public class Demo1312 {
    public int minInsertions(String s) {
        int len = s.length();
        int[][] dp = new int[len+1][len+1];
        for(int l = 2;l<=len;l++){
            for(int i = 0;i<=len-l;i++){
                int j = l+i-1;
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j] = (i+1>=len ||j-1<0)?0:dp[i+1][j-1];
                }else{
                    dp[i][j] = Math.min(i+1>=len?0:dp[i+1][j],j-1<0?0:dp[i][j-1])+1;
                }
            }
        }
        return dp[0][len-1];
    }

    public int minInsertions1(String s) {
        // i 到 j上 我最后会添加i  你只管i+ 1 到j上最少几个
        // 同1
        //i j就是回文 那么里面 i+ 1 j - 1几个
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] c = s.toCharArray();
        return getDp(c);
    }

    private int getDp(char[] c) {
        int[][] dp = new int[c.length][c.length];
        for (int j = 1; j < c.length; j ++) {
            dp[j - 1][j] = c[j - 1] == c[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i --) {
                if (c[i] == c[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][c.length - 1];
    }
}
