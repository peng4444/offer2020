package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: Demo515
 * @Author: pbj
 * @Date: 2020/6/16 10:33
 * @Description: TODO 515. 在每个树行中找最大值
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
public class Demo515 {
    //层次遍历 BFS
    public List<Integer> largestValues(TreeNode root) {
        if(root==null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(node.val, max);
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            ans.add(max);
        }
        return ans;
    }

    // 递归方式 DFS
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        levelOrder(root, res, 0);
        return res;
    }

    //level表示的是第几层，集合res中的第一个数据表示的是
    // 第一层的最大值，第二个数据表示的是第二层的最大值……
    private void levelOrder(TreeNode root, List<Integer> collectors, int level) {
        if (root == null) {
            return;
        }
        //如果走到下一层了直接加入到集合中
        if (level >= collectors.size()) {
            collectors.add(level, root.val);
        } else {
            Integer val = collectors.get(level);
            if (root.val > val) {
                collectors.set(level, root.val);
            }
        }
        levelOrder(root.left, collectors, level + 1);
        levelOrder(root.right, collectors, level + 1);
    }
}
