package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Demo217
 * @Author: pbj
 * @Date: 2020/1/14 13:59
 * @Description: TODO 存在重复元素 给定一个整数数组，判断是否存在重复元素。
 */
public class Demo217 {

    public boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (hashMap.containsKey(nums[i])) {
                return true;
            }
            hashMap.put(nums[i], i);
        }
        return false;
    }

    //set集合不重复 可能更快
    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() < nums.length;
    }
    //排序
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }
    // Time Limit Exceeded
    public boolean containsDuplicate3(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] == nums[i]) return true;
            }
        }
        return false;
    }

}
