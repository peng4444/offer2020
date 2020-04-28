package cn.offer2020.pbj.demo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName: Demo662
 * @Author: pbj
 * @Date: 2020/4/27 09:37
 * @Description: TODO 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 */
public class Demo662 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    private int maxW = 0;
    public int widthOfBinaryTree(TreeNode root) {
        /**
         假设满二叉树表示成数组序列, 根节点所在的位置为1, 则任意位于i节点的左右子节点的index为2*i, 2*i+1
         用一个List保存每层的左端点, 易知二叉树有多少层List的元素就有多少个. 那么可以在dfs的过程中记录每个
         节点的index及其所在的层level, 如果level > List.size()说明当前节点就是新的一层的最左节点, 将其
         加入List中, 否则判断当前节点的index减去List中对应层的最左节点的index的宽度是否大于最大宽度并更新
         **/
        dfs(root, 1, 1, new ArrayList<>());
        return maxW;
    }

    private void dfs(TreeNode r, int level, int index, List<Integer> left) {
        if(r == null) return;
        if(level > left.size()) left.add(index);
        maxW = Math.max(maxW, index - left.get(level-1) + 1);
        dfs(r.left, level+1, index*2, left);
        dfs(r.right, level+1, index*2+1, left);
    }

    //思路：将二叉树的下标存储到数组中，根节点下标为 1，左子树结点为 2 * i，右子树下标为 2 * i+1。
    //然后我们层次遍历，每次比较最大宽度值。具体实现见代码，简单移动。
    public int widthOfBinaryTree1(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();
        q.offer(root);
        list.add(1);
        int res = 1;
        while (!q.isEmpty()) {
            int count = q.size();
            for(int i =count; i> 0; i--) {
                TreeNode cur = q.poll();
                Integer curIndex = list.removeFirst();
                if(cur.left != null) {
                    q.offer(cur.left);
                    list.add(curIndex * 2);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                    list.add(curIndex * 2 +1);
                }
            }
            // list 中 size 为 1 的情况下，宽度也为 1，没有必要计算。
            if(list.size() >= 2) {
                res = Math.max(res, list.getLast() - list.getFirst() + 1);
            }
        }
        return res;
    }
}
