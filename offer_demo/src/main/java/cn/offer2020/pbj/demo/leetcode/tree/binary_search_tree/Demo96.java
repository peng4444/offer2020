package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo96
 * @Author: pbj
 * @Date: 2020/3/12 16:15
 * @Description: TODO 96.不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 */
public class Demo96 {

    /* *
     * 功能描述:动态规划
     * 对于每一个根i他都是由左边子树（1, 2, ..., i - 1)和右边子树（i + 1, i + 2, ..., n)组成的。
     * 因此他的个数肯定是两个子树情况的积。而且，这种根一共有n个，再将这些加起来就可以了。
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/3/12 16:25
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++)
            for (int j = 1; j < i + 1; j++)
                dp[i] += dp[j - 1] * dp[i - j];

        return dp[n];
    }
    
    /**
    * @Description: 公式法
    * @Param: [n]
    * @return: int
    * @Author: pengbingjiang
    * @Date: 2020/7/13 15:12
    */
    public int numTrees1(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

    Map<Integer, Integer> memory = new HashMap<>();
    public int numTrees2(int n) {
        return helper(1, n);
    }
    public int helper(int begin, int end) {
        //因为二叉搜索树的种类只与节点个数有关，那么建立备忘录，防止重复计算
        if (memory.containsKey(end - begin))
            return memory.get(end - begin);
        //空二叉树也是一棵搜索二叉树
        if (begin > end)
            return 1;
        int sum = 0;
        for (int i = begin; i <= end; i++) {
            //以i为根节点时左，右子树的个数
            int leftSize = helper(begin, i - 1);
            int rightSize = helper(i + 1, end);
            sum += leftSize * rightSize;
        }
        memory.put(end - begin, sum);
        return sum;
    }
}
