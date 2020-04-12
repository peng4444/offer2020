package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Demo47
 * @Author: pbj
 * @Date: 2020/3/13 10:51
 * @Description: TODO 47. 全排列 II 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 在全排列I解法的基础上多了一个去重，思想是先对nums数组进行排列，然后在递归的过程中维护一个lastUsed变量，
 * 存储本次迭代的for循环中最近使用的数字，如果下一轮循环的数字是lastUsed，就说明重复了，就跳过本次循环，可以完美避免重复。
 */
public class Demo47 {
    private List<List<Integer>> anslist = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        getPermution(list, used, nums);
        return anslist;
    }

    public void getPermution(List<Integer> list, int[] used, int[] nums) {
        if (list.size() == nums.length - 1) {
            List<Integer> tmp = new ArrayList<>(list);
            for (int i = 0; i < nums.length; i++) {
                if (used[i] == 0) tmp.add(nums[i]);
            }
            anslist.add(tmp);
            return;
        }
        int lastUsed = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 如果nums[i] == lastUsed，则跳过本次循环
            if (used[i] == 0 && nums[i] != lastUsed) {
                used[i] = 1;
                list.add(nums[i]);
                getPermution(list, used, nums);
                lastUsed = nums[i];
                used[i] = 0;
                list.remove(list.size() - 1);
            }
        }
    }
}
