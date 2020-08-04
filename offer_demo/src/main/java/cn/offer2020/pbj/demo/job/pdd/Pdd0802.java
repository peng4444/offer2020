package cn.offer2020.pbj.demo.job.pdd;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @pClassName: Pdd0802
 * @author: pengbingjiang
 * @create: 2020/8/3 14:43
 * @description: TODO 拼多多笔试 2020 08 02
 * [拼多多笔试 2020 08 02](https://www.nowcoder.com/discuss/465066)
 */
public class Pdd0802 {
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int distance = in.nextInt();
        int count = in.nextInt();
        if (distance == 0) {
            System.out.println("paradox");
        }
        int ansCount = 0;
        while (count-- > 0) {
            int num = in.nextInt();
            if (num < distance) {
                distance -= num;
            } else if (num > distance) {
                ansCount++;
                distance = num - distance;
            } else {
                distance -= ansCount;
                if (count > 0) {
                    System.out.println("paradox");
                    return;
                }
            }
        }
        System.out.println(distance + " " + ansCount);
    }

    public static void main(String[] args) {

    }
}
