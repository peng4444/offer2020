package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo120
 * @Author: pbj
 * @Date: 2019/11/18 11:28
 * @Description: TODO 120.三角形最小路径和
 */
public class  Demo120 {

    /* *
     * 功能描述: 将最后一行的元素填入状态数组中，然后就是按照前面分析的策略，从下到上计算即可
     * @param: [traingle]
     * @return: java.lang.Integer
     * @auther: pbj
     * @date: 2019/11/18 11:54
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0){
            return 0;
        }
        // 加1可以不用初始化最后一层
        int[][] dp = new int[triangle.size()+1][triangle.size()+1];
        for (int i = triangle.size()-1; i>=0; i--){
            List<Integer> curTr = triangle.get(i);
            for(int j = 0 ; j< curTr.size(); j++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + curTr.get(j);
            }
        }
        return dp[0][0];
    }

    /* *
     * 功能描述: DP
     * @param: [triangle]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 19:15
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        //不用获取最后一层
        int[] minlen = new int[row+1];
        for (int level = row-1;level>=0;level--){
            for (int i = 0;i<=level;i++){   //第i行有i+1个数字
                minlen[i] = Math.min(minlen[i], minlen[i+1]) + triangle.get(level).get(i);
            }
        }
        return minlen[0];
    }
}
