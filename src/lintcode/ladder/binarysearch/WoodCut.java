package lintcode.ladder.binarysearch;

/**
 *

 Given n pieces of wood with length L[i] (integer array).
 Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
 What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
 Notice

 You couldn't cut wood into float length.

 If you couldn't get >= k pieces, return 0.

 For L=[232, 124, 456], k=7, return 114

 Algorithm: it is to keep checking valid length between 0 and min length of given wood, and then preorderFind a number that
 can longest. The way it tries to change the length is to use binary search.

 * Created by 212595974 on 5/30/2017.
 */
public class WoodCut {
    /**
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces.
     */
    public int woodCut(int[] L, int k) {
        if(L==null||L.length==0)return 0;
        //longest length, which is smallest in all values
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }

        // preorderFind the largest length that can cut more than k pieces of wood.
        //here we change the length between 1 and max possible length, which is shortest original woods
        int start = 1, end = max;
        int cnt=0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            //try the length to cut
            cnt = count(L, mid);
            if(cnt>=k){//as long as bigger than k, we hope to cut it longer and make k as small as possible
                start=mid+1;

            }
            else {
                end = mid-1;//we need to try shorter cut
            }
        }
        return end;

    }
    //O(n)
    private int count(int[] L, int length) {
        int sum = 0;
        for (int i = 0; i < L.length; i++) {
            sum += L[i] / length;
        }
        return sum;
    }
}
