package cn.offer2020.pbj.demo.leetcode;

import javafx.util.Pair;

import java.util.*;

/**
 * @ClassName: Demo973
 * @Author: pbj
 * @Date: 2020/6/11 14:51
 * @Description: TODO 973. 最接近原点的K个点
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 */
public class Demo973 {
    //自己写的，算法思路：将每一个点的距离平方加入到一个数组中，排序区前K大加入到List集合中，
    //再遍历每一个点的距离平方是否在集合中，在就加入结果集。算法时间比较长。
    public int[][] kClosest(int[][] points, int K) {
        if(points.length<=K){
            return points;
        }
        int[] arr = new int[points.length];
        for(int i = 0;i<points.length;i++){
            arr[i] = points[i][0]*points[i][0] + points[i][1] * points[i][1];
        }
        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>(K);
        for(int i = 0;i<K;i++){
            list.add(arr[i]);
        }
        int[][] ans = new int[K][2];
        int k = 0;
        for(int i = 0;i<points.length;i++){
            if(list.contains(points[i][0]*points[i][0] + points[i][1] * points[i][1])){
                ans[k][0] = points[i][0];
                ans[k][1] = points[i][1];
                k++;
            }
        }
        return ans;
    }

    //top K问题，采用堆即可
    public int[][] kClosest1(int[][] points, int K) {
        int[][] res = new int[K][2];
        PriorityQueue<Pair<Long, int[]>> priorityQueue =
                new PriorityQueue<>(K, new Comparator<Pair<Long, int[]>>() {
                    @Override
                    public int compare(Pair<Long, int[]> o1, Pair<Long, int[]> o2) {
                        return (int) (o1.getKey() - o2.getKey());
                    }
                });
        for (int[] point : points) {
            Long distance = Long.valueOf(point[0]* point[0] + point[1]* point[1]);
            priorityQueue.add(new Pair<Long, int[]>(distance, point));
        }
        for (int i = 0; i < K; i++) {
            res[i] = priorityQueue.poll().getValue();
        }
        return res;
    }

    //分治法： 题解中的“选择一个关键元素将数组分为两部分” 的操作不对，当输入两个相同的距离的点的时候，会出现错误。
    int[][] points;
    public int[][] kClosest2(int[][] points, int K) {
        this.points = points; work(0,points.length-1,K); return Arrays.copyOfRange(points,0,K);
    }
    public void work(int low, int high, int k){
        if(high <= low) return;
        //选择选择最左端元素，将数组分为两部分
        int pivot = distance(points[low]);
        int i = low; int j = high;
        while(i<j){
            while(i<j && distance(points[j]) >= pivot) j--;
            int[] mid = points[i]; points[i] = points[j]; points[j] = mid;
            while(i<j && distance(points[i]) <= pivot)  i++;
            mid = points[i]; points[i] = points[j]; points[j] = mid;
        }
        //分治的两种情况
        if(i-low+1>=k) work(low, i, k);
        else work(i+1, high,k-(i-low+1));
    }
    public int distance(int[] a){
        return a[0]*a[0] + a[1]*a[1];
    }
}
