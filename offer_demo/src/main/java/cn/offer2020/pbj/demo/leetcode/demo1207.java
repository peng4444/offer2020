package cn.offer2020.pbj.demo.leetcode;

import java.util.*;

/**
 * @ClassName: demo1207
 * @Author: pbj
 * @Date: 2020/4/26 08:59
 * @Description: TODO 1207. 独一无二的出现次数
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 */
public class demo1207 {
    //HashMap+HashSet
    //因为不到结束无法知道每个数字的出现次数，必须先扫一遍。此时为了存取效率 Map 是最优选择（本题范围不大，也可以用 array）。
    //之后进行判重，无疑 Set 变成了最理想的结构，只需取出 Map 的所有 value 逐个插入即可。
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Set<Integer> set = new HashSet<Integer>();

        // 记录出现次数
        for(int data :arr) {
            if (map.get(data) == null) map.put(data, 1);
            else map.put(data, map.get(data) + 1);
        }

        // 验证重复值
        for(Integer i : map.values()) {
            if (!set.add(i)) return false;
        }
        return true;
    }

    public boolean uniqueOccurrences1(int[] arr) {

        Map<Integer, Integer> counter = new HashMap<>();
        for (int elem : arr)
            counter.put(elem, counter.getOrDefault(elem, 0) + 1);

        return counter.size() == new HashSet<Integer>(counter.values()).size();
    }

}
