package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.*;

/**
 * @ClassName: Demo18
 * @Author: pbj
 * @Date: 2020/5/7 10:40
 * @Description: TODO 18.四数之和
 * 给定一个包含n个整数的数组nums和一个目标值target，判断nums中是否存在四个元素a，b，c和 d，
 * 使得a+b+c+d的值与target相等？找出所有满足条件且不重复的四元组。
 */
public class Demo18 {
    //使用双循环固定两个数，用双指针找另外两个数，通过比较与target 的大小，移动指针。
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j - i > 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target) continue;

                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    int tmp = nums[i] + nums[j] + nums[left] + nums[right];
                    if (tmp == target) {
                        List<Integer> tmpList = new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        res.add(tmpList);
                        while (left < right && nums[left] == nums[left + 1]) left += 1;
                        while (left < right && nums[right] == nums[right - 1]) right -= 1;
                        left += 1;
                        right -= 1;
                    } else if (tmp > target) right -= 1;
                    else left += 1;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length<4) return res;
        Arrays.sort(nums);
        if(nums[0]>target/4 || nums[len-1]<target/4) return res;
        for(int i = 0;i<len;i++){
            if(nums[i]>target/4) break;
            if(i>0 && nums[i]==nums[i-1]) continue;
            int sum = target-nums[i];
            for(int j = i+1;j<len;j++){
                if(nums[j]>sum/3) break;
                if(j>i+1 && nums[j]==nums[j-1]) continue;
                int l = j+1;
                int r = len-1;
                while(l<r){
                    if(nums[r]<sum/3) break;
                    int temp = nums[j] + nums[l] +nums[r];
                    if(temp == sum){
                        res.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while(l<r && nums[l] == nums[l+1]) l++;
                        while(l<r && nums[r] == nums[r-1]) r--;
                        l++;
                        r--;
                    }else if(temp>sum){//结果大了右指针往左
                        r--;
                    }else{//结果小了左指针往右
                        l++;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum3(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        Arrays.sort(nums);
        // 先处理第一对，把它们的sum存下来
        for(int i = 0; i < nums.length - 3; i++) {
            for(int j = i + 1; j < nums.length - 2; j++) {
                int currSum = nums[i] + nums[j];
                List<List<Integer>> pairs = map.getOrDefault(currSum, new ArrayList<>());
                pairs.add(Arrays.asList(i, j));
                map.put(currSum, pairs);
            }
        }

        // 在其后做two sum
        for(int i = 2; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                int currSum = nums[i] + nums[j];
                List<List<Integer>> prevPairs = map.get(target - currSum);
                if(prevPairs == null) {
                    continue;
                }
                for(List<Integer> pair : prevPairs) {
                    if(pair.get(1) < i) {
                        set.add(Arrays.asList(nums[pair.get(0)], nums[pair.get(1)], nums[i], nums[j]));
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}
