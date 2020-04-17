package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

/**
 * @ClassName: Demo669
 * @Author: pbj
 * @Date: 2020/4/17 14:11
 * @Description: TODO 669. 修剪二叉搜索树
 */
public class Demo669 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    /* *
     * 功能描述: 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
     * 你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
     * @param: [root, L, R]
     * @return: cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree.Demo669.TreeNode
     * @auther: pbj
     * @date: 2020/4/17 14:11
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root==null) return null;
        if(root.val>R) return trimBST(root.left,L,R);
        if(root.val<L) return trimBST(root.right,L,R);
        root.left = trimBST(root.left,L,R);
        root.right = trimBST(root.right,L,R);
        return root;
    }
}
