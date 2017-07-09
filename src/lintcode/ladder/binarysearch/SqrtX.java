package lintcode.ladder.binarysearch;

public class SqrtX {

	/**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        //determine left and right boundary the first
        int left=0;
        int right = (int)Math.sqrt(Integer.MAX_VALUE);//make sure no overflow
        //then use binary search to narrow the search:yes, this is to search
        //search can solve a lot of problems
        while(left<=right){
            int mid = (left+right)/2;
            if(mid*mid==x)return mid;
            else if(mid*mid<x){
                left=mid+1;
            }
            else if(mid*mid>x) {
                right=mid-1;
            }
        }
        return (left+right)/2;
    }
}


//binary search is left right and middle, compare middle with search target and adjust left and right
/*


Implement int sqrt(int x).

Compute and return the square root of x.
Have you met this question in a real interview?
Example

sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3

*/