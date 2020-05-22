package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo106
 * @Author: pbj
 * @Date: 2020/5/22 11:43
 * @Description: TODO 106. 从中序与后序遍历序列构造二叉树
 */
public class Demo106 {
    // 其实思路比较好想到，就是如何划分子问题，然后递归的构建左子树和右子树。 inorder = [9,3,15,20,7] postorder = [9,15,7,20,3]
    //因为后序后遍历根节点，后续最后一个节点为整棵树的根节点，可以确定根节点为3;再根据中序得到: leftInOrder = [9]
    //RightInOrder = [15, 20 ,7] 又由于中序和先序的数组大小应该相同的, 所以, LeftPostOrder = [9] RightPostOrder = [15, 7, 20]
    //至此,划分为子问题:leftInOrder = [9] LeftPostOrder = [9] 构建左子树。RightPreOrder = [20, 15, 7] RightPostOrder=[15, 7, 20]构建右子树。
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return help(inorder,postorder,postorder.length-1,0,inorder.length-1);
    }
    public TreeNode help(int[] inorder,int[] postorder,int postEnd,int inStart,int inEnd){
        if(inStart>inEnd){
            return null;
        }
        int currentVal = postorder[postEnd];
        TreeNode current = new TreeNode(currentVal);
        int inIndex = 0;
        for(int i = inStart;i<=inEnd;i++){
            if(inorder[i] == currentVal){
                inIndex = i;
            }
        }
        TreeNode left = help(inorder,postorder,postEnd-(inEnd-inIndex)-1,inStart,inIndex-1);
        TreeNode right = help(inorder,postorder,postEnd-1,inIndex+1,inEnd);
        current.left = left;
        current.right = right;
        return current;
    }
}
