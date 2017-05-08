package triplebyte;

/**
 * Created by andrew on 5/7/2017.
 */
public class NumberOfDifferenceBetweenStringAndItsReverse {
    public static int numOfDiff(String str){
        if(str==null||str.trim().equals(""))return 0;
        int l=0,r=str.length()-1;
        int sumDiff = 0;
        while(l<r){
            if(str.charAt(l)!=str.charAt(r)){
                sumDiff+=2;
            }

            l++;r--;
        }
        return sumDiff;
    }
    public static void main(String[] args){
        //2
        System.out.println(NumberOfDifferenceBetweenStringAndItsReverse.numOfDiff("abc"));
        //2
        System.out.println(NumberOfDifferenceBetweenStringAndItsReverse.numOfDiff("ab"));
        //2
        System.out.println(NumberOfDifferenceBetweenStringAndItsReverse.numOfDiff("abbda"));
        //4
        System.out.println(NumberOfDifferenceBetweenStringAndItsReverse.numOfDiff("abcde"));
        //6
        System.out.println(NumberOfDifferenceBetweenStringAndItsReverse.numOfDiff("abcdef"));
    }
}
