package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo100
 * @Author: pbj
 * @Date: 2020/5/11 09:03
 * @Description: TODO 100. 相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class Demo100 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //自己写的
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p!=null&&q!=null){
            if(p.val==q.val){
                return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
            }else{
                return false;
            }
        }if(p==null&&q==null){
            return true;
        }else{
            return false;
        }
    }
}
