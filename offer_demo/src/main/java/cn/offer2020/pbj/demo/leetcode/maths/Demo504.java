package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo504
 * @Author: pbj
 * @Date: 2020/4/14 17:28
 * @Description: TODO 504. 七进制数
 */
public class Demo504 {
    public String convertToBase7B(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean isNegative = num < 0;
        if (isNegative) {
            num = -num;
        }
        while (num > 0) {
            sb.append(num % 7);
            num = num /7;
        }
        String ret = sb.reverse().toString();
        return isNegative ? "-" + ret : ret;
    }
    public String convertToBase7(int num) {
        return Integer.toString(num,7);
    }
}
