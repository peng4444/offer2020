package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
        if(list.size()==nums.length){
            anslist.add(new ArrayList<>(list));
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

    public List<List<Integer>> result = new LinkedList<>();
    public List<List<Integer>> permuteUnique1(int[] nums) {
        if(nums.length == 0){
            return result;
        }
        //首先给数组排序
        Arrays.sort(nums);
        findUnique(nums,new boolean[nums.length],new LinkedList<Integer>());
        return result;
    }
    public void findUnique(int[] nums, boolean[] visited,LinkedList<Integer> trace){
        //结束条件
        if(trace.size() == nums.length){
            result.add(new LinkedList(trace));
            return ;
        }
        //选择列表
        for(int i = 0; i<nums.length; i++){
            //其次，我们已经选择过的不需要再放进去了
            if(visited[i]) continue;
            //接下来，如果当前节点与他的前一个节点一样，并其他的前一个节点已经被遍历过了，那我们也就不需要了。
            if(i>0 && nums[i] == nums[i-1] && visited[i-1]) break;
            //做出选择
            trace.add(nums[i]);
            visited[i] = true;
            findUnique(nums,visited,trace);
            //撤销选择
            trace.removeLast();
            visited[i] = false;
        }
    }
}
