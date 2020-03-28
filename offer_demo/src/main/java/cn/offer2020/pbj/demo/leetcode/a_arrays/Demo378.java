package cn.offer2020.pbj.demo.leetcode.a_arrays;

/**
 * @ClassName: Demo378
 * @Author: pbj
 * @Date: 2020/3/7 14:10
 * @Description: TODO 有序矩阵中第K小的元素
 */
public class Demo378 {

    //优先队列

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
}
