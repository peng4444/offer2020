package cn.offer2020.pbj.demo.job.ByteDance;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo01
 * @Author: pbj
 * @Date: 2020/1/3 21:04
 * @Description: TODO 最长不重复子序列长度
 */
public class Demo01 {

//    public static int lengthOfLongestSubstring(String s) {
//        if(s.length()<2) return s.length();
//        char[] chars = s.toCharArray();
//
//    }

    //自己的解法
    public static int longestList(List<Integer> list) {
        List<Integer> ans = new ArrayList<>();
        if (list == null) {
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!ans.contains(list.get(i))) {
                ans.add(list.get(i));
            } else {
                break;
            }
        }
        return ans.size();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(5);
        System.out.println(longestList(list));
    }
}
