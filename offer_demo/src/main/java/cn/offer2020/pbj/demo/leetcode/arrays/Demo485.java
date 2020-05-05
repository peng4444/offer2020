package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo485
 * @Author: pbj
 * @Date: 2020/3/7 10:59
 * @Description: TODO 485.最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 */
public class Demo485 {
    //自己写的
    public int findMaxConsecutiveOnes0(int[] nums) {
        int curLen = 0;
        int len = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]==1){
                curLen ++;
            }else{
                curLen = 0;
            }
            len = Math.max(len,curLen);
        }
        return len;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0,maxCount =0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count = count + 1;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }

        }
        return Math.max(maxCount, count);
    }
    //
    public int findMaxConsecutiveOnes1(int[] nums){
        int max = 0,cur = 0;
        for(int i = 0; i< nums.length;i++){
            cur = nums[i]==0?0:cur+1;
            max = Math.max(cur,max);
        }
        return max;
    }
}
