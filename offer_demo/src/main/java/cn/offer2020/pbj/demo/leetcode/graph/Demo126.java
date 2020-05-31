package cn.offer2020.pbj.demo.leetcode.graph;

import java.util.*;

/**
 * @ClassName: Demo126
 * @Author: pbj
 * @Date: 2020/5/31 09:17
 * @Description: TODO 126. 单词接龙 II
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 */
public class Demo126 {
    HashMap<String,Set<String>> map = new HashMap();
    HashMap<Integer,Set<String>> nxtmap = new HashMap();
    int minDist = Integer.MAX_VALUE;
    List<List<String>> ans  = new ArrayList();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // 构建无向图
        nxtmap.put(0,new HashSet());
        nxtmap.get(0).add(beginWord);
        if(!wordList.contains(endWord)){return ans;}
        map.put(beginWord,new HashSet());
        wordList.add(beginWord);
        for(int i=0;i<wordList.size()-1;i++){
            for(int j=i+1;j<wordList.size();j++){
                if(canChange(wordList.get(i),wordList.get(j))){
                    Set<String> a = map.getOrDefault(wordList.get(i),new HashSet());
                    Set<String> b = map.getOrDefault(wordList.get(j),new HashSet());
                    a.add(wordList.get(j));
                    b.add(wordList.get(i));
                    map.put(wordList.get(i),a);
                    map.put(wordList.get(j),b);
                }
            }
        }
        // bfs 找到最短路径;
        minDist = bfs(beginWord,endWord);
        dfs(beginWord,endWord,new HashSet(),new ArrayList());
        return ans;
    }

    private void dfs(String cur, String end, Set<String> visit, List<String> path) {
        visit.add(cur);
        path.add(cur);
        if (path.size() == minDist) {
            if (cur.compareTo(end)==0) {
                ans.add(new ArrayList<>(path));
            }
        } else {
            for (String nxt : map.get(cur)) {
                // 优化： 取当前未访问过，且在该层次候选集合中的节点
                if (visit.contains(nxt) || !nxtmap.get(path.size()).contains(nxt)) continue;
                dfs(nxt, end, visit, path);
            }
        }
        visit.remove(cur);
        path.remove(path.size()-1);

    }

    public boolean canChange(String a,String b){
        int cnt = 0;
        if(a.length()!=b.length()){return false;}
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)){cnt++;}
            if(cnt>1){return false;}
        }
        return cnt==1;
    }

    public int bfs(String a,String b){
        LinkedList<String> queue = new LinkedList();
        List<String> mark = new ArrayList();
        queue.add(a);
        mark.add(a);
        int step = 0;
        while(queue.size()!=0){
            int size = queue.size();
            step++;
            nxtmap.put(step,new HashSet());
            while(size-->0){
                String temp = queue.poll();
                if(temp.equals(b)){return step;}
                Set<String> next = map.get(temp);
                for(String s:next){
                    if(mark.contains(s)){continue;}
                    queue.add(s);
                    mark.add(s);
                    nxtmap.get(step).add(s);
                }
            }
        }
        return -1;
    }

    //BFS
    //在单词接龙的基础上，需要将找到的最短路径存储下来；
    //之前的队列只用来存储每层的元素，那么现在就得存储每层添加元素之后的结果："ab","if",{"cd","af","ib","if"}；
    //（1）第一层：{"ab"}
    //（2）第二层：{"ab","af"}、{"ab","ib"}
    //（3）第三层：{"ab","af","if"}、{"ab","ib","if"}
    //如果该层添加的某一个单词符合目标单词，则该路径为最短路径，该层为最短路径所在的层，但此时不能直接返回结果，必须将该层遍历完，将该层所有符合的结果都添加进结果集；
    //每层添加单词的时候，不能直接添加到总的已访问单词集合中，需要每层有一个单独的该层访问的单词集，该层结束之后，再会合到总的已访问单词集合中，原因就是因为3.
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        // 结果集
        List<List<String>> res = new ArrayList<>();
        Set<String> distSet = new HashSet<>(wordList);
        // 字典中不包含目标单词
        if (!distSet.contains(endWord)) {
            return res;
        }
        // 已经访问过的单词集合：只找最短路径，所以之前出现过的单词不用出现在下一层
        Set<String> visited = new HashSet<>();
        // 累积每一层的结果队列
        Queue<List<String>> queue= new LinkedList<>();
        List<String> list = new ArrayList<>(Arrays.asList(beginWord));
        queue.add(list);
        visited.add(beginWord);
        // 是否到达符合条件的层：如果该层添加的某一单词符合目标单词，则说明截止该层的所有解为最短路径，停止循环
        boolean flag = false;
        while (!queue.isEmpty() && !flag) {
            // 上一层的结果队列
            int size = queue.size();
            // 该层添加的所有元素：每层必须在所有结果都添加完新的单词之后，再将这些单词统一添加到已使用单词集合
            // 如果直接添加到 visited 中，会导致该层本次结果添加之后的相同添加行为失败
            // 如：该层遇到目标单词，有两条路径都可以遇到，但是先到达的将该单词添加进 visited 中，会导致第二条路径无法添加
            Set<String> subVisited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                // 获取该路径上一层的单词
                String word = path.get(path.size() - 1);
                char[] chars = word.toCharArray();
                // 寻找该单词的下一个符合条件的单词
                for (int j = 0; j < chars.length; j++) {
                    char temp = chars[j];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[j] = ch;
                        if (temp == ch) {
                            continue;
                        }
                        String str = new String(chars);
                        // 符合条件：在 wordList 中 && 之前的层没有使用过
                        if (distSet.contains(str) && !visited.contains(str)) {
                            // 生成新的路径
                            List<String> pathList = new ArrayList<>(path);
                            pathList.add(str);
                            // 如果该单词是目标单词：将该路径添加到结果集中，查询截止到该层
                            if (str.equals(endWord)) {
                                flag = true;
                                res.add(pathList);
                            }
                            // 将该路径添加到该层队列中
                            queue.add(pathList);
                            // 将该单词添加到该层已访问的单词集合中
                            subVisited.add(str);
                        }
                    }
                    chars[j] = temp;
                }
            }
            // 将该层所有访问的单词添加到总的已访问集合中
            visited.addAll(subVisited);
        }
        return res;
    }
    //双向BFS + DFS
    //思路说明：利用双向BFS构建出每个单词可达的下层单词，之后根据该关系利用DFS构建路径，输出符合条件的路径即可。
    //单词关系：始终记录该单词的可达的所有下层关系："ab","if",{"cd","af","ib","if"}
    //（1）"ab" -> {"af", "ib"}
    //（2）"af" -> {"if"}
    //（3）"ib" -> {"if"}
    //探索顺序：
    //（1）从上到下：只需在关系map中，添加可达的单词即可
    //（2）从下到上：当前遍历的单词，与该单词相差一个字符的所有单词，因为是从下到上，所以当前遍历的单词是得到的相差一个字符的单词的可达下层单词
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        // 结果集
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        // 字典中不包含目标单词
        if (!words.contains(endWord)) {
            return res;
        }
        // 存放关系：每个单词可达的下层单词
        Map<String, List<String>> mapTree = new HashMap<>();
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        if (buildTree(words, begin, end, mapTree, true)) {
            dfs(res, mapTree, beginWord, endWord, new LinkedList<>());
        }
        return res;
    }

    // 双向BFS，构建每个单词的层级对应关系
    private boolean buildTree(Set<String> words, Set<String> begin, Set<String> end, Map<String, List<String>> mapTree, boolean isFront){
        if (begin.size() == 0) {
            return false;
        }
        // 始终以少的进行探索
        if (begin.size() > end.size()) {
            return buildTree(words, end, begin, mapTree, !isFront);
        }
        // 在已访问的单词集合中去除
        words.removeAll(begin);
        // 标记本层是否已到达目标单词
        boolean isMeet = false;
        // 记录本层所访问的单词
        Set<String> nextLevel = new HashSet<>();
        for (String word : begin) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String str = String.valueOf(chars);
                    if (words.contains(str)) {
                        nextLevel.add(str);
                        // 根据访问顺序，添加层级对应关系：始终保持从上层到下层的存储存储关系
                        // true: 从上往下探索：word -> str
                        // false: 从下往上探索：str -> word（查找到的 str 是 word 上层的单词）
                        String key = isFront ? word : str;
                        String nextWord = isFront ? str : word;
                        // 判断是否遇见目标单词
                        if (end.contains(str)) {
                            isMeet = true;
                        }
                        if (!mapTree.containsKey(key)) {
                            mapTree.put(key, new ArrayList<>());
                        }
                        mapTree.get(key).add(nextWord);
                    }
                }
                chars[i] = temp;
            }
        }
        if (isMeet) {
            return true;
        }
        return buildTree(words, nextLevel, end, mapTree, isFront);
    }

    // DFS: 组合路径
    private void dfs (List<List<String>> res, Map<String, List<String>> mapTree, String beginWord, String endWord, LinkedList<String> list) {
        list.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            list.removeLast();
            return;
        }
        if (mapTree.containsKey(beginWord)) {
            for (String word : mapTree.get(beginWord)) {
                dfs(res, mapTree, word, endWord, list);
            }
        }
        list.removeLast();
    }
}
