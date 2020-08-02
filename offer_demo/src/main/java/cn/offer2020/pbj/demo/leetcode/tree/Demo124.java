package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo124
 * @Author: pbj
 * @Date: 2020/1/11 21:14
 * @Description: TODO 124.二叉树中的最大路径和
 */
public class Demo124 {

    private int ret = Integer.MIN_VALUE;

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public int maxPathSum(TreeNode root) {
        /**
         对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
         1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
         2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
         **/
        getMax(root);
        return ret;
    }

    public int getMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, getMax(root.left));// 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(root.right));
        ret = Math.max(ret, root.val + left + right);// 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        return Math.max(left, right) + root.val;
    }
}
