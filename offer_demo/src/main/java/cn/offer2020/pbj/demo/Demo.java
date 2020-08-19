package cn.offer2020.pbj.demo;

/**
 * @pClassName: Demo
 * @author: pengbingjiang
 * @create: 2020/8/19 11:27
 * @description: TODO
 */
public class Demo {

    public static void main(String[] args) {
        String str1 = "通话";
        String str2 = "重地";
        System.out.println(String.format("str1:%d|str2:%d",str1.hashCode(),str2.hashCode()));
        //str1:1179395|str2:1179395
        System.out.println(str1.equals(str2));
        //false
    }
}
