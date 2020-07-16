package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @pClassName: Demo326
 * @author: pengbingjiang
 * @create: 2020/7/16 10:30
 * @description: TODO 326.3的幂
 */
public class Demo326 {
    // 3的幂次的质因子只有3 ---> 热评第一条
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467%n == 0;
    }

    // 3的n次幂对应的3进制数---> 1  10 100 1000 ...
    public boolean isPowerOfThree1(int n) {
        return Integer.toString(n, 3).matches("10*$");
    }

    public boolean isPowerOfTree2(int n) {
        if(n<1) return false;
        while (n%3==0) n/=3;
        return n==1;
    }

    public boolean isPowerOfThree3(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
}
