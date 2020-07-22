package cn.offer2020.pbj.demo.leetcode.arrays;

/**
 * @pClassName: Demo1409
 * @author: pengbingjiang
 * @create: 2020/7/22 15:20
 * @description: TODO 1409.查询带键的排列
 */
public class Demo1409 {
    //1.初始一个数组f，用来记录初始位置 f[i]=i;
    //2.找出要处理的数 所在的位置 记作res
    //3.直接修改queries数组（用它做返回）
    //4.遍历f数组，将所有 小于 res位置的那个数 位置加一（用来模拟向后移动）
    public int[] processQueries1(int[] queries, int m) {
        int [] f = new int [m+1];
        for(int i=1;i<=m;i++)f[i]=i;
        for(int i=0;i<queries.length;i++){
            int res =queries[i];  //queries[i]是每一个要向前移动的数
            queries[i]=f[res]-1;  //f[res]是要移动的那个数的位置，但是我在最开始就是从1开始的  所以-1；
            for(int j=1;j<=m;j++){
                if(f[j]<f[res])f[j]++;//依次遍历，把所有在res位置之前的数+1，用来模拟新的位置
            }
            f[res]=1;//把之前res的数放到第一位
        }
        return queries;
    }


    public int[] processQueries(int[] queries, int m) {
        int[] index = new int[m+1];
        int[] res = new int[queries.length];
        for(int i = 1;i<=m;i++){
            index[i] = i-1;
        }
        for(int i = 0;i<queries.length;i++){
            int num = queries[i];
            int pos = index[num];
            res[i] = pos;
            if(pos==0) continue;
            int sum = pos;
            for(int j = 1;j<=m;j++){
                if(index[j]<pos){
                    index[j]++;
                    sum--;
                    if(sum==0) break;
                }
            }
            index[num]=0;
        }
        return res;
    }
}
