package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @ClassName: Demo278
 * @Author: pbj
 * @Date: 2020/4/8 12:07
 * @Description: TODO 278. 第一个错误的版本
 */
public class Demo278 {
    boolean isBadVersion(int version) {
        return true;
    }
    //二分查找
    public int firstBadVersion(int n) {
        int low =  0,high = n;
        while(low<=high){
            int mid= low + (high-low)/2;
            if(isBadVersion(mid)){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return low;
    }

    //线性扫描
    public int firstBadVersion1(int n) {
        for (int i = 1; i < n; i++) {
            if (isBadVersion(i)) {
                return i;
            }
        }
        return n;
    }
}
