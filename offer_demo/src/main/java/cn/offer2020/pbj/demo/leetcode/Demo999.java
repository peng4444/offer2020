package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo999
 * @Author: pbj
 * @Date: 2020/4/27 10:34
 * @Description: TODO 999. 可以被一步捕获的棋子数
 */
public class Demo999 {
    //本来挺简单的题，找车的位置，再上下左右各自移动判断就好了，硬是给题目绕晕了。。。
    public int numRookCaptures(char[][] board) {
        // 定义上下左右四个方向
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 找到白车所在的位置
                if (board[i][j] == 'R') {
                    // 分别判断白车的上、下、左、右四个方向
                    int res = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i, y = j;
                        while (true) {
                            x += dx[k];
                            y += dy[k];
                            if (x < 0 || x >= 8 || y < 0 || y >= 8 || board[x][y] == 'B') {
                                break;
                            }
                            if (board[x][y] == 'p') {
                                res++;
                                break;
                            }
                        }
                    }
                    return res;
                }
            }
        }
        return 0;
    }

    //建立坐标系的方法很实用
    public int numRookCaptures1(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //找到R的位置
                if (board[i][j] == 'R') {
                    //以R 为原点建立坐标系
                    //依次向上找,向下找,向右找,向左找
                    return cap(board, i, j, 0, 1) + cap(board, i, j, 0, -1) + cap(board, i, j, 1, 0) + cap(board, i, j, -1, 0);
                }
            }
        }
        return 0;
    }

    public int cap(char[][] a, int x, int y, int dx, int dy) {
        /*参数说明
         *a为原数组矩阵
         *x,y为R的坐标
         *dx,dy为增长步长
         */
        while (x >= 0 && x < a.length && y >= 0 && y < a[x].length && a[x][y] != 'B') {
            if (a[x][y] == 'p') {
                return 1;
            }
            x += dx;
            y += dy;
        }
        return 0;
    }
}
