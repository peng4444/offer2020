package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo118
 * @Author: pbj
 * @Date: 2020/1/5 09:56
 * @Description: TODO  杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 */
public class Demo118 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        int[][] array = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            array[i][0]=1;
            list.add(array[i][0]);
            for (int j = 1; j <= i; j++) {
                array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                list.add(array[i][j]);
            }
            ans.add(list);
        }
        return ans;
    }
}
