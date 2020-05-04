package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo1287
 * @Author: pbj
 * @Date: 2020/5/4 14:48
 * @Description: TODO 1287. 有序数组中出现次数超过25%的元素
 * 给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。
 */
public class Demo1287 {
    //自己写的
    public int findSpecialInteger(int[] arr) {
        int len = arr.length;
        int curSize = 1;
        int size = Integer.MIN_VALUE;
        if(len==1) return arr[0];
        for (int i = 1; i < len; i++) {
            if (arr[i] == arr[i - 1]) {
                curSize++;
            } else {
                curSize = 1;
            }
            size = Math.max(curSize, size);
            if (size > (len / 4)) {
                return arr[i];
            }
        }
        return -1;
    }

    //装逼啊 第i个和i+len/4相等就可以
    public static int findSpecialInteger1(int[] arr) {
        for (int i = 0, len = arr.length / 4; i < arr.length - len; i++)
            if (arr[i] == arr[i + len])
                return arr[i];
        return arr[0];
    }
}
