package leetcode.medium;

public class Leetcode3_extPermutation {
	
	    public void nextPermutation(int[] nums) {
	      if(nums.length<=1){
	          return;
	      }
	      
	      int i= nums.length-1;
	      
	      for(;i>=1;i--){
	         
	         if(nums[i]>nums[i-1]){ //find first number which is smaller than it's after number
	             break;
	         }
	      }
	      
	      if(i!=0){
	          swap(nums,i-1); //if the number exist,which means that the nums not like{5,4,3,2,1}
	      }
	      
	      reverse(nums,i);    
	    }
	    
	    private void swap(int[] a,int i){
	    	//the tail of array between i+1 and tail is reverse ordered
	        for(int j = a.length-1;j>i;j--){
	        	//so swap a[i] with first element that is greater it, which must be smallest in rear part of array
	            if(a[j]>a[i]){
	                int t = a[j];
	                a[j] = a[i];
	                a[i] = t;
	                break;
	            }
	        }
	    }
	    
	    private void reverse(int[] a,int i){//reverse the number after the number we have found
	        int first = i;
	        int last = a.length-1;
	        while(first<last){
	            int t = a[first];
	            a[first] = a[last];
	            a[last] = t;
	            first ++;
	            last --;
	        }
	    }
	    
	}

/*
在当前序列中，从尾端往前寻找两个相邻元素，前一个记为first，后一个记为second，并且满足first 小于 second。然后再从尾端寻找另一个元素number，如果满足first 小于number，即将第first个元素与number元素对调，并将second元素之后（包括second）的所有元素颠倒排序，即求出下一个序列

example:
6，3，4，9，8，7，1
此时 first ＝ 4，second = 9
从尾巴到前找到第一个大于first的数字，就是7
交换4和7，即上面的swap函数，此时序列变成6，3，7，9，8，4，1
再将second＝9以及以后的序列重新排序，让其从小到大排序，使得整体最小，即reverse一下（因为此时肯定是递减序列）
得到最终的结果：6，3，7，1，4，8，9

1.Start from its last element, traverse backward to find the first one with index i that satisfy num[i-1] < num[i]. 
So, elements from num[i] to num[n-1] is reversely sorted.
2.To find the next permutation, we have to swap some numbers at different positions, to minimize the increased amount, 
we have to make the highest changed position as high as possible. Notice that index larger than or equal to i 
is not possible as num[i,n-1] is reversely sorted. So, we want to increase the number at index i-1, clearly, 
swap it with the smallest number between num[i,n-1] that is larger than num[i-1]. 
For example, original number is 121543321, we want to swap the '1' at position 2 with '2' at position 7.
3.The last step is to make the remaining higher position part as small as possible, 
we just have to reversely sort the num[i,n-1]

*/