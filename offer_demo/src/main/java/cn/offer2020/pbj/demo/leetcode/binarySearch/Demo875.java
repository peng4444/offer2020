package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.Arrays;

/**
 * @pClassName: Demo875
 * @author: pengbingjiang
 * @create: 2020/7/26 09:40
 * @description: TODO 875.爱吃香蕉的珂珂
 */
public class Demo875 {
    public int minEatingSpeed(int[] piles, int H) {
        if(piles==null||piles.length==0){
            return 0;
        }
        int left = 0,right = 0;
        Arrays.sort(piles);
        left = 1;
        right = piles[piles.length-1];
        while(left<right){
            int mid = left + (right-left)/2;
            int sum = 0;
            for(int x:piles){
                if(x%mid==0){
                    sum+=x/mid;
                }else{
                    sum+=x/mid+1;
                }
            }
            if(sum>H){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
