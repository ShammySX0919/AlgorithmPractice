package amazon.easy;

public class TheOnlyNumberThatAppearsOddTimes {
   public int findOddTimeAppearence(int[] a){
	   int res = 0;
	   for(int n:a){
		   res^=n;
	   }
	   return res;
   }
}
