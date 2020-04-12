package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo41
 * @Author: pbj
 * @Date: 2020/1/6 09:58
 * @Description: TODO 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *  [1,2,0]  --> 3
 */
public class Demo41 {

    public int firstMissingPositive(int[] nums) {
        int res = 1;
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
            while (map.containsKey(res)) {
                res++;
            }
        }
        return res;
    }

    //
    public int firstMissingPositive2(int[] nums) {
        Arrays.sort(nums);
        int target = 1;
        for(int i=0; i < nums.length; i++) {
            if(nums[i] <= 0) {
                continue;
            }
            if(nums[i] > target) {
                return target;
            }
            if(nums[i] == target) {
                target += 1;
                continue;
            }
        }
        return target;
    }
}
