package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo541
 * @Author: pbj
 * @Date: 2020/3/25 18:05
 * @Description: TODO 翻转字符串
 */
public class Demo541 {
    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }

    public String reverseStr2(String s, int k) {
        int kk = k * 2;
        int N = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < N; i += kk) {
            if (i + kk < N ) {
                reverse(chars, i, i + k - 1); //加2k不越界
            } else if (i + k < N) {
                reverse(chars, i, i + k - 1); //加2k越界了，但当前位置后面够k个字符
            } else {
                reverse(chars, i, N-1);       //加k越界，直接翻转剩余字符
            }
        }
        return new String(chars);
    }
    //翻转方法
    private static char[] reverse (char[] arr, int begin, int end) {
        while (begin < end) {
            char c = arr[begin];
            arr[begin++] = arr[end];
            arr[end--] = c;
        }
        return arr;
    }
}
