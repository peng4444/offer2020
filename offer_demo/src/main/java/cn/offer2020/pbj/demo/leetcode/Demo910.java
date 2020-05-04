package cn.offer2020.pbj.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName: Demo910
 * @Author: pbj
 * @Date: 2020/5/4 15:03
 * @Description: TODO 910. 最小差值 II
 */
public class Demo910 {
    public int smallestRangrII(int[] A, int K) {
        int N = A.length;
        Arrays.sort(A);
        int ans = A[N - 1] - A[0];
        for (int i = 0; i < A.length - 1; i++) {
            int a = A[i], b = A[i + 1];
            int high = Math.max(A[N - 1] - K, a + K);
            int low = Math.min(A[0] + K, b - K);
            ans = Math.min(ans, high - low);
        }
        return ans;
    }
}
