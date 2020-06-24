package cn.offer2020.pbj.demo.leetcode.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: Demo104
 * @Author: pbj
 * @Date: 2019/12/22 11:29
 * @Description: TODO 104.二叉树的最大深度
 */
public class Demo104 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: BFS
     * @param: [root]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/22 11:52
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;//根节点为空，返回空
        Queue<TreeNode> queue = new LinkedList<>();//创建队列
        queue.add(root);//将头节点加入到队列
        int depth = 0;
        while (!queue.isEmpty()) {//如果队列不为空
            int levelSize = queue.size();//获取队列的长度
            depth++;
            for (int i = 0; i < levelSize; i++) {//遍历已经存入队列的
                TreeNode currNode = queue.poll();//从队列中取出一个
                if (currNode.left != null) {//如果取出的节点有左孩子
                    queue.add(currNode.left);//将其加入到队列
                }
                if (currNode.right != null) {//如果取出的节点有右孩子
                    queue.add(currNode.right);//将其加入到队列
                }

            }
        }
        return depth;
    }

    /* *
     * 功能描述: 递归实现 深度优先遍历
     * 时间复杂度O(N)
     * @param: [root]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/22 11:51
     */
    public int maxDepth2(TreeNode root) {
        //递归退出条件，到叶子节点
        if (root == null) {
            return 0;
        } else {
            //计算左子树最大深度
            int left_hight = maxDepth2(root.left);
            //计算右子树最大深度
            int right_hight = maxDepth2(root.right);
            //以某个节点为根节点的数的最大深度为max
            return Math.max(left_hight, right_hight) + 1;
        }
    }

}
