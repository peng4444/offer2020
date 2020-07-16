package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @pClassName: Demo191
 * @author: pengbingjiang
 * @create: 2020/7/15 16:49
 * @description: TODO 191.位1的个数
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为‘1’的个数（也被称为汉明重量）。
 */
public class Demo191 {
    public int hammingWeight(long n) {
        int count = 0;
        for(int i=0; i<32; i++) {
            long tmp = n%2;
            n = n/2;
            if(tmp == 1) {
                count++;
            }
            if(n==0) {
                break;
            }
        }
        return count;
    }

    //除K取余法
    public int hammingWeight1(int n) {
        int ans = 0;
        while (n != 0) {
            ans+=n%2;
            n>>=1;
        }
        return ans;
    }
    //循环和位移动
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for(int i = 0;i<32;i++){
            if((n&mask)!=0){
                bits++;
            }
            mask<<=1;
        }
        return bits;
    }

    public int hammingWeight0(int n) {
        String str = Integer.toBinaryString(n);
        int count=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='1')
                count++;
        }
        return count;
    }
}
