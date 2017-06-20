package expedia.onsite;

public class ReverseString {
public static String reverse(String input){
	if(input==null||input.length()<=1)return input;
	StringBuilder sb = new StringBuilder();
	//or you can use array to do swap of header and tailer
	for(char c:input.toCharArray()){
		sb.insert(0, c);
	}
	return sb.toString();
}
public static String reverseWordsInSentence(String input){
	if(input==null||input.length()<=1)return input;
	String[] words = input.split(" ");
	for(int i=0;i<words.length;i++){
		words[i] = reverse(words[i]);
	}
	StringBuilder sb = new StringBuilder();
	for(String s:words){
		sb.append(s).append(" ");
	}
	sb.delete(sb.length()-1, sb.length()-1);
	return sb.toString();
}
public static void main(String args[]){
	System.out.println(reverse("this is Expedia"));
	System.out.println(reverseWordsInSentence(reverse("this is Expedia")));
}
}
