package cn.offer2020.pbj.demo.leetcode;

/**
 * @ClassName: Demo520
 * @Author: pbj
 * @Date: 2020/5/20 11:21
 * @Description: TODO 520. 检测大写字母
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *  全部字母都是大写，比如"USA"。
 *  单词中所有字母都不是大写，比如"leetcode"。
 *  如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 */
public class Demo520 {
    public boolean detectCapitalUse(String word) {
        //计算字符串中的大写字母的个数
        //1.全部为大写字母，个数为字符串长度。
        //2.全部为小写字母
        //3.只含有一个大写字母，并且为首字母
        char[] chars = word.toCharArray();
        int size = 0;
        for(int i = 0;i<chars.length;i++){
            if(chars[i]>='A'&&chars[i]<='Z'){
                size++;
            }
        }
        if(size==chars.length||size==0){
            return true;
        }
        if(size==1&&chars[0]>='A'&&chars[0]<='Z'){
            return true;
        }
        return false;
    }
}
