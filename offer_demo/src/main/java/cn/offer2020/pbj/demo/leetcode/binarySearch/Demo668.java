package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo668
 * @Author: pbj
 * @Date: 2020/6/17 11:15
 * @Description: TODO 668. 乘法表中第k小的数
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 */
public class Demo668 {

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

    public int findKthNumber1(int m, int n, int k) {
        int lo = 1, hi = m * n + 1, cur_num = 0; // 1是第1小的数(最小数)，m*n是第m*n小的数(最大数)，hi=m*n+1是考虑到k有可能是m*n
        while (lo < hi) {
            cur_num = (lo+hi)/2;  // 测试值，我们需要判断cur_num是否就是第k小的数字。
            int pass = smallNumCount(m, n, cur_num);  // smallNumCount我们需要判断小于等于cur_num的数字的个数。
            if (pass < k) lo = cur_num + 1;  // 如果小于等于cur_num的数字个数不足k，那么说明cur_num偏小，第k小的数字出现在[cur_num+1, hi]
            else hi = cur_num;  // 如果pass>=k，说明cur_num可能比第k小的数字大，也可能cur_num就是第k小的数字，所以第k小的数字出现在[lo, cur_num]
        }
        return lo;
    }
    // m*n的乘法表中，不大于数值target的元素个数
    private int smallNumCount(int m, int n, int target) {
        int cnt = 0;
        for (int i = 1; i <= m; ++i) cnt += Math.min(target/i, n);
        return cnt;
    }
}
