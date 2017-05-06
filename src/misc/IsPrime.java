package misc;

import java.util.Scanner;
/**
 * prime is >1 and only divided by 1 and itself 
 * i is not a prime
 * check from 2 to sqrt(n)
 * O(sqrt(n))
 * @author Andrew
 *
 */
public class IsPrime {
    private static boolean isPrime(int n){
        boolean isPrime = false;
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
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        //in.nextLine();
        
        for(int a0 = 0; a0 < p; a0++){
            boolean isPrime = true;
            int n = in.nextInt();
            isPrime = isPrime(n);
            System.out.println(isPrime?"Prime":"Not prime");
            //if(in.hasNextLine()){
              //  in.nextLine();
            //}
        }
        
    }
}
/**
input:
30
1
4
9
16
25
36
49
64
81
100
121
144
169
196
225
256
289
324
361
400
441
484
529
576
625
676
729
784
841
907
*/