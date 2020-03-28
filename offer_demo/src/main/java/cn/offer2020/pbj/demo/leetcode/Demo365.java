package cn.offer2020.pbj.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo365
 * @Author: pbj
 * @Date: 2019/12/11 10:16
 * @Description: TODO 水壶问题
 */
public class Demo365 {

    public static boolean canMeasureWater(int x, int y, int z) {
        if (x + y == z || z == 0) {
            return true;
        }
        if (x + y < z) {
            return false;
        }
        if (x == y) {
            return x == z;
        }
        //保证y>x
        if (x > y) {
            int t = x;
            x = y;
            y = t;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int hel = y;
        while (!map.containsKey(hel)) {
            map.put(hel, 1);
            if (hel < x) {
                hel += y;
            } else {
                hel -= y;
            }
            if (hel == z) {
                return true;
            }
        }
        return false;
    }

    //ax+by=z;
    //令d=ax+by,则d为x,y最大公约数的1..n倍，
    //所以z%gcd(x,y)=0;
    public static boolean canMeasureWater2(int x, int y, int z) {
        if (z > x + y) return false;
        int gcd = getGcd(x, y);
        if (gcd == 0) return z == 0;
        return z % gcd == 0;
    }

    private static int getGcd(int x, int y) {
        if (y == 0) return x;
        return getGcd(y, x % y);
    }

    public static void main(String[] args) {
        System.out.println(canMeasureWater(3, 4, 5));//有问题
        System.out.println(canMeasureWater2(3,4,5));//true
    }
}
