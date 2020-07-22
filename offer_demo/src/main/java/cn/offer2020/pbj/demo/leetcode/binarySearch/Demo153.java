package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo153
 * @Author: pbj
 * @Date: 2020/6/17 16:45
 * @Description: TODO 153. 寻找旋转排序数组中的最小值,154. 寻找旋转排序数组中的最小值II,
 */
public class Demo153 {
    //二分查找
    public int findMin(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]<nums[right]){
                right = mid;
            }else if(nums[mid]>nums[right]){
                left = mid + 1;
            }else{
                return nums[mid];
            }
        }
        return nums[left];
    }

    public int findMin1(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            min = Math.min(min,nums[i]);
        }
        return min;
    }

    //154.寻找旋转排序数组中的最小值II
    public int findMin2(int[] nums) {
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(nums[mid]<nums[right]){
                right = mid;
            }else if(nums[mid]>nums[right]){
                left = mid + 1;
            }else{
                right--;
            }
        }
        return nums[left];
    }

    //剑指 Offer 11. 旋转数组的最小数字
    public int minArray(int[] numbers) {
        int left = 0,right = numbers.length-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(numbers[mid]<numbers[right]){
                right = mid;
            }else if(numbers[mid]>numbers[right]){
                left = mid +1;
            }else{
                right--;
            }
        }
        return numbers[left];
    }
}
