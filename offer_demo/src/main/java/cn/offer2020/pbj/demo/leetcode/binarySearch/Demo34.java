package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo34
 * @Author: pbj
 * @Date: 2020/4/8 12:13
 * @Description: TODO 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class Demo34 {
    //二分查找
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums,target);
        int last = binarySearch(nums,target+1)-1;
        if(first==nums.length||nums[first]!=target){
            return new int[]{-1,-1};
        }else{
            return new int[]{first,Math.max(first,last)};
        }
    }
    public int binarySearch(int[] nums, int target) {
        int low = 0,high = nums.length;
        while(low<high){
            int mid = low + (high-low)/2;
            if(nums[mid]>=target){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return low;
    }

    //线性扫描
    public int[] searchRange1(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        // find the index of the leftmost appearance of `target`.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetRange[0] = i;
                break;
            }
        }

        // if the last loop did not find any index, then there is no valid range
        // and we return [-1, -1].
        if (targetRange[0] == -1) {
            return targetRange;
        }

        // find the index of the rightmost appearance of `target` (by reverse
        // iteration). it is guaranteed to appear.
        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }

        return targetRange;
    }
}
