package cn.offer2020.pbj.javabasis.java.basis2.date_7;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @ClassName: JDK8Date
 * @Author: pbj
 * @Date: 2019/12/4 21:51
 * @Description: TODO jdk8对于时间的重新定义[参考资料](https://www.cnblogs.com/liqiangchn/p/11974355.html)
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

        //获取UTC时间
        Instant instant = Instant.now();
        System.out.println(instant);
        //获取北京时间(东八区)
        OffsetDateTime beijing = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(beijing);
        //获取毫秒数
        long milli = instant.toEpochMilli();
        //定义时间戳

    }
}
