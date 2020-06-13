package cn.offer2020.pbj.demo.leetcode;

import java.util.Stack;

/**
 * @ClassName: Demo865
 * @Author: pbj
 * @Date: 2020/6/13 15:47
 * @Description: TODO 856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 */
public class Demo865 {


    //分治法
    public int socreOfParenttheses(String s) {
        return help(s, 0, s.length());
    }

    public int help(String s, int i, int j) {
        int ans = 0, bal = 0;
        for (int k = i; k < j; ++k) {
            bal +=s.charAt(k)=='('?1:-1;
            if (bal == 0) {
                if(k-i==1) ans++;
                else ans += 2 * (help(s, i + 1, k));
                i = k + 1;
            }
        }
        return ans;
    }

    //栈
    public int scoreOfParentheses(String S) {
        Stack<Integer> stack = new Stack();
        stack.push(0); // The score of the current frame

        for (char c: S.toCharArray()) {
            if (c == '(')
                stack.push(0);
            else {
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w + Math.max(2 * v, 1));
            }
        }

        return stack.pop();
    }
    //统计核心的数目
    public int scoreOfParentheses2(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i-1) == '(')
                    ans += 1 << bal;
            }
        }

        return ans;
    }
}
