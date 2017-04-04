package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 212595974 on 4/4/2017.
 * counting number of connected components
 *  {{1, 1, 0, 0, 0},
 {0, 1, 0, 0, 1},
 {1, 0, 0, 1, 1},
 {0, 0, 0, 0, 0},
 {1, 0, 1, 0, 1}
 }
 */
public class NumberOfIslands {
    int stepsRow[] = new int[]{-1,-1,-1,0,1,1,1,0};
    int stepsCol[] = new int[]{-1,0,1,1,1,0,-1,-1};

    int countIslands(int m[][], boolean dfs){
        boolean[][] visited = new boolean[m.length][m[0].length];
        //you can modify the matrix directly to change 1 to 2 or other numbers. then you do not need to have this extra array
        int count = 0;//count how many times a BFS or DFS can run
        for(int row = 0;row<m.length;row++){
            for(int col = 0;col<m.length;col++){
                if(m[row][col]==1 &&!visited[row][col]){
                    if(dfs) {
                        dfs(m, row, col, visited);
                    }else{
                        bfs(m, row, col, visited);
                    }
                    count++;
                }
            }
        }
        return count;
    }
    //with bfs, it better has a data structure for coordinators
    private void dfs(int m[][],int row, int col, boolean visited[][]){
        visited[row][col] = true;
        for(int step=0;step<8;step++){
            if(shouldGo(m,row+stepsRow[step],col+stepsCol[step],visited)) {
                dfs(m, row + stepsRow[step], col + stepsCol[step], visited);
            }
        }
    }
    private void bfs(int m[][],int row, int col, boolean visited[][]){
        Queue<String> queue = new LinkedList<>();
        queue.add(row+":"+col);
        while(!queue.isEmpty()){
            String coordinator =queue.poll();
            int r = Integer.parseInt(coordinator.substring(0,coordinator.indexOf(':')));
            int c = Integer.parseInt(coordinator.substring(coordinator.indexOf(':')+1));
            visited[r][c] = true;
            for(int step=0;step<8;step++){
                if(shouldGo(m,r+stepsRow[step],c+stepsCol[step],visited)) {
                    queue.add((r +stepsRow[step])+":"+ (c + stepsCol[step]));
                }
            }
        }
    }
    private boolean shouldGo(int m[][],int row, int col, boolean visited[][]){
        return (row >= 0) && (row < m.length) &&
                (col >= 0) && (col < m[0].length) &&
                (m[row][col]==1 && !visited[row][col]);
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        int M[][]=  new int[][] {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        NumberOfIslands I = new NumberOfIslands();
        System.out.println("Number of islands is: "+ I.countIslands(M,true));
        System.out.println("Number of islands is: "+ I.countIslands(M,false));
        System.out.println(I.countIslands(M,false)==I.countIslands(M,true));
    }
}
