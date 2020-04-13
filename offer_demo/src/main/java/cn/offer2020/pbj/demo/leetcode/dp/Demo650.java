package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo650
 * @Author: pbj
 * @Date: 2020/4/13 22:10
 * @Description: TODO 650. 只有两个键的键盘
 */
public class Demo650 {

    public int minSteps(int n) {
        if(n==1) return 0;
        for(int i = 2;i<=Math.sqrt(n);i++){
            if(n%i==0) return i + minSteps(n/i);
        }
        return n;
    }

    public int minSteps1(int n) {
        int[] dp = new int[n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= h; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }
}
