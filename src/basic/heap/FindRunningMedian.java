package basic.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FindRunningMedian {
    //this is correct but timeout
    private static void printMedian2(int[] a,int length){
        if(a==null||a.length==0){
            throw new IllegalArgumentException();
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int i=0;i<length;i++){
            pq.add(a[i]);
        }
        int steps = length/2;
        boolean isOdd = length%2>0;
        if(isOdd){
            while(steps>0){
                pq.poll();
                steps--;
            }
            System.out.printf("%.1f\n", pq.poll()*1.0);
        }else{
            int one = 0,two=0;
            while(steps>0){
                one=pq.poll();
                steps--;
            }
            two=pq.poll();
            System.out.printf("%.1f\n", (one+two)*1.00/2);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>(100,
           new Comparator<Integer>(){
            public int compare(Integer i1,Integer i2){
                return i2-i1;
            }
        });
        for(int a_i=0; a_i < n; a_i++){
            if(minQ.size()<=maxQ.size()){
                minQ.add(in.nextInt());
            }else{
                maxQ.add(in.nextInt());
            } 
            //rebalance the queues
                //make sure max queue is less than min queue
           while(minQ.size()>0&& maxQ.size()>0 && maxQ.peek()>minQ.peek()){
                int tmp = minQ.poll();
                maxQ.add(tmp);
                minQ.add(maxQ.poll());
            }
            if((a_i+1)%2==0){
                System.out.printf("%.1f\n", (minQ.peek()+maxQ.peek())*1.00/2);
            }else{
                System.out.printf("%.1f\n", minQ.peek()*1.00);
            }
        }
    }
}
