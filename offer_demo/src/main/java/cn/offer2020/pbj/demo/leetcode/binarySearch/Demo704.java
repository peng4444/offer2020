package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo704
 * @Author: pbj
 * @Date: 2020/4/8 11:43
 * @Description: TODO 704. 二分查找
 */
public class Demo704 {

    public int search(int[] nums, int target) {
        int low = 0,high = nums.length-1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                high = mid -1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }
}
