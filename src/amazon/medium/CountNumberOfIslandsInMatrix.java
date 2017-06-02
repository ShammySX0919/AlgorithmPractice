package amazon.medium;

/**
 * this could be done in 2 ways:
 * 1. DFS 8 moving directions, and count number of rounds to mark all 1s(is 1 is valid vertex)
 * 2. union Find. this is better
 *
 * Created by andrew on 5/25/2017.
 */
public class CountNumberOfIslandsInMatrix {
    //No of rows and columns
    int ROW, COL;

    // A function to check if a given cell (row, col) can
    // be included in DFS
    boolean shouldGo(int M[][], int row, int col,
                     boolean visited[][])
    {
        // row number is in range, column number is in range
        // and value is 1 and not yet visited
        return (row >= 0) && (row < ROW) &&
                (col >= 0) && (col < COL) &&
                (M[row][col]==1 && !visited[row][col]);
    }

    // A utility function to do DFS for a 2D matrix.
    // It only considers the 8 neighbors as adjacent vertices
    void DFS(int M[][], int row, int col, boolean visited[][])
    {
        // These arrays are used to get row and column numbers
        // of 8 neighbors of a given cell
        //left-down,down,right-down,left,right,left-up,up,up-right
        int rowNbr[] = new int[] {-1, -1, -1,  0, 0,  1, 1, 1};
        int colNbr[] = new int[] {-1,  0,  1, -1, 1, -1, 0, 1};

        // Mark this cell as visited
        visited[row][col] = true;

        // Recur for all connected neighbours
        for (int k = 0; k < 8; ++k)
            if (shouldGo(M, row + rowNbr[k], col + colNbr[k], visited) )
                DFS(M, row + rowNbr[k], col + colNbr[k], visited);
    }

    // The main function that returns count of islands in a given
    //  boolean 2D matrix
    int countIslands(int M[][])
    {
        if(M==null ||M.length==0)return 0;
        ROW = M.length;
        COL = M[0].length;
        // Make a bool array to mark visited cells.
        // Initially all cells are unvisited
        boolean visited[][] = new boolean[ROW][COL];


        // Initialize count as 0 and travese through the all cells
        // of given matrix
        int count = 0;
        for (int i = 0; i < ROW; ++i)
            for (int j = 0; j < COL; ++j)
                if (M[i][j]==1 && !visited[i][j]) // If a cell with
                {                                 // value 1 is not
                    // visited yet, then new island found, Visit all
                    // cells in this island and increment island count
                    DFS(M, i, j, visited);
                    ++count;
                }

        return count;
    }

    // Driver method
    public static void main (String[] args) throws java.lang.Exception
    {
        int M[][]=  new int[][] {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };
        CountNumberOfIslandsInMatrix o = new CountNumberOfIslandsInMatrix();
        System.out.println("Number of islands is: "+ o.countIslands(M));
    }
}
