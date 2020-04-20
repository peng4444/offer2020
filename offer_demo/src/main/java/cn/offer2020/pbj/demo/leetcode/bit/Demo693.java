package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo693
 * @Author: pbj
 * @Date: 2020/4/19 11:20
 * @Description: TODO 693. 交替位二进制数
 * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 */
public class Demo693 {
    //对于 1010 这种位级表示的数，把它向右移动 1 位得到 101，这两个数每个位都不同，因此异或得到的结果为1111。
    public boolean hasAlternatingBits(int n) {
        int a = (n ^ (n>>1));
        return (a&(a+1))==0;
    }

    //除2
    public boolean hasAlternatingBits1(int n) {
        while(n>0){
            if(n%2==(n/2)%2) return false;
            n = n /2;
        }
        return true;
    }
    //转换为二进制字符串
    public boolean hasAlternatingBits2(int n) {
        char[] chars = Integer.toString(n, 2).toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if(chars[i]==chars[i-1]){
                return false;
            }
        }
        return  true;
    }
}
