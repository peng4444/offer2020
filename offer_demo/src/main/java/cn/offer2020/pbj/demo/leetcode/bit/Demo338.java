package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo338
 * @Author: pbj
 * @Date: 2020/4/19 14:55
 * @Description: TODO 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 */
public class Demo338 {
    public int[] countBits(int num) {
        int[] ret = new int[num+1];
        for(int i = 1;i<=num;i++){
            ret[i] = ret[i&(i-1)]+1;
        }
        return ret;
    }
}
