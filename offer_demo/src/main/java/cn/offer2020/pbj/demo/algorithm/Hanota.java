package cn.offer2020.pbj.demo.algorithm;

import java.util.List;

/**
 * @ClassName: Hanota
 * @Author: pbj
 * @Date: 2020/5/6 14:27
 * @Description: TODO 《程序员面试经典第6版》面试题 08.06. 汉诺塔问题
 */
public class Hanota {


    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        for(int i = 0;i<A.size();i++){
            C.add(i,A.get(i));
        }
    }

    public static void hanota1(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(), A, B, C);
    }

    public static void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C){
        if(n == 1){
            move(A, C);
        }else{
            hanota(n-1, A, C, B);
            move(A, C);
            hanota(n-1, B, A, C);
        }
    }

    public static void move(List<Integer> from, List<Integer> to){
        int pop = from.remove(from.size()-1);
        to.add(pop);
        return;
    }
}
