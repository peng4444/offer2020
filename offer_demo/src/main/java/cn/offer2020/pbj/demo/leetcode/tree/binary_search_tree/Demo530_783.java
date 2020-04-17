package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

/**
 * @ClassName: Demo530_783
 * @Author: pbj
 * @Date: 2020/4/17 15:16
 * @Description: TODO [530. 二叉搜索树的最小绝对差](https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/)
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 */
public class Demo530_783 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            v = val;
        }
    }

    private  int minDiff = Integer.MAX_VALUE;
    private TreeNode preNode = null;
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return minDiff;
    }
    private void inOrder(TreeNode root){
        if(root==null) return;
        inOrder(root.left);
        if(preNode!=null) minDiff = Math.min(minDiff,root.val-preNode.val);
        preNode = root;
        inOrder(root.right);
    }

}
