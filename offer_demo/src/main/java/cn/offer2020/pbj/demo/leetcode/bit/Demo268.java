package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo268
 * @Author: pbj
 * @Date: 2020/4/19 11:00
 * @Description: TODO 268. 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数
 */
public class Demo268 {

    public int missingNumber(int[] nums) {
        //解法一 排序
        // Arrays.sort(nums);
        // for(int i = 0; i < nums.length; i++){
        //     if(nums[i] != i) return i;
        // }
        // return nums.length;

        //解法二 哈希表
        // Set<Integer> set = new HashSet<>();
        // for(int i = 0; i < nums.length; i++) set.add(nums[i]);
        // for(int i = 0; i <= nums.length; i++)
        //     if(!set.contains(i)) return i;
        // return -1;

        //解法三 位运算
        // int res = nums.length;
        // for(int i = 0; i < nums.length; i++){
        //     res ^= nums[i] ^ i;
        // }
        // return res;

        //解法四 数学
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        return nums.length * (nums.length + 1) / 2 - sum;
    }
}
