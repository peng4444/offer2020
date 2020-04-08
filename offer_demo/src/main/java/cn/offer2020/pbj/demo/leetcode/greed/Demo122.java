package cn.offer2020.pbj.demo.leetcode.greed;

/**
 * @ClassName: Demo122
 * @Author: pbj
 * @Date: 2019/12/20 14:06
 * @Description: TODO 买卖股票的最佳时机 II
 */
public class Demo122 {

    /* *
     * 功能描述: dp
     * @param: [prices]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 21:04
     */
    int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

    /* *
     * 功能描述: 峰谷法
     * @param: [prices]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/20 14:22
     */
    public int maxProfit3(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxProfit  = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxProfit = maxProfit + peak - valley;
        }
        return maxProfit;
    }

    /* *
     * 功能描述: 贪心算法 当天的价格比前一天高，就前一天买进，当卖出
     * @param: [prices]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/20 14:08
     */
    public int maxProfit2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit = profit + prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    /* *
     * 功能描述: 暴力法  深度优先遍历
     * 时间复杂度O(n^N)
     * @param: [prices]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/20 14:08
     */
    public int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }

    public int calculate(int[] prices, int s) {
        if (s > prices.length) {
            return 0;
        }
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxProfit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    if (profit > maxProfit) {
                        maxProfit = profit;
                    }
                }
            }
            if (maxProfit > max) {
                max = maxProfit;
            }
        }
        return max;
    }
}
