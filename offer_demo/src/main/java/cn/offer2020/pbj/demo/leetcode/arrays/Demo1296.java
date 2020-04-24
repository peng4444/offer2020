package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.*;

/**
 * @ClassName: Demo1296
 * @Author: pbj
 * @Date: 2020/4/23 16:10
 * @Description: TODO 1296. 划分数组为连续数字的集合
 * 给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。
 */
public class Demo1296 {
    //思路上很清晰，先将数组排序，再把每个数字以及它们对应出现的次数存进map里面；
    //接着遍历数组，每遇到一个num，就把num, num + 1, ... num + k - 1这k个数结合map，按序进行判断;
    //若存在且对应的value值大于0，则value值减1，否则直接return false。
    public boolean isPossibleDivide2(int[] nums, int k) {
        if (nums.length % k != 0)
            return false;
        if (k == 1)
            return true;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        for (int num : nums) {
            if (map.get(num) != 0) {
                int start = num;
                int end = num + k - 1;
                while (start <= end) {
                    Integer v = map.get(start);
                    if (v != null && v > 0)
                        map.put(start++, v - 1);
                    else
                        return false;
                }
            }
        }
        return true;
    }

    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        while(map.size()>0){
            int first = map.firstKey();
            for(int i = first;i<first+k;i++){
                if(!map.containsKey(i)) return false;
                map.put(i,map.get(i)-1);
                if(map.get(i)==0) map.remove(i);
            }
        }
        return true;
    }

    public boolean isPossibleDivide1(int[] nums, int k) {
        int len = nums.length;
        if (len % k != 0) {
            return false;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(len);
        for (int num : nums) {
            minHeap.offer(num);
        }

        while (!minHeap.isEmpty()) {
            Integer top = minHeap.poll();

            for (int i = 1; i < k; i++) {
                // 从 1 开始，正好需要移除 k - 1 个元素
                // i 正好就是相对于 top 的偏移
                // 注意：这个 remove 操作会线性去扫 top + i 的索引，时间复杂度是 O(N)
                if (!minHeap.remove(top + i)) {
                    // 如果移除失败，说明划分不存在，直接返回 false 即可
                    return false;
                }
            }
        }
        return true;
    }
}
