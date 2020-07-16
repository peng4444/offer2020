package cn.offer2020.pbj.demo.leetcode.windows;

import java.util.Arrays;

/**
 * @pClassName: Demo567
 * @author: pengbingjiang
 * @create: 2020/7/16 21:40
 * @description: TODO 567. 字符串的排列
 */
public class Demo567 {
    public boolean checkInclusion(String s1, String s2) {
        int n = s2.length();
        int[] dict = new int[26];
        int[] freq = new int[26];
        int size = 0;
        for(char c : s1.toCharArray()) {
            if(dict[c - 'a'] == 0) size++;
            dict[c - 'a']++;
        }
        int match = 0;
        int left = 0, right = 0;
        while(right < n) {
            char rc = s2.charAt(right);
            freq[rc - 'a']++;
            if(freq[rc - 'a'] == dict[rc - 'a']) match++;
            while(size == match) {
                if(right - left + 1 == s1.length()) return true;
                char lc = s2.charAt(left);
                freq[lc - 'a']--;
                if(freq[lc - 'a'] < dict[lc - 'a']) match--;
                left++;
            }
            right++;
        }
        return false;
    }

    public boolean checkInclusion1(String s1, String s2) {
        int hash[] = new int[128];
        int size = 0 ;
        for(int i = 0 ; i < s1.length() ; i ++ )
        {
            if(hash[s1.charAt(i)] == 0) size++;
            hash[s1.charAt(i)]++;
        }
        for(int i = 0 , j = 0 , cnt = 0 ; j < s2.length() ; j ++ )
        {
            if( hash[s2.charAt(j)] == 1) cnt++;
            hash[s2.charAt(j)]--;
            while(i < s2.length() && hash[s2.charAt(i)] < 0 ) hash[s2.charAt(i++)]++;
            if(cnt == size && j - i + 1 == s1.length()) return true;
        }
        return false;
    }

    //暴力
    public boolean checkInclusion2(String s1, String s2) {
        if(s1.length()>s2.length()) return false;
        int sum = 0,tmp = 0;
        for(int i=0;i<s1.length();i++){
            sum+=s1.charAt(i);
            tmp+=s2.charAt(i);
        }
        if(tmp==sum&&isValid(s1,s2.substring(0,s1.length()))) return true;
        for(int i=1;i<s2.length()-s1.length()+1;i++){
            tmp=tmp-s2.charAt(i-1)+s2.charAt(i+s1.length()-1);
            if(tmp==sum&&isValid(s1,s2.substring(i,i+s1.length()))) return true;
        }
        return false;
    }
    public boolean isValid(String s1,String s2){
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return new String(chars1).equals(new String(chars2));
    }
}
