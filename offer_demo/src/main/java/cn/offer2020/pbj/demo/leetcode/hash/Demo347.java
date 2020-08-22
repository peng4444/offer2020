package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.*;

/**
 * @ClassName: Demo347
 * @Author: pbj
 * @Date: 2020/4/6 10:29
 * @Description: TODO 347. 前K个高频元素
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
    public int[] topKFrequent1(int[] nums, int k) {
        //对每个数字计数
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //初始化堆，按照出现的次数排序
        PriorityQueue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>((x,y)->x.getValue()-y.getValue());
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            heap.add(entry);
            if(heap.size()>k){
                heap.poll();
            }
        }
        int[] res = new int[k];
        int i= 0;
        while(!heap.isEmpty()){
            res[i++] = heap.poll().getKey();
        }
        return res;
    }
}
