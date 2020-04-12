package cn.offer2020.pbj.demo.leetcode.link;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName: Demo144
 * @Author: pbj
 * @Date: 2019/9/3 20:58
 * @Description: TODO   二叉树后序遍历
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

    /* *
     * 功能描述: 队列实现
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/18 16:41
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<Guide> path = new ArrayDeque<>();
        path.addFirst(new Guide(0,root));

        while (!path.isEmpty()) {
            Guide current = path.removeFirst();
            if (current.node == null) {
                continue;
            } else if (current.ope == 1) {
                result.add(current.node.val);
            } else {
                path.addFirst(new Guide(1,current.node));
                path.addFirst(new Guide(0, current.node.right));
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
