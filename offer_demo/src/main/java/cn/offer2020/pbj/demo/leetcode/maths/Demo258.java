package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo258
 * @Author: pbj
 * @Date: 2020/5/20 11:06
 * @Description: TODO 258.各位相加
 */
public class Demo258 {
    //递归循环
    public int addDigits(int num){
        int temp = 0;
        while (num != 0) {
            temp = temp + num%10;
            num = num / 10;
        }
        return temp>=10?addDigits(temp):temp;
    }

    //数学
    public int addDigits1(int num) {
        return (num-1)%9+1;
    }

    public int addDigits2(int num){
        if(num==0) return 0;
        if(num%9==0) return 0;
        return num%9;
    }
}
