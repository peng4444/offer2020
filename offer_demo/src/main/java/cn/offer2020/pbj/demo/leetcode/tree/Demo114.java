package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.Stack;

/**
 * @ClassName: Demo114
 * @Author: pbj
 * @Date: 2020/5/22 11:52
 * @Description: TODO 114.二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 */
public class Demo114 {
    //递归
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while(root.right != null) root = root.right;
        root.right = tmp;
    }
    //栈
    public void flatten1(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty()){
                TreeNode node = stack.pop();
                TreeNode tmp = node.right;
                node.right = node.left;
                node.left = null;

                while(node.right != null) node = node.right;
                node.right = tmp;
                root = tmp;
            }
        }
    }
}
