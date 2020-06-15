package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName: Demo429
 * @Author: pbj
 * @Date: 2020/6/15 15:50
 * @Description: TODO 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
public class Demo429 {
    //递归实现
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        if(root==null) return ans;
        helper(root, 0);
        return ans;
    }
    private void helper(Node node, Integer level) {
        if(node==null) return;
        if(ans.size()<level+1) ans.add(new ArrayList<>());
        ans.get(level).add(node.val);
        List<Node> nodes = node.children;
        if(nodes!=null && nodes.size()>0){
            for(Node childrenNode : nodes){
                helper(childrenNode,level+1);
            }
        }
    }
    //广度优先遍历
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<Node> deque = new ArrayDeque<>();
        // 将 root 结点存储到队列中
        deque.offer(root);
        while (!deque.isEmpty()) {
            List<Integer> values = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node node = deque.poll();
                values.add(node.val); // 记录每一层结点的值
                if (node.children != null && node.children.size() > 0) {
                    for (Node childNode : node.children) {
                        deque.offer(childNode); // 记录每一层结点的子结点
                    }
                }
            }
            result.add(values);
        }
        return result;
    }
}
