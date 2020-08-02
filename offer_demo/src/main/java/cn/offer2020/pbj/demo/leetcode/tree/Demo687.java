package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo687
 * @Author: pbj
 * @Date: 2020/1/11 21:29
 * @Description: TODO 687.最长同值路径
 */
public class Demo687 {
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private int maxL = 0;

    public int longestUnivaluePath1(TreeNode root) {
        /**
         解题思路类似于124题, 对于任意一个节点, 如果最长同值路径包含该节点, 那么只可能是两种情况:
         1. 其左右子树中加上该节点后所构成的同值路径中较长的那个继续向父节点回溯构成最长同值路径
         2. 左右子树加上该节点都在最长同值路径中, 构成了最终的最长同值路径
         需要注意因为要求同值, 所以在判断左右子树能构成的同值路径时要加入当前节点的值作为判断依据
         **/
        if(root == null) return 0;
        getMaxL(root, root.val);
        return maxL;
    }

    private int getMaxL(TreeNode r, int val) {
        if(r == null) return 0;
        int left = getMaxL(r.left, r.val);
        int right = getMaxL(r.right, r.val);
        maxL = Math.max(maxL, left+right); // 路径长度为节点数减1所以此处不加1
        if(r.val == val) // 和父节点值相同才返回以当前节点所能构成的最长通知路径长度, 否则返回0
            return Math.max(left, right) + 1;
        return 0;
    }

    private int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        getEquals(root);
        return res;
    }

    public int getEquals(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getEquals(root.left);
        int right = getEquals(root.right);
        int arrowLeft =0,arrowRight = 0;
        if (root.left != null && root.left.val== root.val) {
            arrowLeft +=left+1;
        }
        if (root.right != null && root.right.val== root.val) {
            arrowRight +=right+1;
        }
        res = Math.max(res, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
