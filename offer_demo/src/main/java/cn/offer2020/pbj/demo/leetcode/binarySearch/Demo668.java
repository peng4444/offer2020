package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName: Demo668
 * @Author: pbj
 * @Date: 2020/6/17 11:15
 * @Description: TODO 668.乘法表中第k小的数
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 */
public class Demo668 {
    //二分查找
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m*n+1;
        while(left<right){
            int mid = left + (right-left>>1);
            if(count(mid,m,n)>k){
                right = mid;
            }else if(count(mid,m,n)==k){
                right = mid;
            }else {
                left = mid +1;
            }
        }
        return left;
    }

    private static int count(int mid, int m, int n) {
        int res = 0;
        for(int i=1;i<=m;i++){
            res += Math.min(mid/i,n);
        }
        return res;
    }

    public int findKthNumber2(int m, int n, int k) {
        int lo = 1, hi = m * n; // 1是第1小的数(最小数)，m*n是第m*n小的数(最大数)，hi=m*n+1是考虑到k有可能是m*n
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if(!enough(mid,m,n,k)) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }

    //最大堆 超时
    public int findKthNumber1(int m, int n, int k) {
        Queue<Integer> queue =new PriorityQueue<>((a, b)->(b-a));
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if (queue.size()==k) {
                    if ((i+1)*(j+1)<queue.peek()) {
                        queue.poll();
                        queue.offer((i + 1) * (j + 1));
                    }
                }else queue.offer((i + 1) * (j + 1));
            }
        }
        return queue.peek();
    }

    //暴力求解 超时
    public int findKthNumber0(int m, int n, int k) {
        int[] table = new int[m*n];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                table[(i - 1) * n + j - 1] = i * j;
            }
        }
        Arrays.sort(table);
        return table[k-1];
    }
}
