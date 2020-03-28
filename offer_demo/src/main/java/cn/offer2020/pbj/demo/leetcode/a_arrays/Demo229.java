package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: Demo229
 * @Author: pbj
 * @Date: 2020/1/6 11:11
 * @Description: TODO
 */
public class Demo229 {

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
