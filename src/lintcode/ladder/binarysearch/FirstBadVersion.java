package lintcode.ladder.binarysearch;

public class FirstBadVersion {
	
	   static class SVNRepo {
	      public static boolean isBadVersion(int k){return false;}
	  }
	  //you can use SVNRepo.isBadVersion(k) to judge whether 
	  //the kth code version is bad or not.
	
	
	    /**
	     * @param n: An integers.
	     * @return: An integer which is the first bad version.
	     */
	    public int findFirstBadVersion(int n) {
	        if(n==1)return 1;
	        if(n==2)return (SVNRepo.isBadVersion(1)?1:2);
	        // write your code here
	        //good thing about version is that they are sorted increasingly
	        int left=1, right=n;
	        boolean isBV = false;
	        while(left<=right){
	            int mid = left+(right-left)/2;
	            isBV = SVNRepo.isBadVersion(mid);
	            if(isBV){
	                //quit condition is !isBV and isBV next
	                if(mid-1>0 && !SVNRepo.isBadVersion(mid-1)){
	                    return mid;
	                }
	                //other wise,set this as right boundary
	                right = mid -1;//we know mid is not answer
	            }else{
	                //now mid is good version, we need to compare its next version
	                if(mid+1<=n && SVNRepo.isBadVersion(mid+1)){
	                    //good +bad, we find first bad
	                    return mid+1;
	                }
	                //otherwise,move left boundary
	                left=mid+1;
	            }
	        }
	        return left;//why left?because right can possible be 0
	    }
	
}
/*


The code base version is an integer start from 1 to n. One day, someone committed a bad version in the code case, so it caused this version and the following versions are all failed in the unit tests. Find the first bad version.

You can call isBadVersion to help you determine which version is the first bad one. The details interface can be found in the code's annotation part.
Notice

Please read the annotation in code area to get the correct way to call isBadVersion in different language. For example, Java is SVNRepo.isBadVersion(v)
Have you met this question in a real interview?
Example

Given n = 5:

isBadVersion(3) -> false
isBadVersion(5) -> true
isBadVersion(4) -> true

Here we are 100% sure that the 4th version is the first bad version.

*/