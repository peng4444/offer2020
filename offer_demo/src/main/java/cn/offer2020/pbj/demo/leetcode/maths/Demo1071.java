package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo1071
 * @Author: pbj
 * @Date: 2020/5/6 14:56
 * @Description: TODO 1071. 字符串的最大公因子
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 */
public class Demo1071 {
    public String gcdOfStrings(String str1, String str2) {
        // 假设str1是N个x，str2是M个x，那么str1+str2肯定是等于str2+str1的。
        if(!(str1+str2).equals(str2+str1)){
            return "";
        }
        // 辗转相除法求gcd。
        return str1.substring(0,gcd(str1.length(),str2.length()));
    }
    public int gcd(int a, int b){
        return b==0?a:gcd(b,a%b);
    }

    public String gcdOfStrings1(String str1, String str2) {
        int len1=(int)str1.length();
        int len2=(int)str2.length();
        for(int i=Math.min(len1, len2);i>=1;--i){
            if(len1 % i==0 && len2 % i==0){
                String X=str1.substring(0,i);
                if(check(X, str1) && check(X, str2)) return X;
            }
        }
        return "";
    }
    private boolean check(String t, String s){
        int lenx = (int)s.length() / (int)t.length();
        String ans = "";
        for (int i = 1; i <= lenx; ++i){
            ans = ans + t;
        }
        //System.out.println(ans);
        // System.out.println(s);
        return ans.equals(s);
    }
}
