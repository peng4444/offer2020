package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: Demo491
 * @Author: pbj
 * @Date: 2020/4/12 15:19
 * @Description: TODO
 */
public class Demo491 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans,new ArrayList<>(),nums,0);
        return ans;
    }
    private void dfs(List<List<Integer>> ans ,List<Integer> list,int[] nums,int cur){
        if(list.size()>1) ans.add(new ArrayList<>(list));
        Set<Integer> set = new HashSet<>();
        for(int i = cur;i<nums.length;i++){
            if(set.contains(nums[i])) continue;
            if(list.size()==0||nums[i]>=list.get(list.size()-1)){
                set.add(nums[i]);
                list.add(nums[i]);
                dfs(ans,list,nums,i+1);
                list.remove(list.size()-1);
            }
        }
    }
}
