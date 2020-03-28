package cn.offer2020.pbj.demo.leetcode.a_tree;

import java.util.LinkedList;

/**
 * @ClassName: Demo112
 * @Author: pbj
 * @Date: 2020/1/12 15:26
 * @Description: TODO 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 */
public class Demo112 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    //递归实现，时间复杂度O(N)
    public boolean hasPathSum(TreeNode root,int sum) {
        if(root==null) return false;
        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }

    //迭代实现
    class Solution {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null)
                return false;

            LinkedList<TreeNode> node_stack = new LinkedList();
            LinkedList<Integer> sum_stack = new LinkedList();
            node_stack.add(root);
            sum_stack.add(sum - root.val);

            TreeNode node;
            int curr_sum;
            while ( !node_stack.isEmpty() ) {
                node = node_stack.pollLast();
                curr_sum = sum_stack.pollLast();
                if ((node.right == null) && (node.left == null) && (curr_sum == 0))
                    return true;

                if (node.right != null) {
                    node_stack.add(node.right);
                    sum_stack.add(curr_sum - node.right.val);
                }
                if (node.left != null) {
                    node_stack.add(node.left);
                    sum_stack.add(curr_sum - node.left.val);
                }
            }
            return false;
        }
    }
}
