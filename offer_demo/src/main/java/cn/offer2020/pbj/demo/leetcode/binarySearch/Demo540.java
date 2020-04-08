package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo540
 * @Author: pbj
 * @Date: 2020/4/8 11:57
 * @Description: TODO 540. 有序数组中的单一元素
 */
public class Demo540 {
    //二分查找 O(logN)
    public int singleNonDuplicate(int[] nums) {
        int low = 0,high = nums.length-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(mid%2==1){
                mid--;
            }
            if(nums[mid]==nums[mid+1]){
                low = mid + 2;
            }else{
                high = mid;
            }
        }
        return nums[high];
    }
    //暴力法
    public int singleNonDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i+=2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }
}
