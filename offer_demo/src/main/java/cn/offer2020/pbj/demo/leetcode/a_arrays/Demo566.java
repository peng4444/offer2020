package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Demo566
 * @Author: pbj
 * @Date: 2020/3/7 09:35
 * @Description: TODO 566重塑矩阵
 */
public class Demo566 {

    // 除法和取模
    public int[][] matrixReshape(int[][] nums, int r, int c){
        int m = nums.length, n = nums[0].length;
        if(m*n!=r*c){
            return nums;
        }
        int[][] ans = new int[r][c];
        int index = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j< c; j++){
                ans[i][j] = nums[index/n][index%n];
                index++;
            }
        }
        return ans;
    }

    //不用额外空间
    public int[][] matrixReshape2(int[][] nums, int r, int c){
        int[][] ans = new int[r][c];
        if(nums.length==0||r*c!=nums.length*nums[0].length){
            return nums;
        }
        int rows = 0, cols = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                ans[rows][cols] = nums[i][j];
                cols++;
                if (cols == c) {
                    rows++;
                    cols = 0;
                }
            }
        }
        return ans;
    }
    //使用队列
    public int[][] matrixReshape1(int[][] nums, int r, int c){
        int[][] ans = new int[r][c];
        if(nums.length==0||r*c!=nums.length*nums[0].length){
            return nums;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i< nums.length; i++){
            for(int j = 0; j < nums[0].length; j++){
                queue.add(nums[i][j]);
            }
        }
        for(int i = 0; i<r;i++){
            for (int j = 0; j < c; j++) {
                ans[i][j] = queue.remove();
            }
        }
        return ans;
    }
}
