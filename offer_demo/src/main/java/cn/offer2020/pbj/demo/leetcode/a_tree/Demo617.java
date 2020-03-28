package cn.offer2020.pbj.demo.leetcode.a_tree;

/**
 * @ClassName: Demo617
 * @Author: pbj
 * @Date: 2020/1/11 21:53
 * @Description: TODO 合并二叉树
 */
public class Demo617 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        t1.val = t1.val + t2.val;
        return t1;
    }

    /**
     * 不修改原二叉树的解法
     */
    public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        // 先合并根节点
        TreeNode root = new TreeNode((t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));
        // 再递归合并左右子树
        root.left = mergeTrees2(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        root.right = mergeTrees2(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
        return root;
    }
}
