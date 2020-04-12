package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.Arrays;

/**
 * @ClassName: Demo698
 * @Author: pbj
 * @Date: 2020/1/3 13:53
 * @Description: TODO 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 */
public class Demo698 {
    /* *
     * 功能描述: 整体就是一个暴力的解法，先算出子集的和是多少，并抽象成k个桶，每个桶的值是子集的和。
     * 然后尝试所有不同的组合（即放数到桶中），如果存在一种组合可以使每个桶都正好放下，那么返回可以。如果不存在，返回不可以。
     * @param: [nums, k]
     * @return: boolean
     * @auther: pbj
     * @date: 2020/1/3 14:13
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //因为题目限制条件不用担心溢出
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % k != 0){
            return false;
        }
        //求出子集的和
        sum = sum / k;
        //排序 小的放最前面大的放最后面
        Arrays.sort(nums);
        //如果子集的和小于数组最大的直接返回false
        if(nums[nums.length - 1] > sum){
            return false;
        }
        //建立一个长度为k的桶
        int[] arr = new int[k];
        //桶的每一个值都是子集的和
        Arrays.fill(arr, sum);
        //从数组最后一个数开始进行递归
        return help(nums, nums.length - 1, arr, k);
    }

    boolean help(int[] nums, int cur, int[] arr, int k){
        //已经遍历到了-1说明前面的所有数都正好可以放入桶里，那所有桶的值此时都为0，说明找到了结果，返回true
        if(cur < 0){
            return true;
        }
        //遍历k个桶
        for(int i = 0; i < k; i++){
            //如果正好能放下当前的数或者放下当前的数后，还有机会继续放前面的数（剪枝）
            if(arr[i] == nums[cur] || (cur > 0 && arr[i] - nums[cur] >= nums[0])){
                //放当前的数到桶i里
                arr[i] -= nums[cur];
                //开始放下一个数
                if(help(nums, cur - 1, arr, k)){
                    return true;
                }
                //这个数不该放在桶i中
                //从桶中拿回当前的数
                arr[i] += nums[cur];
            }
        }
        return false;
    }

    class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int N = nums.length;
            Arrays.sort(nums);
            int sum = Arrays.stream(nums).sum();
            int target = sum / k;
            if (sum % k > 0 || nums[N - 1] > target) return false;

            boolean[] dp = new boolean[1 << N];
            dp[0] = true;
            int[] total = new int[1 << N];

            for (int state = 0; state < (1 << N); state++) {
                if (!dp[state]) continue;
                for (int i = 0; i < N; i++) {
                    int future = state | (1 << i);
                    if (state != future && !dp[future]) {
                        if (nums[i] <= target - (total[state] % target)) {
                            dp[future] = true;
                            total[future] = total[state] + nums[i];
                        } else {
                            break;
                        }
                    }
                }
            }
            return dp[(1 << N) - 1];
        }
    }
}
