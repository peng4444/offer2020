package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo214
 * @Author: pbj
 * @Date: 2020/4/5 10:21
 * @Description: TODO
 */
public class Demo214 {
    //KMP 算法
    public String shortestPalindrome3(String s) {
        String ss = s + '#' + new StringBuilder(s).reverse();
        int max = getLastNext(ss);
        return new StringBuilder(s.substring(max)).reverse() + s;
    }

    //返回 next 数组的最后一个值
    public int getLastNext(String s) {
        int n = s.length();
        char[] c = s.toCharArray();
        int[] next = new int[n + 1];
        next[0] = -1;
        next[1] = 0;
        int k = 0;
        int i = 2;
        while (i <= n) {
            if (k == -1 || c[i - 1] == c[k]) {
                next[i] = k + 1;
                k++;
                i++;
            } else {
                k = next[k];
            }
        }
        return next[n];
    }

    public String shortestPalindrome2(String s) {
        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int i = 0;
        for (; i < n; i++) {
            if (s.substring(0, n - i).equals(r.substring(i))) {
                break;
            }
        }
        return new StringBuilder(s.substring(n - i)).reverse() + s;
    }

    //双指针
    public String shortestPalindrome1(String s) {
        int i = 0,j=s.length()-1;
        char[] c = s.toCharArray();
        while (j >= 0) {
            if (i == j) {
                continue;
            }
            if (c[i] == c[j]) {
                i++;
            }
            j--;
        }
        //此时代表整个字符串是回文串
        if (i == s.length()) {
            return s;
        }
        //后缀
        String suffix = s.substring(i);
        //后缀倒置
        String reverse = new StringBuilder(suffix).reverse().toString();
        //加到开头
        return reverse+s;
    }

    // 暴力法
    public String shortestPalindrome(String s) {
        int end = s.length()-1;
        for (; end > 0; end--) {
            if (isReverse(s, 0, end)) {
                break;
            }
        }
        return new StringBuilder(s.substring(end+1)).reverse()+s;
    }

    public boolean isReverse(String s, int start, int end) {
        char[] c = s.toCharArray();
        while (start < end) {
            if (c[start] != c[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
