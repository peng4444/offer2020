package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo28
 * @Author: pbj
 * @Date: 2020/5/21 11:54
 * @Description: TODO
 */
public class Demo28 {

    //双指针
    public int strStr(String haystack, String needle) {
        if(needle=="") return 0;
        int i = 0;
        int j = 0;
        while(i<haystack.length()&&j<needle.length()){
            if(haystack.charAt(i) == needle.charAt(j)){i++;j++;}
            else {i = i-j+1;j = 0;}
        }
        if(j==needle.length()) return i - needle.length();
        else return -1;
    }

    public int strStr1(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();

        for (int start = 0; start < n - L + 1; ++start) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

}
