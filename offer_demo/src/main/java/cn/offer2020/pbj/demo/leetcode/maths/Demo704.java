package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo704
 * @Author: pbj
 * @Date: 2020/3/27 09:15
 * @Description: TODO 704:二分查找
 */
public class Demo704 {

    //时间复杂度O(logN)
    public int binarySearch(int[] nums,int target){
        if(nums.length<=0) return -1;
        int left = 0,right = nums.length-1;
        while (left<=right){
            int mid = left +(right-left)/2;
             if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid-1;
            } else{
                left = mid+1;
            }
        }
        return -1;
    }
}
