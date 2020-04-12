package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo50
 * @Author: pbj
 * @Date: 2019/12/20 10:33
 * @Description: TODO Pow(x, n)
 */
public class Demo50 {

    /* *
     * 功能描述: 快速幂算法（循环）
     * @param: [x, n]
     * @return: double
     * @auther: pbj
     * @date: 2019/12/20 11:20
     */
    public double myPow4(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1/x;
            N = -N;
        }
        double ans = 1;
        double curr = x;
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 == 1) {
                ans = ans * curr;
            }
            curr = curr * curr;
        }
        return ans;
    }
    /* *
     * 功能描述: 快速幂算法（递归)
     * 时间复杂度O(log n)
     * @param: [x, n]
     * @return: double
     * @auther: pbj
     * @date: 2019/12/20 11:17
     */
    public double myPow3(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1/x;
            N = -N;
        }
        return fastPow(x, N);
    }

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /* *
     * 功能描述: 蛮力
     * 时间复杂度O(N)
     * @param: [x, n]
     * @return: double
     * @auther: pbj
     * @date: 2019/12/20 11:13
     */
    public double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1/x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++) {
            ans = ans *x;
        }
        return ans;
    }
    /* *
     * 功能描述: 递归实现，符号转换整数会溢出
     * @param: [x, n]
     * @return: double
     * @auther: pbj
     * @date: 2019/12/20 11:10
     */
    public static double myPow(double x, int n) {
        if(n==0) return 1;
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        if (n % 2 != 0) {
            return x * myPow(x, n - 1);
        } else {
            return myPow(x * x, n / 2);
        }
    }

    public static void main(String[] args) {
        System.out.println(myPow(2.0,10));
        System.out.println(myPow(2.0,-10));
        System.out.println(myPow(2.0,-2));
    }
}
