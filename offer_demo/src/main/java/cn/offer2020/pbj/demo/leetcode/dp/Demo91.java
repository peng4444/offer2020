package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo91
 * @Author: pbj
 * @Date: 2020/4/11 11:34
 * @Description: TODO 91. 解码方法
 */
public class Demo91 {
    public int numDecodings(String s) {
        if(s==null||s.length()==0) return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0)=='0'?0:1;
        for(int i = 2;i<=n;i++){
            int one = Integer.valueOf(s.substring(i-1,i));
            if(one!=0) dp[i] = dp[i]+dp[i-1];
            if(s.charAt(i-2)=='0') continue;
            int two = Integer.valueOf(s.substring(i-2,i));
            if(two<=26) dp[i] = dp[i] +dp[i-2];
        }
        return dp[n];
    }
}
