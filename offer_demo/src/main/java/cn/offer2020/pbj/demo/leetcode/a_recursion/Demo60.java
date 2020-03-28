package cn.offer2020.pbj.demo.leetcode.a_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo60
 * @Author: pbj
 * @Date: 2020/3/13 11:00
 * @Description: TODO 60第k个排列

 */
public class Demo60 {

    class Solution {

        public String getPermutation(int n, int k) {
            /**
             直接用回溯法做的话需要在回溯到第k个排列时终止就不会超时了, 但是效率依旧感人
             可以用数学的方法来解, 因为数字都是从1开始的连续自然数, 排列出现的次序可以推
             算出来, 对于n=4, k=15 找到k=15排列的过程:

             1 + 对2,3,4的全排列 (3!个)
             2 + 对1,3,4的全排列 (3!个)         3, 1 + 对2,4的全排列(2!个)
             3 + 对1,2,4的全排列 (3!个)-------> 3, 2 + 对1,4的全排列(2!个)-------> 3, 2, 1 + 对4的全排列(1!个)-------> 3214
             4 + 对1,2,3的全排列 (3!个)         3, 4 + 对1,2的全排列(2!个)         3, 2, 4 + 对1的全排列(1!个)

             确定第一位:
             k = 14(从0开始计数)
             index = k / (n-1)! = 2, 说明第15个数的第一位是3
             更新k
             k = k - index*(n-1)! = 2
             确定第二位:
             k = 2
             index = k / (n-2)! = 1, 说明第15个数的第二位是2
             更新k
             k = k - index*(n-2)! = 0
             确定第三位:
             k = 0
             index = k / (n-3)! = 0, 说明第15个数的第三位是1
             更新k
             k = k - index*(n-3)! = 0
             确定第四位:
             k = 0
             index = k / (n-4)! = 0, 说明第15个数的第四位是4
             最终确定n=4时第15个数为3214
             **/

            StringBuilder sb = new StringBuilder();
            // 候选数字
            List<Integer> candidates = new ArrayList<>();
            // 分母的阶乘数
            int[] factorials = new int[n+1];
            factorials[0] = 1;
            int fact = 1;
            for(int i = 1; i <= n; ++i) {
                candidates.add(i);
                fact *= i;
                factorials[i] = fact;
            }
            k -= 1;
            for(int i = n-1; i >= 0; --i) {
                // 计算候选数字的index
                int index = k / factorials[i];
                sb.append(candidates.remove(index));
                k -= index*factorials[i];
            }
            return sb.toString();
        }
    }


    // * 解题思路：将 n! 种排列分为：n 组，每组有 (n - 1)!个排列，根据k值可以确定是第几组的第几个排列，选取该排列的第1个数字，然后递归从剩余的数字里面选取下一个数字，直到n=1为止。
    // * 作者：zhuanglee
    // * 链接：https://leetcode-cn.com/problems/permutation-sequence/solution/zhi-xing-yong-shi-1-ms-zai-suo-you-java-ti-jiao-54/
    // * 来源：力扣（LeetCode）
    // * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public String getPermutation(int n, int k) {
        boolean visited[] = new boolean[n];
        return help(n, fab(n - 1), k, visited);
    }

    public String help(int n, int f, int k, boolean[] visited) {
        int offSet = k%f;
        int groupIndex = k / f + (offSet > 0 ? 1 : 0);
        int i = 0;
        for (; i < visited.length && groupIndex > 0; i++) {
            if (!visited[i]) {
                groupIndex--;
            }
        }
        visited[i - 1] = true;
        if (n - 1 > 0) {
            return String.valueOf(i) + help(n - 1, f / (n - 1), offSet == 0 ? 1 : 0, visited);
        } else {
            return String.valueOf(i);
        }
    }

    //求n!
    public int fab(int n) {
        int res = 1;
        for (int i = n; i > 1; i--) {
            res = res *i;
        }
        return res;
    }
}
