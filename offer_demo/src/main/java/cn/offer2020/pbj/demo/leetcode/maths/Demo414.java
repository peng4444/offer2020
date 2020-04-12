package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.TreeSet;

/**
 * @ClassName: Demo414
 * @Author: pbj
 * @Date: 2020/4/6 10:19
 * @Description: TODO 414. 第三大的数
 */
public class Demo414 {

    // 借用TreeSet（红黑树） O(n)
    public int thridMax(int[] nums) {
        if(nums==null||nums.length==0) throw new RuntimeException("Error");
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (Integer num : nums) {
            treeSet.add(num);
            if (treeSet.size() > 3) {
                treeSet.remove(treeSet.first());
            }
        }
        return treeSet.size() < 3 ? treeSet.last() : treeSet.first();
    }

    //三个变量来存放第一大，第二大，第三大的元素的变量，分别为one, two, three；
    private long MIN = Long.MIN_VALUE;    // MIN代表没有在值

    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) throw new RuntimeException("nums is null or length of 0");
        int n = nums.length;

        int one = nums[0];
        long two = MIN;
        long three = MIN;

        for (int i = 1; i <  n; i++) {
            int now = nums[i];
            if (now == one || now == two || now == three) continue;    // 如果存在过就跳过不看
            if (now > one) {
                three = two;
                two = one;
                one = now;
            } else if (now > two) {
                three = two;
                two = now;
            } else if (now > three) {
                three = now;
            }
        }

        if (three == MIN) return one;  // 没有第三大的元素，就返回最大值
        return (int)three;
    }
}
