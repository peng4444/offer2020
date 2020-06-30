package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo283
 * @Author: pbj
 * @Date: 2020/3/6 17:01
 * @Description: TODO 283.移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 */
public class Demo283 {
    //思路：设置一个index，表示非0数的个数，循环遍历数组，
    //如果不是0，将非0值移动到第index位置,然后index + 1
    //遍历结束之后，index值表示为非0的个数，再次遍历，从index位置后的位置此时都应该为0
    public void moveZeroes(int[] nums){
        int index = 0;
        for(int i = 0; i< nums.length;i++){
            if(nums[i]!=0){
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    //双指针-不为0赋值给j,如果i不等于j,i赋值为0.
    public void moveZeroes1(int[] nums) {
        int j = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[j]=nums[i];
                if(i!=j){
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    //双指针
    public void moveZeroes2(int[] nums){
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }
}
