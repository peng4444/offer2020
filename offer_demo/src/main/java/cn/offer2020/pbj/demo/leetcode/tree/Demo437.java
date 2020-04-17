package cn.offer2020.pbj.demo.leetcode.tree;

/**
 * @ClassName: Demo437
 * @Author: pbj
 * @Date: 2020/4/16 11:32
 * @Description: TODO 437. 路径总和 III
 */
public class Demo437 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public int pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
        int ans = pathSumWithRoot(root,sum)+pathSum(root.left,sum)+pathSum(root.right,sum);
        return ans;
    }
    public int pathSumWithRoot(TreeNode root,int sum){
        if(root==null) return 0;
        int ans = 0;
        if(root.val==sum) ans++;
        ans = ans + pathSumWithRoot(root.left,sum-root.val)+pathSumWithRoot(root.right,sum-root.val);
        return ans;
    }

    //首先先序递归遍历每个节点，再以每个节点作为起始点递归寻找满足条件的路径。
    int pathnumber;
    public int pathSum2(TreeNode root, int sum) {
        if(root == null) return 0;
        Sum(root,sum);
        pathSum(root.left,sum);
        pathSum(root.right,sum);
        return pathnumber;
    }


    public void Sum(TreeNode root, int sum){
        if(root == null) return;
        sum-=root.val;
        if(sum == 0){
            pathnumber++;
        }
        Sum(root.left,sum);
        Sum(root.right,sum);
    }
}
