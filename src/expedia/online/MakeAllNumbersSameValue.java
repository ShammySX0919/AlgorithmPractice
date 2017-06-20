package expedia.online;

import java.util.Arrays;
//a formula is  total - a.length * min. total is sum of original array, min is min or original array
public class MakeAllNumbersSameValue {
//method: loop until min=max
	//in each loop, increase every elements exccept the maximum one,
	//using sort to figure out the max and min
	public static int numberOfTimeToBecomeEven(int[] arr){
		if(arr==null||arr.length<=1)return 0;
		Arrays.sort(arr);
		int ans = 0;
		while(arr[0]!=arr[arr.length-1]){
			for(int i=0;i<arr.length-1;i++){
				arr[i]++;
			}
			Arrays.sort(arr);
			ans++;//within loop, we increase ans to reflect the real times it moves
		}
		return ans;
	}
	public static void main(String[] args){
		int[] arr = new int[]{1,2,3,4,5,6};
		System.out.println(numberOfTimeToBecomeEven(arr));
		System.out.println(21-6*1);
	}
}
/*
一个数组长度为n，每次increment n-1 numbers （+1），此动作成为一个move 问最少需要几个move能让所有数相等
*/