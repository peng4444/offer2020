package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo7
 * @Author: pbj
 * @Date: 2019/8/30 11:18
 * @Description: TODO
 */
public class Demo7 {
    //利用除 10 取余的方法，将最低位和最高倒序输出即可
    public int reverseInteger(int n) {
        int reversed_n = 0;
        while (n != 0) {
            int temp = reversed_n * 10 + n % 10;
            n = n / 10;
            if (temp / 10 != reversed_n) {
                reversed_n = 0;
                break;
            }
            reversed_n = temp;
        }
        return reversed_n;
    }


    public static int reverse2(int x) {
        int flag = 0;
        if (x > 0) {
            flag = 1;
        } else if (x < 0) {
            flag = -1;
        }
        long y=flag*x;
        long ret = 0;
        while (y != 0) {
            ret=ret*10+(y%10);
            y = y/10;
        }
        return (int)ret*flag;
    }

    /* *
     * 功能描述: 弹出和推入数字 & 溢出前进行检查
     * @param: [x]
     * @return: int
     * @auther: pbj
     * @date: 2019/12/10 17:14
     */
    public int reverse3(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(reverse2(123));
        System.out.println(reverse2(-123));
        System.out.println(reverse2(120));
        System.out.println(reverse2(-120));
        System.out.println(reverse2(1534236469));
    }
    public static int reverse(int x) {
        String str = ""+x;
        char[] strChar = str.toCharArray();
        int i = 0;
        int l = strChar.length;
        char temp ;
        if(strChar[0]=='0'){
            return 0;
        } else if(strChar[0]>'9'||strChar[0]<'0'&&strChar[l-1]!='0'){
            for(i=1;i<((l-1)/2)+1;i++){
                temp = strChar[i];
                strChar[i] = strChar[l-i];
                strChar[l-i] = temp;
            }
        }else if(strChar[0]>'9'||strChar[0]<'0'&&strChar[l-1]=='0'){
            for(i=1;i<(l-2)/2+1;i++){
                temp = strChar[i];
                strChar[i] = strChar[l-1-i];
                strChar[l-1-i] = temp;
            }
            String str3 = new String(strChar);
            String s = str3.substring(0, str3.length() - 1);
            char[] strChar2 = s.toCharArray();
            strChar = strChar2;
        } else if(strChar[0]>='0'&&strChar[0]<='9'&&strChar[l-1]!='0'){
            for(i=0;i<l/2;i++){
                temp = strChar[i];
                strChar[i] = strChar[l-1-i];
                strChar[l-1-i] = temp;
            }
        }else if(strChar[0]>='0'&&strChar[0]<='9'&&strChar[l-1]=='0'){
            for(i=0;i<(l-1)/2;i++){
                temp = strChar[i];
                strChar[i] = strChar[l-2-i];
                strChar[l-2-i] = temp;
            }
            String str3 = new String(strChar);
            String s = str3.substring(0, str3.length() - 1);
            char[] strChar2 = s.toCharArray();
            strChar = strChar2;
        }
        String str2 = new String(strChar);
        long a = Integer.parseInt(str2);
        if (a < Integer.MIN_VALUE || a > Integer.MAX_VALUE) {
            return 0;
        }else {
            return (int) a;
        }
    }
}

