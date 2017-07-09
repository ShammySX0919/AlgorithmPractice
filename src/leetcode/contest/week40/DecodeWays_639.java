package leetcode.contest.week40;

public class DecodeWays_639 {

	    int mo=1000000007;
	    public int numDecodings(String s) {
	        int n=s.length();
	        long[] f=new long[n+1];
	        f[0]=1;
	        for (int i=1;i<=n;i++)
	        {
	            char ch=s.charAt(i-1);
	            if (ch=='*') f[i]=(f[i-1]*9)%mo;  // * can represent 1,2,3...9
	            else f[i]=(ch=='0')?0:f[i-1];
	            if (i>1)
	            {
	                char ch2=s.charAt(i-2);
	                if (ch2=='*')
	                {
	                    if (ch=='*') f[i]=(f[i]+f[i-2]*15)%mo; // "**" can represent 11~26 except 20 (15 number in total)
	                    else if (ch>='7') f[i]=(f[i]+f[i-2])%mo; // previous * can only be 1
	                         else f[i]=(f[i]+f[i-2]*2)%mo; // previous * can be 1 or 2
	                }
	                if (ch2=='1')
	                {
	                    if (ch=='*') f[i]=(f[i]+f[i-2]*9)%mo; else f[i]=(f[i]+f[i-2])%mo;
	                }
	                if (ch2=='2')
	                {
	                    if (ch=='*') f[i]=(f[i]+f[i-2]*6)%mo;
	                    else if (ch<'7') f[i]=(f[i]+f[i-2])%mo;
	                }
	            }
	        }
	        return (int)f[n];
	    }
	}

/*
639. Decode Ways II


Difficulty: Hard

A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:

Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".

Example 2:

Input: "1*"
Output: 9 + 9 = 18

Note:

The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.

*/