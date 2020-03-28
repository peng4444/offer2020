package cn.offer2020.pbj.demo.leetcode.a_maths;

/**
 * @ClassName: Demo470
 * @Author: pbj
 * @Date: 2020/3/11 16:46
 * @Description: TODO 470. 用 Rand7() 实现 Rand10()
 */
public class Demo470 {


    //构造一个大于10的均匀分布. 然后从这个均匀分布中取值,并映射到10以内.
    //选取两个rand7的值构造一个二维的点阵. 点阵中共有49个点. 将最后9个点抛弃掉.然后将前40个点映射到10以内.
    public int rand10() {
        while(true){
            int r = ((rand7() - 1) * 7 + rand7());
            if(r <= 40) {return r%10+1;}
        }
    }

    //首先获取等概率的 1,2,3,4,5 (如果rand7()结果>5则抛弃重新来一次 根据对称性原理 1,2,3,4,5等概率)
    //再以50%的概率是否加上5 这样可以得到等概率的1-10
    public int rand10_1() {
        int result = rand7();
        while (result > 5) {
            result = rand7();
        }
        int temp = rand7();
        while (temp == 7) {
            temp = rand7();
        }
        return temp <= 3 ? result : result + 5;
    }

    public int rand7() {
        return 1;
    }
}
