package triplebyte;

/**
 * Created by andrew on 6/18/2017.
 */
public class AlmostPalindrome {
    public static int almost_palindromes(String str) {
        int diff = 0;
        int N = str.length();
        for(int i=0;i<N;i++){
            if (str.charAt(i)!=str.charAt(N-i-1)) diff++;
        }
        return diff;
    }
}
