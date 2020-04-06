package cn.offer2020.pbj.demo.leetcode.a_arrays;

/**
 * @ClassName: Demo75
 * @Author: pbj
 * @Date: 2020/4/6 11:11
 * @Description: TODO 75. 颜色分类
 */
public class Demo75 {

    public void sortColors(int[] nums) {
        int zero = -1,one = 0,two = nums.length;
        while(one<two){
            if(nums[one]==0){
                swap(nums,++zero,one++);
            }else if(nums[one]==2){
                swap(nums,--two,one);
            }else{
                ++one;
            }
        }
    }
    public void swap(int[] num, int i,int j){
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    public void sortColors1(int[] nums) {
        //双指针
        int low = 0, high = nums.length - 1;
        int i = 0;
        while(i <= high){
            if(nums[i] == 0){
                int tmp = nums[i];
                nums[i] = nums[low];
                nums[low] = tmp;
                ++low; ++i;
            }else if(nums[i] == 1){
                ++i;
            }else if(i <= high && nums[i] == 2){
                int tmp = nums[i];
                nums[i] = nums[high];
                nums[high] = tmp;
                --high;
            }
        }
    }
}
