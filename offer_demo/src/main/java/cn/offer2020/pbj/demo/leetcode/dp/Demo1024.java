package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @ClassName: Demo1024
 * @Author: pbj
 * @Date: 2020/4/24 15:11
 * @Description: TODO 1024. 视频拼接
 */
public class Demo1024 {
    public int videoStitching(int[][] clips, int T) {
        int maxRight = -1;
        int result = 0;
        for(int i = 1; i<=T;i++){
            boolean found = false;
            if(maxRight>=i){
                found = true;
            }else{
                for(int j = 0;j<clips.length;j++){
                    if(i-1>=clips[j][0]&&i<=clips[j][1]){
                        found = true;
                        maxRight = Math.max(maxRight,clips[j][1]);
                    }
                }
                if(found){
                    result++;
                }else{
                    result = -1;
                    break;
                }
            }
        }
        return result;
    }

    public int videoStitching1(int[][] clips, int T) {
        //最坏的初始情况是，0~T是由(0,1),(1,2),(2,3)...(T-1,T)个片段构成，那么最大片段个数不会超过T+1
        //所以初始化最大值为T+1;
        int[] dp = new int[T+1];
        Arrays.fill(dp,T+1);
        //T=0的时候，片段为0
        dp[0] = 0;
        for(int i=0;i<=T;i++){
            for(int j=0;j<clips.length;j++){
                if(i>=clips[j][0]&&i<=clips[j][1]){
                    //如果i这个目标值在这个区间里，那么意味着dp[i]的最小片段，有可能是前一个最小情况时候+1，
                    //也就是dp[clips[j][0]] + 1
                    dp[i] = Math.min(dp[i],dp[clips[j][0]] + 1);
                }
            }
        }
        return dp[T]==T+1?-1:dp[T];

    }
}
