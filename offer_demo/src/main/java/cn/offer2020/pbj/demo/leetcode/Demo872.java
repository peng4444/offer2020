package cn.offer2020.pbj.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo872
 * @Author: pbj
 * @Date: 2020/5/6 15:34
 * @Description: TODO 872. 叶子相似的树
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 */
public class Demo872 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList();
        List<Integer> leaves2 = new ArrayList();
        dfs(root1, leaves1);
        dfs(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    public void dfs(TreeNode node, List<Integer> leafValues) {
        if (node != null) {
            if (node.left == null && node.right == null)
                leafValues.add(node.val);
            dfs(node.left, leafValues);
            dfs(node.right, leafValues);
        }
    }

    public boolean leafSimilar1(TreeNode root1, TreeNode root2) {
        String str1 = myfun(root1,"");
        String str2 = myfun(root2,"");
        return str1.equals(str2);
    }
    public String myfun(TreeNode root,String str){
        if(root == null){
            return str;
        }
        if(root.right == null && root.left == null){
            str = str +root.val;
        }
        return myfun(root.left,str) + myfun(root.right,str);
    }
}
