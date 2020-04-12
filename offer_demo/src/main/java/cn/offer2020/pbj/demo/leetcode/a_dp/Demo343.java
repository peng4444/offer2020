package cn.offer2020.pbj.demo.leetcode.a_dp;

/**
 * @ClassName: Demo343
 * @Author: pbj
 * @Date: 2020/4/11 11:09
 * @Description: TODO 343. 整数拆分
 */
public class Demo343 {

    //dp
    public int integerBreak1(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2;i<=n;i++){
            for(int j = 1;j<=i-1;j++){
                dp[i] = Math.max(dp[i],Math.max(j*dp[i-j],j*(i-j)));
            }
        }
        return dp[n];
    }

    //math
    public int integerBreak(int n) {
        if(n <= 3) return n - 1;
        int a = n / 3, b = n % 3;
        if(b == 0) return (int)Math.pow(3, a);
        if(b == 1) return (int)Math.pow(3, a - 1) * 4;
        return (int)Math.pow(3, a) * 2;
    }

    public int integerBreak2(int n) {
        int p = n % 3, q = n / 3, r = p + (2 * p + 1) % 5;
        return n <= 3 ? n - 1 : (int)(Math.pow(3, q - (p & 1)) * r);
    }
}
