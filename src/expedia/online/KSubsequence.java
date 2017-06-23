package expedia.online;

import java.util.HashMap;
import java.util.Map;

public class KSubsequence {
	//similar to subarray of sum k
	int numOfKSubSequence(int k, int[] arr){
		if(arr==null||arr.length==0)return 0;
		int cnt=0;
		long rSum=0;
		Map<Long,Integer> rSumStats = new HashMap<>();
		for(int i=0;i<arr.length;i++){
			rSum+=arr[i];
			rSumStats.put(rSum, i);
			int n=1;
			while(rSum-k*n>0){
				if(rSumStats.containsKey((rSum-k*n))&&(rSum-k*n)%k==0)cnt++;
				n++;
			}
		}
		return cnt;
	}

}
