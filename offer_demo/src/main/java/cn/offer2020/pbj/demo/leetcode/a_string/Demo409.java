package cn.offer2020.pbj.demo.leetcode.a_string;

import java.util.HashSet;

/**
 * @ClassName: Demo409
 * @Author: pbj
 * @Date: 2020/1/15 22:20
 * @Description: TODO 最长回文串
 */
public class Demo409 {

    //统计字母出现的次数即可，双数才能构成回文。因为允许中间一个数单独出现，比如“abcba”，所以如果最后有字母落单，总长度可以加 1。
    public int longestPalindrome(String s) {
        if(s.length()==0||s==null) return 0;
        HashSet<Character> set = new HashSet<>();
        int len = s.length();
        int count = 0;
        for(int i = 0; i< len ;i++){
            if(set.contains(s.charAt(i))){
                set.remove(s.charAt(i));
                count++;
            }else{
                set.add(s.charAt(i));
            }
        }
        return set.isEmpty()? count*2:count*2+1;
    }

    //贪心 【通过】
    class Solution {
        public int longestPalindrome(String s) {
            int[] count = new int[128];
            for (char c: s.toCharArray())
                count[c]++;

            int ans = 0;
            for (int v: count) {
                ans += v / 2 * 2;
                if (v % 2 == 1 && ans % 2 == 0)
                    ans++;
            }
            return ans;
        }
    }

}
