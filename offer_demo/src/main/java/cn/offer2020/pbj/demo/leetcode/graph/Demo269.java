package cn.offer2020.pbj.demo.leetcode.graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
//1.生成三个数组，1.int[26]存储不同节点的入度（实际上这里应该使用最小堆，但是由于只有26个字母，用数组效率更高），
//      2.一个存储边对边数量int[26][26]（实际上这里就是存储图的，用的是邻接矩阵方式存储图，而不是邻接链表，因为只有26个字母，所以很有限）,
//      3.int[26]第三个数组用来存储字母的出现顺序，用来应对在出现多种拓扑排序的结果时，用来确定哪个字母放在前面的；
//2.初始化数组1，所有的值为-1，将出现了的字符置为0；并记录字符出现的顺序到数组3；获取相邻字符串的第一个不同字符，作为边，
//      记录到数组2，当出现一个边时，还要对入度数组1对应的节点的数量++；（这一步实际上就是拓扑排序的准备工作，图结构和入度最小堆的构造）
//3.拓扑排序。（将入度为0的节点取出以后，要将数组1中当前位置的值置为-1。假如出现多个入度为0的节点，则使用数组3进行排序）
//4.检查入度数组，是否所有的值都为-1，如果不是则说明图中有环，输出为“”，否则输出结果。（这一步实际上是拓扑排序的收尾工作）
//————————————————
//版权声明：本文为CSDN博主「a921122」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/a921122/article/details/60407972
public class Demo269 {
    private final static int NUM = 26;

    int[] nArrInDegreeNum;
    int[][] nArrEdges;
    int[] nArrOrders;

    public String alienOrder(String[] words) {
        nArrInDegreeNum = new int[NUM];// store number of indegree, like
        // min heap
        // init all to -1, which means there is no such element
        for (int i = 0; i < NUM; i++) {
            nArrInDegreeNum[i] = -1;
        }
        nArrEdges = new int[26][26];// store edge
        nArrOrders = new int[26];// for the order of alphabet
        int nOrder = 1;

        // init all character appeared to 0, and decide the order of alph
        for (String string : words) {
            // if it appears and =-2, set it = -1
            for (char c : string.toCharArray()) {
                if (nArrInDegreeNum[c - 'a'] == -1) {
                    nArrInDegreeNum[c - 'a'] = 0;
                }
                if (nArrOrders[c - 'a'] == 0) {
                    nArrOrders[c - 'a'] = nOrder;
                    nOrder++;
                }
            }
        }

        // compare the neighbor strings, and find the first different char
        for (int index = 0; index < words.length; index++) {
            if (index < words.length - 1) {
                findFirstDifferentChar(words[index], words[index + 1]);
            }
        }

        // top sort
        StringBuilder sb = new StringBuilder();
        topologicalSorting(sb);

        // judge whether all degree is -1, if not return ""
        for (int i = 0; i < NUM; i++) {
            if (nArrInDegreeNum[i] != -1) {
                return "";
            }
        }
        return sb.toString();
    }

    private void topologicalSorting(StringBuilder sb) {
        boolean bFind = true;
        LinkedList<Integer> llFind = new LinkedList<>();

        while (bFind) {
            bFind = false;
            for (int i = 0; i < NUM; i++) {
                if (nArrInDegreeNum[i] == 0) {
                    bFind = true;
                    llFind.add(i);
                    nArrInDegreeNum[i] = -1;
                }
            }
            // if find the indegree==0
            if (bFind) {
                // for two same chars, sort as its appeared order
                Collections.sort(llFind, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return nArrOrders[o1] - nArrOrders[o2];
                    }
                });
                for (Integer integer : llFind) {
                    char c = (char) ('a' + integer);
                    sb.append(c);
                    for (int i = 0; i < NUM; i++) {
                        if (nArrEdges[integer][i] > 0) {
                            nArrInDegreeNum[i] -= nArrEdges[integer][i];
                            nArrEdges[integer][i] = 0;
                        }
                    }
                }
                llFind.clear();
            }
        }
    }

    private void findFirstDifferentChar(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        int nLength = Math.min(c1.length, c2.length);
        for (int i = 0; i < nLength; i++) {
            if (c1[i] != c2[i]) {
                nArrInDegreeNum[c2[i] - 'a']++;// indegree num ++
                nArrEdges[c1[i] - 'a'][c2[i] - 'a']++;// edge ++
                break;
            }
        }
    }

    //图的深度优先搜索。深度优先的关键点在于如何检查环路，使用visited=0/1/2而不是布尔类型可以解决，即visited=0表示未访问UNVISITED，
    // 1表示访问中VISITING，2表示已访问VISITED。另外，深度优先搜索的话，graph用入边来表示，graph[i][j] = true <=> j->i，
    // 这样就容易通过递归方式，先解决所依赖的节点。
    //————————————————
    //版权声明：本文为CSDN博主「jmspan」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    //原文链接：https://blog.csdn.net/jmspan/article/details/51139859
    public class Solution {
        private void find(boolean[] alphabets, boolean[][] graph, int tail, int[] visited, StringBuilder sb) {
            if (!alphabets[tail] || visited[tail] != 0) return;
            visited[tail] = 1;
            for(int i=0; i<graph[tail].length; i++) {
                if (graph[tail][i] && alphabets[i] && visited[i] == 1) return;
                if (graph[tail][i] && alphabets[i] && visited[i] == 0) find(alphabets, graph, i, visited, sb);
            }
            visited[tail] = 2;
            sb.append((char)(tail+'a'));
        }
        public String alienOrder(String[] words) {
            char[][] ws = new char[words.length][];
            boolean[] alphabets = new boolean[26];
            int letters = 0;
            for(int i=0; i<words.length; i++) {
                ws[i] = words[i].toCharArray();
                for(int j=0; j<ws[i].length; j++) {
                    if (!alphabets[ws[i][j]-'a']) {
                        alphabets[ws[i][j]-'a'] = true;
                        letters ++;
                    }
                }
            }
            boolean[][] graph = new boolean[26][26];
            for(int i=0; i<words.length-1; i++) {
                for(int j=0; j<Math.min(words[i].length(), words[i+1].length()); j++) {
                    if (ws[i+1][j] != ws[i][j]) {
                        graph[ws[i+1][j]-'a'][ws[i][j]-'a'] = true;
                        break;
                    }
                }
            }
            int[] visited = new int[26];
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<alphabets.length; i++) {
                if (!alphabets[i] || visited[i]!=0) continue;
                find(alphabets, graph, i, visited, sb);
            }
            // System.out.println(sb.toString());
            if (sb.length() == letters) return sb.toString(); else return "";
        }
    }
}
