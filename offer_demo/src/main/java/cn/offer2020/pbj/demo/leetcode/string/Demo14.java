package cn.offer2020.pbj.demo.leetcode.string;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName: Demo14
 * @Author: pbj
 * @Date: 2020/1/15 21:54
 * @Description: TODO 14.最长公共前缀
 */
public class Demo14 {

    /* *
     * 功能描述: 首先对字符串数组进行排序，然后拿数组中的第一个和最后一个字符串进行比较，
     * 从第 0 位开始，如果相同，把它加入 res 中，不同则退出。最后返回 res
     * @param: [strs]
     * @return: java.lang.String
     * @auther: pbj
     * @date: 2020/1/15 21:58
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        StringBuilder ans = new StringBuilder();
        int len = first.length < last.length ? first.length : last.length;
        int i = 0;
        while (i < len) {
            if (first[i] == last[i]) {
                ans.append(first[i]);
                i++;
            } else {
                break;
            }
        }
        return ans.toString();
    }

    //水平扫描法
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    //水平扫描
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = str.split(",");
        if(strs==null||strs.length==0) System.out.println("");
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length-1].toCharArray();
        StringBuilder sb = new StringBuilder();
        int len = first.length>last.length?last.length:first.length;
        for(int i = 0;i<len;i++){
            if(first[i]==last[i]){
                sb.append(first[i]);
            }else{
                break;
            }
        }
        System.out.println(sb.toString());
    }
}
