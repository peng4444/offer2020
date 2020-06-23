package cn.offer2020.pbj.demo.leetcode.dp;

public class Demo44 {
    class Solution {
        // 状态 dp[i][j] : 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配 (true 的话表示匹配)
        // 状态转移方程：
        //      1. 当 s[i] == p[j]，或者 p[j] == ? 那么 dp[i][j] = dp[i - 1][j - 1];
        //      2. 当 p[j] == * 那么 dp[i][j] = dp[i][j - 1] || dp[i - 1][j]    其中：
        //      dp[i][j - 1] 表示 * 代表的是空字符，例如 ab, ab*
        //      dp[i - 1][j] 表示 * 代表的是非空字符，例如 abcd, ab*
        // 初始化：
        //      1. dp[0][0] 表示什么都没有，其值为 true
        //      2. 第一行 dp[0][j]，换句话说，s 为空，与 p 匹配，所以只要 p 开始为 * 才为 true
        //      3. 第一列 dp[i][0]，当然全部为 false
        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            // 状态 dp[i][j] : 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
            boolean[][] dp = new boolean[m + 1][n + 1];

            // 初始化
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
            }

            // 状态转移
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }

            // 返回结果
            return dp[m][n];

        }
    }

    //贪心+动态规划
    public boolean isMatch(String s, String p) {
        if (p==null||p.isEmpty())return s==null||s.isEmpty();
        int i=0,j=0,istart=-1,jstart=-1,slen=s.length(),plen=p.length();
        //判断s的所有字符是否匹配
        while (i<slen){
            //三种匹配成功情况以及匹配失败返回false
            if (j<plen&&(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')){
                i++;
                j++;
            }else if (j<plen&&p.charAt(j)=='*'){
                istart=i;
                jstart=j++;
            }else if (istart>-1){
                i=++istart;
                j=jstart+1;
            }else {
                return false;
            }
        }
        //s中的字符都判断完毕，则认为s为空，此时需要p为空或者p中只剩下星号的时候，才能成功匹配。
        //如果p中剩余的都是*，则可以移除剩余的*
        while (j<plen&&p.charAt(j)=='*')j++;
        return j==plen;
    }
}
