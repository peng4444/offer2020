package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo90
 * @Author: pbj
 * @Date: 2019/12/28 19:58
 * @Description: TODO  单词搜索
 */
public class Demo79 {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, String word, int i, int j, int k) {
        if(k>=word.length()) return true;
        if(i<0||i>=board.length||j<0||j>board[0].length||board[i][j]!=word.charAt(k)) return false;
        board[i][j] +=256;
        boolean result = dfs(board, word, i - 1, j, k + 1) || dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i, j - 1, k + 1) || dfs(board, word, i, j + 1, k + 1);
        board[i][j] -=256;
        return result;
    }
}
