package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: Demo229
 * @Author: pbj
 * @Date: 2020/1/6 11:11
 * @Description: TODO 229. 求众数 II
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 */
public class Demo229 {
    //HashMap
    public List<Integer> majorityElement1(int[] nums) {
        int len = nums.length;
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (!hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], 1);
            } else {
                int count = hashMap.get(nums[i]);
                count++;
                hashMap.put(nums[i], count);
            }
        }
        for (Integer i : hashMap.keySet()) {
            if (hashMap.get(i) > nums.length / 3) {
                ans.add(i);
            }
        }
        return ans;
    }

    //采用摩尔投票法，具体就是遇到相等的数，统计该数的个数自动加1，否则自动减一，一旦减到0后，更换当前存储的数字。摩尔投票法首次运用的题是求一维数组中数目超过一半的数
    //将两个数字设为候选数，遍历一遍数组确定这两个候选数，再遍历一遍数组确定这两个候选数是否是满足题目要求的。
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int count1 = 0, temp1 = 0;
        int count2 = 0, temp2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((count1 == 0 || temp1 == nums[i]) && temp2 != nums[i]) {
                count1++;
                temp1 = nums[i];
            } else if (count2 == 0 || temp2 == nums[i]) {
                count2++;
                temp2 = nums[i];
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for(int i = 0;i < nums.length;i++) {
            if(nums[i] == temp1)
                count1++;
            else if(nums[i] == temp2)
                count2++;
        }
        if(count1 > nums.length / 3)
            ans.add(temp1);
        if(temp1 != temp2 && count2 > nums.length / 3)
            ans.add(temp2);
        return ans;
    }
}
