package lintcode.ladder.binarysearch;

public class SearchForARange {
	/** 
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
     /*lazy implement
    public int[] searchRange(int[] A, int target) {
        // write your code here
        if(A==null||A.length==0)return new int[]{-1,-1};
        int i = Arrays.binarySearch(A,target);//it returns position relatd number
        if(i<0)return new int[]{-1,-1};
        int left=i,right=i;
        while(left>=0&&A[left]==target)left--;
        left++;
        while(right<A.length&&A[right]==target)right++;
        right--;
        return new int[]{left,right};
        
    }
    */
    //write binary search again, loving the simple code patterns
     public int[] searchRange(int[] A, int target) {
         int result[] = new int[]{-1,-1};
         if(A==null||A.length==0)return result;
         
         int left=0,right=A.length-1;
         
         while(left<=right){
             int mid = left+(right-left)/2;
             if(A[mid]==target){
                 //when it was found,try to figure out the range
                while(mid>=0&&A[mid]==target)mid--;
                //mid now is no longer target
                result[0]=++mid;//need go back one
                while(mid<A.length&&A[mid]==target)mid++;
                result[1]=--mid;
                return result;
             }
             else if(A[mid]<target){
                 left = mid+1;
             }
             else
             right=mid -1;
         }
         return result;
     }
}
/*


Given a sorted array of n integers, find the starting and ending position of a given target value.

If the target is not found in the array, return [-1, -1].
Have you met this question in a real interview?
Example

Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

*/