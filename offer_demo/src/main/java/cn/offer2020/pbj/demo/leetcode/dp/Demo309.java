package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo309
 * @Author: pbj
 * @Date: 2020/4/13 21:05
 * @Description: TODO 309. 最佳买卖股票时机含冷冻期
 */
public class Demo309 {
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length==0) return 0;
        int len = prices.length;
        int[] buy = new int[len];
        int[] s1 = new int[len];
        int[] sell = new int[len];
        int[] s2 = new int[len];
        s1[0] = buy[0] = -prices[0];
        sell[0] = s2[0] = 0;
        for(int i = 1;i<len;i++){
            buy[i] = s2[i-1] - prices[i];
            s1[i] = Math.max(buy[i-1],s1[i-1]);
            sell[i] = Math.max(buy[i-1],s1[i-1])+prices[i];
            s2[i] = Math.max(s2[i-1],sell[i-1]);
        }
        return Math.max(sell[len-1],s2[len-1]);
    }
}
