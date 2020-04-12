package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Demo136
 * @Author: pbj
 * @Date: 2020/4/3 10:30
 * @Description: TODO 136. 只出现一次的数字
 */
public class Demo136 {
    //异或
    public int singleNumber(int[] nums) {
        if(nums==null||nums.length==0){
            return -1;
        }
        int ans = 0;
        for(int i = 0;i<nums.length;i++){
            ans^=nums[i];
        }
        return ans;
    }

    //hash
    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1; // can't find it.
    }

    // 区别ArrayList中的remove(int index)和remove(Object 0)
    // 代码是会报出 java.lang.IndexOutOfBoundsException: Index: 3, Size: 3
    public static int singleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                list.remove(nums[i]);
            }else {
                list.add(nums[i]);
            }
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        System.out.println(singleNumber2(new int[]{1, 2, 3, 1, 2, 3, 4}));;
    }
}
