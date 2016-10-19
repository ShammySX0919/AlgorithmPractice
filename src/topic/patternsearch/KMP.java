package topic.patternsearch;
/**
 * KMP (Knuth Morris Pratt) Pattern Searching
 * http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
 * O(n) because it does not check back
 * preprocess: analysis on pattern is to build an auxilary array that indicates 
 *  "longest proper prefix which is also suffix"
 * @author Andrew Ma
 *
 */
public class KMP {
	void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0
 
        // the loop calculates lps[i] for i = 1 to M-1
        while (i<M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            //If pat[i] and pat[len] do not match and len is not 0, we update len to lps[len-1]
            else  // (pat[i] != pat[len])
            {
                if (len != 0)
                {
                    // This is tricky. Consider the example
                    // AAACAAAA and i = 7.
                    len = lps[len-1];
 
                    // Also, note that we do not increment
                    // i here
                }
                else  // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
	void KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
 
        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int p = 0;  // index for pat[]
 
        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat,M,lps);
 
        int i = 0;  // index for txt[]
        while (i < N)
        {
            if (pat.charAt(p) == txt.charAt(i))
            {
                p++;
                i++;
            }
            if (p == M)
            {
                System.out.println("Found pattern "+
                              "at index " + (i-p));
                p = lps[p-1];
            }
 
            // mismatch after j matches
            else if (i < N && pat.charAt(p) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (p != 0)
                    p = lps[p-1];
                else
                    i = i+1;
            }
        }
    }
	 // Driver program to test above function
    public static void main(String args[])
    {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        new KMP().KMPSearch(pat,txt);
    }
}
