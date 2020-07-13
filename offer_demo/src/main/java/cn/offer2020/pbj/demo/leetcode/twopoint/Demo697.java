package cn.offer2020.pbj.demo.leetcode.twopoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo697
 * @Author: pbj
 * @Date: 2020/3/10 23:34
 * @Description: TODO 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
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
    //双指针伸缩窗
    public int findShortestSubArray1(int[] A) {
        int n = A.length;
        if (n == 0) return 0;
        int[] freq = new int[50000];
        int d = 0;
        for (int i : A) {
            freq[i]++;
            d = Math.max(d, freq[i]);
        }
        freq = new int[50000];
        int l = -1, r = 0, ans = Integer.MAX_VALUE;
        while (r < n) {
            int v = A[r];
            freq[v]++;
            // 收缩l
            boolean done = false;
            while (freq[v] == d) {
                l++;
                freq[A[l]]--;
                done = true;
            }
            if (done) ans = Math.min(ans, r-l+1);
            r++;
        }
        return ans;
    }
}
