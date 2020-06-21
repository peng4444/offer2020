package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Stack;

//227. 基本计算器 II
//给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
//表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
public class Demo227 {

    public int calculate1(String s) {
        char[] chars = s.replaceAll(" ", "").toCharArray();
        Stack<Integer> stack = new Stack<>();
        int res = 0, n = chars.length;
        // 跟踪前一个操作符
        char pre = ' ';
        for (int i = 0; i < n; i++) {
            // 因为整数可能不止一位，这个地方要注意把整数取完整
            StringBuilder builder = new StringBuilder();
            while (i < n && Character.isDigit(chars[i])) {
                builder.append(chars[i++]);
            }
            int num = Integer.parseInt(builder.toString());
            // 然后查看其前一个运算符分情况讨论
            if (pre == '+') {
                stack.push(num);
            } else if (pre == '-') {
                // 把减法全换成加法统一处理
                stack.push(-num);
            } else if (pre == '*') {
                // 乘法除法优先级高先处理
                stack.push(stack.pop() * num);
            } else if (pre == '/') {
                stack.push(stack.pop() / num);
            } else {
                stack.push(num);
            }
            if (i < n) {
                pre = chars[i];
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public int calculate(String s) {
        boolean op = true;
        s = s.replaceAll(" ","");
        Stack<Integer> stack = new Stack();
        Integer tmp = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='+'){
                stack.push(tmp);
                tmp = 0;
                op = true;
            }else if(c=='-'){
                stack.push(tmp);
                tmp = 0;
                op = false;
            }else if(c=='*'){
                int tmp2 = 0;
                while(++i<s.length()&&(s.charAt(i)>='0'&&s.charAt(i)<='9')){
                    tmp2 = tmp2*10 + (s.charAt(i)-'0');
                }
                tmp = tmp*tmp2;
                op = true;
                i--;
            }else if(c=='/'){
                int tmp2 = 0;
                while(++i<s.length()&&(s.charAt(i)>='0'&&s.charAt(i)<='9')){
                    tmp2 = tmp2*10 + (s.charAt(i)-'0');
                }
                tmp = tmp/tmp2;
                op = true;
                i--;
            }else{
                int app = s.charAt(i)-'0';
                tmp = tmp*10 + (op?app:-app);
            }
        }
        int res = 0;
        stack.push(tmp);
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }
}
