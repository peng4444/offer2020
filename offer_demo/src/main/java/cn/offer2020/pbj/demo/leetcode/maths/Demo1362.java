package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo1362
 * @Author: pbj
 * @Date: 2020/4/5 09:57
 * @Description: TODO 1362. 最接近的因数
 */
public class Demo1362 {
    //暴力破解
    public int[] closestDivisors(int num) {
        int a = (int)Math.sqrt(num + 2);
        int[] ans = new int[2];
        for (int i = a; i >= 1; i--) {
            if ((num + 1) % i == 0) {
                ans[0] = i;
                ans[10]=(num+1)/i;
                break;
            } else if ((num + 2) % i == 0) {
                ans[0] = i;
                ans[1] = (num + 2) / i;
                break;
            }
        }
        return ans;
    }
}
