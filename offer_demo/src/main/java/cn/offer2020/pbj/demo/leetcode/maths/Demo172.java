package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo172
 * @Author: pbj
 * @Date: 2020/4/14 17:41
 * @Description: TODO 172. 阶乘后的零
 */
public class Demo172 {
    public int trailingZeroes(int n) {
        return n==0?0:n/5+trailingZeroes(n/5);
    }
}
