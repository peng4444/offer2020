package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo983
 * @Author: pbj
 * @Date: 2020/4/26 09:30
 * @Description: TODO 983. 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 */
public class Demo983 {
    public int mincostTickets(int[] days, int[] costs) {
        // 将从新年到某一天的花过的所有钱数全部记录起来。
        int[] lastAllDaysCost = new int[366];
        //  days的下标，确保遍历365天时，以便于知道下次旅游的日期。
        int dayIdx = 0;
        // 日，月，年的花费。
        int ticketDay = costs[0];
        int ticketWeek = costs[1];
        int ticketMonth = costs[2];
        // 因为是第一天，所以过去的总花费为0
        lastAllDaysCost[0] = 0;
        // lastAllCost[i] 是截至到今年的第 i 天的总花费.

        // 模拟新年的第一天跑到旅行的最后一天。
        for (int today = 1; today <= 365; today++) {
            if(dayIdx >= days.length){
                break;
            }
            // 判断今天是否属于旅行日。
            if (days[dayIdx] != today) {
                // 如果这一天不旅行那么直接把上一天的过去总花费拿过来直接使用。
                lastAllDaysCost[today] = lastAllDaysCost[today - 1];
                continue;
            }
            // 开始等待下一个待旅行的日子到来。
            dayIdx++;
            // 如果一月前，买了月票，会不会更便宜？
            // 如果一周前，买了周票，会不会更便宜？
            // 如果都不会的话，那我暂时先买日票试试呗。
            lastAllDaysCost[today] = Math.min(
                    Math.min(
                            lastAllDaysCost[Math.max(0, today - 1)] + ticketDay
                            , lastAllDaysCost[Math.max(0, today - 7)] + ticketWeek)
                    , lastAllDaysCost[Math.max(0, today - 30)] + ticketMonth);
        }
        return lastAllDaysCost[days[days.length - 1]];
    }

    // 暴力递归:对每个旅行日可以买1日票，7日票，30日票。再改成记忆化搜索
    public int mincostTickets1(int[] days, int[] costs) {
        int[] dp = new int[366];
        return f(days, costs, 1, dp);
    }
    // 从st天开始，到最后一个旅行日，所需最小费用
    private int f(int[] days, int[] costs, int st, int[] dp){
        // 二分找到大于等于st的最小旅行日，从该旅行日开始
        int loc = bi(days, st);
        if(loc == -1){
            return 0;
        }
        if(dp[st] != 0){
            return dp[st];
        }
        // 1. 买1日票
        int cost1 = costs[0] + f(days, costs, days[loc]+1, dp);
        // 2. 买7日票
        int cost2 = costs[1] + f(days, costs, days[loc]+7, dp);
        // 3. 买30日票
        int cost3 = costs[2] + f(days, costs, days[loc]+30, dp);
        return dp[st] = Math.min(cost1, Math.min(cost2, cost3));
    }
    // 二分查找大于等于v的最小值的位置
    private int bi(int[] a, int v){
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while(l <= r){
            int mid = l + ((r-l)>>1);
            if(a[mid] >= v){
                res = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return res;
    }
}
