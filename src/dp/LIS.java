package dp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by andrew on 30/09/16.
 */
public class LIS {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		int[] arrayOfNum = new int[n];
		for (int i = 0; i < n; i++) {
			arrayOfNum[i] = Integer.valueOf(in.nextLine());
		}
		in.close();
		// lis(arrayOfNum);
		// lisNlgN(arrayOfNum);
		List<List<Integer>> r = getLis(arrayOfNum);
		System.out.println(r);
	}

	/**
	 * this is quadratic time, runs time out
	 */
	private static void lis(int[] arr) {
		int[] dpLis = new int[arr.length];
		// initialize them with 1, the start length
		for (int i = 0; i < arr.length; i++) {
			dpLis[i] = 1;
		}
		// from bottom up, no duplicate calculation as compared with top down
		// approach
		for (int upBound = 1; upBound < arr.length; upBound++) {
			for (int bottomBound = 0; bottomBound < upBound; bottomBound++) {
				// increase when condition satisfied
				// this also guarantees the dpLis[upBound] is max of all
				// dpLis[bottomBound]+1
				if (arr[upBound] > arr[bottomBound]
						&& dpLis[upBound] < dpLis[bottomBound] + 1) {
					dpLis[upBound] = dpLis[bottomBound] + 1;
				}
			}
		}
		// find max in a loop
		int maxLis = 0;
		for (int i = 0; i < arr.length; i++) {
			maxLis = Math.max(maxLis, dpLis[i]);
		}
		System.out.println(maxLis);
	}

	/**
	 * under help of binary search for each number to determine it's position in
	 * a list that keeps updating. very good thought!
	 */
	private static void lisNlgN(int[] nums) {
		if (nums == null || nums.length == 0)
			return;
		// list can be naturally an array, but I found using list is easier to
		// understand
		ArrayList<Integer> auxList = new ArrayList<Integer>();

		for (int num : nums) {
			// adding the num to tail of list if list is empty or num is greater
			// than current tail of list
			if (auxList.size() == 0 || num > auxList.get(auxList.size() - 1)) {
				auxList.add(num);
			} else {
				// if number is smaller than current tail of list, try to find a
				// position to insert it to list
				int left = 0; // left boundary
				int right = auxList.size() - 1;// right boundary. these two make
												// the length of current list
				// do binary search to determine position of current num in the
				// list
				// you can also use java's binary search method on collections
				// to return expected position
				// the position returned indicates the current num is less than
				// the one already there
				// overwriting the existing one with this smaller one
				// this is also an algorithm of using binary search to determine
				// insertion position of a given number
				while (left < right) {
					int mid = (left + right) / 2;
					if (auxList.get(mid) < num) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				// after this, the right is num's expected position in existing
				// list
				// overwrite the value in that position: it's smaller than the
				// value being overwritten!
				// indices in auxList does not reflects a list of longest
				// increasing sequence, but its size is
				// the correct number of LIS
				auxList.set(right, num);
			}
		}

		System.out.println(auxList.size());
	}

	/**
	 * return the LIS. it can be multiple ones Borrowing the ideas from
	 * http://www
	 * .geeksforgeeks.org/longest-monotonically-increasing-subsequence-
	 * size-n-log-n/. keep the list then you get the result.
	 * 
	 * @param nums
	 * @return
	 */
	static List<List<Integer>> getLis(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0)
			return result;
		// not optimized, mainly for showing the ideas
		for (int i = 0; i < nums.length; i++) {
			if (isSmallest(result, nums[i])) {
				ArrayList<Integer> l = new ArrayList<Integer>();
				l.add(nums[i]);
				result.add(l);
			} else if (isBiggest(result, nums[i])) {
				cloneAndExtendToLongest(result, nums[i]);
			} else {
				// now num falls into middle of some lists
				cloneExtendAndDiscard(result, nums[i]);
			}
		}

		int maxLen = Integer.MIN_VALUE;
		for (List<Integer> l : result) {
			if (maxLen < l.size()) {
				maxLen = l.size();
			}
		}
		// remove the ones less then maxLen
		Iterator<List<Integer>> it = result.iterator();
		while (it.hasNext()) {
			if (maxLen > it.next().size()) {
				it.remove();
			}
		}
		return result;
	}

	private static void cloneExtendAndDiscard(List<List<Integer>> result,
			int num) {
		// find a list which the end element is largest that is smaller than num
		List<Integer> largestSmallerList = null;
		int maxLast = Integer.MIN_VALUE;
		for (List<Integer> l : result) {
			if (largestSmallerList == null
					|| largestSmallerList.get(largestSmallerList.size() - 1) < num
					&& maxLast < largestSmallerList.get(largestSmallerList
							.size() - 1)) {
				largestSmallerList = l;
				maxLast = largestSmallerList.get(largestSmallerList.size() - 1);
			}
		}
		// clone it
		List<Integer> clone = new ArrayList<Integer>();
		clone.addAll(largestSmallerList);
		clone.add(num);

		// then discard other ones with same length
		Iterator<List<Integer>> it = result.iterator();
		while (it.hasNext()) {
			List<Integer> l = it.next();
			if (l.size() == clone.size()) {
				it.remove();
			}
		}
		result.add(clone);
	}

	private static void cloneAndExtendToLongest(List<List<Integer>> result,
			int num) {
		List<Integer> ll = null;
		for (List<Integer> l : result) {
			if (ll == null || l.size() > ll.size()) {
				ll = l;
			}
		}
		// clone the longest list
		List<Integer> clone = new ArrayList<Integer>();
		clone.addAll(ll);
		clone.add(num);
		result.add(clone);
	}

	/**
	 * check if given number is smaller than smallest numbers in given list
	 * 
	 * @param list
	 * @param num
	 * @return
	 */
	private static boolean isSmallest(List<List<Integer>> list, int num) {
		for (List<Integer> l : list) {
			if (l.size() > 0
					&& Integer.valueOf(l.get(0).toString()).intValue() <= num) {
				return false;
			}
		}
		return true;
	}

	private static boolean isBiggest(List<List<Integer>> list, int num) {
		for (List<Integer> l : list) {
			if (l.size() > 0
					&& Integer.valueOf(l.get(l.size() - 1).toString())
							.intValue() >= num) {
				return false;
			}
		}
		return true;
	}
}

/*
 * input 
9
2
5
3
7
11
8
10
13
6
 
 */