package expedia.online;

public class SeperatingStudents {
//this is insertion sort, but count number of swaps. 
	static int commonInsersionSort(int arr[],boolean asc)
    {	int cnt = 0;
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
        	int j=i;
            while (j>0 && (asc?arr[j-1] > arr[j]:arr[j-1] < arr[j]))
            {
            	int tmp=arr[j];
            	arr[j]=arr[j-1];
                arr[j-1] = tmp;
                cnt++;
                j--;
            }
            
        }
        return cnt;
    }
	static int minMove(int[] arr){
		int[] arrClone = new int[arr.length];
		System.arraycopy(arr, 0, arrClone, 0, arr.length);
		return Math.min(commonInsersionSort(arr,true),commonInsersionSort(arrClone,false));
	}
	void quickerInsersionSort(int arr[])
    {
		int cnt=0;
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }
	public static void main(String[] args){
		int[] arr = new int[]{1,1,1,1,0,0,0};
		System.out.println(0==minMove(arr));
		arr = new int[]{0,1,1,1,0,0,0};
		System.out.println(3==minMove(arr));
	}
}
