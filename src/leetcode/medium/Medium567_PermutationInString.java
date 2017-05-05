package leetcode.medium;

public class Medium567_PermutationInString {
	 public boolean checkInclusion(String s1, String s2) {
	        if(s2.length()<s1.length())return false;
	        int[] stats = new int[26];
	        int[] newStats = null;
	        for(int i=0;i<s1.length();i++){
	            stats[s1.charAt(i)-'a']++;
	        }
	        //from each s2's position that could make a substring same length of s1
	        for(int i=0;i<s2.length()-s1.length()+1;i++){
	            newStats = new int[26];
	            //collect statistics for substring of length of s1
	            for(int j=i;j<i+s1.length();j++){
	                newStats[s2.charAt(j)-'a']++;
	            }
	            if(areStatsSame(stats,newStats))return true;
	        }
	        return false;
	    }
	    private boolean areStatsSame(int[] a, int[] b){
	        for(int i=0;i<a.length;i++){
	            if(a[i]!=b[i])return false;
	        }
	        return true;
	    }
	    public static void main(String[] args){
	    	Medium567_PermutationInString o = new Medium567_PermutationInString();;
	    	System.out.println(o.checkInclusion("adc", "dcda"));
	    }
	}