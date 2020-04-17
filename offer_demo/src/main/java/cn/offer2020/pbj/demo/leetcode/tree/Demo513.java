package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Demo513
 * @Author: pbj
 * @Date: 2020/4/17 10:27
 * @Description: TODO 513. 找树左下角的值
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 */
public class Demo513 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //广度优先遍历BFS：按照从右往左的层序遍历，最后一个就是结果
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            if(root.right!=null) queue.add(root.right);
            if(root.left!=null) queue.add(root.left);
        }
        return root.val;
    }

    //深度优先遍历DFS：记录深度最大的第一个节点
    int[] result = new int[]{0,-1};
    public int findBottomLeftValue1(TreeNode root) {
        dfs(root,0);
        return result[0];
    }
    public void dfs(TreeNode root,int depth){
        if(root==null) return ;
        if(depth>result[1]){
            result[0] = root.val;
            result[1] = depth;
        }
        dfs(root.left,depth+1);
        dfs(root.right,depth+1);
    }
}
