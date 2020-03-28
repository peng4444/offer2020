package cn.offer2020.pbj.demo.leetcode.a_tree;

/**
 * @ClassName: Demo129
 * @Author: pbj
 * @Date: 2020/3/12 11:28
 * @Description: TODO 129. 求根到叶子节点数字之和
 */
public class Demo129 {

    //定义一个二叉树
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public static int sumNumbers(TreeNode root){
        return getSum(root,0);
    }

    public static int getSum(TreeNode root,int val) {
        if(root==null){
            return 0;
        }
        int temp = val*10+root.val;
        if(root.right==null&&root.left==null){
            return temp;
        }
        return getSum(root.left,temp)+getSum(root.right,temp);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        System.out.println(sumNumbers(node));;
    }

    static int sum;
    public int sumNumbers2(TreeNode root) {
        sum = 0;
        childSum(0, root);
        return sum;
    }
    public static void  childSum(int val, TreeNode root) {
        if(root == null) return;
        int k = (val * 10 + root.val) ;
        if(root.left == null && root.right == null) {
            sum += k;
        }
        childSum(k, root.left);
        childSum(k, root.right);
    }
}
