package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo12
 * @Author: pbj
 * @Date: 2020/5/21 10:42
 * @Description: TODO 12. 整数转罗马数字
 */
public class Demo12 {
    public String intToRoman(int num) {
        int values[] = {1000,900,500,400,100,90,50,40,10,9,5,4,1,0};
        String reps[] = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder  res = new StringBuilder();
        for(int i =0; i<13;i++){
            while(num>=values[i]){
                num -= values[i];
                res.append(reps[i]);
            }
        }
        return res.toString();
    }

    public String intToRoman1(int num) {

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }
}
