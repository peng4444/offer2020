package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo1010
 * @Author: pbj
 * @Date: 2020/1/5 14:11
 * @Description: TODO
 */
public class Demo1010 {

    //自己的解法，超出时间限制
    public int numPairsDivisibleBy60(int[] time) {
        int len = time.length;
        if(len<2) return 0;
        int result = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    //用一个数组记录每个时间对应的模60结果的总和，如果两个数的模相加为60，则这两个数相加是60的倍数
    class Solution {
        public int numPairsDivisibleBy60(int[] time) {
            int[] record = new int[60];
            int count = 0;
            for(int t : time){
                t %= 60;        //求这个时间的余数
                if(t != 0)
                    count += record[60 - t];    //如果时间余数不为0，找出相加为0的余数总和相加
                else count += record[t];        //如果为0，加其他为0的数
                record[t] ++;
            }
            return count;
        }
    }

    class Solution2 {
        public int numPairsDivisibleBy60(int[] time) {
            int[] count = new int[60];
            for(int t:time){
                count[t%60]++;
            }
            int num = (count[0]*(count[0]-1))/2;
            num += (count[30]*(count[30]-1))/2;
            for(int i=1;i<30;i++){
                num += count[i]*count[60-i];
            }
            return num;
        }
    }
}
