package topic.patternsearch;

public class NaivePatternSearch {
void search(String ptn,String txt){
	int N = txt.length();
	int M = ptn.length();
	//for all valid position in txt
	for(int i=0;i<N-M;i++){
		int j;
		//starting at 0, for every character in pattern
		for(j=0;j<M;j++){
			if(txt.charAt(i+j)!=ptn.charAt(j)){
				break;
			}
		}
		if(j==M){
			System.out.println("There's a match at position "+i);
		}
	}
}
public static void main(String[] args){
	NaivePatternSearch o = new NaivePatternSearch();
	o.search("AABA", "AABAACAADAABAAABAA");
}
}
