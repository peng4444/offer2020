package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo518
 * @Author: pbj
 * @Date: 2020/4/11 21:07
 * @Description: TODO 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 */
public class Demo518 {

    public int change1(int amount, int[] coins) {
        int dp[] = new int[amount+1];
        // 设置起始状态
        dp[0] = 1;

        for (int coin : coins) {
            // 记录每添加一种面额的零钱，总金额j的变化
            for (int j = 1; j <= amount; j++) {
                if (j >= coin) {
                    // 在上一钟零钱状态的基础上增大
                    // 例如对于总额5，当只有面额为1的零钱时，只有一种可能 5x1
                    // 当加了面额为2的零钱时，除了原来的那一种可能外
                    // 还加上了组合了两块钱的情况，而总额为5是在总额为3的基础上加上两块钱来的
                    // 所以就加上此时总额为3的所有组合情况
                    dp[j] = dp[j] + dp[j - coin];
                }
            }
        }
        return dp[amount];
    }

    public int change(int amount, int[] coins) {
        if(amount==0){
            return 1;
        }
        if(amount>0&&coins.length==0||coins==null){
            return 0;
        }
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int coin : coins){
            for(int i = coin;i<=amount;i++){
                dp[i] = dp[i] + dp[i-coin];
            }
        }
        return dp[amount];
    }
}
