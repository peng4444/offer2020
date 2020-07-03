package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo66
 * @Author: pbj
 * @Date: 2020/3/29 11:53
 * @Description: TODO 66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 */
public class Demo66 {
    public int[] plusOne1(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                break;
            }
        }
        //需要进位
        int[] res;
        if (digits[0] == 0) {
            res = new int[length+1];
            res[0] = 1;
        } else return digits;
        return res;
    }

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
