package cn.offer2020.pbj.demo.leetcode.twopoint;

import java.util.Arrays;

/**
 * @pClassName: Demo977
 * @author: pengbingjiang
 * @create: 2020/7/31 11:27
 * @description: TODO 977.有序数组的平方
 * 给定一个按非递减顺序排序的整数数组A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 */
public class Demo977 {
    //双指针
    //从原数组两头往中间进行数字平方结果的比较。 结果需要按照非递减顺序排列，则可考虑从后往前填充结果。
    //先将平方数值大的放入结果数组的尾部，然后移动原数组的左右指针向中间靠拢。
    public int[] sortedSquares1(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        int left = 0, right = len -1, resIndex = len - 1;
        while (left <= right) {
            if (A[left] * A[left] <= A[right] * A[right]) {
                res[resIndex--] = A[right]*A[right];
                right--;
            }else {
                res[resIndex--] = A[left] * A[left];
                left++;
            }
        }
        return res;
    }
    //暴力求解
    public int[] sortedSquares(int[] A) {
        for(int i = 0;i<A.length;i++){
            A[i] = A[i]*A[i];
        }
        Arrays.sort(A);
        return A;
    }
}
