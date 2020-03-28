package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo645
 * @Author: pbj
 * @Date: 2020/3/10 15:52
 * @Description: TODO 645错误的集合
 */
public class Demo645 {

    //hashmap
    public int[] findErrorNums2(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();
        int dup = -1,missing = 1;
        for(int n:nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }
        for(int i = 1; i<=nums.length;i++){
            if(map.containsKey(i)){
                if(map.get(i)==2){
                    dup = i;
                }
            }else {
                missing = i;
            }
        }
        return new int[]{dup, missing};
    }

    //排序  排序 numsnums 数组后，相等的两个数字将会连续出现。此外，检查相邻的两个数字是否只相差 1 可以找到缺失数字。
    public int[] findErrorNums1(int[] nums){
        Arrays.sort(nums);
        int dup = -1,missing = 1;
        for(int i = 1; i< nums.length;i++){
            if(nums[i]==nums[i-1]){
                dup = nums[i];
            }else if(nums[i]>nums[i-1]+1){
                missing = nums[i-1]+1;
            }
        }
        return new int[] {dup,nums[nums.length-1]!=nums.length ? nums.length:missing};
    }
    //暴力 O(n^2)
    public int[] findErrorNums(int[] nums){
        int dup = -1,missing = -1;
        for(int i = 1;i<=nums.length;i++){
            int count = 0;
            for(int j = 0; j<nums.length;i++){
                if(nums[j]==i){
                    count++;
                }
            }
            if(count==2) dup = i;
            else if(count==0) missing = i;
            if(dup>0&&missing>0) break;//优化
        }
        return new int[] {dup,missing};
    }
}
