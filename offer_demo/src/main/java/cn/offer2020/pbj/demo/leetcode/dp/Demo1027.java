package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @pClassName: Demo1027
 * @author: pengbingjiang
 * @create: 2020/7/18 19:30
 * @description: TODO
 */
public class Demo1027 {

    //d[i][cha]表示到子数组[0..i]差值为cha的子数组(该子数组以A[i]元素结尾)的最长长度。
    public class Solution {
        public int longestArithSeqLength(int[] A) {
            //0 <= A[i] <= 10000 公差 【-10000，10000】 数组大小 10000*2+1；
            int[][] d = new int[A.length][20001];
            int putInPlace = 10000;
            int res = 0;
            for (int i = 1; i < A.length; i++) {
                for (int j = 0; j < i; j++) {
                    int cha = A[i] - A[j] < 0 ? A[j]-A[i] + putInPlace : A[i] - A[j];
                    d[i][cha] = d[j][cha] + 1;
                    res = Math.max(res, d[i][cha]);
                }
            }
            return res+1;
        }
    }

    // 这个题目真的坑爹，这个等差是可以有间隔的，并不是连续等差数组
    // [9,4,7,2,10] -> [4,7,10] 是等差，所以长度为 3
    public static int longestArithSeqLength(int[] A) {
        if (A== null|| A.length < 2) {
            return 0;
        }
        // 两个数才能构成的等差,所以结果值初始化为 2
        int res = 2;
        // dp[diff][idx] 差值为 diff 的序列长度
        Map<Integer, Integer>[] dp = new HashMap[A.length];
        for (int i = 0; i < A.length; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j];
                // 如果 j 的前面也存在 diff 的等差序列，把它也加上
                if (dp[j].containsKey(diff)) {
                    dp[i].put(diff, dp[j].get(diff) + 1);
                } else {
                    dp[i].put(diff, 2);
                }
                res = Math.max(res, dp[i].get(diff));
            }
        }
        return res;
    }

    public int longestArithSeqLength1(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int i: nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        int delta = max - min;
        int maxGap = 2 * delta;
        int[][] dp = new int[nums.length][maxGap + 1];
        int gap, res = 0;
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                gap = nums[i] - nums[j] + delta;
                dp[i][gap] = dp[j][gap] + 1;
                res = Math.max(res, dp[i][gap]);
            }
        }
        return res + 1;
    }
}
