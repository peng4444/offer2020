package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo766
 * @Author: pbj
 * @Date: 2020/3/10 23:52
 * @Description: TODO 766托普利茨矩阵
 */
public class Demo766 {

    // 对角线法
    public boolean isToeplitzMatrix(int[][] matrix){
        Map<Integer, Integer> map = new HashMap<>();
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (!map.containsKey(r - c)) {
                    map.put(r - c, matrix[r][c]);
                } else if(map.get(r-c)!=matrix[r][c]){
                    return false;
                }
            }
        }
        return true;
    }

    //检查左上邻居
    class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            for (int r = 0; r < matrix.length; ++r)
                for (int c = 0; c < matrix[0].length; ++c)
                    if (r > 0 && c > 0 && matrix[r-1][c-1] != matrix[r][c])
                        return false;
            return true;
        }
    }
}
