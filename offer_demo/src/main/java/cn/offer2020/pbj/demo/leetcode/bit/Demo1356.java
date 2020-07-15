package cn.offer2020.pbj.demo.leetcode.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @pClassName: Demo1356
 * @author: pengbingjiang
 * @create: 2020/7/15 16:56
 * @description: TODO 1356. 根据数字二进制下 1 的数目排序
 * 给你一个整数数组arr。请你将数组中的元素按照其二进制表示中数字1的数目升序排序。
 */
public class Demo1356 {
    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for(int num:arr){
            list.add(num);
        }
        list.sort((o1,o2)->{
            int c1 = Integer.bitCount(o1);
            int c2 = Integer.bitCount(o2);
            return c1!=c2?c1-c2:o1-o2;
        });
        int[] res = new int[arr.length];
        int index = 0;
        for(Integer num:list){
            res[index++] = num;
        }
        return res;
    }

    public int[] sortByBits2(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            Integer c1 = Integer.bitCount(o1);
            Integer c2 = Integer.bitCount(o2);
            return c1 == c2 ? o1 - o2 : c1 - c2;
        });
        for (int num : arr) {
            queue.offer(num);
        }
        for (int i = 0; i < arr.length && !queue.isEmpty(); i++) {
            arr[i] = queue.poll();
        }
        return arr;
    }

    public int[] sortByBits1(int[] arr) {
        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        Arrays.sort(map);
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i] % 10000000;
        }
        return map;
    }
}
