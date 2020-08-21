package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.HashMap;

/**
 * @pClassName: Demo523
 * @author: pengbingjiang
 * @create: 2020/8/21 10:28
 * @description: TODO 523.连续的子数组和
 */
public class Demo523 {

    public class Solution {
        public boolean checkSubarraySum(int[] nums, int k) {
            int sum = 0;
            HashMap< Integer, Integer > map = new HashMap < > ();
            map.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (k != 0)
                    sum = sum % k;
                if (map.containsKey(sum)) {
                    if (i - map.get(sum) > 1)
                        return true;
                } else
                    map.put(sum, i);
            }
            return false;
        }
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        for(int i = 0;i<len-1;i++){
            int sum = nums[i];
            for(int j = i+1;j<len;j++){
                sum+=nums[j];
                if(sum==k||(k!=0&&sum%k==0)){
                    return true;
                }
            }
        }
        return false;
    }
}
