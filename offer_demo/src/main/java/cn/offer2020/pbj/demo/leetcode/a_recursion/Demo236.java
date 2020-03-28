package cn.offer2020.pbj.demo.leetcode.a_recursion;

/**
 * @ClassName: Demo236
 * @Author: pbj
 * @Date: 2019/12/18 16:55
 * @Description: TODO 二叉树的最近公共祖先
 */
public class Demo236 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            v = val;
        }
    }

    /* *
     * 功能描述: 二叉树节点的公共祖先
     * @param: [root, p, q]
     * @return: cn.offer2020.pbj.demo.leetcode.a_recursion.Demo236.TreeNode
     * @auther: pbj
     * @date: 2020/1/3 11:10
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||p==root||q==root) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null&&right!=null){
            return root;
        }else if(left!=null){
            return left;
        }else if(right!=null){
            return right;
        }
        return null;
    }
}
