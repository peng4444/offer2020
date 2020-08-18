package cn.offer2020.pbj.demo.leetcode.windows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @pClassName: Demo1456
 * @author: pengbingjiang
 * @create: 2020/8/18 20:25
 * @description: TODO 1456.定长子串中元音的最大数目
 */
public class Demo1456 {
    //滑动窗口
    public int maxVowels3(String s, int k) {
        int left = 0;
        int right = 0;
        int max = 0;
        int count = 0;
        while(right<s.length()){
            char temp = s.charAt(right);
            if(temp=='a'||temp=='e'||temp=='i'||temp=='o'||temp=='u'){
                count++;
            }
            right++;
            if (right-left==k){
                max = Math.max(max,count);
                char c = s.charAt(left);
                if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
                    count--;
                }
                left++;
            }
        }
        return max;
    }
    //前缀和
    public int maxVowels2(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + (isVowel(s.charAt(i - 1)) ? 1 : 0);
        }
        for (int i = 0; i < n - k + 1; i++) {
            maxLen = Math.max(maxLen, prefixSum[i + k] - prefixSum[i]);
        }
        return maxLen;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public int maxVowels1(String s, int k) {
        Set<Character> set = new HashSet();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        int r = 0, cur = 0;
        for (r = 0; r < k; r++) {
            if (r >= s.length()) return cur;
            if (set.contains(s.charAt(r))) cur++;
        }
        int l = 0, max = cur;
        while (r < s.length()) {
            if (set.contains(s.charAt(l++))) cur--;
            if (set.contains(s.charAt(r++))) cur++;
            max = Math.max(max, cur);
        }
        return max;
    }
    //暴力求解-超时
    public int maxVowels(String s, int k) {
        List<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        int len = s.length();
        int ans = Integer.MIN_VALUE;
        for(int i = 0;i<len-k+1;i++){
            int size = 0;
            for(int j = i;j<i+k;j++){
                if(list.contains(s.charAt(j))){
                    size++;
                }
            }
            ans = Math.max(ans,size);
        }
        return ans;
    }
}
