package cn.offer2020.pbj.demo.leetcode.hard;

import java.util.*;

/**
 * @pClassName: Demo1235
 * @author: pengbingjiang
 * @create: 2020/7/14 09:28
 * @description: TODO 1235. 规划兼职工作
 */

public class Demo1235 {
    //dp
    public int jobScheduling(int[] startTime, int[] endTime, int[] profile) {
        int len = startTime.length;
        Job[] jobs = new Job[len];
        for(int i = 0; i < len; i++){
            Job job = new Job(startTime[i], endTime[i], profile[i]);
            jobs[i] = job;
        }
        Arrays.sort(jobs, (j1, j2) -> (j1.end - j2.end));
        int[] dp = new int[len];
        //初始化的利益就是只做当前这份工作的利益
        for (int i = 0; i < len; i++) {
            dp[i] = jobs[i].profit;
        }
        for (int i = 1; i < len; i++) {
            //1.先找出dp[i-1],和profit[i]的利益最大值。得出第一步dp[i]
            dp[i] = Math.max(dp[i - 1], jobs[i].profit);
            //2.再找出离i最近的不重合的j，取dp[j]+profit[i],第一步的dp[i]，两者之间的最大值
            for (int j = i - 1; j >= 0; j--) {
                //第一次找到j时得出的最大值，一定是dp[i]的最大值，
                // 再往更小的j取寻找得到的都不是最大值，所以此处break。
                if (jobs[j].end <= jobs[i].start) {
                    dp[i] = Math.max(dp[j] + jobs[i].profit, dp[i]);
                    break;
                }
            }
        }
        return dp[len - 1];
    }

    //深度优先遍历+二分搜索
    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(o -> o.start));
        int[] cache = new int[n];
        Arrays.fill(cache, -1);
        return dfs(jobs, 0, cache);
    }

    private int dfs(Job[] jobs, int index, int[] cache) {
        if (index >= jobs.length) return 0;
        if (cache[index] != -1) return cache[index];
        int ans = dfs(jobs, index + 1, cache);
        int next = binarySearch(jobs, index + 1, jobs.length - 1, jobs[index].end);
        ans = Math.max(ans, jobs[index].profit + dfs(jobs, next, cache));
        return cache[index] = ans;
    }

    private int binarySearch(Job[] jobs, int start, int end, int target) {
        int left = start;
        int right = end + 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int key = jobs[mid].start;
            if (key >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    //优先队列
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for(int i=0; i<n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        //startTime asc
        Arrays.sort(jobs, (a,b)->a[0]-b[0]);
        //  0- endTime asc;   1- profit dec;
        PriorityQueue<int[]> endQueue = new PriorityQueue<>((a, b)->{
            if(a[1] == b[1]) {
                return a[0]-b[0];
            }
            return b[1]-a[1];
        });

        for(int i=0; i<n; i++) {
            int[] cur = jobs[i];
            Queue<int[]> tmp = new LinkedList<>();
            //将不能叠加的区间先取出
            while(!endQueue.isEmpty() && endQueue.peek()[0] > cur[0]) {
                tmp.add(endQueue.poll());
            }
            //没有可以叠加的前序区间
            if(endQueue.isEmpty()) {
                tmp.add(new int[]{cur[1], cur[2]});
            } else {
                //前序区间叠加，endQueue.peek() 已经是profit最大，且endTime最小的了，剩下的都可以丢弃
                tmp.add(new int[]{cur[1], cur[2]+endQueue.peek()[1]});
                tmp.add(endQueue.peek());
                endQueue.clear();
            }
            while(!tmp.isEmpty()) {
                //  System.out.println(tmp.peek()[0]+","+tmp.peek()[1]);
                endQueue.add(tmp.poll());
            }
            //   System.out.println("-----");
        }
        return endQueue.peek()[1];
    }


    class Job{
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}
