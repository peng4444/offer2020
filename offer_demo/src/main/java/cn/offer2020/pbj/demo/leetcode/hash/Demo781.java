package cn.offer2020.pbj.demo.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Demo781
 * @Author: pbj
 * @Date: 2020/5/7 11:11
 * @Description: TODO 781. 森林中的兔子
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在answers数组里。
 * 返回森林中兔子的最少数量。
 */
public class Demo781 {
    public int numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<answers.length;i++){
            if(map.containsKey(answers[i])&&map.get(answers[i])>0){
                map.put(answers[i],map.get(answers[i])-1);
            }else{
                ans = ans + answers[i] + 1;
                map.put(answers[i],answers[i]);
            }
        }
        return ans;
    }

    public int numRabbits1(int[] answers) {
        int count = 0;
        int[] nums = new int[1000];
        for (int i : answers) {
            if (nums[i] == 0 || nums[i] % (i + 1) == 0) {
                count += i + 1;
            }
            nums[i]++;
        }
        return count;
    }

    //先对数组排序（这样相同数字就挨在一起了），从前往后遍历，对于每个回答ansers[i]+1记为k就是这种颜色兔子的数量,
    // 并且往后最多可以容纳k个和answers[i]相等的数(包括ansers[i])。
    public int numRabbits2(int[] answers) {
        int n = answers.length;
        if(n < 1) return 0;
        int res = 0, max = 0; //max表示最对允许的相同数字
        Arrays.sort(answers);
        for(int i = 0; i < n; i++){
            res += answers[i] + 1;
            max = answers[i] + 1;
            int k = answers[i], start = i;
            while(i < n && k == answers[i] && i - start < max)i++;  //往后过滤max个相同数字
            i--;
        }
        return res;
    }
}
