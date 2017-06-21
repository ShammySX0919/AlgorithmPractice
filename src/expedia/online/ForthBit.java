package expedia.online;

public class ForthBit {
	static int get4thBit(int num){
		int tmp = num>>3;//move out 3 bits
		tmp = tmp&1;
		return tmp;
	}
public static void main(String args[]){
	System.out.println(get4thBit(8));
	System.out.println(get4thBit(4));
	System.out.println(get4thBit(32));
	System.out.println(get4thBit(12));
}
}
