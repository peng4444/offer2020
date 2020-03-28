package cn.offer2020.pbj.demo.leetcode.a_queue_stack;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName: Demo594
 * @Author: pbj
 * @Date: 2020/1/14 14:17
 * @Description: TODO 最长和谐子序列 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 */
public class Demo594 {

    public static int findLHS(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int size = 1;
            if(hashMap.containsKey(nums[i])){
                size = size+hashMap.get(nums[i]);
            }
            hashMap.put(nums[i], size);
        }
        for (int i = 0; i < nums.length; i++) {
            int res = hashMap.get(nums[i]);
            int res1 = 0;
            int res2 = 0;
            if(hashMap.containsKey(nums[i]+1)){
                res1 = res1 + hashMap.get(nums[i] + 1);
            }
            if (hashMap.containsKey(nums[i] - 1)) {
                res2 = res2 + hashMap.get(nums[i]-1);
            }
            if(res1>0||res2>0){
                res = res + Math.max(res1, res2);
                ans = Math.max(ans, res);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2,3,3,3,4,4,4};
        System.out.println(findLHS(nums));;
    }
    //哈希映射 + 单次扫描
    public class Solution {
        public int findLHS(int[] nums) {
            HashMap < Integer, Integer > map = new HashMap < > ();
            int res = 0;
            for (int num: nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (map.containsKey(num + 1))
                    res = Math.max(res, map.get(num) + map.get(num + 1));
                if (map.containsKey(num - 1))
                    res = Math.max(res, map.get(num) + map.get(num - 1));
            }
            return res;
        }
    }

    //排序后，用两个指针，类似于窗口
    class Solution2 {
        public int findLHS(int[] nums) {
            Arrays.sort(nums);
            int begin = 0,res = 0;
            for(int end = 0;end < nums.length;end++){
                while(nums[end] - nums[begin] > 1)
                    begin++;
                if(nums[end] - nums[begin] == 1)
                    res = Math.max(res,end - begin + 1);
            }
            return res;
        }
    }
    //N^2
    public class Solution3 {
        public int findLHS(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                int count = 0;
                boolean flag = false;
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] == nums[i])
                        count++;
                    else if (nums[j] + 1 == nums[i]) {
                        count++;
                        flag = true;
                    }
                }
                if (flag)
                    res = Math.max(count, res);
            }
            return res;
        }
    }
}
