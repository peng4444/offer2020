package cn.offer2020.pbj.demo.leetcode.tree;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * @ClassName: Demo404
 * @Author: pbj
 * @Date: 2020/4/16 12:09
 * @Description: TODO 404. 左叶子之和
 */
public class Demo404 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null) return 0;
        if(isLeft(root.left)) return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }
    public boolean isLeft(TreeNode node){
        if(node==null) return false;
        return node.left==null&&node.right==null;
    }

    //迭代
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Pair<TreeNode, Boolean>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, false));

        int sum = 0;
        Boolean flag;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Boolean> pair = stack.pop();
            root = pair.getKey();
            flag = pair.getValue();
            if (flag && root.left == null && root.right == null) {
                sum += root.val;
            }
            if (root.left != null) {
                stack.push(new Pair<>(root.left, true));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, false));
            }
        }
        return sum;
    }
}
