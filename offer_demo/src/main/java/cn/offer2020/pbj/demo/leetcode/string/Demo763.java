package cn.offer2020.pbj.demo.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @pClassName: Demo763
 * @author: pengbingjiang
 * @create: 2020/8/1 19:53
 * @description: TODO 763.划分字母区间
 */
public class Demo763 {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        while(true){
            for(int i = startIndex;i<=endIndex;i++){
                endIndex = Math.max(endIndex,S.lastIndexOf(S.charAt(i)));
            }
            ans.add(endIndex-startIndex+1);
            if(endIndex==S.length()-1){
                break;
            }else{
                startIndex = ++endIndex;
            }
        }
        return ans;
    }

    //贪心
    class Solution {
        public List<Integer> partitionLabels(String S) {
            int[] last = new int[26];
            for (int i = 0; i < S.length(); ++i)
                last[S.charAt(i) - 'a'] = i;

            int j = 0, anchor = 0;
            List<Integer> ans = new ArrayList();
            for (int i = 0; i < S.length(); ++i) {
                j = Math.max(j, last[S.charAt(i) - 'a']);
                if (i == j) {
                    ans.add(i - anchor + 1);
                    anchor = i + 1;
                }
            }
            return ans;
        }
    }
}
