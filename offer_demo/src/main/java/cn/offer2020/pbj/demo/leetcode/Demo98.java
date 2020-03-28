package cn.offer2020.pbj.demo.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @ClassName: Demo98
 * @Author: pbj
 * @Date: 2019/12/18 15:32
 * @Description: TODO 验证二叉搜索树
 */
public class Demo98 {

    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /* *
     * 功能描述: 对二叉树进行中序遍历，如果遍历之后为升序即为二叉搜索树
     * 时间复杂度O(N)
     * @param: [root]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/18 15:40
     */
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    /* *
     * 功能描述: 递归实现:首先将结点的值与上界和下界（如果有）比较。然后，对左子树和右子树递归进行该过程。
     * 时间复杂度O(N)
     * @param: [root]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/18 15:41
     */
    public boolean helper(TreeNode root, Integer min, Integer max) {
        if(root==null) return true;
        int val = root.val;
        if(min!=null&& val <= min) return false;
        if(max!=null&& val >= max) return false;
        if(!helper(root.right,val,max)) return false;
        if(!helper(root.left,min,val)) return false;
        return true;
    }
    public boolean isValidBST2(TreeNode root) {
        return helper(root, null, null);
    }

    /* *
     * 功能描述: 通过使用栈，上面的递归法可以转化为迭代法。这里使用深度优先搜索，比广度优先搜索要快一些。
     * @param: [root]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/18 15:52
     */
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>();
    LinkedList<Integer> lowers = new LinkedList<>();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }
    public boolean isValidBST3(TreeNode root) {
        Integer lower = null;
        Integer upper = null;
        Integer val;
        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();
            if(root==null) continue;
            val = root.val;
            if(lower!=null&& val <=lower) return false;
            if(upper!=null& val >=upper) return false;
            update(root.right,val,upper);
            update(root.left,lower,val);
        }
        return true;
    }
}
