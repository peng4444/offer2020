package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.Stack;

/**
 * @ClassName: Demo415
 * @Author: pbj
 * @Date: 2020/4/14 21:36
 * @Description: TODO 415.字符串相加
 */
public class Demo415 {

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0, i = num1.length()-1,j = num2.length()-1;
        while(carry==1||i>=0||j>=0){
            int x = i<0?0:num1.charAt(i--)-'0';
            int y = j<0?0:num2.charAt(j--)-'0';
            sb.append((x+y+carry)%10);
            carry = (x+y+carry)/10;
        }
        return sb.reverse().toString();
    }

    public String addStrings1(String num1, String num2) {
        StringBuilder s = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i < 0 ? 0 : num1.charAt(i--) - '0';
            int y = j < 0 ? 0 : num2.charAt(j--) - '0';
            int sum = x + y + carry;
            s.insert(0, sum % 10);//插入到s字符串的第一个位置
            carry = sum / 10;
        }
        return s.toString();
    }

    public String addStrings2(String num1, String num2) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            carry += i >= 0 ? num1.charAt(i--) - '0' : 0;
            carry += j >= 0 ? num2.charAt(j--) - '0' : 0;
            stack.push(carry % 10);
            carry = carry / 10;
        }
        while (!stack.isEmpty())
            sb.append(stack.pop());
        return sb.toString();
    }

    public String addStrings3(String num1, String num2) {
        return addBinaryHelper(num1, num1.length() - 1, num2, num2.length() - 1, 0);
    }

    public String addBinaryHelper(String num1, int indexA, String num2, int indexB, int carry) {
        if (indexA < 0 && indexB < 0 && carry == 0)
            return "";
        carry += indexA < 0 ? 0 : num1.charAt(indexA--) - '0';
        carry += indexB < 0 ? 0 : num2.charAt(indexB--) - '0';
        int digit = carry % 10;
        carry = carry / 10;
        String res = addBinaryHelper(num1, indexA, num2, indexB, carry);
        return res + digit;
    }
}
