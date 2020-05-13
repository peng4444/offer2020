package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo993
 * @Author: pbj
 * @Date: 2020/5/11 09:45
 * @Description: TODO 993. 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 */
public class Demo993 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if(root.val==x||root.val==y) return false;
        if(nodeLevel(root,x)==nodeLevel(root,y)&&parentNode(root,x)!=parentNode(root,y)){
            return true;
        }else{
            return false;
        }
    }
    //返回节点的深度
    int level = 1;
    public int nodeLevel(TreeNode Node,int x){
        if(Node.val==x){
            return level;
        }
        if(Node.left!=null) nodeLevel(Node.left,level+1);
        if(Node.right!=null)    nodeLevel(Node.right,level+1);
        return level;
    }
    //返回节点的父节点
    public TreeNode parentNode(TreeNode Node,int x){
        if(Node.left!=null&&Node.left.val==x){
            return Node;
        }
        if(Node.right!=null&&Node.right.val==x){
            return Node;
        }
        parentNode(Node.left,x);
        parentNode(Node.right,x);
        return null;
    }

    Map<Integer, Integer> depth;
    Map<Integer, TreeNode> parent;
    public boolean isCousins1(TreeNode root, int x, int y) {
        depth = new HashMap();
        parent = new HashMap();
        dfs(root, null);
        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    Map<Integer, Integer> kmap = new HashMap<Integer, Integer>();
    Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
    public boolean isCousins2(TreeNode root, int x, int y) {
        dfs(root, 0 , null);
        if (kmap.get(x) == kmap.get(y) && map.get(x).val != map.get(y).val) {
            return true;
        }
        return false;
    }

    public void dfs(TreeNode root, int k, TreeNode node) {
        if (root != null) {
            kmap.put(root.val, k);
            map.put(root.val, node);
            dfs(root.left, k + 1, root);
            dfs(root.right, k + 1, root);
        }
    }
}
