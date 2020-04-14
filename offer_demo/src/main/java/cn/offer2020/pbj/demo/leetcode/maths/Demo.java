package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo
 * @Author: pbj
 * @Date: 2020/4/14 17:14
 * @Description: TODO 最大公约数 最小公倍数
 */
public class Demo {
    //最大公约数
    int gcd(int a,int b){
        return b==0?a:gcd(b,a%b);
    }

    //最小公倍数
    int lcm(int a, int b) {
        return a*b/gcd(a,b);
    }

    // 使用位操作和减法求解最大公约数
//    public int gcd1(int a, int b) {
//        if (a < b) {
//            return gcd(b, a);
//        }
//        if (b == 0) {
//            return a;
//        }
//        boolean isAEven = isEven(a), isBEven = isEven(b);
//        if (isAEven && isBEven) {
//            return 2 * gcd(a >> 1, b >> 1);
//        } else if (isAEven && !isBEven) {
//            return gcd(a >> 1, b);
//        } else if (!isAEven && isBEven) {
//            return gcd(a, b >> 1);
//        } else {
//            return gcd(b, a - b);
//        }
//    }
}
