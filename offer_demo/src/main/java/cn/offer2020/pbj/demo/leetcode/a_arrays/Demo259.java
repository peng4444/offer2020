package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @ClassName: Demo259
 * @Author: pbj
 * @Date: 2020/3/23 11:53
 * @Description: TODO Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 *
 * Return 2. Because there are two triplets which sums are less than 2:
 * ————————————————
 * 版权声明：本文为CSDN博主「jmspan」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/jmspan/article/details/51103149
 */
public class Demo259 {


    public class Solution {
        public int threeSumSmaller(int[] nums, int target) {
            int counts = 0;
            for (int i = 0; i < nums.length - 2; i++) {
                for (int j = i + 1; j < nums.length - 1; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] < target) counts++;
                    }
                }
            }
            return counts;
        }
    }


    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        int counts = 0;
        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        treemap.put(nums[0], 1);
        for(int i=1; i<nums.length-1; i++) {
            for(int j=i+1; j<nums.length; j++) {
                for(int count: treemap.headMap(target-nums[i]-nums[j]).values()) {
                    counts += count;
                }
            }
            Integer count = treemap.get(nums[i]);
            if (count == null) count = 1; else count ++;
            treemap.put(nums[i], count);
        }
        return counts;
    }

    public int threeSumSmaller2(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for(int i=0; i<nums.length-2; i++) {
            int j=i+1, k=nums.length-1;
            while (j<k) {
                if (nums[i]+nums[j]+nums[k] < target) {
                    count += k-j;
                    j ++;
                } else {
                    k --;
                }
            }
        }
        return count;
    }
}
