package zenifit.oa;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by andrew on 6/25/2017.
 */
public class SellTickets {
    public long sellTicket1(int[] arr, long m) {
        long res = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer el1, Integer el2) {
                return el2 - el1;
            }
        });
        for (int num : arr) {
            queue.offer(num);
        }
        long tickets = m;
        while (tickets > 0) {
            int count = queue.poll();
            res += count;
            queue.offer(count - 1);
            tickets--;
        }
        return res;
    }
    public  long sellTicket(int[] arr, long m) {
        long res = 0;
        int threshold = 0;
        int l = 0;
        int r = 0;
        for (int num : arr) {
            r = Math.max(num, r);
        }
        while (l <= r) {
            threshold = l + (r - l) / 2;
            long sum1 = getSum(threshold, arr);
            long sum2 = getSum(threshold + 1, arr);
            if (sum2 <= m && m <= sum1) {
                break;
            } else if (sum1 < m) {
                r = threshold;
            } else {
                l = threshold;
            }
        }
        long tickets = 0;
        for (int num : arr) {
            if (num >= threshold) {
                tickets += num - threshold + 1;
                res += ((long) (threshold + num) * (long) (num - threshold + 1)) / 2;
            }
        }
        res -= (tickets - m) * threshold;
        return res;
    }

    private long getSum(int threshold, int[] arr) {
        long res = 0;
        for (int num : arr) {
            res += num - threshold >= 0 ? num - threshold + 1 : 0;
        }
        return res;
    }
}
