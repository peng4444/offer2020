package cn.offer2020.pbj.demo.leetcode.sort;

import java.util.Arrays;

/**
 * @pClassName: Demo1122
 * @author: pengbingjiang
 * @create: 2020/7/16 19:04
 * @description: TODO 1122. 数组的相对排序
 * https://leetcode-cn.com/problems/relative-sort-array/solution/ming-que-bi-jiao-fang-shi-hou-xiang-zen-yao-pai-ji/
 */
public class Demo1122 {
    //计数排序
    //思路：先将arr1中的数全都记录到数组count中，然后遍历arr2的同时，将arr2中的数置入arr1，注意由于arr2中的数在arr1中也出现了，
    //所以直接从count中取出即可，处理好arr2之后，再处理剩下的数字，一格一格往里填就好了。
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        // 将 arr1 中的数记录下来
        for (int num1 : arr1) {
            count[num1]++;
        }
        // 先安排 arr2 中的数
        int i = 0;
        for (int num2 : arr2) {
            while (count[num2] > 0) {
                arr1[i++] = num2;
                count[num2]--;
            }
        }
        // 再排剩下的数
        for (int num1 = 0; num1 < count.length; num1++) {
            while (count[num1] > 0) {
                arr1[i++] = num1;
                count[num1]--;
            }
        }
        return arr1;
    }

    //桶排序，先把arr2中的数按顺序拿完，在把桶中剩下的按顺序拿完。
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {

        int[] bucket = new int[1001];
        for (int i = 0; i < arr1.length; i++)
            bucket[arr1[i]]++;

        int idx = 0;
        for (int i = 0; i < arr2.length; i++)
            while (bucket[arr2[i]] > 0) {
                bucket[arr2[i]]--;
                arr1[idx++] = arr2[i];
            }

        for (int i = 0; i < bucket.length; i++)
            while (bucket[i] > 0) {
                bucket[i]--;
                arr1[idx++] = i;
            }

        return arr1;
    }

    //暴力
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int[] ints = new int[arr1.length];
        int j = 0;
        for (int a : arr2) {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == a) {
                    ints[j] = arr1[i];
                    arr1[i] = -1;
                    j++;
                }
            }
        }
        Arrays.sort(arr1);
        int i = arr1.length - j;
        if (i > 0) System.arraycopy(arr1, j, ints, j, i);
        return ints;
    }

}
