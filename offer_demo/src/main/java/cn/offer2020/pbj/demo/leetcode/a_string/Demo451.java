package cn.offer2020.pbj.demo.leetcode.a_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Demo451
 * @Author: pbj
 * @Date: 2020/4/6 10:56
 * @Description: TODO
 */
public class Demo451 {
    //桶排序
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character>[] bucket = new ArrayList[s.length() + 1];
        for (char c : map.keySet()) {
            int f = map.get(c);
            if(bucket[f]==null){
                bucket[f] = new ArrayList<>();
            }
            bucket[f].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == null) {
                continue;
            }
            for (char c : bucket[i]) {
                for(int j = 0;j<i;j++){
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
