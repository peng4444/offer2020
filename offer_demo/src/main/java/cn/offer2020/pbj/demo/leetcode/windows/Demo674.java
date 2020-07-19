package cn.offer2020.pbj.demo.leetcode.windows;

/**
 * @ClassName: Demo674
 * @Author: pbj
 * @Date: 2020/4/12 15:08
 * @Description: TODO 674. 最长连续递增序列
 */
public class Demo674 {

    //滑动窗口
    class Solution {
        public int findLengthOfLCIS(int[] nums) {
            int ans = 0, anchor = 0;
            for (int i = 0; i < nums.length; ++i) {
                if (i > 0 && nums[i-1] >= nums[i]) anchor = i;
                ans = Math.max(ans, i - anchor + 1);
            }
            return ans;
        }
    }

    //dp
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length<2) return nums.length;
        int[] dp = new int[nums.length+1];
        int count = 1;
        int ans = 1;
        for(int i = 0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1]){
                ans++;
                if(ans>count) count = ans;
            }else{
                ans = 1;
            }
        }
        return count;
    }

    public int findLengthOfLCIS1(int[] nums) {
        int len = nums.length;
        if(len < 2) return len;
        int res = 1, start = 0;
        for(int i = 1; i < len; i++){
            if(nums[i] <= nums[i - 1]){
                res = Math.max(res, i - start);
                start = i;
            }
        }
        res = Math.max(res, len - start);
        return res;
    }
}
