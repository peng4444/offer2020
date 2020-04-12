package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo171
 * @Author: pbj
 * @Date: 2020/3/25 11:52
 * @Description: TODO
 */
public class Demo171 {

    public int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i)-'A'+1;
            ans = ans * 26+num;
        }
        return ans;
    }


    public int titleToNumber2(String s) {
        int len = s.length(), num = 0, bit = 1;
        for(int i=len-1; i>=0; i--) {
            num += bit * (s.charAt(i) - 64);
            bit *= 26;
        }
        return num;
    }
}
