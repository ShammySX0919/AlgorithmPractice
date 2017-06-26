package zenifit.oa;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * using max priority queue to retrieve max value ticket every time
 * Created by andrew on 6/25/2017.
 */
public class SellTickets {
    public long sellTicket1(int[] arr, long m) {
        long res = 0;
        //max priority queue
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
        	//sell one ticket with value amt
            int amt = queue.poll();
            res += amt;
            queue.offer(amt - 1);//now that window's next price is amt-1
            tickets--;//adjust ticket quantity. or this could be not adding 0 amt and until queue is empty
        }
        return res;
    }
    }
