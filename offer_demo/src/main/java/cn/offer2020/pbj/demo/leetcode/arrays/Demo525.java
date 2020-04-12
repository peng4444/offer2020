package cn.offer2020.pbj.demo.leetcode.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo525
 * @Author: pbj
 * @Date: 2020/3/27 10:04
 * @Description: TODO
 */
public class Demo525 {

    //Hashmap
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxLen = 0,count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxLen = Math.max(maxLen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxLen;
    }

    //将原数组的0全部变为-1 则问题等价于“元素值总和为0的连续数组” 接着遍历数组 记录当前的前缀和的值
    // 若该前缀和的值已出现过 则说明标记中的下标到当前扫描的下标的这段数组的总和值是为0的
    public int findMaxLength2(int[] nums) {
        int res = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == 0 && i > res) {
                res = i + 1;
            }
            if (map.containsKey(sum)) {
                res = Math.max(i - map.get(sum), res);
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }

    //我写的十个啥
    public int findMaxLength1(int[] nums) {
        if(nums.length<2) return 0;
        int ans = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]+nums[i-1]==1){
                ans++;
            }
        }
        return ans;
    }
}
