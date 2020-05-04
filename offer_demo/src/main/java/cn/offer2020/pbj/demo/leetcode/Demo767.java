package cn.offer2020.pbj.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName: Demo767
 * @Author: pbj
 * @Date: 2020/5/4 16:06
 * @Description: TODO 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 */
public class Demo767 {
    //判断解存在很容易，出现次数最多的字符不能超过1半[上取整]
    public boolean reorganizeString(String S) {
        char[] chars = S.toCharArray();
        if(chars.length==1) return true;
        Arrays.sort(chars);
        int curSize = 1;
        int size = Integer.MIN_VALUE;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == chars[i]) {
                curSize++;
            } else {
                curSize = 1;
            }
            size = Math.max(size, curSize);
            if (size > chars.length / 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * 有两个关键点：
     * 1、如何用桶来表示出字母数量和字母值：用 /100表示数字，用 %100表示字母值
     * 2、字母之间位置如何摆放：奇数放数量多的，偶数放数量少的
     * @param S 输入
     * @return
     */
    public String reorganizeString1(String S) {
        int N = S.length();
        int[] counts = new int[26];

        // 用桶来表示26个字母，值为size * 100
        for (char c : S.toCharArray()) counts[c - 'a'] += 100;

        // 每位字母加上下标，用来标识是哪个字母
        for (int i = 0; i < 26; ++i) counts[i] += i;

        // 将数量大的放到后面
        Arrays.sort(counts);

        char[] res = new char[N];

        // 奇数位置放数量少的
        int index = 1;

        for (int code : counts) {
            // 真实数量
            int count = code / 100;
            // 真实字母
            char ch = (char) ('a' + (code % 100));
            if (count > (N + 1) / 2) return "";
            for (int i = 0; i < count; ++i) {
                if (index >= N) {
                    // 奇数位置已满，放偶数位置
                    index = 0;
                }
                res[index] = ch;
                // 相同字母不能相邻
                index += 2;
            }
        }

        return String.valueOf(res);
    }
}
