package misc;

/**
 * 给定一个n个数，告诉你数的range是1 到 n-1，有且仅有一个duplicate，如何找到它。如果这个array是sorted，能有什么优化
 * Created by andrew on 6/18/2017.
 */
public class NNumber1Dup {
    static int getDupNum(int arr[]){
        long sum = 0;
        int n = arr.length;
        long sumOf1Ton = (1+n)*n/2;
        for(int i:arr)sum+=i;
        //this is the formula(observed from some samples)
        //also because, if a number is duplicated, all its subsequent number will be 1 less than original sequence
        //that's why n-(sum of difference) is the duplicated number
        //1,2,3,4
        //1,1,2,3
        //1,2,2,3
        //1,2,3,3
        return (int)(n - (sumOf1Ton-sum));
    }
    public static void main(String[] str){
        System.out.println(getDupNum(new int[]{1,1,2,3}));
        System.out.println(getDupNum(new int[]{1,2,2,3}));
        System.out.println(getDupNum(new int[]{1,2,3,3}));
    }
}
