package cn.offer2020.pbj.demo.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: Demo501
 * @Author: pbj
 * @Date: 2020/3/29 11:28
 * @Description: TODO
 */
public class Demo501 {

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    int maxTimes = 0;
    int thisTimes = 0;
    List<Integer> list = new LinkedList<>();
    TreeNode pre = null;

    public int[] findMode(TreeNode root) {
        inOrder(root);
        int length = list.size();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public void inOrder(TreeNode root){
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (pre != null && pre.val == root.val) {
            thisTimes++;
        } else {
            thisTimes = 1;
        }
        if (thisTimes == maxTimes) {
            list.add(root.val);
        } else if (thisTimes > maxTimes) {
            maxTimes = thisTimes;
            list.clear();
            list.add(root.val);
        }
        pre = root;
        inOrder(root.right);
    }


    //
    class Solution {
        int preVal = 0, curTimes = 0, maxTimes = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        public int[] findMode(TreeNode root) {
            traversal(root);
            //list转换为int[]
            int size = list.size();
            int[] ans = new int[size];
            for(int i = 0; i < size; i++){
                ans[i] = list.get(i);
            }
            return ans;
        }
        //二叉搜索树中序遍历是递增顺序
        public void traversal(TreeNode root){
            if(root != null){
                traversal(root.left);
                //判断当前值与上一个值的关系, 更新 curTimes 和 preVal
                if(preVal == root.val){
                    curTimes++;
                }else{
                    preVal = root.val;
                    curTimes = 1;
                }
                //判断当前数量与最大数量的关系, 更新 list 和 maxTimes
                if(curTimes == maxTimes){
                    list.add(root.val);
                }else if(curTimes > maxTimes){
                    list.clear();
                    list.add(root.val);
                    maxTimes = curTimes;
                }
                traversal(root.right);
            }
        }
    }
}
