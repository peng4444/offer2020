package cn.offer2020.pbj.demo.leetcode.windows;

import java.util.HashMap;
import java.util.Map;

/**
 * @pClassName: Demo992
 * @author: pengbingjiang
 * @create: 2020/7/30 10:15
 * @description: TODO 992.K个不同整数的子数组
 */
public class Demo992 {
    public int subarraysWithKDistinct(int[] A, int K) {
        return subarraysWithMostK(A,K) - subarraysWithMostK(A,K-1);
    }
    private int subarraysWithMostK(int[] nums,int k){
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        int res = 0;
        int left = 0;
        int right = 0;
        while(right<nums.length){
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            if(map.get(nums[right])==1){
                count++;
            }
            right++;
            while(count>k){
                map.put(nums[left],map.get(nums[left])-1);
                if(map.get(nums[left])==0){
                    count--;
                }
                left++;
            }
            res+=right-left+1;
        }
        return res;
    }

    public int subarraysWithKDistinct1(int[] A, int K) {
        Map<Integer, Integer> m = new HashMap<>();//int->count
        int res = 0, dp = 1, p = 0;
        for (int i = 0; i < A.length; i++) {
            if (!m.containsKey(A[i])) m.put(A[i], 1);
            else m.put(A[i], m.get(A[i]) + 1);
            if (m.keySet().size() > K) {
                m.remove(A[p]);
                p++;
                dp = 1;
            }
            if (m.keySet().size() == K) {
                while (m.get(A[p]) > 1) {
                    m.put(A[p], m.get(A[p]) - 1);
                    dp++;
                    p++;
                }
                res += dp;
            }
        }
        return res;
    }
}
