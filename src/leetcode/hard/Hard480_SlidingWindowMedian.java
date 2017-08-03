package leetcode.hard;


import java.util.Collections;
import java.util.PriorityQueue;

public class Hard480_SlidingWindowMedian {
	
	public double[] medianSlidingWindow2(int[] nums, int k) {
		 
		double[] res = new double[nums.length - k + 1];
        int idx = 0;
        boolean kIsEven = k % 2 == 0;
        PriorityQueue<Integer> pqMin = new PriorityQueue<>((a, b)->{return (int)((double)b-a);});
        PriorityQueue<Integer> pqMax = new PriorityQueue<>();
        for(int i = 0; i<nums.length; i++){
            if(pqMin.size() + pqMax.size()==k){
                Integer toRemove = new Integer(nums[i-k]);
                if(toRemove <= pqMin.peek()) pqMin.remove(toRemove);
                else pqMax.remove(toRemove);
            }
            //always keep small.size() == big.size() or small.size() == big.size()+1
            if(pqMin.size()<=pqMax.size()) pqMin.add(nums[i]);
            else pqMax.add(nums[i]);
            
            if(pqMax.size()>0){
                while(pqMin.peek()>pqMax.peek()){
                    pqMax.add(pqMin.poll());
                    pqMin.add(pqMax.poll());
                }
            }
            
            if(pqMin.size() + pqMax.size()==k){
                if(kIsEven) res[idx++] = ((double)pqMin.peek() + pqMax.peek())/2.0;
                else res[idx++] = (double)pqMin.peek();
            }
        }
        return res;
    }
	//my solution
	public double[] medianSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length==0)return null;
		double[] res = new double[nums.length-k+1];
		PriorityQueue<Integer> pqMin = new PriorityQueue<>();
        PriorityQueue<Integer> pqMax = new PriorityQueue<>(Collections.reverseOrder());
        boolean kIsOdd = k%2==0?false:true;
        for(int i=0;i<nums.length;i++){//counting the right boundary of k sliding window
        	if(i<k-1){
        		pqMax.offer(nums[i]);
        	}else if (i==k-1){
        		pqMax.offer(nums[i]);
        		while(pqMax.size()>(kIsOdd?k/2+1:k/2))pqMin.offer(pqMax.poll());
        		res[0] = kIsOdd?pqMax.peek()*1d:((pqMin.isEmpty()?0:pqMin.peek()*1d)+pqMax.peek()*1d)/2;
        	}else{
        		pqMax.offer(nums[i]);
        		if(!pqMax.remove(nums[i-k]))pqMin.remove(nums[i-k]);//not in pqMin, it must be in pqMax
        		while(!pqMin.isEmpty() && !pqMax.isEmpty() && pqMin.peek()<=pqMax.peek())
        			pqMin.offer(pqMax.poll());
        		while(pqMin.size()>pqMax.size())
        			pqMax.offer(pqMin.poll());//make sure max queue always contains no less than min queue
        		while(pqMax.size()>(kIsOdd?k/2+1:k/2))pqMin.offer(pqMax.poll());
        		res[i-k+1] = kIsOdd?pqMax.peek()*1d:(pqMin.peek()*1d+pqMax.peek()*1d)/2d;
        	}
        }
        
        return res;
    }
	public static void main(String[] args){
		Hard480_SlidingWindowMedian o = new Hard480_SlidingWindowMedian();
		int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
		//int[] nums = new int[]{1,2};
		double[] res = o.medianSlidingWindow(nums, 3);
		System.out.println();

	}
}
