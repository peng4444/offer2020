package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo263
 * @Author: pbj
 * @Date: 2020/4/15 11:51
 * @Description: TODO 263. 丑数
 */
public class Demo263 {

    public boolean isUgly(int num) {
        if(num<1) return false;
        while(num%5==0) num = num / 5;
        while(num%3==0) num = num / 3;
        while(num%2==0) num = num / 2;
        return num == 1;
    }

    public boolean isUgly1(int num) {
        int temp;
        while (num!=1){
            temp=num;
            num=num%2==0?num/2:num;
            num=num%3==0?num/3:num;
            num=num%5==0?num/5:num;
            if (temp==num){
                return false;
            }
        }
        return true;

    }
}
