package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Demo448
 * @Author: pbj
 * @Date: 2020/3/10 16:57
 * @Description: TODO 448. 找到所有数组中消失的数字
 */
public class Demo448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        Map<Integer,Boolean> map = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            map.put(nums[i],true);
        }
        List<Integer> ans = new LinkedList<>();
        for(int i = 1; i<= nums.length;i++){
            if(!map.containsKey(i)){
                ans.add(i);
            }
        }
        return ans;
    }

    //
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        //第一遍扫描，根据数组的值找到对应的下标，比如3对应下标2
        //将arr[2]设置成负数
        for(int i = 0;i<nums.length;i++){
            int newIndex = Math.abs(nums[i])-1;
            if(nums[i]>0){
                nums[newIndex] *=-1;
            }
        }
        List<Integer> ans = new LinkedList<>();
        //第二遍扫描，找到所有非负数，非负数所在的下标+1，即为缺失的数字
        for(int i = 1; i<= nums.length;i++){
            if(nums[i-1]>0){
                ans.add(i);
            }
        }
        return ans;
    }
}
