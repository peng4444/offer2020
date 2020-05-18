package cn.offer2020.pbj.demo.algorithm;

/**
 * @ClassName: KMPDemo
 * @Author: pbj
 * @Date: 2020/5/18 16:51
 * @Description: TODO 串、KMP模式匹配算法 https://www.cnblogs.com/Joey777210/p/11973279.html
 */
public class KMPDemo {

    //KMP
    public static int index_KMP(String main, String arg) {
        int i = 0;
        int j = 0;
        int[] next = getNext(arg);
        while (i < main.length() && j < arg.length()) {
            if (j == -1 ||
                    main.charAt(i) == arg.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= arg.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private static int[] getNextval(String arg) {
        int[] nextval = new int[arg.length() + 1];
        nextval[0] = -1;
        int i = 0;//模式串中当前匹配到的位置的下标值
        int j = -1;//子串匹配前缀最后一个位置的下一个元素的下标
        while (i < arg.length()) {
            if (j == -1 || arg.charAt(i) == arg.charAt(j)) {
                i++;
                j++;
                if (arg.charAt(i) != arg.charAt(j)){
                    nextval[i] = j;
                }else {
                    nextval[i] = nextval[j];
                }
            } else {//若上一轮匹配到的前j个元素加上这轮新来的一个元素现在不能满足这么长的前缀等于后缀，则找到之前求得的next[j]，说明当前满足的前缀要变短才能匹配。
                j = nextval[j];
            }
        }
        return nextval;
    }

    private static int[] getNext(String arg) {
        int[] next = new int[arg.length() + 1];
        next[0] = -1;
        int i = 0;//模式串中当前匹配到的位置的下标值
        int j = -1;//子串匹配前缀最后一个位置的下标，-1表示没有子串，即已经回退到了0位置
        while (i < arg.length()) {
            if (j == -1 || arg.charAt(i) == arg.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {//若上一轮匹配到的前j个元素加上这轮新来的一个元素现在不能满足这么长的前缀等于后缀，则找到之前求得的next[j]，说明当前满足的前缀要变短才能匹配。
                j = next[j];
            }
        }
        return next;
    }

    //一个循环+角标回溯的方法 时间复杂度很高
    public static int index(String main, String arg, int pos){
        int i = pos;  //控制main的角标
        int j = 0;  //控制arg的角标
        while(i < main.length() && j < arg.length()){
            if (main.charAt(i) == arg.charAt(j)){
                i++;
                j++;
            }else{
                i = i - j + 1;
                j = 0;
            }
        }
        if (j >= arg.length()){
            return i - j;
        }else {
            return -1;
        }
    }
    /*
    朴素模式匹配就是对主串的每一个字符作为子串开头，与要匹配的字符串进行匹配。对主串做大循环，每个字符开头做小循环，直到匹配成功或全部遍历完成为止。
    外层控制主串，内层控制匹配串
    如果内层匹配到最后一个位置了还没有break，则说明匹配成功
    */
        public static int index(String main, String arg){
            int index = -1;
            for (int i = 0; i < (main.length() - arg.length() + 1); i++){
                for (int j = 0; j < arg.length(); j++){
                    if (main.charAt(i + j) != arg.charAt(j)){
                        break;
                    }else if (j == arg.length()-1){
                        index = i;
                    }
                }
            }
            if (index == -1){
                return -1;
            }
            return index;
        }
}
