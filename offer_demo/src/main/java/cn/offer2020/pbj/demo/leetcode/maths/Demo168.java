package cn.offer2020.pbj.demo.leetcode.maths;

/**
 * @ClassName: Demo168
 * @Author: pbj
 * @Date: 2020/3/25 11:46
 * @Description: TODO 168. Excel表列名称
 */
public class Demo168 {

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n>0){
            n--;
            sb.append((char)((n%26)+'A'));
            n/=26;
        }
        return sb.reverse().toString();
    }

    public String convertTotitle1(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int c = n % 26;
            if (c == 0) {
                c=26;
                n = n-1;
            }
            sb.insert(0, (char) ('A' + c - 1));
            n/=26;
        }
        return sb.toString();
    }
}
