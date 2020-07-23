package cn.offer2020.pbj.demo.leetcode.hard;

/**
 * @pClassName: Demo887
 * @author: pengbingjiang
 * @create: 2020/7/23 23:15
 * @description: TODO 887. 鸡蛋掉落
 */
public class Demo887 {
    public int superEggDrop(int K, int N) {
        int[] dp = new int[K+1];
        int m = 0;
        while(dp[K]<N){
            for(int i = K;i>=1;i--){
                dp[i]+=dp[i-1]+1;
            }
            m++;
        }
        return m;
    }

    public int superEggDrop1(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        for (int m = 1; m <= N; m++) {
            dp[0][m] = 0; // zero egg
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
                if (dp[k][m] >= N) {
                    return m;
                }
            }
        }
        return N;
    }
}
