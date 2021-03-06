package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo78
 * @Author: pbj
 * @Date: 2020/1/4 10:10
 * @Description: TODO 78.子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 */
public class Demo78 {
    //暴力
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for(int i = 0;i<nums.length;i++){
            int all = res.size();
            for(int j= 0;j<all;j++){
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }

    //回溯
    private static List<List<Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        if(nums.length<=0) return ans;
        List<Integer> list = new ArrayList<>();
        helper(nums,0,list);
        return ans;
    }
    public static void helper(int[] nums,int start,List<Integer> list){
        ans.add(new ArrayList<>(list));
        for(int i = start;i<nums.length;i++){
            // 剪枝
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            helper(nums,i+1,list);
            list.remove(list.size()-1);
        }
    }
}
