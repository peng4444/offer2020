package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

import java.util.*;

/**
 * @pClassName: Demo472
 * @author: pengbingjiang
 * @create: 2020/8/22 16:28
 * @description: TODO
 */
public class Demo472 {
    //1.集合+DFS
    private Set<String> dict = new HashSet<>();

    public List<String> findAllConcatenatedWordInADict(String[] words) {
        dict.addAll(Arrays.asList(words));
        List<String> ans = new ArrayList<>(words.length);
        for (String word : words) {
            if (dfs(word, 0, 0)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean dfs(String word, int idx, int cnt) {
        if (idx == word.length()) {
            return cnt>1;
        }
        for (int i = idx; i < word.length(); i++) {
            if (dict.contains(word.substring(idx, i + 1))) {
                if (dfs(word, i + 1, cnt + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    //字典树-把所有单词建成字典树，然后用DFS让每个单词在这课字典树上跑，看是否由多个单词组成
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        List<String> ans = new ArrayList<>(words.length);
        for (String word : words) {
            if (trie.search(word, 0)) {
                ans.add(word);
            }
        }

        return ans;
    }

    /**
     * 使用字典树
     */
    private static class Trie {
        private TreeNode root;

        private static class TreeNode {
            String val;
            TreeNode[] children;

            public TreeNode() {
                this.val = null;
                this.children = new TreeNode[26];
            }
        }

        Trie() {
            this.root = new TreeNode();
        }

        private void insert(String word) {
            TreeNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TreeNode();
                }
                cur = cur.children[c];
            }
            cur.val = word;
        }

        private boolean search(String word, int idx) {
            TreeNode cur = root;
            for (int i = idx; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                cur = cur.children[c];
                if (cur == null) {
                    return false;
                }
                if (i < word.length() - 1) {
                    if (cur.val != null && !word.equals(cur.val)) {
                        if (search(word, i + 1)) {
                            return true;
                        }
                    }
                }
            }
            return cur.val != null && !word.equals(cur.val);
        }
    }
}
