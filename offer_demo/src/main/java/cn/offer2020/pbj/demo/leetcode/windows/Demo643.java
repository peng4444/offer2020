package cn.offer2020.pbj.demo.leetcode.windows;

/**
 * @pClassName: Demo643
 * @author: pengbingjiang
 * @create: 2020/8/24 19:10
 * @description: TODO
 */
public class Demo643 {
    //滑动窗口
    public double findMaxAverage1(int[] nums, int k) {
        double sum = 0;
        for(int i = 0;i<k;i++){
            sum += nums[i];
        }
        double res = sum;
        for (int i = k; i < nums.length; i++) {
            sum+=nums[i]-nums[i-k];
            res = Math.max(res, sum);
        }
        return res/k;
    }
    //累加求和
    public double findMaxAverage(int[] nums,int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for(int i = 1;i<nums.length;i++){
            sum[i] = sum[i-1]+nums[i];
        }
        double res = sum[k-1]*1.0/k;
        for(int i = k;i<nums.length;i++){
            res = Math.max(res,(sum[i]-sum[i-k])*1.0/k);
        }
        return res;
    }

    //Leetcode 644. Maximum Average Subarray II 最大平均区间2 解题报告 https://blog.csdn.net/MebiuW/article/details/76222743
    public class Solution {
        public double findMaxAverage(int[] nums, int k) {
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;

            // 寻找最值
            for (int n: nums) {
                max = Math.max(max, n);
                min = Math.min(min, n);
            }

            double last_mid = max, error = Integer.MAX_VALUE;
            while (max-min > 0.00001) {
                double mid = (max + min) / 2.0;
                if (check(nums, mid, k))
                    min = mid;
                else
                    max = mid;
                error = Math.abs(last_mid - mid);
                last_mid = mid;
            }
            return min;
        }


        // 判断这个区间里面，是否有一段大于等于K的长度的最长序列，满足要求，就是最大的累计和，减去最小的累积和
        public boolean check(int[] nums, double mid, int k) {
            double sum = 0, prev = 0, min_sum = 0;
            for (int i = 0; i < k; i++)
                sum += nums[i] - mid;
            if (sum >= 0)
                return true;
            for (int i = k; i < nums.length; i++) {
                sum += nums[i] - mid;
                prev += nums[i - k] - mid;
                min_sum = Math.min(prev, min_sum);
                if (sum >= min_sum)
                    return true;
            }
            return false;
        }
    }
}
