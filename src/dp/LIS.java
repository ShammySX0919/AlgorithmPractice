package dp;

/**
 * Created by andrew on 30/09/16.
 */
public class LIS {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[] arrayOfNum = new int[n];
        for (int i = 0; i < n; i++) {
            arrayOfNum[i] = Integer.valueOf(in.nextLine());
        }
        in.close();
        //lis(arrayOfNum);
        lisNlgN(arrayOfNum);
    }

    /**
     * this is quadratic time, runs time out
     */
    private static void lis(int[] arr) {
        int[] dpLis = new int[arr.length];
        //initialize them with 1, the start length
        for (int i = 0; i < arr.length; i++) {
            dpLis[i] = 1;
        }
        //from bottom up, no duplicate calculation as compared with top down approach
        for (int u = 1; u < arr.length; u++) {
            for (int d = 0; d < u; d++) {
                //increase when condition satisfied
                if (arr[u] > arr[d] && dpLis[u] < dpLis[d] + 1) {
                    dpLis[u] = dpLis[d] + 1;
                }
            }
        }
        //find max in a loop
        int maxLis = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLis = Math.max(maxLis, dpLis[i]);
        }
        System.out.println(maxLis);
    }

    /**
     * under help of binary search for each number to determine it's positon in
     * a list that keeps updating. very good thought!
     */
    private static void lisNlgN(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        //list can be natually an array
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int num : nums) {
            //adding the num to tail of list if list is empty or num is greater than current tail of list
            if (list.size() == 0 || num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                //if number is smaller than current tail of list, try to find a position to insert it to list
                int i = 0; //left boundary
                int j = list.size() - 1;//right boundary. these two make the length of current list
                //do binary search to determine position of num in the list
                //you can also use java's binary search method on collections to return expected position
                while (i < j) {
                    int mid = (i + j) / 2;
                    if (list.get(mid) < num) {
                        i = mid + 1;
                    } else {
                        j = mid;
                    }
                }
                //after this, the j is num's expected postion in existing list
                //overwrite the value in that postion: good! it's smaller than the value being overwriten!
                //so to keep the sequence effect for all rest of numbers
                list.set(j, num);
            }
        }

        //return list.size();
        System.out.println(list.size());
    }
}