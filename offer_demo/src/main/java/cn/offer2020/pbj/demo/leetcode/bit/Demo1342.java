package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @pClassName: Demo1342
 * @author: pengbingjiang
 * @create: 2020/7/16 10:50
 * @description: TODO 1342.将数字变成0的操作次数
 * 给你一个非负整数num，请你返回将它变成0所需要的步数。如果当前数字是偶数，你需要把它除以2；否则，减去1 。
 */
public class Demo1342 {

    public int numberOfSteps(int num) {
        int count = 0;
        while (num > 1) {
            count+=num%2!=0?2:1;
            num/=2;
        }
        return count+1;
    }

    public int numberOfSteps1(int num) {
        int count = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num -1;
            }
            count++;
        }
        return count;
    }

    private int count = 0;

    public int numberOfSteps2(int num) {
        if (num != 0) {
            count++;
            if ((num & 1) != 0) {
                numberOfSteps(num & -2);
            } else {
                numberOfSteps(num >> 1);
            }
        }
        return count;
    }

    public int numberOfSteps4(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num = (num & 1) == 1 ? num & -2 : num >> 1;
        }
        return count;
    }
}
