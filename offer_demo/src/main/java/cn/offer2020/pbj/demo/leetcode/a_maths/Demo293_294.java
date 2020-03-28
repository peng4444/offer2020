package cn.offer2020.pbj.demo.leetcode.a_maths;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo293_294
 * @Author: pbj
 * @Date: 2020/3/25 14:53
 * @Description: TODO
 */
public class Demo293_294 {

    //leetcode293 给定一个只包含两种字符的字符串：+和-，你和你的小伙伴轮流翻转"++"变成"--"。
    // 当一个人无法采取行动时游戏结束，另一个人将是赢家。编写一个函数，计算字符串在一次有效移动后的所有可能状态。
    //从前到后遍历，遇到连续两个'+'，就将两个加号变成'-'组成新的字符串加入到结果中。
    public List<String> generatePossibleNextMoves(String s) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '-') {
                ans.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length() - i - 1));
            }
        }
        return ans;
    }

    //leetcode 294
    public boolean canWin(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+' && !canWin(s.substring(0, i - 1) + "--" + s.substring(i + 1))) {
                return true;
            }
        }
        return false;
    }
}
