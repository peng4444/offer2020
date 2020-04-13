package cn.offer2020.pbj.demo.leetcode.beibao;

/**
 * @ClassName: Demo494
 * @Author: pbj
 * @Date: 2020/4/13 14:56
 * @Description: TODO 494. 目标和
 */
public class Demo494 {

    public int findTargetSumWays1(int[] nums, int S) {
        int sum = countArraySum(nums);
        if(sum<S||(sum+S)%2==1){
            return 0;
        }
        int w = (sum + S)/2;
        int[] dp = new int[w+1];
        dp[0] = 1;
        for(int num:nums){
            for(int i = w;i>=num;i--){
                dp[i] = dp[i] + dp[i-num];
            }
        }
        return dp[w];
    }
    private int countArraySum(int[] nums){
        int sum = 0;
        for(int num:nums){
            sum = sum + num;
        }
        return sum;
    }

    public int findTargetSumWays2(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }

    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }


    //枚举
    public class Solution {
        int count = 0;
        public int findTargetSumWays(int[] nums, int S) {
            calculate(nums, 0, 0, S);
            return count;
        }
        public void calculate(int[] nums, int i, int sum, int S) {
            if (i == nums.length) {
                if (sum == S)
                    count++;
            } else {
                calculate(nums, i + 1, sum + nums[i], S);
                calculate(nums, i + 1, sum - nums[i], S);
            }
        }
    }
}
