package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo33
 * @Author: pbj
 * @Date: 2020/5/8 17:26
 * @Description: TODO 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 */
public class Demo33 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len-1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] < nums[right]){
                if(nums[mid] < target && target <= nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            }
            else{
                if(nums[left] <= target && target < nums[mid])
                    right = mid-1;
                else
                    left = mid+1;
            }
        }
        return -1;
    }

    public int search1(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                return i;
            }
        }
        return -1;
    }
}
