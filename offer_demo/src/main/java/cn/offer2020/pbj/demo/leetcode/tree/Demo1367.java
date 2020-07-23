package cn.offer2020.pbj.demo.leetcode.tree;


/**
 * @pClassName: Demo1367
 * @author: pengbingjiang
 * @create: 2020/7/23 11:28
 * @description: TODO 1367.二叉树中的列表
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 */
public class Demo1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(root==null){
            return false;
        }
        return help(head,root)||isSubPath(head,root.left)||isSubPath(head,root.right);
    }

    private boolean help(ListNode head,TreeNode root){
        if(head==null) return true;
        if(root==null) return false;
        if(root.val==head.val){
            return help(head.next,root.left)||help(head.next,root.right);
        }
        return false;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //面试题 04.10. 检查子树
    //检查子树。你有两棵非常大的二叉树T1，有几万个节点；T2，有几万个节点。设计一个算法，判断T2是否为T1的子树。
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if(t2==null) return true;
        else if(t1==null) return false;
        if(t1.val!=t2.val){
            return checkSubTree(t1.left,t2)||checkSubTree(t1.right,t2);
        }else{
            return checkSubTree(t1.left,t2.left)&&checkSubTree(t1.right,t2.right);
        }
    }
}
