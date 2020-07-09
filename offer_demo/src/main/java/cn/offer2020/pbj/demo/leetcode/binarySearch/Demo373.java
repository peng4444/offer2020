package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.*;

/**
 * @pClassName: Demo373
 * @author: pengbingjiang
 * @create: 2020/7/9 18:01
 * @description: TODO 373. 查找和最小的K对数字
 */
public class Demo373 {

    //优先队列
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (nums1[o1[0]]+nums2[o1[1]]) - (nums1[o2[0]]+nums2[o2[1]]));
        List<List<Integer>> result = new ArrayList<>();

        if (n1 == 0 || n2 == 0 || k == 0) return result;
        for (int i = 0; i < n1; i++) pq.offer(new int[]{i, 0});

        while(pq.size() > 0 && k > 0) {
            int[] pair = pq.poll();
            if (pair[1] + 1 < n2) pq.offer(new int[]{pair[0], pair[1]+1});
            List<Integer> temp = new ArrayList<>();
            temp.add(nums1[pair[0]]);
            temp.add(nums2[pair[1]]);
            result.add(temp);
            k--;
        }
        return result;
    }

    //大顶堆
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        k = Math.min(k,nums1.length*nums2.length); //注意k的取值
        if(k==0) return res;

        Queue<int[]> Q = new PriorityQueue<>(k,(o1, o2)->o2[0]+o2[1]-o1[0]-o1[1]);
        for(int e1:nums1)
            for(int e2:nums2){
                if(Q.size()<k) {
                    Q.offer(new int[]{e1,e2});
                }else if(e1+e2<=Q.peek()[0]+Q.peek()[1]){
                    Q.poll();
                    Q.offer(new int[]{e1,e2});
                }
            }
        while(k-->0){
            int[] e = Q.poll();
            res.add(0, Arrays.asList(e[0],e[1]));
        }
        return res;
    }

    //暴力法-暴力枚举全部n1*n2对数字，排序后取其前K对
    public List<List<Integer>> kSmallestPairs0(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length, n2 = nums2.length;
        List<List<Integer>> result = new ArrayList<>();
        if (n1 == 0 || n2 == 0 || k == 0) return result;
        int[][] arr = new int[n1 * n2][2];
        int idx = 0;

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                arr[idx][0] = nums1[i];
                arr[idx][1] = nums2[j];
                idx++;
            }
        }

        Arrays.sort(arr, (o1, o2) -> (o1[0] + o1[1]) - (o2[0] + o2[1]));

        for (int i = 0; i < Math.min(k, arr.length); i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(arr[i][0]);
            temp.add(arr[i][1]);
            result.add(temp);
        }

        return result;
    }
}
