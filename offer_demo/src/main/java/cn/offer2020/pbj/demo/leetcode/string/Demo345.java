package cn.offer2020.pbj.demo.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName: Demo345
 * @Author: pbj
 * @Date: 2020/4/5 11:30
 * @Description: TODO 345. 反转字符串中的元音字母
 */
public class Demo345 {

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));

    //双指针
    public String reverseVowels(String s) {
        int i = 0,j = s.length()-1;
        char[] ans = new char[s.length()];
        while(i<=j){
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if(!vowels.contains(ci)){
                ans[i++]= ci;
            }else if(!vowels.contains(cj)){
                ans[j--] = cj;
            }else{
                ans[i++] = cj;
                ans[j--] = ci;
            }
        }
        return new String(ans);
    }

    public String reverseVowels1(String s) {
        // 先将字符串转成字符数组（方便操作）
        // 以上是只针对 Java 语言来说的 因为 chatAt(i) 每次都要检查是否越界 有性能消耗
        char[] arr = s.toCharArray();
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            // 从左判断如果当前元素不是元音
            while (l < n && !isVowel(arr[l]) ) {
                l++;
            }
            // 从右判断如果当前元素不是元音
            while (r >= 0 && !isVowel(arr[r]) ) {
                r--;
            }
            // 如果没有元音
            if (l >= r) {
                break;
            }
            // 交换前后的元音
            swap(arr, l, r);
            // 这里要分开写，不要写进数组里面去
            l++;
            r--;
        }
        // 最后返回的时候要转换成字符串输出
        return new String(arr);
    }

    private void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    // 判断是不是元音，如果不是元音才返回 true
    private boolean isVowel(char ch) {
        // 这里要直接用 return 语句返回，不要返回 true 或者 false
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
                ||ch=='A'|| ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}
