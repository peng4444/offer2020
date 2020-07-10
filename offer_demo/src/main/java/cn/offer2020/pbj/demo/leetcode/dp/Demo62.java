package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo62
 * @Author: pbj
 * @Date: 2020/3/15 14:33
 * @Description: TODO 62. 不同路径
 */
public class Demo62 {
    //排列组合
    public int uniquePaths2(int m, int n) {
        //只跟第几行第几列有关，从m+n-2步中抽出m-1步
        long ans=1;
        for(int i=0;i<Math.min(m-1,n-1);i++){
            ans*=m+n-2-i;
            ans/=i+1;
        }
        return (int)ans;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0;i<m;i++){
            dp[i][0] = 1;
        }
        for(int i = 0;i<n;i++){
            dp[0][i] = 1;
        }
        for(int i = 1; i< m;i++){
            for(int  j = 1; j<n;j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public int uniquePaths1(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i =0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(i==0||j==0){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
