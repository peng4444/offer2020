package cn.offer2020.pbj.demo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo621
 * @Author: pbj
 * @Date: 2020/6/10 10:00
 * @Description: TODO 621. 任务调度器
 */
public class Demo621 {
    //桶排序
    public int leastInterval1(char[] tasks, int n) {
        HashMap<Character, Integer> task_map = new HashMap<>();
        int max_count = 0;
        int difference = 0;
        for (Character task : tasks) {
            int count = task_map.getOrDefault(task,0)+1;
            task_map.put(task, count);
            max_count = Math.max(max_count, count);
        }
        for (Map.Entry<Character, Integer> entry : task_map.entrySet()) {
            if(entry.getValue()==max_count) difference++;
        }
        int number1 = (n+1)*(max_count-1) + difference;
        int number2 = tasks.length;
        return Math.max(number1, number2);
    }
    //排序
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for(int i = 0;i<tasks.length;i++){
            count[tasks[i]-'A']++;
        }
        Arrays.sort(count);
        int maxCount = 0;
        for(int i = 25;i>=0;i--){
            if(count[i]!=count[25]){
                break;
            }
            maxCount++;
        }
        return Math.max((count[25]-1)*(n+1)+maxCount,tasks.length);
    }
}
