package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo1018
 * @Author: pbj
 * @Date: 2020/1/5 14:30
 * @Description: TODO 可被 5 整除的二进制前缀
 */
public class Demo1018 {

    public List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> ans = new ArrayList<>();
        int sum = 0;
        for(int i = 0; i< A.length; i++){
            // 每次乘以 2 加上 A[i]，再对 5 取余
            sum = ((sum<<1)+A[i])%5;
            if(sum==0) ans.add(true);
            else ans.add(false);
        }
        return ans;
    }
    //可被5整除的数字只跟该数字的最后一位数字（为0或5）有关系，于是不需要具体的算出二进制前缀对应的
    // 十进制整数是多少，只需每次保留最后一位数字（保留用该数字对10取余的十进制整数的结果）就好，
    // 而下一个二进制前缀对应的十进制整数 = 上一次的结果左移一位（乘以2）的结果 + 这次的A[i]（0或者1，
    // 正好对应十进制的0或者1）的结果。
    public List<Boolean> prefixesDivBy51(int[] A) {
        List<Boolean> ans = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < A.length; i++) {
            num<<=1;
            num += A[i];
            num%=10;
            ans.add(num % 5 == 0);
        }
        return ans;
    }
}
