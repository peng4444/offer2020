package cn.offer2020.pbj.demo.leetcode.a_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo113
 * @Author: pbj
 * @Date: 2020/3/12 13:13
 * @Description: TODO 113. 路径总和 II
 */
public class Demo113 {

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    private List<List<Integer>> ans = new ArrayList<>();

    //1
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return ans;
        }
        List<Integer> list = new ArrayList<>();
        pathSum(root, sum, list);
        return ans;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            ans.add(list);
        }
        pathSum(root.left, sum - root.val, new ArrayList<Integer>(list));
        pathSum(root.left, sum - root.val, new ArrayList<Integer>(list));
    }

    //2
    ArrayList<Integer> inner = new ArrayList<>();

    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return ans;
        }
        sum = sum - root.val;
        inner.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == 0) {
                ans.add(new ArrayList<>(inner));
            }
        }
        if (root.left != null) {
            pathSum2(root.left, sum);
        }
        if (root.right != null) {
            pathSum2(root.right,sum);
        }
        inner.remove(inner.size() - 1);
        return ans;
    }
}
