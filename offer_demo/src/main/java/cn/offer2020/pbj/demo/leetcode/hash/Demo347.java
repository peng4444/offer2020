package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.*;

/**
 * @ClassName: Demo347
 * @Author: pbj
 * @Date: 2020/4/6 10:29
 * @Description: TODO 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前k高的元素。
 */
public class Demo347 {
    //桶排序法
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int frequent = map.get(key);
            if (buckets[frequent] == null) {
                buckets[frequent] = new ArrayList<>();
            }
            buckets[frequent].add(key);
        }
        List<Integer> topK = new ArrayList<>();
        for(int i = buckets.length-1;i>=0&&topK.size()<k;i--){
            if (buckets[i] == null) {
                continue;
            }
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                topK.addAll(buckets[i].subList(0, k - topK.size()));
            }
        }
        return topK;
    }
    //最小堆
    public List<Integer> topKFrequent1(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        //List to 数组
        int[] a = res.stream().mapToInt(Integer::valueOf).toArray();
        return res;
    }
}
