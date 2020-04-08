package cn.offer2020.pbj.demo.leetcode.a_arrays;

/**
 * @ClassName: Demo27
 * @Author: pbj
 * @Date: 2019/12/15 10:47
 * @Description: TODO 移除元素
 */
public class Demo27 {

    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    /* *
     * 功能描述: 当我们遇到 nums[i] = valnums[i]=val 时，我们可以将当前元素与最后一个元素进行交换，并释放最后一个元素。这实际上使数组的大小减少了 1。
     * @param: [nums, val]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/15 10:52
     */
    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                n--;
            } else {
                i++;
            }
        }
        return n;
    }
}
