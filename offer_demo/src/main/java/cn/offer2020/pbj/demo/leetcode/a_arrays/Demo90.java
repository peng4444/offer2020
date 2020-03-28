package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Demo90
 * @Author: pbj
 * @Date: 2020/1/4 12:23
 * @Description: TODO 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 解集不能包含重复的子集。
 */
public class Demo90 {
    private static List<List<Integer>> ans;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new ArrayList<>();
        if(nums.length<=0) return ans;
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums,0,list);
        return ans;
    }
    public static void helper(int[] nums,int start,List<Integer> list){
        ans.add(new ArrayList<>(list));
        for(int i = start;i<nums.length;i++){
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            helper(nums,i+1,list);
            list.remove(list.size()-1);
        }
    }
}
