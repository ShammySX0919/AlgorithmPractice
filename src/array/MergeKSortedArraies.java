package array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * two methods:
 * 1. supported with min heap. heaptify cost is log(k)
 * if maximum length of array is n, then cost is nk(lg(k))
 * <p>
 * 2.another simple way: copy all arrays to output array of size n*k, then sort
 * output array in nk(lg(nk))
 * Created by Andrew Ma on 1/5/2017.
 */
class ArrayAndIndex implements Comparable<ArrayAndIndex> {
    int[] arr;//this solution is for int array
    int index;

    public ArrayAndIndex(int[] arr, int index) {
        this.arr = arr;
        this.index = index;
    }

    //ascending order
    @Override
    public int compareTo(ArrayAndIndex o) {
        return this.arr[this.index] - o.arr[o.index];
    }
}

public class MergeKSortedArraies {
    public static int[] mergeKSortedArray(int[][] arrs) {
        PriorityQueue<ArrayAndIndex> heap = new PriorityQueue<ArrayAndIndex>();
        int ttlElements = 0;
        for (int i = 0; i < arrs.length; i++) {
            heap.add(new ArrayAndIndex(arrs[i], 0));
            ttlElements += arrs[i].length;
        }
        int[] result = new int[ttlElements];
        int resultInx = 0;
        while (!heap.isEmpty()) {
            ArrayAndIndex ai = heap.poll();
            result[resultInx++] = ai.arr[ai.index];
            //adding next element in same array
            if (ai.index < ai.arr.length - 1) {
                heap.add(new ArrayAndIndex(ai.arr, ai.index + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] arr3 = {0, 9, 10, 11};

        int[] result = mergeKSortedArray(new int[][]{arr1, arr2, arr3});
        System.out.println(Arrays.toString(result));
    }
}
