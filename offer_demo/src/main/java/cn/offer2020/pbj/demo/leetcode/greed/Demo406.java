package cn.offer2020.pbj.demo.leetcode.greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Demo406
 * @Author: pbj
 * @Date: 2020/4/7 10:15
 * @Description: TODO 406. 根据身高重建队列
 */
public class Demo406 {
    // 贪心 身高 h 降序、个数 k 值升序，然后将某个学生插入队列的第 k 个位置中。
    public int[][] reconstructQueue(int[][] people) {
        if(people==null||people.length==0||people[0].length==0){
            return new int[0][0];
        }
        Arrays.sort(people,(a, b)->(a[0]==b[0]?a[1]-b[1]:b[0]-a[0]));
        List<int[]> queue = new ArrayList<>();
        for(int[] p:people){
            queue.add(p[1],p);
        }
        return queue.toArray(new int[queue.size()][]);
    }
}
