package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo80
 * @Author: pbj
 * @Date: 2020/3/29 16:42
 * @Description: TODO
 */
public class Demo80 {

    //大神做法
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) nums[i++] = n;
        }
        return i;
    }

    //方法一：删除多余的重复项
    public int removeDuplicates1(int[] nums) {
        int i = 1, count = 1, length = nums.length;
        while (i < length) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > 2) {
                    this.remElement(nums, i);
                    i--;
                    length--;
                }
            } else {
                count = 1;
            }
            i++;
        }
        return length;
    }

    public int[] remElement(int[] nums, int index) {
        for (int i = index + 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }
        return nums;
    }

    //方法二：覆盖多余的重复项
    public int removeDuplicates2(int[] nums) {
        int j = 1,count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
