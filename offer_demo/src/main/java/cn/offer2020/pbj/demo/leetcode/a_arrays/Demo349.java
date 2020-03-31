package cn.offer2020.pbj.demo.leetcode.a_arrays;

import java.util.*;

/**
 * @ClassName: Demo349
 * @Author: pbj
 * @Date: 2020/3/31 11:38
 * @Description: TODO
 */
public class Demo349 {

    //hashSet
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums1.length;i++){
            map.put(nums1[i],i);
        }
        for(int j = 0;j<nums2.length;j++){
            if(map.containsKey(nums2[j])){
                set.add(nums2[j]);
            }
        }
        int[] res = new int[set.size()];
        Iterator iterator = set.iterator();
        int k = 0;
        while(iterator.hasNext()){
            res[k++] = (int)iterator.next();
        }
        return res;
    }
    //方法二：内置函数
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }

    //自己写的错误代码
    public int[] intersection(int[] nums1, int[] nums2) {
        int [] ans = new int[nums1.length];
        int n = 0;
        for(int i = 0;i<nums1.length;i++){
            for(int j = 0;j<nums2.length;j++){
                if(nums1[i]==nums2[j])  ans[n++] = nums1[i];
            }
        }
        return ans;
    }
}
