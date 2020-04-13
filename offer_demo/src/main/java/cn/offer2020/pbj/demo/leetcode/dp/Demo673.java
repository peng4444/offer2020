package cn.offer2020.pbj.demo.leetcode.dp;

import java.util.Arrays;

/**
 * @ClassName: Demo673
 * @Author: pbj
 * @Date: 2020/4/12 15:25
 * @Description: TODO
 */
public class Demo673 {
    //dp
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            int N = nums.length;
            if (N <= 1) return N;
            int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
            int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
            Arrays.fill(counts, 1);

            for (int j = 0; j < N; ++j) {
                for (int i = 0; i < j; ++i) if (nums[i] < nums[j]) {
                    if (lengths[i] >= lengths[j]) {
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[i] + 1 == lengths[j]) {
                        counts[j] += counts[i];
                    }
                }
            }

            int longest = 0, ans = 0;
            for (int length: lengths) {
                longest = Math.max(longest, length);
            }
            for (int i = 0; i < N; ++i) {
                if (lengths[i] == longest) {
                    ans += counts[i];
                }
            }
            return ans;
        }
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
