package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo58
 * @Author: pbj
 * @Date: 2020/5/11 08:43
 * @Description: TODO 58.最后一个单词的长度
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * 如果不存在最后一个单词，请返回 0 。
 */
public class Demo58 {
    public static int lengthOfLastWord(String s) {
        char[] c = s.toCharArray();
        int ans = 0;
        for(int i = c.length-1;i>=0;i--){
            if(c[i] != ' '){
                ans++;
            }else if(ans!=0){
                return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        lengthOfLastWord("a ");
    }
    //从右向左遍历，从第一个不是空格的字符开始计数，一旦开始计数，再遇到空格就结束了
    public int lengthOfLastWord1(String s) {
        if(s == null || s.length() == 0) return 0;
        int count = 0;
        for(int i = s.length()-1; i >= 0; i--){
            if(s.charAt(i) == ' '){
                if(count == 0) continue;
                break;
            }
            count++;
        }
        return count;
    }
}
