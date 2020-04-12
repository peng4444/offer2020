package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo894
 * @Author: pbj
 * @Date: 2020/1/3 13:29
 * @Description: TODO N个节点所有可能的满二叉树
 */
public class Demo894 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: 递归实现 递推关系: allPossibleFBT(N) = allPossibleFBT(i) + allPossibleFBT(N – 1 - i)，其中i为奇数，1<= i<N。
     * 1.递归求解所有可能的左子树集合allPossibleFBT(i)和右子树集合allPossibleFBT(N - i - 1) 2.使用记忆化搜索 用List来保存递归过程中的重复子过程
     * @param: [N]
     * @return: java.util.List<cn.offer2020.pbj.demo.leetcode.recursion.Demo894.TreeNode>
     * @auther: pbj
     * @date: 2020/1/3 13:32
     */
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<>();
        if (N % 2 == 0) {
            return ans;
        }

        if (N == 1) {
            TreeNode head = new TreeNode(0);
            ans.add(head);
            return ans;
        }

        for (int i = 1; i < N; i = i + 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - 1 - i);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode head = new TreeNode(0);
                    head.left = l;
                    head.right = r;
                    ans.add(head);
                }
            }
        }
        return ans;
    }
}
