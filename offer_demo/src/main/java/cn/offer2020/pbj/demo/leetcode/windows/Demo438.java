package cn.offer2020.pbj.demo.leetcode.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Demo438
 * @Author: pbj
 * @Date: 2020/5/14 11:43
 * @Description: TODO 438. 找到字符串中所有字母异位词
 */
public class Demo438 {
    public List<Integer> findAnagrams(String s, String p) {
        /**
         滑动窗口法, 定义两个指针lo和hi表示窗口的左端和右端, 一个counter=p.length作为指示
         从右端开始右滑, 遇到一个p内的字符就将counter减1, 如果counter变为0判断窗口是否满足要求
         一旦窗口大小等于p的长度, 需要删去左端点字符, 删去时需要考虑是否要将counter加1
         **/
        List<Integer> ret = new ArrayList<>();
        int m = s.length();
        int n = p.length();
        if(s.length() < p.length()) return ret;
        int[] map = new int[26];
        for(char ch : p.toCharArray())
            map[ch-'a']++;

        int counter = p.length();  // 指示条件, 如果counter被减为0需要判断窗口内的字符是否满足要求
        int lo = 0, hi = 0;
        char[] ss = s.toCharArray();

        while(hi < m) {
            // 每次都要向右移动一次hi, 如果hi对应的字符出现次数大于等于1说明该字符在p中, 将counter减1
            if (map[ss[hi]-'a']-- >= 1)
                counter--;
            hi++;
            // 如果counter减为0, 将左端点index加入返回值
            if (counter == 0)
                ret.add(lo);
            // 如果hi-lo == p.length说明窗口大小超出限制, 需要将lo右移一位同时判断lo对应的字符是否在p中并决定是否对counter加1
            if (hi-lo == n) {
                // 只有当lo对应的字符个数大于等于0才说明lo对应的字符属于p, 否则其对应值应为负值
                if(map[ss[lo]-'a']++ >= 0)
                    counter++;
                lo++;
            }
        }
        return ret;
    }

    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> result = new ArrayList<>();
        char [] sChar = s.toCharArray();
        char [] pChar = p.toCharArray();
        int [] curAToZ = new int[26];
        int [] aToZ = new int[26];
        for (char c : pChar) {
            aToZ[c - 'a']++;
        }
        for (int i = 0; i < sChar.length; i++) {
            if (i >= pChar.length) {
                curAToZ[sChar[i-pChar.length] - 'a']--;
            }
            curAToZ[sChar[i] - 'a']++;
            if (Arrays.equals(curAToZ, aToZ)) {
                result.add(i - pChar.length + 1);
            }
        }
        return result;
    }

    //超时
    public List<Integer> findAnagrams2(String s, String p) {
        int len = p.length();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i<s.length()-len+1;i++){
            char[] ss = s.substring(i,i+len).toCharArray();
            char[] sp = p.toCharArray();
            Arrays.sort(ss);
            Arrays.sort(sp);
            if(Arrays.equals(ss,sp)){
                ans.add(i);
            }
        }
        return ans;
    }
}
