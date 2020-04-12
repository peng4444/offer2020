package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Demo442
 * @Author: pbj
 * @Date: 2020/3/10 16:39
 * @Description: TODO 442数组中重复的数据
 */
public class Demo442 {

    //输入输出的空间不属于额外空间，可以在输入数组中用数字的正负来表示该位置所对应数字是否已经出现过。
    // 遍历输入数组，给对应位置的数字取相反数，如果已经是负数，说明前面已经出现过，直接放入输出数组。
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0;i<nums.length;i++){
            int num = Math.abs(nums[i]);
            if(nums[num-1]>0){
                nums[num-1] *=-1;
            }else{
                ans.add(Math.abs(nums[i]));
            }
        }
        return ans;
    }

    //
    class Solution {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> res = new ArrayList<>();
            if(nums.length == 0) return res;
            boolean mark[] = new boolean[nums.length];
            Arrays.fill(mark, false);
            for(int i = 0; i < nums.length;i++){
                int index = nums[i] - 1;
                if(mark[index]) res.add(nums[i]);
                else mark[index] = true;
            }
            return res;
        }
    }
    //会超时
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for(int i =0;i<nums.length;i++){
            if(list.contains(nums[i])){
                ans.add(nums[i]);
            }else{
                list.add(nums[i]);
            }
        }
        return ans;
    }
}
