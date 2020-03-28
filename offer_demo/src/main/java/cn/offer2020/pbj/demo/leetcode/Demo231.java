package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo231
 * @Author: pbj
 * @Date: 2019/12/28 20:59
 * @Description: TODO 2的幂判断
 */
public class Demo231 {
    /* *
     * 功能描述: 常规方法
     * @param: [n]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/28 21:06
     */
    public static boolean isPowerOfTwo(int n) {
        if(n==-2147483648) return true;
        String str = Integer.toBinaryString(n);
        char[] chars = str.toCharArray();
        int size = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='1')
                size++;
        }
        if(size==1) {return true;}
        else{ return false;}
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(2));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }

    /* *
     * 功能描述: 位运算 n&(n-1)=0
     * @param: [n]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/28 21:09
     */
    public boolean isPowerOfTwo1(int n) {
        return n > 0 && (n & (n - 1))==0;
    }
}
