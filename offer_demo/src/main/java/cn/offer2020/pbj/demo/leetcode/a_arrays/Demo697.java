package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo697
 * @Author: pbj
 * @Date: 2020/3/10 23:34
 * @Description: TODO 697. 数组的度
 */
public class Demo697 {

    public int findShortestSubArray(int[] nums){
        Map<Integer, Integer> map = new HashMap<>(),left = new HashMap<>(),right = new HashMap<>();
        for (int i = 0;i<nums.length;i++) {
            int x = nums[i];
            if(left.get(x)==null) left.put(x, i);
            right.put(x, i);
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        int ans = nums.length;
        int degree = Collections.max(map.values());
        for (int x : map.keySet()) {
            if (map.get(x) == degree) {
                ans = Math.min(ans, right.get(x) - left.get(x) + 1);
            }
        }
        return ans;
    }
}
