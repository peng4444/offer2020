package cn.offer2020.pbj.demo.leetcode;

import java.util.Stack;

/**
 * @ClassName: Demo1021
 * @Author: pbj
 * @Date: 2020/4/27 10:17
 * @Description: TODO 1021. 删除最外层的括号
 * 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 */
public class Demo1021 {
    //这道题可以用栈来找出所有原语分解，然后对每个分解后的去除最左边和最右边的括号最后连在一起即可，但是这种复杂度较高。
    //由于题中说了S一定是合法的，我们就可以用统计左括号的个数的方法直接过滤掉每个原语的最外层括号。
    public String removeOuterParentheses(String S) {
        int left = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(' && left++ > 0) {
                res.append('(');
            }
            if (S.charAt(i) == ')' && --left > 0) {
                res.append(')');
            }
        }
        return res.toString();
    }
    //思路：遍历字符串，遇到左括号就入栈，遇到右括号就出栈，每次栈空的时候，都说明找到了一个原语，记录下每个原语的起始位置和结束位置，
    // 取原字符串在原语的起始位置+1到原语的结束位置的子串便得到原语删除了最外层括号的字符串，拼接，即可解出答案。
    public String removeOuterParentheses1(String S) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == '('){
                stack.push('(');
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    sb.append(S.substring(start + 1, i));
                    start = i + 1;
                }
            }
        }
        return sb.toString();
    }
}
