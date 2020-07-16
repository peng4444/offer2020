package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo342
 * @Author: pbj
 * @Date: 2020/4/19 11:09
 * @Description: TODO 342.4的幂
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 */
public class Demo342 {
    //这种数在二进制表示中有且只有一个奇数位为 1，例如 16（10000）。
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0b01010101010101010101010101010101) != 0;
    }

    public boolean isPowerOfFour2(int num) {
        if(num<0||(num & (num-1))!=0){
            return false;
        }
        return num%3==1;
        //return num>0&&((num&(num-1))==0)&&(num%3==1);
    }

    //用正则表达式进行匹配
    public boolean isPowerOfFour1(int num) {
        return Integer.toString(num,4).matches("10*");
    }
}
