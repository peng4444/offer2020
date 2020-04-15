package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.Arrays;

/**
 * @ClassName: Demo462
 * @Author: pbj
 * @Date: 2020/4/15 09:50
 * @Description: TODO 462. 最少移动次数使数组元素相等 II
 * 寻找中位数
 */
public class Demo462 {
    //排序
    public int minMoves22(int[] nums) {
        Arrays.sort(nums);
        int move = 0;
        int l = 0,h = nums.length-1;
        while(l<=h){
            move = move + nums[h] - nums[l];
            l++;
            h--;
        }
        return move;
    }


    //使用快速选择寻找中位数
    public void swap(int[] list, int i, int pivot_index) {
        int temp = list[i];
        list[i] = list[pivot_index];
        list[pivot_index] = temp;
    }
    public int partition(int[] list, int left, int right) {
        int pivotValue = list[right];
        int i = left;
        for (int j = left; j <= right; j++) {
            if (list[j] < pivotValue) {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, right, i);
        return i;
    }
    int select(int[] list, int left, int right, int k) {
        if (left == right) {
            return list[left];
        }
        int pivotIndex = partition(list, left, right);
        if (k == pivotIndex) {
            return list[k];
        } else if (k < pivotIndex) {
            return select(list, left, pivotIndex - 1, k);
        } else {
            return select(list, pivotIndex + 1, right, k);
        }
    }
    public int minMoves2(int[] nums) {
        int sum = 0;
        int median = select(nums, 0, nums.length - 1, nums.length / 2);

        for (int num : nums) {
            sum += Math.abs(median - num);
        }
        return sum;
    }
}
