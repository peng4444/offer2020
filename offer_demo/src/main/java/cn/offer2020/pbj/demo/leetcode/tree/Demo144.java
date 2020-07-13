package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.*;

/**
 * @ClassName: Demo144
 * @Author: pbj
 * @Date: 2019/9/3 20:58
 * @Description: TODO   144.二叉树前序遍历
 */
public class Demo144 {
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
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:39
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root,list);
        return list;
    }
    private void helper(TreeNode root,List<Integer> ans){
        if(root!=null){
            ans.add(root.val);
            if(root.left!=null){
                helper(root.left,ans);
            }
            if(root.right!=null){
                helper(root.right,ans);
            }
        }
    }
    /* *
     * 功能描述: 栈实现
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:40
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(Integer.valueOf(node.val));
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

    /* *
     * 功能描述: 队列实现
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:40
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // 判断非空
        if(null == root){
            return res;
        }
        // 使用双端队列
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.removeLast();
            res.add(node.val);
            // 要先加右孩子、再加左孩子
            if(null != node.right){
                stack.add(node.right);
            }
            if(null != node.left){
                stack.add(node.left);
            }
        }
        return res;
    }
}
