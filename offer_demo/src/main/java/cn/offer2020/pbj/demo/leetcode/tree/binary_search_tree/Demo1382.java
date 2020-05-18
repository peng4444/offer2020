package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo1382
 * @Author: pbj
 * @Date: 2020/5/18 10:41
 * @Description: TODO 1382. 将二叉搜索树变平衡
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 */
public class Demo1382 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            v = val;
        }
    }

    public TreeNode balabceBST(TreeNode root) {
        List<Integer> buffer = new ArrayList<>();
        //1.把二叉搜索树变成有序数组
        search(root,buffer);
        //2.把有序数组变成二叉平衡树
        build(buffer, 0, buffer.size() - 1);
        return root;
    }

    //把二叉搜索树变成有序数组
    private void search(TreeNode root, List<Integer> buffer) {
        if(root==null) return;
        search(root.left,buffer);
        buffer.add(root.val);
        search(root.right, buffer);
    }

    //将有序集合转换为二叉搜索树
    public TreeNode build(List<Integer> buffer, int i, int j) {
        if(i>j) return null;
        if(i==j) return new TreeNode(buffer.get(i));
        int mid = i + (j - i) / 2;
        TreeNode root = new TreeNode(buffer.get(mid));
        root.left = build(buffer, i, mid - 1);
        root.right = build(buffer, mid + 1, j);
        return root;
    }

    //将有序数组转换为二叉搜索树
    private TreeNode toBST(int[] nums,int start,int end){
        if(end<start){
            return null;
        }
        int mid = start + (end - start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBST(nums,start,mid-1);
        root.right = toBST(nums,mid+1,end);
        return root;
    }

    List<Integer> order;
    public TreeNode balanceBST(TreeNode root) {
        order = new ArrayList<>();
        inOrder(root);
        return helper(0, order.size() - 1);
    }

    public TreeNode helper(int left, int right) {
        if (left > right)
            return null;
        if (left == right)
            return new TreeNode(order.get(left));
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(order.get(mid));
        root.left = helper(left, mid - 1);
        root.right = helper(mid + 1, right);

        return root;
    }

    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        order.add(root.val);
        inOrder(root.right);
    }
}
