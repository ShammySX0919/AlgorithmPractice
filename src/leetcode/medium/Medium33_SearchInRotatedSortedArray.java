package leetcode.medium;

/**
 * Created by andrew on 5/4/2017.
 */
public class Medium33_SearchInRotatedSortedArray {
    //this is a more general binary search, it handles sorted array too
    public int search(int[] nums, int target) {
        //return searchRec(nums,target,0,nums.length-1);//recursion version of binary search
        int l=0,r=nums.length-1;
        if(nums.length==1&&nums[0]==target)return 0;
        while(l<=r){//while there is still a valid search range
            int mid=l+(r-l)/2;//middle of search reange
            //same as binary search, check if preorderFind the target
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

    public  int searchRec(int[] nums, int target,int left, int right) {
        if(nums.length==1&&nums[0]==target)return 0;
        if(left>=right){
            if(nums[left]==target)return left;
            else return -1;
        }
        int mid=left+(right-left)/2;
        if(nums[mid]==target)return mid;
        //in a rotated array, there must be one half is sorted
        //we try to identify the sorted part out and start from dealng with it because it is easier
        if(nums[mid]<=nums[right]){
            //sorted part is right array
            if(target>nums[mid]&&target<=nums[right])
                return searchRec(nums,target,mid+1,right);//we know mid is not the selection because otherwise it's returned
            else//it's not in right, then it is in left
                return searchRec(nums,target,left,mid-1);
        }else{//then sorted part is in left
            if(target>=nums[left]&&target<nums[mid])//it is within sorted left array
                return searchRec(nums,target,left,mid - 1);
            else//if not, it is in right part
                return searchRec(nums,target,mid+1,right);
        }

    }
}
