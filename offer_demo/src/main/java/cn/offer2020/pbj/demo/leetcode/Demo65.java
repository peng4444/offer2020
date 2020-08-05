package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo65
 * @Author: pbj
 * @Date: 2020/5/20 10:44
 * @Description: TODO 65.有效数字
 * 验证给定的字符串是否可以解释为十进制数字。
 */
public class Demo65 {
    class Solution {
        public int make(char c) {
            switch(c) {
                case ' ': return 0;
                case '+':
                case '-': return 1;
                case '.': return 3;
                case 'e': return 4;
                default:
                    if(c >= 48 && c <= 57) return 2;
            }
            return -1;
        }

        public boolean isNumber(String s) {
            int state = 0;
            int finals = 0b101101000;
            int[][] transfer = new int[][]{{ 0, 1, 6, 2,-1},
                    {-1,-1, 6, 2,-1},
                    {-1,-1, 3,-1,-1},
                    { 8,-1, 3,-1, 4},
                    {-1, 7, 5,-1,-1},
                    { 8,-1, 5,-1,-1},
                    { 8,-1, 6, 3, 4},
                    {-1,-1, 5,-1,-1},
                    { 8,-1,-1,-1,-1}};
            char[] ss = s.toCharArray();
            for(int i=0; i < ss.length; ++i) {
                int id = make(ss[i]);
                if (id < 0) return false;
                state = transfer[state][id];
                if (state < 0) return false;
            }
            return (finals & (1 << state)) > 0;
        }
    }

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.trim();
        if (s.matches("^(\\-|\\+)?[0-9]+$")) {
            return true;
        }
        if (s.matches("^(\\-|\\+)?(0)?\\.[0-9]+$")) {
            return true;
        }
        if (s.matches("^(\\-|\\+)?[0-9]+\\.[0-9]*$")) {
            return true;
        }
        if (s.matches("^(\\-|\\+)?[0-9]*(\\.)?[0-9]+e(\\-|\\+)?[0-9][0-9]*$")) {
            return true;
        }
        if (s.matches("^(\\-|\\+)?[0-9]+(\\.)?[0-9]*e(\\-|\\+)?[0-9][0-9]*$")) {
            return true;
        }
        return false;
    }

    class Solution1 {
        public boolean isNumber(String s) {
            int left_blank = 0;
            int right_blank = s.length() - 1;
            while (left_blank < s.length() && s.charAt(left_blank) == ' ') {
                left_blank ++;
            }
            while (right_blank >= 0 && s.charAt(right_blank) == ' ') {
                right_blank --;
            }
            if (left_blank > right_blank) return false;
            s = s.substring(left_blank, right_blank + 1);
            int counte = 0;
            int countdot = 0;
            int eP = 0;
            int dotP = 0;
            for (int i=0; i<s.length(); i++) {
                if (s.charAt(i) == 'e') {
                    counte ++;
                    eP = i;
                }
                if (s.charAt(i) == '.') {
                    countdot ++;
                    dotP = i;
                }
            }
            if (counte >= 2 || countdot >= 2) return false;
            String integerPart = "";
            String decimalPart = "";
            String expPart = "";
            if (countdot == 1 && counte == 1) {
                if (dotP > eP) return false;
                integerPart = s.substring(0, dotP);
                decimalPart = s.substring(dotP+1, eP);
                expPart = s.substring(eP+1, s.length());
                if (!integerPart.isEmpty() && !isIntegerPart(integerPart)) return false;
                if (!decimalPart.isEmpty() && !isDecimalPart(decimalPart)) return false;
                if (integerPart.isEmpty() && decimalPart.isEmpty()) return false;
                if ((integerPart.equals("+") || integerPart.equals("-")) && decimalPart.isEmpty()) return false;
                if (expPart.isEmpty() || !isExpPart(expPart)) return false;
            } else if (countdot == 1) {
                integerPart = s.substring(0, dotP);
                decimalPart = s.substring(dotP+1, s.length());
                if (!integerPart.isEmpty() && !isIntegerPart(integerPart)) return false;
                if (!decimalPart.isEmpty() && !isDecimalPart(decimalPart)) return false;
                if (integerPart.isEmpty() && decimalPart.isEmpty()) return false;
                if ((integerPart.equals("+") || integerPart.equals("-")) && decimalPart.isEmpty()) return false;
            } else if (counte == 1) {
                integerPart = s.substring(0, eP);
                expPart = s.substring(eP+1, s.length());
                if (integerPart.isEmpty() || !isIntegerPart(integerPart)) return false;
                if (expPart.isEmpty() || !isExpPart(expPart)) return false;
                if (integerPart.equals("+") || integerPart.equals("-")) return false;
            } else {
                if (!isIntegerPart(s)) return false;
            }
            return true;
        }

        public boolean isIntegerPart(String s) {
            if (s.charAt(0) != '+' && s.charAt(0) != '-' && !Character.isDigit(s.charAt(0))) {
                return false;
            }
            for (int i=1; i<s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        public boolean isDecimalPart(String s) {
            for (int i=0; i<s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        public boolean isExpPart(String s) {
            if (s.charAt(0) != '-' && s.charAt(0) != '+' && !Character.isDigit(s.charAt(0))) {
                return false;
            }
            if (s.equals("-") || s.equals("+")) return false;
            for (int i=1; i<s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}
