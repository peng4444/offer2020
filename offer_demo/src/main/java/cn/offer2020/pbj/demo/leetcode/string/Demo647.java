package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo647
 * @Author: pbj
 * @Date: 2020/4/18 15:16
 * @Description: TODO 647.回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 */
public class Demo647 {
    private int count = 0;
    public int countSubstrings(String s) {
        for(int i = 0;i<s.length();i++){
            extendSubstring(s,i,i);//回文串长度为奇数
            extendSubstring(s,i,i+1);//回文串长度为偶数
        }
        return count;
    }

    private void extendSubstring(String s,int i,int j){
        while(i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j)){
            i--;
            j++;
            count++;
        }
    }

    public int countSubstrings0(String s) {
        int res = 0;
        int n = s.length();

        // dp[i][j] 表示[i,j]的字符是否为回文子串
        boolean[][] dp = new boolean[n][n];

        // 注意，外层循环要倒着写，内层循环要正着写
        // 因为要求dp[i][j] 需要知道dp[i+1][j-1]
        for(int i=n-1; i>=0; i--){
            for(int j=i; j<n; j++){

                // (s.charAt(i)==s.charAt(j) 时，当元素个数为1,2,3个时，一定为回文子串
                if(s.charAt(i)==s.charAt(j) && (j-i<=2 || dp[i+1][j-1])){
                    dp[i][j] = true;
                    res++;
                }
            }
        }

        return res;
    }

    public int countSubstrings1(String S) {
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2*N-1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    class Solution {
        public int countSubstrings(String S) {
            char[] A = new char[2 * S.length() + 3];
            A[0] = '@';
            A[1] = '#';
            A[A.length - 1] = '$';
            int t = 2;
            for (char c: S.toCharArray()) {
                A[t++] = c;
                A[t++] = '#';
            }

            int[] Z = new int[A.length];
            int center = 0, right = 0;
            for (int i = 1; i < Z.length - 1; ++i) {
                if (i < right)
                    Z[i] = Math.min(right - i, Z[2 * center - i]);
                while (A[i + Z[i] + 1] == A[i - Z[i] - 1])
                    Z[i]++;
                if (i + Z[i] > right) {
                    center = i;
                    right = i + Z[i];
                }
            }
            int ans = 0;
            for (int v: Z) ans += (v + 1) / 2;
            return ans;
        }
    }
}
