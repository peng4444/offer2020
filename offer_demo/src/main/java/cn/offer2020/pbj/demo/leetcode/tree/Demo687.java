package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo687
 * @Author: pbj
 * @Date: 2020/1/11 21:29
 * @Description: TODO 最长同值路径
 */
public class Demo687 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        getEquals(root);
        return res;
    }

    public int getEquals(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getEquals(root.left);
        int right = getEquals(root.right);
        int arrowLeft =0,arrowRight = 0;
        if (root.left != null && root.left.val== root.val) {
            arrowLeft +=left+1;
        }
        if (root.right != null && root.right.val== root.val) {
            arrowRight +=right+1;
        }
        res = Math.max(res, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
