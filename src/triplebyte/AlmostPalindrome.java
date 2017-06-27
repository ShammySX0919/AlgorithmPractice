package triplebyte;

/**
 * Created by andrew on 6/18/2017.
 */
public class AlmostPalindrome {
    public static int almost_palindromes(String str) {
        int diff = 0;
        int left=0,right=str.length()-1;
        while(left<right){
            if (str.charAt(left)!=str.charAt(right)) diff++;
            left++;
            right--;
        }
        return diff;
    }
    public static void main(String args[]){
        System.out.println(almost_palindromes("abcd"));
    }
}
