package cn.offer2020.pbj.demo.leetcode.beibao;

/**
 * @ClassName: Demo474
 * @Author: pbj
 * @Date: 2020/4/13 15:43
 * @Description: TODO 474. 一和零
 */
public class Demo474 {

    public int findMaxForm(String[] strs, int m, int n) {
        if(strs==null||strs.length==0) return 0;
        int[][] dp = new int[m+1][n+1];
        for(String str : strs){
            int ones = 0,zero = 0;
            for(char c:str.toCharArray()){
                if(c=='0'){
                    zero++;
                }else{
                    ones++;
                }
            }
            for(int i = m;i>=zero;i--){
                for(int j = n;j>=ones;j--){
                    dp[i][j] = Math.max(dp[i][j],dp[i-zero][j-ones]+1);
                }
            }
        }
        return dp[m][n];
    }
}
