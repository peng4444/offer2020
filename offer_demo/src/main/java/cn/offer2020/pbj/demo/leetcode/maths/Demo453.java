package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.Arrays;

/**
 * @ClassName: Demo453
 * @Author: pbj
 * @Date: 2020/4/15 09:38
 * @Description: TODO 453. 最小移动次数使数组元素相等
 */
public class Demo453 {
    /* *
     * 功能描述: 假设目前数组总和为sum，我们需要移动次数为m，那么整体数组总和将会增加m * (n - 1)，这里的n为数组长度，
     * 最后数组所有元素都相等为x，于是有：sum + m * (n - 1) = x * n (1)
     * 我们再设数组最小的元素为min_val，m = x - min_val​，即 ​x = m + min_val​带入(1)得：
     * m = sum - min_val * n​
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2020/4/15 9:39
     */
    public int minMoves(int[] nums) {
        int len = nums.length;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<len;i++){
            sum += nums[i];
            if(nums[i]<min){
                min = nums[i];
            }
        }
        return sum-min*len;
    }
    //dp
    public int minMoves1(int[] nums) {
        Arrays.sort(nums);
        int moves = 0;
        for (int i = 1; i < nums.length; i++) {
            int diff = (moves + nums[i]) - nums[i - 1];
            nums[i] += moves;
            moves += diff;
        }
        return moves;
    }
    //排序
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }

    //暴力法
    public class Solution {
        public int minMoves(int[] nums) {
            int min = 0, max = nums.length - 1, count = 0;
            while (true) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[max] < nums[i]) {
                        max = i;
                    }
                    if (nums[min] > nums[i]) {
                        min = i;
                    }
                }
                if (nums[max] == nums[min]) {
                    break;
                }
                for (int i = 0; i < nums.length; i++) {
                    if (i != max) {
                        nums[i]++;
                    }
                }
                count++;
            }
            return count;
        }
    }
}
