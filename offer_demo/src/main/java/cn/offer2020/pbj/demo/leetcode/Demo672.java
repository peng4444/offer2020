package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo672
 * @Author: pbj
 * @Date: 2020/4/26 10:02
 * @Description: TODO 672. 灯泡开关 Ⅱ
 * 现有一个房间，墙上挂有 n 只已经打开的灯泡和 4 个按钮。在进行了 m 次未知操作后，你需要返回这 n 只灯泡可能有多少种不同的状态。
 */
public class Demo672 {
    public int flipLights(int n, int m) {
        n = Math.min(n, 3);
        if (m == 0) return 1;
        if (m == 1) return n == 1 ? 2 : n == 2 ? 3 : 4;
        if (m == 2) return n == 1 ? 2 : n == 2 ? 4 : 7;
        return n == 1 ? 2 : n == 2 ? 4 : 8;
    }
    /*
所有的操作进行偶数次是会抵消的，那么所有操作只存在0,1两种，即无效果和有效果；
且各一次操作2,3等效为一次操作1；
画一个类似真值表，可以推出  n>=3且m>=3时,结果只会是8；
接下来考虑个别情况即可。
*/
    public int flipLights1(int n, int m) {
        if (n == 0 || m == 0) {
            return 1;
        }
        if (m == 1) {
            return n < 3 ? n + m : 4;
        } else if (m == 2) {
            return n < 3 ? n * m : 7;
        } else {
            return n < 3 ? 2 * n : 8;
        }
    }
}

