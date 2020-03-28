package cn.offer2020.pbj.demo.leetcode.a_tree;

/**
 * @ClassName: Demo666
 * @Author: pbj
 * @Date: 2020/3/12 14:03
 * @Description: TODO
 */
public class Demo666 {

    //定义一个二叉树
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private static int sum;
    public static int sumNumbers(TreeNode root) {
        sum = 0;
        childSum(0, root);
        return sum;
    }
    public static void  childSum(int val, TreeNode root) {
        if(root == null) return;
        int k = (val  + root.val) ;
        if(root.left == null && root.right == null) {
            sum += k;
        }
        childSum(k, root.left);
        childSum(k, root.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(sumNumbers(node));
    }
}
