package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo415
 * @Author: pbj
 * @Date: 2020/4/14 21:36
 * @Description: TODO 415. 字符串相加
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
}
