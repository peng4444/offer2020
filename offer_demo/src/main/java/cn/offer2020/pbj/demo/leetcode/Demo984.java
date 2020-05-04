package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo984
 * @Author: pbj
 * @Date: 2020/5/4 15:52
 * @Description: TODO 984. 不含 AAA 或 BBB 的字符串
 * 设当前还需要写入字符串的'a'与'b'中较多的那一个为x，如果我们已经连续写了两个x了，下一次我们应该写另一个字母。否则，我们应该继续写 x。
 */
public class Demo984 {
    public String strWithout3a3b(int A, int B) {
        StringBuilder stringBuilder = new StringBuilder();
        while (A > 0 && B > 0) {
            if (A > B) {
                stringBuilder.append("aab");
                A -= 2;
                B -= 1;
            } else if (A == B) {
                for (int i = 0; i < A; i++) {
                    stringBuilder.append("ab");
                }
                A = 0;
                B = 0;
            } else {
                stringBuilder.append("bba");
                A -= 1;
                B -= 2;
            }
        }
        if (A == 0) {
            for (int i = 0; i < B; i++) {
                stringBuilder.append("b");
            }
        } else {
            for (int i = 0; i < A; i++) {
                stringBuilder.append("a");
            }
        }
        return stringBuilder.toString();
    }
}
