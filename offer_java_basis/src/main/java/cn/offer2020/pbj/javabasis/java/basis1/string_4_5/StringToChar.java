package cn.offer2020.pbj.javabasis.java.basis1.string_4_5;

/*
* java可以使用两种方法直接将字符数组转为字符串
* 方法1：直接在构造String时转换。
char[] data = {‘a’, ‘b’, ‘c’};
String str = new String(data);
* 方法2：调用String类的方法转换。
String.valueOf(char[] ch)
*
* java可以使用两种方法直接将字符串转为字符数组
* 情况一：如果是有分隔符的那种例如”abc,def,ghi”;就直接分割就行了.
String string = “abc,def,ghi”;
String [] strArr= string.split(“,”); //注意分隔符是需要转译
* 情况二：如果是”abcdefghijk”这种字符串,就直接
String string1 = “abcdefghijk” ;
char [] strArr1 = string1.toCharArray(); //注意返回值是char数组
*/

public class StringToChar {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 将字符数组转为字符串
        // 方法一
        char[] data = { 'a', 'b', 'c' };// 字符数组
        String str = new String(data);
        System.out.println(str);
        // 方法二
        String str1 = String.valueOf(data);
        System.out.println(str1);

        // 将字符串转为字符串数组
        // 方法一
        String string = "abc,def,ghi";
        String[] strArr = string.split(",");//注意分隔符是需要转译
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
        }
        // 方法二
        String string1 = "abcdefghijk";
        char[] strArr1 = string1.toCharArray();
        for (int i = 0; i < strArr1.length; i++) {
            System.out.println(strArr1[i]);
        }

    }

}

