package cn.offer2020.pbj.demo.leetcode.a_maths;

/**
 * @ClassName: Demo69
 * @Author: pbj
 * @Date: 2019/12/28 15:50
 * @Description: TODO  x 的平方根
 * 计算并返回 x 的平方根，其中 x 是非负整数。由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 */
public class Demo69 {

    /* *
     * 功能描述: 二分法
     * 时间复杂度O(logN)
     * @param: [x]
     * @return: int 返回的是整数
     * @auther: pbj
     * @date: 2019/12/28 15:51
     */
    public int mySqrt(int x) {
        if(x==0||x==1) return x;
        int left = 1,right=x,res=0;
        while (left <= right) {
            int m = (left + right) / 2;
            if (m == x / m) {
                return m;
            } else if (m > x / m) {
                right= m - 1;
            } else {
                left = m+1;
                res = m;
            }
        }
        return res;
    }

    public static double mySqrt(int x,double y) {
        if(x==0||x==1) return x;
        double left = y,right=x,res=0;
        double m = 0;
        while (Math.abs(x-m*m)>y) {
            m = (left + right) / 2;
            if (m == x / m) {
                return m;
            } else if (m > x / m) {
                right= m;
            } else {
                left = m;
            }
            res =m;
        }
        return res;
    }

    public static void main(String[] args) {
        double v = mySqrt(10, 1e-9);
        System.out.println(v);//3.75000000025
        System.out.println(mySqrt(2,1e-9));
        System.out.println("-----------------");
        System.out.println(mySqrt1(3,1e-9));
    }

    /* *
     * 功能描述: 牛顿迭代法
     * 时间复杂度O(N)
     * @param: [x]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/28 15:51
     */
    public int mySqrt1(int x) {
        long a = x;
        while (a * a > x) {
            a = (a+x/a)/2;
        }
        return (int)a;
    }

    public static double mySqrt1(int x,double y) {
        double a = x;
        while (Math.abs(x-a*a)>y) {
            a = (a+x/a)/2;
        }
        return a;
    }
    //方法三：递归+位操作
    class Solution {
        public int mySqrt(int x) {
            if (x < 2) return x;

            int left = mySqrt(x >> 2) << 1;
            int right = left + 1;
            return (long)right * right > x ? left : right;
        }
    }
}
