package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo216
 * @Author: pbj
 * @Date: 2020/4/10 11:21
 * @Description: TODO 216. 组合总和 III
 */
public class Demo216 {

    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> list = new ArrayList<>();
        helper(k,n,1,list);
        return ans;
    }
    public void helper(int k,int n,int start,List<Integer> list){
        if(k==0&&n==0){
            ans.add(new ArrayList<>(list));
            return;
        }
        if(k==0||n==0) return;
        for(int i = start;i<=9;i++){
            list.add(i);
            helper(k-1,n-i,i+1,list);
            list.remove(list.size()-1);
        }
    }
}
