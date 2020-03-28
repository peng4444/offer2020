package cn.offer2020.pbj.javabasis.java.basis1.other_21;

public class DataPackageDemo {


    public static void main(String[] args) {
        /*
         * 基本数据类型包装类
         * 对象型包装类：character , Boolean
         * 数值类型包装类：Byte，Short, Integer, Folat ,Double ,Long,
         * 操作方法：intValue(),doubleValue(),folatValue(),byteValue(),shortValue(),longValue()
         */
        //int Integer
        Integer obj = new Integer(10);//基本数据类型装箱
        int temp = obj.intValue();//基本数据类型拆箱
        System.out.println(temp * 2);
        //JDK 1.5之后自动装箱和拆箱
        Integer obi = 10;//自动装箱
        int tep = obi;//自动拆箱
        obi++;
        System.out.println(temp * obi);
        /*
         * 字符串与基本数据类型
         * Integer类： public static int parseInt(String s)
         * Double类：public static double parseDouble(String s)
         * Boolean类: public static boolean parseBoolean(String s)
         */
        String str = "1234";
        int te = Integer.parseInt(str);
        System.out.println(te * 2);
    }

}
