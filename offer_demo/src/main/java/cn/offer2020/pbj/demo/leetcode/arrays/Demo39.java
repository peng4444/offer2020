package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo39
 * @Author: pbj
 * @Date: 2020/1/4 10:22
 * @Description: TODO 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 */
public class Demo39 {

    /* *
     * 功能描述:
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/1/4 11:23
     */
    private static List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidate, int target) {
        ans = new ArrayList<>();
        if (candidate == null || candidate.length == 0) {
            return ans;
        }
        List<Integer> list = new ArrayList<>();
        helper(0,candidate, target, list);
        return ans;
    }

    private static void helper(int start,int[] candidate, int target, List<Integer> list) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidate.length; i++) {
                list.add(candidate[i]);
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                helper(i,candidate,target - candidate[i],list);
                list.remove(list.size() - 1);
            }
        }
    }
}
