package cn.offer2020.pbj.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName: Demo561
 * @Author: pbj
 * @Date: 2020/4/20 16:49
 * @Description: TODO 561. 数组拆分 I
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 */
public class Demo561 {
    /* *
     * 功能描述: 数组排序，取两个中小的那个相加  复杂度O(nlog(n))
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2020/4/20 16:49
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0;i<nums.length;i=i+2){
            sum = sum + nums[i];
        }
        return sum;
    }
    //使用额外的空间
    public int arrayPairSum1(int[] nums) {
        int[] arr = new int[20001];
        int lim = 10000;
        for (int num: nums)
            arr[num + lim]++;
        int d = 0, sum = 0;
        for (int i = -10000; i <= 10000; i++) {
            sum += (arr[i + lim] + 1 - d) / 2 * i;
            d = (2 + arr[i + lim] - d) % 2;
        }
        return sum;
    }
}
