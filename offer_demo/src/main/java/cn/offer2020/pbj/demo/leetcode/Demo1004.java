package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo1004
 * @Author: pbj
 * @Date: 2020/5/5 11:32
 * @Description: TODO 1004. 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。返回仅包含 1 的最长（连续）子数组的长度。
 * 将连续序列中的若干个0翻转成1使得总长度最长，可以用一个尺子找到一个区间，使得区间内包含k个0，而且区间长度最长，也就是采用滑动窗口的解法。
 */
public class Demo1004 {
    public int longestOnes(int[] A, int K) {
        int maxLen=0;
        int zeros=0;
        int left=0;
        int right=0;
        while(right<A.length){
            if(A[right]==0){
                zeros++;
            }
            while(zeros>K){
                if(A[left]==0){
                    zeros--;
                }
                left++;
            }
            maxLen=Math.max(maxLen,right-left+1);
            right++;
        }
        return maxLen;
    }
}
