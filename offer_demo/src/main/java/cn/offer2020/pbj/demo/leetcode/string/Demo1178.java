package cn.offer2020.pbj.demo.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @pClassName: Demo1178
 * @author: pengbingjiang
 * @create: 2020/7/23 11:40
 * @description: TODO
 */
public class Demo1178 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        boolean isTrue = true;
        ArrayList<Integer> list = new ArrayList<>();

        for (String s : puzzles) {
            int count = 0;
            for (String word : words) {
                isTrue = true;
                if (word.startsWith(s.substring(0, 1))) {
                    for (int i = 1; i < word.length(); i++) {
                        if (s.indexOf(word.substring(i, i + 1)) == -1) {
                            isTrue = false;
                            break;
                        }
                    }
                    if (isTrue) count++;
                }
            }
            list.add(count);
        }
        return list;
    }

    public List<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {
        //变量声明定义
        List<Integer>res = new ArrayList<Integer>(); //用于存储答案
        int WordCount = words.length, PuzzlesCount = puzzles.length, count = 0, Tempdata;
        HashMap<Integer,Integer> Hashmap = new HashMap<Integer, Integer>();  //存放words的哈希表

        //遍历单词，将words的所有单词通过二进制转换为int类型数字存储在hash表里（相同key时value增加计数）
        for(String tempString : words){
            Tempdata = 0;
            for(char tempchar : tempString.toCharArray()){
                Tempdata |= 1 << ('z' - tempchar); //或运算转化为int类型
            }
            if(Hashmap.containsKey(Tempdata)){
                //判断hashmap里是否有该key，有就累加，没有就填入
                Hashmap.put(Tempdata,Hashmap.get(Tempdata) + 1);
            }
            else{
                Hashmap.put(Tempdata,1);
            }
        }

        //遍历谜面，对每一个谜面累加该谜面每一个子模式对应的哈希值，将该值加入返回容器里
        for(String tempString : puzzles){
            Tempdata = 0;
            count = 0;
            for(char tempchar : tempString.toCharArray()){
                Tempdata |= 1 << ('z' - tempchar);//或运算转化为int类型
            }
            for(int child = Tempdata; child > 0; child = (child - 1)&Tempdata){ //遍历每一个子模式
                if((child | (1<<('z' - tempString.charAt(0)))) == child && Hashmap.containsKey(child)){
                    //判断是否有首字母
                    count += Hashmap.get(child);
                }
            }
            res.add(count);
        }
        return res;
    }
}
