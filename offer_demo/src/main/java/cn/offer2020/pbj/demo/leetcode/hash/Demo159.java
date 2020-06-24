package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.Collections;
import java.util.HashMap;

//leetcode159. 至多包含两个不同字符的最长子串(https://blog.csdn.net/hebtu666/article/details/104107625)
//给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t 。
public class Demo159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) return n;

        int left = 0;
        int right = 0;
        //K-V：K是对应字符，V是最后一次出现的位置。
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 2;

        while (right < n) {
            //符合要求就继续向右扩
            if (hashmap.size() < 3){
                hashmap.put(s.charAt(right), right++);
            }
            if (hashmap.size() == 3) {
                int index = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(index));
                left = index + 1;
            }
            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
