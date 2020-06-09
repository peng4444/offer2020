package cn.offer2020.pbj.javabasis.java.basis1.enum_29;

/**
 * @ClassName: Season
 * @Author: pbj
 * @Date: 2020/6/9 09:05
 * @Description: TODO 自定义季节的枚举类
 */
public class Season {
    //声明Season对象的属性,为private final修饰
    private final String seasonName;

    //私有化构造器,并为对象赋值
    private Season(String seasonName) {
        this.seasonName = seasonName;
    }

    //提供当前枚举的多个对象,为public static final修饰
    public static final Season SPRING = new Season("春天");
    public static final Season SUMMER = new Season("夏天");
    public static final Season AUTUMN = new Season("秋天");
    public static final Season WINTER = new Season("冬天");

    //提供外界通过getter方法来获取枚举对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    //重写toString方法,以便打印出枚举结果
    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                '}';
    }
}
