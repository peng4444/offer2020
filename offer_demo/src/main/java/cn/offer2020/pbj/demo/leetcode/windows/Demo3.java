package cn.offer2020.pbj.demo.leetcode.windows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: Demo3
 * @Author: pbj
 * @Date: 2020/3/16 10:17
 * @Description: TODO  3.无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 */
public class Demo3 {

    //优化的滑动窗口
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // 如果窗口中包含当前字符
            if (map.containsKey(s.charAt(j))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            ans = Math.max(ans, j - i + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    //HashSet 滑动窗口
    public class Solution {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int ans = 0, i = 0, j = 0;
            while (i < n && j < n) {
                // try to extend the range [i, j]
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j++));
                    ans = Math.max(ans, j - i);
                } else {
                    set.remove(s.charAt(i++));
                }
            }
            return ans;
        }
    }

    // HashMap 滑动窗口
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        // 计算无重复字符子串开始的位置
        int start = -1;
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                int tmp = map.get(s.charAt(i));
                // 上一次的位置在左边界右边, 则需要向右移动左边界
                if (tmp >= start) {
                    start = tmp;
                }
            }

            map.put(s.charAt(i), i);
            max = Math.max(max, i - start);
        }
        return max;
    }

    //哈希表 滑动窗口
    public int lengthOfLongestSubstring3(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    //时间复杂度O(n^2)
    public int lengthOfLongestSubstring2(String s) {
        int len = s.length(),i=0,j,k,max = 0;
        char[] chars = s.toCharArray();
        for (j = 0; j < len; j++) {
            for (k = i; k < j; k++) {
                if (chars[k] == chars[j]) {
                    i = k + 1;
                    break;
                }
            }
            if (j - i + 1 > max) {
                max = j - i + 1;
            }
        }
        return max;
    }

    //暴力法 --排列出所有的字符排列，统计长度
    public class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            int ans = 0;
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j <= n; j++)
                    if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
            return ans;
        }

        public boolean allUnique(String s, int start, int end) {
            Set<Character> set = new HashSet<>();
            for (int i = start; i < end; i++) {
                Character ch = s.charAt(i);
                if (set.contains(ch)) return false;
                set.add(ch);
            }
            return true;
        }
    }
}
