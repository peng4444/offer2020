package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @ClassName: Demo509
 * @Author: pbj
 * @Date: 2020/1/3 10:06
 * @Description: TODO 509.斐波那契数
 */
public class Demo509 {

    public int fib4(int n) {
        if(n<=1) return n;
        int pre = 0,res = 1,sum;
        for(int i = 1;i<=n;i++){
            sum = (res + pre)%1000000007;
            pre = res;
            res = sum;
        }
        return res;
    }

    /* *
     * 功能描述: 自底向上进行迭代
     * 时间复杂度O(N) 空间复杂度O(N)
     * @param: [N]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/3 10:33
     */
    public int fib2(int N) {
        if(N==0) return 0;
        int pre = 0,res = 1;
        for(int i=1;i<=N;i++){
            int temp = pre + res;
            pre = res;
            res = temp;
        }
        return res;
    }

    /* *
     * 功能描述: 记忆化自底向上的方法
     * 自底向上通过迭代计算斐波那契数的子问题并存储已计算的值，通过已计算的值进行计算。减少递归带来的重复计算。
     * 时间复杂度O(N) 空间复杂度O(N)
     * @param: [N]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/3 10:09
     */
    public int fib1(int N) {
        if(N<=1) return N;
        int[] fib = new int[N+1];
        fib[0] = 0;
        fib[1] = 1;
        for(int i = 2;i<=N;i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib[N];
    }
    /* *
     * 功能描述: 递归实现
     * 时间复杂度O(2^N),空间复杂度O(N)
     * @param: [N]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/3 10:07
     */
    public int fib(int N) {
        if (N == 0 || N == 1) {
            return N;
        }
        return fib(N - 1) + fib(N - 2);
    }
}
