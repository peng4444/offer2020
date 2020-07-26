package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @pClassName: DemoLCP12
 * @author: pengbingjiang
 * @create: 2020/7/25 16:24
 * @description: TODO LCP 12.小张刷题计划
 */
public class DemoLCP12 {
    public int minTime(int[] time, int m) {
        if(m>=time.length) return 0;
        long left = 0,right = 0;
        for(int i:time){
            right+=i;
        }
        int count = 1;
        long res = 0;
        while(left<right){
            long mid = left + (right-left)/2;
            int temp = 0;
            count = 1;
            int max = -1;
            for(int i:time){
                temp+=i;
                max = Math.max(max,i);
                if(temp-max>mid){
                    count++;
                    temp = i;
                    max = i;
                }
            }
            res = mid;
            if(count>m){
                left = mid + 1;
            }else if(count<=m){
                right = mid;
            }
        }
        return (int)left;
    }
}
