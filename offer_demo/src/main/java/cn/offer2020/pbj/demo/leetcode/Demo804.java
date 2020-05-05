package cn.offer2020.pbj.demo.leetcode;

import java.util.HashSet;

/**
 * @ClassName: Demo804
 * @Author: pbj
 * @Date: 2020/5/5 11:13
 * @Description: TODO 804. 唯一摩尔斯密码词
 */
public class Demo804 {
    private static String[] map = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        if (words == null) return 0;
        HashSet<String> set = new HashSet<String>();
        for (String s : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                sb.append(map[c - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
