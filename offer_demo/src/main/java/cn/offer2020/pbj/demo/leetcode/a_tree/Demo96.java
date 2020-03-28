package cn.offer2020.pbj.demo.leetcode.a_tree;

/**
 * @ClassName: Demo96
 * @Author: pbj
 * @Date: 2020/3/12 16:15
 * @Description: TODO
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
     * 功能描述:  作者：guanpengchn
    链接：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/hua-jie-suan-fa-96-bu-tong-de-er-cha-sou-suo-shu-b/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
