package cn.offer2020.pbj.demo.leetcode.beibao;

/**
 * @ClassName: BeiBao
 * @Author: pbj
 * @Date: 2020/3/15 15:48
 * @Description: TODO 背包问题
 * 也是很经典的一道算法题：0-1背包问题说的是，给定背包容量W，一系列物品{weiht,value}，每个物品只能取一件，计算可以获得的value的最大值。
 * 最优解问题，当然是我们DP，最难的一步还是状态转移方程，我们先把方程给出来，再对其进行讨论.
 */
public class BeiBao {
    /**
     * 此函数用于计算背包中能存放的最大values
     * @param m     m[i][j]用于记录1,2,...,i个物品在背包容量为j时候的最大value
     * @param w     w数组存放了每个物品的重量weight,w[i]表示第i+1个物品的weight
     * @param v     v数组存放了每个物品的价值value,v[i]表示第i+1个物品的value
     * @param C     C表示背包最大容量
     * @param sum   sum表示物品个数
     * 状态转移方程: m[i][j] = max{ m[i-1][j-w[i]]+v[i] , m[i-1][j]}
     *            m[i-1][j]很好理解，就是不将第i个物品放入背包的最大value
     *            m[i-1][j-w[i]]+v[i]表示将第i个物品放入背包，m[i-1][j-w[i]]表示在背包中先给第i个物品把地方腾出来
     *            然后背包可用空间就是j-w[i],在这些可用空间里1,2,...,i-1个物品放的最大value就是m[i-1][j-w[i]],那
     *            最后再加上第i个物品的value，就是将第i个物品放入背包的最大value了
     */
    public static void knap(int[][] m, int[] w,int[] v, int C, int sum){
        for(int j = 0; j < C; j++){     //初始化   stuttering
            if(j+1 >= w[0]){        //第一行只有一个物品，如果物品比背包容量大就放进去，否则最大value只能为0
                m[0][j] = v[0];
            }else{
                m[0][j] = 0;
            }
        }
        for(int i = 1; i < sum; i++){
            for(int j = 0; j < C; j++){
                int a = 0, b = 0;       //a表示将第i个物品放入背包的value，b表示不放第i个物品
                if(j >= w[i])
                    a = m[i-1][j-w[i]]+v[i];
                b = m[i-1][j];
                m[i][j] = (a>b?a:b);
            }
        }
    }

    public int knapsack(int W, int N, int[] weights, int[] values) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= N; i++) {
            int w = weights[i - 1], v = values[i - 1];
            for (int j = W; j >= 1; j--) {
                if (j >= w) {
                    dp[j] = Math.max(dp[j], dp[j - w] + v);
                }
            }
        }
        return dp[W];
    }
}
