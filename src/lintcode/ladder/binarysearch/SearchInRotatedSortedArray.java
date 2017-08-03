package lintcode.ladder.binarysearch;

public class SearchInRotatedSortedArray {
	/** 
     *@param A : an integer rotated sorted array
     *@param target :  an integer to be searched
     *return : an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if(A==null||A.length==0)return -1;
        if(A.length==1)return (A[0]==target?0:-1);
        int left=0,right=A.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            //there must be one part of array is sorted, we deal with what we know for sure
            //do we find it?
            if(A[mid]==target)return mid;
            if(A[mid]<=A[right]){//it is important to include = condition here and there is else if,beccause mid can equal to right
                //then right side is sorted
                if(target>A[mid] && target<=A[right]){
                    //then we know target is in right side for sure
                    left=mid+1;
                }else{
                    //otherwise, it must be in left side
                    right = mid-1;
                }
            }else if(A[left]<=A[mid]){//if left side is sorted
                //then left side is sorted
                if(target >=A[left] && target<A[mid]){
                    //then it is in this side
                    right=mid-1;
                }else{
                    //then it is in right side
                    left=mid+1;
                }
            }
        }
        return -1;
    }
}
/*


Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
Have you met this question in a real interview?
Example

For [4, 5, 1, 2, 3] and target=1, return 2.

For [4, 5, 1, 2, 3] and target=0, return -1.

*/