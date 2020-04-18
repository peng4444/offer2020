package cn.offer2020.pbj.demo.leetcode.string;

import java.util.HashMap;

/**
 * @ClassName: Demo205
 * @Author: pbj
 * @Date: 2020/4/18 15:03
 * @Description: TODO 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 */
public class Demo205 {

    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] preIndexOfs = new int[256];
        int[] preIndexOft = new int[256];
        for(int i = 0;i<s.length();i++){
            char sc = s.charAt(i),tc = t.charAt(i);
            if(preIndexOfs[sc]!=preIndexOft[tc]){
                return false;
            }
            preIndexOfs[sc] = i + 1;
            preIndexOft[tc] = i + 1;
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        return isIsomorphicHelper(s).equals(isIsomorphicHelper(t));
    }

    private String isIsomorphicHelper(String s) {
        int[] map = new int[128];
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            //当前字母第一次出现,赋值
            if (map[c] == 0) {
                map[c] = count;
                count++;
            }
            sb.append(map[c]);
        }
        return sb.toString();
    }

    public boolean isIsomorphic1(String s, String t) {
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);

    }

    private boolean isIsomorphicHelper(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (map.get(c1) != c2) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }
}
