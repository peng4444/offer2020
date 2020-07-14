package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Demo36
 * @Author: pbj
 * @Date: 2019/12/25 22:23
 * @Description: TODO 36.判断有效的数独
 * 思想:使用line,column,和子区域来分别保存元素,如果元素有重复的就返回false,否则返回true即可. 在这里巧用了一下集合.
 * 判断某个元素是否在集合中时间复杂度是O(1),如果使用列表的话就是O(n)了. 其中划分子区域的技巧很巧妙,使用的是 pos = (i//3)*3 + j//3.
 */
public class Demo36 {

    public boolean isValidSudoku1(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i / 3 + "-" + j / 3))
                        return false;
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIndex = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }

    /* *
     * 功能描述: 主要两个要点：
    1、只遍历一次如何储存数据；
    2、判断是在一个3*3的框中的方法。
    1、使用了2进制的9个位数，如果是第一个数是1，那么统计标志就是0000000010(二进制 1左移1位)，如果第二个数是3那么统计标识变为0000001010(二进制 1左移3位再加上原来的)，每次判断有没有重复就右移相应位数之后整除2即可。
    2、同官方解法int boxNum = i / 3 * 3 + j / 3;如果是0,1,2行的话整除3就是0，然后再加上列数整除3，这样就把整个9*9分为了编号0-8的9个3*3的区域。
     * @param: [board]
     * @return: boolean
     * @auther: pbj
     * @date: 2019/12/25 22:29
     */
    public boolean isValidSudoku(char[][] board) {
        int[] rowCnt = new int[9];
        int[] colCnt = new int[9];
        int[] boxCnt = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ('.' == board[i][j]) {
                    continue;
                }
                int num = board[i][j] - 48;
                // 处理行
                if ((rowCnt[i] >> num) % 2 == 1) {
                    return false;
                } else {
                    rowCnt[i] += 1 << num;
                }
                // 处理列
                if ((colCnt[j] >> num) % 2 == 1) {
                    return false;
                } else {
                    colCnt[j] += 1 << num;
                }
                // 处理框
                int boxNum = i / 3 * 3 + j / 3;
                if ((boxCnt[boxNum] >> num) % 2 == 1) {
                    return false;
                } else {
                    boxCnt[boxNum] += 1 << num;
                }
            }
        }
        return true;
    }

    /* *
     * 功能描述:官方解答
     * @param:
     * @return:
     * @auther: pbj
     * @date: 2019/12/27 21:35
     */
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            // init data
            HashMap<Integer, Integer> [] rows = new HashMap[9];
            HashMap<Integer, Integer> [] columns = new HashMap[9];
            HashMap<Integer, Integer> [] boxes = new HashMap[9];
            for (int i = 0; i < 9; i++) {
                rows[i] = new HashMap<Integer, Integer>();
                columns[i] = new HashMap<Integer, Integer>();
                boxes[i] = new HashMap<Integer, Integer>();
            }

            // validate a board
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char num = board[i][j];
                    if (num != '.') {
                        int n = (int)num;
                        int box_index = (i / 3 ) * 3 + j / 3;

                        // keep the current cell value
                        rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                        columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                        boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                        // check if this value has been already seen before
                        if (rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1)
                            return false;
                    }
                }
            }

            return true;
        }
    }
}
