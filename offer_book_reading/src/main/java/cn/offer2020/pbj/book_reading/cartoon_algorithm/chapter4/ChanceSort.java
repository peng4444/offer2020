package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

import java.util.Arrays;

/**
 * @ClassName: ChanceSort
 * @Author: pbj
 * @Date: 2020/3/13 14:05
 * @Description: TODO 选择排序 时间复杂度O(n^2)
 *  第一个跟后面的所有数相比，如果小于（或小于）第一个数的时候，暂存较小数的下标，第一趟结束后，将第一个数，与暂存的那个最小数进行交换，第一个数就是最小（或最大的数）
 *  下标移到第二位，第二个数跟后面的所有数相比，一趟下来，确定第二小（或第二大）的数
 *   重复以上步骤，直到指针移到倒数第二位，确定倒数第二小（或倒数第二大）的数，那么最后一位也就确定了，排序完成。
 */
public class ChanceSort {
    public int[] chanceSort(int[] array) {
        if (null == array || array.length == 0) {
            throw new RuntimeException("数组为null或长度为0");
        }
        int[] arr = Arrays.copyOf(array,array.length);
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }
}
