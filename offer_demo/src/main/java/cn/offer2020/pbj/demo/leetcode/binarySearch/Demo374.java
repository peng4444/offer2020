package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @pClassName: Demo374
 * @author: pengbingjiang
 * @create: 2020/7/9 11:10
 * @description: TODO 374. 猜数字大小
 */
public class Demo374 {
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0) return mid;
            else if (guess(mid) == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    //三分查找
    public int guessNumber1(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0)
                return mid1;
            if (res2 == 0)
                return mid2;
            else if (res1 < 0)
                high = mid1 - 1;
            else if (res2 > 0)
                low = mid2 + 1;
            else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
    }

    int guess(int num) {
        return 0;
    }
}
