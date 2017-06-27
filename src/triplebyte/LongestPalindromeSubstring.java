package triplebyte;

import java.util.Objects;

public class LongestPalindromeSubstring {
	static int longestPalSubstring(String str)
	{
		if(str==null||str.length()==0)return 0;
	    int maxLength = 1;  //each character is a palindrome
	 
	    int start = 0;
	    int len = str.length();
	 
	    int left, right;
	 
	    // One by one consider every character as center point of even length palindromes
	    for (int i = 1; i < len; ++i)
	    {
	        // Find the longest even length palindrome with center points
	        // as i-1 and i.  
	        left = i - 1;
	        right = i;
	        while (left >= 0 && right < len && str.charAt(left) == str.charAt(right))
	        {
	        	//for each palindrome, collect new start and maxlength
	            if (right - left + 1 > maxLength)
	            {
	                start = left;
	                maxLength = right - left + 1;
	            }
	            --left;
	            ++right;
	        }
	 
	        // Find the longest odd length palindrome with center point as i
	        left = i - 1;
	        right = i + 1;
	        while (left >= 0 && right < len && str.charAt(left) == str.charAt(right))
	        {
	            if (right - left + 1 > maxLength)
	            {
	                start = left;
	                maxLength = right - left + 1;
	            }
	            --left;
	            ++right;
	        }
	    }
	 
	    System.out.println("Longest palindrome substring is: ");
	    System.out.println(str.substring(start, start + maxLength));
	 
	    return maxLength;
	}
	public static void main(String[] args){
		System.out.println(longestPalSubstring("forgeeksskeegfor"));
	}

	//this run out of time limit
	public String longestPalindrome(String s) {
		String longestPStr="";
		if(s.length()<=1)return s;
		for(int i=0;i<s.length();i++){
			String pStr = getPalindrome(s,i);
			if(longestPStr.length()<pStr.length())
				longestPStr = pStr;
		}
		return longestPStr;
	}

	private String getPalindrome(String s, int i){
		//center i
		int left=i-1,right=i+1,start=i,maxLen=1;
		String s1=null;

		while(left>=0 && right<s.length() &&s.charAt(left)==s.charAt(right)){
			start=left;
			maxLen = Math.max(maxLen,right-left+1);
			left--;
			right++;
		}
		s1 = s.substring(start,start+maxLen);
		maxLen=0;
		//i as left
		left=i;right=i+1;
		String s2= null;
		while(left>=0 && right<s.length() &&s.charAt(left)==s.charAt(right)){
			start = left;
			maxLen = Math.max(maxLen,right-left+1);
			left--;
			right++;
		}
		s2 = s.substring(start,start+maxLen);
		return s1.length()>s2.length()?s1:s2;
	}
	//this passed
	public String longestPalindromeBetter(String s) {
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
//extra
public static boolean isPalindromeDP(String s) {
	Objects.requireNonNull(s);
	if (s.length() <= 1)
		return true;

	// a little bit waste of space
	// they are initially false, so no need to init it in a loop
	boolean range[][] = new boolean[s.length()][s.length()];
	// range[i][j] is palindrome when s[i]=s[j] && range[i+1][j-1]
	// make sure i+1 and j-1 are in valid range
	for (int i = s.length() - 1; i >= 0; i--) {
		for (int j = i; j < s.length(); j++) {

			if (s.charAt(i) == s.charAt(j)
					//j-i<2 means they are adjacent, they share a virtual center
					&& (j - i < 2 || range[i + 1][j - 1])) {
				range[i][j] = true;
			}
		}
	}
	return range[0][s.length() - 1];
}
	public String longestPalindromeDP(String s) {
		int n = s.length();
		String res = null;

		boolean[][] dp = new boolean[n][n];//if string between i and j is palindrome
		//this solution starts from rear end. it could also start from head
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

				if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
					res = s.substring(i, j + 1);
				}
			}
		}

		return res;
	}

}
