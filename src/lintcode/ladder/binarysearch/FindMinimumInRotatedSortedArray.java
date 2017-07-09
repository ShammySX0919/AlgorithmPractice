package lintcode.ladder.binarysearch;

public class FindMinimumInRotatedSortedArray {
	/**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if(num==null||num.length==0)return -1;
        if(num.length==1)return num[0];
        if(num.length==2)return Math.min(num[0],num[1]);
        
        int left=0,right=num.length-1;
        while(left<=right){
            int mid = left+(right-left)/2;
            //start from sure end: sorted part...then get rid of this end and continue the rest part of array
            if(num[mid]<=num[right]){//we know now right is sorted
                if((mid>0)&&(num[mid-1]>num[mid])){
                    return num[mid];//now mid is the min num
                }
                //otherwise, we know min is in left side
                right=mid-1;
            }else if(num[left]<=num[mid]){//now we know left side is sorted
                if((mid+1<=num.length-1) && (num[mid]>num[mid+1])){
                    return num[mid+1];
                }
                //other wise, min is in right part of array
                left=mid+1;
            }
        }
        return num[left];//there must be a found
    }
}
/*


Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.
Notice

You may assume no duplicate exists in the array.
Have you met this question in a real interview?
Example

Given [4, 5, 6, 7, 0, 1, 2] return 0

*/