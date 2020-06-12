package cn.offer2020.pbj.javabasis.java.basis2.date_7;

import java.time.*;
import java.util.Date;

/**
 * @ClassName: JDK8Date
 * @Author: pbj
 * @Date: 2019/12/4 21:51
 * @Description: TODO jdk8对于时间的重新定义[参考资料](https://www.cnblogs.com/liqiangchn/p/11974355.html)
 *         [JDK8中日期类型该如何使用？](https://www.cnblogs.com/zwwhnly/p/13097475.html)
 */
public class JDK8Date {
    /* *
     * 功能描述: jdk8以前 Date表示时间和日期，不支持时间日期计算操作，不支持国际化
     * Calender 表示时间和日期，支持日期和时间计算操作，不支持格式化
     * SimpleDateFormat:仅仅支持格式化Date
     * JDK1.8设计完善，分工明确，所有类都是不可变的，线程安全，兼容旧的日期时间
     * LocalDateTime:日期和时间
     * LocalDate:仅表示日期
     * LocalTime:仅表示时间
     * Peroid：仅表示日期间隔
     * Duration:时间间隔，以秒和纳秒为单位
     * DateTimeFormatter:可以格式化LocalDate，LocalTime,LocalDateTime
     * @param: [args]
     * @return: void
     * @auther: pbj
     * @date: 2019/12/4 21:53
     */
    public static void main(String[] args) {
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //获取指定时间
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 4, 23, 59, 59);
        System.out.println(dateTime);
        //日期时间加减操作
        LocalDateTime dateTime1 = dateTime.plusDays(1).plusHours(1);
        System.out.println(dateTime1);
        //获取指定的字段(年月日时分秒，纳秒)
        System.out.println("现在是："+now.getYear()+"年中的第"+now.getDayOfYear()+"天");

        //LocalDate  获取当前日期 获取年月日
        LocalDate today = LocalDate.now();

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.println("year: " + year);
        System.out.println("month: " + month);
        System.out.println("day: " + day);
        //指定日期
        LocalDate specifiedDate = LocalDate.of(2020, 6, 1);
        System.out.println("specifiedDate: " + specifiedDate);
        LocalDate specifiedDate1 = LocalDate.of(2020, Month.JUNE, 1);
        //比较日期是否相等
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.of(2020, 6, 10);
        if (localDate1.equals(localDate2)) {
            System.out.println("localDate1 equals localDate2");
        }
        //获取日期是本周/本月/本年的第几天
        System.out.println("Today:" + today);
        System.out.println("Today is:" + today.getDayOfWeek());
        System.out.println("今天是本周的第" + today.getDayOfWeek().getValue() + "天");
        System.out.println("今天是本月的第" + today.getDayOfMonth() + "天");
        System.out.println("今天是本年的第" + today.getDayOfYear() + "天");
        //判断是否为闰年
        System.out.println(today.getYear() + " is leap year:" + today.isLeapYear());

        //LocalTime 获取时分秒
        Date date = new Date();

        int hour = date.getHours();
        int minute = date.getMinutes();
        int second = date.getSeconds();

        System.out.println("hour: " + hour);
        System.out.println("minute: " + minute);
        System.out.println("second: " + second);
        //获取UTC时间
        Instant instant = Instant.now();
        System.out.println(instant);
        //获取北京时间(东八区)
        OffsetDateTime beijing = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(beijing);
        System.out.println(instant.atZone(ZoneId.systemDefault()));//如果要输出北京时间，可以加上默认时区：
        //获取毫秒数
        long milli = instant.toEpochMilli();
        long epochSecond = instant.getEpochSecond();
        //定义时间戳

    }
}
