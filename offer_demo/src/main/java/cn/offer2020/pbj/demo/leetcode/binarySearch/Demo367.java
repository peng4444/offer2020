package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo367
 * @Author: pbj
 * @Date: 2019/12/28 16:06
 * @Description: TODO 367.有效的完全平方数
 * 给定一个正整数num，编写一个函数，如果num是一个完全平方数，则返回True，否则返回False。
 */
public class Demo367 {

    /* *
     * 功能描述: 暴力求解，时间复杂度较大
     * 时间复杂度O(N)
     * @param: [num]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/28 16:08
     */
    public boolean isPerfectSquare(int num){
        if(num==0||num==1) return true;
        for (int i = 1; i <= num / 2; i++) {
            if(i*i==num) return true;
        }
        return false;
    }

    /* *
     * 功能描述: 二分查找
     * 时间复杂度O(logN)
     * 若 num < 2，返回 true。
        设置左边界为 2，右边界为 num/2。
        当 left <= right：
        令 x = (left + right) / 2 作为一个猜测，计算 guess_squared = x * x 与 num 做比较：
        如果 guess_squared == num，则 num 是一个完全平方数，返回 true。
        如果 guess_squared > num ，设置右边界 right = x-1。
        否则设置左边界为 left = x+1。
        如果在循环体内没有找到，则说明 num 不是完全平方数，返回 false。
     * @param: [num]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/28 16:11
     */
    public boolean idPerfectSquare1(int num) {
        if(num<2) return true;
        long left = 2,right=num/2,x,guessSquared;
        while (left < right) {
            x = left + (right - left) / 2;
            guessSquared = x*x;
            if(guessSquared==num) return true;
            if (guessSquared > num) {
                right = x - 1;
            } else {
                left = x+1;
            }
        }
        return false;
    }

    /* *
     * 功能描述: 牛顿迭代法
     * 我们取 num/2 作为初始近似值。
     * 当 x*x>num时，用牛顿迭代法取下一个近似值 x=(x+num/x)/2
     * 返回x*x=num
     * @param: [num]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/28 16:15
     */
    public boolean idPerfectSquare2(int num) {
        if(num<2) return true;
        long x = num;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }
    //间隔为等差数列，使用这个特性可以得到从 1 开始的平方序列。
    public boolean isPerfectSquare3(int num) {
        int subSum = 1;
        while(num>0){
            num = num -subSum;
            subSum = subSum + 2;
        }
        return num ==0;
    }

//    public boolean isPerfectSquare3(int num) {
//        if(num<2) return true;
//        int res = 0;
//        char[] chars = Long.toBinaryString(num).toCharArray();
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i]=='1') res++;
//        }
//        if(res==1) return true;
//        else return false;
//    }
}
