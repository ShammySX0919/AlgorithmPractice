package leetcode;

/**
 * Created by Andrew Ma on 12/20/2016.
 */
public class Mediem477_HammingDistance {
    /*
    my brute-force solution, exceeding time limit of course
    public int totalHammingDistance(int[] nums) {
        int result = 0;
        if(nums==null||nums.length==1)return result;

        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                result+=hammingDistance(nums[i],nums[j]);
            }
        }
        return result;
    }
    private int hammingDistance(int num1,int num2){
        int xorResult = num1^num2;
        int cnter = 0;
        while(xorResult>0){
            int i = xorResult & 1;
            cnter+=i;
            xorResult=xorResult>>1;
        }
        return cnter;
    }
    */
    //better solution

    /**
     * for each bit position in 32bits integer, count the number of integers in the array which have that bit set as 1.
     * if there are n integers in array and k of them having particular bit set, then there are n-k not set, then that bit
     * contributes k*(n-k) hamming distance to the total distance.
     * O(n*32)~=O(n)
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j=0;j<32;j++) {
            int bitCount = 0;
            for (int i=0;i<n;i++) {
                bitCount += (nums[i] >> j) & 1;
            }
            total += bitCount*(n - bitCount);
        }
        return total;
    }
    public static void main(String[] args){
        Mediem477_HammingDistance obj = new Mediem477_HammingDistance();
        int[] data = new int[]{4,14,2};
        System.out.println(obj.totalHammingDistance(data));
    }
}
