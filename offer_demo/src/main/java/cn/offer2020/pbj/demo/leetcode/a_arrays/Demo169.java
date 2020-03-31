package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo169
 * @Author: pbj
 * @Date: 2019/12/20 11:26
 * @Description: TODO 多数元素
 */
public class Demo169 {

    /* *
     * 功能描述: 投票算法【适用于1/2情况】 从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1，减到0就重新换个数开始计数，总能找到最多的那个
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/20 13:35
     */
    public int majorityElement5(int[] nums) {
        int count = 0;
        int ret = 0;
        for (int num : nums) {
            if (count == 0) {
                ret = num;
            }
            count += (num == ret) ? 1 : -1;
        }
        return ret;
    }

    public int majorityElement4(int[] nums) {
        //位运算法,统计每个数字每一位0，1出现的次数，如果某一位1出现的次数多则该位为1，0同理；
        //最后按为统计出来的数就是众数
        int res=0;
        int len = nums.length;
        for(int i=0;i<32;i++){
            int ones=0,zero=0;
            for(int j=0;j < len; j++){
                if(ones>len/2 ||zero>len/2) break;
                if((nums[j]&(1<<i)) != 0) ones++;
                else zero++;
            }
            if(ones > zero)
                res = res|(1<<i);
        }
        return res;

    }

    /* *
     * 功能描述: 排序
     * 时间复杂度O(NlogN)
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/20 13:26
     */
    public int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /* *
     * 功能描述: 哈希表存储
     * 时间复杂度O(N)
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/20 13:24
     */
    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> count = countNums(nums);
        Map.Entry<Integer,Integer> entry = null;
        for (Map.Entry<Integer, Integer> entry1 : count.entrySet()) {
            if (entry == null || entry1.getValue() > entry.getValue()) {
                entry = entry1;
            }
        }
        return entry.getValue();
    }

    public Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    /* *
     * 功能描述: 暴力求解
     * 时间复杂度O(N^2)
     * @param: [nums]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/20 13:01
     */
    public int majorityElement(int[] nums) {
        int majorityCount = nums.length/2;
        for (int num : nums) {
            int count = 0;
            for (int elem : nums) {
                if (elem == num) {
                    count++;
                }
            }
            if (count > majorityCount) {
                return num;
            }
        }
        return -1;
    }
}
