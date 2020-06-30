package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo70
 * @Author: pbj
 * @Date: 2019/11/16 22:17
 * @Description: TODO  LeetCode 70 爬楼梯 动态规划(https://www.cnblogs.com/fivestudy/p/11855853.html)
 */
public class Demo70 {

    /* *
     * 功能描述: 假设你正在爬楼梯。需要n阶你才能到达楼顶。每次你可以爬1或2个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     * 动态规划 时间复杂度O(N)，空间复杂度变成O(N)
     * @param: [n]
     * @return: int
     * @auther: pbj
     * @date: 2019/11/16 22:18
     */
    public static int climbStairs(int n) {
        if (n < 1) {
            System.out.println("请输入大于0的正整数");
        } else if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];//多加一位，考虑起始位置
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(1));
    }

    /* *
     * 功能描述: 时间复杂度O(N) 空间复杂度变成O(1)
     * @param: [n]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 17:10
     */
    public static int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int one_step_before = 2;
        int two_step_before = 1;
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = one_step_before + two_step_before;
            two_step_before = one_step_before;
            one_step_before = ans;
        }
        return ans;
    }

    /* *
     * 功能描述:暴力法
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/29 17:11
     */
    public class Solution {
        public int climbStairs2(int n) {
            return climb_Stairs(0, n);
        }

        public int climb_Stairs(int i, int n) {
            if (i > n) {
                return 0;
            }
            if (i == n) {
                return 1;
            }
            return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
        }
    }

    /* *
     * 功能描述: 暴力法+记忆递归
     * @param: [n]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/29 17:12
     */
    public int climbStairs3(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }

    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
}
