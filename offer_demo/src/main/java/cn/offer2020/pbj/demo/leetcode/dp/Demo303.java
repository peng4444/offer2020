package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo303
 * @Author: pbj
 * @Date: 2020/4/11 10:44
 * @Description: TODO 303. 区域和检索 - 数组不可变
 */
public class Demo303 {

    private int[]  sums;
    public Demo303(int[] nums) {
        sums = new int[nums.length+1];
        for(int i = 1;i<=nums.length;i++){
            sums[i] = sums[i-1] + nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }
}
