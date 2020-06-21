package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Stack;
//224. 基本计算器
//实现一个基本的计算器来计算一个简单的字符串表达式的值。
//字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
public class Demo224 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int sign = 1,res = 0;
        int length = s.length();
        for(int i = 0;i<length;i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                int cur = ch - '0';
                while(i+1<length&&Character.isDigit(s.charAt(i+1)))
                    cur = cur * 10 + s.charAt(++i)-'0';
                res = res + sign * cur;
            }else if(ch=='+'){
                sign = 1;
            }else if(ch=='-'){
                sign = -1;
            }else if(ch=='('){
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            }else if(ch == ')'){
                res = stack.pop()*res+stack.pop();
            }
        }
        return res;
    }
}
