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
    //寻找左边界的二分搜索
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

    public int[] searchRange2(int[] nums, int target) {
        int l = 0,r = nums.length-1,m;
        int [] res = new int[2];
        if(nums.length==0){
            res[0]=-1;
            res[1]=-1;
            return res;
        }
        while(l<r){
            m=l+(r-l)/2;
            if(nums[m]<target){
                l=m+1;
            }else if(nums[m]>target){
                r=m-1;
            }else{
                r=m;
            }
        }
        if(nums[l]!=target){
            res[0]=-1;
            res[1]=-1;
            return res;
        }else{
            res[0]=l;
            while(l<nums.length){
                if(nums[l]==target){
                    l++;
                }else{
                    res[1]=l-1;
                    return res;
                }
            }
            res[1]=l-1;
            return res;
        }
    }

    //线性扫描
    public int[] searchRange1(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetRange[0] = i;
                break;
            }
        }

        if (targetRange[0] == -1) {
            return targetRange;
        }

        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }

        return targetRange;
    }
}
