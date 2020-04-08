package cn.offer2020.pbj.demo.leetcode.greed;

/**
 * @ClassName: Demo605
 * @Author: pbj
 * @Date: 2020/4/7 10:40
 * @Description: TODO 605. 种花问题
 */
public class Demo605 {
    //贪心
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int cnt = 0;
        for(int i =0;i<len&&cnt<n;i++){
            if(flowerbed[i]==1){
                continue;
            }
            int pre = i==0?0:flowerbed[i-1];
            int next = i==len-1?0:flowerbed[i+1];
            if(pre==0&&next==0){
                cnt++;
                flowerbed[i]=1;
            }
        }
        return cnt>=n;
    }
}
