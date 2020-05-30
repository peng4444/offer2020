package cn.offer2020.pbj.demo.leetcode.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName: Demo247
 * @Author: pbj
 * @Date: 2020/5/29 11:22
 * @Description: TODO 247.中心对称数II
 * 中心对称数是指一个数字在旋转了180度之后看起来依然相同的数字(或者上下颠倒地看)
 * 找到所有长度为n的中心对称数
 * 输入： n = 2
 * 输出：["11","69","88","96"]
 */
public class Demo247 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(helper(n,m));
    }
    //递归模板
    private static List<String> helper(int n, int m) {
        //第一步：判断输入或者状态是否非法
        if(m<0||m<0||n>m){
            throw new IllegalArgumentException("invalid input");
        }

        //第二步：判断读递归是否应当结束
        if(n==0) return new ArrayList<String>(Arrays.asList(""));
        if(n==1) return new ArrayList<String>(Arrays.asList("0", "1", "0"));

        //第三步：缩小问题规模
        List<String> list = helper(n - 2, m);

        //第四步：整合结果
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if(n!=m) res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("6" + s + "6");
            res.add("8" + s + "8");
            res.add("9" + s + "9");
        }
        return res;
    }
}
