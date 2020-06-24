package cn.offer2020.pbj.demo.leetcode.hard;

/**
 * @pClassName: Demo214
 * @author: pengbingjiang
 * @create: 2020/6/24 09:10
 * @description: TODO 214. 最短回文串
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 */
public class Demo214 {
    public static String shortestPalindrome(String s) {
        StringBuilder r = new StringBuilder(s).reverse();
        String str = s + "#" + r;
        int[] next = next(str);
        String prefix = r.substring(0, r.length() - next[str.length()]);
        return prefix + s;
    }

    // next数组
    private static int[] next(String P) {
        int[] next = new int[P.length() + 1];
        next[0] = -1;
        int k = -1;
        int i = 1;
        while (i < next.length) {
            if (k == -1 || P.charAt(k) == P.charAt(i - 1)) {
                next[i++] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 回文，生成DP
     *
     * @param chas
     * @return
     */
    private int[][] getDp(char[] chas) {
        int n = chas.length;
        int[][] dp = new int[n][n];
        for (int j = 1; j < n; j++) {
            dp[j - 1][j] = chas[j - 1] == chas[j] ? 0 : 1;
            for (int i = j - 2; i > -1; i--) {
                if (chas[i] == chas[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp;
    }

    /**
     * 214. 最短回文串 Hard
     * @param s
     * @return
     */
    public String shortestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chas = s.toCharArray();
        int[][] dp = getDp(chas);
        int n = chas.length;
        char[] resChar = new char[n + dp[0][n - 1]];
        int l = 0, r = n - 1;
        int resL = 0, resR = resChar.length - 1;
        while (l <= r) {
            if (chas[l] == chas[r]) {
                resChar[resL++] = chas[l++];
                resChar[resR--] = chas[r--];
            } else if (dp[l][r - 1] <= dp[l + 1][r]) {
                resChar[resL++] = chas[r];
                resChar[resR--] = chas[r--];
            } else {
                resChar[resL++] = chas[l];
                resChar[resR--] = chas[l++];
            }
        }
        return String.valueOf(resChar);
    }
}
