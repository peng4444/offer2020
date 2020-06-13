package cn.offer2020.pbj.demo.leetcode;

import java.util.Stack;

/**
 * @ClassName: Demo921
 * @Author: pbj
 * @Date: 2020/6/13 16:05
 * @Description: TODO 921. 使括号有效的最少添加
 * 给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。
 */
public class Demo921 {
    public int minAddToMakeValid(String S) {
        int bal = 0, res = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                bal++;
            } else {
                if (bal != 0) bal--;
                else res++;
            }
        }
        return bal + res;
    }

    /*
       遍历一遍字符串，
       1.如果遇到 （ ， 将其入栈，如果此时遍历到最后一个字符，那么栈的大小，即为答案，也就是所缺的 ） 的数量
       2.如果遇到 ） ， 如果栈顶不为空， 则将栈顶出栈，若为空，那么表明此时缺少一个 （ 与其匹配。
       其实，最开始，并没有想出来怎么写？然后看了一下这个题的标签 是 栈， 然后就会写了。这是什么原因 ？？
   */
    int minAddToMakeValid1(String S) {
        Stack<Character> st = new Stack<>();
        int len = S.length();

        int res = 0;
        for (int i=0; i<len; ++i) {
            char ch = S.charAt(i);
            if (ch == '(') {
                st.push(ch);
            } else {
                if (!st.empty()) {
                    st.pop();
                } else {
                    res++;
                }
            }
        }
        return (res+st.size());
    }
}
