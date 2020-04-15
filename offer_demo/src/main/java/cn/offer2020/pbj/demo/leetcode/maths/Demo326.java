package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo326
 * @Author: pbj
 * @Date: 2020/4/15 11:11
 * @Description: TODO 326. 3的幂
 */
public class Demo326 {


    //循环迭代
    public class Solution {
        public boolean isPowerOfThree(int n) {
            if (n < 1) {
                return false;
            }

            while (n % 3 == 0) {
                n /= 3;
            }

            return n == 1;
        }
    }

    public boolean isPowerOfThree(int n) {
        return n>0&&(1162261467%n==0);
    }
}
