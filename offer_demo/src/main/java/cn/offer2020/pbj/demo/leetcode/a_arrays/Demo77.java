package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo77
 * @Author: pbj
 * @Date: 2020/1/4 11:35
 * @Description: TODO 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class Demo77 {
    private static List<List<Integer>> ans;

    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) {
            return ans;
        }
        List<Integer> list = new ArrayList<>();
        helper(n, k, 1, list);
        return ans;
    }

    public static void helper(int n, int k, int start, List<Integer> list) {
        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n-(k-list.size())+1; i++) {
            list.add(i);
            helper(n,k,i+1,list);
            list.remove(list.size() - 1);
        }
    }
}
