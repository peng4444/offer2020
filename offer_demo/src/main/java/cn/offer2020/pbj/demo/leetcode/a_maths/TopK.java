package cn.offer2020.pbj.demo.leetcode.a_maths;

import java.util.*;

/**
 * @ClassName: TopK
 * @Author: pbj
 * @Date: 2020/3/27 17:30
 * @Description: TODO
 */
public class TopK {

    //TopK
    // 维护一个 PriorityQueue，以返回前K大的数
    public int[] topk(int[] nums, int k) {
        int[] result = new int[k];
        if (nums == null || nums.length < k) {
            return result;
        }

        Queue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) {
                // poll() 方法用于检索或获取和删除队列的第一个元素或队列头部的元素
                pq.poll();
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }

        return result;
    }

    //前K大的数II
    public class Solution {
        private int maxSize;
        private Queue<Integer> minheap;
        public Solution(int k) {
            minheap = new PriorityQueue<>();
            maxSize = k;
        }

        public void add(int num) {
            if (minheap.size() < maxSize) {
                // add(E e)和offer(E e)的语义相同，都是向优先队列中插入元素
                // 只是Queue接口规定二者对插入失败时的处理不同
                // 前者在插入失败时抛出异常，后则则会返回false
                minheap.offer(num);
                return;
            }

            if (num > minheap.peek()) {
                minheap.poll();
                minheap.offer(num);
            }
        }

        public List<Integer> topk() {
            // 将队列中的数存到数组中
            Iterator it = minheap.iterator();
            List<Integer> result = new ArrayList<Integer>();
            while (it.hasNext()) {
                result.add((Integer) it.next());
            }
            // 调用数组排序法后返回
            Collections.sort(result, Collections.reverseOrder());
            return result;
        }
    }

    //数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> que = new PriorityQueue<>();
        for(int num : nums) {
            if(que.size() < k) {
                que.offer(num);
            } else {
                if(que.peek() < num) {
                    que.poll();
                    que.offer(num);
                }
            }
        }
        return que.peek();
    }

    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    private int partition(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }

        int left = start, right = end;
        int pivot = nums[(start + end) / 2];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        if (k <= right) {
            return partition(nums, start, right, k);
        }
        if (k >= left) {
            return partition(nums, left, end, k);
        }
        return nums[k];
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
