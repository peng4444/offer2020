package cn.offer2020.pbj.demo.leetcode.greed;

import java.util.Arrays;

/**
 * @ClassName: Demo455
 * @Author: pbj
 * @Date: 2020/4/6 21:54
 * @Description: TODO 455. 分发饼干
 */
public class Demo455 {
    //贪心算法 给剩余孩子里最小饥饿度的孩子分配最小的能饱腹的饼干
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0,si =0;
        while(gi<g.length&&si<s.length){
            if(g[gi]<=s[si]){
                gi++;
            }
            si++;
        }
        return gi;
    }
}
