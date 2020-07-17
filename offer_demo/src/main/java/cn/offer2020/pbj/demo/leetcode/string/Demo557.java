package cn.offer2020.pbj.demo.leetcode.string;

import java.util.ArrayList;

/**
 * @ClassName: Demo557
 * @Author: pbj
 * @Date: 2020/3/25 17:54
 * @Description: TODO 557.翻转字符串里的单词III
 */
public class Demo557 {

    public class Solution {
        public String reverseWords(String s) {
            String words[] = s.split(" ");
            StringBuilder res=new StringBuilder();
            for (String word: words)
                res.append(new StringBuffer(word).reverse().toString() + " ");
            return res.toString().trim();
        }
    }

    public class Solution2 {
        public String reverseWords(String s) {
            String words[] = split(s);
            StringBuilder res=new StringBuilder();
            for (String word: words)
                res.append(reverse(word) + " ");
            return res.toString().trim();
        }
        public String[] split(String s) {
            ArrayList< String > words = new ArrayList < > ();
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    words.add(word.toString());
                    word = new StringBuilder();
                } else
                    word.append( s.charAt(i));
            }
            words.add(word.toString());
            return words.toArray(new String[words.size()]);
        }
        public String reverse(String s) {
            StringBuilder res=new StringBuilder();
            for (int i = 0; i < s.length(); i++)
                res.insert(0,s.charAt(i));
            return res.toString();
        }
    }

    public class Solution3 {
        public String reverseWords(String input) {
            final StringBuilder result = new StringBuilder();
            final StringBuilder word = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) != ' ') {
                    word.append(input.charAt(i));
                } else {
                    result.append(word.reverse());
                    result.append(" ");
                    word.setLength(0);
                }
            }
            result.append(word.reverse());
            return result.toString();
        }
    }

    public static String reverseWords(String s) {
        if(s==null||s.length()==0){
            return "";
        }
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<arr.length;i++){
            if(!arr[i].equals("")){
                if(sb.length()>0){
                    sb.append(" ");
                }
            }
            sb.append(swap(arr[i]));
        }
        return sb.toString();
    }
    public static String swap(String s){
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<s.length();i++){
            sb.insert(0,s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        reverseWords("aa ca ba");
    }
}
