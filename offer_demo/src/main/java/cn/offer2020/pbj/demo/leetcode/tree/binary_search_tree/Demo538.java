package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

import java.util.Stack;

/**
 * @ClassName: Demo538
 * @Author: pbj
 * @Date: 2020/4/17 14:19
 * @Description: TODO 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 */
public class Demo538 {

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //回溯
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
    //说到第就是从右到左的中序遍历
    int shareSum  = 0; // 共享单元
    public TreeNode convertBST2(TreeNode root) {
        if(root == null) return null;
        midOrder(root);
        return root;
    }
    public void midOrder(TreeNode root){
        if(root == null) return ;
        midOrder(root.right);
        root.val = root.val + shareSum;
        shareSum = root.val;
        midOrder(root.left);
    }

    //使用栈迭代
    public TreeNode convertBST1(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (!stack.isEmpty() || node != null) {
            /* push all nodes up to (and including) this subtree's maximum on
             * the stack. */
            while (node != null) {
                stack.add(node);
                node = node.right;
            }

            node = stack.pop();
            sum += node.val;
            node.val = sum;

            /* all nodes with values between the current and its parent lie in
             * the left subtree. */
            node = node.left;
        }

        return root;
    }
}
