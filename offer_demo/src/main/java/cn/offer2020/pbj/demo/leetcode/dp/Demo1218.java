package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo1218
 * @Author: pbj
 * @Date: 2020/4/12 15:50
 * @Description: TODO 1218. 最长定差子序列
 */
public class Demo1218 {

    public int longestSubsequence(int[] arr, int difference) {
        if(arr.length==1) return 1;
        int[] dp = new int[20001];
        int max = 1;
        for(int i:arr){
            if(i-difference<=10000&&i-difference>=-10000){
                dp[i+10000] = dp[i-difference+10000]+1;
                max = Math.max(max,dp[i+10000]);
            }else{
                dp[i+10000]=1;
            }
        }
        return max;
    }
}
