package cn.offer2020.pbj.demo.job.alibaba;

import java.util.Stack;

/**
 * @ClassName: Alibaba0327
 * @Author: pbj
 * @Date: 2020/3/27 16:04
 * @Description: TODO 阿里巴巴0327笔试
 * [阿里2020/03/27笔试](https://www.nowcoder.com/discuss/393249?type=1)
 */
public class Alibaba0327 {

    //问题1：给定两个等长字符串S,T。可以移动S的任意字符到其末尾。问最少移动几次可以使的S和T完全相同。不可以的话输出-1；
    public int moveTimes(String s, String t) {
        Stack<Character> stack = new Stack<>();
        int i = 0, j = 0;
        while (i < s.length()) {
            stack.push(s.charAt(i));
            if (stack.peek() == t.charAt(j)) {
                stack.pop();
                j++;
            }
        }
        return stack.size();
    }

    public int moveTimes2(String s,String t){
        if(s.length()!=t.length()) return -1;
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr1[s.charAt(i)-'a']++;
            arr2[t.charAt(i)-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if(arr1[i]!=arr2[i]) return -1;
        }
        int i = 0,j=0;
        while (i < s.length() && j < s.length()) {
            if (s.charAt(i) == t.charAt(i)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return s.length()-j;
    }

    //问题2：给定n(n <= 2000)个区间[L,R](1 <= L <= R <= 2000)，从这n个区间中等概率取一个整数，求最小值的期望。
    public float sloe(int n, int[] l, int[] r) {
        float ans = 0.0f;
        float[] nums = new float[2001];
        nums[1] = 1;
        for (int i = 2; i <= 2000; i++) {
            float sum = 1.0f;
            for (int j = 0; j < n; j++) {
                if (i < l[j]) {
                    continue;
                } else if (i > r[j]) {
                    sum = 0;
                    break;
                } else {
                    sum *=(float)(r[j]-i+1)/(float)(r[j]-l[j]+1);
                }
            }
            nums[i] = sum;
        }
        for (int k = 1; k <= 2000; k++) {
            ans = ans + (float) k * (nums[k] - nums[k + 1]);
        }
        return ans;
    }
}
