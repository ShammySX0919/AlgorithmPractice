package leetcode.contest.week31;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrew on 5/6/2017.
 */
public class No575_DistributeCandies {
    public int distributeCandies(int[] candies) {
        if(candies==null||candies.length==0)return 0;
        Map<Integer,Integer> stats = new HashMap<>();
        //collect stats
        for(int i:candies){
            if(stats.containsKey(i))
                stats.put(i,stats.get(i)+1);
            else{
                stats.put(i,1);
            }
        }
        return Math.min(candies.length/2,stats.size());
    }
}
