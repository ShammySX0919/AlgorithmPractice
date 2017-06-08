package amazon.hard;

import java.util.*;

/**
 * it is DFS. as long as it is based on last step, you can think about DFS
 *
 * Created by andrew on 6/7/2017.
 */
public class LeetCodeHard403_FrogJump {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {return false;}
        int n = stones.length;
        if (n == 1) {return true;}//already on last stone, which is also first stone
        if (stones[1] != 1) {return false;}//first step must be 1
        if (n == 2) {return true;}//secnd step can be any unit and it lands on last stone
        int last = stones[n - 1];
        HashSet<Integer> hs = new HashSet();
        for (int i = 0; i < n; i++) {
            if (i > 3 && stones[i] > stones[i - 1] * 2) {return false;} // The two stones are too far away.
            hs.add(stones[i]);
        }
        return canReach(hs, last, 1, 1);
    }

    private boolean canReach(HashSet<Integer> hs, int lastPosition, int curPos, int lastJump) {
        //3 way of jumping
        if (curPos + lastJump - 1 == lastPosition || curPos + lastJump == lastPosition || curPos + lastJump + 1 == lastPosition) {
            return true;
        }
        //it is recursion, just calmly go through all situations one by one
        //jumping lastJump+1 steps
        if (hs.contains(curPos + lastJump + 1)) {
            if (canReach(hs, lastPosition, curPos + lastJump + 1, lastJump + 1)) {return true;}
        }
        if (hs.contains(curPos + lastJump)) {
            if (canReach(hs, lastPosition, curPos + lastJump, lastJump)) {return true;}
        }
        if (lastJump > 1 && hs.contains(curPos + lastJump - 1)) {
            if (canReach(hs, lastPosition, curPos + lastJump - 1, lastJump - 1)) {return true;}
        }
        //after trying all possibilities, return false
        return false;
    }
    //below is my adjacency list solution
    public boolean canCrossMine(int[] stones) {
        Objects.requireNonNull(stones);
        if(stones.length==1)return true;//one stone means it already on last
        if(stones.length==2) return (stones[1]==1?true:false);//first step must be 1
        //this is an adjacency list. and our target is to figure out
        //if there's a path to a node following certain restrictions
        //Controversy to traditional use of adjacency list, this list is built up on the fly
        Map<Integer, Set<Integer>> validMovements = new HashMap<Integer, Set<Integer>>();
        //each stone is a vertex in graph
        for(int i:stones){
            validMovements.put(i, new HashSet<Integer>());
        }
        validMovements.get(0).add(1);
        return dfs(validMovements,1,1,stones[stones.length-1]);
    }
    private boolean dfs(Map<Integer, Set<Integer>> validPositions, int curPosition, int lastJumpUnits, int endPosition){
        boolean reachable = false;
        for(int i=-1;i<=1;i++){//three way of jumping -1,0, 1 based on last jump
            int nextPosition = curPosition + lastJumpUnits+i;
            if(nextPosition==endPosition){
                //get answer, no need to add steps
                return true;
            }
            //lastJumpUnits+i=0, not jumping, will make infinite loop so to avoid it
            if(lastJumpUnits+i>0 && validPositions.containsKey(nextPosition)
                    && !validPositions.get(nextPosition).contains(lastJumpUnits+i)){
                //we go here only when nextPosition is valid and nextPosition's movement has not been done
                validPositions.get(curPosition).add(lastJumpUnits+i);//adding steps that has tried
                reachable = dfs(validPositions,nextPosition,lastJumpUnits+i,endPosition);
                if(reachable)break;
            }
        }

        return reachable;
    }
}
