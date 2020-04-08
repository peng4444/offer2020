package cn.offer2020.pbj.demo.leetcode.greed;

/**
 * @ClassName: Demo665
 * @Author: pbj
 * @Date: 2020/4/7 16:14
 * @Description: TODO 665. 非递减数列
 */
public class Demo665 {

    public boolean checkPossibility2(int[] nums) {
        int count = 0;
        for(int i = 1;i<nums.length&&count<2;i++){
            if(nums[i]>=nums[i-1]){
                continue;
            }
            count++;
            if(i-2>=0&&nums[i-2]>nums[i]){
                nums[i] =nums[i-1];
            }else{
                nums[i-1] = nums[i];
            }
        }
        return count<=1;
    }
    //往小看齐，遇到逆序的，先和上一个看齐，再和下一个看齐
    public boolean checkPossibility1(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                int temp = nums[i];
                if (i >= 1) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i] = nums[i + 1];
                }
                if (nums[i] > nums[i + 1]) {
                    nums[i] = temp;
                    nums[i + 1] = nums[i];
                }
                count++;
                if (count == 2) {
                    return false;
                }
            }
        }
        return true;
    }

    //自己写的  一部分测试用例无法通过 【3,4,2,3】
    public boolean checkPossibility(int[] nums) {
        if(nums.length==0) return true;
        int size = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                size++;
            }
        }
        if(size>1){
            return false;
        }
        return true;
    }
}
