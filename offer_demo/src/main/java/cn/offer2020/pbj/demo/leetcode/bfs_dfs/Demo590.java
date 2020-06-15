package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.*;

/**
 * @ClassName: Demo590
 * @Author: pbj
 * @Date: 2020/6/15 16:12
 * @Description: TODO 590. N叉树的后序遍历
 */
public class Demo590 {

    //递归实现
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root==null) return ans;
        for(Node child:root.children){
            postorder(child);
        }
        ans.add(root.val);
        return ans;
    }

    //深度优先遍历实现
    List<Integer> list = new ArrayList<>();
    List<Node> stack = new ArrayList<>();
    public List<Integer> postorder1(Node root) {
        if (root == null) return list;
        Node temp = root;
        stack.add(temp);
        while(stack.size() > 0) {
            temp = stack.remove(stack.size() - 1);
            list.add(temp.val);

            for(Node child : temp.children){
                stack.add(child);
            }
        }

        Collections.reverse(list);
        return list;
    }

    public List<Integer> postorder2(Node root) {
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
