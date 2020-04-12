package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.Arrays;

/**
 * @ClassName: Demo26
 * @Author: pbj
 * @Date: 2019/12/15 09:34
 * @Description: TODO  删除排序数组中的重复项
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
