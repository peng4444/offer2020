package cn.offer2020.pbj.demo;

import java.util.*;

public class TestDemo {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[][] chars = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(help(chars,str));
        //System.out.println(true);
    }

    public static boolean help(char[][] chars,String str){
        boolean[][] visited = new boolean[chars.length][chars[0].length];
        for(int i = 0;i<chars.length;i++){
            for(int j = 0;j<chars[0].length;j++){
                if(dfs(chars,str,i,j,0,visited)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] chars,String str,int i,int j,int index,boolean[][] visited){
        if(index==str.length()){
            return true;
        }
        if(i<0||i>=chars.length||j<0||j>=chars[0].length||chars[i][j]!=str.charAt(index)||visited[i][j]==true){
            return false;
        }else{
            visited[i][j] = true;
        }
        boolean ans = dfs(chars,str,i-1,j,index+1,visited)||dfs(chars,str,i+1,j,index+1,visited)
                ||dfs(chars,str,i,j-1,index+1,visited)||dfs(chars,str,i,j+1,index+1,visited);
        visited[i][j] = false;
        return ans;
    }
}