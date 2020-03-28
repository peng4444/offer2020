package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter4;

/**
 * @ClassName: BinarySearch
 * @Author: pbj
 * @Date: 2020/3/6 13:05
 * @Description: TODO 二分查找
 */
public class BinarySearch {

    //迭代
    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }

    //寻找左侧边界的二分搜索
    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }


    //寻找右侧边界的二分查找   ​
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

    //递归
    public static int binarySearch2(int[] array, int k) {
        return rank(k, array, 0, array.length - 1);
    }

    public static int rank(int key, int[] array, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (lo + hi) / 2;
        if (key < array[mid]) {
            return rank(key, array, lo, mid);
        } else if (key > array[mid]) {
            return rank(key, array, mid, hi);
        } else {
            return mid;
        }

    }
}
