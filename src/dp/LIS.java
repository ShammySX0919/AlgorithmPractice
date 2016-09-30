package dp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by andrew on 30/09/16.
 */
public class LIS {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[] arrayOfNum = new int[n];
        for (int i = 0; i < n; i++) {
            arrayOfNum[i] = Integer.valueOf(in.nextLine());
        }
        in.close();
        //lis(arrayOfNum);
        lisNlgN(arrayOfNum);
    }

    /**
     * this is quadratic time, runs time out
     */
    private static void lis(int[] arr) {
        int[] dpLis = new int[arr.length];
        //initialize them with 1, the start length
        for (int i = 0; i < arr.length; i++) {
            dpLis[i] = 1;
        }
        //from bottom up, no duplicate calculation as compared with top down approach
        for (int upBound = 1; upBound < arr.length; upBound++) {
            for (int bottomBound = 0; bottomBound < upBound; bottomBound++) {
                //increase when condition satisfied
            	//this also guarantees the dpLis[upBound] is max of all dpLis[bottomBound]+1
                if (arr[upBound] > arr[bottomBound] && dpLis[upBound] < dpLis[bottomBound] + 1) {
                    dpLis[upBound] = dpLis[bottomBound] + 1;
                }
            }
        }
        //find max in a loop
        int maxLis = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLis = Math.max(maxLis, dpLis[i]);
        }
        System.out.println(maxLis);
    }

    /**
     * under help of binary search for each number to determine it's position in
     * a list that keeps updating. very good thought!
     */
    private static void lisNlgN(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        //list can be naturally an array, but I found using list is easier to understand
        ArrayList<Integer> auxList = new ArrayList<Integer>();

        for (int num : nums) {
            //adding the num to tail of list if list is empty or num is greater than current tail of list
            if (auxList.size() == 0 || num > auxList.get(auxList.size() - 1)) {
                auxList.add(num);
            } else {
                //if number is smaller than current tail of list, try to find a position to insert it to list
                int left = 0; //left boundary
                int right = auxList.size() - 1;//right boundary. these two make the length of current list
                //do binary search to determine position of current num in the list
                //you can also use java's binary search method on collections to return expected position
                //the position returned indicates the current num is less than the one already there
                //overwriting the existing one with this smaller one
                //this is also an algorithm of using binary search to determine insertion position of a given number
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (auxList.get(mid) < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                //after this, the right is num's expected position in existing list
                //overwrite the value in that position: it's smaller than the value being overwritten!
                //indices in auxList does not reflects a list of longest increasing sequence, but its size is
                //the correct number of LIS
                auxList.set(right, num);
            }
        }

        System.out.println(auxList.size());
    }
}