package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: Demo46
 * @Author: pbj
 * @Date: 2020/3/13 10:44
 * @Description: TODO 46. 全排列
 */
public class Demo46 {

    /* *
     * 功能描述:backtrack的公式：
     * result = []
        def backtrack(路径, 选择列表):
            if 满足结束条件:
            result.add(路径)
            return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2020/3/13 10:45
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if(list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int num : nums) {
            if(!list.contains(num)) {
                list.add(num);
                backtrack(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    //上面的优化+剪枝
    public List<List<Integer>> permute1(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        int[] visited = new int[nums.length];
        backtrack(res, nums, new ArrayList<Integer>(), visited);
        return res;

    }

    private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int[] visited) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            tmp.add(nums[i]);
            backtrack(res, nums, tmp, visited);
            visited[i] = 0;
            tmp.remove(tmp.size() - 1);
        }
    }
}
