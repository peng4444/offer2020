package cn.offer2020.pbj.demo.job.kuaishou;

import java.util.ArrayList;

/**
 * @ClassName: KuaiShou0322
 * @Author: pbj
 * @Date: 2020/3/23 14:37
 * @Description: TODO
 */
public class KuaiShou0322 {

    //1.获取队中从前到后每个人与前方身高高于自己的人的最短距离
    public static int[] DistanceToHigh(int[] height) {
        int len = height.length;
        int[] flag = new int[len];
        flag[0] = 0;
        for (int i = len - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > height[i]) {
                    flag[i] = height[j] - height[i];
                    break;
                }
                if (j == 0) {
                    flag[i] = 0;
                }
            }
        }
        return flag;
    }

    //2.求数组0~ i-1中只有一个值比A[i]大的

    public static int[] four(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return new int[]{};
        }
//    判断最大数重复的情况
        boolean maxDump = false;
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < max && nums[i] >= secondMax && !maxDump) {
                list.add(i);
            }
            if (nums[i] > max) {
                maxDump = false;
                secondMax = max;
                max = nums[i];
            } else if (nums[i] < max && nums[i] > secondMax) {
                secondMax = nums[i];
            } else if (nums[i] == max) {
                maxDump = true;
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    //3.求手机中的靓号如888、0123这种连号

}
