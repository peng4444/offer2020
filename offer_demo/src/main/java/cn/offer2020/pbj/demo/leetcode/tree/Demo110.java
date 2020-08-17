package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo110
 * @Author: pbj
 * @Date: 2020/1/12 14:53
 * @Description: TODO  110.平衡二叉树 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 */
public class Demo110 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private boolean res = true;
    /* *
     * 功能描述: 自底向上  时间复杂度为O(n)
     * @param: [root]
     * @return: boolean
     * @auther: pbj
     * @date: 2020/1/12 15:03
     */
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return  res;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (Math.abs(left - right) > 1) {
            res = false;
        }
        return 1 + Math.max(left, right);
    }

    /**
     * 解法一：递归判断左右子树的高度差是否大于 1
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左右子树的高度差的绝对值超过 1
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }
        // 递归判断左子树
        if (!isBalanced2(root.left)) {
            return false;
        }
        // 再递归判断右子树
        return isBalanced2(root.right);
    }

    // 求节点的高度
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = height(root.left);
            int right = height(root.right);
            return Math.max(left,right) + 1;
        }
    }

    //从顶至底（暴力法）
    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;
            return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }

        private int depth(TreeNode root) {
            if (root == null) return 0;
            return Math.max(depth(root.left), depth(root.right)) + 1;
        }
    }
}
