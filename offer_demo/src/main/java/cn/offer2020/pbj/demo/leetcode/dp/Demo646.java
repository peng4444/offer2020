package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @ClassName: Demo646
 * @Author: pbj
 * @Date: 2020/4/12 10:47
 * @Description: TODO 646.最长数对链
 */
public class Demo646 {
    //贪心
    public int findLongestChain1(int[][] pairs) {
        Arrays.sort(pairs,(a,b)-> a[1]-b[1]);
        int res = 1,tmp = pairs[0][1];
        for(int i = 1;i < pairs.length;i++){
            if(pairs[i][0] > tmp){
                res++;
                tmp = pairs[i][1];
            }
        }
        return res;
    }
    //dp
    public int findLongestChain(int[][] pairs) {
        if(pairs==null||pairs.length==0) return 0;
        Arrays.sort(pairs,(a, b)->(a[0]-b[0]));
        int n = pairs.length;
        //设dp[i]表示以pairs[i][j]结尾的最长链的长度
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 1;i<n;i++){
            for(int j =0;j<i;j++){
                if(pairs[j][1]<pairs[i][0]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        int ans = 0;
        for (int x: dp) if (x > ans) ans = x;
        return ans;
    }
}
