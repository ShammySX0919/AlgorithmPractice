package amazon.oa.y2017;

import java.util.LinkedList;
import java.util.Queue;

/**
 * a maze, 1 is through, 0 is blocked, give exit as (x,y), find minimum steps to go out. if can not go out, return -1.
 * Analyze: this is asking minimum steps of exit, so BFS will give us answer.
 *
 * it allows to move four directions in this implementation.
 *
 * if you have to return path, a pathTo array need to be maintained. usually the pathTo or dist count be part of data node.
 *
 * this is same as Shortest path in a Binary Maze at http://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 * Created by andrew on 5/17/2017.
 */
class Cell{
    int r,c;
    int dist=0;
    public Cell(int r,int c){
        this.r = r;this.c = c;
    }
}
public class MinStepsEscapingMaze {
    private static boolean isValidMove(int r, int c, int rs, int cs){
        if(r<0||r>=rs)return false;
        if(c<0||c>=cs)return false;
        return true;
    }
    //a better solution
    private static boolean isValidMove(Cell c, int rl, int cl){
        if(c.r<0||c.r>=rl)return false;
        if(c.c<0||c.c>=cl)return false;
        return true;
    }
    public static int shortestStepsToReach(int ro,int co,int rd,int cd,int[][] matrix){
        if(matrix==null||matrix.length==0)return -1;
        if(matrix[ro][co]==0)return -1;
        int R = matrix.length;
        int C = matrix[0].length;
        //false by default
        boolean visited[][] = new boolean[R][C];

        //queue for bfs
        Queue<Cell> q = new LinkedList<>();
        //adding source cell to queue to start
        Cell source = new Cell(ro,co);
        q.add(source);
        //bfs
        while(!q.isEmpty()){
            Cell curCell = q.poll();
            visited[curCell.r][curCell.c] = true;
            //reach target
            if(curCell.c==cd && curCell.r==rd)return curCell.dist;
            //level by level, that is just one step increase
            //adding 4 possible neighbors
            Cell next = null;
            //up
            next = new Cell(curCell.r-1,curCell.c);
            if(isValidMove(next,R,C) && !visited[curCell.r-1][curCell.c]&&matrix[curCell.r-1][curCell.c]==1){
                next.dist = curCell.dist+1;
                q.add(next);
            }

            //right
            next = new Cell(curCell.r,curCell.c+1);
            if(isValidMove(next,R,C) && !visited[curCell.r][curCell.c+1]&&matrix[curCell.r][curCell.c]==1){
                next.dist = curCell.dist+1;
                q.add(next);
            }
            //down
            next = new Cell(curCell.r+1,curCell.c);
            if(isValidMove(next,R,C) && !visited[curCell.r+1][curCell.c]&&matrix[curCell.r+1][curCell.c]==1){
                next.dist = curCell.dist+1;
                q.add(next);
            }
            //left
            next = new Cell(curCell.r,curCell.c-1);
            if(isValidMove(next,R,C) && !visited[curCell.r][curCell.c-1]&&matrix[curCell.r][curCell.c-1]==1){

                next.dist = curCell.dist+1;
                q.add(next);
            }
        }
        return -1;//not reachable after all searching
    }
    public static void main(String... args){
        int mat[][] = new int[][]
        {
            { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
            { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
            { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
            { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
        };
        System.out.println(shortestStepsToReach(0,0,3,4,mat));
    }
}
