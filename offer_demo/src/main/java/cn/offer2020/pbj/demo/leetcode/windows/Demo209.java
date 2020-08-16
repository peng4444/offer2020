package cn.offer2020.pbj.demo.leetcode.windows;

/**
 * @pClassName: Demo209
 * @author: pengbingjiang
 * @create: 2020/8/16 20:16
 * @description: TODO 209.长度最小的子数组
 * 给定一个含有n个正整数的数组和一个正整数s，找出该数组中满足其和≥s的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 */
public class Demo209 {
    //双指针
    public int minSubArrayLen1(int s, int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        int ans = Integer.MAX_VALUE;
        int start = 0,end = 0;
        int sum = 0;
        while (end < len) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
    //暴力求解
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        int sum = 0;
        int min = len + 1;
        for(int i = 0;i<len;i++){
            for(int j = i;j<len;j++){
                sum+=nums[j];
                if (sum >= s) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
            sum = 0;
        }
        return min = min == len+1?0:min;
    }
}
