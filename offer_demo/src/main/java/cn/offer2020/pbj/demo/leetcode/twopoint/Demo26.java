package cn.offer2020.pbj.demo.leetcode.twopoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @ClassName: Demo26
 * @Author: pbj
 * @Date: 2019/12/15 09:34
 * @Description: TODO  26.删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class Demo26 {
    /* *
     * 功能描述: 双指针法
     * 时间复杂度O(n),空间复杂度O(1)
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/15 9:46
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    //使用while循环的双指针
    public int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int q = 0;
        int p = 1;
        while (p < nums.length) {
            if (nums[q] != nums[p]) {
                nums[q + 1] = nums[p];
                q++;
            }
            p++;
        }
        return q + 1;
    }

    public int removeDuplicates5(int[] nums) {
        if (null == nums || nums.length == 1) return nums.length;
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }

    //上面的优化
    public int reverseDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }

    //TreeSet
    public int removeDuplicates4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : nums) {
            set.add(i);
        }
        Iterator<Integer> it = set.iterator();
        ArrayList<Integer> list = new ArrayList<>();
        while (it.hasNext()) {
            list.add(it.next());
        }
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[]  nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int len = removeDuplicates(nums);
//        int[] ans = new int[len];
//        for (int i = 0; i < len; i++) {
//            ans[i] = nums[i];
//        }
        int[] ans = Arrays.copyOfRange(nums, 0, len);
        System.out.println(Arrays.toString(ans));
    }
}
