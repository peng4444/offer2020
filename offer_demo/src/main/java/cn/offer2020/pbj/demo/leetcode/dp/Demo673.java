package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @ClassName: Demo673
 * @Author: pbj
 * @Date: 2020/4/12 15:25
 * @Description: TODO 673.最长递增子序列的个数
 */
public class Demo673 {
    //dp
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // dp[i]表示以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[n];
        // counter[i]表示以nums[i]结尾的最长递增子序列的个数
        int[] counter = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(counter, 1);
        int len = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) { // 所有以i结尾的子序列中出现了counter[j]个更长的序列
                        counter[i] = counter[j];
                        dp[i] = dp[j] + 1;
                    } else if (dp[j] + 1 == dp[i]) { // 出现了长度相同的序列，累加数量
                        counter[i] += counter[j];
                    }
                }
            }
            len = Math.max(len, dp[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == len) ans += counter[i];
        }
        return ans;
    }

    //线段树
    class Solution1 {
        public Value merge(Value v1, Value v2) {
            if (v1.length == v2.length) {
                if (v1.length == 0) return new Value(0, 1);
                return new Value(v1.length, v1.count + v2.count);
            }
            return v1.length > v2.length ? v1 : v2;
        }

        public void insert(Node node, int key, Value val) {
            if (node.range_left == node.range_right) {
                node.val = merge(val, node.val);
                return;
            } else if (key <= node.getRangeMid()) {
                insert(node.getLeft(), key, val);
            } else {
                insert(node.getRight(), key, val);
            }
            node.val = merge(node.getLeft().val, node.getRight().val);
        }

        public Value query(Node node, int key) {
            if (node.range_right <= key) return node.val;
            else if (node.range_left > key) return new Value(0, 1);
            else return merge(query(node.getLeft(), key), query(node.getRight(), key));
        }

        public int findNumberOfLIS(int[] nums) {
            if (nums.length == 0) return 0;
            int min = nums[0], max = nums[0];
            for (int num: nums) {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            Node root = new Node(min, max);
            for (int num: nums) {
                Value v = query(root, num-1);
                insert(root, num, new Value(v.length + 1, v.count));
            }
            return root.val.count;
        }
    }

    class Node {
        int range_left, range_right;
        Node left, right;
        Value val;
        public Node(int start, int end) {
            range_left = start;
            range_right = end;
            left = null;
            right = null;
            val = new Value(0, 1);
        }
        public int getRangeMid() {
            return range_left + (range_right - range_left) / 2;
        }
        public Node getLeft() {
            if (left == null) left = new Node(range_left, getRangeMid());
            return left;
        }
        public Node getRight() {
            if (right == null) right = new Node(getRangeMid() + 1, range_right);
            return right;
        }
    }

    class Value {
        int length;
        int count;
        public Value(int len, int ct) {
            length = len;
            count = ct;
        }
    }
}
