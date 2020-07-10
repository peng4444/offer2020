package cn.offer2020.pbj.demo.leetcode.bfs_dfs;

/**
 * @pClassName: Demo980
 * @author: pengbingjiang
 * @create: 2020/7/10 23:12
 * @description: TODO 980.不同路径III
 */
public class Demo980 {
    int ans = 0;
    public int uniquePathsIII(int[][] grid) {
        int bushu=1;//存储从开始到结束需要走的步数
        int qibu[]=new int[2];//用于存储起点的坐标
        boolean[][] laiguo=new boolean[grid.length][grid[0].length];
        //矩阵laiguo用于记录某个位置是否走过
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==0){bushu++;}
                else if(grid[i][j]==1){
                    qibu[0]=i;
                    qibu[1]=j;
                }
            }
        }
        luxian(grid,laiguo,bushu,qibu[0],qibu[1]);
        return ans;
    }
    public void luxian(int[][] grid,boolean[][] laiguo,int bushu,int x,int y){
        //此方法用于判断每条道路是否可以走通
        if(x==grid.length||x<0||y==grid[0].length||y<0||laiguo[x][y]||grid[x][y]==-1){
            return;
        }
        else if(bushu==0){
            //bushu在每个嵌套方法中被减1，因此要在走完的时候判断是否够了步数，也就是bushu==0？
            if(grid[x][y]==2){ans++;}//判断正确道路，还应该终点到了2
            return;
        }
        else{//对四个方向继续进行搜索
            laiguo[x][y]=true;
            luxian(grid,laiguo,bushu-1,x+1,y);
            luxian(grid,laiguo,bushu-1,x-1,y);
            luxian(grid,laiguo,bushu-1,x,y+1);
            luxian(grid,laiguo,bushu-1,x,y-1);
            laiguo[x][y]=false;
        }
    }
}
