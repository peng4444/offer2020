package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.Arrays;

/**
 * @pClassName: Demo940
 * @author: pengbingjiang
 * @create: 2020/7/18 12:03
 * @description: TODO 940. 不同的子序列 II
 * 给定一个字符串 S，计算 S 的不同非空子序列的个数。
 * 因为结果可能很大，所以返回答案模 10^9 + 7.
 */
public class Demo940 {
    //dp
    public int distinctSubseqII1(String S) {
        int MOD = 1_000_000_007;
        int N = S.length();
        int[] dp = new int[N+1];
        dp[0] = 1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < N; ++i) {
            int x = S.charAt(i) - 'a';
            dp[i+1] = dp[i] * 2 % MOD;
            if (last[x] >= 0)
                dp[i+1] -= dp[last[x]];
            dp[i+1] %= MOD;
            last[x] = i;
        }

        dp[N]--;
        if (dp[N] < 0) dp[N] += MOD;
        return dp[N];
    }

    public int distinctSubseqII(String S) {
        long MOD = (long)1e9 + 7;
        long[] end = new long[26];
        long sum = 0;
        long inc = 0;
        for(char c:S.toCharArray()){
            inc = end[c-'a'];
            end[c-'a'] = (sum+1)%MOD;
            inc = end[c-'a'] - inc;
            sum = (sum+inc+MOD) %MOD;
        }
        return (int)sum;
    }
}
