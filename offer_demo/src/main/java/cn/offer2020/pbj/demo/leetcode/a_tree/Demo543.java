package cn.offer2020.pbj.demo.leetcode.a_tree;

/**
 * @ClassName: Demo543
 * @Author: pbj
 * @Date: 2020/1/12 15:07
 * @Description: TODO 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 */
public class Demo543 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private int len = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        treeLen(root);
        return len;
    }

    private int treeLen(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLen = treeLen(root.left);
        int rightLen = treeLen(root.right);
        len = Math.max(len, leftLen + rightLen);
        return Math.max(leftLen, rightLen)+1;
    }
}
