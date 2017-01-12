package array;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/candies
 * @author Andrew Ma
 *
 */
public class Candies {
	/**
	 * it goes through the array from left to right to figure out right neighbor's candy
	 * 
	 * it then goes through the array from right to left to figure out left neighbor's candy
	 * 
	 * then it take the maximum number of candies from the two passes since each one is valid
	 * from one direction, the maximum covers validity of both directions.
	 * 
	 * @param arr
	 * @return
	 */
	public static long calculate(int[] arr) {
	    int m = arr.length;
	    //this can be optimized to use only one array
	    int[] left = new int[m];
	    int[] right = new int[m];
	    long candies = 0;
	    left[0] = 1;
	    for (int i = 1; i < m; i++)
	        left[i] = arr[i] > arr[i - 1] ? left[i - 1] + 1 : 1;
	    right[m - 1] = 1;
	    for (int i = m - 2; i >= 0; i--)
	        right[i] = arr[i] > arr[i + 1] ? right[i + 1] + 1 : 1;
	    for (int i = 0; i < m; i++)
	        candies += Math.max(left[i], right[i]);
	    return candies;
	}
	public static long calculate2(int[] arr) {
	    int m = arr.length;
	    //this can be optimized to use only one array
	    long[] aux = new long[m];

	    long candies = 0;
	    aux[0] = 1;//first one is always 1
	    for (int i = 1; i < m; i++)
	        aux[i] = arr[i] > arr[i - 1] ? aux[i - 1] + 1 : 1;
	    aux[m - 1] = Math.max(aux[m - 1], 1);//last one is always 1
	    for (int i = m - 2; i >= 0; i--)
	        aux[i] = Math.max(aux[i], arr[i] > arr[i + 1] ? aux[i + 1] + 1 : 1);
	    for (int i = 0; i < m; i++)
	        candies += aux[i];
	    return candies;
	}
	    public static void main(String[] args) {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	        Scanner in = new Scanner(System.in);
	        int n = in.nextInt();in.nextLine();
	        int[] ratings = new int[n];
	        long total = 0;
	        for(int i=0;i<n;i++){
	            int r = in.nextInt();
	            ratings[i] = r;
	            if(in.hasNextLine()){
	                in.nextLine();
	            }
	        }
	       System.out.println(calculate(ratings));
	    }
}
