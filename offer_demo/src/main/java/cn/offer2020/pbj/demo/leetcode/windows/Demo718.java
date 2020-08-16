package cn.offer2020.pbj.demo.leetcode.windows;

/**
 * @pClassName: Demo718
 * @author: pengbingjiang
 * @create: 2020/8/16 20:49
 * @description: TODO 718.最长重复子数组
 */
public class Demo718 {
    //dp 注意子数组和子序列的区别 如果是子序列的话 递推公式就是：dp[i][j] = max(dp[i-1][j-1]+(A[i-1] == B[j-1]?1:0),dp[i-1][j],dp[i][j-1])
    public int findLength(int[] A, int[] B) {
        if(A.length==0||B.length==0) return 0;
        int[][] dp = new int[A.length+1][B.length+1];
        int res = 0;
        for(int i = 1;i<=A.length;i++){
            for(int j = 1;j<=B.length;j++){
                if(A[i-1]==B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    res = Math.max(res,dp[i][j]);
                }
            }
        }
        return res;
    }

    //滑动窗口
    public int findLength1(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxLen = maxLength(A, B, i, 0, len);
            res = Math.max(res, maxLen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxLen = maxLength(A, B, 0, i, len);
            res = Math.max(res, maxLen);
        }
        return res;
    }

    private int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int res = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k=0;
            }
            res = Math.max(res, k);
        }
        return res;
    }
}
