package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo458
 * @Author: pbj
 * @Date: 2020/5/20 11:39
 * @Description: TODO 458. 可怜的小猪
 */
public class Demo458 {
    public int poorPigs(int buckets,int minutesToDie,int minutesToTest){
        int times = minutesToTest / minutesToDie;
        int base = times + 1;
        double temp = Math.log(buckets) / Math.log(base);
        int ans = (int) Math.ceil(temp);
        return ans;
    }
}
