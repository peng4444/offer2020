package cn.offer2020.pbj.demo.leetcode;

import java.util.Scanner;

/**
 * @ClassName: Demo639
 * @Author: pbj
 * @Date: 2020/5/29 14:52
 * @Description: TODO 639. 解码方法 2
 * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
 *除了上述的条件以外，现在加密字符串可以包含字符 '*'了，字符'*'可以被当做1到9当中的任意一个数字。
 * 给定一条包含数字和字符'*'的加密信息，请确定解码方法的总数。
 * 同时，由于结果值可能会相当的大，所以你应当对109 + 7取模。
 */
public class Demo639 {
    /* *
     * 功能描述:
     *   dp[i]表示[0...i]中的解码数
  dp[i] = dp[i - 1] * [第i个单独解码的个数] + dp[i - 2] * [第i-1 和第i一起解码的个数]
  包含有这样的00 / 30 / 40 ...无法解码
     * @param: [args]
     * @return: void
     * @auther: pbj
     * @date: 2020/5/29 15:07
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (s == null || s.length() == 0) {
            System.out.println(0);
        }
        if (s.length() == 1) {
            System.out.println(s.charAt(0)=='0'?0:s.charAt(0)=='*'?9:1);
        }
        if (s.charAt(0) == '0') {
            System.out.println(0);
        }
        char[] chars = s.toCharArray();
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = chars[0] == '*' ? 9 : 1;
        for (int i = 2; i <= chars.length; i++) {
            char first = chars[i - 2];//表示第i - 1个字符
            char second = chars[i - 1];//表示第i个字符
            //对于dp[i - 1]
            if (second == '*') {
                dp[i] += 9 * dp[i - 1];
            } else if (second > '0') {
                dp[i] += dp[i - 1];
            }
            //对于dp[i - 2]
            if (first == '*') {
                if (second == '*') {
                    dp[i] += 15 * dp[i - 2];
                } else if (second <= '6') {
                    dp[i] += 2 * dp[i - 2];
                } else {
                    dp[i] += dp[i - 2];
                }
            } else if (first == '1' || first == '2') {
                if (second == '*') {
                    dp[i]+=first=='1'?9*dp[i-2]:6*dp[i-2];
                }else if((first-'0')*10+second-'0'<=26){
                    dp[i] += dp[i - 2];
                }
            }
            dp[i]%=1000000007;
        }
        System.out.println(dp[s.length()]);
    }
}
