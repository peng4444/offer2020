package cn.offer2020.pbj.demo.leetcode.dp;

/**
 * @pClassName: Demo600
 * @author: pengbingjiang
 * @create: 2020/8/5 22:16
 * @description: TODO 600. 不含连续1的非负整数
 * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
 */
public class Demo600 {
    public int findIntegers(int num) {
        if(num==0||num==1){
            return num + 1;
        }
        int n = Integer.toBinaryString(num).length();
        if(num>=3<<n-2){
            return help(n);
        }else{
            return help(n-1) + findIntegers(num&(1<<n-2)-1);
        }
    }

    int help(int n){
        if(n==0||n==1) return n+ 1;
        else return help(n-2)+help(n-1);
    }

    //暴力求解：
    public int findIntegers1(int num) {
        int count = 0;
        for (int i = 0; i <= num; i++)
            if (check(i))
                count++;
        return count;
    }
    public boolean check(int n) {
        int i = 31;
        while (i > 0) {
            if ((n & (1 << i)) != 0 && (n & (1 << (i - 1))) != 0)
                return false;
            i--;
        }
        return true;
    }
}
