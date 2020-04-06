package cn.offer2020.pbj.demo.leetcode.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName: Demo435
 * @Author: pbj
 * @Date: 2020/4/6 22:03
 * @Description: TODO 435. 无重叠区间
 */
public class Demo435 {

    //贪心 按区间的结尾进行排序，每次选择结尾最小，并且和前一个区间不重叠的区间。
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length==0)return 0;
        //lambda表达式
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1,int[] o2){
                return o1[1]-o2[1];
            }
        });
        int cnt =1;
        int end = intervals[0][1];
        for(int i = 1;i<intervals.length;i++){
            if(intervals[i][0]<end){
                continue;
            }
            end = intervals[i][1];
            cnt++;
        }
        return intervals.length-cnt;
    }
}
