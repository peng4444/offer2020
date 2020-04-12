package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Demo101
 * @Author: pbj
 * @Date: 2020/1/11 20:42
 * @Description: TODO 对称二叉树
 */
public class Demo101 {

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
     * 功能描述: 递归思想的一个简单应用，从以树的根节点的左右子节点为根开始进行深度优先搜索，依次判断两颗子树的左子树是否更与其右子树，
     * 右子树是否等于其左子树即可。如果采用迭代则只需使用层次遍历，判断每层元素是否满足镜像对称即可。
     * @param: [root]
     * @return: boolean
     * @auther: pbj
     * @date: 2020/1/11 20:47
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    /* *
     * 功能描述: 利用队列进行迭代
     * 队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像,每次提取两个结点并比较它们的值。
     * 然后，将两个结点的左右子结点按相反的顺序插入队列中。当队列为空时，或者我们检测到树不对称
     * （即从队列中取出两个不相等的连续结点）时，该算法结束。
     * @param: [root]
     * @return: boolean
     * @auther: pbj
     * @date: 2020/1/11 21:00
     */
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}
