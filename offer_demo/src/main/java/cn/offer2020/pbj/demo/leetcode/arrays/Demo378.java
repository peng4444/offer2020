package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName: Demo378
 * @Author: pbj
 * @Date: 2020/3/7 14:10
 * @Description: TODO 有序矩阵中第K小的元素
 */
public class Demo378 {

    //优先队列
    public int kthSmallest2(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        Queue<Integer> QAQ = new LinkedList<>();
        int[] array = new int[row*col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++) QAQ.add(matrix[i][j]);
        }
        for(int i=0; i<array.length; i++) array[i] = QAQ.remove();
        Arrays.sort(array);
        return array[k-1];
    }
    //超时
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length,n = matrix[0].length;
        int lo = matrix[0][0],hi = matrix[m-1][n-1];
        while(lo<=hi){
            int mid = lo+(hi+lo)/2;
            int cnt = 0;
            for(int i = 0; i<m;i++){
                for(int j = 0; j < n && matrix[i][j]<=mid;j++){
                    cnt++;
                }
            }
            if(cnt < k){
                lo = mid + 1;
            }else{
                hi = mid -1;
            }
        }
        return lo;
    }

    //使用大顶堆排序
    public int kthSmallest1(int[][] matrix, int k) {
        // 使用大堆顶
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n2 - n1);
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                heap.add(matrix[i][j]);
            }
        }
        while (heap.size() > k) {
            heap.poll();
        }
        return heap.poll();
    }
}
