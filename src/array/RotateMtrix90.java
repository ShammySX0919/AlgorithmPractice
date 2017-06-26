package array;

/**
 [1][2][3][4]
 [5][6][7][8]
 [9][0][1][2]
 [3][4][5][6]
 (0,0)-->(0,n-1); (0,1)-->(1,n-1); (n-1,0)-->(0,0)
 [3][9][5][1]
 [4][0][6][2]
 [5][1][7][3]
 [6][2][8][4]
 move element that is
 row changed to col, col change to row

 * Created by andrew on 5/30/2017.
 */
public class RotateMtrix90 {
    //O(n^2) space
    static int[][] RotateMatrixBasic(int[][] matrix) {
        if (matrix == null || matrix.length != matrix[0].length) return null;
        int n = matrix.length;
        int[][] ret = new int[n][n];
        //taking r=0 as example, in new array, element in r 0 are originally col 0, with positions complemented by n
        //so while, c is changing, right side is row is changing, but in n-c-1 manner
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < n; ++c) {
                //pattern to remember: outer indices are same, either r or c
                //inner one is c or r are reversed to above, then one of it changing in reversed order
                //ret[r][c] = matrix[n - c - 1][r];
                ret[c][n-r-1] = matrix[r][c];//this is easier to understand and remember: copy matrix row to ret col. first row to last col
            }
        }
        return ret;
    }
    //layer by layer rotation in pace
    static int[][] RotateMatrixLayerByLayer(int[][] matrix) {
        int n = matrix.length;
        for(int layer=0;layer<= n/2;layer++){
            int lastPositionInLayer = n-layer-1;
            for(int i=layer;i<lastPositionInLayer;i++){
                int offset = i-layer;
                //keep in tmp
                int top = matrix[layer][i];
                //left to rop
                matrix[layer][i] = matrix[lastPositionInLayer-offset][layer];
                //bottom to left
                matrix[lastPositionInLayer-offset][layer] = matrix[lastPositionInLayer][lastPositionInLayer - offset];
                //right to bottom
                matrix[lastPositionInLayer][lastPositionInLayer - offset] = matrix[i][lastPositionInLayer];
                //top to right
                matrix[i][lastPositionInLayer] = top;
            }
        }
        return matrix;
    }
   static private void swap(int x1, int y1,int x2, int y2, int matrix[][]){
        int tmp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = tmp;
    }
    //no extra space needed
    static int[][] RotateMatrixLayerByLayerSwaping(int[][] matrix) {
        int n = matrix.length;
        //totally n/2 layers
        for(int layer=0;layer< n/2;layer++){
            int lastPosition = n-layer-1;
            //it takes lastPosition -1 steps to move to lastPosition, because layer is the first position
            for(int i=layer;i<lastPosition;i++){
                int offset = i-layer;
                //always swap with left top coner
                //swap left top with right top
                swap(layer,i,i,lastPosition,matrix);
                //swap left top, which now is the right top value, with right down
                swap(layer,i,lastPosition,lastPosition-offset,matrix);
                //swap left top, which is now right down, with left down
                swap(layer,i,lastPosition-offset,i,matrix);
            }
        }
        return matrix;
    }
    static void displayMatrix(int mat[][])
    {
        int N = mat.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.printf("%2d ", mat[i][j]);

            System.out.printf("\n");
        }
        System.out.printf("\n");
    }
    public static void main(String... args){
        int mat[][] =
        {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        displayMatrix(mat);
        //RotateMatrixBasic(mat);
        //RotateMatrixLayerByLayer(mat);
        displayMatrix(RotateMatrixBasic(mat));
        //displayMatrix(mat);
    }
    //roate NxM clockwise 90
    static int[][] rotateMatrixBy90DegreeClockwise(int[][] matrix) {

        int R = matrix[0].length; //Total columns of Original Matrix
        int C = matrix.length; //Total rows of Original Matrix
        //copy of matrix
        int[][] rotatedMatrix = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                //pattern to remember: left and right indices are the same, inner ones are changing reversed direction
                rotatedMatrix[c][ (C-1)- r] = matrix[r][c];
                //matrix's first row become new matrix's last col
                //second row become new matrix's last second col, ..., etc. matrix's row col change become new matrix's row change
                //while matrix's row is increasing, new matrix's col is changing decreasingly
            }
        }
        return rotatedMatrix;
    }
}
