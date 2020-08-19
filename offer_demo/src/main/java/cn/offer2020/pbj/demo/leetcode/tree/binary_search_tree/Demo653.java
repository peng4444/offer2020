package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

import java.util.*;

/**
 * @ClassName: Demo653
 * @Author: pbj
 * @Date: 2020/4/17 15:07
 * @Description: TODO 653.两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 */
public class Demo653 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            v = val;
        }
    }
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root,nums);
        int i = 0,j = nums.size()-1;
        while(i<j){
            int sum = nums.get(i)+nums.get(j);
            if(sum==k) return true;
            if(sum<k) i++;
            else j--;
        }
        return false;
    }
    private void inOrder(TreeNode root,List<Integer> nums){
        if(root==null) return;
        inOrder(root.left,nums);
        nums.add(root.val);
        inOrder(root.right,nums);
    }
    //遍历二叉树+HashSet
    public boolean findTarget1(TreeNode root, int k) {
        HashSet<Integer> hashset = new HashSet<Integer>();
        return preOrder(root,hashset,k);
    }
    public boolean preOrder(TreeNode root,HashSet<Integer> hashset,int k){
        if(root == null)
            return false;
        if(hashset.contains(k - root.val)){
            return true;
        }
        hashset.add(root.val);
        return preOrder(root.left,hashset,k) || preOrder(root.right,hashset,k);
    }

    Set<Integer> set = new HashSet<>();
    public boolean findTarget2(TreeNode root, int k) {
        if(root==null) return false;
        if(set.contains(root.val)){
            return true;
        }
        set.add(k - root.val);
        return findTarget2(root.left,k)||findTarget2(root.right,k);
    }

    public boolean findTarget3(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (set.contains(k - node.val)) {
                return true;
            }
            set.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return false;
    }
}
