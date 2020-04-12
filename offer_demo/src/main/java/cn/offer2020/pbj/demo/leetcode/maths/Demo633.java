package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo633
 * @Author: pbj
 * @Date: 2019/12/28 16:37
 * @Description: TODO 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a*a + b*b = c。
 */
public class Demo633 {

    //双指针
    public boolean judgeSquareSum2(int c) {
        int i = 0,j=(int)Math.sqrt(c);
        while(i<=j){
            int powSum = i*i+j*j;
            if(powSum==c){
                return true;
            }else if(powSum>c){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }

    /* *
     * 功能描述: 二分查找
     * @param: [c]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/28 16:53
     */
    public boolean judgeSquareSum(int c) {
        for (int a = 0; a * a <= c; a++) {
            int b= c-(int)(a*a);
            if (binary_search(0, b, b)) {
                return true;
            }
        }
        return false;
    }

    public boolean binary_search(long s, long e, int n) {
        if(s>e) return false;
        long mid = s + (e - s) / 2;
        if(mid*mid==n) return true;
        if (mid * mid > n) {
            return binary_search(s, mid - 1, n);
        }
        return binary_search(mid + 1, e, n);
    }

    /* *
     * 功能描述: 使用Math.sqrt()函数
     * @param: [c]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/28 16:53
     */
    public boolean judgeSquareSum1(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b= Math.sqrt(c-(a*a));
            if(b==(int)b) return true;
        }
        return false;
    }

    /* *
     * 功能描述: 费马平方和定理:一个非负整数 c能够表示为两个整数的平方和，当且仅当 c的所有形如 4k+3的质因子的幂次均为偶数。
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/28 16:54
     */
    public class Solution {
        public boolean judgeSquareSum(int c) {
            for (int i = 2; i * i <= c; i++) {
                int count = 0;
                if (c % i == 0) {
                    while (c % i == 0) {
                        count++;
                        c /= i;
                    }
                    if (i % 4 == 3 && count % 2 != 0)
                        return false;
                }
            }
            return c % 4 != 3;
        }
    }

}
