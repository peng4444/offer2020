package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo877
 * @Author: pbj
 * @Date: 2020/4/26 08:35
 * @Description: TODO 877. 石子游戏
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * 游戏以谁手中的石子最多来决出胜负。
 */
public class Demo877 {

    //自己写的，好不容易一次通过100%  里面的循环判断可以写成一个函数。
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int sum1 = 0;
        int sum2 = 0;
        int start = 0,end = len - 1;
        while(start<end){
            if(piles[start]>piles[end]){
                sum1 = sum1 + piles[start];
                start++;
                if(piles[start]>piles[end]){
                    sum1 = sum1 + piles[start];
                    start++;
                }else{
                    sum1 = sum1 + piles[end];
                    end--;
                }
            }else{
                sum1 = sum1 + piles[end];
                end--;
                if(piles[start]>piles[end]){
                    sum1 = sum1 + piles[start];
                    start++;
                }else{
                    sum1 = sum1 + piles[end];
                    end--;
                }
            }
        }
        return sum1>sum2?true:false;
    }

    //dp 时间复杂度O(N^2)
    public boolean stoneGame1(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }

    //弱智游戏 
    public boolean stoneGame2(int[] piles) {
        return true;
    }

}
