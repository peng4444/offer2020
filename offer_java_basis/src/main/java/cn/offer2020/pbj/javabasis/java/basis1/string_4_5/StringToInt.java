package cn.offer2020.pbj.javabasis.java.basis1.string_4_5;

/**
 * (1) int类型转换成String类型
 * 第一种方法：s=i+"";???//会产生两个String对象
 * 第二种方法：s=String.valueOf(i); //直接使用String类的静态方法，只产生一个对象
 *  (2)  String类型转换成int类型
 * 第一种方法：i=Integer.parseInt(s);//直接使用静态方法，不会产生多余的对象，但会抛出异常
 * 第二种方法：i=Integer.valueOf(s).intValue();//Integer.valueOf(s) 相当于 new Integer(Integer.parseInt(s))，
 * 也会抛异常，但会多产生一个对象
 */
public class StringToInt {
    public static void main(String args[]){
        //(1) int类型转换成String类型
        int numA = 123;
        String strA = "" + numA;
        String strE = Integer.toString(numA);
        System.out.println(strA+":"+strA.getClass().getName());
        System.out.println(strE+":"+strE.getClass().getName());
        //(2) int类型转换成String类型
        int numB = 123;
        String strB  = String.valueOf(numB);
        System.out.println(strB+":"+strB.getClass().getName());
        //(3) String类型转换成int类型
        String strC = "123";
        int numC = Integer.parseInt(strC);
        System.out.println(numC);
		/*System.out.println(numC.getClass().getName());
		基本数据类型无法使用此判断数据类型
		*/
        //(4) String类型转换成int类型
        String strD = "123";
        int numD = Integer.valueOf(strD).intValue();
        System.out.println(numD);
		/*
		(1):strA="" + numA;???//会产生两个String对象
		(2):strB=String.valueOf(numB); //直接使用String类的静态方法，只产生一个对象
		(3):numc=Integer.parseInt(strC);//直接使用静态方法，不会产生多余的对象，但会抛出异常
		(4):numD=Integer.valueOf(strD).intValue();//Integer.valueOf(s) 相当于 new Integer(Integer.parseInt(s))，
		也会抛异常，但会多产生一个对象
		*/
        //int数组和String数组的转换
        // (5)int数组 --> String数组
        int num1[] =new int[]{1,2,3,4};
        String str1[] = new String[num1.length];
        for(int i = 0;i < num1.length;i++){
            //str1[i] = num1[i].ToString();
            str1[i]  = String.valueOf(num1[i]);
        }
        for(int i = 0;i < str1.length ;i++ ){
            System.out.println(str1[i]);
        }
        //(6)String数组 --> int数组
        String str2[] =new String []{"123","231","321"};
        int num2[] = new int[str2.length];
        for(int i = 0;i < str2.length;i++){
            num2[i] = Integer.parseInt(str2[i]);
        }
        for(int i = 0;i < num2.length ;i++ ){
            System.out.println(num2[i]);
        }
    }
}
