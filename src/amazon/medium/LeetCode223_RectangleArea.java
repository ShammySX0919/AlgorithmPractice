package amazon.medium;

public class LeetCode223_RectangleArea {
	public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //figuring (left,top) and (right, bottom) of overlaping area, assuming there is one
        //then the formula is
        int left = Math.max(A,E), right = Math.min(C,G);
        //previous solution is too smart, this is one easy to understand
        int bottom = Math.max(B,F), top = Math.min(D,H);
        //overlap area is (right-left)*(top-bottom)
        //for the case there is no overlap,height or width should be 0
        return (C-A)*(D-B) - (right<left?0:right-left)*(top<bottom?0:top-bottom) + (G-E)*(H-F);
    }
	public static void main(String[] args){
		System.out.println(computeArea(-2,-2,2,2,-2,-2,2,2));
		System.out.println(computeArea(-1500000001,0,-1500000000,1,1500000000,0,1500000001,1));
	}
}