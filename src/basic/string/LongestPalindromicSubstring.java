package basic.string;

public class LongestPalindromicSubstring {
	//get palindrome basing index. there are two, it returns longer one
	private static String getPalinedrome(String str, int index){
		if(str==null || str.length()==0||index<0||index>=str.length())return "";
		
		//char at index
		String curPalindrome=str.substring(index,index+1);//
		String longestStr=curPalindrome;
		//even, center is in between index and index-1
		int left = index-1,right=index;
		while(left>=0 && right<str.length()&&str.charAt(left)==str.charAt(right)){
			curPalindrome = str.substring(left,right+1);
			if(curPalindrome.length()>longestStr.length()){
				longestStr = curPalindrome;
			}
			left--;right++;
		}
		//odd, center is index
		left = index-1;
		right = index+1;
		while(left>=0 && right<str.length()&&str.charAt(left)==str.charAt(right)){
			curPalindrome = str.substring(left,right+1);
			if(curPalindrome.length()>longestStr.length()){
				longestStr = curPalindrome;
			}
			left--;right++;
		}
		return longestStr;
	}
	public static String getLPS(String str){
		String lps="";
		String curPs = "";
		for(int i=0;i<str.length();i++){
			curPs = getPalinedrome(str,i);
			if(curPs.length()>lps.length()){
				lps = curPs;
			}
		}
		return lps;
	}
	static String getLongestPalinedrome(String str){
		if(str==null || str.length()==0)return "";
		
		//char at index
		String curPalindrome="";
		String longestStr=curPalindrome;
		//even, center is in between index and index-1
		int left = 0,right=0;
		for(int i=0;i<str.length();i++){
			//itself
			curPalindrome = str.substring(i,i+1);
			if(longestStr.length()<curPalindrome.length()){
				longestStr = curPalindrome;
			}
			//even 
			left=i;
			right=i+1;
			while(left>=0 && right<str.length()&&str.charAt(left)==str.charAt(right)){
				curPalindrome = str.substring(left,right+1);
				//here you can take all palindromes in a set
				if(curPalindrome.length()>longestStr.length()){
					longestStr = curPalindrome;
				}
				left--;right++;
			}
			//odd, center is index
			left = i-1;
			right = i+1;
			while(left>=0 && right<str.length()&&str.charAt(left)==str.charAt(right)){
				//here you can take all palindromes in a set
				curPalindrome = str.substring(left,right+1);
				if(curPalindrome.length()>longestStr.length()){
					longestStr = curPalindrome;
				}
				left--;right++;
			}
		}
		return longestStr;
	}
	public static void main(String[] args){
		System.out.println(getLPS("abadefed"));
		System.out.println(getLongestPalinedrome("abaed"));
	}
}
