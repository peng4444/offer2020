package cn.offer2020.pbj.demo.leetcode.string;

import java.util.*;

/**
 * @ClassName: Demo151
 * @Author: pbj
 * @Date: 2020/3/25 15:16
 * @Description: TODO 151.翻转字符串里的单词
 */
public class Demo151 {
    public String reverseWords0(String s) {
        String[] strs = s.trim().split(" "); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for(int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if(strs[i].equals("")) continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }

    public String reverseWords(String s) {
        if(s==null||s.length()==0){
            return "";
        }
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder(arr.length);
        for(int i = arr.length-1;i>=0;i--){
            if(!arr[i].equals("")){
                if(sb.length()>0){
                    sb.append(" ");
                }
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public String reverseWords1(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    //双端队列
    public String reverseWords2(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') ++left;

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') --right;

        Deque<String> d = new ArrayDeque();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }
}
