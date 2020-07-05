package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: Demo137
 * @Author: pbj
 * @Date: 2020/4/3 11:30
 * @Description: TODO 137. 只出现一次的数字 II
 */
public class Demo137 {

    //位运算符：NOT，AND 和 XOR
    public int singleNumber2(int[] nums) {
        int seenOnce=0,seenTwice=0;
        for (int num : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1; // can't find it.
    }

    //将输入数组存储到 HashSet，然后使用 HashSet 中数字和的三倍与数组之和比较。
    public int singleNumber1(int[] nums) {
        Set<Long> set = new HashSet<>();
        long sumSet = 0, sumArray = 0;
        for(int n : nums) {
            sumArray += n;
            set.add((long)n);
        }
        for(Long s : set) {
            sumSet += s;
        }
        return (int)((3 * sumSet - sumArray) / 2);
    }
}
