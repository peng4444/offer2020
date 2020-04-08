package cn.offer2020.pbj.demo.leetcode.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo763
 * @Author: pbj
 * @Date: 2020/4/7 16:39
 * @Description: TODO
 */
public class Demo763 {

    //贪心 定义数组 last[char] 来表示字符 char 最后一次出现的下标。定义 anchor 和 j 来表示当前区间的首尾。如果遇到的字符最后一次出现的位置下标大于 j， 就让 j=last[c] 来拓展当前的区间。当遍历到了当前区间的末尾时(即 i==j )，把当前区间加入答案，同时将 start 设为 i+1 去找下一个区间。
    public List<Integer> partitionLabels2(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            last[s.charAt(i)-'a']=i;
        }
        int j = 0,anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            j = Math.max(j, last[s.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i-anchor+1);
                anchor = i + 1;
            }
        }
        return ans;
    }
    //无法通过的代码
    public static List<Integer> partitionLabels1(String S) {
        int len = S.length();
        List<Integer> ans = new ArrayList<>();
        int i = 0,start=0;
        while(i<len){
            for(int j=len-1;j>i;j--){
                if(S.charAt(i)==S.charAt(j)){
                    ans.add(S.substring(i,j).length());
                    start = j;
                    break;
                }
            }
            i = start;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels1("abacdefcj"));
    }

    //自己写的错误理解代码
    public List<Integer> partitionLabels(String S) {
        int len = S.length();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i<len-1;i++){
            for(int j = i+1;j<len;j++){
                if(S.charAt(i)==S.charAt(j)){
                    ans.add(S.substring(i,j).length());
                }
            }
        }
        return ans;
    }
}
