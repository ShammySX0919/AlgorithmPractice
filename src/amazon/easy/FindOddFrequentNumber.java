package amazon.easy;

/**
 * Created by andrew on 5/22/2017.
 */
public class FindOddFrequentNumber {
    //an array contains number occurs mostly even times, except one that occurs odd times, find that numbers
    //XOR the elements in array to find that number appears odd times
    public static int findOddFrequentNumber(int[] values){
        int sum = 0;
        for(int i:values)
            sum^=i;
        return sum;
    }
    public static void main(String[] args){
        int[] v = new int[]{1,1,2,2,4,4,5,6,6,7,7,9,9,13,13};
        System.out.println(findOddFrequentNumber(v));
    }
}
