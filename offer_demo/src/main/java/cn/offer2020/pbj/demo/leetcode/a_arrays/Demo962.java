package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.Arrays;

/**
 * @ClassName: Demo962
 * @Author: pbj
 * @Date: 2020/1/5 13:30
 * @Description: TODO 最大宽度坡
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 */
public class Demo962 {

    //使用滑动模块 从最大长度开始依次长度-1尝试 本题情况下跑出100%
    public int maxWidthRamp2(int[] A) {
        int i = A.length - 1;
        while (i > 0) {
            int left = 0;
            int right = i;

            while (right < A.length) {
                if (A[left] <= A[right]) {
                    return right - left;
                } else {
                    left++;
                    right++;
                }
            }

            i--;
        }

        return 0;
    }
    //排序
    class Solution {
        public int maxWidthRamp(int[] A) {
            int N = A.length;
            Integer[] B = new Integer[N];
            for (int i = 0; i < N; ++i)
                B[i] = i;

            Arrays.sort(B, (i, j) -> ((Integer) A[i]).compareTo(A[j]));

            int ans = 0;
            int m = N;
            for (int i: B) {
                ans = Math.max(ans, i - m);
                m = Math.min(m, i);
            }

            return ans;
        }
    }

    //自己的解法 时间复杂度太大O(N^2)
    public int maxWidthRamp(int[] A) {
        if (A.length < 2) {
            return 0;
        }
        int result = 0;
        int max = 0;
        int len = A.length;
        for (int i = 0; i < len-1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (A[i] <= A[j]) {
                    max = j-i;
                }
            }
            result = Math.max(max, result);
        }
        return result;
    }
}
