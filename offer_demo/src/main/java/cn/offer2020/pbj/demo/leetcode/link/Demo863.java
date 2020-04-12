package cn.offer2020.pbj.demo.leetcode.link;

import java.util.*;

/**
 * @ClassName: Demo863
 * @Author: pbj
 * @Date: 2019/12/9 19:55
 * @Description: TODO 二叉树中所有距离为 K 的结点
 */
public class Demo863 {

      //Definition for a binary tree node.
      public class TreeNode {
         int val;
         TreeNode left;
          TreeNode right;
         TreeNode(int x) { val = x; }
      }

    HashMap<TreeNode, TreeNode> parent = new HashMap<>();
      /* *
       * 功能描述: 对所有节点添加一个指向父节点的引用，之后做深度优先搜索，找到所有距离 target 节点 K 距离的节点。
       * 时间复杂度 O(n) 空间复杂度O(n)
       * @param: [root, target, k]
       * @return: java.util.List<java.lang.Integer>
       * @auther: pbj
       * @date: 2019/12/9 20:10
       */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root,null);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == k) {
                    List<Integer> ans = new ArrayList<>();
                    for (TreeNode n : queue) {
                        ans.add(n.val);
                    }
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }
        return new ArrayList<>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left,node);
            dfs(node.right, node);
        }
    }

    List<Integer> ans;
    TreeNode target;
    int K;
    /* *
     * 功能描述: 如果 target 节点在 root 节点的左子树中，且 target 节点深度为 3，那所有 root 节点右子树中深度为 K - 3 的节点到 target 的距离就都是 K。
     * 时间复杂度 O(n) 空间复杂度O(n)
     * @param: [root, target, K]
     * @return: java.util.List<java.lang.Integer>
     * @auther: pbj
     * @date: 2019/12/9 20:28
     */
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        ans = new LinkedList<>();
        this.target = target;
        this.K = K;
        return ans;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return -1;
        } else if (node == target) {
            subtree_add(node, 0);
            return 1;
        } else {
            int L = dfs(node.left), R = dfs(node.right);
            if (L != -1) {
                if (L == K) ans.add(node.val);
                subtree_add(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K) ans.add(node.val);
                subtree_add(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    public void subtree_add(TreeNode node, int dist) {
        if (node == null) {
            return;
        }
        if (dist == K) {
            ans.add(node.val);
        } else {
            subtree_add(node.left,dist+1);
            subtree_add(node.right,dist+1);
        }
    }
}
