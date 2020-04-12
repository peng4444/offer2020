package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.Objects;
import java.util.Stack;

/**
 * @ClassName: Demo54
 * @Author: pbj
 * @Date: 2020/3/11 15:57
 * @Description: TODO 面试题54. 二叉搜索树的第k大节点
 */
public class Demo54 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        private int ans = 0, count = 0;
        public int kthLargest(TreeNode root, int k) {
            // clarification:  root == null?   k <= 1?
            helper(root, k);
            return ans;
        }

        private void helper(TreeNode root, int k) {
            if (root.right != null) helper(root.right, k);
            if (++count == k) {
                ans = root.val;
                return;
            }
            if (root.left != null) helper(root.left, k);
        }
    }
    //递归实现
    int count,result = -1;
    public int KthLargest(TreeNode root,int K){
        count = K;
        KthLargest(root);
        return result;
    }

    public void KthLargest(TreeNode root){
        if(Objects.nonNull(root)){
            KthLargest(root.right);
            if (count == 1) {
                result = root.val;
                count--;
                return;
            }
            count--;
            KthLargest(root.left);
        }
    }

    //迭代
    public int KthLargest2(TreeNode root, int K) {
        int count = 1;
        Stack<TreeNode> stack = new Stack<>();
        while (Objects.nonNull(root) || !stack.isEmpty()) {
            while (Objects.nonNull(root)) {
                stack.push(root);
                root = root.right;
            }
            TreeNode pop = stack.pop();
            if (count == K) {
                return pop.val;
            }
            count++;
            root = pop.left;
        }
        return 0;
    }
}
