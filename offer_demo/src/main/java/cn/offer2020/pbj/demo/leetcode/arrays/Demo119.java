package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Demo119
 * @Author: pbj
 * @Date: 2020/1/5 10:46
 * @Description: TODO 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 */
public class Demo119 {

    /**
     * 获取杨辉三角的指定行
     * 直接使用组合公式C(n,i) = n!/(i!*(n-i)!)
     * 则第(i+1)项是第i项的倍数=(n-i)/(i+1);
     */
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        long cur = 1;
        for (int i = 0; i <= rowIndex; i++) {
            res.add((int) cur);
            cur = cur * (rowIndex - i) / (i + 1);
        }
        return res;
    }

    //看不懂的解法
    public List<Integer> getRow2(int rowIndex) {
        Integer[] dp = new Integer[rowIndex + 1];
        Arrays.fill(dp, 1);
        for (int i = 2; i < dp.length; i++) {
            for (int j = i - 1; j > 0; j--)
                dp[j] = dp[j] + dp[j - 1];
        }
        List<Integer> res = Arrays.asList(dp);
        return res;
    }

    //自己的垃圾解法
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>(rowIndex);
        int[][] array = new int[rowIndex][rowIndex];
        for (int i = 0; i < rowIndex; i++) {
            array[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
            }
        }
        if (rowIndex == 0) {
            ans.add(1);
            return ans;
        } else {
            ans.add(1);
            for (int i = 1; i < rowIndex; i++) {
                ans.add(array[rowIndex - 1][i - 1] + array[rowIndex - 1][i]);
            }
            ans.add(1);
        }
        return ans;
    }

    //递归解法
    class Solution {

        public List<Integer> getRow(int rowIndex) {
            List<Integer> result = new ArrayList<>();
            if (rowIndex + 1 <= 0)
                return result;
            dfs(result, rowIndex + 1);
            return result;
        }

        public void dfs(List<Integer> result, int rowIndex) {
            if (rowIndex == 1) {
                result.add(1);
                return;
            }
            dfs(result, rowIndex - 1);
            int len = result.size();
            int temp = 1;
            for (int i = 1; i < len; i++) {
                int t = result.get(i);
                result.set(i, temp + result.get(i));
                temp = t;
            }
            result.add(1);
        }
    }
}
