package cn.offer2020.pbj.demo.job.alibaba;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @pClassName: Demo0729
 * @author: pengbingjiang
 * @create: 2020/7/29 16:14
 * @description: TODO [阿里笔试7.29 线性复杂度](https://www.nowcoder.com/discuss/462000)
 */
public class Demo0729 {
    /*
    * 给n个恐龙蛋及恐龙蛋的大小，按从大到小排列，第i个恐龙蛋每天增大i，问最少几天会出现两个同样大小的恐龙蛋。
    * 分析：根据题意，相邻的恐龙蛋增长速度相差为1。因此，如果相邻蛋的大小差异数组d为{d1, d2, …, dn-1}，那么每经过1天，数组d中每个元素都会减少1。
    * 当d中首次出现0元素时，就是首次出现两个相同大小恐龙蛋的时候。这很容易证明，因为根据题设，d中的元素均非负。在d中出现0元素之前，恐龙蛋大小是严格递减的，不存在相同大小的蛋。
    * 很显然，d中首次出现0元素的天数，就是数组d中的最小值。数组d及其最小值可以在O(n)时间内计算得到。算法的时间复杂度为O(n)。
    */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }
            int res = Integer.MAX_VALUE;
            for (int j = 0, prev = Integer.MAX_VALUE, curr = 0; j < n; j++) {
                res = Math.min(res, prev - arr[j]);
                prev = arr[j];
            }
            System.out.println("最小："+res+"天");
        }
    }

    /*
    * n个客栈依次排列，给出n-1条路的权重。从任意一处出发，每走过一条路，该路的权重减1，但得到1点利益。不能走权重为0的路。求最大利益。
        分析：题中涉及到一个图An，An的n个顶点是n个客栈。其中第k-1个客栈到第k个客栈的边的权重用v[k-1]表示。
        我们采用动态规划去求解这个问题。假设Lk是由第0,…,k个客栈所组成的An的子图，Rk是由第k, …,n-1个客栈所组成的An的子图。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int res = 0;
        int n = sc.nextInt();

    }
}
