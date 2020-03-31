package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter3;

import java.util.*;

/**
 * @ClassName: BinaryTree
 * @Author: pbj
 * @Date: 2019/9/16 14:27
 * @Description: TODO
 */
public class BinaryTree {
    //二叉树节点
    public static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int data) {
            this.data = data;
        }
    }
    //构建二叉树 二叉树的构建方法有很多，这里把一个线性的链表转化成非线性的二叉树，链表节点的顺序恰恰是二叉树前序遍历的顺序。链表中的空值，代表二叉树节点的左孩子或右孩子为空的情况。
    public static TreeNode createBinaryTree(LinkedList<Integer> inuptList) {
        TreeNode node = null;
        if (inuptList == null || inuptList.isEmpty()) {
            return null;
        }
        Integer data = inuptList.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inuptList);
            node.rightChild = createBinaryTree(inuptList);
        }
        return node;
    }

    //二叉树前序遍历
    public static void preOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data+" ");
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    //二叉树中序遍历
    public static void inOrderTreaveral(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderTreaveral(node.leftChild);
        System.out.print(node.data+" ");
        inOrderTreaveral(node.rightChild);
    }

    //后序遍历
    public static void postOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
        System.out.print(node.data+" ");
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.asList(new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4}));
        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历");
        preOrderTraveral(treeNode);
        System.out.println();
        System.out.println("中序遍历");
        inOrderTreaveral(treeNode);
        System.out.println();
        System.out.println("后序遍历");
        postOrderTravel(treeNode);
    }

    //二叉树非递归前序遍历(栈实现)
    public static void perOrderTraveralWithStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = root;
        while (treeNode != null || !stack.isEmpty()) {
            //迭代访问节点的左孩子并且入栈
            while (treeNode != null) {
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问右节点孩子
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }
        }
     }

    //二叉树层次遍历//BFS   队列实现
    public static void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
    }

    // 层次遍历(DFS)
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        dfs(root, res, 0);
        return res;
    }

    private static void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (level == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.data);

        dfs(root.leftChild, res, level + 1);
        dfs(root.rightChild, res, level + 1);
    }



}
