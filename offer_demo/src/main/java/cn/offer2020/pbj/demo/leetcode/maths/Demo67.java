package cn.offer2020.pbj.demo.leetcode.maths;

import java.math.BigInteger;

/**
 * @ClassName: Demo67
 * @Author: pbj
 * @Date: 2020/4/14 21:27
 * @Description: TODO 67.二进制求和
 */
public class Demo67 {

    public String addBinary(String a, String b) {
        int i = a.length()-1,j = b.length()-1,carry = 0;
        StringBuilder sb = new StringBuilder();
        while(carry==1||i>=0||j>=0){
            if(i>=0&&a.charAt(i--)=='1'){
                carry++;
            }
            if(j>=0&&b.charAt(j--)=='1'){
                carry++;
            }
            sb.append(carry%2);
            carry/=2;
        }
        return sb.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }

    public String addBinary1(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }
}
