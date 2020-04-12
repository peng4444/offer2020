package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: Demo102
 * @Author: pbj
 * @Date: 2019/12/20 16:59
 * @Description: TODO 二叉树的层次遍历
 */
public class Demo102 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: 二叉树广度优先搜索和二叉树深度优先搜索
     * @param: [root]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     * @auther: pbj
     * @date: 2019/12/20 17:01
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();//创建结果集合
        if (root == null) return ans;//根节点为空，返回空
        Queue<TreeNode> queue = new LinkedList<>();//创建队列
        queue.add(root);//将头节点加入到队列
        while (!queue.isEmpty()) {//如果队列不为空
            int levelSize = queue.size();//获取队列的长度
            List<Integer> currLevel = new ArrayList<>();//创建接收层的集合
            for (int i = 0; i < levelSize; i++) {//遍历已经存入队列的
                TreeNode currNode = queue.poll();//从队列中取出一个
                currLevel.add(currNode.val);//将取出的值加入层集合
                if (currNode.left != null) {//如果取出的节点有左孩子
                    queue.add(currNode.left);//将其加入到队列
                }
                if (currNode.right != null) {//如果取出的节点有右孩子
                    queue.add(currNode.right);//将其加入到队列
                }

            }
            ans.add(currLevel);//将当前层的节点加入到结果集

        }
        return ans;
    }

    /* *
     * 功能描述: 递归实现
     * @param: [root]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     * @auther: pbj
     * @date: 2019/12/20 17:28
     */
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null) return ans;
        helper(root, 0);
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
}
