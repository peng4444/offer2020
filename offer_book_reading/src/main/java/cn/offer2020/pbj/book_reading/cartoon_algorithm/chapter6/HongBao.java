package cn.offer2020.pbj.book_reading.cartoon_algorithm.chapter6;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName: HongBao
 * @Author: pbj
 * @Date: 2020/3/19 16:31
 * @Description: TODO
 */
public class HongBao {
    //二倍均值法
    /*** 拆分红包* @param totalAmount 总金额（以分为单位）* @param totalPeopleNu总人数*/
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            //随机范围：[1，剩余人均金额的2倍-1] 分
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(1000, 10);
        for (Integer amount : amountList) {
            System.out.println(" 抢到金额：" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }

    //线段切割法

}
