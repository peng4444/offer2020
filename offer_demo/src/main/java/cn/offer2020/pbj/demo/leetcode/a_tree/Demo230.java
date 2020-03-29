package cn.offer2020.pbj.demo.leetcode.a_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;
/* *
 * 功能描述: 230. 二叉搜索树中第K小的元素 ,  面试题54. 二叉搜索树的第k大节点
 * @param:
 * @return:
 * @auther: pbj
 * @date: 2020/3/29 11:17
 */

public class Demo230 {

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    //230. 二叉搜索树中第K小的元素
    public int kthSmallest(TreeNode root, int k) {
        int count = 1;
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty()||root!=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(count==k){
                return root.val;
            }
            count++;
            root = root.right;
        }
        return 0;
    }

    //递归
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    public int kthSmallest1(TreeNode root, int k) {
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());
        return nums.get(k - 1);
    }

    //迭代
    public int kthSmallest2(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) return root.val;
            root = root.right;
        }
    }



    /**面试题54. 二叉搜索树的第k大节点
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int kthLargest(TreeNode root, int k) {
            int count = 1;
            Stack<TreeNode> stack = new Stack<>();
            while(Objects.nonNull(root)||!stack.isEmpty()){
                while(Objects.nonNull(root)){
                    stack.push(root);
                    root = root.right;
                }
                TreeNode pop = stack.pop();
                if(count==k){
                    return pop.val;
                }
                count++;
                root = pop.left;
            }
            return 0;
        }
    }
}
