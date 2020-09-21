package cn.offer2020.pbj.demo;

import java.util.Random;
import java.util.Scanner;

/**
 * @pClassName: Demo0920
 * @author: pengbingjiang
 * @create: 2020/9/20 16:07
 * @description: TODO
 */
public class Demo0920 {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int searchType = sc.nextInt();
        int totalSize = sc.nextInt();
        int[][] nums = new int[totalSize][2];
        for (int i = 0; i < totalSize; i++) {
            String str = sc.next();
            String[] strs = str.split("\\,");
            nums[i][0] = Integer.valueOf(strs[0]);
            nums[i][1] = Integer.valueOf(strs[1]);
        }
        String searchStr = sc.next();
        String[] searchStrs = searchStr.split("\\,");
        int start = Integer.valueOf(searchStrs[0]);
        int end = Integer.valueOf(searchStrs[1]);
        int[][] ans;
        if (searchType == 1) {
            ans = help1(nums, start, end);
        } else {
            ans = help2(nums, start, end);
        }
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i][0] + "," + ans[i][1]);
        }
    }

    public static int[][] help1(int[][] nums, int start, int end) {
        int[][] ans = new int[nums.length][2];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i][0] >= start && nums[i][1] <= end) {
                ans[index][0] = nums[i][0];
                ans[index][1] = nums[i][1];
                index++;
            }
        }
        return ans;
    }

    public static int[][] help2(int[][] nums, int start, int end) {
        int[][] ans = new int[nums.length][2];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i][0] <= start && nums[i][1] >= start) || (nums[i][0] <= end && nums[i][1] >= end) || (nums[i][0] >= start && nums[i][1] <= end)) {
                ans[index][0] = nums[i][0];
                ans[index][1] = nums[i][1];
                index++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        String[] strs = new String[m];
        int[][] nums = new int[m][2];
        for (int i = 0; i < m; i++) {
            strs[i] = sc.next();
            String[] strs2 = strs[i].split("\\,");
            nums[i][0] = Integer.valueOf(strs2[0]);
            nums[i][1] = Integer.valueOf(strs2[1]);
        }
        int[][] temp = nums;
        for (int i = 0; i < 10000; i++) {
            if (check(temp)) {//重新赋值
                temp = nums;
            }
            Random random = new Random();
            int index = random.nextInt(m);
            while (true) {
                if (temp[index][1] > 0) {
                    System.out.println(temp[index][0]);
                    temp[index][1]--;
                    break;
                } else {
                    index = random.nextInt(m);
                }
            }
        }
    }

    public static boolean check(int[][] temp) {
        for (int i = 0; i < temp.length; i++) {
            if (temp[i][1] != 0) {
                return false;
            }
        }
        return true;
    }
}
