package hackerrank.woc;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * my thought was correct. but code has defects to pass some test cases.
 * defects:wrong calculation on lcm.There's no reason, but I used a.length-1 in for loop. it should be just a.length!
 * 
 * 1. find set a's least common multiply. l
 * 2. find set b's greatest common divisor. g
 * 3. find eligible numbers between g and l
 * @author Andrew Ma
 *
 */
public class BetweenTwoSets {

private static int gcd(int a, int b){
	 BigInteger b1 = BigInteger.valueOf(a);
	 BigInteger b2 = BigInteger.valueOf(b);
	 return b1.gcd(b2).intValue();
}
private static int lcm(int a, int b){
	if(a==0 || b==0)throw new IllegalArgumentException("number is zero");
	return a*b/gcd(a,b);
}

private static int gcd(int[] a){
    if(a==null || a.length==0)throw new IllegalArgumentException("Array is empty");
    if(a.length==1)return a[0];
    int gcd=a[0];
    for(int i=0;i<a.length;i++){
        gcd = gcd(gcd,a[i]);
    }
    return gcd;
}
private static int lcm(int[] a){
	if(a==null || a.length==0)throw new IllegalArgumentException("Array is empty");
	if(a.length==1)return a[0];
	int lcm=a[0];
    for(int i=0;i<a.length;i++){
        lcm = lcm(lcm,a[i]);
    }
    return lcm;
}

public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int[] a = new int[n];
    for(int a_i=0; a_i < n; a_i++){
        a[a_i] = in.nextInt();
    }
    int[] b = new int[m];
    for(int b_i=0; b_i < m; b_i++){
        b[b_i] = in.nextInt();
    }
    
    int bGcd = 0;
    int aLcm = 0;
    try{
    	bGcd = gcd(b);
    	aLcm = lcm(a);
    	if(aLcm>100){//bigger than number in b
    		System.out.println(0);
    		return;
    	}
    }catch(Exception e){
        System.out.println(0);
        return;
    }

    int num=0;
    
    if(bGcd%aLcm!=0){
        System.out.println(0);
        return;
    }else{
    	//
        for(int i=1;i*aLcm<=bGcd;i++){
          if(bGcd%(i*aLcm)==0){
              num++;
          }  
        }
        System.out.println(num);
    }

}
}
