package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.*;

/**
 * @ClassName: Demo144
 * @Author: pbj
 * @Date: 2019/9/3 20:58
 * @Description: TODO  145.二叉树后序遍历
 */
public class Demo145 {
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
     * @date: 2019/12/18 16:41
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        helper(root,list);
        return list;
    }
    private void helper(TreeNode root,List<Integer> ans){
        if(root!=null){
            if(root.left!=null){
                helper(root.left,ans);
            }
            if(root.right!=null){
                helper(root.right,ans);
            }
            ans.add(root.val);
        }
    }
    /* *
     * 功能描述: 栈实现
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:41
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left != null) stack.push(node.left);//和传统先序遍历不一样，先将左结点入栈
            if(node.right != null) stack.push(node.right);//后将右结点入栈
            res.add(0,node.val);                        //逆序添加结点值
        }
        return res;
    }

    /* *
     * 功能描述: 队列实现
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:41
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) return list;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.right;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                cur = cur.left;
            }
        }
        Collections.reverse(list);
        return list;
    }
}
