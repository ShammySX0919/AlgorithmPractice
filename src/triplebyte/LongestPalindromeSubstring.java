package triplebyte;

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
}
