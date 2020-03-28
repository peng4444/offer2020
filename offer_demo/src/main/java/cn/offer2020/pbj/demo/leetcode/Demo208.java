package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo208
 * @Author: pbj
 * @Date: 2019/12/28 19:28
 * @Description: TODO 实现 Trie (前缀树) 字典树
 */
public class Demo208 {

    class TiredNode {
        public char val;
        public boolean isWord;
        public TiredNode[] children = new TiredNode[26];

        public TiredNode() {
        }

        TiredNode(char c) {
            TiredNode node = new TiredNode();
            node.val = c;
        }
    }

    //    /** Initialize your data structure here. */
        private TiredNode root;

    public Demo208() {
        root = new TiredNode();
        root.val =' ';
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TiredNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (ws.children[c - 'a'] == null) {
                ws.children[c - 'a'] = new TiredNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TiredNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(ws.children[c-'a']==null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TiredNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(ws.children[c-'a']==null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }
}
