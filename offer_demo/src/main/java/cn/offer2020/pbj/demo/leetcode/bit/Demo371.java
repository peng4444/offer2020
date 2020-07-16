package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo371
 * @Author: pbj
 * @Date: 2020/4/19 14:45
 * @Description: TODO 371.两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 */
public class Demo371 {
    public int getSum(int a, int b) {
        return b==0?a:getSum((a^b),(a&b)<<1);
    }

    public int getSum1(int a, int b)
    {
        while (b > 0)
        {
            a++;
            b--;
        }

        while (b < 0)
        {
            a--;
            b++;
        }
        return a;
    }
}
