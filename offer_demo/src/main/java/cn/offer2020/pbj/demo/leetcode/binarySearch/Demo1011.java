package cn.offer2020.pbj.demo.leetcode.binarySearch;

/**
 * @pClassName: Demo1011
 * @author: pengbingjiang
 * @create: 2020/7/26 10:20
 * @description: TODO 1011.在D天内送达包裹的能力
 */
public class Demo1011 {
    public int shipWithinDays(int[] weights, int D) {
        if(weights==null||weights.length==0) return 0;
        int left = 0;
        int right = 0;
        for(int weight:weights){
            right+=weight;
            left = Math.max(weight,left);
        }
        while(left<right){
            int mid = left + (right-left)/2;
            int index = 0;
            int count = 0;
            while(index<weights.length){
                int sum = 0;
                while(index<weights.length && sum+weights[index]<=mid){
                    sum+=weights[index++];
                }
                count++;
            }
            if(count<=D){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
}
