package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo413
 * @Author: pbj
 * @Date: 2020/4/11 11:00
 * @Description: TODO 413. 等差数列划分
 */
public class Demo413 {

    //动态规划
    public int numberOfArithmeticSlices(int[] A) {
        if(A==null||A.length==0){
            return 0;
        }
        int n = A.length;
        int[] dp = new int[n];
        for(int i = 2;i<n;i++){
            if(A[i]-A[i-1]==A[i-1]-A[i-2]){
                dp[i] = dp[i-1] + 1;
            }
        }
        int total = 0;
        for(int cnt :dp){
            total = total + cnt;
        }
        return total;
    }

    //递归
    int sum = 0;
    public int numberOfArithmeticSlices2(int[] A) {
        slices(A, A.length - 1);
        return sum;
    }
    public int slices(int[] A, int i) {
        if (i < 2)
            return 0;
        int ap = 0;
        if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
            ap = 1 + slices(A, i - 1);
            sum += ap;
        } else
            slices(A, i - 1);
        return ap;
    }

    //暴力法
    public int numberOfArithmeticSlices1(int[] A) {
        int count = 0;
        for (int s = 0; s < A.length - 2; s++) {
            int d = A[s + 1] - A[s];
            for (int e = s + 2; e < A.length; e++) {
                int i = 0;
                for (i = s + 1; i <= e; i++)
                    if (A[i] - A[i - 1] != d)
                        break;
                if (i > e)
                    count++;
            }
        }
        return count;
    }
}
