package cn.offer2020.pbj.demo.leetcode.string;

import java.util.Stack;

/**
 * @pClassName: Demo917
 * @author: pengbingjiang
 * @create: 2020/7/17 14:52
 * @description: TODO 917.仅仅反转字母
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 */
public class Demo917 {
    //双指针
    public String reverseOnlyLetters(String S) {
        int left = 0, right = S.length() - 1;
        char[] chars = S.toCharArray();
        while (left < right) {
            while (!(Character.isLetter(chars[left])) && left < right)
                left++;
            while (!(Character.isLetter(chars[right])) && left < right)
                right--;
            char c = chars[left];
            chars[left] = chars[right];
            chars[right] = c;
            left++;
            right--;
        }
        return String.valueOf(chars);
    }

    public String reverseOnlyLetters2(String S) {
        Stack<Character> letters = new Stack();
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }

    public String reverseOnlyLetters1(String S) {
        char[] str = S.toCharArray();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length; i++) {
            if ((str[i]>='a' && str[i] <= 'z') || (str[i] >='A' && str[i] <= 'Z'))
                sb.append(str[i]);
        }
        char[] chars = sb.reverse().toString().toCharArray();
        for (int i = 0,j = 0; i < str.length; i++) {
            if ((str[i]>='a' && str[i] <= 'z') || (str[i]>='A' && str[i] <= 'Z')){
                str[i] = chars[j];
                j++;
            }
        }
        return String.valueOf(str);
    }

    public String reverseOnlyLetters3(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); ++i) {
            if (Character.isLetter(S.charAt(i))) {
                while (!Character.isLetter(S.charAt(j)))
                    j--;
                ans.append(S.charAt(j--));
            } else {
                ans.append(S.charAt(i));
            }
        }
        return ans.toString();
    }
}
