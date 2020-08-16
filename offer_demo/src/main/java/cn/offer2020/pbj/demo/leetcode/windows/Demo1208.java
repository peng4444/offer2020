package cn.offer2020.pbj.demo.leetcode.windows;

/**
 * @pClassName: Demo1208
 * @author: pengbingjiang
 * @create: 2020/8/16 21:40
 * @description: TODO 1208.尽可能使字符串相等
 */
public class Demo1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int maxLen = 0;
        int cost = 0;
        for(int l = 0, r = 0;r<s.length();r++){
            cost+=Math.abs(s.charAt(r)-t.charAt(r));
            while(cost>maxCost){
                cost-=Math.abs(s.charAt(l)-t.charAt(l));
                l++;
            }
            maxLen = Math.max(maxLen,r-l+1);
        }
        return maxLen;
    }
}
