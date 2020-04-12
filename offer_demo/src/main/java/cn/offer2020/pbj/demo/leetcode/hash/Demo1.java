package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo1
 * @Author: pbj
 * @Date: 2019/12/9 19:17
 * @Description: TODO  1.两数之和 hash+双指针
 */
public class Demo1 {

    /* *
     * 功能描述: 两遍哈希表 一个简单的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
     * 然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target - nums[i]target−nums[i]）是否存在于表中。
     * 注意，该目标元素不能是 nums[i]nums[i] 本身！
     * 时间复杂度 O(n) 空间复杂度O(n)
     * @param: [nums, target]
     * @return: int[]
     * @auther: pbj
     * @date: 2019/12/9 19:24
     */
    public static int [] twoSum2(int[] nums,int target){
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /* *
     * 功能描述: 一遍哈希表 在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     * 时间复杂度 O(n) 空间复杂度O(n)
     * @param: [nums, target]
     * @return: int[]
     * @auther: pbj
     * @date: 2019/12/9 19:30
     */
    public static int [] twoSum1(int[] nums,int target){
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement),i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    /* *
     * 功能描述: 暴力法很简单，遍历每个元素 xx，并查找是否存在一个值与 target - xtarget−x 相等的目标元素。
     * 时间复杂度 O(n^2) 空间复杂度O(1)
     * @param: [nums, target]
     * @return: int[]
     * @auther: pbj
     * @date: 2019/12/9 19:22
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(twoSum(nums,target));
        System.out.println(twoSum2(nums,target));
        System.out.println(twoSum1(nums,target));
    }
}
