package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Demo89
 * @Author: pbj
 * @Date: 2020/4/3 11:49
 * @Description: TODO 89. 格雷编码
 */
public class Demo89 {

    public List<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < (1 << n); i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }

    //常规的回溯思想
    List<Integer> res = new ArrayList();
    boolean[] visited;
    public List<Integer> grayCode1(int n) {
        visited = new boolean[1<<n];
        dfs(0,n);
        return res;
    }

    boolean dfs(int cur,int n){
        if(res.size()==(1<<n))
            return true;
        res.add(cur);
        visited[cur]=true;
        for(int i=0;i<n;i++){
            int next = cur^(1<<i); //这里改变cur的某一位，用异或
            if(!visited[next]&&dfs(next,n))
                return true;
        }
        visited[cur] = false;
        return false;

    }
}
