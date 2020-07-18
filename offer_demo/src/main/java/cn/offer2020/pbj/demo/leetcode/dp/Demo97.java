package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * @pClassName: Demo97
 * @author: pengbingjiang
 * @create: 2020/7/18 09:00
 * @description: TODO 97.交错字符串
 *         //给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 */
public class Demo97 {
    //dp->将s1和s2组成一个二维数组，s3判断是否存在一条路径
    public boolean isInterleave(String s1, String s2, String s3) {
        //1.判断s1+s2 == s3
        if(s1.length()+s2.length()!=s3.length()) return false;
        int m = s1.length(),n = s2.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i = 0;i<m;i++){
            dp[i+1][0] = s1.charAt(i) == s3.charAt(i) && dp[i][0];
        }
        for(int i = 0;i<n;i++){
            dp[0][i+1] = s2.charAt(i) == s3.charAt(i) && dp[0][i];
        }
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                char ch = s3.charAt(i+j+1);
                dp[i+1][j+1] = s1.charAt(i) ==ch && dp[i][j+1] || s2.charAt(j)==ch && dp[i+1][j];
            }
        }
        return dp[m][n];
    }

    //带记录的回溯->将i、j、k、返回值用meom[i][j][k]记录起来，当回溯再次遇到此i、j、k时，直接返回。
    public boolean isInterleave1(String s1, String s2, String s3) {
        if(s1 == null) s1 = "";
        if(s2 == null) s2 = "";
        if(s3 == null) s3 = "";
        return helper(s1, s2, s3, 0, 0, 0,
                new Boolean[s1.length()+1][s2.length()+1]);
    }

    public boolean helper(String s1, String s2, String s3, int i, int j, int k, Boolean[][] memo){
        // 若当前memo[i][j]不为空，则直接返回
        if(memo[i][j] != null)
            return memo[i][j];
        if(i == s1.length() && j == s2.length() && k == s3.length())
            return true;

        // 记录
        if(k >= s3.length())
            return memo[i][j] = false;

        if(s1.length() > i){
            if(s1.charAt(i) == s3.charAt(k) &&
                    helper(s1, s2, s3, i+1, j, k+1, memo))
                return true;
        }

        if(j < s2.length()){
            if(s2.charAt(j) == s3.charAt(k) &&
                    helper(s1, s2, s3, i, j+1, k+1, memo))
                return true;
        }

        // 记录
        return memo[i][j] = false;
    }

    //暴力求解->尽可能枚举所有可能的情况，最后判断s3能否被s1和s2交替组成即可。
    public boolean isInterleave2(String s1, String s2, String s3) {
        // 题目没有明确说明s123是否为null，需要判null，这里直接令其等于空字符
        // 若s1为空，即不会参数空指针异常也不会参与到计算当中
        if(s1 == null) s1 = "";
        if(s2 == null) s2 = "";
        if(s3 == null) s3 = "";
        return helper(s1, s2, s3, 0, 0, 0);
    }

    public boolean helper(String s1, String s2, String s3, int i, int j, int k){
        // 若回溯用完了s1,s2,s3，说明能够s3能够被交替组成
        if(i == s1.length() && j == s2.length() && k == s3.length())
            return true;

        if(k >= s3.length())
            return false;

        if(i < s1.length()){
            // 若当前s1的i位置的字符与s3的k位置字符相等，则消耗一个字符，
            // 并向下回溯，若回溯返回true则返回的这个true最开始一定
            // 是由该函数内第二行代码返回的，即表示s3能够被交替组成，直接返回true
            if(s1.charAt(i) == s3.charAt(k) &&
                    helper(s1, s2, s3, i+1, j, k+1))
                return true;
        }

        if(j < s2.length()){
            if(s2.charAt(j) == s3.charAt(k) &&
                    helper(s1, s2, s3, i, j+1, k+1))
                return true;
        }

        // 在此i、j、k下，无论如何都不能组成s3.substring(0, k+1),返回false
        return false;
    }
}
