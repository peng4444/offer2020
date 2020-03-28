package cn.offer2020.pbj.demo.leetcode;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: Demo212
 * @Author: pbj
 * @Date: 2019/12/28 19:43
 * @Description: TODO 单词搜索 II
 */
public class Demo212 {
    Set<String> res = new HashSet<>();
    public List<String> findWords(char[][] board, String[] words) {
        Demo208 trie = new Demo208();
        for (String word : words) {
            trie.insert(word);
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }
        return new ArrayList<>(res);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Demo208 trie) {
        if(x<0||x>=board.length||y<0||y>=board[0].length) return;
        if(visited[x][y]) return;
        str += board[x][y];
        if(!trie.startsWith(str)) return;
        if(trie.search(str)) res.add(str);
        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y-1, trie);
        dfs(board, visited, str, x , y+1, trie);
        visited[x][y] = false;
    }
}
