package cn.offer2020.pbj.demo.leetcode;

import java.util.Arrays;

/**
 * @ClassName: Demo324
 * @Author: pbj
 * @Date: 2020/6/11 09:13
 * @Description: TODO 280.摆动排序  324. 摆动排序 II
 */
public class Demo324 {
    //324.摆动排序 II
    /* *
     * 题目描述：给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
     * 解题描述:排序后，两个指针，一个从中间往前，一个从末尾往前。j、k的初始化这注意，必须保证0～j比j+1～k大于等于1个，这样才能填满。
     * 时间复杂度：O(n) //nums中int的个数  空间复杂度：O(n)
     */
    public static void wiggleSortII(int[] nums) {
        int[] temp = nums.clone();
        Arrays.sort(temp);
        int j = (nums.length - 1) / 2, k = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i % 2 == 1 ? temp[k--] : temp[j--];
        }
    }
    //先排序，再穿插
    public void wiggleSortII2(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        int[] val = Arrays.copyOf(nums, nums.length);
        Arrays.sort(val);
        int index = nums.length - 1;
        for(int i = 1; i < nums.length; i += 2) {
            nums[i] = val[index--];
        }
        for(int i = 0; i < nums.length; i += 2) {
            nums[i] = val[index--];
        }
    }

    //280.摆动排序
    /* *
     * 题目描述：给定一个无序的数组，按照num[0]<=nums[1]>=nums[2]<=nums[3].....重新排列
     * 解题描述:只需要从左往右扫一次，如果当前INDEX是奇数，那么前面比它大的话就和前面那个数字交换，如果当前INDEX是偶数，那么前面比它小的话就进行交换。
     * 时间复杂度：O(n) //nums中int的个数  空间复杂度：O(1)
     */
    public static void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            //数组下标为奇数并且当前数小于前一个数
            if (i % 2 == 1 && nums[i] < nums[i - 1]) {
                swap(nums, i, i - 1);
                //数组下标为偶数并且当前数大于前一个数
            } else if (i % 2 == 0 && nums[i] > nums[i - 1]) {
                swap(nums, i, i - 1);
            }
        }
        System.out.println(Arrays.toString(nums));
    }
    //先排序，再对偶数下标与前一个数交换位置
    public static void wiggleSort1(int[] nums) {
        Arrays.sort(nums);
        if(nums.length<=2) return;
        for (int i = 2; i < nums.length; i = i + 2) {
            swap(nums,i,i-1);
        }
        System.out.println(Arrays.toString(nums));
    }

    //数组下标值交换
    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        wiggleSort(arr);
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6};
        wiggleSort1(arr1);
    }

    //归并排序
    public void wiggleSort4(int[] nums) {
        int midNum = getMiddleNum(nums);//获取中位数
        threePartition(nums, midNum);//将nums分成三组：小于midNum、等于midNum、大于midNum
        int[] lower = new int[(nums.length % 2 != 0) ? nums.length / 2 + 1 : nums.length / 2];
        int[] upper = new int[nums.length / 2];
        System.arraycopy(nums, 0, lower, 0, lower.length);
        System.arraycopy(nums, lower.length, upper, 0, upper.length);
        for (int i = 0; i <= upper.length - 1; i++) {
            nums[i * 2] = lower[lower.length - 1 - i];//反序获取
            nums[i * 2 + 1] = upper[upper.length - 1 - i];
        }
        if (nums.length % 2 != 0) nums[nums.length - 1] = lower[0];
    }

    private int getMiddleNum(int[] nums) {
        int start = 0, end = nums.length - 1;
        int index = partition(nums, start, end);
        while (index != nums.length / 2) {
            if (index > nums.length / 2) end = index - 1;
            else start = index + 1;
            index = partition(nums, start, end);
        }
        return nums[index];
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = (int)(start + Math.random() * (end - start + 1));
        swap(nums, pivot, end);
        int small = start - 1;
        for (int i = start; i <= end; i++) {
            if (nums[i] <= nums[end]) {
                small++;
                if (i > small) swap(nums, i, small);
            }
        }
        return small;
    }

    private void threePartition(int[] nums, int midNum) {
        int start = 0, end = nums.length - 1;
        for (int i = start; i <= end; i++) {
            while (nums[i] > midNum && i < end) swap(nums, i, end--);
            while (nums[i] < midNum && i > start) swap(nums, i, start++);
        }
    }
}
