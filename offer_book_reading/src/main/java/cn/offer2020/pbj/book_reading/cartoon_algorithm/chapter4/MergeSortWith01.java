package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

import java.util.Arrays;

/**
 * @pClassName: MergeSortWith01
 * @author: pengbingjiang
 * @create: 2020/7/17 16:52
 * @description: TODO 实现一个空间复杂度为 O(1) 的归并排序
 * [面试官：请实现一个空间复杂度为 O(1) 的归并排序！](https://mp.weixin.qq.com/s?__biz=MzUyNjQxNjYyMg==&mid=2247491563&idx=3&sn=60febecf3367a6e0fbe6ef2c2a23dcc8&chksm=fa0e706acd79f97c80d2ab19482405e542287733a72135542bd379d9e4e3889d7b14dfa6e915&mpshare=1&scene=23&srcid=07170b5luvHFwHUuGg4Yq7ye&sharer_sharetime=1594946444038&sharer_shareid=d812adcc01829f0f7f8fb06aea118511#rd)
 */
public class MergeSortWith01 {
    static void merge(int[] arr, int left, int mid, int right, int maxVal) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (arr[i] % maxVal <= arr[j] % maxVal) {
                arr[k] = arr[k] + (arr[i] % maxVal) * maxVal;
                k++;
                i++;
            } else {
                arr[k] = arr[k] + (arr[j] % maxVal) * maxVal;
                k++;
                j++;
            }
        }
        while (i <= mid) {
            arr[k] = arr[k] + (arr[j] % maxVal) * maxVal;
            k++;
            i++;
        }
        while (j <= right) {
            arr[k] = arr[k] + (arr[j] % maxVal) * maxVal;
            k++;
            j++;
        }
        //获取原始的有序队列
        for (i = left; i <= right; i++) {
            arr[i] = arr[i] / maxVal;
        }
    }
    // 递归归并排序
    static void mergeSortRec(int[] arr, int left,
                             int right, int maxval)
    {
        if (left < right)
        {
            int mid = (left + right) / 2;
            mergeSortRec(arr, left, mid, maxval);
            mergeSortRec(arr, mid + 1, right, maxval);
            merge(arr, left, mid, right, maxval);
        }
    }

    //找到数组的最大值，并计算出maxval,然后调用递归的归并排序
    static void mergeSort(int[] arr, int n)
    {
        int maxval = Arrays.stream(arr).max().getAsInt() + 1;
        mergeSortRec(arr, 0, n - 1, maxval);
    }
}
