package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo96
 * @Author: pbj
 * @Date: 2020/3/12 16:15
 * @Description: TODO 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 */
public class Demo96 {

    //动态规划
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for(int i = 2;i<=n;i++){
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1]*G[i-j];
            }
        }
        return G[n];
    }

    /* *
     * 功能描述:
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/3/12 16:25
     */
    class Solution {
        public int numTrees(int n) {
            int[] dp = new int[n+1];
            dp[0] = 1;
            dp[1] = 1;

            for(int i = 2; i < n + 1; i++)
                for(int j = 1; j < i + 1; j++)
                    dp[i] += dp[j-1] * dp[i-j];

            return dp[n];
        }
    }


}
