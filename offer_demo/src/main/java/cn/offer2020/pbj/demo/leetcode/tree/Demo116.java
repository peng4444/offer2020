package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo116
 * @Author: pbj
 * @Date: 2020/5/9 12:01
 * @Description: TODO 116. 填充每个节点的下一个右侧节点指针
 *
 */
public class Demo116 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if(root==null) return null;
        dfs(root.left,root.right);
        return root;
    }
    public void dfs(Node left,Node right){
        if(left==null||right==null) return;
        left.next = right;
        dfs(left.left,left.right);
        dfs(right.left,right.right);
        dfs(left.right,right.left);
    }
    //使用已建立的 next 指针（递归法）
    public Node connect1(Node root) {
        if (root == null) {
            return root;
        }
        if(root.left != null)
            root.left.next = root.right;
        if(root.right != null)
            root.right.next = root.next != null ? root.next.left : null;
        connect(root.left);
        connect(root.right);
        return root;
    }
}
