package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @ClassName: Demo283
 * @Author: pbj
 * @Date: 2020/3/6 17:01
 * @Description: TODO 283移动零
 */
public class Demo283 {

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


}
