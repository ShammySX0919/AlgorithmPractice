package amazon.medium;

/**
 * this is using quick selection algorithm, derived from quick sort
 * selecting kth smallest is equivelent to n-k th largest
 * Created by andrew on 6/13/2017.
 */
public class KthSmallestInUnsortedArray {
    /**
     * quicksort partition:
     * 1.range is the part of array to deal with(divid and conquer)
     * 2.this method use first element in range as a pivot
     * 3. returned value is index of pivot and pivot is in right position and won't change anymore
     * @param a
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] a, int left, int right) {
        int i = left+1;//moving towards right, skip first because that's pivot value
        int j = right;//moving towards left
        int pivot = a[left];
        while(true) {//a[left] is pivot, someone might use right one, and someone randomly pick one
            //move i, until it's a position that's no longer less than pivot
            while(i < right && a[i] < pivot)i++;
            //move j, until it's a position that is no longer greater than pivot
            while(j > left && pivot < a[j])j--;
            if(i >= j) {//this is important:only swap when i <j
                break;
            }//
            swap(a, i, j);//now a[i]>=pivot and a[j] <=pivot, swap them to put them into right range
        }
        swap(a, left, j);//finally put left/pivot to right position
        return j;
    }
    /**
     * basic array two elements swap
     * @param a
     * @param i
     * @param j
     */
    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    /**
     * QuickSelect. partially in-place sorted
     * Use partition algorithm in Quick Sort
     */
    public int findKthSmallest(int[] nums, int k) {
        //k = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            final int j = partition(nums, l, r);//j's value is figured out
            if(j < k) {
                l = j + 1;//j and its left are guaranteed to be less or equal to nums[j]
            } else if (j > k) {
                r = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    /**
     * QuickSelect
     * Use partition algorithm in Quick Sort
     */
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        findKthSmallest(nums, nums.length-k);
        return nums[k];
    }
}
