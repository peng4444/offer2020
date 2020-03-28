package cn.offer2020.pbj.demo.leetcode.a_arrays;

/**
 * @ClassName: Demo240
 * @Author: pbj
 * @Date: 2020/3/7 11:28
 * @Description: TODO 240搜索二维矩阵 II
 */
public class Demo240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0){
            return false;
        }
        int r = matrix.length, l = matrix[0].length;
        for(int i = 0; i < r;i++){
            for(int j= 0; j< l;j++){
                if(matrix[i][j]==target){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length-1,col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }
}
