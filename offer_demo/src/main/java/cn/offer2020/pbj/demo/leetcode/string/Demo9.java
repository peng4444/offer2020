package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo9
 * @Author: pbj
 * @Date: 2020/3/25 21:26
 * @Description: TODO
 */
public class Demo9 {
    class Solution {
        public boolean isPalindrome(int x) {
            String str = ""+x;
            char [] strChar = str.toCharArray();
            int l = strChar.length;
            for(int i =0;i<l/2;i++){
                if(strChar[i]!=strChar[l-i-1]) {
                    return false;
                }
            }
            return true;
        }
    }


    public boolean isPalindrome(int x) {
        int k = x;
        if(k<0)
            return false;
        int n=0;
        while(k>0){
            n = k%10 + n*10;
            k /= 10;
        }
        return (x==n) ? true : false;
    }
}
