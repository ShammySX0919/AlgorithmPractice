package array;

/**
 * Created by 212595974 on 6/22/2017.
 */
public class SpiralPrintMatrix {
    // Function print matrix in spiral form
    static void spiralPrint(int a[][])
    {
        int rowEnd=a.length, colEnd = a[0].length;
        int i, rowStart = 0, colStart = 0;
        /*  rowStart - starting row index
        rowEnd - ending row index
        colStart - starting column index
        colEnd - ending column index
        i - iterator
        */

        while (rowStart < rowEnd && colStart < colEnd)
        {
            // Print the first row from the remaining rows
            for (i = colStart; i < colEnd; ++i)
            {
                System.out.print(a[rowStart][i]+" ");
            }
            rowStart++;

            // Print the last column from the remaining columns
            for (i = rowStart; i < rowEnd; ++i)
            {
                System.out.print(a[i][colEnd-1]+" ");
            }
            colEnd--;

            // Print the last row from the remaining rows */
            if ( rowStart < rowEnd)
            {
                for (i = colEnd-1; i >= colStart; --i)
                {
                    System.out.print(a[rowEnd-1][i]+" ");
                }
                rowEnd--;
            }

            // Print the first column from the remaining columns */
            if (colStart < colEnd)
            {
                for (i = rowEnd-1; i >= rowStart; --i)
                {
                    System.out.print(a[i][colStart]+" ");
                }
                colStart++;
            }
        }
    }
    // driver program
    public static void main (String[] args)
    {

        int a[][] = { {1,  2,  3,  4,  5,  6},
                {7,  8,  9,  10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };
        spiralPrint(a);
    }
}
