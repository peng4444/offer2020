package cn.offer2020.pbj.demo.leetcode.bit;

import java.util.Arrays;

/**
 * @pClassName: Demo389
 * @author: pengbingjiang
 * @create: 2020/8/1 15:45
 * @description: TODO
 */
public class Demo389 {

    public char findTheDifference1(String s, String t) {
        char res = t.charAt(t.length()-1);
        for(int i=0; i<s.length(); i++){
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return res;
    }

    public char findTheDifference(String s, String t) {
        int len = s.length();
        int ans = t.charAt(len);
        for (int i = 0; i < len; ++i) {
            ans += t.charAt(i) - s.charAt(i);
        }
        return (char) ans;
    }

    //暴力
    public char findTheDifference2(String s, String t) {
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ct);
        for(int i = 0;i<cs.length;i++){
            if(ct[i]!=cs[i]){
                return ct[i];
            }
        }
        return ct[cs.length];
    }
}
