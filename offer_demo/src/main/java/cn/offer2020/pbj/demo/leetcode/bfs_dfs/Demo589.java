package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.*;

/**
 * @ClassName: Demo589
 * @Author: pbj
 * @Date: 2020/6/15 16:04
 * @Description: TODO 589. N叉树的前序遍历
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 */
public class Demo589 {
    //1.参考二叉树的递归遍历方式：先遍历根节点，然后递归遍历左子树，再递归遍历右子树。
    //2.二N叉树的前序遍历就是先遍历根节点，然后依次递归遍历每个子树。
    //3.时间复杂度O（N）,空间复杂度O（N）
    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if(root==null) return ans;
        ans.add(root.val);
        for(Node child:root.children){
            preorder(child);
        }
        return ans;
    }

    //非递归版 深度优先遍历
    //1.二叉树的非递归遍历是每次将当前结点右孩子节点和左孩子节点依次压入栈中，注意是先右后左。
    //2.然后将出栈节点输出，并且在将其右子节点和左子节点压入栈中。
    //3.推广到N叉树，就是将当前结点的孩子节点由右到左依次压入栈中。
    //4.然后将出栈节点输出，并且将其孩子节点依次压入栈中。
    //5.时间复杂度O（N），空间复杂度O（N）
    public List<Integer> preorder1(Node root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<Node> stack = new Stack<Node>();
        if(root == null)
            return res;
        stack.push(root);
        while(!stack.isEmpty())
        {
            Node node = stack.pop();
            res.add (node.val);
            for(int i =  node.children.size()-1;i>= 0;i--)
            {
                stack.add(node.children.get(i));
            }
        }
        return res;
    }

    public List<Integer> preorder2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            Collections.reverse(node.children);
            for (Node item : node.children) {
                stack.add(item);
            }
        }
        return output;
    }
}
