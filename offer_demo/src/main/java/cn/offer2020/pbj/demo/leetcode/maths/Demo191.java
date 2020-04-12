package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo191
 * @Author: pbj
 * @Date: 2019/12/28 20:46
 * @Description: TODO 数二进制位1的个数
 */
public class Demo191 {
    /* *
     * 功能描述: 常规求解
     * @param: [n]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/28 20:47
     */
    public int hammingWeight(int n) {
        String str = Integer.toBinaryString(n);
        int ans = 0;
        char[] chars = str.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(chars[i]=='1') ans++;
        }
        return ans;
    }

    /* *
     * 功能描述: 位运算
     * @param: [n]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/28 20:50
     */
    public int hammingWeight1(int n) {
        int ans  = 0;
        while (n != 0) {
            ans++;
            n &= (n - 1);
        }
        return ans;
    }
}
