package leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by andrew on 6/11/2017.
 */
//bfs, starting from start[], bfs all the matrix, and update the distance to the cells if later calculation is shorter
//then return the distance value in dest location.
public class Hard505_MazeII {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        //init to max values
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        //start distance is known as 0
        distance[start[0]][start[1]] = 0;
        //moving directions
        int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
        //queue for bfs. it could be easier by counting layers
        Queue< int[] > queue = new LinkedList< >();
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] s = queue.remove();
            for (int[] dir: dirs) {
                int x = s[0] + dir[0];
                int y = s[1] + dir[1];
                int count = 0;
                //only move to 0 cells
                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                    x += dir[0];//it has one extra move when exit the loop
                    y += dir[1];
                    count++;
                }
                int newPosition[] = new int[]{x - dir[0], y - dir[1]};//this is because x and y is 1 extra move
                //adding new element only when that makes shorter distance
                if (distance[s[0]][s[1]] + count < distance[newPosition[0]][newPosition[1]]) {
                    distance[newPosition[0]][newPosition[1]] = distance[s[0]][s[1]] + count;
                    queue.add(newPosition);
                }
            }
        }
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }
}
