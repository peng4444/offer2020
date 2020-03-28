package cn.offer2020.pbj.demo.job.tencent;

import org.junit.Test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @ClassName: QSong
 * @Author: pbj
 * @Date: 2020/3/3 15:53
 * @Description: TODO 腾讯 牛客网 小Q的歌单
 * https://www.nowcoder.com/questionTerminal/f3ab6fe72af34b71a2fd1d83304cbbb3
 */
public class QSong {
    public int XiaoQSongList(int len, int[] arr) {
        HashMap<Integer, Integer> songList = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < arr.length; i = i + 2) {
            songList.put(arr[i], arr[i + 1]);
        }
        for (int i = 0; i < arr.length; i = i + 2) {
            if (!songList.containsKey(len - arr[i])) {
                continue;
            } else if (songList.containsKey(len - arr[i]) && (len - arr[i] != arr[i])) {
                ans = ans + songList.get(arr[i]) * songList.get(arr[len - arr[i]]);
                songList.remove(arr[i]);
            } else if (songList.containsKey(len - arr[i]) && (len - arr[i] == arr[i])) {
                ans = ans + help(arr[i]);
            }
        }
        return ans;
    }

    public int help(int x) {
        if (x <= 1) return 0;
        if (x == 2) return 1;
        return help(x - 1) + (x - 1);
    }

    @Test
    public void test() {
        int[] arr = new int[]{2, 3, 3, 3};
        System.out.println(XiaoQSongList(5, arr));
        System.out.println("---------");
        int[] arr2 = new int[]{2,3,5,5,8,2};
        System.out.println(XiaoQSongList(10,arr2));
    }

    public static final int ASD = 1000000007;
    //动态规划，模仿背包问题，问题简化为有x+y种物品，其中x种的容积为a，y种的容积为b，背包容积为k，问背包装满一共有多少种解法？
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int a = sc.nextInt(), x = sc.nextInt();
        int b = sc.nextInt(), y = sc.nextInt();
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < x; i++) {
            for (int j = k; j >= a; j--) {
                dp[j] = (dp[j] + dp[j - a]) % ASD;
            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = k; j >= b; j--) {
                dp[j] = (dp[j] + dp[j - b]) % ASD;
            }
        }
        System.out.println(dp[k]);
        sc.close();
    }
}
