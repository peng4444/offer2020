package cn.offer2020.pbj.demo.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @pClassName: Demo336
 * @author: pengbingjiang
 * @create: 2020/6/24 09:00
 * @description: TODO 336. 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 */
public class Demo336 {
    //前缀树解法
    class Solution {
        private TrieNode root;
        public boolean isPalindrome(String s){
            int i=0, j=s.length()-1;
            while (i < j){
                if (s.charAt(i) != s.charAt(j)){
                    return false;
                }
                i++;
                j--;
            }

            return true;
        }
        public List<List<Integer>> palindromePairs(String[] words) {
            this.root = new TrieNode();
            int n = words.length;

            //build TrieNode Tree
            for (int i=0; i<n; i++){
                String word = new StringBuilder(words[i]).reverse().toString();
                TrieNode cur = root;

                if (isPalindrome(word.substring(0)))
                    cur.suffixs.add(i);
                for (int j=0; j<word.length(); j++){
                    int index = word.charAt(j) - 'a';
                    if (cur.children[index] == null)
                        cur.children[index] = new TrieNode();
                    cur = cur.children[index];
                    if (isPalindrome(word.substring(j+1)))
                        cur.suffixs.add(i);
                }
                cur.index = i;
            }

            //search
            List<List<Integer>> res = new ArrayList<>();
            for (int i=0; i<n; i++){
                String word = words[i];
                TrieNode cur = root;

                int j=0;
                for (; j<word.length(); j++){
                    if (isPalindrome(word.substring(j)) && cur.index!=-1){
                        res.add(Arrays.asList(i, cur.index));
                    }

                    int index = word.charAt(j) - 'a';
                    if (cur.children[index] == null)
                        break;
                    cur = cur.children[index];
                }

                if (j == word.length()){
                    for (int k : cur.suffixs){
                        if (k != i)
                            res.add(Arrays.asList(i, k));
                    }
                }
            }

            return res;
        }
    }

    class TrieNode {
        public TrieNode[] children;
        public int index;
        public List<Integer> suffixs;

        public TrieNode(){
            this.children = new TrieNode[26];
            this.index = -1;
            this.suffixs = new ArrayList<>();
        }
    }

    //暴力法:遍历所有可能的字符串组合结果并检查它们是否是回文.
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                String combined = words[i].concat(words[j]);
                String reversed = new StringBuilder(combined).reverse().toString();
                if (combined.equals(reversed)) {
                    pairs.add(Arrays.asList(i, j));
                }
            }
        }
        return pairs;
    }


}
