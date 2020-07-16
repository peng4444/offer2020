package cn.offer2020.pbj.demo.leetcode.windows;

import java.util.*;

/**
 * @pClassName: Demo493
 * @author: pengbingjiang
 * @create: 2020/7/16 21:52
 * @description: TODO 493. 翻转对
 */
public class Demo493 {
    public class Solution {
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            return mergeSort(nums, 0, nums.length - 1);
        }
        private int mergeSort(int[] nums, int l, int r) {
            if (l >= r) return 0;
            int mid = l + (r - l)/2;
            int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);
            int[] cache = new int[r - l + 1];
            int i = l, t = l, c = 0;
            for (int j = mid + 1; j <= r; j++, c++) {
                while (i <= mid && nums[i] <= 2 * (long)nums[j]) i++;
                while (t <= mid && nums[t] < nums[j]) cache[c++] = nums[t++];
                cache[c] = nums[j];
                count += mid - i + 1;
            }
            while (t <= mid) cache[c++] = nums[t++];
            System.arraycopy(cache, 0, nums, l, r - l + 1);
            return count;
        }
    }

    // 翻转对
    public int reversePairs1(int[] nums) {
        int ans = 0;
        List<Long> helper = new ArrayList<>();
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            int t = binarySearch(helper, nums[i]);
            ans += t;
            long val = (long) nums[i] << 1;
            int idx = binarySearch(helper, val);
            helper.add(idx, val);
        }
        return ans;
    }

    private int binarySearch(List<Long> nums, long target) {
        int lo = 0;
        int hi = nums.size() - 1;
        int ans = nums.size();
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            Long val = nums.get(mid);
            if (target > val) {
                lo = mid + 1;
            } else {
                ans = mid;
                hi = mid - 1;
            }
        }
        return ans;
    }

    //超时
    public int reversePairs(int[] nums) {
        int count = 0;
        for(int i = 0;i<nums.length;i++){
            for(int j = i+1;j<nums.length;j++){
                long n1 = nums[i];
                long n2 = (long)nums[j]*2;
                if(n1>n2){
                    count++;
                }
            }
        }
        return count;
    }

    //树状数组 + 离散化
    public int reversePairs2(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        // 离散化
        TreeSet<Long> set = new TreeSet<>();
        for (int num : nums) set.add((long) num << 1);
        Map<Long, Integer> map = new HashMap<>();
        int rank = 1;
        for (Long num : set) map.put(num, rank++);

        List<Long> doub = new ArrayList<>(set);
        Collections.sort(doub);

        // 初始化
        t = new int[set.size() + 1];
        n = set.size();

        int ans = 0;
        for (int i = len - 1; i >= 0; i--) {
            int index = binarySearch(doub, nums[i]);
            if (index - 1 >= 0) {
                Long num = doub.get(index - 1);
                rank = map.get(num);
                ans += query(rank);
            }
            update(map.get((long) nums[i] << 1), 1);
        }
        return ans;
    }

    private int[] t;

    private int n;

    private int lowbit(int i) {
        return i & (-i);
    }

    private void update(int i, int k) {
        while (i <= n) {
            t[i] += k;
            i += lowbit(i);
        }
    }

    private int query(int i) {
        int ans = 0;
        while (i > 0) {
            ans += t[i];
            i -= lowbit(i);
        }
        return ans;
    }

    private int binarySearch(List<Long> nums, int target) {
        int ans = nums.size(), lo = 0, hi = nums.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            Long val = nums.get(mid);
            if (target > val) {
                lo = mid + 1;
            } else {
                ans = mid;
                hi = mid - 1;
            }
        }
        return ans;
    }
}
