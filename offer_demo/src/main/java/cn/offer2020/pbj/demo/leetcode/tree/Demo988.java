package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @pClassName: Demo988
 * @author: pengbingjiang
 * @create: 2020/8/2 16:07
 * @description: TODO 988.从叶结点开始的最小字符串
 */
public class Demo988 {
    String ans = "~";
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    public void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        sb.append((char)('a' + node.val));

        if (node.left == null && node.right == null) {
            sb.reverse();
            String S = sb.toString();
            sb.reverse();

            if (S.compareTo(ans) < 0)
                ans = S;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
