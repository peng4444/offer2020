package cn.offer2020.pbj.demo.job.alibaba;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @pClassName: Alibaba0803
 * @author: pengbingjiang
 * @create: 2020/8/4 08:21
 * @description: TODO [阿里笔试0803 题解](https://www.nowcoder.com/discuss/466066)
 */
public class Alibaba0803 {
    /*
    *
    有n个人，每人有对应的钱币，有m个房子，每个房子有对应的价值和舒适度。
    每个人只能买一个房子，每个房子只能被一个人买，求最大的舒适度和。
    解法：
    贪心，首先对每个人拥有的钱币大小排序，再对房子按价格大小排序。
    每个人买他能买到的价格内最大舒适度的房子，总和即为所求
    */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] money = new int[n];
        for (int i = 0; i < n; i++) {
            money[i] = sc.nextInt();
        }
        int[][] house = new int[m][2];
        for (int i = 0; i < m; i++) {
            // 第一个维度是舒适度，第二个维度是价格
            house[i][0] = sc.nextInt();
            house[i][1] = sc.nextInt();
        }
        Arrays.sort(house,(o1,o2)->(o1[1]-o2[1]));//按照房子价格排序
        Arrays.sort(money);// 钱少的人先选
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        long res = 0;
        int index = 0;
        for (int i = 0; i < money.length; i++) {
            int cur_money = money[i];
            while (index < house.length && house[index][1] <= cur_money) {
                queue.add(house[index][0]);
                index++;
            }
            int add =0;
            if (!queue.isEmpty()) {
                add = queue.poll();
            }
            res += add;
        }
        System.out.println(res);
    }

    /**
    *
     * 给定一个字符串，字符串只包含abcdef 6个字母，求满足下列规则的最长子序列：
     * 1.a必须在c,e前，c必须在e前；
     * 2.b必须在d,f前, d必须在f前;
     * 解法：
     * 两个条件相互独立，可以首先把输入字符串拆分成两个只包含ace的字符串和bdf的字符串
     * 然后求每个字符串的最长不下降子序列，和即为所求。
     * 最长不下降子序列的求法应用二分优化，不会的可以看看leetcode最长上升子序列的解法。
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] cs = sc.next().toCharArray();
        int len = cs.length;
        int l1 = 0,l2 = 0;
        char[] c1 = new char[len];
        char[] c2 = new char[len];
        for (char c : cs) {
            if(c=='a'||c=='c'||c=='e') c1[l1++] = c;
            if(c=='b'||c=='d'||c=='f') c2[l2++] = c;
        }
        System.out.println(lIS(c1,l1)+lIS(c2,l2));
    }

    private static int lIS(char[] arr, int len) {
        int[] dp = new int[arr.length];
        int res = len;
        for (int i = 0; i < len; i++) {
            char c = arr[i];
            int l = 0,r = res;
            while (l < r) {
                int m = l + (r - l) /2;
                if(dp[m]<=c) l = m + 1;
                else r = m;
            }
            dp[l] = c;
            if(l==len) res++;
        }
        return res;
    }
}
