package cn.offer2020.pbj.demo.leetcode.a_tree;

import java.util.*;

/**
 * @ClassName: Demo94
 * @Author: pbj
 * @Date: 2019/9/3 20:58
 * @Description: TODO  二叉树中序遍历
 */
public class Demo94 {

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
     * 功能描述: 递归实现
     * 时间复杂度O(N)
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:19
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        helper(root, ans);
        return ans;
    }
    public void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left,res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right,res);
            }
        }
    }

    /* *
     * 功能描述: 栈实现
     * 时间复杂度O(N)
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:23
     */
    public List<Integer> inorderTreversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            ans.add(curr.val);
            curr = curr.right;
        }
        return ans;

    }

    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        Deque<Guide> path = new ArrayDeque();
        path.addFirst(new Guide(0,root));

        while (!path.isEmpty()) {
            Guide current = path.removeFirst();
            if (current.node == null) {
                continue;
            } else if (current.ope == 1) {
                result.add(current.node.val);
            } else {
                path.addFirst(new Guide(0, current.node.right));
                path.addFirst(new Guide(1,current.node));
                path.addFirst(new Guide((0), current.node.left));
            }
        }
        return result;
    }
    private class Guide {
        int ope;//0:visit 1:print
        TreeNode node;
        public Guide(int ope, TreeNode node) {
            this.ope = ope;
            this.node = node;
        }
    }
}
