package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter5;

/**
 * @ClassName: GreatCommonDivisor
 * @Author: pbj
 * @Date: 2019/10/30 09:13
 * @Description: TODO  求最大公约数
 */
public class GreatCommonDivisor {
    /* *
     * 功能描述: 暴力求解  O(min(a,b))
     * @param: [a, b]
     * @return: int
     * @auther: pbj
     * @date: 2019/10/30 9:18
     */
    public static int getGreatCommonDivisor(int a ,int b) {
        int big = a>b?a:b;
        int small = a<b?a:b;
        if (big % small == 0) {
            return small;
        }
        for (int i = small / 2; i > 1; i--) {
            if (small % i == 0 && big % i == 0) {
                return i;
            }
        }
        return 1;
    }


    /* *
     * 功能描述: 欧几里得算法  递归实现 O(log(max(a,b)))
     * @param: [a, b]
     * @return: int
     * @auther: pbj
     * @date: 2019/10/30 9:26
     */
    public static int getGreatCommonDivisor2(int a, int b) {
        int big = a>b?a:b;
        int small = a<b?a:b;
        if (big % small == 0) {
            return small;
        }
        return getGreatCommonDivisor2(big % small, small);
    }

    /* *
     * 功能描述: 相减法  最坏O(max(a,b))
     * @param: [a, b]
     * @return: int
     * @auther: pbj
     * @date: 2019/10/30 9:32
     */
    public static int getGreatCommonDivisor3(int a, int b) {
        if (a == b) {
            return a;
        }
        int big = a>b?a:b;
        int small = a>b?a:b;
        return getGreatCommonDivisor3(big - small, small);
    }

    /* *
     * 功能描述: 相除和相减结合  O(log(max(a,b)))
     * @param: [a, b]
     * @return: int
     * @auther: pbj
     * @date: 2019/10/30 9:38
     */
    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        if ((a & 1) == 0 && (b & 1) == 0) {
            return gcd(a >> 1, b >> 1);
        } else if ((a & 1) == 0 && (b & 1) != 0) {
            return gcd(a >> 1, b);
        } else if ((a & 1) != 0 && (b & 1) == 0) {
            return gcd(a, b >> 1);
        }else {
            int big = a>b?a:b;
            int small = a>b?a:b;
            return gcd(big - small, small);
        }
    }

    public static void main(String[] args) {
        System.out.println(getGreatCommonDivisor(25,5));
        System.out.println(getGreatCommonDivisor(100,5));
        System.out.println(getGreatCommonDivisor(27,14));
        System.out.println(getGreatCommonDivisor2(25,5));
        System.out.println(getGreatCommonDivisor2(100,5));
        System.out.println(getGreatCommonDivisor2(27,14));
        System.out.println(getGreatCommonDivisor3(25,5));
        System.out.println(getGreatCommonDivisor3(100,5));
        System.out.println(getGreatCommonDivisor3(27,14));
    }
}
