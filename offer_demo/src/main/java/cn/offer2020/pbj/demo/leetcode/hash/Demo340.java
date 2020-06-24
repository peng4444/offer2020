package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.HashMap;

//leetcode340.至多包含 K 个不同字符的最长子串:给定一个字符串 s ，找出至多包含k个不同字符的最长子串 T。
//[leetcode340.至多包含 K 个不同字符的最长子串](https://blog.csdn.net/qq_28468707/article/details/103804344)
public class Demo340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char cj = s.charAt(j);
            map.put(cj, map.getOrDefault(cj, 0) + 1);
            while (map.size() > k) {
                char ci = s.charAt(i);
                if ((map.get(ci) == 0)) {
                    map.remove(ci);
                }
                i++;
            }
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
