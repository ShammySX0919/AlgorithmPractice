package leetcode.medium;

/**
 * Created by andrew on 5/4/2017.
 */
public class Medium33_SearchInRotatedSortedArray {
    //this is a more general binary search, it handles sorted array too
    public int search(int[] nums, int target) {
        int l=0,r=nums.length-1;
        if(nums.length==1&&nums[0]==target)return 0;
        while(l<=r){//while there is still a valid search range
            int mid=l+(r-l)/2;//middle of search reange
            //same as binary search, check if find the target
            if(nums[mid]==target)return mid;

            //in a rotated array, there must be one half is fully sorted
            //we try to identify the sorted part out and start to deal with it because it is in that part, we can change l or r for sure
            if(nums[mid]<=nums[r]){
                //sorted part is right array
                //then try to change l or r according to target
                if(target>nums[mid]&&target<=nums[r])
                    l=mid+1;//we know mid is not the selection because otherwise it's returned
                else//it's not in right, then it is in left
                    r=mid-1;
            }else{//then sorted part is in left
                if(target>=nums[l]&&target<nums[mid])//it is within sorted left array
                    r=mid - 1;
                else//if not, it is in right part
                    l = mid+1;
            }
        }
        return -1;
    }

}
