package cn.offer2020.pbj.demo.leetcode;

import java.util.*;

/**
 * @ClassName: Demo126
 * @Author: pbj
 * @Date: 2020/4/27 10:11
 * @Description: TODO 126. 单词接龙 II
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 * 1.每次转换只能改变一个字母。
 * 2.转换过程中的中间单词必须是字典中的单词。
 */
public class Demo126 {
    //递归
    public  List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //结果
        List<List<String>> res = new ArrayList<>();
        if(wordList == null) return res;
        //bfs搜索所用的字典
        Set<String> dicts = new HashSet<>(wordList);
        if(!dicts.contains(endWord)) return res;
        if(dicts.contains(beginWord)) dicts.remove(beginWord);
        //bfs搜索最短路径所用的开始和结束的字典
        Set<String> endList = new HashSet<>(),
                beginList = new HashSet<>();
        //每个点所对应的邻接点，list
        Map<String, List<String>> map = new HashMap<>();
        beginList.add(beginWord);
        endList.add(endWord);
        bfs(map, beginList, endList, beginWord, endWord,dicts, false);
        //dfs的前进路线保存list
        List<String> subList = new ArrayList<>();
        subList.add(beginWord);
        dfs(map, res, subList, beginWord, endWord);
        return res;
    }
    void dfs (Map<String, List<String>> map,
              List<List<String>> result, List<String> subList,
              String beginWord, String endWord) {
        if(beginWord.equals(endWord)) {
            result.add(new ArrayList<>(subList));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String word : map.get(beginWord)) {
            subList.add(word);
            dfs(map, result, subList, word, endWord);
            subList.remove(subList.size() - 1);
        }
    }
    //reverse是双端bfs的一个优化
    void bfs(Map<String, List<String>> map, Set<String> beginList, Set<String> endList, String beginWord, String endWord,Set<String> wordList, boolean reverse){
        if(beginList.size() == 0) return;
        wordList.removeAll(beginList);
        boolean finish = false;
        Set<String> temp = new HashSet<>();
        for(String str : beginList){
            char[] charr = str.toCharArray();
            for(int chI = 0; chI < charr.length; chI++){
                char old = charr[chI];
                for(char ch = 'a'; ch <= 'z'; ch++){
                    if(ch == old)
                        continue;
                    charr[chI] = ch;
                    String newstr = new String(charr);
                    if(!wordList.contains(newstr)){
                        continue;
                    }
                    //若是在某一层找到了最后的节点，就直接标记找到了，即一票决定。这里因为要找所有的最短路径，所以循环还是要继续的。
                    if(endList.contains(newstr)){
                        finish = true;
                    }else{
                        temp.add(newstr);
                    }
                    //无论怎么变换方向，永远用开始方向的字符做key，是为了后面的dfs，单一方向搜索
                    String key = reverse? newstr:str;
                    String value = reverse ? str : newstr;
                    if(!map.containsKey(key)){
                        map.put(key, new ArrayList<>());
                    }
                    map.get(key).add(value);

                }
                charr[chI] = old;
            }
        }
        if(!finish) {
            if(temp.size() > endList.size()){
                bfs(map, endList, temp, beginWord, endWord,wordList, !reverse);
            }else{
                bfs(map, temp, endList, beginWord, endWord, wordList, reverse);
            }
        }
    }
    //非递归
    List<List<String>> res = new ArrayList<>();//结果
    Map<String, List<String>> map = new HashMap<>();//key为字符，value为其临近点的list，单一方向的（只会保存bfs时start向end前进所产生的记录）
    public  List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        if(wordList == null) return res;
        Set<String> dicSet = new HashSet<>(wordList);
        if(!dicSet.contains(endWord)) return res;
        if(dicSet.contains(beginWord)) dicSet.remove(beginWord);
        Set<String> endSet = new HashSet<>(),beginSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        boolean finish=false;
        boolean reverse=false;//双端bfs，false表示从头，true表示从尾

        //非递归bfs
        while(!beginSet.isEmpty()){
            dicSet.removeAll(beginSet);//标记走过
            Set<String> tmpSet=new HashSet<>();
            for(String str:beginSet){
                char[] cArr=str.toCharArray();
                for(int i=0;i<cArr.length;i++){
                    char cTmp=cArr[i];//变化
                    for(char c='a';c<='z';c++){
                        if(c==cTmp) continue;
                        cArr[i]=c;
                        String newStr=new String(cArr);
                        if(!dicSet.contains(newStr)) continue;

                        if(endSet.contains(newStr)){
                            finish=true;
                        }else{
                            tmpSet.add(newStr);
                        }
                        String key = reverse? newStr:str;
                        String value = reverse ? str : newStr;
                        if(!map.containsKey(key)){
                            map.put(key, new ArrayList<>());
                        }
                        map.get(key).add(value);
                    }
                    cArr[i]=cTmp;//复原
                }
            }
            if(tmpSet.size()<=endSet.size()){
                beginSet=tmpSet;
            }else{
                reverse=!reverse;//来回反转
                beginSet=endSet;
                endSet=tmpSet;
            }
            if(finish) break;
        }

        //dfs的前进路线保存list
        List<String> subList = new ArrayList<>();
        subList.add(beginWord);
        dfs(subList,beginWord,endWord);
        return res;
    }

    //简单dfs，因为是以此进行，所以不用辅助标记
    void dfs (List<String> subList,String beginWord,String endWord) {
        if(beginWord.equals(endWord)) {
            res.add(new ArrayList<>(subList));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String word : map.get(beginWord)) {
            subList.add(word);
            dfs(subList,word,endWord);
            subList.remove(subList.size() - 1);
        }
    }
}
