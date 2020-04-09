package cn.offer2020.pbj.demo.leetcode.a_recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: Demo257
 * @Author: pbj
 * @Date: 2020/4/9 15:28
 * @Description: TODO
 */
public class Demo257 {
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> resultList = new LinkedList<>();
        binaryTreePaths(root, new StringBuilder(), resultList);
        return resultList;
    }

    private void binaryTreePaths(TreeNode node,StringBuilder sb,List<String> resultList) {
        if (node == null) {
            return;
        }
        if (isLeaf(node)) {
            sb.append(node.val);
            resultList.add(sb.toString());
            return;
        } else {
            sb.append(node.val).append("->");
        }
        binaryTreePaths(node.left,new StringBuilder().append(sb),resultList);
        binaryTreePaths(node.right,new StringBuilder().append(sb),resultList);
    }


    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
    //递归
    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> path = new LinkedList<>();
        construct_paths(root,"",path);
        return path;
    }
    public void construct_paths(TreeNode root, String path, LinkedList<String> paths) {
        if (root != null) {
            path = path + Integer.toString(root.val);
            if (root.left == null && root.right == null) {
                paths.add(path);
            } else {
                path += "->";
                construct_paths(root.left, path, paths);
                construct_paths(root.right, path, paths);
            }
        }
    }
    //迭代
    public List<String> binaryTreePaths1(TreeNode root) {
        LinkedList<String> paths = new LinkedList();
        if (root == null)
            return paths;

        LinkedList<TreeNode> node_stack = new LinkedList();
        LinkedList<String> path_stack = new LinkedList();
        node_stack.add(root);
        path_stack.add(Integer.toString(root.val));
        TreeNode node;
        String path;
        while (!node_stack.isEmpty()) {
            node = node_stack.pollLast();
            path = path_stack.pollLast();
            if ((node.left == null) && (node.right == null))
                paths.add(path);
            if (node.left != null) {
                node_stack.add(node.left);
                path_stack.add(path + "->" + Integer.toString(node.left.val));
            }
            if (node.right != null) {
                node_stack.add(node.right);
                path_stack.add(path + "->" + Integer.toString(node.right.val));
            }
        }
        return paths;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
