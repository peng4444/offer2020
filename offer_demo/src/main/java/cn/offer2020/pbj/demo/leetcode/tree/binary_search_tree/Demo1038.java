package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

/**
 * @ClassName: Demo1038
 * @Author: pbj
 * @Date: 2020/5/18 11:06
 * @Description: TODO 1038. 从二叉搜索树到更大和树
 * 给出二叉搜索树的根节点，该二叉树的节点值各不相同，修改二叉树，使每个节点node的新值等于原树中大于或等于node.val的值之和。
 */
public class Demo1038 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            v = val;
        }
    }

    private int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        traver(root);
        return root;
    }
    private void traver(TreeNode root){
        if(root==null) return;
        traver(root.right);
        sum = sum + root.val;
        root.val = sum;
        traver(root.left);
    }
}
