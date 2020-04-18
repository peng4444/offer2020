package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.HashMap;

/**
 * @ClassName: Demo219
 * @Author: pbj
 * @Date: 2020/4/18 12:06
 * @Description: TODO 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 */
public class Demo219 {

    //hash
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        int n = nums.length;
        if(k == 0) return false;
        if(n <= 0) return false;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            if(map.get(nums[i]) != null && (i - map.get(nums[i])) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }

    //暴力求解
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for(int i = 0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){
                if(nums[i]==nums[j]&&(j-i<=k)){
                    return true;
                }
            }
        }
        return false;
    }
}
