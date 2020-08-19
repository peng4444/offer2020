package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;

/**
 * @ClassName: Demo16
 * @Author: pbj
 * @Date: 2020/3/23 11:35
 * @Description: TODO 16.最接近的三数之和
 */
public class Demo16 {

    //暴力
    public int threeSumClosest(int[] nums, int target) {
        int mindis,ans;
        mindis = Math.abs(nums[0] + nums[1] + nums[2]-target);
        ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (Math.abs(target - nums[i] - nums[j] - nums[k]) < mindis) {
                        ans = nums[i] + nums[k] + nums[j];
                        mindis = Math.abs(ans - target);
                    }
                }
            }
        }
        return ans;
    }

    //排序和双指针
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i+1,end = nums.length-1;
            while (start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    return ans;
                }
            }
        }
        return ans;
    }
}
