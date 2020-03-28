package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Demo39
 * @Author: pbj
 * @Date: 2020/1/4 10:22
 * @Description: TODO 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates  中的每个数字在每个组合中只能使用一次
 */
public class Demo40 {

    /* *
     * 功能描述:
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/1/4 11:19
     */
    private static List<List<Integer>> ans;

    public List<List<Integer>> combinationSum(int[] candidate, int target) {
        ans = new ArrayList<>();
        if (candidate == null || candidate.length == 0) {
            return ans;
        }
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidate);
        helper(0, candidate, target, list);
        return ans;
    }

    private static void helper(int start, int[] candidate, int target, List<Integer> list) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidate.length; i++) {
                //因为这个数和上个数相同，所以从这个数开始的所以情况，在上个数里面都考虑过了，需要跳过
                if (i > start && candidate[i] == candidate[i - 1]) {
                    continue;
                }
                list.add(candidate[i]);
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                helper(i + 1, candidate, target - candidate[i], list);
                list.remove(list.size() - 1);
            }
        }
    }
}
