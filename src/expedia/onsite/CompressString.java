package expedia.onsite;

public class CompressString {
public static String compressString(String input){
	StringBuilder sb = new StringBuilder();
	int cnt=1;
	char curChar=input.charAt(0);
	char prevChar = curChar;
	sb.append(curChar);
	
	for(int i=1;i<input.length();i++){
		curChar = input.charAt(i);
		if(prevChar==curChar){
			cnt++;
		}else{
			//find a new character, reset things
			sb.append(cnt);//finish up previous char
			cnt=1;
			sb.append(curChar);
			prevChar = curChar;
		}
	}
	sb.append(cnt);
	return sb.toString();
}
public static void main(String args[]){
	System.out.println(compressString("aabbbccccc"));
}

}
