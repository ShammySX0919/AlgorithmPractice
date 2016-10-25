package array;

import java.util.Scanner;

/**
 * Created by Andrew Ma on 10/25/2016.
 */
public class Easy_MinimumDistance {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int A[] = new int[n];
        int minDist = Integer.MAX_VALUE;
        for(int A_i=0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
        }
        //to avoid duplicate compute, avoid refer it back, thinking about directed graph
        //make sure j!=i
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(A[i]==A[j]){
                    minDist = Math.min(minDist,Math.abs(i-j));
                }
            }
        }
        System.out.println(minDist==Integer.MAX_VALUE?-1:minDist);
    }
}
