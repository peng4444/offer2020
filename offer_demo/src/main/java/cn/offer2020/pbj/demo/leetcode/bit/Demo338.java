package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo338
 * @Author: pbj
 * @Date: 2020/4/19 14:55
 * @Description: TODO 338.比特位计数
 * 给定一个非负整数num。对于0 ≤ i ≤ num范围中的每个数字i，计算其二进制数中的1的数目并将它们作为数组返回。
 */
public class Demo338 {
    //i&(i-1)可以去掉i最右边的一个1（如果有），因此i&(i-1）是比i小的，而且i&(i-1)的1的个数已经在前面算过了，所以i的1的个数就是i&(i-1)的1的个数加上1
    public int[] countBits(int num) {
        int[] ret = new int[num+1];
        for(int i = 1;i<=num;i++){
            ret[i] = ret[i&(i-1)]+1;
        }
        return ret;
    }

    //i>>1会把最低位去掉，因此i>>1 也是比i小的，同样也是在前面的数组里算过。当i的最低位是0，则i中1的个数和i>>1中1的个数相同；当i的最低位是1，i中1的个数是 i>>1中1的个数再加1
    public int[] countBits1(int num) {
        int[] res = new int[num + 1];
        for(int i = 0;i<= num;i++){
            res[i] = res[i >> 1] + (i & 1);  //注意i&1需要加括号
        }
        return res;
    }

    public int[] countBits2(int num) {
        int dp[] = new int[num+1];
        for (int i = 0; i <= num/2; i++) {
            dp[i*2] = dp[i];
            if(i*2+1 <= num)
                dp[i*2+1] = dp[i] + 1;
        }
        return dp;
    }
}
