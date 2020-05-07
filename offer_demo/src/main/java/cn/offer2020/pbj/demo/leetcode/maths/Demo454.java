package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo454
 * @Author: pbj
 * @Date: 2020/5/7 10:50
 * @Description: TODO 454. 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 */
public class Demo454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //遍历A和B所有元素和的组合情况，并记录在ab_map中，ab_map的key为两数和，value为该两数和出现的次数
        //遍历C和D所有元素和的组合情况，取和的负值判断其是否在ab_map 中，若存在则取出ab_map对应的value值，count=count+value
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (map.containsKey(A[i] + B[j])) {
                    map.put(A[i] + B[j], map.get(A[i] + B[j]) + 1);
                } else {
                    map.put(A[i] + B[j], 1);
                }
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (map.containsKey(-C[i] - D[j])) {
                    ans = ans +map.get(-C[i] - D[j]);
                }
            }
        }
        return ans;
    }
}
