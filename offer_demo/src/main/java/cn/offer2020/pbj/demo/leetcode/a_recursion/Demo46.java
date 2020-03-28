package cn.offer2020.pbj.demo.leetcode.a_recursion;

import java.util.ArrayList;
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
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        help(ans,list,nums);
        return ans;
    }

    public void help(List<List<Integer>> ans, List<Integer> list, int[] nums) {
        if (ans.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0;i<nums.length;i++){
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                help(ans,list,nums);
                list.remove(list.size() - 1);
            }
        }
    }
}
