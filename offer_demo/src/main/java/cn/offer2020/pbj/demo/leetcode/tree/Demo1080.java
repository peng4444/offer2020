package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo1080
 * @Author: pbj
 * @Date: 2020/4/23 16:18
 * @Description: TODO
 */
public class Demo1080 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        TreeNode left, right;

        if(root == null)
            return null;
        //如果是叶子节点，判断是否需要删去
        if(root.left == null && root.right == null){
            if(root.val < limit)
                return null;
            return root;
        }
        left = sufficientSubset(root.left, limit - root.val);
        right = sufficientSubset(root.right, limit - root.val);
        //如果两个子树都被删掉,说明这个节点是不足节点
        if(left == null && right == null)
            return null;
        //没被全删点就说明不是不足节点，保留即可
        root.left = left;
        root.right = right;
        return root;
    }

    //DFS
    public TreeNode sufficientSubset1(TreeNode root, int limit) {
        if(root==null)
            return null;
        boolean flag = dfs(root,0,limit);
        if(flag)
            return null;
        return root;
    }

    public boolean dfs(TreeNode root,int step,int limit){
        if(root==null)
            return true;
        step+=root.val;
        if(root.left==null && root.right==null){
            if(step<limit)
                return true;
            else
                return false;
        }
        boolean left = dfs(root.left,step,limit);
        boolean right = dfs(root.right,step,limit);
        if(left)
            root.left=null;
        if(right)
            root.right=null;
        return left && right;
    }
}
