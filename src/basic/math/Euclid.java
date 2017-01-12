package basic.math;
/**
 * one http://introcs.cs.princeton.edu/java/23recursion/Euclid.java.html
 * https://en.wikipedia.org/wiki/Greatest_common_divisor
 * The greatest common divisor is useful for reducing fractions to be in lowest terms
 * @author Andrew Ma
 *
 */
public class Euclid {
	// recursive implementation
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }

    // non-recursive implementation
    public static int gcdInterative(int p, int q) {
        while (q != 0) {
            int temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }
    /**
     * if p and q are both non-zero, gcd(p,q)=p*q/lcm(p,q)
     * but more commonly, lcm is computed one gcd
     * @param p
     * @param q
     * @return
     */
//    public static int gcdByLcm(int p, int q){
//    	return p*q/(lcm(p,q));
//    }
    /**
     * smallest positive integer that is divisible by both a and b.
     * only meaningful when both a and b are non-zero
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a , int b){
    	return a*b/gcd(a,b);
    }
    
}
