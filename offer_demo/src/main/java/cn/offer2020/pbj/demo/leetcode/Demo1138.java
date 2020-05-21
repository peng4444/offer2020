package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo1138
 * @Author: pbj
 * @Date: 2020/5/21 10:52
 * @Description: TODO 1138. 字母板上的路径
 */
public class Demo1138 {
    public String alphabetBoardPath(String target) {
        String[] board = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
        int[] res = new int[26];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length(); j++)
                res[board[i].charAt(j) - 'a'] = 10 * i + j;
        StringBuffer sb = new StringBuffer();
        int index = 0, x, y, dt;
        for (int i = 0; i < target.length(); i++) {
            dt = res[target.charAt(i) - 'a'];
            x = dt / 10 - index / 10;
            y = dt % 10 - index % 10;
            if (target.charAt(i) == 'z') {
                change(sb, y, 'R', 'L');
                change(sb, x, 'D', 'U');
            } else {
                change(sb, x, 'D', 'U');
                change(sb, y, 'R', 'L');
            }
            sb.append('!');
            index = dt;
        }
        return sb.toString();
    }

    private void change(StringBuffer sb, int x, char ch1, char ch2) {
        int sign = x > 0 ? 1 : -1;
        char tmp = x > 0 ? ch1 : ch2;
        while (x != 0) {
            sb.append(tmp);
            x -= sign;
        }
    }
}
