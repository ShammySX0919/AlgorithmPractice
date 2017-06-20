package amazon.medium;

/**
 * focus on place value, using predefined map, to replace place value with roman symbols.
 * M:1000
 * CM:900
 * D:500
 * C:100
 * L:50
 * X:10
 * IX:9
 * VIII:8
 * VII:7
 * VI:6
 * V:5
 * IV:4
 * III:3
 * II:2
 * I:1
 */
public class LeetCode12_IntegerToRomanNumeral {
	public String intToRoman(int num) {
		if(num<1||num>3999)
			throw new RuntimeException("invlaid input");//this solution only works for 1--3999
	        //there is no zero in roman numeral, "" is for 0
        String M[] = {"", "M", "MM", "MMM"};//1000,2000,3000
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};//100,200,300,400,500,600,700,800,900
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};//10,20,30,40,50,60,70,80,90
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};//1,2,3,4,5,6,7,8,9
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}
