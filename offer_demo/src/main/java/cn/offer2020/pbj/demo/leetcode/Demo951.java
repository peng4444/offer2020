package cn.offer2020.pbj.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo951
 * @Author: pbj
 * @Date: 2020/5/5 10:57
 * @Description: TODO 951. 翻转等价二叉树
 * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
 * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。
 */
public class Demo951 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //递归
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1==null&&root2==null) return true;
        if(root1==null||root2==null) return false;
        if(root1.val==root2.val){
            return (flipEquiv(root1.left,root2.left)) && flipEquiv(root1.right,root2.right)             ||  (flipEquiv(root1.left,root2.right) && flipEquiv(root1.right,root2.left));
        }
        return false;
    }

    // 标准态遍历
    public boolean flipEquiv1(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList();
        List<Integer> vals2 = new ArrayList();
        dfs(root1, vals1);
        dfs(root2, vals2);
        return vals1.equals(vals2);
    }

    public void dfs(TreeNode node, List<Integer> vals) {
        if (node != null) {
            vals.add(node.val);
            int L = node.left != null ? node.left.val : -1;
            int R = node.right != null ? node.right.val : -1;

            if (L < R) {
                dfs(node.left, vals);
                dfs(node.right, vals);
            } else {
                dfs(node.right, vals);
                dfs(node.left, vals);
            }

            vals.add(null);
        }
    }
}
