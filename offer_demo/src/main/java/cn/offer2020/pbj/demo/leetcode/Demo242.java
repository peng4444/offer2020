package cn.offer2020.pbj.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName: Demo242
 * @Author: pbj
 * @Date: 2019/12/17 10:29
 * @Description: TODO 有效的字母异位词
 */
public class Demo242 {

    /* *
     * 功能描述: 对字符串s和t中否字符进行排序，排序之后比较两者是否相同
     * 时间复杂度O(NlogN) 快速排序
     * @param: [s, t]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/17 10:32
     */
    public boolean isAnagram(String s,String t){
        if(s.length()!=t.length())
            return false;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        return Arrays.equals(sc, tc);
    }

    /* *
     * 功能描述: 利用hash散列表，将字符串中的每个字符的个数进行记录，之后比较每个字符的个数是否相等。
     * 时间复杂度O(N)
     * @param: [s, t]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/17 10:34
     */
    public boolean isAnagram2(String s,String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i)-'a']++;
            counter[t.charAt(i)-'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram3(String s,String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i)-'a']--;
            if(count[t.charAt(i)-'a']<0)
                return false;
        }
        return true;
    }
}
