package cn.offer2020.pbj.demo.leetcode.maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @pClassName: Demo989
 * @author: pengbingjiang
 * @create: 2020/8/3 16:52
 * @description: TODO
 */
public class Demo989 {
    public List<Integer> addToArrayForm(int[] A, int K) {
        int len = A.length;
        int lastNum =K;
        LinkedList<Integer> ret= new LinkedList<>();

        int i = len-1;
        while(i >=0 || lastNum > 0){
            if(i >= 0){
                lastNum+=A[i];
            }
            ret.addFirst(lastNum%10);
            lastNum/=10;
            i--;
        }

        return ret;
    }

    public List<Integer> addToArrayForm1(int[] A, int K) {
        int N = A.length;
        int cur = K;
        List<Integer> ans = new ArrayList();

        int i = N;
        while (--i >= 0 || cur > 0) {
            if (i >= 0)
                cur += A[i];
            ans.add(cur % 10);
            cur /= 10;
        }

        Collections.reverse(ans);
        return ans;
    }
}
