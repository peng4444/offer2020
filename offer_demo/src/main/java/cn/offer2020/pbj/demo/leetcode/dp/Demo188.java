package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo188
 * @Author: pbj
 * @Date: 2020/4/13 21:31
 * @Description: TODO 188. 买卖股票的最佳时机 IV
 */
public class Demo188 {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if(k>=len/2){
            int maxProfit = 0;
            for(int i = 1;i<len;i++){
                if(prices[i]>prices[i-1]){
                    maxProfit  = maxProfit + prices[i] - prices[i-1];
                }
            }
            return maxProfit;
        }
        int[][] maxProfit = new int[k+1][len];
        for(int i = 1;i<=k;i++){
            int localMax = maxProfit[i-1][0] -prices[0];
            for(int j = 1;j<len;j++){
                maxProfit[i][j] = Math.max(maxProfit[i][j-1],prices[j]+localMax);
                localMax = Math.max(localMax,maxProfit[i-1][j]-prices[j]);
            }
        }
        return maxProfit[k][len-1];
    }
}
