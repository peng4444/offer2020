package cn.offer2020.pbj.demo.leetcode.a_dp;

/**
 * @ClassName: Demo746
 * @Author: pbj
 * @Date: 2020/3/26 16:17
 * @Description: TODO 746. 使用最小花费爬楼梯
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
}
