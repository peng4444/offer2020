package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo235
 * @Author: pbj
 * @Date: 2019/12/18 17:01
 * @Description: TODO 二叉搜索树的最近公共祖先
 */
public class Demo235 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            v = val;
        }
    }

    /* *
     * 功能描述: 递归实现
     * @param: [root, p, q]
     * @return: cn.offer2020.pbj.demo.leetcode.tree.Demo235.TreeNode
     * @auther: pbj
     * @date: 2019/12/18 17:21
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > q.val && root.val > p.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < q.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    /* *
     * 功能描述: 上面的递归迭代实现
     * @param: [root, p, q]
     * @return: cn.offer2020.pbj.demo.leetcode.tree.Demo235.TreeNode
     * @auther: pbj
     * @date: 2019/12/18 17:22
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }
}
