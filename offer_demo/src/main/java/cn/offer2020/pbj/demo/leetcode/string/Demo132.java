package cn.offer2020.pbj.demo.leetcode.string;

import java.util.Arrays;

/**
 * @pClassName: Demo132
 * @author: pengbingjiang
 * @create: 2020/7/27 20:21
 * @description: TODO
 */
public class Demo132 {
    //当切割次数最少使得切割后的所有字符串都是回文时，也正是这些回文子串最长的时候，那么如果说能找到以每个字符为中心的最长回文串，实际上就已经找到了答案
    public int minCut(String s) {
        if(s==null||s.length()<=1) return 0;
        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp,len-1);
        for (int i = 0; i < len; i++) {
            // 注意偶数长度与奇数长度回文串的特点
            minCutHelper(s, i, i, dp);// 奇数回文串以1个字符为中心
            minCutHelper(s, i, i + 1, dp);// 偶数回文串以2个字符为中心
        }
        return dp[len - 1];
    }

    private void minCutHelper(String s, int i, int j, int[] dp) {
        int len = s.length();
        while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
            dp[j] = Math.min(dp[j], (i == 0 ? -1 : dp[i - 1]) + 1);
            i--;
            j++;
        }
    }

    public int minCut1(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return 0;
        }

        // 状态定义：dp[i]：前缀子串 s[0:i] （包括索引 i 处的字符）符合要求的最少分割次数
        // 状态转移方程：
        // dp[i] = min(dp[j] + 1 if s[j + 1: i] 是回文 for j in range(i))

        int[] dp = new int[len];
        // 2 个字符最多分割 1 次；
        // 3 个字符最多分割 2 次
        // 初始化的时候，设置成为这个最多分割次数

        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }

        // 参考「力扣」第 5 题：最长回文子串 动态规划 的解法
        boolean[][] checkPalindrome = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || checkPalindrome[left + 1][right - 1])) {
                    checkPalindrome[left][right] = true;
                }
            }
        }

        // 1 个字符的时候，不用判断，因此 i 从 1 开始
        for (int i = 1; i < len; i++) {
            if (checkPalindrome[0][i]){
                dp[i] = 0;
                continue;
            }

            // 注意：这里是严格，要保证 s[j + 1:i] 至少得有一个字符串
            for (int j = 0; j < i; j++) {
                if (checkPalindrome[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }
}
