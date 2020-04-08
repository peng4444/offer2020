package cn.offer2020.pbj.demo.leetcode.greed;

/**
 * @ClassName: Demo392
 * @Author: pbj
 * @Date: 2020/4/7 15:59
 * @Description: TODO 392. 判断子序列
 */
public class Demo392 {
    //双指针
    public boolean isSubsequence1(String s,String t){
        int i = 0,j= 0;
        int m = s.length(),n = t.length();
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            }else {
                j++;
            }
        }
        return i==m;
    }
    //贪心
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for(char c:s.toCharArray()){
            index = t.indexOf(c,index+1);
            if(index==-1){
                return false;
            }
        }
        return true;
    }

    //dp
    public boolean isSubsequence2(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen > tLen) return false;
        if (sLen == 0) return true;
        boolean[][] dp = new boolean[sLen + 1][tLen + 1];
        //初始化
        for (int j = 0; j < tLen; j++) {
            dp[0][j] = true;
        }
        //dp
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[sLen][tLen];
    }
}
