package amazon;

import java.util.Arrays;

/**
 * Created by andrew on 5/5/2017.
 */
public class FirstNPrime {
    private static boolean isPrime(int n){
        boolean isPrime = true;
        if(n==1){
            isPrime=false;
        }else{
            for(int i=2;i*i<=n;i++){
                if((n%i)==0){
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }
    public static int[] firstNPrime(int n){
        int[] result = new int[n];
        int i = 1,cnt = 0;
        while(cnt<n){
            while(!isPrime(i)){
                i++;//keep growing until it is a prime
            }
            //i is a prime, add it to result
            result[cnt] = i;
            cnt++;//increase counter
            i++;//proceed to next number
        }//until taking enough number of primes
        return result;
    }
    public static void main(String[] args){
        System.out.println(Arrays.toString(FirstNPrime.firstNPrime(0)));
        System.out.println(Arrays.toString(FirstNPrime.firstNPrime(1)));
        System.out.println(Arrays.toString(FirstNPrime.firstNPrime(5)));
    }
}
