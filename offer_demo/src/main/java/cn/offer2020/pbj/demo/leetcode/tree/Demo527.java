package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo527
 * @Author: pbj
 * @Date: 2020/4/16 11:53
 * @Description: TODO 572. 另一个树的子树
 */
public class Demo527 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //比较节点
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null) return false;
        return isSubTreeWithRoot(s,t)||isSubtree(s.left,t)||isSubtree(s.right,t);
    }
    public boolean isSubTreeWithRoot(TreeNode s,TreeNode t){
        if(t==null&&s==null) return true;
        if(t==null||s==null) return false;
        if(t.val!=s.val) return false;
        return isSubTreeWithRoot(s.left,t.left)&&isSubTreeWithRoot(s.right,t.right);
    }

    //先序遍历
    public boolean isSubtree2(TreeNode s, TreeNode t) {
        String tree1 = preOrder(s, true);
        String tree2 = preOrder(t, true);
        return tree1.indexOf(tree2) >= 0;
    }

    private String preOrder(TreeNode node, boolean left) {
        if (node == null) {
            if (left) return "lnull";
            else return "rnull";
        }

        return "#" + node.val + " " + preOrder(node.left, true) + " " + preOrder(node.right, false);
    }
}
