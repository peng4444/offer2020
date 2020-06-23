package cn.offer2020.pbj.demo.leetcode.dp;

public class Demo10 {

    //动态规划-自底向上
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j>1 &&p.charAt(j-1)=='*'&&dp[0][j-2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && isMatch(s.charAt(i - 1), p.charAt(j - 1));
                } else {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] && isMatch(s.charAt(i - 1), p.charAt(j - 2));
                }
            }
        }
        return dp[m][n];
    }


    //递归实现
    public boolean isMatch1(String s, String p) {
        if(s==null||p==null) return false;
        return isMatch1(s, s.length(), p, p.length());
    }

    boolean isMatch1(String s, int i, String p, int j) {
        if(j==0) return i==0;
        if(i==0) return j>1&&p.charAt(j-1)=='*'&&isMatch1(s,i,p,j-2);
        if(p.charAt(j-1)!='*'){
            return isMatch(s.charAt(i - 1), p.charAt(j - 1)) && isMatch1(s, i - 1, p, j - 1);
        }
        return isMatch1(s, i, p, j - 2) || isMatch1(s, i - 1, p, j) && isMatch(s.charAt(i - 1), p.charAt(j - 2));
    }

    boolean isMatch(char c1, char c2) {
        return c1 == c2 || c2 == '.';
    }

    public boolean isMatch2(String s, String p) {
        if(s==null||p==null) return false;
        return isMatch2( s, 0, p, 0);
    }
    boolean isMatch2(String s,int i,String p,int j){
        int m = s.length();
        int n = p.length();
        if(j==n){
            return i==m;
        }
        if(j==n-1||p.charAt(j+1)=='*'){
            return (i<m) && (p.charAt(j)=='.'||s.charAt(i)==p.charAt(j)) && isMatch2(s,i+1,p,j+1);
        }
        if(j<n-1&&p.charAt(j+1)=='*'){
            while((i<m)&&(p.charAt(j)=='.'||s.charAt(i)==p.charAt(j))){
                if(isMatch2(s,i,p,j+2)){
                    return true;
                }
                i++;
            }
        }
        return true;
    }
}
