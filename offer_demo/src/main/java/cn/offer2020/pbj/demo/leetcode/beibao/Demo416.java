package cn.offer2020.pbj.demo.leetcode.beibao;

/**
 * @ClassName: Demo416
 * @Author: pbj
 * @Date: 2020/4/13 14:44
 * @Description: TODO 416. 分割等和子集
 */
public class Demo416 {
    //01 背包问题 的解法
    public boolean canPartition(int[] nums) {
        int sum = countArraySum(nums);
        if(sum%2!=0)return false;
        int w = sum/2;
        boolean[] dp = new boolean[w+1];
        dp[0] = true;
        for(int num:nums){
            for(int i = w;i>=num;i--){
                dp[i] = dp[i]||dp[i-num];//使用或运算求
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

    //动态规划
    public boolean canPartition1(int[] nums) {
        //动态规划，背包问题，从nums中选择一部分数字组合，填满容量为sum/2的背包
        int n=nums.length;
        if(n == 0){
            return false;
        }

        //确定背包c的大小
        int sum = 0;
        for(int i=0; i<n; i++){
            sum+=nums[i];
        }
        int c = sum/2;

        //两个相等的整数的和一定为偶数
        if(sum%2==1){
            return false;
        }

        //动态规划
        //明确状态：dp[m][n] 考虑是否将第m个数字放入容量为n的背包
        boolean[][] dp = new  boolean[n][c+1];

        //状态初始化
        for(int i=0; i<=c; i++){
            if(i!=nums[0]){
                dp[0][i] = false;
            }else{
                dp[0][i] = true;
            }
        }

        //状态转移方程：dp[m][n] = dp[m-1][n] || dp[m-1][n-nums[m]]
        for(int i=1; i<n; i++){
            for(int j=0; j<=c; j++){
                dp[i][j] = dp[i-1][j];
                if(nums[i]<=j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }

            }
        }

        return dp[n-1][c];
    }
}
