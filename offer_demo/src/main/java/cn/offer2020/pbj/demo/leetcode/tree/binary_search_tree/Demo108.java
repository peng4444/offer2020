package cn.offer2020.pbj.demo.leetcode.tree.binary_search_tree;

/**
 * @ClassName: Demo108
 * @Author: pbj
 * @Date: 2020/4/17 14:54
 * @Description: TODO 108. 将有序数组转换为二叉搜索树
 */
public class Demo108 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            v = val;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums,0,nums.length-1);
    }
    private TreeNode toBST(int[] nums,int start,int end){
        if(end<start){
            return null;
        }
        int mid = start + (end - start)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBST(nums,start,mid-1);
        root.right = toBST(nums,mid+1,end);
        return root;
    }
}
