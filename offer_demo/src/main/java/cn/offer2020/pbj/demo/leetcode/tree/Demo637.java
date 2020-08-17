package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: Demo637
 * @Author: pbj
 * @Date: 2020/4/17 10:07
 * @Description: TODO 637.二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 */
public class Demo637 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //利用队列实现广度优先搜索
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if(root==null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int cnt = queue.size();
            double sum = 0;
            for(int i = 0;i<cnt;i++){
                TreeNode node = queue.poll();
                sum +=node.val;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            ans.add(sum/cnt);
        }
        return ans;
    }

    //深度优先遍历
    public List < Double > averageOfLevels1(TreeNode root) {
        List < Integer > count = new ArrayList < > ();
        List < Double > res = new ArrayList < > ();
        average(root, 0, res, count);
        for (int i = 0; i < res.size(); i++)
            res.set(i, res.get(i) / count.get(i));
        return res;
    }
    public void average(TreeNode t, int i, List < Double > sum, List < Integer > count) {
        if (t == null)
            return;
        if (i < sum.size()) {
            sum.set(i, sum.get(i) + t.val);
            count.set(i, count.get(i) + 1);
        } else {
            sum.add(1.0 * t.val);
            count.add(1);
        }
        average(t.left, i + 1, sum, count);
        average(t.right, i + 1, sum, count);
    }
}
