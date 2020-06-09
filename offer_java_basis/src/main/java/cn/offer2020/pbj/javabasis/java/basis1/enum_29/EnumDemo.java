package cn.offer2020.pbj.javabasis.java.basis1.enum_29;

/**
 * @ClassName: EnumDemo
 * @Author: pbj
 * @Date: 2020/6/9 09:00
 * @Description: TODO 为女朋友买哪个杯型的珍珠奶茶(默认大杯)
 */
public class EnumDemo {
    public static void main(String[] args) {
        //创建大杯的珍珠奶茶对象
        PearlMilkTea pearlMilkTea = PearlMilkTea.LARGE;
        EnumDemo.drinkSize(pearlMilkTea);
    }

    //判断为女朋友买哪个杯型的珍珠奶茶
    public static void drinkSize(PearlMilkTea pearlMilkTea) {
        if (pearlMilkTea == PearlMilkTea.LARGE) {
            System.out.println("我为女朋友买了一大杯珍珠奶茶！");
        } else if (pearlMilkTea == PearlMilkTea.MEDIUM) {
            System.out.println("我为女朋友买了一中杯珍珠奶茶！");
        } else {
            System.out.println("我为女朋友买了一小杯珍珠奶茶！");
        }
    }
}
