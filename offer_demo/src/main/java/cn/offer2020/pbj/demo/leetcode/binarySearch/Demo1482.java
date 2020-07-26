package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @pClassName: Demo1482
 * @author: pengbingjiang
 * @create: 2020/7/26 10:51
 * @description: TODO 1482.制作m束花所需的最少天数
 */
public class Demo1482 {
    public int minDays(int[] bloomDay, int m, int k) {
        if(m*k>bloomDay.length) return -1;
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for(int num:bloomDay){
            left = Math.min(left,num);
            right = Math.max(right,num);
        }
        while(left<right){
            int mid = left + (right - left) / 2;
            int curM = 0;
            int curK = 0;
            for(int value:bloomDay){
                if(value<=mid){
                    curK++;
                }else{
                    curK = 0;
                }
                if(curK==k){
                    curM++;
                    curK = 0;
                }
            }
            if(curM<m){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
