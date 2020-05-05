package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo487
 * @Author: pbj
 * @Date: 2020/5/5 11:26
 * @Description: TODO 487 最大连续1的个数II
 * 给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。
 */
public class Demo487 {

    //滑动窗口
     public int findMaxConsecutiveOnes(int[] nums) {
         int zeros = 0;
         int l = 0, r = 0, ret = 0;
         while(r < nums.length){
             if(nums[r]==0){
                 zeros++;
             }
             while (zeros > 1) {
                 if (nums[l++] == 0) {
                     zeros--;
                 }
             }
             ret = Math.max(ret, r - l + 1);
             r++;
         }
         return ret;
     }
}
