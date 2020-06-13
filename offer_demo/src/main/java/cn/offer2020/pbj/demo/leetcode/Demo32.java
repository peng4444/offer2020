package cn.offer2020.pbj.demo.leetcode;

import java.util.Stack;

/**
 * @ClassName: Demo32
 * @Author: pbj
 * @Date: 2020/5/21 14:32
 * @Description: TODO 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 */
public class Demo32 {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if(n<2)return 0;
        int[] dp = new int[n];
        dp[0] = 0;
        int res = 0;
        for(int i = 1;i<n;i++){
            if(s.charAt(i)==')'){
                int j = i - dp[i-1] - 1;
                if(j>=0&&s.charAt(j)=='('){
                    dp[i] = dp[i-1] +2+((j-1>=0?dp[j-1]:0));
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public int longestValidParentheses1(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public int longestValidParentheses2(String s) {
        if(s == null || s.length() < 1)
            return 0;
        int left = 0, right = 0, max = 0;
        // 从左到右
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if(right > left){
                left = right = 0;
            }
        }
        left = right = 0;
        // 从右到左
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }
}
