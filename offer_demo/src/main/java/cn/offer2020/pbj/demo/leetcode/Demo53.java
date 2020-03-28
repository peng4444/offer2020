package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo53
 * @Author: pbj
 * @Date: 2019/11/18 11:58
 * @Description: TODO  最大子序和
 */
public class Demo53 {
    /* *
     * 功能描述: 贪心
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 19:41
     */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < n; ++i) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }


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

    public int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int p = (left + right) / 2;
        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);
        return Math.max(Math.max(leftSum, rightSum), crossSum);
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

    public static void main(String args[]) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
