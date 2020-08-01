package cn.offer2020.pbj.demo.leetcode.hard;

import java.util.Arrays;
import java.util.List;

/**
 * @pClassName: Demo632
 * @author: pengbingjiang
 * @create: 2020/8/1 16:58
 * @description: TODO 632.最小区间
 */
public class Demo632 {
    public int[] smallestRange(List<List<Integer>> nums) {

        //得到所有元素的个数
        int N=0;
        for(List<Integer> num:nums) N+=num.size();

        //将k个列表放进一个二维数组，前面放值，后面放第几个表
        int[][] ordered = new int[N][2];
        int i=0,j=0;
        for(List<Integer> num:nums){
            for(Integer temp:num){
                ordered[i][0]=temp;
                ordered[i][1]=j;
                i++;
            }
            j++;
        }

        //排序
        Arrays.sort(ordered,(o1, o2)->(o1[0]-o2[0]));

        //滑动窗口
        int[] count = new int[j];
        int[] ans = new int[2];
        int start=0,k=0;
        for(int end=0;end<N;end++){

            if(0 == count[ordered[end][1]]++) k++;
            if(k==nums.size()){
                //保持窗内为最小的包含所有列表值的区间
                while(count[ordered[start][1]] > 1) count[ordered[start++][1]]--;
                if(ans[0]==0 || ans[1]-ans[0] > ordered[end][0]-ordered[start][0] ){
                    ans[0]=ordered[start][0];
                    ans[1]=ordered[end][0];
                }
            }
        }
        return ans;

    }
}
