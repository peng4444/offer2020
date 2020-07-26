package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @pClassName: Demo1014
 * @author: pengbingjiang
 * @create: 2020/7/26 10:28
 * @description: TODO
 */
public class Demo1014 {

    /*
可以先从暴力法中找思路
暴力法：算出每两对的 得分取最大值
        for (j : 1 -> A.length) {
            for (i : 0 -> j) {
                maxvalue = Math.max(maxvalue, A[j] - j   +   A[i] + i);
           }
        }
当然是超时的 -------> 优化 贪心算法
从暴力法可以看出在每次计算过程中 在每个 j 都固定 A[j] -j 计算它前面的 A[i] + i 的最大值 (i : 1 -> j)

贪心算法：将问题分解为 A[i] + i 和 A[j] - j 的最大值  i < j
那我们在一次遍历 j 的时候只需要不断保存并且更新 A[i] + i 的值即可求出最大值


*/
    class Solution {
        public int maxScoreSightseeingPair(int[] A) {
            int maxscore = Integer.MIN_VALUE, left = A[0];
            for (int i = 1; i < A.length; i++) {
                maxscore = Math.max(maxscore, A[i] - i + left);  //更新 最大值
                left = Math.max(left, A[i] + i);  //更新 A[i] + i
            }
            return maxscore;
        }
    }

    public int maxScoreSightseeingPair1(int[] A) {
        int[] dp = new int[A.length];
        int ans = 0;
        dp[0] = 0;
        for (int i = 1; i < A.length; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-1]+i-1);
            ans = Math.max(ans, dp[i] + A[i] - 1);
        }
        return ans;
    }

    //超时
    public int maxScoreSightseeingPair(int[] A) {
        int ans = Integer.MIN_VALUE;
        for(int i = 0;i<A.length-1;i++){
            for(int j = i+1;j<A.length;j++){
                ans = Math.max(ans,A[i]+A[j]+i-j);
            }
        }
        return ans;
    }
}
