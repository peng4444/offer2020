package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @pClassName: Demo560
 * @author: pengbingjiang
 * @create: 2020/7/2 15:51
 * @description: TODO 560.和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 */
public class Demo560 {

    /**
     扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
     **/
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ret = 0;

        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if(map.containsKey(sum-k))
                ret += map.get(sum-k);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return ret;
    }

    //暴力优化
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        for (int left = 0; left < len; left++) {
            int sum = 0;
            // 区间里可能会有一些互相抵销的元素
            for (int right = left; right < len; right++) {
                sum += nums[right];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    //暴力
    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        for(int i = 0;i < nums.length;i++){
            int result = 0;
            for(int j = i;j < nums.length;j++){
                result += nums[j];
                if(result == k){
                    count++;
                }
            }
        }
        return count;
    }
}
