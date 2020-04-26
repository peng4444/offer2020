package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: demo1145
 * @Author: pbj
 * @Date: 2020/4/26 08:44
 * @Description: TODO 1145. 二叉树着色游戏
 */
public class demo1145 {

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    //极客1选的起始点有多少个左节点
    private int left = 0;
    //极客1选的起始点有多少个右节点
    private int right = 0;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        //极客1选了第一个节点后，将树划分为了三个部分（可能为空）
        //第一部分：left 第二部分：right 第三部分：n - (left + right) - 1
        //只需要总结点的数的一半 < 三个部分中的最大值，极客2就可以获胜
        return getNum(root, x) / 2 < Math.max(Math.max(left, right),n-(left+right)-1);
    }

    private int getNum(TreeNode node, int x) {
        if (node == null) {
            return  0;
        }
        int r = getNum(node.right, x);
        int l = getNum(node.left, x);
        if (node.val == x) {
            left  = l;
            right = r;
        }
        return l + r + 1;
    }


    int max = 0;

    int dfs(TreeNode root, int x, int n)
    {
        if(root == null)return 0;
        int l = dfs(root.left, x, n);
        int r = dfs(root.right, x, n);
        if(root.val == x){
            max = Math.max(max, l);
            max = Math.max(max, r);
            max = Math.max(max, n-l-r-1);
        }
        return l + r + 1;
    }

    public boolean btreeGameWinningMove1(TreeNode root, int n, int x) {
        dfs(root, x, n);
        return max > n-max;
    }
}
