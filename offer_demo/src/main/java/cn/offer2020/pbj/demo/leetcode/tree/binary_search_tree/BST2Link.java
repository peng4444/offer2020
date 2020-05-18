package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

/**
 * @ClassName: BST2Link
 * @Author: pbj
 * @Date: 2020/5/18 11:19
 * @Description: TODO
 * 实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 */
public class BST2Link {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            v = val;
        }
    }

    private TreeNode pre;
    private TreeNode head;
    public TreeNode convertBiNode(TreeNode root) {
        infixOrder(root);
        return head;
    }
    private void infixOrder(TreeNode node){
        if(node == null){
            return;
        }
        infixOrder(node.left);
        if(pre!=null){
            node.left = null;
            pre.right = node;
        }else{
            head = node;
        }
        pre = node;
        infixOrder(node.right);
    }

    public TreeNode convertBiNode1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode subRoot = convertBiNode(root.left);
        if (subRoot == null) {
            subRoot = root;
        } else {
            TreeNode subRootTmp = subRoot;
            while (subRoot.right != null) {
                subRoot = subRoot.right;
            }
            subRoot.right = root;
            subRoot = subRootTmp;
        }
        root.left = null;
        root.right = convertBiNode(root.right);
        return subRoot;
    }
}
