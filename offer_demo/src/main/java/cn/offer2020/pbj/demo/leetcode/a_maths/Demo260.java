package cn.offer2020.pbj.demo.leetcode.a_maths;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo260
 * @Author: pbj
 * @Date: 2020/4/3 11:41
 * @Description: TODO 260. 只出现一次的数字 III
 */
public class Demo260 {

    //两个掩码:
    public int[] singleNumber1(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);

        int x = 0;
        // bitmask which will contain only x
        for (int num : nums) if ((num & diff) != 0) x ^= num;

        return new int[]{x, bitmask^x};
    }

    //hashmap
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        int k = 0;
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                ans[k++] = i;
            }
        }
        return ans; // can't find it.
    }

}
