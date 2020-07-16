package cn.offer2020.pbj.demo.leetcode.sort;

import java.util.*;

/**
 * @pClassName: Demo49
 * @author: pengbingjiang
 * @create: 2020/7/16 19:27
 * @description: TODO 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/
 */
public class Demo49 {
    public List<List<String>> groupAnagrams1(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    public List<List<String>> groupAnagrams0(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(int i = 0;i<strs.length;i++){
            List<String> temp = new ArrayList<>();
            if(!list.contains(strs[i])){
                temp.add(strs[i]);
                list.add(strs[i]);
                for(int j = i+1;j<strs.length;j++){
                    if(help(strs[i],strs[j])){
                        temp.add(strs[j]);
                        list.add(strs[j]);
                    }
                }
            }
            if(temp.size()>0){
                ans.add(temp);
            }
            // ans.add(temp);
        }
        return ans;
    }
    private boolean help(String str1,String str2){
        if(str1.length()!=str2.length()) return false;
        int[] num = new int[26];
        for(int i = 0;i<str1.length();i++){
            num[str1.charAt(i)-'a']++;
            num[str2.charAt(i)-'a']--;
        }
        for(int i = 0;i<num.length;i++){
            if(num[i]!=0) return false;
        }
        return true;
    }

}
