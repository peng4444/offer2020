package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo647
 * @Author: pbj
 * @Date: 2020/4/18 15:16
 * @Description: TODO 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 */
public class Demo647 {
    private int count = 0;
    public int countSubstrings(String s) {
        for(int i = 0;i<s.length();i++){
            extendSubstring(s,i,i);
            extendSubstring(s,i,i+1);
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
