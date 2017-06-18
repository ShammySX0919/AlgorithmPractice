package leetcode.contest.week37;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by andrew on 6/17/2017.
 */
public class MaxDistanceInArrays {
    //time limit exceed
    public static int maxDistance(int[][] arrays) {
        int[] mins = new int[arrays.length];
        int[] maxs = new int[arrays.length];
        int i=0;
        for(int[] arr:arrays){
            mins[i] = arr[0];
            maxs[i]=arr[arr.length-1];
            i++;
        }
        int min = 0;
        int max = 0;
        int maxDist = Integer.MIN_VALUE;
        for(i=0;i<mins.length;i++){
            for(int j=0;j<maxs.length;j++){
                int newMax = Math.abs(maxs[j]-mins[i]);
                if(i!=j && newMax>maxDist){
                    maxDist =newMax;
                    min = i;max=j;
                }
            }
        }
        return maxDist;
    }

public static void main(String[] args){
        int arrs[][] = new int[][]{{1,2,3},{4,5},{1,2,3}};
    //int arrs[][] = new int[][]{{1,4},{0,5}};
        System.out.println(maxDistance(arrs));
}
}
