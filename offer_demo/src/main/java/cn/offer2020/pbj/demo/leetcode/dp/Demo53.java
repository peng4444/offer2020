package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo53
 * @Author: pbj
 * @Date: 2019/11/18 11:58
 * @Description: TODO  53.最大子序和
 * 给定一个整数数组nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * [LeetCode一题多解|53.最大子数组和：五种解法完全手册](https://mp.weixin.qq.com/s?__biz=MzA5ODk3ODA4OQ==&mid=2648167638&idx=1&sn=d6e504ab46757b1a35a5c8c7734d6f27&chksm=88aa2490bfddad86277a3958d623d146967036c0f59fef52bd20c80e31b0392d36024bbf68fb&mpshare=1&scene=23&srcid=0719HoKG9QngwsA8RClPUFra&sharer_sharetime=1595126396244&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
 */
public class Demo53 {

    /* *
     * 功能描述: dp
     * @param: [nums]
     * @return: java.lang.Integer
     * @auther: pbj
     * @date: 2019/12/29 19:27
     */
    public static Integer maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /* *
     * 功能描述: 贪心
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 19:41
     */
    public int maxSubArray2(int[] nums) {
        int sum = 0; // 计算当前的部分子数组和
        int res = Integer.MIN_VALUE;
        for (int n : nums) {
            // 如果部分和小于零，直接舍弃，从零开始重新累加
            if (sum < 0) {
                sum = 0;
            }
            sum += n; // 加上当前元素
            res = Math.max(res, sum);
        }
        return res;
    }

    public static int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    /* *
     * 功能描述: 分治法
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 19:39
     */
    public int maxSubArray1(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    //// 计算 nums[lo..hi] 的最大子数组和
    public int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int p = (left + right) / 2;
        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    //暴力法改进
    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                res = Math.max(res, sum);
            }
        }
        return res;
    }
    //暴力法 两层循环用于穷举所有可能的子数组，一层循环用于计算某个子数组的和
    public int maxSubArray0(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        // 穷举各种可能的子数组 nums[i..j]
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 计算子数组 nums[i..j] 的和
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                res = Math.max(res, sum);
            }
        }
        return res;
    }
}
