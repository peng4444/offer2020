package cn.offer2020.pbj.demo.leetcode;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * @ClassName: Demo111
 * @Author: pbj
 * @Date: 2019/12/22 12:49
 * @Description: TODO 二叉树的最小深度
 */
public class Demo111 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: BFS
     * @param: [root]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/22 13:02
     */
    public int minDepth3(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            stack.add(new Pair(root, 1));
        }

        int current_depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                break;
            }
            if (root.left != null) {
                stack.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return current_depth;
    }

    /* *
     * 功能描述: DFS
     * @param: [root]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/22 13:01
     */
    public int maxDepth2(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if(root==null) return 0;
        else stack.add(new Pair<>(root, 1));
        int min_depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.getKey();
            int current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                min_depth = Math.min(min_depth, current_depth);
            }
            if (root.left != null) {
                stack.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return min_depth;
    }

    /* *
     * 功能描述: 递归实现
     * @param: [root]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/22 12:59
     */
    public int minDepth1(TreeNode root){
        if(root==null) return 0;
        if(root.left==null||root.right==null) return 1;
        int min_depth = Integer.MAX_VALUE;
        if(root.left!=null) min_depth = Math.min(minDepth1(root.left), min_depth);
        if(root.right!=null) min_depth = Math.max(minDepth1(root.right), min_depth);
        return min_depth+1;
    }

    /* *
     * 功能描述: 递归实现
     * @param: [root]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/22 12:56
     */
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left_depth = minDepth(root.left);
        int right_depth = minDepth(root.right);
        return (left_depth == 0 || right_depth == 0) ? left_depth + right_depth + 1 : Math.min(left_depth,right_depth)+1;
    }


}
