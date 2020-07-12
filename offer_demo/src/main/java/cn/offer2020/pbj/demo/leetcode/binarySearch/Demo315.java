package cn.offer2020.pbj.demo.leetcode.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @pClassName: Demo315
 * @author: pengbingjiang
 * @create: 2020/7/11 15:41
 * @description: TODO 315.计算右侧小于当前元素的个数
 * [5种思路总结](https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/cong-you-wang-zuo-cha-ru-pai-xu-by-utopiahiker/)
 */
public class Demo315 {
    //二叉搜索树
    class Solution {
        public List<Integer> countSmaller(int[] nums) {
            //初始化
            Integer[] res = new Integer[nums.length];
            Arrays.fill(res, 0);
            List<Integer> list = new ArrayList<>();
            //反向构造二叉树，统计右边的较小数
            TreeNode root = null;
            for (int i = nums.length - 1; i >= 0; i--){
                root = addAndCount(root, new TreeNode(nums[i]), res, i);
            }
            return Arrays.asList(res);
        }

        public TreeNode addAndCount(TreeNode root, TreeNode node, Integer[] res, int i){
            if(root == null){
                root = node;
                return root;
            }
            //根节点的左边保存不大于根节点的元素
            if(root.val >= node.val){
                //统计左节点的元素个数
                root.count++;
                root.left = addAndCount(root.left, node, res, i);
            }else{
                //走到右边获取该元素左边的个数（根节点 1 + 左节点 root.count）
                res[i] += 1 + root.count;
                //统计右边是否还有更小的元素
                root.right = addAndCount(root.right, node, res, i);
            }
            return root;
        }
    }

    class TreeNode{
        int val;
        int count;
        TreeNode left, right;

        public TreeNode(int val){
            this.val = val;
            this.count = 0;
            left = null;
            right = null;
        }
    }

    //归并排序 + 索引数组
    private int[] index;
    private int[] aux;
    private int[] counter;

    public List<Integer> countSmaller3(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) return res;
        aux = new int[len];
        counter = new int[len];
        index = new int[len];
        for (int i = 0; i < len; i++) index[i] = i;
        //归并排序并统计
        mergeAndCount(nums, 0, len - 1);
        //遍历获取统计结果
        for (int i = 0; i < len; i++) {
            res.add(counter[i]);
        }
        return res;
    }
    //归并排序入口
    private void mergeAndCount(int[] nums, int l, int r) {
        if (l == r) return;
        int m = l + (r - l) / 2;
        mergeAndCount(nums, l, m);
        mergeAndCount(nums, m + 1, r);
        //检查已排序的部分
        if (nums[index[m]] > nums[index[m + 1]]) {
            sortAndCount(nums, l, m, r);
        }
    }
    //子数组排序并统计
    private void sortAndCount(int[] nums, int l, int m, int r) {
        for(int i = l; i <= r; i++) aux[i] = index[i];
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i > m) {
                index[k] = aux[j++];
            } else if (j > r) {
                index[k] = aux[i++];
                //排序的是索引数组，仍然可以通过索引找到原来数组中的元素，并更新统计值
                //右边先走完，那么右边的都是逆序
                counter[index[k]] += (r - m);
            } else if (nums[aux[i]] <= nums[aux[j]]) {
                index[k] = aux[i++];
                //插入左边的元素时，统计已经产生的逆序部分
                counter[index[k]] += (j - m - 1);
            } else {
                index[k] = aux[j++];
            }
        }
    }

    //插入排序 时间复杂度O(nlogn)
    public List<Integer> countSmaller2(int[] nums) {
        if(nums == null || nums.length == 0) return new LinkedList<>();
        //使用链表头插法
        LinkedList<Integer> res = new LinkedList<>();
        int len = nums.length;
        //反向插入排序
        for(int i = len - 2; i >= 0; i--){
            int j = i + 1, temp = nums[i];
            while(j < len && nums[j] >= temp){
                nums[j - 1] = nums[j];
                j++;
            }
            nums[j - 1] = temp;
            //len - j就表示计数个数
            res.addFirst(len - j);
        }
        //添加最后一个数
        res.add(0);
        //LinkedList也是List
        return res;
    }

    //二叉搜索
    public List<Integer> countSmaller1(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        Integer[] res = new Integer[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                list.add(nums[i]);
                res[i] = 0;
            } else {
                //search
                int ind = binaryS(list, nums[i]);
                list.add(ind, nums[i]);
                res[i] = ind;

            }
        }
        return Arrays.asList(res);

    }

    //边界二叉搜索
    public int binaryS(List<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums.get(mid) >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    //暴力求解-复杂度太大
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i<nums.length;i++){
            int count = 0;
            for(int j = i+1;j<nums.length;j++){
                if(nums[j]<nums[i]) count++;
            }
            ans.add(count);
        }
        return ans;
    }
}
