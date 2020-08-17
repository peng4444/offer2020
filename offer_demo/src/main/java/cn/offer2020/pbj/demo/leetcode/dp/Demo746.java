package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo746
 * @Author: pbj
 * @Date: 2020/3/26 16:17
 * @Description: TODO 746.使用最小花费爬楼梯
 */
public class Demo746 {

    //时间复杂度O(N)
    //计算花费 f[i] 有一个清楚的递归关系：f[i] = cost[i] + min(f[i+1], f[i+2])。
    public int minCostClimbingStairs(int[] cost) {
        int f1 = 0,f2 =0;
        for (int i = cost.length - 1; i >= 0; i--) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }

    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int [] shit=new int[cost.length];
            shit[0]=cost[0];
            shit[1]=cost[1];
            for(int i=2;i<cost.length+1;i++){//目的地是最后一阶楼梯的再下一步
                if(i==cost.length) return Math.min(shit[i-1],shit[i-2]);//目的地没屎，吃够了
                shit[i]=Math.min(shit[i-1],shit[i-2])+cost[i];//反正不到目的地，当前阶梯的屎肯定是要吃的
            }
            return -1;
        }
    }

    public int minCostClimbingStairs2(int[] cost) {
        //我是这样理解的,感觉比较好理解
        //dp数组的每一个元素表示到达当前楼层所需的最小花费
        //也就是dp的第i个值是不包含cost[i]的，因为还没从第i层走出去，没消耗体力
        //dp的长度=cost+1，dp的最后一个元素就是到达楼顶所需的最小花费
        int[] dp = new int[cost.length+1];
        dp[0] = 0;
        dp[1] = 0;//因为最开始可以选择起点，而选择起点是不消耗体力的
        for(int i=2; i<dp.length; i++){
            //要么从第i-2层走到第i层，从要么第i-1层走到第i层
            dp[i] = Math.min(dp[i-2]+cost[i-2], dp[i-1]+cost[i-1]);
        }

        return dp[dp.length-1];
    }
}
