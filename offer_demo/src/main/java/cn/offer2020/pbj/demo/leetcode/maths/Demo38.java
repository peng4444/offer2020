package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo38
 * @Author: pbj
 * @Date: 2019/12/15 16:48
 * @Description: TODO 报数
 */
public class Demo38 {
    /* *
     * 功能描述: 解题思路：
     * 本题的难点在于：报数的概念理解，至少我从题意中没有很清晰的理解，但是感觉像是个递推式
     * 从4->5分析，将4个每一位拆开看（个数+数字），4=1211 => 1=11，2=12，11=21，所以5=111221
     * 所以解题用循环，从1->n可求解出来
     * @param: [n]
     * @return: java.lang.String
     * @auther: pbj
     * @date: 2019/12/15 16:54
     */
    public static String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            char pre = str.charAt(0);
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                char c = str.charAt(j);
                if (c == pre) {
                    count++;
                } else {
                    builder.append(count).append(pre);
                    pre = c;
                    count = 1;
                }
            }
            builder.append(count).append(pre);
            str = builder.toString();
        }

        return str;
    }
}
