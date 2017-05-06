package leetcode.easy;

public class Easy458_PoorPigs {
	public static int poorPigs(int buckets, int minutesToDie,int minutesToTest){
		return (int)(Math.ceil(Math.log10(minutesToTest/minutesToDie+1)/Math.log10(buckets)));
	}
}
