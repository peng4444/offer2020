package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo66
 * @Author: pbj
 * @Date: 2020/3/29 11:53
 * @Description: TODO 66. 加一
 */
public class Demo66 {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i]%10;
            if(digits[i]!=0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] =1;
        return digits;
    }
}
