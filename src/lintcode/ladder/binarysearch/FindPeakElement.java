package lintcode.ladder.binarysearch;

public class FindPeakElement {
	/**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
     /*O(n) works
    public int findPeak(int[] A) {
        // write your code here
        if(A==null||A.length==0)return -1;
        if(A.length==3)return 1;
        //keep a moving window of 3 elements
        int left=0,mid=left+1,right=mid+1;
        while(right<=A.length-1){
            if(A[mid]>A[left]&&A[mid]>A[right]){
                return mid;
            }
            left=mid;
            mid=right;
            right=mid+1;
        }
        return -1;
    }//woops, not binary search , just sliding window
    */
    //since it asks for any peak, so binary search can work for us
    ///I did not use recursive solutions, recursive is also natural for this kind of
    //divid and conquor problem
    //in ordder to use binary search for this porblem, precodition like described have to satisfy
    public int findPeak(int[] A) {
        // write your code here
        if(A==null||A.length==0)return -1;
        if(A.length==3)return 1;
        //keep a moving window of 3 elements
        int left=0,right=A.length-1;
        while(left+1<right){
           int mid = left+(right-left)/2;
           //do not forget this streight return point
           if (A[mid]>A[mid-1]&&A[mid]>A[mid+1])return mid;
           
           if(mid+1<A.length-1&& A[mid]<A[mid+1]){//then peak must be in right
               if(A[mid+1]>A[mid+2])return mid+1;
               //else redefine left boundary
               left=mid+1;
           }else if(mid-1>0 && A[mid]<A[mid-1]){
               //then peak must be in left side
               if(A[mid-1]>A[mid-2])return mid-1;
               //else it must be in left side
               //redefin e right
               right=mid-1;
           }
        }
        return -1;
    }
}
/*


There is an integer array which has the following features:

    The numbers in adjacent positions are different.
    A[0] < A[1] && A[A.length - 2] > A[A.length - 1].

We define a position P is a peek if:

A[P] > A[P-1] && A[P] > A[P+1]

Find a peak element in this array. Return the index of the peak.
Notice

The array may contains multiple peeks, find any of them.
Have you met this question in a real interview?
Example

Given [1, 2, 1, 3, 4, 5, 7, 6]

Return index 1 (which is number 2) or 6 (which is number 7)

*/