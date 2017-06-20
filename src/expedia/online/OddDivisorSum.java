package expedia.online;

import java.util.ArrayList;
import java.util.List;

public class OddDivisorSum {
//given an array, return sum of odd divisor to every number within array
public static int getOddDivisorSum(int arr[]){
	long sum = 0;
	for(int num:arr){
		List<Integer> oddDivisors = getOddDivisors(num);
		for(Integer i:oddDivisors)sum+=i;
	}
	return (int)sum;
}
private static List<Integer> getOddDivisors(int num){
	List<Integer> ans = new ArrayList<>();
	for(int i=1;i<=num;i++){
		if(num%i==0 && i%2!=0)ans.add(i);
	}
	return ans;
}
public static void main(String[] args){
	int[] arr = new int[]{3,4,20};
	System.out.println(getOddDivisorSum(arr));
}
}
