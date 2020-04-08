package cn.offer2020.pbj.demo.leetcode.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName: Demo452
 * @Author: pbj
 * @Date: 2020/4/7 10:07
 * @Description: TODO 452. 用最少数量的箭引爆气球
 */
public class Demo452 {
    //贪心  每个气球只少需要一支箭, 先按照右端点排序, 然后每次从最小的右端点射出一支箭, 去掉被射爆的气球, 重复该过程.
    public int findMinArrowShots(int[][] points) {
        if(points.length==0) return 0;
        Arrays.sort(points, Comparator.comparingInt(o->o[1]));
        int cnt = 1,end = points[0][1];
        for(int i = 1;i<points.length;i++){
            if(points[i][0]<=end){
                continue;
            }
            cnt++;
            end = points[i][1];
        }
        return cnt;
    }
}
