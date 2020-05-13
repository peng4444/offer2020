package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.*;

/**
 * @ClassName: Demo107
 * @Author: pbj
 * @Date: 2020/5/11 09:20
 * @Description: TODO
 */
public class Demo107 {

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    // 递归实现，在翻转链表
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null) return ans;
        helper(root, 0);
        Collections.reverse(ans);
        return ans;
    }
    private void helper(TreeNode node, Integer level) {
        if (ans.size() == level) {
            ans.add(new ArrayList<Integer>());
        }
        ans.get(level).add(node.val);
        if(node.left!=null) helper(node.left,level+1);
        if(node.right!=null) helper(node.right,level+1);
    }
    //迭代实现
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> oneLevel = new ArrayList<>();
            // 每次都取出一层的所有数据
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                oneLevel.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // 每次都往队头塞
            result.addFirst(oneLevel);
        }
        return result;
    }
}
