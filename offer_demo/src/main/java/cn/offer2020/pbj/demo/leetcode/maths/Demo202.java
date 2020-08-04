package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.HashSet;
import java.util.Set;

/**
 * @pClassName: Demo202
 * @author: pengbingjiang
 * @create: 2020/8/3 20:40
 * @description: TODO 202. 快乐数
 */
public class Demo202 {
    //参考英文网站热评第一。这题可以用快慢指针的思想去做，有点类似于检测是否为环形链表那道题
//如果给定的数字最后会一直循环重复，那么快的指针（值）一定会追上慢的指针（值），也就是
//两者一定会相等。如果没有循环重复，那么最后快慢指针也会相等，且都等于1。
    class Solution {
        public boolean isHappy(int n) {
            int fast=n;
            int slow=n;
            do{
                slow=squareSum(slow);
                fast=squareSum(fast);
                fast=squareSum(fast);
            }while(slow!=fast);
            if(fast==1)
                return true;
            else return false;
        }

        private int squareSum(int m){
            int squaresum=0;
            while(m!=0){
                squaresum+=(m%10)*(m%10);
                m/=10;
            }
            return squaresum;
        }
    }

    public boolean isHappy(int n) {
        if (n <= 0)
            return false;
        int now = 0;
        Set<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1){
            while (n != 0){
                now += Math.pow(n % 10, 2);
                n /= 10;
            }
            //出现重复值就终止
            if (set.contains(now)){
                return false;
            }
            set.add(now);
            n = now;
            now = 0;
        }
        return true;
    }
}
