package cn.offer2020.pbj.demo.leetcode.bit;

/**
 * @ClassName: Demo318
 * @Author: pbj
 * @Date: 2020/4/19 14:51
 * @Description: TODO 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 */
public class Demo318 {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] val = new int[n];
        for(int i = 0;i<n;i++){
            for(char c :words[i].toCharArray()){
                val[i] |= 1<<(c-'a');
            }
        }
        int ret = 0;
        for(int  i=0;i<n;i++){
            for(int j = i+1;j<n;j++){
                if((val[i]&val[j])==0){
                    ret = Math.max(ret,words[i].length() * words[j].length());
                }
            }
        }
        return ret;
    }
}
