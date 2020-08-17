package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @pClassName: Demo589
 * @author: pengbingjiang
 * @create: 2020/8/17 14:56
 * @description: TODO 589.N叉树的前序遍历 590.N叉树的后序遍历
 */
public class Demo589 {
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
    /**
    * 递归版解题思路：
     * 参考二叉树的递归遍历方式：先遍历根节点，然后递归遍历左子树，再递归遍历右子树。
     * 二N叉树的前序遍历就是先遍历根节点，然后依次递归遍历每个子树。
     * 时间复杂度O（N）,空间复杂度O（N）
    */
    List<Integer> res = new ArrayList<Integer>();
    public List<Integer> preorder(Node root) {
        //递归版
        if(root == null)
            return res;
        res.add(root.val);
        for(Node child:root.children)
        {
            preorder(child);
        }

        return res;
    }

    /**
    * 非递归版解题思路：
     * 二叉树的非递归遍历是每次将当前结点右孩子节点和左孩子节点依次压入栈中，注意是先右后左。
     * 然后将出栈节点输出，并且在将其右子节点和左子节点压入栈中。
     * 推广到N叉树，就是将当前结点的孩子节点由右到左依次压入栈中。
     * 然后将出栈节点输出，并且将其孩子节点依次压入栈中。
     * 时间复杂度O（N），空间复杂度O（N）
    */
    public List<Integer> preorder1(Node root) {
        //非递归版
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

    public List<Integer> postorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.addFirst(node.val);
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return output;
    }

}
