package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.*;

/**
 * @ClassName: Demo103
 * @Author: pbj
 * @Date: 2020/4/20 17:07
 * @Description: TODO 103.二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class Demo103 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //使用dfs，对应层判断一下奇偶，决定在表头还是表尾添加元素就可以了
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, ans, 0);
        return ans;
    }

    private void dfs(TreeNode root, List<List<Integer>> ans, int level) {
        if (root == null) {
            return;
        }
        if (ans.size() == level) {
            ans.add(new ArrayList<Integer>());
        }
        if ((level & 1) == 1) {
            ans.get(level).add(0, root.val);
        } else {
            ans.get(level).add(root.val);
        }
        dfs(root.left, ans, level + 1);
        dfs(root.right, ans, level + 1);
    }


    //层次遍历+标志位
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth = 0;
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while(count > 0){
                TreeNode node = queue.poll();
                if(depth%2 == 1){
                    list.add(0,node.val);
                }else{
                    list.add(node.val);
                }
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                count--;
            }
            depth++;
            res.add(list);
        }
        return res;
    }

    //用两个栈做的
    private List<List<Integer>> res = new ArrayList<>();
    private ArrayList<Stack<TreeNode>> stack = new ArrayList<>();  // 可惜范型不能用数组，不然两个栈的切换看起来就更简洁

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) return res;
        stack.add(new Stack<TreeNode>());
        stack.add(new Stack<TreeNode>());
        int flag = 0;  // 是否先将左子节点压栈
        TreeNode n = root;
        List<Integer> buf = new ArrayList<>();
        do {
            buf.add(n.val);

            // 把下一层的节点压到另一个栈
            if (flag == 0) {
                if (n.left != null) stack.get(flag).push(n.left);
                if (n.right != null) stack.get(flag).push(n.right);
            } else {
                if (n.right != null) stack.get(flag).push(n.right);
                if (n.left != null) stack.get(flag).push(n.left);
            }

            if (stack.get(1 - flag).size() != 0) {
                n = stack.get(1 - flag).pop();
            } else {    // 这一层的栈遍历完，是时候遍历下一层的栈了
                if (buf.size() != 0) res.add(buf);
                buf = new ArrayList<>();
                n = stack.get(flag).size() != 0 ? stack.get(flag).pop() : null;
                flag = 1 - flag;
            }

        } while (n != null);

        return res;
    }
}
