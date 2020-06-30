package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.*;

/**
 * @ClassName: Demo15
 * @Author: pbj
 * @Date: 2019/12/17 11:16
 * @Description: TODO 15.三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 */
public class Demo15 {

    /* *
     * 功能描述: 暴力解法：三层循环，比较。超出时间限制,考虑去重
     * 时间复杂度O(N^3)
     * @param: [nums, target]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     * @auther: pbj
     * @date: 2019/12/17 11:29
     */
    private List<List<Integer>> directlySolution(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        Set<List<Integer>> result = new LinkedHashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> value = Arrays.asList(nums[i], nums[j], nums[k]);
                        result.add(value);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }


    /* *
     * 功能描述: 三层优化，将所有的数存入散列表，两层循环，比较差值是否包含在散列表中
     * 时间复杂度O(N^2)
     * @param: [nums, target]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     * @auther: pbj
     * @date: 2019/12/17 11:30
     */
    private List<List<Integer>> hashSolution(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return Collections.emptyList();
        }
        Set<List<Integer>> result = new LinkedHashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int target = -nums[i];
            Map<Integer, Integer> hashMap = new HashMap<>(nums.length - i);
            for (int j = i + 1; j < nums.length; j++) {
                int v = target - nums[j];
                Integer exist = hashMap.get(v);
                if (exist != null) {
                    List<Integer> list = Arrays.asList(nums[i], exist, nums[j]);
                    list.sort(Comparator.naturalOrder());
                    result.add(list);
                } else {
                    hashMap.put(nums[j], nums[j]);
                }
            }
        }

        return new ArrayList<>(result);
    }

    /* *
     * 功能描述: 先对数组进行排序，然后迭代数据 取开始为定点a，取定点下一个元素b和最后一个元素c与target比较，
     * a+b+c>target c--； a+b+c>target b++;
     * @param: [nums, target]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     * @auther: pbj
     * @date: 2019/12/17 11:36
     */
    public List<List<Integer>> threeSum3(int[] nums, int target) {
        if (nums.length < 3) {
            throw new RuntimeException("number Array length No pass");
        }
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }
            if(i>0&&nums[i]==nums[i-1]) {
                continue;
            }
            int L  = i+1;
            int R  = len-1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L<R&& nums[L] == nums[L+1]) {
                        L++;
                    }
                    while (L<R&& nums[R] == nums[R+1]) {
                        R--;
                    }
                    L++;
                    R--;
                }
                else if(sum>0) {
                    R--;
                } else if(sum<0) {
                    L++;
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0;i<nums.length-2;i++){
            if(i==0||(i>0&&nums[i]!=nums[i-1])){
                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while(l < r){
                    if(nums[l]+nums[r]==sum){
                        ans.add(Arrays.asList(nums[i],nums[l],nums[r]));
                        while(l<r && nums[l]==nums[l+1]) {
                            l++;
                        }
                        while(l<r && nums[r]==nums[r-1]) {
                            r--;
                        }
                        l++;
                        r--;
                    }else if(nums[l]+nums[r]<sum){
                        while(l<r && nums[l]==nums[l+1]) {
                            l++;
                        }
                        l++;
                    }else{
                        while(l<r&&nums[r]==nums[r-1]) {
                            r--;
                        }
                        r--;
                    }
                }
            }
        }
        return ans;
    }
}
