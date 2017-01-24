package leetcode.medium;

/**
 * Created by andrew on 23/01/17.
 */
public class Mediem486_PredictTheWiner {
    public boolean PredictTheWinner(int[] nums) {
        //check if players's final gain is greater than 0 between range l and r
        return pick(nums, 0, nums.length-1, new Integer[nums.length][nums.length])>=0;
    }

    /**
     * define pick be the result of current player's final gain
     * @param nums
     * @param l
     * @param r
     * @param mem
     * @return
     */
    private int pick(int[] nums, int l, int r, Integer[][] mem){
        if(mem[l][r]==null){
            /*
            if l==r, there is only one choice, let it add to player's result
            if player pick l, next player's final gain would be pick(nums,l+1,r,mem), current player's final gain is nums[l]-nextplayer's final gain between l+1 and r
             if player pick r, next player's final gain would be pick(nums,l,r-1,mem), current player's final gain is nums[r]-nextplayer's final gain between l and r-1
             between the two situations, pick maximum gain
             */
            mem[l][r] = (l==r ? nums[r] :
                    Math.max(nums[r]-pick(nums,l,r-1,mem),nums[l]-pick(nums,l+1,r,mem)));
        }
        return mem[l][r];
    }
}
