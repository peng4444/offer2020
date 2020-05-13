package cn.offer2020.pbj.demo.leetcode.recursion;

/**
 * @ClassName: Demo509
 * @Author: pbj
 * @Date: 2020/1/3 10:06
 * @Description: TODO 509.斐波那契数
 */
public class Demo509 {

    /* *
     * 功能描述: 自底向上进行迭代
     * 时间复杂度O(N) 空间复杂度O(N)
     * @param: [N]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/3 10:33
     */
    public int fib3(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }
        int current = 0;
        int prev1 = 1;
        int prev2 = 1;
        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    /* *
     * 功能描述: 记忆化自顶向下的方法
     * 我们先计算存储子问题的答案，然后利用子问题的答案计算当前斐波那契数的答案。我们将递归计算，但是通过记忆化不重复计算已计算的值。
     * 时间复杂度O(N) 空间复杂度O(N)
     * @param: [N]
     * @return: int
     * @auther: pbj
     * @date: 2020/1/3 10:30
     */
    private Integer[] cache = new Integer[31];
    public int fib2(int N) {
        if (N <= 1) {
            return N;
        }
        cache[0] = 0;
        cache[1] = 1;
        return memoize2(N);
    }

    public int memoize2(int N) {
        if (cache[N] != null) {
            return cache[N];
        }
        cache[N] = memoize(N - 1) + memoize(N - 2);
        return memoize(N);
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
        if (N <= 1) {
            return N;
        }
        return memoize(N);
    }
    public int memoize(int N) {
        int[] cache = new int[N + 1];
        cache[1]=1;
        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }
        return cache[N];
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
