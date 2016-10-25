package dp;

import java.util.Scanner;

/**
 * key is to define thunderclouds as bigger value so that they are not picked
 * to contribute to accumulate to next step
 * Created by Andrew Ma on 10/25/2016.
 */
public class Easy_JumpOnClounds {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c[] = new int[n];
        for(int c_i=0; c_i < n; c_i++){
            c[c_i] = in.nextInt();
        }
        //can be recursion: minSteps(n)=min(minSteps(n-1),minSteps(n-2))
        //can be dp
        int[] minSteps = new int[n];
        minSteps[0]=0;
        //use Integer.MAX_VALUE to avoid thunderclouds be chosen
        minSteps[1] = (c[1]==1?Integer.MAX_VALUE:1);
        for( int i=2;i<n;i++){
            //if it is thunderclouds, there's no step to go here,
            //give it biggest value to guarantee not picking it
            minSteps[i] =
                    (c[i]==0?Math.min(minSteps[i-1],minSteps[i-2])+1:Integer.MAX_VALUE);
        }
        System.out.println(minSteps[n-1]);
    }
}
