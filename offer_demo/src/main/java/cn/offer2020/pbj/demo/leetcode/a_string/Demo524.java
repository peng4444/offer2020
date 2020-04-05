package cn.offer2020.pbj.demo.leetcode.a_string;

import java.util.List;

/**
 * @ClassName: Demo524
 * @Author: pbj
 * @Date: 2020/4/5 18:30
 * @Description: TODO
 */
public class Demo524 {

    //双指针
    public String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for(String target:d){
            int l1 = longestWord.length(),l2 = target.length();
            if(l1>l2||(l1==l2&&longestWord.compareTo(target)<0)){
                continue;
            }
            if(isSubStr(s,target)){
                longestWord = target;
            }
        }
        return longestWord;
    }
    public boolean isSubStr(String s,String target){
        int i = 0,j=0;
        while(i<s.length()&&j<target.length()){
            if(s.charAt(i)==target.charAt(j)){
                j++;
            }
            i++;
        }
        return j==target.length();
    }

    //遍历求解
    public String findLongestWord1(String s, List<String> d) {
        String str="";
        for(String sstr:d){
            for(int i=0,j=0;i<s.length()&&j<sstr.length();i++){
                if(s.charAt(i)==sstr.charAt(j)) j++;
                if(j==sstr.length()){
                    if(sstr.length()>str.length()||(sstr.length()==str.length()&&str.compareTo(sstr)>0))  str=sstr;
                }
            }
        }
        return str;
    }
}
