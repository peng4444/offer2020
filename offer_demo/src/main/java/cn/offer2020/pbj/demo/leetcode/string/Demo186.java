package cn.offer2020.pbj.demo.leetcode.string;

/**
 * @ClassName: Demo186
 * @Author: pbj
 * @Date: 2020/3/25 15:27
 * @Description: TODO 186.翻转字符串里的单词II
 */
public class Demo186 {
    public void reverseWords(char[] s) {
        int start=0;
        for(int i=0;i<s.length;i++){
            if(s[i]==' '){
                reverseWord(s,start,i-1);
                start=i+1;
            }
        }
        reverseWord(s,start,s.length-1);
        reverseWord(s,0,s.length-1);
    }
    public void reverseWord(char[] s,int start,int end){
        char temp;
        while(start<end){
            temp=s[start];
            s[start]=s[end];
            s[end]=temp;
            start++;
            end--;
        }
    }
}
