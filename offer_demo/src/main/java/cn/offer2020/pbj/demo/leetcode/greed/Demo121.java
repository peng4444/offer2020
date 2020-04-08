package cn.offer2020.pbj.demo.leetcode.greed;

/**
 * @ClassName: Demo121
 * @Author: pbj
 * @Date: 2019/12/29 20:39
 * @Description: TODO 买卖股票的最佳时机 I
 */
public class Demo121 {
    /* *
     * 功能描述: dp
     * @param: [prices]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 21:01
     */
    public int maxProfit2(int[] prices) {
        if(prices.length <= 1)
            return 0;
        int min = prices[0], max = 0;
        for(int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
    /* *
     * 功能描述: 暴力求解
     * @param: [prices]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 20:56
     */
    public int maxProfit(int prices[]) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }

    /* *
     * 功能描述: 贪心
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/29 21:21
     */
    public class Solution {
        public int maxProfit(int prices[]) {
            int minprice = Integer.MAX_VALUE;
            int maxprofit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (prices[i] < minprice)
                    minprice = prices[i];
                else if (prices[i] - minprice > maxprofit)
                    maxprofit = prices[i] - minprice;
            }
            return maxprofit;
        }
    }


}
