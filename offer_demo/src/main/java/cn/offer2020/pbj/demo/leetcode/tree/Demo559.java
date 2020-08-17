package cn.offer2020.pbj.demo.leetcode.tree;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @pClassName: Demo559
 * @author: pengbingjiang
 * @create: 2020/8/17 11:15
 * @description: TODO 559.N叉树的最大深度
 */
public class Demo559 {
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
    };
    //递归
    public int maxDepth(Node root) {
        if(root==null) return 0;
        if(root.children==null) return 1;
        int max = 0;
        for(Node node:root.children){
            max = Math.max(max,maxDepth(node));
        }
        return max + 1;
    }
    //迭代
    public int maxDepth1(Node root) {
        Queue<Pair<Node, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<Node, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                for (Node c : root.children) {
                    stack.add(new Pair(c, current_depth + 1));
                }
            }
        }
        return depth;
    }

}
