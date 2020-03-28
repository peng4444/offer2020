package cn.offer2020.pbj.demo.leetcode.a_dp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName: Demo198
 * @Author: pbj
 * @Date: 2020/3/26 22:59
 * @Description: TODO 198. 打家劫舍  213.打家劫舍2  337.打家劫舍3
 */
public class Demo198 {

    //dp 198. 打家劫舍
    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        int[] ans = new int[nums.length+1];
        ans[0] = 0;
        ans[1] = nums[0];
        for(int i = 2;i<ans.length;i++){
            ans[i] = Math.max(ans[i-2]+nums[i-1],ans[i-1]);
        }
        return ans[nums.length];
    }
    //198. 打家劫舍
    public int rob1(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    //213.打家劫舍2
    public int rob2(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        return Math.max(help(nums,0,nums.length-2),help(nums,1,nums.length-1));
    }
    public int help(int[] nums,int start,int end){
        if(start==end) return nums[start];
        int fa = nums[start];
        int fb = Math.max(nums[start],nums[start+1]);
        for(int i = start+2;i<=end;i++){
            int sum = Math.max(fa+nums[i],fb);
            fa = fb;
            fb = sum;
        }
        return fb;
    }

    public int rob22(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }

    //337.打家劫舍3
    //定义一个二叉树
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public int rob(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        return Math.max(money, rob(root.left) + rob(root.right));
    }

    public int rob3(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }

    public int rob33(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }
}
