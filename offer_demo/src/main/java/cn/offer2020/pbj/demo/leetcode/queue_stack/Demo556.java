package cn.offer2020.pbj.demo.leetcode.queue_stack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName: Demo556
 * @Author: pbj
 * @Date: 2020/4/18 11:51
 * @Description: TODO 556. 下一个更大元素 III
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
 */
public class Demo556 {
    /* *
     * 功能描述:
     * 1.从个位数开始往高位数遍历，如果每个高位数都大于相邻的低位数，则此数字已经是最大的了，直接返回-1。
        2.否则遍历直到发现首次出现高位数比相邻的低位数小的情况（称这个高位数所在的位为“分界点”），
        * 这时应该在分界点右边的所有数里面找出最小的比它大的数，把两个数交换位置，此时“分界点”右边的数仍然是从大到小，
        * 两两交换使它们顺序改为从小到大，然后转换成int返回。
     * @param: [n]
     * @return: int
     * @auther: pbj
     * @date: 2020/4/18 11:54
     */
    public int nextGreaterElement(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        for(int i = chars.length - 1; i > 0; --i){
            if(chars[i - 1] >= chars[i]) continue;
            --i;
            int j = i;
            while(j != chars.length - 1 && chars[j + 1] > chars[i]) ++j;
            swap(chars, i , j);
            for(int k = i + 1; k <= i + (chars.length - 1 - i) / 2; ++k){
                swap(chars, k, chars.length + i - k);
            }
            try{
                return Integer.parseInt(new String(chars));
            }
            catch (Exception e){
                return -1;
            }
        }
        return -1;
    }

    private void swap(char[] chars, int i, int j){
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
    //线性解法
    public class Solution1 {
        public int nextGreaterElement(int n) {
            char[] a = ("" + n).toCharArray();
            int i = a.length - 2;
            while (i >= 0 && a[i + 1] <= a[i]) {
                i--;
            }
            if (i < 0)
                return -1;
            int j = a.length - 1;
            while (j >= 0 && a[j] <= a[i]) {
                j--;
            }
            swap(a, i, j);
            reverse(a, i + 1);
            try {
                return Integer.parseInt(new String(a));
            } catch (Exception e) {
                return -1;
            }
        }
        private void reverse(char[] a, int start) {
            int i = start, j = a.length - 1;
            while (i < j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        private void swap(char[] a, int i, int j) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    //暴力求解
    public class Solution {
        public String swap(String s, int i0, int i1) {
            if (i0 == i1)
                return s;
            String s1 = s.substring(0, i0);
            String s2 = s.substring(i0 + 1, i1);
            String s3 = s.substring(i1 + 1);
            return s1 + s.charAt(i1) + s2 + s.charAt(i0) + s3;
        }
        ArrayList< String > list = new ArrayList < > ();
        void permute(String a, int l, int r) {
            int i;
            if (l == r)
                list.add(a);
            else {
                for (i = l; i <= r; i++) {
                    a = swap(a, l, i);
                    permute(a, l + 1, r);
                    a = swap(a, l, i);
                }
            }
        }
        public int nextGreaterElement(int n) {
            String s = "" + n;
            permute(s, 0, s.length() - 1);
            Collections.sort(list);
            int i;
            for (i = list.size() - 1; i >= 0; i--) {
                if (list.get(i).equals("" + n))
                    break;
            }
            return i == list.size() - 1 ? -1 : Integer.parseInt(list.get(i + 1));
        }
    }
}
