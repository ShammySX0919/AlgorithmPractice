package leetcode.easy;
import java.util.HashMap;
import java.util.Map;


public class Easy1_TwoSum {


		  static int[] getIndicesOfItemWights(int[] arr, int limit) {
		    if(arr==null||arr.length<2)return null;
		    int[] res = new int[2];
		    // your code goes here
		    Map<Integer,Integer> stats = new HashMap<Integer, Integer>();
		    int seekingFor = 0;
		    for(int i=0;i<arr.length;i++){
		      seekingFor = limit - arr[i];
		      if(stats.containsKey(seekingFor)){
		        res[0] = i>stats.get(seekingFor)?stats.get(seekingFor):i;
		        res[1] = i>stats.get(seekingFor)?i:stats.get(seekingFor);
		        return res;
		      }else{
		        stats.put(arr[i],i);
		      }
		      
		    }
		    return null;
		  }

		  public static void main(String[] args) {
		    int[] arr = new int[]{4, 6, 10, 15, 16};
		    int[] res = getIndicesOfItemWights(arr,21);
		    System.out.printf("%d,%d",res[0],res[1]);
		  }

		}
