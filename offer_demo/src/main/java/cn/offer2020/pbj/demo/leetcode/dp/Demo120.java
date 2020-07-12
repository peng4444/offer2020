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
    public static Integer miniNumTotal(List<List<Integer>> traingle) {
        int n = traingle.size();
        int[][] dp = new int[n][n];
        List<Integer> lastRow = traingle.get(n - 1);
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = lastRow.get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> row = traingle.get(i);
            for (int j = 0; j < i + 1; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + row.get(j);
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
