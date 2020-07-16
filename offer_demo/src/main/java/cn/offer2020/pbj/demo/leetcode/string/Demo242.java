package cn.offer2020.pbj.demo.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @ClassName: Demo242
 * @Author: pbj
 * @Date: 2019/12/17 10:29
 * @Description: TODO 242.有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String t = scanner.nextLine();
        if(s.length()!=t.length())
            System.out.println(false);
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        System.out.println(Arrays.equals(sc, tc));
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
    //或者我们可以先用计数器表计算 ss，然后用 tt 减少计数器表中的每个字母的计数器。如果在任何时候计数器低于零，我们知道 tt 包含一个不在 ss 中的额外字母，并立即返回 FALSE。
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

    public boolean isAnagram4(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (char ch : t.toCharArray()) {
            Integer count = map.get(ch);
            if (count == null) {
                return false;
            } else if (count > 1) {
                map.put(ch, count - 1);
            } else {
                map.remove(ch);
            }
        }
        return map.isEmpty();
    }
}
