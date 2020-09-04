package cn.offer2020.pbj.demo;

import java.util.ArrayList;
import java.util.List;

public class TestDemo{
    public static int[] SpiralMatrix (int[][] matrix) {
        // write code here
        int col = matrix.length;
        int row = matrix[0].length;
        if(col==0||row==0) return new int[]{};
        int len = col*row;
        int[] ans = new int[len];
        List<Integer> list = new ArrayList<>();
        int index = 0;
        while(index<(col+1)/2&&index<(row+1)/2){
            for(int r = index;r<row-index;r++){
                list.add(matrix[index][r]);
            }
            for(int c = index+1;c<col-index;c++){
                list.add(matrix[c][row-index-1]);
            }
            for(int r = row-index-2;r>index&&col-index-1>index;r--){
                list.add(matrix[col-index-1][r]);
            }
            for(int c = col-index-1;c>index&&index<row-index-1;c--){
                list.add(matrix[c][index]);
            }
            index++;
        }
        for(int i = 0;i<list.size();i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] num = new int[][]{{1,2,3,4} ,{ 5, 6, 7,8} , {9,10,11,12}};
        SpiralMatrix(num);
    }
}