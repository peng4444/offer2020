# 树算法题
[LeetCode刷题总结-树篇（上）](https://www.cnblogs.com/liuzhen1995/p/11921771.html)
## 树的四大类
    树的自身特性：对称，翻转，打印，合并，剪枝，右视图，深度和广度
    树的类型：依据前序和中序(或者后序)遍历构造二叉树，依据前序遍历还原二叉树
    子树问题：
    新概念定义问题
```javascript
//最大值
public int getMax(TreeNode root) {
    if (root == null) {
        return Integer.MIN_VALUE;
    } else {
        int left = getMax(root.left);
        int right = getMax(root.right);
        return Math.max(Math.max(left, rigth), root.val);
    }
}
//最大深度
public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }

    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left, right) + 1;
}
//最小深度
public int minDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (left == 0) {
        return right + 1;
    } else if (right == 0) {
        return left + 1;
    } else {
        return Math.min(left, right) + 1;
    }
}
```
### 完成
```markdown
94,144,145二叉树前序，中序，后序遍历
> 二叉搜索树 95,96
101,111,236,124,687,617
>> 二叉树路径长度：112,113，124,129，666
```
### 未完成 337,257,437,199,971,655,814,662,889,1028,623,863,968,1145,979,987
[LeetCode刷题总结-树篇（中）](https://www.cnblogs.com/liuzhen1995/p/11951163.html)
### 二叉搜索树 95,98,99,230,450,501,701
### 平衡二叉树 110
```javascript
public boolean isBalanced(TreeNode root) {
    return maxDepth(root) != -1;
}
private int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
        return -1;
    }
    return Math.max(left, right) + 1;
}
```
### 满二叉树 894
### 完全二叉树 222,919,958
### 线段树 715,732,850
### 字典树 208,212,648
### 树段数组 307,315
### 树的拓展 114,117,297,834,998,508,652,865，1110
[Leetcode树](https://cyc2018.github.io/CS-Notes/#/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E6%A0%91)

### 完成
```markdown
104,110,543,226,617,112,101,111,687
```
### 未完成
```markdown
437,572,404,337,671,637,513,669,230,235,108,109,653,530,208,677,
```
