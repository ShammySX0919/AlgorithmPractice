package expedia.online;

public class BalancedSales {
public static int getBalancePoint(int sales[]){
	
	int l=0,r=sales.length-1;
	long leftSum=sales[0];
	long rightSum=sales[sales.length-1];
	
	while(l<r){
		if(leftSum==rightSum&&r-l==2)return l+1;
		else if(leftSum<rightSum){l++;leftSum+=sales[l];}
		else {r--;rightSum+=sales[r];}
	}
	return -1;
}
public static void main(String[] args){
	int[] sales = new int[]{1,2,3,6,4,2};
	System.out.println(3==getBalancePoint(sales));
}
}
