package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo273
 * @Author: pbj
 * @Date: 2020/5/6 15:10
 * @Description: TODO 273. 整数转换英文表示
 * 将非负整数转换为其对应的英文表示。可以保证给定输入小于 2^31 - 1 。
 */
public class Demo273 {
    public String numberToWords(int num) {
        String[] one = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] two = new String[]{"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        if(num >= 1000000000){
            if(num % 1000000000 == 0)
                return numberToWords(num / 1000000000) + " Billion";
            return numberToWords(num / 1000000000) + " Billion " + numberToWords(num % 1000000000);
        }
        if(num >= 1000000){
            if(num % 1000000 == 0)
                return numberToWords(num / 1000000) + " Million";
            return numberToWords(num / 1000000) + " Million " + numberToWords(num % 1000000);
        }
        if(num >= 1000){
            if(num % 1000 == 0)
                return numberToWords(num / 1000) + " Thousand";
            return numberToWords(num / 1000) + " Thousand " + numberToWords(num % 1000);
        }
        if(num >= 100){
            if(num % 100 == 0)
                return numberToWords(num / 100) + " Hundred";
            return numberToWords(num / 100) + " Hundred " + numberToWords(num % 100);
        }
        if(num % 10 == 0)
            return two[num/10];
        if(num > 20)
            return numberToWords(num /10 *10) + " " + numberToWords(num % 10);
        return one[num];
    }
}
