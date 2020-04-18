package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @ClassName: Demo220
 * @Author: pbj
 * @Date: 2020/4/18 12:18
 * @Description: TODO 220. 存在重复元素 III
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 */
public class Demo220 {
    //桶
    public class Solution {
        // Get the ID of the bucket from element value x and bucket width w
        // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
        private long getID(long x, long w) {
            return x < 0 ? (x + 1) / w - 1 : x / w;
        }

        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (t < 0) return false;
            Map<Long, Long> d = new HashMap<>();
            long w = (long)t + 1;
            for (int i = 0; i < nums.length; ++i) {
                long m = getID(nums[i], w);
                // check if bucket m is empty, each bucket may contain at most one element
                if (d.containsKey(m))
                    return true;
                // check the nei***or buckets for almost duplicate
                if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                    return true;
                if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                    return true;
                // now bucket m is empty and no almost duplicate in nei***or buckets
                d.put(m, (long)nums[i]);
                if (i >= k) d.remove(getID(nums[i - k], w));
            }
            return false;
        }
    }
    //二叉搜索树
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;

            // Find the predecessor of current element
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    //运行超时
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1;j<nums.length;j++){
                if(Math.abs((long)nums[i]-(long)nums[j])<=(long)t&&j-i<=k){
                    return true;
                }
            }
        }
        return false;
    }
}
