package cn.offer2020.pbj.demo.leetcode.a_recursion;

/**
 * @ClassName: Demo344
 * @Author: pbj
 * @Date: 2020/1/3 11:17
 * @Description: TODO 反转字符串 你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 */
public class Demo344 {

    /* *
     * 功能描述: 将递归用迭代实现
     * @param: [chars]
     * @return: void
     * @auther: pbj
     * @date: 2020/1/3 11:24
     */
    public void reverseString2(char[] chars) {
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
    }
    /* *
     * 功能描述: 递归实现 递推关系：reverse(s[0,n]) = reverse(s[1,n-1])
     * @param: [chars]
     * @return: void
     * @auther: pbj
     * @date: 2020/1/3 11:20
     */
    public void reverseString1(char[] chars) {
        dfs(chars, 0, chars.length);
    }
    public void dfs(char[] s, int start, int end) {
        if (start >= end) {
            return;
        }
        dfs(s, start + 1, end - 1);
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
    }
    /* *
     * 功能描述: 暴力法
     * @param: [s]
     * @return: void
     * @auther: pbj
     * @date: 2020/1/3 11:18
     */
    public void reverseString(char[] s) {
        if(s.length<2) return ;
        for(int i = 0; i<s.length/2; i++){
            char temp = s[i];
            s[i] = s[s.length-i-1];
            s[s.length-i-1] = temp;
        }
        return ;
    }
}
