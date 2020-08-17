package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @pClassName: Demo429
 * @author: pengbingjiang
 * @create: 2020/8/17 15:15
 * @description: TODO 429.N叉树的层序遍历
 */
public class Demo429 {
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
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i = 0;i<len;i++){
                Node node = queue.poll();
                list.add(node.val);
                List<Node> n_list = node.children;
                for(Node no:n_list){
                    queue.offer(no);
                }
            }
            res.add(list);
        }
        return res;
    }
    //bfs
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        List<Node> previousLayer = Arrays.asList(root);
        while (!previousLayer.isEmpty()) {
            List<Node> currentLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();
            for (Node node : previousLayer) {
                previousVals.add(node.val);
                currentLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = currentLayer;
        }
        return result;
    }

    //递归
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder2(Node root) {
        if (root != null) traverseNode(root, 0);
        return result;
    }

    private void traverseNode(Node node, int level) {
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        for (Node child : node.children) {
            traverseNode(child, level + 1);
        }
    }
}
